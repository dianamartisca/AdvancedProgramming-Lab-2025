package org.example.TestClasses;

import org.example.Test;

public class SampleTest2 {

    @Test
    public static void testHello() {
        System.out.println("Hello from testHello!");
    }

    @Test
    public void testAddition() {
        int result = 2 + 3;
        System.out.println("2 + 3 = " + result);
    }

    @Test
    public static void testGreeting() {
        String greeting = "Hi, Copilot!";
        System.out.println(greeting);
    }
}
