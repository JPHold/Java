package com.budd.java.jdkBasic.interfaces.interfaceImpl.innerInterfaceImpl;

public class InnerInterfaceImplContainer {


    //-------------接口为包权限-----------------
    interface ProtectedInterface {
        void f();
    }

    public class ProtectedInterfaceImpl implements ProtectedInterface {
        @Override
        public void f() {
        }
    }

    private class ProtectedInterfaceImpl2 implements ProtectedInterface {
        @Override
        public void f() {
        }
    }

    public ProtectedInterface getProtectedInterface() {
        return new ProtectedInterfaceImpl2();
    }

    //------------------------------


    //-------------接口为私有权限-----------------
    private PrivateInterface privateInterfaceRef;

    private interface PrivateInterface {
        void f();
    }

    private class PrivateInterfaceImpl implements PrivateInterface {
        public void f() {
            System.out.println("PrivateInterfaceImpl");
        }
    }

    public class PrivateInterfaceImpl2 implements PrivateInterface {
        public void f() {
            System.out.println("PrivateInterfaceImpl2");
        }
    }

    public PrivateInterface getPrivateInterface() {
        return new PrivateInterfaceImpl2();
    }

    public void receiveD(PrivateInterface privateInterface) {
        this.privateInterfaceRef = privateInterface;
        this.privateInterfaceRef.f();
    }

    //---------------

}
