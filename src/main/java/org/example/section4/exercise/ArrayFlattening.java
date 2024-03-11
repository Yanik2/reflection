package org.example.section4.exercise;

import java.lang.reflect.Array;

/**
 * Smart Array Concatenation
 * In this exercise, we are going to implement a method that performs "smart concatenation" of elements.
 * Input:
 * The type T represents the type of elements the method should return.
 * A variable number of arguments.
 * The arguments can be of:
 * Some type T
 * An array of type T
 * A combination of arrays of type T and elements of type T.
 * Output: A flattened array containing all the input elements of type T.
 * Example 1
 * Integer [] result = concat(Integer.class, 1,2,3,4,5);
 * The result will be an array of 5 integers containing the following elements: [1,2,3,4,5]
 * Example 2:
 * int [] result = contact(int.class, 1, 2, 3, new int[] {4, 5, 6}, 7);
 * The result will be an array of 7 integers containing the elements: [1, 2, 3, 4 ,5, 6, 7];
 * Example 3:
 * String [] result = contact(String.class, new String[]{"a", "b"}, "c", new String[] {"d", "e"});
 * The result will be an array of 5 Strings containing the elements : ["a", "b", "c", "d", "e"]
 * Note: You can always assume that the given type as the first argument is assignable from any element given as an input to the method.
 * Useful links: java.lang.reflect.Array
 */

public class ArrayFlattening {
    public <T> T concat(Class<?> type, Object... arguments) {
        if (arguments.length == 0) {
            return null;
        }

        final var resultSize = getResultSize(arguments);
        final var arrayObject = Array.newInstance(type, resultSize);
        int i = 0;
        int j = 0;

        while(i < resultSize) {
            if (arguments[j].getClass().isArray()) {
                setArrayInResult(arrayObject, arguments[j], i);
                i += Array.getLength(arguments[j]);
            } else {
                Array.set(arrayObject, i, arguments[j]);
                i++;
            }
            j++;
        }

        return (T) arrayObject;
    }

    private void setArrayInResult(Object resultArray, Object sourceArray, int index) {
        final var sourceLength = Array.getLength(sourceArray);

        for (int i = 0; i < sourceLength; i++, index++) {
            Array.set(resultArray, index, Array.get(sourceArray, i));
        }
    }

    private int getResultSize(Object... arguments) {
        int size = 0;

        for (int i = 0; i < arguments.length; i++) {
            if (arguments[i].getClass().isArray()) {
                size += Array.getLength(arguments[i]);
            } else {
                size++;
            }
        }
        return size;
    }
}
