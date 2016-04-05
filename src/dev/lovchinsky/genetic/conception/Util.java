package dev.lovchinsky.genetic.conception;

public class Util {

    public static double getDistance(City city1, City city2) {
        return Math.sqrt(Math.pow(city1.getX() - city2.getX(),2) + Math.pow(city1.getY() - city2.getY(),2));
    }

    public static double getTotalDistance(double[][] distances, int[] ids) {
        double totalDistance = 0;
        for (int i = 0, length = ids.length; i < length - 1; i++) {
            totalDistance += distances[ids[i]][ids[i+1]];
        }
        totalDistance += distances[ids[0]][ids[ids.length - 1]];
        return totalDistance;
    }
}
