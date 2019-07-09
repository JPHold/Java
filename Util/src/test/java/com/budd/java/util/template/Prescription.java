package com.budd.java.util.template;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Prescription implements Serializable {
	private String prescriptionSn;
	private String pid;
	private String times;
	private String accountSn;
	private String prescriptionClassCode;//处方类别代码
	private String prescriptionClassName;//处方类别名称
	private String prescriptionTypeCode;//处方类型代码
	private String prescriptionTypeName;//处方类型名称
	private String visitSn;//就诊内部序列号
	private String patientSn;//患者内部序列号
	private String patientDomain;//域代码
	private String patientLid;//患者本地ID
	private String patientAge;//年龄
	private String orgCode;//医疗机构编码
	private String orgName;//医疗机构名称
	private String prescriptionNo;//处方号
	private String price;//费用
	private Date orderTime;//开方日期
	private String reviewPersonId;//审核人ID
	private String reviewPersonName;//审核人姓名(开方医生)
	private Date reviewTime;//审核时间
	private String diagnosis;//诊断信息
	private String medViewCode;//药观药物代码
	private String medViewName;//药观药物名称
	private String medViewFlag;//是否药观标识
	private Date createTime;//创建时间
	private Date updateTime;//更新时间
	private String updateCount;//更新回数
	private String deleteFlag;//删除标志
	private Date deleteTime;//删除时间
	private String createby;//创建者
	private String updateby;//修改者
	private String deleteby;//删除者
	private String validdays;//处方有效天数
	private String comments;//处方备注信息
	private String theraTreat;//治则治法
	private String outpNum;//门诊号
	private String visitTimes;//就诊次数
	private String chargeType;//医保类型
	private String patientType;//患者类型00普通01特需
	private String dispensary;//发药药局编号
	private String receiptNo;//发票编号
	private String prescAttr;//处方属性手工处方，临时处方等文本信息
	private String dosage;//剂数
	private String actualFee;//实付费用
	private String enteredBy;//录方人
	private String dispensePri;//配药优先级（付费处到药房距离）数字从小到大表示优先级从高到低
	private String accountFlag;//0 是未费 1已退费
	private String windowNo;//发药窗口号

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
	
	public String getAccountSn() {
		return accountSn;
	}
	
	public void setAccountSn(String accountSn) {
		this.accountSn = accountSn;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getUpdateCount() {
		return updateCount;
	}
	
	public void setUpdateCount(String updateCount) {
		this.updateCount = updateCount;
	}
	
	public String getValiddays() {
		return validdays;
	}
	
	public void setValiddays(String validdays) {
		this.validdays = validdays;
	}
	
	public String getVisitTimes() {
		return visitTimes;
	}
	
	public void setVisitTimes(String visitTimes) {
		this.visitTimes = visitTimes;
	}
	
	public String getActualFee() {
		return actualFee;
	}
	
	public void setActualFee(String actualFee) {
		this.actualFee = actualFee;
	}
	
	public String getPrescriptionSn() {
        return prescriptionSn;
    }

    public void setPrescriptionSn(String prescriptionSn) {
        this.prescriptionSn = prescriptionSn;
    }

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

    public String getPrescriptionNo() {
        return prescriptionNo;
    }

    public void setPrescriptionNo(String prescriptionNo) {
        this.prescriptionNo = prescriptionNo;
    }

    public String getPrescriptionClassCode() {
        return prescriptionClassCode;
    }

    public void setPrescriptionClassCode(String prescriptionClassCode) {
        this.prescriptionClassCode = prescriptionClassCode;
    }

    public String getPrescriptionClassName() {
        return prescriptionClassName;
    }

    public void setPrescriptionClassName(String prescriptionClassName) {
        this.prescriptionClassName = prescriptionClassName;
    }

    public String getPrescriptionTypeCode() {
        return prescriptionTypeCode;
    }

    public void setPrescriptionTypeCode(String prescriptionTypeCode) {
        this.prescriptionTypeCode = prescriptionTypeCode;
    }

    public String getPrescriptionTypeName() {
        return prescriptionTypeName;
    }

    public void setPrescriptionTypeName(String prescriptionTypeName) {
        this.prescriptionTypeName = prescriptionTypeName;
    }
    

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getReviewPersonId() {
        return reviewPersonId;
    }

    public void setReviewPersonId(String reviewPersonId) {
        this.reviewPersonId = reviewPersonId;
    }

    public String getReviewPersonName() {
        return reviewPersonName;
    }

    public void setReviewPersonName(String reviewPersonName) {
        this.reviewPersonName = reviewPersonName;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getMedViewCode() {
        return medViewCode;
    }

    public void setMedViewCode(String medViewCode) {
        this.medViewCode = medViewCode;
    }

    public String getMedViewName() {
        return medViewName;
    }

    public void setMedViewName(String medViewName) {
        this.medViewName = medViewName;
    }

    public String getMedViewFlag() {
        return medViewFlag;
    }

    public void setMedViewFlag(String medViewFlag) {
        this.medViewFlag = medViewFlag;
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
    
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTheraTreat() {
        return theraTreat;
    }

    public void setTheraTreat(String theraTreat) {
        this.theraTreat = theraTreat;
    }

    public String getOutpNum() {
        return outpNum;
    }

    public void setOutpNum(String outpNum) {
        this.outpNum = outpNum;
    }
    
    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public String getPatientType() {
        return patientType;
    }

    public void setPatientType(String patientType) {
        this.patientType = patientType;
    }

    public String getDispensary() {
        return dispensary;
    }

    public void setDispensary(String dispensary) {
        this.dispensary = dispensary;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getPrescAttr() {
        return prescAttr;
    }

    public void setPrescAttr(String prescAttr) {
        this.prescAttr = prescAttr;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(String enteredBy) {
        this.enteredBy = enteredBy;
    }

    public String getDispensePri() {
        return dispensePri;
    }

    public void setDispensePri(String dispensePri) {
        this.dispensePri = dispensePri;
    }

    public String getAccountFlag() {
        return accountFlag;
    }

    public void setAccountFlag(String accountFlag) {
        this.accountFlag = accountFlag;
    }

    public String getWindowNo() {
        return windowNo;
    }

    public void setWindowNo(String windowNo) {
        this.windowNo = windowNo;
    }
}