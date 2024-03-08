package org.example.section3.exercise;

/**
 * Object Size Calculation
 * There are many ways to measure/estimate the size of an object in Java,
 * such as building an instrumentation agent, calculating the free JVM memory before
 * and after forcing a garbage collection and others.
 * In this exercise, we are going to use Java Reflection to estimate the size of an object of any class.
 * Input: An object of some class
 * Output: An estimate in bytes of the amount of memory the object takes in the JVM.
 * Formula
 * Size Of(Object) = {header size} + {object reference} +{sum of the sizes of all its fields}
 * Assumptions:
 * header size = 12 bytes
 * Object reference = 4 bytes (that is correct for JVM with a heap size smaller than 32 GB,
 * which is good enough for our estimation)
 * To keep things simple, the only types of fields our class can have are:
 * int
 * byte
 * long
 * double
 * float
 * short
 * String
 * (Others can be easily added later)
 * Also, we can assume that the class does not inherit any fields from superclasses
 * For example, given this class:
 * public class AccountSummary {
 *     private final String firstName;
 *     private final String lastName;
 *     private final short age;
 *     private final int salary;
 *
 *     public AccountSummary(String firstName, String lastName, short age, int salary) {
 *         this.firstName = firstName;
 *         this.lastName = lastName;
 *         this.age = age;
 *         this.salary = salary
 *     }
 * }
 * And this object as input:
 * AccountSummary accountSummary = new AccountSummary("John", "Smith", 20, 100_000);
 * The estimate for the size of the object will be
 * SizeOf(accountSummary) = {header} + {reference} + SizeOf{firstName} +SizeOf{lastName}
 * + SizeOf{age} + SizeOf{salary} = 12 + 4 + (12 + 4 + 4) + (12 + 4 + 5) + 2 + 4 = 63
 * A method to calculate each supported type's size is provided for your convenience.
 */

public class ObjectSizeCalculator {
    private static final long HEADER_SIZE = 12;
    private static final long REFERENCE_SIZE = 4;

    public long sizeOfObject(Object input) throws IllegalAccessException {
        final var fields = input.getClass().getDeclaredFields();
        long objectSize = HEADER_SIZE + REFERENCE_SIZE;

        for (int i = 0; i < fields.length; i++) {
            final var field = fields[i];

            if (field.isSynthetic()) {
                continue;
            }

            if (field.getType().isPrimitive()) {
                objectSize += sizeOfPrimitiveType(field.getType());
            } else if (String.class.equals(field.getType())) {
                field.setAccessible(true);
                final var str = (String) field.get(input);
                objectSize += sizeOfString(str);
            }
        }

        return objectSize;
    }


    /*************** Helper methods ********************************/
    private long sizeOfPrimitiveType(Class<?> primitiveType) {
        if (primitiveType.equals(int.class)) {
            return 4;
        } else if (primitiveType.equals(long.class)) {
            return 8;
        } else if (primitiveType.equals(float.class)) {
            return 4;
        } else if (primitiveType.equals(double.class)) {
            return 8;
        } else if (primitiveType.equals(byte.class)) {
            return 1;
        } else if (primitiveType.equals(short.class)) {
            return 2;
        }
        throw new IllegalArgumentException(String.format("Type: %s is not supported", primitiveType));
    }

    private long sizeOfString(String inputString) {
        int stringBytesSize = inputString.getBytes().length;
        return HEADER_SIZE + REFERENCE_SIZE + stringBytesSize;
    }
}
