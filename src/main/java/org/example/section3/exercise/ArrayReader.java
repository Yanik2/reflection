package org.example.section3.exercise;

import java.lang.reflect.Array;

/**
 * Reading Arrays
 * In this exercise, we are going to implement a method to read an array from both the beginning and the end.
 * Input:
 * An object of type array
 * Index of the element we want to read. The index can be both positive, negative, and zero.
 * Output:
 * If the index is non-negative, the method returns the element at the given index, counting from the beginning of the array.
 * If the index is negative, the method will return the element at the given index from the end of the array.
 * For example, for the inputs:
 * int [] input = new int[] {0, 10, 20, 30, 40};
 * and the index = 3.
 * The output is 30.
 * For the inputs:
 * String[] names = new String[] {"John", "Michael", "Joe", "David"};
 * and index = -1;
 * The output is "David".
 */

public class ArrayReader {
    public Object getArrayElement(Object array, int index) {
        final var length = Array.getLength(array);

        if (index >= 0) {
            return Array.get(array, index);
        } else {
            return Array.get(array, length + index);
        }
    }
}
