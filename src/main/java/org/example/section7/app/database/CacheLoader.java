package org.example.section7.app.database;

import org.example.section7.app.annotations.InitializerClass;
import org.example.section7.app.annotations.InitializerMethod;

@InitializerClass
public class CacheLoader {

    @InitializerMethod
    public void loadCache() {
        System.out.println("Loading data from cache");
    }

    public void reloadCache() {
        System.out.println("Reload cache");
    }
}
