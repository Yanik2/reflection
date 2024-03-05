package org.example.section2;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class ConstructorMain {
    public static void main(String[] args)
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
//        printConstructorData(Person.class);
//        System.out.println();
//        printConstructorData(Address.class);

        final var person = createObject(Person.class, "john", 33);

        System.out.println(person);
    }

    private static <T> T createObject(Class<T> clazz, Object ... args)
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        final var constructors = clazz.getDeclaredConstructors();

        for (Constructor<?> constructor : constructors) {
            if (constructor.getParameterCount() == args.length) {
                return (T) constructor.newInstance(args);
            }
        }

        System.out.println("No matching constructor");
        return null;
    }

    private static void printConstructorData(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        System.out.println("Class: " + clazz.getSimpleName());
        System.out.println("Number of constructors: " + constructors.length);

        for (Constructor<?> constructor : constructors) {
            List<String> parameterTypeNames = Arrays.stream(constructor.getParameterTypes())
                .map(p -> p.getSimpleName())
                .toList();

            System.out.println(parameterTypeNames);
        }
    }
}
