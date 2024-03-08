package org.example.section3.exercise;

public class ExerciseMain {
    public static void main(String[] args) {
        final var reader = new ArrayReader();
        String[] names = new String[]{"john", "michael", "joe", "david"};

        System.out.println(reader.getArrayElement(names, -1));
    }
}
