package org.example;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassAnalyzer {
    private static int totalTests = 0;
    private static int passedTests = 0;
    private static int failedTests = 0;

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java -cp target/classes org.example.ClassAnalyzer target/classes or path/to/jarfile.jar or path/to/classfile.class");
            return;
        }
        String input = args[0];
        File file = new File(input);

        List<String> classNames = new ArrayList<>();
        List<URL> classpath = new ArrayList<>();

        if (file.isDirectory()) {
            findClassFiles(file, file, classNames);
            classpath.add(file.toURI().toURL());
        } else if (file.isFile() && input.endsWith(".jar")) {
            findClassesInJar(file, classNames);
            classpath.add(file.toURI().toURL());
        } else if (input.endsWith(".class")) {
            String className = toClassName(file.getName());
            classNames.add(className);
            classpath.add(file.getParentFile().toURI().toURL());
        } else {
            classNames.add(input);
        }

        try (URLClassLoader loader = new URLClassLoader(classpath.toArray(new URL[0]), ClassAnalyzer.class.getClassLoader())) {
            for (String className : classNames) {
                try {
                    Class<?> clazz = loader.loadClass(className);
                    analyzeClass(clazz);
                } catch (Throwable t) {
                    System.out.println("Error loading class " + className + ": " + t.getMessage());
                    t.printStackTrace();
                }
            }
        }

        System.out.println("\nTest statistics:");
        System.out.println("Total tests: " + totalTests);
        System.out.println("Passed: " + passedTests);
        System.out.println("Failed: " + failedTests);
    }

    private static void findClassFiles(File root, File dir, List<String> classNames) {
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) {
                findClassFiles(root, file, classNames);
            } else if (file.getName().endsWith(".class")) {
                String relPath = root.toPath().relativize(file.toPath()).toString();
                String className = relPath.replace(File.separatorChar, '.').replaceAll("\\.class$", "");
                classNames.add(className);
            }
        }
    }

    private static void findClassesInJar(File jarFile, List<String> classNames) throws IOException {
        try (JarFile jar = new JarFile(jarFile)) {
            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (entry.getName().endsWith(".class")) {
                    String className = entry.getName().replace('/', '.').replaceAll("\\.class$", "");
                    classNames.add(className);
                }
            }
        }
    }

    private static String toClassName(String fileName) {
        return fileName.replaceAll("\\.class$", "");
    }

    private static void analyzeClass(Class<?> clazz) {
        if (!Modifier.isPublic(clazz.getModifiers())) return;

        boolean classHasTest = isTestAnnotated(clazz);
        boolean anyMethodHasTest = Arrays.stream(clazz.getDeclaredMethods()).anyMatch(ClassAnalyzer::isTestAnnotated);

        if (!classHasTest && !anyMethodHasTest) return;

        System.out.println("Class: " + clazz.getName());
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println("  " + Modifier.toString(method.getModifiers()) + " " +
                    method.getReturnType().getSimpleName() + " " +
                    method.getName() + "(" +
                    String.join(", ",
                            Arrays.stream(method.getParameterTypes())
                                    .map(Class::getSimpleName)
                                    .toArray(String[]::new)
                    ) + ")");
        }

        for (Method method : clazz.getDeclaredMethods()) {
            if (isTestAnnotated(method)) {
                totalTests++;
                try {
                    Object instance = null;
                    if (!Modifier.isStatic(method.getModifiers())) {
                        Constructor<?> ctor = clazz.getDeclaredConstructor();
                        ctor.setAccessible(true);
                        instance = ctor.newInstance();
                    }
                    Object[] args = mockArguments(method.getParameterTypes());
                    System.out.print("Invoking " + method.getName() + "... ");
                    method.setAccessible(true);
                    method.invoke(instance, args);
                    System.out.println("done.");
                    passedTests++;
                } catch (Throwable t) {
                    System.out.println("FAILED: " + t.getCause());
                    failedTests++;
                }
            }
        }
    }

    private static boolean isTestAnnotated(AnnotatedElement element) {
        for (Annotation annotation : element.getAnnotations()) {
            if (annotation.annotationType().getSimpleName().equals("Test")) {
                return true;
            }
        }
        return false;
    }

    private static Object[] mockArguments(Class<?>[] paramTypes) {
        Object[] args = new Object[paramTypes.length];
        for (int param = 0; param < paramTypes.length; param++) {
            Class<?> t = paramTypes[param];
            if (t == int.class || t == Integer.class) args[param] = 42;
            else if (t == long.class || t == Long.class) args[param] = 42L;
            else if (t == double.class || t == Double.class) args[param] = 3.14;
            else if (t == float.class || t == Float.class) args[param] = 2.71f;
            else if (t == boolean.class || t == Boolean.class) args[param] = true;
            else if (t == char.class || t == Character.class) args[param] = 'A';
            else if (t == byte.class || t == Byte.class) args[param] = (byte) 1;
            else if (t == short.class || t == Short.class) args[param] = (short) 2;
            else if (t == String.class) args[param] = "mock";
            else args[param] = null;
        }
        return args;
    }
}