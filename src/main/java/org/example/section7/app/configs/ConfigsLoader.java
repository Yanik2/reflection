package org.example.section7.app.configs;

import org.example.section7.app.annotations.InitializerClass;
import org.example.section7.app.annotations.InitializerMethod;

@InitializerClass
public class ConfigsLoader {

    @InitializerMethod
    public void loadAllConfigs() {
        System.out.println("Loading all configuration files");
    }
}
