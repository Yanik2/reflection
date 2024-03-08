package org.example.section3.array;

import java.lang.reflect.Array;

public class ArrayMain {
    public static void main(String[] args) {
        int[] oneDimensionalArray = {1, 2};

        inspectArrayObject(oneDimensionalArray);
        inspectArrayValues(oneDimensionalArray);
    }

    private static void inspectArrayValues(Object array) {
        int length = Array.getLength(array);

        System.out.print("[");
        for (int i = 0; i < length; i++) {
            Object element = Array.get(array, i);

            System.out.print(element);

            if (i != length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }

    private static void inspectArrayObject(Object array) {
        Class<?> clazz = array.getClass();

        System.out.println("Is array: " + clazz.isArray());

        Class<?> arrayType = clazz.getComponentType();

        System.out.println("Array type: " + arrayType.getTypeName());
    }
}
