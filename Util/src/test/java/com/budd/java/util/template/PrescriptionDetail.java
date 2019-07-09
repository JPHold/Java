package com.budd.java.util.template;

import java.io.Serializable;
import java.util.Date;

public class PrescriptionDetail implements Serializable {
	
	private String prescriptionSn;
	private String pid;
	private String times;
	private String accountSn;
	private String prescriptionType;//处方类别代码
	
	private String prescriptionDetailSn;//处方细项序列号
	
	private String prescriptionNum;//处方号
	private String drugCode;//药物编码
	private String drugName;//药物通用名称
	private String dosage;//剂量
	private String dosageUnit;//剂量单位
	private String totalDosage;//药物使用-总用量
	private String totalDosageUnit;//药物使用-总用量代码
	private String drugSpec;//药物规格
	private String drugFrequency;//药物使用-频率代码
	private String drugFreqName;//药物使用-频率名称
	private String routeCode;//用药途径代码
	private String routeName;//用药途径名称(使用方法)
	private String drugTypeCode;//药物类型代码
	private String drugTypeName;//药物类型名称
	private String prescriptionDetailNo;//处方细项号
	private String chineseHerbalpieces;//中药饮片处方描述
	private String decoctingMethodDesc;//中药饮片煎煮方法描述
	private String medicationMethodDesc;//中药用药方法的描述
	private String drsFlag;//是否电子处方
	private String drugManuf;//生产厂家
	private String drugGroup;//组号
	private String doseUom;//单次量单位
	private String adminCode;//给药途径代码
	private String duration;//服药天数
	private String skinTestFlag;//是否做过皮试
	private String chargesRelate;//与收费信息关联号
	private String dispRelate;//与发药信息关联号
	private String ordState;//处方状态
	private String refDate;//记录更新时间
	private String dosageone;//单次量
	private String prescSequence;//处方序号（药品序号）
	private String prescDoc;//开药医生工号
	private String prescSpec;//开药科室编码
	private String freq;//单日服用次数
	private Date createTime;//创建时间
	private Date updateTime;//更新时间
	private String adviceCode;//医嘱编号
	private String drugManufNo;//厂商编号
	private String packageSpec;//药品包装规格
	private String packageUnits;//药品包装单位
	private String additionusage;//补充用法
	private String rcptRemark;//处方明细备注信息
	private String actualFee;//实付费用
	private String price;//费用
	private String prescriptionTypeCode;//处方类别代码
	private String prescriptionTypeName;//处方类别名称

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
	
	public String getPrescriptionType() {
		return prescriptionType;
	}
	
