package org.example;

public class Main {
    public static void main(String[] args) {
        final var start = System.currentTimeMillis();
        final var result = longOperation();
        final var end = System.currentTimeMillis();

        System.out.println(end - start);
//        System.out.println(result);
    }

    private static String longOperation() {
        String str = "";
        for (int i = 0; i < 200000; i++) {
            str += i;
        }

        return str;
    }
}