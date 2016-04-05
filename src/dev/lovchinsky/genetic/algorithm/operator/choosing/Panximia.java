package dev.lovchinsky.genetic.algorithm.operator.choosing;

import dev.lovchinsky.genetic.algorithm.component.Parents;
import dev.lovchinsky.genetic.algorithm.component.Population;

public class Panximia implements Chooser {

    @Override
    public Parents[] choose(Population population, int requiredSize) {
        Parents[] parents = new Parents[requiredSize / 2];
        int size = population.size();

        population.shuffle();

        chooseEvenRandom(size, parents, population);
        chooseRandom(size, requiredSize, parents, population);

        return parents;
    }

    private void chooseEvenRandom(int size, Parents[] parents, Population population) {
        int numberOfChosen = 0;
        int index = 0;

        while(numberOfChosen < size / 2) {
            parents[numberOfChosen++] = Parents.of(population.get(index++), population.get(index++));
        }
    }

    private void chooseRandom(int size, int requriedSize, Parents[] parents, Population population) {
        int numberOfChosen = size / 2;
        int firstIndex;
        int secondIndex;

        while(numberOfChosen < requriedSize / 2) {
            firstIndex = (int) (Math.random() * size);
            do {
                secondIndex = (int) (Math.random() * size);
            } while (firstIndex == secondIndex);
            parents[numberOfChosen++] = Parents.of(population.get(firstIndex), population.get(secondIndex));
        }
    }
}
