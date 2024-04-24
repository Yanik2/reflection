package org.example.section7.exercise;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.example.section7.exercise.util.OperationType;
import org.example.section7.exercise.util.Role;

public class Annotations {

    @Repeatable(PermissionsContainer.class)
    public @interface Permissions {
        Role role();
        OperationType[] allowed();
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface PermissionsContainer {
        Permissions[] value();
    }
}