	public void setPrescriptionType(String prescriptionType) {
		this.prescriptionType = prescriptionType;
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
	
	private static final long serialVersionUID = 1L;

    public String getPrescriptionDetailSn() {
        return prescriptionDetailSn;
    }

    public void setPrescriptionDetailSn(String prescriptionDetailSn) {
        this.prescriptionDetailSn = prescriptionDetailSn;
    }

    public String getPrescriptionSn() {
        return prescriptionSn;
    }

    public void setPrescriptionSn(String prescriptionSn) {
        this.prescriptionSn = prescriptionSn;
    }

    public String getPrescriptionNum() {
        return prescriptionNum;
    }

    public void setPrescriptionNum(String prescriptionNum) {
        this.prescriptionNum = prescriptionNum;
    }

    public String getDrugCode() {
        return drugCode;
    }

    public void setDrugCode(String drugCode) {
        this.drugCode = drugCode;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDosageUnit() {
        return dosageUnit;
    }

    public void setDosageUnit(String dosageUnit) {
        this.dosageUnit = dosageUnit;
    }

    public String getTotalDosage() {
        return totalDosage;
    }

    public void setTotalDosage(String totalDosage) {
        this.totalDosage = totalDosage;
    }

    public String getTotalDosageUnit() {
        return totalDosageUnit;
    }

    public void setTotalDosageUnit(String totalDosageUnit) {
        this.totalDosageUnit = totalDosageUnit;
    }

    public String getDrugSpec() {
        return drugSpec;
    }

    public void setDrugSpec(String drugSpec) {
        this.drugSpec = drugSpec;
    }

    public String getDrugFrequency() {
        return drugFrequency;
    }

    public void setDrugFrequency(String drugFrequency) {
        this.drugFrequency = drugFrequency;
    }

    public String getDrugFreqName() {
        return drugFreqName;
    }

    public void setDrugFreqName(String drugFreqName) {
        this.drugFreqName = drugFreqName;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getDrugTypeCode() {
        return drugTypeCode;
    }

    public void setDrugTypeCode(String drugTypeCode) {
        this.drugTypeCode = drugTypeCode;
    }

    public String getDrugTypeName() {
        return drugTypeName;
    }

    public void setDrugTypeName(String drugTypeName) {
        this.drugTypeName = drugTypeName;
    }

    public String getPrescriptionDetailNo() {
        return prescriptionDetailNo;
    }

    public void setPrescriptionDetailNo(String prescriptionDetailNo) {
        this.prescriptionDetailNo = prescriptionDetailNo;
    }

    public String getChineseHerbalpieces() {
        return chineseHerbalpieces;
    }

    public void setChineseHerbalpieces(String chineseHerbalpieces) {
        this.chineseHerbalpieces = chineseHerbalpieces;
    }

    public String getDecoctingMethodDesc() {
        return decoctingMethodDesc;
    }

    public void setDecoctingMethodDesc(String decoctingMethodDesc) {
        this.decoctingMethodDesc = decoctingMethodDesc;
    }

    public String getMedicationMethodDesc() {
        return medicationMethodDesc;
    }

    public void setMedicationMethodDesc(String medicationMethodDesc) {
        this.medicationMethodDesc = medicationMethodDesc;
    }

    public String getDrsFlag() {
        return drsFlag;
    }

    public void setDrsFlag(String drsFlag) {
        this.drsFlag = drsFlag;
    }

    public String getDrugManuf() {
        return drugManuf;
    }

    public void setDrugManuf(String drugManuf) {
        this.drugManuf = drugManuf;
    }

    public String getDrugGroup() {
        return drugGroup;
    }

    public void setDrugGroup(String drugGroup) {
        this.drugGroup = drugGroup;
    }

    public String getDoseUom() {
        return doseUom;
    }

    public void setDoseUom(String doseUom) {
        this.doseUom = doseUom;
    }

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSkinTestFlag() {
        return skinTestFlag;
    }

    public void setSkinTestFlag(String skinTestFlag) {
        this.skinTestFlag = skinTestFlag;
    }

    public String getChargesRelate() {
        return chargesRelate;
    }

    public void setChargesRelate(String chargesRelate) {
        this.chargesRelate = chargesRelate;
    }

    public String getDispRelate() {
        return dispRelate;
    }

    public void setDispRelate(String dispRelate) {
        this.dispRelate = dispRelate;
    }

    public String getOrdState() {
        return ordState;
    }

    public void setOrdState(String ordState) {
        this.ordState = ordState;
    }

    public String getRefDate() {
        return refDate;
    }

    public void setRefDate(String refDate) {
        this.refDate = refDate;
    }

    public String getDosageone() {
        return dosageone;
    }

    public void setDosageone(String dosageone) {
        this.dosageone = dosageone;
    }

    public String getPrescSequence() {
        return prescSequence;
    }

    public void setPrescSequence(String prescSequence) {
        this.prescSequence = prescSequence;
    }

    public String getPrescDoc() {
        return prescDoc;
    }

    public void setPrescDoc(String prescDoc) {
        this.prescDoc = prescDoc;
    }

    public String getPrescSpec() {
        return prescSpec;
    }

    public void setPrescSpec(String prescSpec) {
        this.prescSpec = prescSpec;
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

    public String getAdviceCode() {
        return adviceCode;
    }

    public void setAdviceCode(String adviceCode) {
        this.adviceCode = adviceCode;
    }

    public String getDrugManufNo() {
        return drugManufNo;
    }

    public void setDrugManufNo(String drugManufNo) {
        this.drugManufNo = drugManufNo;
    }

    public String getPackageSpec() {
        return packageSpec;
    }

    public void setPackageSpec(String packageSpec) {
        this.packageSpec = packageSpec;
    }

    public String getPackageUnits() {
        return packageUnits;
    }

    public void setPackageUnits(String packageUnits) {
        this.packageUnits = packageUnits;
    }

    public String getAdditionusage() {
        return additionusage;
    }

    public void setAdditionusage(String additionusage) {
        this.additionusage = additionusage;
    }

    public String getRcptRemark() {
        return rcptRemark;
    }

    public void setRcptRemark(String rcptRemark) {
        this.rcptRemark = rcptRemark;
    }
	
	public String getFreq() {
		return freq;
	}
	
	public void setFreq(String freq) {
		this.freq = freq;
	}
	
	public String getActualFee() {
		return actualFee;
	}
	
	public void setActualFee(String actualFee) {
		this.actualFee = actualFee;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
}