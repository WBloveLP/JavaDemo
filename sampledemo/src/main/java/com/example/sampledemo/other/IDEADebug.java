package com.example.sampledemo.other;

public class IDEADebug {

    public static void main(String[] args) {
        String str = null;
        StringBuffer sb1 = new StringBuffer();
        sb1.append(str);
        System.out.println(sb1.length());
        StringBuffer sb2 = new StringBuffer(str);
        System.out.println(sb2.length());
    }

}