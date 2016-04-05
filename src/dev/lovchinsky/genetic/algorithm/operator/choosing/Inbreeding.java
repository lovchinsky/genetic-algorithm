package dev.lovchinsky.genetic.algorithm.operator.choosing;

import dev.lovchinsky.genetic.algorithm.component.Parents;
import dev.lovchinsky.genetic.algorithm.component.Population;

public class Inbreeding implements Chooser {

    @Override
    public Parents[] choose(Population population, int requiredSize) {
        Parents[] parents = new Parents[requiredSize / 2];
        int size = population.size();
        int numberOfChosen = 0;
        int index = 0;
        int offset = 0;

        population.sortByBetterAdapting();

        for (int i = size - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                parents[numberOfChosen++] = Parents.of(population.get(index), population.get(index += offset + 1));
                if(numberOfChosen == requiredSize / 2) {
                    return parents;
                }
                index -= offset;
            }
            index = 0;
            offset++;
        }

        return parents;
    }
}
