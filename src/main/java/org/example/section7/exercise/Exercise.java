package org.example.section7.exercise;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class Exercise {
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface OpenResources {

    }

    public Set<Method> getAllAnnotatedMethods(Object input) {
        Set<Method> methods = new HashSet<>();

        final var declaredMethods = input.getClass().getDeclaredMethods();

        for (var method : declaredMethods) {
            if (method.isAnnotationPresent(OpenResources.class))
                methods.add(method);
        }

        return methods;
    }
}
