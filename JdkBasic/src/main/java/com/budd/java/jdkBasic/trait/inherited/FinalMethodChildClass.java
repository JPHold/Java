package com.budd.java.jdkBasic.trait.inherited;

public class FinalMethodChildClass extends FinalMethodBasicClass {

    public void privateSameMethod() {
        System.out.println("派生类的私有同个方法");
    }
	
	/*public final void finalSameMethod(){
		System.out.println("基类的不变同个方法");
	}*/

    public final void finalSameMethod(String name) {
        System.out.println(String.format("基类的不变同个方法(可重写),name=%s", name));
    }

}
