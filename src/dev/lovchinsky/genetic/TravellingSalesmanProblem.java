package dev.lovchinsky.genetic;

import dev.lovchinsky.genetic.algorithm.Algorithm;
import dev.lovchinsky.genetic.conception.Area;
import dev.lovchinsky.genetic.conception.City;

public class TravellingSalesmanProblem {
    private Algorithm algorithm;
    private Area area;
    private Display display;

    public TravellingSalesmanProblem() {

    }

    public void setAlgorithm(int sizeOfPopulation, int numberOfIteration, double probabilityOfMutation,
                             Algorithm.Selection selection, Algorithm.Choosing choosing, Algorithm.Crossing crossing,
                             Algorithm.Mutation mutation) {
        algorithm = new Algorithm(sizeOfPopulation, numberOfIteration, probabilityOfMutation,
                selection, choosing, crossing, mutation,
                genes -> 1 / area.getTotalDistance(genes),
                (firstGene, secondGene) -> 1 / area.getDistance(firstGene, secondGene));
    }

    public void setArea(double width, double height) {
        area = new Area(width, height);
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public void generateRandomCities(int number) {
        area.generateRandomCities(number);
    }

    public void drawCities() {
        display.clear();
        for (City city : area.getCities()) {
            display.drawCity(city);
        }
    }

    private void drawRoutes(int[] citiesIds) {
        drawCities();
        City[] cities = area.getCities();
        for (int i = 0, length = citiesIds.length - 1; i < length; i++) {
            display.drawRoute(cities[citiesIds[i]], cities[citiesIds[i + 1]]);
        }
        display.drawRoute(cities[citiesIds[citiesIds.length - 1]], cities[citiesIds[0]]);
    }

    public void decide() throws Exception {
        if(area.isEmpty()) {
            throw new Exception("Згенеруйте нові міста");
        } else {
            area.calculateDistances();
            algorithm.start(area.getCitiesIds(),
                    population -> {
                        population.sortByBetterAdapting();
                        drawRoutes(population.get(population.size() - 1).getGenes());
                    });
        }
    }
}
