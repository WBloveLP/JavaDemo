package com.example.sampledemo.other;

import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
        double original = 1000;
//        double result = original;
//        for (int i = 0; i < 10; i++) {
//            result = (1 + 0.15) * result;
//        }
//        System.out.println(result);
        System.out.println(snowball(original, 10));
    }

    private static double snowball(double figure, int year) {
        if (year == 0) return figure;
        return snowball(figure, year - 1) * (1 + 0.15);
    }

    public static int a = 0b10; // 2
    public static int b = 010; // 8
    public static int c = 0x10; // 16
    public static int d = 10; // 16

    public static float f = 0.3f;
    public static double dd = 0.3;

    public static void main2(String[] args) {
        try {
//            File tempFile = File.createTempFile("hhhh", "txt");
//            tempFile.deleteOnExit();
            strs(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void strs(String... strs) {
//        if(strs == null) return;
        for (String str : strs) {
            System.out.println(str);
        }
    }

    public static void string2Binary(String str) {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(Integer.toBinaryString(b));
        }
        System.out.println(sb);
    }

    public static void main1(String[] args) {
        new $().$();
        new X_().x_();
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
    }
}

class $ {
    void $() {
        System.out.println("$");
    }
}

class X_ {
    void x_() {
        System.out.println("other.X_");
    }
}
