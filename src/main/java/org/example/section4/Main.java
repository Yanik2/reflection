package org.example.section4;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final Path GAME_CONFIG_PATH = Path.of("src/main/resources/game-properties.cfg");
    private static final Path USER_INTERFACE_PATH = Path.of("src/main/resources/user-interface.cfg");

    public static void main(String[] args)
        throws IOException, InvocationTargetException, NoSuchMethodException,
        InstantiationException, IllegalAccessException {
        GameConfig gameConfig = createConfigObject(GameConfig.class, GAME_CONFIG_PATH);

        System.out.println(gameConfig);

        UserInterfaceConfig userInterfaceConfig = createConfigObject(UserInterfaceConfig.class, USER_INTERFACE_PATH);

        System.out.println(userInterfaceConfig);
    }

    private static <T> T createConfigObject(Class<T> clazz, Path filePath)
        throws IOException, NoSuchMethodException, InvocationTargetException,
        InstantiationException, IllegalAccessException {
        Scanner scanner = new Scanner(filePath);

        Constructor<?> constructor = clazz.getDeclaredConstructor();

        T configInstance = (T) constructor.newInstance();

        while(scanner.hasNextLine()) {
            String configLine = scanner.nextLine();
            String[] keyValuePair = configLine.split("=");
            String key = keyValuePair[0];
            String value = keyValuePair[1];

            getField(key, clazz)
                .ifPresent(f -> {
                    f.setAccessible(true);
                    Object parsedValue;
                    if (f.getType().isArray()) {
                        parsedValue = parseArray(f.getType().getComponentType(), value);
                    } else {
                        parsedValue = parseValue(f.getType(), value);
                    }

                    try {
                        f.set(configInstance, parsedValue);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
        }

        return configInstance;
    }

    private static <T> Optional<Field> getField(String fieldName, Class<T> clazz) {
        try {
            return Optional.of(clazz.getDeclaredField(fieldName));
        } catch (Exception ex) {
            System.out.println("field is not present: " + fieldName);
            return Optional.empty();
        }
    }

    private static Object parseArray(Class<?> arrayElementType, String value) {
        String[] values = value.split(",");

        Object arrayObject = Array.newInstance(arrayElementType, values.length);

        for (int i = 0; i < values.length; i++) {
            Array.set(arrayObject, i, parseValue(arrayElementType, values[i]));
        }

        return arrayObject;
    }

    private static Object parseValue(Class<?> type, String value) {
        if (int.class.equals(type)) {
            return Integer.parseInt(value);
        } else if (short.class.equals(type)) {
            return Short.parseShort(value);
        } else if (long.class.equals(type)) {
            return Long.parseLong(value);
        } else if (double.class.equals(type)) {
            return Double.parseDouble(value);
        } else if (float.class.equals(type)) {
            return Float.parseFloat(value);
        } else if (String.class.equals(type)) {
            return value;
        }
        throw new RuntimeException();
    }
}
