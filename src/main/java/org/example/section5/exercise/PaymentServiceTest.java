package org.example.section5.exercise;

public class PaymentServiceTest {

    public static void beforeClass() {
        System.out.println("Before class");
    }

    public void setupTest() {
        System.out.println("setup test");
    }

    public void testCreditCardPayment() {
        System.out.println("test credit card payment");
    }

    public void testWireTransfer() {
        System.out.println("test wire transfer");
    }

    public void testInsufficientFunds() {
        System.out.println("test insufficient funds");
    }

    public static void afterClass() {
        System.out.println("after class");
    }
}
