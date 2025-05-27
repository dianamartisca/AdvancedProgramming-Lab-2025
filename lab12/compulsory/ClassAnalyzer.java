package org.example;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.annotation.Annotation;

public class ClassAnalyzer {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java -cp target/classes org.example.ClassAnalyzer org.example.ClassName");
            return;
        }
        String className = args[0];
        Class<?> clazz = Class.forName(className);

        System.out.println("Class: " + clazz.getName());
        System.out.println("Methods:");
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println("  " + Modifier.toString(method.getModifiers()) + " " +
                    method.getReturnType().getSimpleName() + " " +
                    method.getName() + "(" +
                    String.join(", ",
                            java.util.Arrays.stream(method.getParameterTypes())
                                    .map(Class::getSimpleName)
                                    .toArray(String[]::new)
                    ) + ")");
        }

        System.out.println("\nInvoking @Test static no-arg methods:");
        for (Method method : clazz.getDeclaredMethods()) {
            if (Modifier.isStatic(method.getModifiers()) &&
                    method.getParameterCount() == 0 &&
                    isTestAnnotated(method)) {
                System.out.print("Invoking " + method.getName() + "... ");
                method.setAccessible(true);
                method.invoke(null);
                System.out.println("done.");
            }
        }
    }

    private static boolean isTestAnnotated(Method method) {
        for (Annotation annotation : method.getAnnotations()) {
            if (annotation.annotationType().getSimpleName().equals("Test")) {
                return true;
            }
        }
        return false;
    }
}