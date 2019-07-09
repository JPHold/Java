package com.budd.java.util.template;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class VisitDiagnosis implements Serializable {
	
	private String  pid;
	private String  times;
	
	private String diagnosisSn;//诊断内部序列号
	private String visitSn;//就诊内部序列号
	private String documentSn;//文档内部序列号
	private String patientSn;//患者内部序列号
	private String patientDomain;//域代码
	private String patientLid;//患者本地ID
	private String orgCode;//医疗机构编码
	private String orgName;//医疗机构名称
	private String diagnosticDeptId;//西医诊断科室ID
	private String diagnosticDeptName;//西医诊断科室名称
	private String diagnosisDoctorId;//西医诊断医生ID
	private String diagnosisDoctorName;//西医诊断医生姓名
	private Date diagnosisDate;//西医诊断日期
	private String diagnosisSequence;//西医诊断顺位（从属关系）
	private String diagnosisTypeCode;//西医诊断类型代码
	private String diagnosisTypeName;//西医诊断类型名称
	private String diseaseCode;//西医疾病代码
	private String diseaseName;//西医疾病名称
	private String diagnoiseBasisCode;//西医诊断依据代码
	private String diagnoiseBasisName;//西医诊断依据
	private String mainDiagnosisFlag;//是否主要诊断(西医)
	private String uncertainFlag;//是否待查(西医)
	private String contagiousFlag;//是否传染病(西医)
	private Date createTime;//创建时间
	private Date updateTime;//更新时间
	private BigDecimal updateCount;//更新回数
	private String deleteFlag;//删除标志
	private Date deleteTime;//删除时间
	private String createby;//创建者
	private String updateby;//修改者
	private String deleteby;//删除者
	private String otherWmDiagCode;//其他西医诊断编码
	private String otherMedicalDisposal;//其他医学处置
	private String chineseMedicalTypeCode;//中药使用类别代码
	private String tcmSyndromeCode;//中医证候代码
	private String diagnosisNum;//诊断标识号
	private String docType;//上传的xml文档类型
	private String relateSn;//主表的主键
	private String tcmDiseaseCode;//中医疾病代码(中医疾病代码)
	private String tcmDiseaseName;//中医疾病名称(中医疾病名称)
	private String diagClass;//诊断类别代码(0主要诊断,1第一辅诊,2第二辅诊)
	private String diagResult;//治疗情况(1未愈,2好转,3治愈)
	private String healDoc;//治愈医生工号
	
	private static final long serialVersionUID = 1L;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getTimes() {
		return times;
	}
	
	public void setTimes(String times) {
		this.times = times;
	}
	
	public String getDiagnosisSn() {
		return diagnosisSn;
	}
	
	public void setDiagnosisSn(String diagnosisSn) {
		this.diagnosisSn = diagnosisSn;
	}
	
	public String getVisitSn() {
		return visitSn;
	}
	
	public void setVisitSn(String visitSn) {
		this.visitSn = visitSn;
	}
	
	public String getDocumentSn() {
		return documentSn;
	}
	
	public void setDocumentSn(String documentSn) {
		this.documentSn = documentSn;
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
	
	public String getDiagnosticDeptId() {
		return diagnosticDeptId;
	}
	
	public void setDiagnosticDeptId(String diagnosticDeptId) {
		this.diagnosticDeptId = diagnosticDeptId;
	}
	
	public String getDiagnosticDeptName() {
		return diagnosticDeptName;
	}
	
	public void setDiagnosticDeptName(String diagnosticDeptName) {
		this.diagnosticDeptName = diagnosticDeptName;
	}
	
	public String getDiagnosisDoctorId() {
		return diagnosisDoctorId;
	}
	
	public void setDiagnosisDoctorId(String diagnosisDoctorId) {
		this.diagnosisDoctorId = diagnosisDoctorId;
	}
	
	public String getDiagnosisDoctorName() {
		return diagnosisDoctorName;
	}
	
	public void setDiagnosisDoctorName(String diagnosisDoctorName) {
		this.diagnosisDoctorName = diagnosisDoctorName;
	}
	
	public Date getDiagnosisDate() {
		return diagnosisDate;
	}
	
	public void setDiagnosisDate(Date diagnosisDate) {
		this.diagnosisDate = diagnosisDate;
	}
	
	public String getDiagnosisSequence() {
		return diagnosisSequence;
	}
	
	public void setDiagnosisSequence(String diagnosisSequence) {
		this.diagnosisSequence = diagnosisSequence;
	}
	
	public String getDiagnosisTypeCode() {
		return diagnosisTypeCode;
	}
	
	public void setDiagnosisTypeCode(String diagnosisTypeCode) {
		this.diagnosisTypeCode = diagnosisTypeCode;
	}
	
	public String getDiagnosisTypeName() {
		return diagnosisTypeName;
	}
	
	public void setDiagnosisTypeName(String diagnosisTypeName) {
		this.diagnosisTypeName = diagnosisTypeName;
	}
	
	public String getDiseaseCode() {
		return diseaseCode;
	}
	
	public void setDiseaseCode(String diseaseCode) {
		this.diseaseCode = diseaseCode;
	}
	
	public String getDiseaseName() {
		return diseaseName;
	}
	
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}
	
	public String getDiagnoiseBasisCode() {
		return diagnoiseBasisCode;
	}
	
	public void setDiagnoiseBasisCode(String diagnoiseBasisCode) {
		this.diagnoiseBasisCode = diagnoiseBasisCode;
	}
	
	public String getDiagnoiseBasisName() {
		return diagnoiseBasisName;
	}
	
	public void setDiagnoiseBasisName(String diagnoiseBasisName) {
		this.diagnoiseBasisName = diagnoiseBasisName;
	}
	
	public String getMainDiagnosisFlag() {
		return mainDiagnosisFlag;
	}
	
	public void setMainDiagnosisFlag(String mainDiagnosisFlag) {
		this.mainDiagnosisFlag = mainDiagnosisFlag;
	}
	
	public String getUncertainFlag() {
		return uncertainFlag;
	}
	
	public void setUncertainFlag(String uncertainFlag) {
		this.uncertainFlag = uncertainFlag;
	}
	
	public String getContagiousFlag() {
		return contagiousFlag;
	}
	
	public void setContagiousFlag(String contagiousFlag) {
		this.contagiousFlag = contagiousFlag;
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
	
	public String getOtherWmDiagCode() {
		return otherWmDiagCode;
	}
	
	public void setOtherWmDiagCode(String otherWmDiagCode) {
		this.otherWmDiagCode = otherWmDiagCode;
	}
	
	public String getOtherMedicalDisposal() {
		return otherMedicalDisposal;
	}
	
	public void setOtherMedicalDisposal(String otherMedicalDisposal) {
		this.otherMedicalDisposal = otherMedicalDisposal;
	}
	
	public String getChineseMedicalTypeCode() {
		return chineseMedicalTypeCode;
	}
	
	public void setChineseMedicalTypeCode(String chineseMedicalTypeCode) {
		this.chineseMedicalTypeCode = chineseMedicalTypeCode;
	}
	
	public String getTcmSyndromeCode() {
		return tcmSyndromeCode;
	}
	
	public void setTcmSyndromeCode(String tcmSyndromeCode) {
		this.tcmSyndromeCode = tcmSyndromeCode;
	}
	
	public String getDiagnosisNum() {
		return diagnosisNum;
	}
	
	public void setDiagnosisNum(String diagnosisNum) {
		this.diagnosisNum = diagnosisNum;
	}
	
	public String getDocType() {
		return docType;
	}
	
	public void setDocType(String docType) {
		this.docType = docType;
	}
	
	public String getRelateSn() {
		return relateSn;
	}
	
	public void setRelateSn(String relateSn) {
		this.relateSn = relateSn;
	}
	
	public String getTcmDiseaseCode() {
		return tcmDiseaseCode;
	}
	
	public void setTcmDiseaseCode(String tcmDiseaseCode) {
		this.tcmDiseaseCode = tcmDiseaseCode;
	}
	
	public String getTcmDiseaseName() {
		return tcmDiseaseName;
	}
	
	public void setTcmDiseaseName(String tcmDiseaseName) {
		this.tcmDiseaseName = tcmDiseaseName;
	}
	
	public String getDiagClass() {
		return diagClass;
	}
	
	public void setDiagClass(String diagClass) {
		this.diagClass = diagClass;
	}
	
	public String getDiagResult() {
		return diagResult;
	}
	
	public void setDiagResult(String diagResult) {
		this.diagResult = diagResult;
	}
	
	public String getHealDoc() {
		return healDoc;
	}
	
	public void setHealDoc(String healDoc) {
		this.healDoc = healDoc;
	}
}