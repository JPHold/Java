<#escape x as x!"">
<PRPA_HIP1000 xmlns="urn:hl7-org:v3"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" ITSVersion="XML_1.0"
              xsi:schemaLocation="urn:hl7-org:v3">
    <!--id-消息流水号 -->
    <id extension="${id}"/>
    <!--creationTime-消息创建时间 -->
    <creationTime value="${(creationTime?string("yyyyMMddHHmmss"))!''}"/>
    <!--interactionId-消息的服务标识 -->
    <interactionId root="2.16.840.1.113883.1.6" extension="PRPA_HIP1000"/>
    <!--processingCode-处理代码。标识此消息是否是产品、训练、调试系统的一部分。D：调试；P：产品；T：训练 -->
    <processingCode code="P"/>
    <!-- processingModeCode-处理模型代码。定义此消息是一个文档处理还是一个初始装载的一部分。A： 存档；I：初始装载；R：从存档中恢复；T：当前处理，间隔传递。 -->
    <processingModeCode/>
    <!-- acceptAckCode-接收确认类型 AL：总是确认；NE：从不确认；ER：仅在错误/或拒绝时确认；SU： 仅在成功完成时确认。 -->
    <acceptAckCode code="AL"/>
    <!--接收方编号请向集成平台提供商获取-->
    <receiver typeCode="RCV">
        <device classCode="DEV" determinerCode="INSTANCE">
            <id>
                <item extension="${senderId}"/>
            </id>
        </device>
    </receiver>
    <!--发送方编号请向集成平台提供商获取-->
    <sender typeCode="SND">
        <device classCode="DEV" determinerCode="INSTANCE">
            <id>
                <item extension="${receiverId}"/>
            </id>
        </device>
    </sender>
    <controlActProcess classCode="STC" moodCode="EVN">
        <code code="InpatientEncounterStarted">
            <displayName value="门诊就诊新增"/>
        </code>
    <#if medicalVisit ??>
		<subject typeCode="SUBJ">
            <encounterEvent classCode="ENC" moodCode="EVN">
                <!-- 门诊时间 -->
                <time value="${(medicalVisit.outpDate?string("yyyyMMddHHmmss"))!''}"></time>
                <id>
                    <!--就诊流水号 -->
                    <item root="2.16.156.10011.1.10" extension="${medicalVisit.outpNum}"/>
                </id>
                <!--就诊类别代码 -->
                <code code="${medicalVisit.visitTypeCode}" codeSystem="2.16.156.10011.2.3.1.271"
                      codeSystemName="患者类型代码表">
                    <displayName value="${medicalVisit.visitTypeName}"/>
                </code>
                <statusCode/>
                <!--发病日期时间 -->
                <effectiveTime>
                    <low value="${(medicalVisit.outpDate?string("yyyyMMddHHmmss"))!''}"/>
                </effectiveTime>
                <!--就诊原因 -->
                <reasonCode>
                    <item>
                        <originalText value="${medicalVisit.visitReason}"/>
                    </item>
                </reasonCode>
                <!-- 号别字典：;普通号;急诊号;知名专家教授;正主任医师;副主任医师;主治医师;优惠号;灌氧气;开麻醉药;附加费用; -->
                <registrationClass code="${medicalVisit.registrationClassCode}"
                                   value="${medicalVisit.registrationClassName}"/>
                <!-- 号类字典：;门诊号;功能社区预约号;电话预约号;自助挂号;诊间预约号;共同体预约号;诊间加号;114挂号; -->
                <registrationMethod code="${medicalVisit.registrationMethodCode}"
                                    value="${medicalVisit.registrationMethodName}"/>
                <!--就诊次数 -->
                <lengthOfStayQuantity unit="次" value="${medicalVisit.visitTimes}"/>
                <!--患者 -->
                <subject typeCode="SBJ">
                    <patient classCode="PAT">
                        <!-- 域ID -->
                        <id root="1.2.156.112635.1.2.1.2" extension="45586017-0" />

                        <id>
                            <!--PatientID -->
                            <item extension="${medicalVisit.patientLid}"/>
                        </id>
                        <patientPerson>
                            <!--患者身份证号标识-->
                            <id root="2.16.156.10011.1.3" extension="${medicalVisit.patientIdentity}"/>
                            <!--姓名 -->
                            <name xsi:type="DSET_EN">
                                <item>
                                    <part value="${medicalVisit.patientName}"/>
                                </item>
                            </name>
                            <!--年龄-->
                            <age unit="${medicalVisit.ageUnit}" value="${medicalVisit.years}"></age>
                            <!--出生日期-->
                            <birthTime value="${medicalVisit.birthDay}"/>
                            <!--性别-->
                            <administrativeGenderCode code="${medicalVisit.gender}"
                                                      codeSystem="2.16.156.10011.2.3.3.4"
                                                      codeSystemName="生理性别代码表（GB/T 2261.1）"/>
                        </patientPerson>
                        <!-- 籍贯编码+籍贯名称 -->
                        <nativeplace>
                            <item code="${medicalVisit.nativeplaceCode}" value="${medicalVisit.nativeplaceName}"
                                  displayName="籍贯"></item>
                        </nativeplace>
                        <!--医保类型-->
                        <chargeType value="${prescription.chargeType}"/>
                    </patient>
                </subject>


                <!-- 就诊医师签名 -->
                <authenticator>
                    <time/>
                    <signatureCode/>
                    <assignedEntity>
                        <id>
                            <item root="2.16.156.10011.1.4" extension="${medicalVisit.visitDoctorId}"/>
                        </id>
                        <code displayName="就诊医师"/>
                        <assignedPerson>
                            <name>${medicalVisit.visitDoctorName}</name>
                        </assignedPerson>
                    </assignedEntity>
                </authenticator>


                <!--服务场所（机构+科室） -->
                <location typeCode="LOC">
                    <time/>
                    <statusCode code="active"/>
                    <serviceDeliveryLocation classCode="SDLOC">
                        <location classCode="PLC" determinerCode="INSTANCE">
                            <id>
                                <item root="2.16.156.10011.1.26" extension="${medicalVisit.deptCode}"/>
                            </id>
                            <name xsi:type="DSET_EN">
                                <item>
                                    <part value="${medicalVisit.deptName}"/>
                                </item>
                            </name>
                            <locatedEntityHasParts classCode="LOCE">
                                <locatedPlace classCode="PLC" determinerCode="INSTANCE">
                                    <id>
                                        <item root="2.16.156.10011.1.27" extension=""/>
                                    </id>
                                    <name xsi:type="DSET_EN">
                                        <item>
                                            <part value=""/>
                                        </item>
                                    </name>
                                    <locatedEntityHasParts classCode="LOCE">
                                        <locatedPlace classCode="PLC" determinerCode="INSTANCE">
                                            <id>
                                                <item root="2.16.156.10011.1.21" extension=""/>
                                            </id>
                                            <name xsi:type="DSET_EN">
                                                <item>
                                                    <part value=""/>
                                                </item>
                                            </name>
                                            <locatedEntityHasParts classCode="LOCE">
                                                <locatedPlace classCode="PLC" determinerCode="INSTANCE">
                                                    <id>
                                                        <item root="2.16.156.10011.1.22" extension=""/>
                                                    </id>
                                                    <name xsi:type="DSET_EN">
                                                        <item>
                                                            <part value=""/>
                                                        </item>
                                                    </name>
                                                </locatedPlace>
                                            </locatedEntityHasParts>
                                        </locatedPlace>
                                    </locatedEntityHasParts>
                                </locatedPlace>
                            </locatedEntityHasParts>
                        </location>
                        <!--医疗机构 -->
                        <custodian typeCode="CST">
                            <assignedCustodian classCode="ASSIGNED">
                                <representedCustodianOrganization
                                        classCode="ORG" determinerCode="INSTANCE">
                                    <!--医疗卫生服务机构标识 OID 表D.2 可维护对象OID分配表 -->
                                    <id root="2.16.156.10011.1.5" extension="45586017-0"/>
                                    <name>广州中医药大学第一附属医院</name>
                                </representedCustodianOrganization>
                            </assignedCustodian>
                        </custodian>
                    </serviceDeliveryLocation>
                </location>

                <visitStatus>
                    <!-- 门诊状态代码 -->
                    <visitStatusCode code="${medicalVisit.visitStatusCode}" displayName="门诊状态代码"/>
                    <!-- 门诊状态名称 -->
                    <visitStatusName value="${medicalVisit.visitStatusName}" displayName="门诊状态名称"></visitStatusName>
                </visitStatus>
                <urgentLevel>
                    <urgentLevelCode code="${medicalVisit.urgentLevelCode}" displayName="紧急程度代码"/>
                    <urgentLevelName value="${medicalVisit.urgentLevelName}" displayName="紧急程度名称"/>
                </urgentLevel>
                <!--电子申请单号-->
                <requestNo value="${medicalVisit.requestNo}"/>

                <!--主诉章节 -->
                <component displayName="主诉条目">
                    <section>
                        <code code="10154-3" displayName="CHIEF COMPLAINT"
                              codeSystem="2.16.840.1.113883.6.1" codeSystemName="LOINC"/>
                        <!-- 体征 -->
                        <text displayName="体征">${medicalVisit.physicalSign}</text>
                        <!--主诉条目 -->
                        <entry>
                            <observation classCode="OBS" moodCode="EVN">
                                <code code="DE04.01.119.00" codeSystem="2.16.156.10011.2.2.1"
                                      codeSystemName="卫生信息数据元目录" displayName="主诉"/>
                                <value xsi:type="ST">${medicalVisit.mainSuit}</value>
                            </observation>
                        </entry>

                    </section>
                </component>
                <!--费用章节 -->
                <component>
                    <section>
                        <!--费用类别 -->
                        <entry displayName="费用类别">
                            <observation classCode="OBS" moodCode="EVN">
                                <code code="${medicalVisit.mainSuit}" displayName="费用类别代码"/>
                                <value xsi:type="ST">${medicalVisit.mainSuit}</value>
                            </observation>
                        </entry>
                        <entry>
                            <observation classCode="OBS" moodCode="EVN">
                                <!-- 实收费用 -->
                                <actual unit="元" value="${medicalVisit.actualFee}" displayName="实收费用"/>
                                <!-- 应收费用 -->
                                <receivable unit="元" value="${medicalVisit.receivableFee}" displayName="应收费用"/>
                            </observation>
                        </entry>
                        <whereabout code="${medicalVisit.whereabout}" displayName="患者去向代码"></whereabout>
                    </section>
                </component>
                <!-- 诊断（可循环）-->
                <!-- 诊断章节 -->
                <component displayName="诊断章节">
                    <section>
                        <!--诊断-西医条目-->
                        <entry displayName="西医诊断">
                            <observation classCode="OBS" moodCode="EVN">

                                <code code="DE05.01.025.00" codeSystem="2.16.156.10011.2.2.1"
                                      codeSystemName="卫生信息数据元目录" displayName="西医诊断名称"/>
                                <!--诊断日期-->
                                <effectiveTime value="${(visitDiagnosis.diagnosisDate?string("yyyyMMddHHmmss"))!''}"/>
                                <!-- 诊断医师信息 -->
                                <performer typeCode="PRF ">
                                    <assignedEntity>
                                        <!-- 诊断医生编码 -->
                                        <id root="1.2.156.112635.1.1.2" extension="${visitDiagnosis.diagnosisDoctorId}"/>
                                        <assignedPerson determinerCode="INSTANCE"
                                                        classCode="PSN">
                                            <!-- 诊断医生名称 -->
                                            <name>${visitDiagnosis.diagnosisDoctorName}</name>
                                        </assignedPerson>
                                        <representedOrganization classCode="ORG"
                                                                 determinerCode="INSTANCE">
                                            <!-- 诊断科室编码 -->
                                            <id root="1.2.156.112635.1.1.1" extension="${visitDiagnosis.diagnosticDeptId}"/>
                                            <!-- 诊断科室名称 -->
                                            <name>${visitDiagnosis.diagnosticDeptName}</name>
                                        </representedOrganization>
                                    </assignedEntity>
                                </performer>

                                <!--诊断类别 -->
                                <code code="${visitDiagnosis.diagnosisTypeCode}" codeSystem="1.2.156.112635.1.1.29">
                                    <displayName value="${visitDiagnosis.diagnosisTypeName}"/>
                                </code>

                                <!-- 诊断依据 -->
                                <entryRelationship>
                                    <observation classCode="OBS" moodCode="EVN">
                                        <code code="DE05.01.070.00" codeSystem="2.16.156.10011.2.2.1"
                                              codeSystemName="卫生信息数据元目录 " displayName="诊断依据代码"></code>
                                        <value xsi:type="ST" code="${visitDiagnosis.diagnoiseBasisCode}">${visitDiagnosis.diagnoiseBasisName}</value>
                                    </observation>
                                </entryRelationship>

                                <entryRelationship>
                                    <observation classCode="OBS" moodCode="EVN">
                                        <code displayName="是否主要诊断"/>
                                        <value xsi:type="BL" value="${visitDiagnosis.mainDiagnosisFlag}"/>
                                    </observation>
                                </entryRelationship>

                                <entryRelationship>
                                    <observation classCode="OBS" moodCode="EVN">
                                        <code displayName="是否待查"/>
                                        <value xsi:type="BL" value="${visitDiagnosis.uncertainFlag}"/>
                                    </observation>
                                </entryRelationship>

                                <entryRelationship>
                                    <observation classCode="OBS" moodCode="EVN">
                                        <code displayName="是否传染病"/>
                                        <value xsi:type="BL" value="${visitDiagnosis.contagiousFlag}"/>
                                    </observation>
                                </entryRelationship>

                                <!-- 诊断标识号 -->
                                <diagnosisNum value="${visitDiagnosis.diagnosisNum}"/>

                                <value xsi:type="ST">${visitDiagnosis.diseaseName}</value>
                                <entryRelationship typeCode="COMP">
                                    <observation classCode="OBS" moodCode="EVN">
                                        <!--诊断-西医诊断编码-代码-->
                                        <code code="DE05.01.024.00" codeSystem="2.16.156.10011.2.2.1"
                                              codeSystemName="卫生信息数据元目录" displayName="诊断-西医诊断编码"/>
                                        <value xsi:type="CD" code="${visitDiagnosis.diseaseCode}" displayName="${visitDiagnosis.diseaseName}"
                                               codeSystem="2.16.156.10011.2.3.3.11" codeSystemName="ICD-10"/>
                                    </observation>
                                </entryRelationship>

                                <entryRelationship>
                                    <observation classCode="OBS" moodCode="EVN">
                                        <code code="DE05.01.024.00" displayName="其他西医诊断编码"
                                              codeSystem="2.16.156.10011.2.2.1" codeSystemName="卫生信息数据元目录">
                                            <qualifier>
                                                <name displayName="其他西医诊断编码"/>
                                            </qualifier>
                                        </code>
                                        <value xsi:type="CD" code="${visitDiagnosis.otherWmDiagCode}" displayName="其他西医诊断编码"
                                               codeSystem="2.16.156.10011.2.3.3.11" codeSystemName="ICD-10"/>
                                    </observation>
                                </entryRelationship>
                                <!--诊断顺位-->
                                <entryRelationship typeCode="COMP">
                                    <observation classCode="OBS" moodCode="EVN">
                                        <code code="DE05.01.080.00" codeSystem="2.16.156.10011.2.2.1"
                                              codeSystemName="卫生信息数据元目录" displayName="诊断顺位"/>
                                        <value xsi:type="INT" value="${visitDiagnosis.diagnosisSequence}"/>
                                    </observation>
                                </entryRelationship>
                                <!--其他医学处置-->
                                <entryRelationship typeCode="COMP">
                                    <observation classCode="OBS" moodCode="EVN">
                                        <code code="DE06.00.251.00" displayName="其他医学处置"
                                              codeSystem="2.16.156.10011.2.2.1" codeSystemName="卫生信息数据元目录"/>
                                        <value xsi:type="ST">${visitDiagnosis.otherMedicalDisposal}</value>
                                    </observation>
                                </entryRelationship>

                            </observation>
                        </entry>
                        <!--诊断-中医条目-->
                        <entry displayName="中医诊断">
                            <observation classCode="OBS" moodCode="EVN">

                                <code code="DE05.10.172.00" codeSystem="2.16.156.10011.2.2.1"
                                      codeSystemName="卫生信息数据元目录" displayName="诊断-中医病名代码"/>

                                <!--诊断日期-->
                                <effectiveTime value="${(visitDiagnosis.diagnosisDate?string("yyyyMMddHHmmss"))!''}"/>

                                <!--中药使用类别-->
                                <entryRelationship typeCode="COMP">
                                    <observation classCode="OBS" moodCode="EVN">
                                        <code code="DE06.00.164.00" codeSystem="2.16.156.10011.2.2.1"
                                              codeSystemName="卫生信息数据元目录" displayName="中药使用类别代码"/>
                                        <!--中药使用类别代码-->
                                        <value code="${visitDiagnosis.chineseMedicalTypeCode}" displayName="中药使用类别代码" codeSystem="2.16.156.10011.2.3.1.157"
                                               codeSystemName="中药使用类别代码表" xsi:type="CD"/>
                                    </observation>
                                </entryRelationship>
                                <value xsi:type="ST">${visitDiagnosis.tcmDiseaseName}</value>
                                <entryRelationship typeCode="COMP">
                                    <observation classCode="OBS" moodCode="EVN">
                                        <!--诊断-中医诊断编码-代码-->
                                        <code code="DE05.10.130.00" codeSystem="2.16.156.10011.2.2.1"
                                              codeSystemName="卫生信息数据元目录" displayName="诊断-中医病名代码"/>
                                        <value xsi:type="CD" code="${visitDiagnosis.tcmDiseaseCode}" displayName="${visitDiagnosis.tcmDiseaseName}"
                                               codeSystem="2.16.156.10011.2.3.3.14"
                                               codeSystemName="中医病证分类与代码表( GB/T 15657)"/>
                                    </observation>
                                </entryRelationship>
                                <entryRelationship typeCode="COMP">
                                    <observation classCode="OBS" moodCode="EVN">
                                        <!--诊断-中医证候编码-名称-->
                                        <code code="DE05.10.172.00" codeSystem="2.16.156.10011.2.2.1"
                                              codeSystemName="卫生信息数据元目录" displayName="诊断-中医证候名称"/>
                                        <value xsi:type="ST"></value>
                                    </observation>
                                </entryRelationship>
                                <entryRelationship typeCode="COMP">
                                    <observation classCode="OBS" moodCode="EVN">
                                        <!--诊断-中医证候编码-代码-->
                                        <code code="DE05.10.130.00" codeSystem="2.16.156.10011.2.2.1"
                                              codeSystemName="卫生信息数据元目录" displayName="诊断-中医证候代码"/>
                                        <value xsi:type="CD" code="${visitDiagnosis.tcmSyndromeCode}" displayName="中医证候代码"
                                               codeSystem="2.16.156.10011.2.3.3.14"
                                               codeSystemName="中医病证分类与代码表( GB/T 15657)"/>
                                    </observation>
                                </entryRelationship>
                                <!--诊断顺位-->
                                <entryRelationship typeCode="COMP">
                                    <observation classCode="OBS" moodCode="EVN">
                                        <code code="DE05.01.080.00" codeSystem="2.16.156.10011.2.2.1"
                                              codeSystemName="卫生信息数据元目录" displayName="诊断顺位"/>
                                        <value xsi:type="INT" value="${visitDiagnosis.diagnosisSequence}"/>
                                    </observation>
                                </entryRelationship>

                            </observation>
                        </entry>
                    </section>
                </component>
                <!-- 处方章节(可循环) -->
                <component displayName="处方章节">
                    <section>
                        <code code="18776-5" displayName="TREATMENT PLAN" codeSystem="2.16.840.1.113883.6.1"
                              codeSystemName="LOINC"/>
                        <text/>
                        <!-- 开方医生签名 -->
                        <authenticator displayName="开方医生">
                            <time value=""/>
                            <signatureCode/>
                            <assignedEntity>
                                <id root="2.16.156.10011.1.4" extension="${prescription.reviewPersonId}"/>
                                <code displayName="处方审核药剂师"/>
                                <assignedPerson classCode="PSN" determinerCode="INSTANCE">
                                    <name>${prescription.reviewPersonName}</name>
                                </assignedPerson>
                            </assignedEntity>
                        </authenticator>
                        <!-- 录方医生签名 -->
                        <authenticator displayName="录方医生">
                            <time/>
                            <signatureCode/>
                            <assignedEntity>
                                <id root="2.16.156.10011.1.4" extension="${prescription.enteredBy}"/>
                                <code displayName="录方人"/>
                                <assignedPerson classCode="PSN" determinerCode="INSTANCE">
                                    <name>${prescription.reviewPersonName}</name>
                                </assignedPerson>
                            </assignedEntity>
                        </authenticator>
                        <!--处方编号-->
                        <id root="2.16.156.10011.1.20" extension="${prescription.prescriptionNo}"/>
                        <!-- 诊断信息 -->
                        <diagnosis>${prescription.diagnosis}</diagnosis>
                        <!-- 是否药观标识 -->
                        <flag code="1" displayName="是否药观标识"/>
                        <dosage value="${prescription.dosage}" displayName="剂数"/>
                        <!--实付费用-->
                        <fee value="${prescription.actualFee}" unit="元"></fee>
                        <!-- 开方日期 -->
                        <time value="${(prescription.orderTime?string("yyyyMMddHHmmss"))!''}"/>
                        <!-- 发药药局 -->
                        <dispensary>${prescription.dispensary}</dispensary>
                        <!-- 发票号 -->
                        <receiptNo value="${prescription.receiptNo}"/>
                        <!--患者类型("00" 普通 "01" 特需)-->
                        <patientType value="${prescription.patientType}"/>
                        <!--处方属性-->
                        <prescAttr value="${prescription.prescAttr}"/>
                        <!--窗口号-->
                        <windowNo>${prescription.windowNo}</windowNo>
                        <!--处方备注信息 -->
                        <entry displayName="处方备注信息">
                            <observation classCode="OBS" moodCode="EVN">
                                <code code="DE06.00.179.00" displayName="处方备注信息"
                                      codeSystem="2.16.156.10011.2.2.1" codeSystemName="卫生信息数据元目录"/>
                                <value xsi:type="ST">${prescription.comments}</value>
                            </observation>
                        </entry>
                        <!--治则治法 -->
                        <entry displayName="治则治法">
                            <observation classCode="OBS" moodCode="EVN">
                                <code code="DE06.00.300.00" displayName="治则治法"
                                      codeSystem="2.16.156.10011.2.2.1" codeSystemName="卫生信息数据元目录"/>
                                <value xsi:type="ST">${prescription.theraTreat}</value>
                            </observation>
                        </entry>
                        <!-- 处方类别代码 DE08.50.032.00 处方类别代码 -->
                        <entry displayName="处方类别">
                            <observation classCode="OBS" moodCode="EVN">
                                <code code="DE08.50.032.00" displayName="处方类别代码"
                                      codeSystem="2.16.156.10011.2.2.1" codeSystemName="卫生信息数据元目录"/>
                                <value xsi:type="CD" code="${prescription.prescriptionClassCode}"
                                       displayName="${prescription.prescriptionClassName}"
                                       codeSystem="2.16.156.10011.2.3.2.40" codeSystemName="处方类别代码表"/>
                            </observation>
                        </entry>
                        <!-- 处方类型代码 -->
                        <entry displayName="处方类型">
                            <observation classCode="OBS" moodCode="EVN">
                                <value xsi:type="CD" value="${prescription.prescriptionTypeName}"
                                       code="${prescription.prescriptionTypeCode}" displayName="${prescription.prescriptionTypeName}"/>
                            </observation>
                        </entry>
                        <!-- 药观药物代码 -->
                        <entry displayName="药观药物">
                            <observation classCode="OBS" moodCode="EVN">
                                <value xsi:type="CD" code="${prescription.medViewCode}"
                                       value="${prescription.medViewName}" displayName="药观药物名称"/>
                            </observation>
                        </entry>
                        <!--费用 -->
                        <entry displayName="处方药品金额">
                            <observation classCode="OBS" moodCode="EVN">
                                <code code="DE07.00.004.00" displayName="处方药品金额" codeSystem="2.16.156.10011.2.2.1"
                                      codeSystemName="卫生信息数据元目录"/>
                                <value xsi:type="MO" value="${prescription.price}" currency="元"/>
                            </observation>
                        </entry>
                        <!--处方有效天数 -->
                        <entry displayName="处方有效天数">
                            <observation classCode="OBS" moodCode="EVN">
                                <code code="DE06.00.294.00" displayName="处方有效天数"
                                      codeSystem="2.16.156.10011.2.2.1" codeSystemName="卫生信息数据元目录"/>
                                <value xsi:type="PQ" value="${prescription.validdays}" unit="天"/>
                            </observation>
                        </entry>
                        <#if prescriptionDetailList ??>
                            <#list prescriptionDetailList as prescriptionDetailL>
                                <prescription displayName="处方用药明细">
                                    <detail>
                                        <!-- 医嘱编号 非必填 -->
                                        <id root="2.16.156.10011.1.28" extension="${prescriptionDetailL.adviceCode}" displayName="医嘱编号"/>
                                        <drugCode value="${prescriptionDetailL.adviceCode}" displayName="药物编码"/>
                                        <drugName value="${prescriptionDetailL.drugName}" displayName="药物通用名称"/>
                                        <dosage value="${prescriptionDetailL.dosage}" displayName="剂量"/>
                                        <dosageUnit value="${prescriptionDetailL.dosageUnit}" displayName="剂量单位"/>
                                        <totalDosage value="${prescriptionDetailL.totalDosage}" displayName="药物使用-总用量"/>
                                        <totalDosageUnit value="${prescriptionDetailL.totalDosageUnit}" displayName="药物使用-总用量代码"/>
                                        <drugSpec value="${prescriptionDetailL.drugSpec}" displayName="药物规格"/>
                                        <drugFrequency value="${prescriptionDetailL.drugFrequency}" displayName="药物使用-频率代码"/>
                                        <drugFreqName value="${prescriptionDetailL.drugFreqName}" displayName="药物使用-频率名称"/>
                                        <routeCode value="${prescriptionDetailL.routeCode}" displayName="用药途径代码"/>
                                        <routeName value="${prescriptionDetailL.routeName}" displayName="用药途径名称"/>
                                        <drugTypeCode value="${prescriptionDetailL.drugTypeCode}" displayName="药物类型代码"/>
                                        <drugTypeName value="${prescriptionDetailL.drugTypeName}" displayName="药物类型名称"/>
                                        <prescriptionDetailNo value="${prescriptionDetailL.prescriptionDetailNo}" displayName="处方细项号"/>

                                        <packageSpec value="${prescriptionDetailL.packageSpec}" displayName="药品包装规格"/>
                                        <packageUnits value="${prescriptionDetailL.packageUnits}" displayName="药品包装单位"/>
                                        <additionusage value="${prescriptionDetailL.additionusage}" displayName="补充用法"/>
                                        <rcptRemark value="${prescriptionDetailL.rcptRemark}" displayName="处方明细备注信息"/>

                                        <!--费用-->
                                        <entry>
                                            <observation classCode="OBS" moodCode="EVN">
                                                <code code="DE07.00.004.00" displayName="处方药品金额"
                                                      codeSystem="2.16.156.10011.2.2.1" codeSystemName="卫生信息数据元目录"/>
                                                <value xsi:type="MO" value="${prescriptionDetailL.price}" currency="元"/>
                                            </observation>
                                        </entry>
                                        <!--实付费用-->
                                        <fee value="${prescriptionDetailL.actualFee}" unit="元"></fee>

                                        <!--中药饮片处方-->
                                        <entry>
                                            <observation classCode="OBS" moodCode="EVN ">
                                                <code code="DE08.50.049.00" displayName="中药饮片处方"
                                                      codeSystem="2.16.156.10011.2.2.1" codeSystemName="卫生信息数据元目录"/>
                                                <value xsi:type="ST" displayName="中药饮片处方">${prescriptionDetailL.chineseHerbalpieces}</value>

                                                <!--中药饮片煎煮法-->
                                                <entryRelationship typeCode="COMP">
                                                    <observation classCode="OBS" moodCode="EVN ">
                                                        <code code="DE08.50.047.00" displayName="中药饮片煎煮法"
                                                              codeSystem="2.16.156.10011.2.2.1" codeSystemName="卫生信息数据元目录"/>
                                                        <value xsi:type="ST" displayName="中药饮片煎煮法">${prescriptionDetailL.decoctingMethodDesc}</value>
                                                    </observation>
                                                </entryRelationship>
                                                <!--中药用药方法-->
                                                <entryRelationship typeCode="COMP">
                                                    <observation classCode="OBS" moodCode="EVN ">
                                                        <code code="DE06.00.136.00" displayName="中药用药法"
                                                              codeSystem="2.16.156.10011.2.2.1" codeSystemName="卫生信息数据元目录"/>
                                                        <value xsi:type="ST" displayName="中药用药法">${prescriptionDetailL.medicationMethodDesc}</value>
                                                    </observation>
                                                </entryRelationship>
                                            </observation>
                                        </entry>
                                        <antibacterial_drugs>
                                            <!--是否电子处方-->
                                            <drsFlag value="${prescriptionDetailL.drsFlag}"/>
                                            <!--生产厂家-->
                                            <drugManuf code="${prescriptionDetailL.drugManufNo}" value="${prescriptionDetailL.drugManuf}"/>
                                            <!--组号-->
                                            <drugGroup value="${prescriptionDetailL.drugGroup}"/>
                                            <!--单次量单位-->
                                            <doseUom value="${prescriptionDetailL.doseUom}"/>
                                            <!--给药途径代码-->
                                            <adminCode value="${prescriptionDetailL.adminCode}"/>
                                            <!--服药天数-->
                                            <duration value="${prescriptionDetailL.duration}"/>
                                            <!--是否做过皮试-->
                                            <skinTestFlag value="${prescriptionDetailL.skinTestFlag}"/>
                                            <!--与收费信息关联号-->
                                            <chargesRelate value="${prescriptionDetailL.chargesRelate}"/>
                                            <!--与发药信息关联号-->
                                            <dispRelate value="${prescriptionDetailL.dispRelate}"/>
                                            <!--处方状态-->
                                            <ordState value="${prescriptionDetailL.ordState}"/>
                                            <!--记录更新时间-->
                                            <refDate value="${prescriptionDetailL.refDate}"/>
                                            <!--单次量-->
                                            <dosageone value="${prescriptionDetailL.dosageone}"/>
                                            <!--开药医生工号-->
                                            <prescDoc value="${prescriptionDetailL.prescDoc}"/>
                                            <!--开药科室编码-->
                                            <prescSpec value="${prescriptionDetailL.prescSpec}"/>
                                            <!--单日服用次数-->
                                            <freq value="${prescriptionDetailL.freq}"/>
                                            <!--处方序号（药品序号）-->
                                            <prescSequence value="${prescriptionDetailL.prescSequence}"/>
                                        </antibacterial_drugs>
                                        <!-- 处方类型代码 -->
                                        <entry displayName="处方类型">
                                            <observation classCode="OBS" moodCode="EVN">
                                                <value xsi:type="CD" value="${prescriptionDetailL.prescriptionTypeName}"
                                                       code="${prescriptionDetailL.prescriptionTypeCode}" displayName="${prescriptionDetailL.prescriptionTypeName}"/>
                                            </observation>
                                        </entry>
                                    </detail>
                                </prescription>
                            </#list>
                        </#if>
                    </section>
                </component>
                <!--过敏史章节-->
                <component displayName="过敏史章节">
                    <section>
                        <code code="48765-2" displayName="Allergies, adverse reactions, alerts"
                              codeSystem="2.16.840.1.113883.6.1" codeSystemName="LOINC"/>
                        <text/>
                        <entry typeCode="DRIV">
                            <act classCode="ACT" moodCode="EVN">
                                <code/>
                                <entryRelationship typeCode="SUBJ">
                                    <observation classCode="OBS" moodCode="EVN">
                                        <code code="DE02.10.023.00" codeSystem="2.16.156.10011.2.2.1"
                                              codeSystemName="卫生信息数据元目录" displayName="过敏史标志"/>
                                        <value xsi:type="BL" value="false"/>
                                        <participant typeCode="CSM">
                                            <participantRole classCode="MANU">
                                                <playingEntity classCode="MMAT">
                                                    <!--过敏史描述 -->
                                                    <code code="DE02.10.022.00" codeSystem="2.16.156.10011.2.2.1"
                                                          codeSystemName="卫生信息数据元目录" displayName="过敏史"/>
                                                    <desc xsi:type="ST"></desc>
                                                </playingEntity>
                                            </participantRole>
                                        </participant>
                                    </observation>
                                </entryRelationship>
                            </act>
                        </entry>
                    </section>
                </component>

            </encounterEvent>
        </subject>
    </#if>
    </controlActProcess>
</PRPA_HIP1000>
</#escape>