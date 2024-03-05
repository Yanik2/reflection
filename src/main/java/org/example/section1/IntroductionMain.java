package org.example.section1;

import java.lang.constant.Constable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class IntroductionMain {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<String> stringClass = String.class;

        Map<Integer, String> map = new HashMap<>();

        Class<?> mapClass = map.getClass();

        Class<?> squareClass = Class.forName("org.example.section1.IntroductionMain$Square");

        printClassInfo(stringClass, mapClass, squareClass);

        final var drawable = new Drawable() {
            @Override
            public int getNumberOfCorners() {
                return 0;
            }
        };

//        printClassInfo(Collection.class, boolean.class, int[][].class, Colors.class, drawable.getClass());
    }

    private static void printClassInfo(Class<?> ... classes) {
        for (Class<?> clazz : classes) {
            System.out.println("Simple name: " + clazz.getSimpleName());
            System.out.println("Package name: " + clazz.getPackageName());

            Class<?>[] ints = clazz.getInterfaces();

            for (Class<?> interf : ints) {
                System.out.println("Class name: " + clazz.getSimpleName());
                System.out.println("Interface name: " + interf.getSimpleName());
            }

            System.out.println();
            System.out.println("Is array: " + clazz.isArray());
            System.out.println("Is primitive: " + clazz.isPrimitive());
            System.out.println("Is enum: " + clazz.isEnum());
            System.out.println("Is interface: " + clazz.isInterface());
            System.out.println("Is anonymous: " + clazz.isAnonymousClass());
            System.out.println("Type name: " + clazz.getTypeName());
            System.out.println();
        }

        System.out.println();
    }

    private static class Square implements Drawable {

        @Override
        public int getNumberOfCorners() {
            return 4;
        }
    }

    private interface Drawable {
        int getNumberOfCorners();
    }

    private enum Colors {
        BLUE, RED, GREEN
    }
}
