package org.example.section6;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        printFieldModifiers(Auction.class.getDeclaredFields());
    }

    private static void printFieldModifiers(Field[] fields) {
        for (Field field : fields) {
            int modifiers = field.getModifiers();

            System.out.println("Access modifier: " + getModifier(modifiers));

            if (Modifier.isVolatile(modifiers)) {
                System.out.println("The field is volatile");
            }

            if (Modifier.isFinal(modifiers)) {
                System.out.println("The field is final");
            }

            if (Modifier.isTransient(modifiers)) {
                System.out.println("The field is transient");
            }

            System.out.println();
        }
    }

    private static void printMethodModifiers(Method[]  methods) {
        for (Method method: methods) {
            int modifiers = method.getModifiers();

            System.out.println("Access modifier is: " + getModifier(modifiers));

            if (Modifier.isSynchronized(modifiers)) {
                System.out.println("Method is Synchronized");
            } else {
                System.out.println("Method is not Synchronized");
            }

            System.out.println();
        }
    }

    private static void printClassModifiers(Class<?> clazz) {
         int modifiers = clazz.getModifiers();

        System.out.println("Class access modifiers: " + getModifier(modifiers));

        if (Modifier.isAbstract(modifiers)) {
            System.out.println("This class is abstract");
        }

        if (Modifier.isInterface(modifiers)) {
            System.out.println("This class is interface");
        }

        if (Modifier.isStatic(modifiers)) {
            System.out.println("This class is static");
        }
    }

    public static String getModifier(int modifier) {
        if (Modifier.isPublic(modifier)) {
            return "public";
        } else if (Modifier.isPrivate(modifier)) {
            return "private";
        } else if (Modifier.isProtected(modifier)) {
            return "protected";
        } else {
            return "package-private";
        }
    }

    public static void runAuction() {
        Auction auction = new Auction();
        auction.startAuction();

        Bid bid1 = Bid.builder()
            .setBidderName("Company1")
            .setPrice(10)
            .build();
        auction.addBid(bid1);

        Bid bid2 = Bid.builder()
            .setBidderName("Company2")
            .setPrice(12)
            .build();
        auction.addBid(bid2);

        auction.stopAuction();

        System.out.println(auction.getAllBids());
        System.out.println("Highest bid: " + auction.getHighestBid());
    }
}
