package com.budd.java.jdkBasic.typeSafeEnum;

/**
 * 枚举出现前的方案
 *
 * @author budd
 * @since 2019/10/26 11:26
 **/
public class OldEnumImpl {

    private static final String SPRING = "春天";
    private static final String SUMMER = "夏天";
    private static final String AUTUMN = "秋天";
    private static final String WINTER = "冬天";
    private static final String UNDEFINE = "未选择";


    public void getSeason(int i) {
        switch (i) {
            case 1:
                print(SPRING);
                break;
            case 2:
                print(SUMMER);
                break;
            case 3:
                print(AUTUMN);
                break;
            case 4:
                print(WINTER);
                break;
            default:
                print(UNDEFINE);
                break;
        }
    }

    public void getSeason(String i) {
        switch (i) {
            case "1":
                print(SPRING);
                break;
            case "2":
                print(SUMMER);
                break;
            case "3":
                print(AUTUMN);
                break;
            case "4":
                print(WINTER);
                break;
            default:
                print(UNDEFINE);
                break;
        }
    }

    private void print(String season) {
        System.out.println(season);
    }

}
