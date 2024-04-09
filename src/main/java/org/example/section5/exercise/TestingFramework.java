package org.example.section5.exercise;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestingFramework {

    public void runTestSuite(Class<?> testClass) throws Throwable {
        final var methods = testClass.getMethods();
        final var before = findMethodByName(methods, "beforeClass");
        invokeMethod(before, null);
        final var setup = findMethodByName(methods, "setupTest");
        final var tests = findMethodsByPrefix(methods, "test");

        for (Method test : tests) {
            final var target = testClass.getConstructor().newInstance();
            testClass.getDeclaredC
            invokeMethod(setup, target);
            invokeMethod(test, target);
        }

        final var after = findMethodByName(methods, "afterClass");
        invokeMethod(after, null);
    }

    /**
     * Helper method to find a method by name
     * Returns null if a method with the given name does not exist
     */
    private Method findMethodByName(Method[] methods, String name) {
        return Arrays.stream(methods)
            .filter(method -> name.equals(method.getName()))
            .findAny().orElse(null);
    }

    /**
     * Helper method to find all the methods that start with the given prefix
     */
    private List<Method> findMethodsByPrefix(Method[] methods, String prefix) {
        return Arrays.stream(methods)
            .filter(method -> method.getName().startsWith(prefix))
            .toList();
    }

    private void invokeMethod(Method method, Object target)
        throws InvocationTargetException, IllegalAccessException {
        if (method != null) {
            if (method.getParameterCount() == 0 && void.class.equals(method.getReturnType())) {
                method.invoke(target);
            }
        }
    }
}
