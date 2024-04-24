package org.example.section7.app.http;

import java.security.Provider;
import org.example.section7.app.annotations.InitializerClass;
import org.example.section7.app.annotations.InitializerMethod;

@InitializerClass
public class ServiceRegistry {

    @InitializerMethod
    public void registerService(Provider.Service service) {
        System.out.println("Service successfully registered");
    }
}
