package org.example.section7.app.database;

import java.io.IOException;
import org.example.section7.app.annotations.InitializerClass;
import org.example.section7.app.annotations.InitializerMethod;
import org.example.section7.app.annotations.RetryOperation;

@InitializerClass
public class DatabaseConnection {
    private int failCounter = 5;

    @RetryOperation(
        numberOfRetries = 10,
        retryExceptions = IOException.class,
        durationBetweenRetriesMs = 1000,
        failureMessage = "Connection to database 1 failed after retries"
    )
    @InitializerMethod
    public void connection1() throws IOException {
        System.out.println("Connecting to database 1");
        if (failCounter > 0) {
            failCounter--;
            throw new IOException("Connection failed");
        }

        System.out.println("Connection to database 1 succeeded");
    }

    @InitializerMethod
    public void connection2() {
        System.out.println("Connection two is successful");
    }
}
