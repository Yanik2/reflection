package org.example.section4.exercise;

import java.lang.reflect.Array;

public class ArrayFlatteningMain {
    public static void main(String[] args) {
        final var arrayFlat = new ArrayFlattening();

        final var result1 = arrayFlat.concat(Integer.class, 1,2,3,4,5);
        printArray(result1);
        System.out.println();
        final var result2 = arrayFlat.concat(int.class, 1, 2, 3, new int[] {4, 5, 6}, 7);
        printArray(result2);

        final var result3 = arrayFlat.concat(String.class, new String[]{"a", "b"}, "c", new String[]{"d", "e"});
        printArray(result3);
    }

    private static void printArray(Object array) {
        int length = Array.getLength(array);
        System.out.print("[");
        for (int i = 0; i < length; i++) {
            System.out.print(Array.get(array, i));
            System.out.print(",");
        }

        System.out.print("]");
    }
}
