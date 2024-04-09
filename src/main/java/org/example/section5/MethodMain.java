package org.example.section5;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodMain {
    public static void main(String[] args) {
        testGetters(Product.class);
        testSetters(Product.class);

        testGetters(ClothingProduct.class);
        testSetters(ClothingProduct.class);
    }

    private static void testSetters(Class<?> dataClass) {
        List<Field> fields = getAllFields(dataClass);

        for (Field field : fields) {
           String setterName = "set" + capitalizeFirstLetter(field.getName());

           Method method = null;
            try {
                method = dataClass.getMethod(setterName, field.getType());
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("setter is not present " + field.getName());
            }

            if (!method.getReturnType().equals(void.class)) {
                throw new RuntimeException("return type should be void");
            }
        }
    }

    private static void testGetters(Class<?> dataClass) {
        List<Field> fields = getAllFields(dataClass);

        final var methods = mapMethodNameToMethod(dataClass);

        for (Field field : fields) {
            String getterName = "get" + capitalizeFirstLetter(field.getName());

            if (!methods.containsKey(getterName)) {
                throw new IllegalStateException("no getter");
            }

            Method method = methods.get(getterName);

            if (!method.getReturnType().equals(field.getType())) {
                throw new RuntimeException("incorrect return type on getter");
            }

            if (method.getParameterCount() > 0) {
                throw new RuntimeException("getter should not have parameters");
            }
        }
    }

    private static List<Field> getAllFields(Class<?> clazz) {
        if (clazz == null || Object.class.equals(clazz)) {
            return Collections.emptyList();
        }

        Field[] fields = clazz.getDeclaredFields();

        List<Field> inheritedFields = getAllFields(clazz.getSuperclass());

        List<Field> allFields = new ArrayList<>();

        allFields.addAll(Arrays.asList(fields));
        allFields.addAll(inheritedFields);
        return allFields;
    }

    private static String capitalizeFirstLetter(String str) {
        return str.substring(0, 1).toUpperCase().concat(str.substring(1));
    }

    private static Map<String, Method> mapMethodNameToMethod(Class<?> dataClass) {
        Method[] methods = dataClass.getMethods();
        final var map = new HashMap<String, Method>();

        for (Method method : methods) {
            map.put(method.getName(), method);
        }

        return map;
    }
}
