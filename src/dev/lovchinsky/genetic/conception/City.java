package dev.lovchinsky.genetic.conception;

public class City {
    private final int id;
    private final double x;
    private final double y;

    public City(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public City(int id, Area area) {
        this.id = id;
        this.x = Math.random() * area.getWidth();
        this.y = Math.random() * area.getHeight();
    }

    public int getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
