package dev.lovchinsky.genetic.conception;

public class Area {
    private final double width;
    private final double height;

    private City[] cities;
    private double[][] distances;

    public Area(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public void generateRandomCities(int number) {
        cities = new City[number];
        for(int i=0; i<number; i++) {
            cities[i] = new City(i, this);
        }
    }

    public City[] getCities() {
        return cities;
    }

    public int[] getCitiesIds() {
        int length = cities.length;
        int[] ids = new int[length];
        for (int i = 0; i < length; i++) {
            ids[i] = cities[i].getId();
        }
        return ids;
    }

    public void calculateDistances() {
        int length = cities.length;
        distances = new double[length][length];
        for(int i = 0; i < length; i++) {
            for(int j = 0; j < length; j++) {
                distances[i][j] = Util.getDistance(cities[i],cities[j]);
            }
        }
    }

    public double getDistance(int id1, int id2) {
        return distances[id1][id2];
    }

    public double getTotalDistance(int[] ids) {
        return Util.getTotalDistance(distances, ids);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public boolean isEmpty() {
        return cities == null || cities.length == 0;
    }
}
