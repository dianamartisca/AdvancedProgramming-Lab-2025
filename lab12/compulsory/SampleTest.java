package org.example;

public class SampleTest {
    @Test
    public static void testHello() {
        System.out.println("Hello from testHello!");
    }

    @Test
    public static void testAddition() {
        int result = 2 + 3;
        System.out.println("2 + 3 = " + result);
    }

    @Test
    public static void testGreeting() {
        String greeting = "Hi, Copilot!";
        System.out.println(greeting);
    }

    public static int testWithParam(int x) {
        System.out.println("testWithParam: " + x);
        return x;
    }

    public static void testWithTwoParams(String msg, int count) {
        System.out.println("testWithTwoParams: " + msg + ", " + count);
    }
}