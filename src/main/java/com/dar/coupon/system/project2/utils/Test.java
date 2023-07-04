package com.dar.coupon.system.project2.utils;

public class Test {
    private static int count = 1;

    public static void test(String title) {
        System.out.printf(">>>>>>>>>>>>>>> Test %03d - %s", count++, title + "\n");
    }
}
