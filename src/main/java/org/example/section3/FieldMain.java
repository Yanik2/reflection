package org.example.section3;

import java.lang.reflect.Field;
import java.sql.SQLOutput;

public class FieldMain {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
//        System.out.println("product");
//        final var product = new Product("Iron man", 2003);
//        printDeclaredFields(Product.class, product);
//        System.out.println();
//        System.out.println("movie");
//        final var movie = new Movie("Titanic", 2000, true, Category.COMEDY, 12);
//        printDeclaredFields(Movie.class, movie);
//        System.out.println();
//        System.out.println("Movie stats");
//        final var movieStats = movie.new MovieStats(30);
//        printDeclaredFields(Movie.MovieStats.class, movieStats);
//        System.out.println();
//        System.out.println("Category");
//        printDeclaredFields(Category.class, Category.ACTION);

        final var staticField = Movie.class.getField("MINIMUM_PRICE");

        //it works because MINIMUM_PRICE is a static field
        // and value is stored in class, not in instance
        // if we try to reach instance field with field.get(null) we would get NPE
        final var staticFieldValue = staticField.get(null);
        System.out.println("Static field value: " + staticFieldValue);
    }

    private static <T> void printDeclaredFields(Class<? extends T> clazz, T instance)
        throws IllegalAccessException {
        for (Field field : clazz.getDeclaredFields()) {
            System.out.println(
                String.format("Field name: %s type: %s", field.getName(), field.getType())
            );

            System.out.println("Is synthetic: " + field.isSynthetic() );
            System.out.println("Field value: " + field.get(instance));

            System.out.println();
        }
    }
}
