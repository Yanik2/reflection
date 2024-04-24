package org.example.section7;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.example.section7.app.annotations.InitializerClass;
import org.example.section7.app.annotations.InitializerMethod;
import org.example.section7.app.annotations.RetryOperation;
import org.example.section7.app.annotations.ScanPackages;

@ScanPackages({"app", "app.configs", "app.databases", "app.http"})
public class Main {
    public static void main(String[] args) throws Throwable {
        initialize();
    }

    public static void initialize() throws Throwable {
        ScanPackages scanPackages = Main.class.getAnnotation(ScanPackages.class);

        if (scanPackages == null || scanPackages.value().length == 0)
            return;

        List<Class<?>> classes = getAllClasses(scanPackages.value());

        for (Class<?> clazz : classes) {
            if (!clazz.isAnnotationPresent(InitializerClass.class)) {
                continue;
            }

            List<Method> methods = getInitializingMethods(clazz);

            Object instance = clazz.getDeclaredConstructor().newInstance();

            for (Method method : methods) {
                callInitializingMethod(instance, method);
            }
        }
    }

    private static void callInitializingMethod(Object instance, Method method)
        throws Throwable {
        RetryOperation retryOperation = method.getAnnotation(RetryOperation.class);

        int numberOfRetries = retryOperation == null ? 0 : retryOperation.numberOfRetries();

        while(true) {
            try {
                method.invoke(instance);
                break;
            } catch (InvocationTargetException e) {
                Throwable targetException = e.getTargetException();

                if (numberOfRetries > 0 && Set.of(retryOperation.retryExceptions()).contains(targetException.getClass())) {
                    numberOfRetries--;

                    System.out.println("Retrying...");
                    Thread.sleep(retryOperation.durationBetweenRetriesMs());
                } else if (retryOperation != null) {
                    throw new Exception(retryOperation.failureMessage(), targetException);
                } else {
                    throw targetException;
                }
            }
        }
    }

    public static List<Method> getInitializingMethods(Class<?> clazz) {
        List<Method> methods = new ArrayList<>();

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(InitializerMethod.class)) {
                methods.add(method);
            }
        }

        return methods;
    }

    public static List<Class<?>> getAllClasses(String ... packageNames)
        throws URISyntaxException, IOException, ClassNotFoundException {
        List<Class<?>> allClasses = new ArrayList<>();

        for (String packageName : packageNames) {
            String packageRelativePath = packageName.replace('.', '/');
            URI packageUri = Main.class.getResource(packageRelativePath).toURI();

            if (packageUri.getScheme().equals("file")) {
                Path packageFullPath = Paths.get(packageUri);
                allClasses.addAll(getAllPackageClasses(packageFullPath, packageName));
            } else if (packageUri.getScheme().equals("jar")) {
                FileSystem fileSystem = FileSystems.newFileSystem(packageUri, Collections.emptyMap());

                Path packageFullInJar = fileSystem.getPath(packageRelativePath);
                allClasses.addAll(getAllPackageClasses(packageFullInJar, packageName));

                fileSystem.close();
            }
        }

        return allClasses;
    }

    public static List<Class<?>> getAllPackageClasses(Path packagePath, String packageName)
        throws ClassNotFoundException, IOException {
        if (!Files.exists(packagePath)) {
            return Collections.emptyList();
        }

        List<Path> files = Files.list(packagePath)
            .filter(Files::isRegularFile)
            .collect(Collectors.toUnmodifiableList());

        List<Class<?>> classes = new ArrayList<>();

        for (Path file : files) {
            String fileName = file.getFileName().toString();

            if (fileName.endsWith(".class")) {
                String classFullName = packageName + "." + fileName.replaceFirst("\\.class$", "");
                Class<?> clazz = Class.forName(classFullName);
                classes.add(clazz);
            }
        }

        return classes;
    }
}
