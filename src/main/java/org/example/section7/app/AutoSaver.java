package org.example.section7.app;

import org.example.section7.app.annotations.InitializerClass;
import org.example.section7.app.annotations.InitializerMethod;

@InitializerClass
public class AutoSaver {

    @InitializerMethod
    public void startAutoSavingThreads() {
        System.out.println("Start automatic data saving to disk");
    }
}
