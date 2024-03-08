package org.example.section3.jsonwriter.mapper;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class ObjectMapper {

    public static String objectToJson(Object instance) throws IllegalAccessException {
        Field[] fields = instance.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder("{");

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            if (field.isSynthetic()) {
                continue;
            }

            sb.append(formatStringValue(field.getName()));
            sb.append(": ");

            if (field.getType().isPrimitive()) {
                sb.append(formatPrimitiveValue(field.get(instance), field.getType()));
            } else if (field.getType().equals(String.class)) {
                sb.append(formatStringValue(field.get(instance).toString()));
            } else if (field.getType().isArray()) {
              sb.append(arrayToJson(field.get(instance)));
            } else {
                sb.append(objectToJson(field.get(instance)));
            }

            if (i != fields.length - 1) {
                sb.append(",");
            }
        }

        sb.append("}");
        return sb.toString();
    }

    public static String arrayToJson(Object array) throws IllegalAccessException {
        final var sb = new StringBuilder("[");
        final var length = Array.getLength(array);

        for (int i = 0; i < length; i++) {
            final var value = Array.get(array, i);

            if (value.getClass().isPrimitive()) {
                sb.append(formatPrimitiveValue(value, value.getClass()));
            } else if (value.getClass().isArray()) {
                sb.append(arrayToJson(value));
            } else if (String.class.equals(value.getClass())) {
                sb.append(formatStringValue((String) value));
            } else {
                sb.append(objectToJson(value));
            }

            if (i != length - 1) {
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }

    public static String formatPrimitiveValue(Object instance, Class<?> type) {
        if (int.class.equals(type)
            || boolean.class.equals(type)
            || long.class.equals(type)
            || short.class.equals(type)) {
            return instance.toString();
        } else if (double.class.equals(type)
        || float.class.equals(type)) {
            return String.format("%.02f", instance);
        } else {
            throw new RuntimeException("Unsupported type: " + type);
        }
    }

    public static String formatStringValue(String value) {
        return String.format("\"%s\"", value);
    }
}
