package org.example.section2;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ServerConfigurationMain {
    public static void main(String[] args)
        throws InvocationTargetException, NoSuchMethodException, InstantiationException,
        IllegalAccessException {
        var serverConfiguration = ServerConfiguration.getInstance();

        if (serverConfiguration != null)
            System.out.println("Greeting: " + serverConfiguration.getGreetingMessage());

        initConfiguration();
        serverConfiguration = ServerConfiguration.getInstance();

        System.out.println("greeting: " + serverConfiguration.getGreetingMessage());
    }

    public static void initConfiguration()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException,
        IllegalAccessException {
        Constructor<ServerConfiguration> constructor =
            ServerConfiguration.class.getDeclaredConstructor(int.class, String.class);

        constructor.setAccessible(true);

        constructor.newInstance(8080, "Hello work!");
    }
}
