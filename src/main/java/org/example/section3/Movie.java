package org.example.section3;

public class Movie extends Product {
    public static final double MINIMUM_PRICE = 10.99;

    private boolean isReleased;
    private Category category;
    private double actualPrice;

    public Movie(String name, int year, boolean isReleased, Category category, double actualPrice) {
        super(name, year);
        this.isReleased = isReleased;
        this.category = category;
        this.actualPrice = Math.max(actualPrice, MINIMUM_PRICE);
    }

    public class MovieStats {
        private double timesWatched;

        public MovieStats(double timesWatched) {
            this.timesWatched = timesWatched;
        }

        public double getRevenue() {
            return timesWatched * actualPrice;
        }
    }
}
