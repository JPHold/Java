package com.budd.java.util.template;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MedicalVisit implements Serializable {
	private String visitSn;//就诊内部序列号
	private String patientSn;//患者内部序列号
	private String patientDomain;//域代码
	private String patientLid;//患者本地ID
	private String patientAge;//患者年龄
	private String nativeplaceCode;//籍贯编码
	private String nativeplaceName;//籍贯名称
	private String pid;
	private String visitTimes;//就诊次数
	private String visitTypeCode;//就诊类别代码
	private String visitTypeName;//就诊类别名称
	private String orgCode;//医疗机构编码
	private String orgName;//医疗机构名称
	private String visitDoctorId;//就诊医生ID
	private String visitDoctorName;//就诊医生姓名
	private String registrationClassCode;//就诊号别代码
	private String registrationClassName;//就诊号别名称
	private String registrationMethodCode;//就诊号类代码
	private String registrationMethodName;//就诊号类名称
	private String visitStatusCode;//门诊状态代码
	private String visitStatusName;//门诊状态名称
	private String urgentLevelCode;//紧急程度代码
	private String urgentLevelName;//紧急程度名称
	private String chargeClass;//费用类别代码
	private String chargeClassName;//费用类别名称
	private BigDecimal actualFee;//实收费用
	private BigDecimal receivableFee;//应收费用
	private Date createTime;//创建时间
	private Date updateTime;//更新时间
	private BigDecimal updateCount;//更新回数
	private String deleteFlag;//删除标志
	private Date deleteTime;//删除时间
	private String createby;//创建者
	private String updateby;//修改者
	private String deleteby;//删除者
	private Date dateOfOnset;//发病日期时间
	private String visitReason;//就诊原因
	private BigDecimal years;//年龄(岁)
	private BigDecimal months;//年龄(月)
	private String requestNo;//电子申请单编号
	private String whereabout;//患者去向代码
	private String physicalSign;//体征
	private String outpRegistSn;//门诊挂号序列号
	private String mainSuit;//主诉
	private String deptName;//科室名称(开单科室名称)
	private String deptCode;//科室编码(开单科室编号)
	private Date outpDate;//门诊时间
	private String outpNum;//就诊流水号
	private String patientName;//患者姓名
	private String ageUnit;//年龄单位
	private String patientIdentity;//患者身份
	private String birthDay;//出生日期
	private String gender;//性别（男/女）
	private String chargeType;//医保类型

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getChargeType() {
		return chargeType;
	}
	
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	
	private static final long serialVersionUID = 1L;

    public String getVisitSn() {
        return visitSn;
    }

    public void setVisitSn(String visitSn) {
        this.visitSn = visitSn;
    }

    public String getPatientSn() {
        return patientSn;
    }

    public void setPatientSn(String patientSn) {
        this.patientSn = patientSn;
    }

    public String getPatientDomain() {
        return patientDomain;
    }

    public void setPatientDomain(String patientDomain) {
        this.patientDomain = patientDomain;
    }

    public String getPatientLid() {
        return patientLid;
    }

    public void setPatientLid(String patientLid) {
        this.patientLid = patientLid;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getNativeplaceCode() {
        return nativeplaceCode;
    }

    public void setNativeplaceCode(String nativeplaceCode) {
        this.nativeplaceCode = nativeplaceCode;
    }

    public String getNativeplaceName() {
        return nativeplaceName;
    }

    public void setNativeplaceName(String nativeplaceName) {
        this.nativeplaceName = nativeplaceName;
    }

    public String getVisitTimes() {
        return visitTimes;
    }

    public void setVisitTimes(String visitTimes) {
        this.visitTimes = visitTimes;
    }

    public String getVisitTypeCode() {
        return visitTypeCode;
    }

    public void setVisitTypeCode(String visitTypeCode) {
        this.visitTypeCode = visitTypeCode;
    }

    public String getVisitTypeName() {
        return visitTypeName;
    }

    public void setVisitTypeName(String visitTypeName) {
        this.visitTypeName = visitTypeName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getVisitDoctorId() {
        return visitDoctorId;
    }

    public void setVisitDoctorId(String visitDoctorId) {
        this.visitDoctorId = visitDoctorId;
    }

    public String getVisitDoctorName() {
        return visitDoctorName;
    }

    public void setVisitDoctorName(String visitDoctorName) {
        this.visitDoctorName = visitDoctorName;
    }

    public String getRegistrationClassCode() {
        return registrationClassCode;
    }

    public void setRegistrationClassCode(String registrationClassCode) {
        this.registrationClassCode = registrationClassCode;
    }

    public String getRegistrationClassName() {
        return registrationClassName;
    }

    public void setRegistrationClassName(String registrationClassName) {
        this.registrationClassName = registrationClassName;
    }

    public String getRegistrationMethodCode() {
        return registrationMethodCode;
    }

    public void setRegistrationMethodCode(String registrationMethodCode) {
        this.registrationMethodCode = registrationMethodCode;
    }

    public String getRegistrationMethodName() {
        return registrationMethodName;
    }

    public void setRegistrationMethodName(String registrationMethodName) {
        this.registrationMethodName = registrationMethodName;
    }

    public String getVisitStatusCode() {
        return visitStatusCode;
    }

    public void setVisitStatusCode(String visitStatusCode) {
        this.visitStatusCode = visitStatusCode;
    }

    public String getVisitStatusName() {
        return visitStatusName;
    }

    public void setVisitStatusName(String visitStatusName) {
        this.visitStatusName = visitStatusName;
    }

    public String getUrgentLevelCode() {
        return urgentLevelCode;
    }

    public void setUrgentLevelCode(String urgentLevelCode) {
        this.urgentLevelCode = urgentLevelCode;
    }

    public String getUrgentLevelName() {
        return urgentLevelName;
    }

    public void setUrgentLevelName(String urgentLevelName) {
        this.urgentLevelName = urgentLevelName;
    }

    public String getChargeClass() {
        return chargeClass;
    }

    public void setChargeClass(String chargeClass) {
        this.chargeClass = chargeClass;
    }

    public String getChargeClassName() {
        return chargeClassName;
    }

    public void setChargeClassName(String chargeClassName) {
        this.chargeClassName = chargeClassName;
    }

    public BigDecimal getActualFee() {
        return actualFee;
    }

    public void setActualFee(BigDecimal actualFee) {
        this.actualFee = actualFee;
    }

    public BigDecimal getReceivableFee() {
        return receivableFee;
    }

    public void setReceivableFee(BigDecimal receivableFee) {
        this.receivableFee = receivableFee;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public BigDecimal getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(BigDecimal updateCount) {
        this.updateCount = updateCount;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby;
    }

    public String getDeleteby() {
        return deleteby;
    }

    public void setDeleteby(String deleteby) {
        this.deleteby = deleteby;
    }

    public Date getDateOfOnset() {
        return dateOfOnset;
    }

    public void setDateOfOnset(Date dateOfOnset) {
        this.dateOfOnset = dateOfOnset;
    }

    public String getVisitReason() {
        return visitReason;
    }

    public void setVisitReason(String visitReason) {
        this.visitReason = visitReason;
    }

    public BigDecimal getYears() {
        return years;
    }

    public void setYears(BigDecimal years) {
        this.years = years;
    }

    public BigDecimal getMonths() {
        return months;
    }

    public void setMonths(BigDecimal months) {
        this.months = months;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getWhereabout() {
        return whereabout;
    }

    public void setWhereabout(String whereabout) {
        this.whereabout = whereabout;
    }

    public String getPhysicalSign() {
        return physicalSign;
    }

    public void setPhysicalSign(String physicalSign) {
        this.physicalSign = physicalSign;
    }

    public String getOutpRegistSn() {
        return outpRegistSn;
    }

    public void setOutpRegistSn(String outpRegistSn) {
        this.outpRegistSn = outpRegistSn;
    }

    public String getMainSuit() {
        return mainSuit;
    }

    public void setMainSuit(String mainSuit) {
        this.mainSuit = mainSuit;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public Date getOutpDate() {
        return outpDate;
    }

    public void setOutpDate(Date outpDate) {
        this.outpDate = outpDate;
    }

    public String getOutpNum() {
        return outpNum;
    }

    public void setOutpNum(String outpNum) {
        this.outpNum = outpNum;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getAgeUnit() {
        return ageUnit;
    }

    public void setAgeUnit(String ageUnit) {
        this.ageUnit = ageUnit;
    }

    public String getPatientIdentity() {
        return patientIdentity;
    }

    public void setPatientIdentity(String patientIdentity) {
        this.patientIdentity = patientIdentity;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}