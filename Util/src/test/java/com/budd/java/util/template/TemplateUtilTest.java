package com.budd.java.util.template;


import com.budd.java.util.BaseTest;
import com.budd.java.util.TemplateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class TemplateUtilTest extends BaseTest<TemplateUtil> {

    @Override
    protected Class<TemplateUtil> assignBeanClass() {
        return TemplateUtil.class;
    }

    @Test
    public void showTemplate() throws InterruptedException {
        //值
        MedicalVisit medicalVisit = new MedicalVisit();
        medicalVisit.setPatientDomain("45586017-0");
        medicalVisit.setPatientLid("m001175332900");
        medicalVisit.setPatientAge("1岁");
        medicalVisit.setPid("001175332900");
        medicalVisit.setVisitTimes("2");
        medicalVisit.setVisitTypeCode("1");
        medicalVisit.setVisitTypeName("门诊");
        medicalVisit.setOrgCode("45586017-0");
        medicalVisit.setOrgName("广州中医药大学第一附属医院");
        medicalVisit.setVisitDoctorId("03530");
        medicalVisit.setVisitDoctorName("谢瑞珍");
        medicalVisit.setRegistrationClassCode("02");
        medicalVisit.setRegistrationClassName("副教授出诊20元");
        medicalVisit.setVisitStatusCode("4");
        medicalVisit.setVisitStatusName("就诊完毕");
        medicalVisit.setChargeClass("01");
        medicalVisit.setChargeClassName("自费");
        medicalVisit.setYears(BigDecimal.valueOf(1));
        medicalVisit.setOutpRegistSn("22");
        medicalVisit.setDeptName("儿科门诊");
        medicalVisit.setDeptCode("0014000");
        medicalVisit.setOutpDate(new Date());
        medicalVisit.setOutpNum("0011753329002");
        medicalVisit.setPatientName("廖雨萌");
        medicalVisit.setAgeUnit("岁");
        medicalVisit.setPatientIdentity("421023201708017567");
        medicalVisit.setBirthDay("20170801");
        medicalVisit.setGender("女");

        Prescription prescription = new Prescription();
        prescription.setPrescriptionSn("3");
        prescription.setPid("001175332900");
        prescription.setTimes("2");
        prescription.setAccountSn("3");
        prescription.setPrescriptionClassCode("05");
        prescription.setPrescriptionClassName("处 方");
        prescription.setPrescriptionTypeCode("2");
        prescription.setPrescriptionTypeName("西药处方");
        prescription.setPrescriptionNo("001175332900_2_05_3_3");
        prescription.setPrice("51.8205");
        prescription.setOrderTime(new Date());
        prescription.setReviewPersonId("03530");
        prescription.setReviewPersonName("谢瑞珍");
        prescription.setReviewTime(new Date());
        prescription.setOutpNum("0011753329002");
        prescription.setVisitTimes("2");
        prescription.setChargeType("01");
        prescription.setPatientType("00");
        prescription.setDispensary("201001");
        prescription.setReceiptNo("KV13343159");
        prescription.setDosage("1");
        prescription.setActualFee("51.82");
        prescription.setEnteredBy("03530");
        prescription.setWindowNo("17");

        ArrayList<PrescriptionDetail> prescriptionDetails = Lists.newArrayList();
        for (int i = 0; i < 4; i++) {
            PrescriptionDetail prescriptionDetail = new PrescriptionDetail();
            prescriptionDetails.add(prescriptionDetail);
            prescriptionDetail.setPrescriptionSn("" + i);
            prescriptionDetail.setPid("001175332900");
            prescriptionDetail.setTimes("2");
            prescriptionDetail.setAccountSn("3");
            prescriptionDetail.setPrescriptionType("05");
            prescriptionDetail.setPrescriptionNum("001175332900_2_05_3_3");
            prescriptionDetail.setDrugCode("001435");
            prescriptionDetail.setDrugName("抗病毒口服液");
            prescriptionDetail.setDosage("1");
            prescriptionDetail.setDosageUnit("02");
            prescriptionDetail.setTotalDosage("1");
            prescriptionDetail.setTotalDosageUnit("02");
            prescriptionDetail.setDrugSpec("盒|10ml×10支/盒");
            prescriptionDetail.setDrugFrequency("003");
            prescriptionDetail.setDrugFreqName("每日三次");
            prescriptionDetail.setRouteCode("001");
            prescriptionDetail.setRouteName("口服");
            prescriptionDetail.setDrugTypeCode("5000770");
            prescriptionDetail.setPrescriptionDetailNo("001175332900_2_05_3_3_1");
            prescriptionDetail.setMedicationMethodDesc("口服");
            prescriptionDetail.setDrsFlag("手工处方");
            prescriptionDetail.setDoseUom("02");
            prescriptionDetail.setAdminCode("001");
            prescriptionDetail.setChargesRelate("001175332900|2|3");
            prescriptionDetail.setDispRelate("001175332900_2_05_3_3_1");
            prescriptionDetail.setOrdState("未确认");
            prescriptionDetail.setRefDate("2019-07-09 18:24:09");
            prescriptionDetail.setDosageone("1");
            prescriptionDetail.setPrescSequence("1");
            prescriptionDetail.setPrescDoc("03530");
            prescriptionDetail.setPrescSpec("0014000");
            prescriptionDetail.setFreq("003");
            prescriptionDetail.setAdviceCode("001435");
            prescriptionDetail.setPackageSpec("1");
            prescriptionDetail.setPackageUnits("10ml×10支/盒");
            prescriptionDetail.setActualFee("17.39");
            prescriptionDetail.setPrice("17.39");
            prescriptionDetail.setPrescriptionTypeCode("2");
            prescriptionDetail.setPrescriptionTypeName("西药处方");
        }

        VisitDiagnosis visitDiagnosis = new VisitDiagnosis();
        visitDiagnosis.setPid("001175332900");
        visitDiagnosis.setTimes("2");
        visitDiagnosis.setVisitSn("0011753329002");
        visitDiagnosis.setDiagnosticDeptId("0014000");
        visitDiagnosis.setDiagnosticDeptName("儿科门诊");
        visitDiagnosis.setDiagnosisDoctorId("03530");
        visitDiagnosis.setDiagnosisDoctorName("谢瑞珍");
        visitDiagnosis.setDiagnosisDate(new Date());
        visitDiagnosis.setDiagnosisTypeCode("ICD10");
        visitDiagnosis.setDiagnosisTypeName("ICD10");
        visitDiagnosis.setMainDiagnosisFlag("2");
        visitDiagnosis.setContagiousFlag("2");
        visitDiagnosis.setDiagClass("0");

        final TemplateUtil templateUtil = getBean();
        final HashMap<Object, Object> valueMap = Maps.newHashMap();
        valueMap.put("medicalVisit", medicalVisit);
        valueMap.put("prescription", prescription);
        valueMap.put("visitDiagnosis", visitDiagnosis);
        valueMap.put("prescriptionDetailList", prescriptionDetails);
        Integer stopSize = 10;
        String xmlContent = templateUtil.showTemplate("HIP1000.ftl", valueMap);

        final CountDownLatch countDownLatch = new CountDownLatch(stopSize);

        for (int i = 0; i < stopSize; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long start = System.currentTimeMillis();
                    String xmlContent = templateUtil.showTemplate("HIP1000.ftl", valueMap);
                    long end = System.currentTimeMillis();
//        System.out.println("--------xml---------");
//        System.out.println(xmlContent);
                    System.out.println("--------耗时---------");
                    System.out.println(end - start);
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
    }

    /**
     * 模板性能
     */
    @Test
    public void templateBest(){
        //https://www.imooc.com/qadetail/200232
    }

    @Test
    public void testList(){
        TemplateUtil templateUtil = getBean();

        HashMap<Object, Object> valueMap = Maps.newHashMap();

        ArrayList<String> emptyList = Lists.newArrayList();
        valueMap.put("emptyList",emptyList);

        ArrayList<String> contentList = Lists.newArrayList();
        contentList.add("橙子");
        contentList.add("橘子");
        valueMap.put("contentList",contentList);

        String xmlContent = templateUtil.showTemplate("list.ftl", valueMap);
        System.out.println(xmlContent);
    }

    @Test
    public void testFunction(){
        TemplateUtil templateUtil = getBean();

        HashMap<Object, Object> valueMap = Maps.newHashMap();

        valueMap.put("upperCase","upper_Case");
        valueMap.put("capFirst","cap_first");
        valueMap.put("strLength","length");
        valueMap.put("startsWith","starts_with");
        valueMap.put("booleanStr",true);
        ArrayList<String> contentList = Lists.newArrayList();
        contentList.add("橙子");
        contentList.add("橘子");
        valueMap.put("contentList",contentList);

        String xmlContent = templateUtil.showTemplate("function.ftlh", valueMap);
        System.out.println(xmlContent);
    }

}