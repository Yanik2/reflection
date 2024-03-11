package org.example.section4;

import java.util.Random;

public class GameConfig {
    //compiler replace this field with constant during optimization, so it won't be changed
    // with reflection
//    private final int releaseYear = 2000;
    private final int releaseYear;
    private String gameName;
    private double price;

    //this is runtime code, so compiler not cannot replace final field with constant
    // and despite it final it can be changed with reflection
    public GameConfig() {
//        this.releaseYear = new Random().nextInt(2000);
        this.releaseYear = 2000;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getGameName() {
        return gameName;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "GameConfig{" +
            "releaseYear=" + releaseYear +
            ", gameName='" + gameName + '\'' +
            ", price=" + price +
            '}';
    }
}
