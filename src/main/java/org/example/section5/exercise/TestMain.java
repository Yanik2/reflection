package org.example.section5.exercise;

public class TestMain {
    public static void main(String[] args) throws Throwable {
        final var testingFramework = new TestingFramework();
        testingFramework.runTestSuite(PaymentServiceTest.class);
    }
}
