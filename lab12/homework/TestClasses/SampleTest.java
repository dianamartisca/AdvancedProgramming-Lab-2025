package org.example.TestClasses;

import org.example.Test;

public class SampleTest {

    @Test
    public static int testWithParam(int x) {
        System.out.println("testWithParam: " + x);
        return x;
    }

    @Test
    public void testWithTwoParams(String msg, int count) {
        System.out.println("testWithTwoParams: " + msg + ", " + count);
    }

    @Test
    public void testWithPrimitives(int a, double b, boolean flag) {
        System.out.println("testWithPrimitives: " + a + ", " + b + ", " + flag);
    }

    @Test
    public static void testWithString(String s) {
        System.out.println("testWithString: " + s);
    }
}