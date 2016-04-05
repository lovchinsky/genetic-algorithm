package dev.lovchinsky.genetic.algorithm.operator.choosing;

import dev.lovchinsky.genetic.algorithm.component.Parents;
import dev.lovchinsky.genetic.algorithm.component.Population;

public class Outbreeding implements Chooser {

    @Override
    public Parents[] choose(Population population, int requiredSize) {
        Parents[] parents = new Parents[requiredSize / 2];
        int size = population.size();
        int numberOfChosen = 0;
        int firstIndex = 0;
        int secondIndex = size - 1;
        int offsetForFirstIndex = 0;
        int offsetForSecondIndex = 0;
        int offset = 0;

        population.sortByBetterAdapting();

        for (int i = 0; i < (size + 1) * size / 2; i++) {
            while (firstIndex < secondIndex) {
                parents[numberOfChosen++] = Parents.of(population.get(firstIndex), population.get(secondIndex));
                if(numberOfChosen == requiredSize / 2) {
                    return parents;
                }
                firstIndex++;
                secondIndex--;
            }

            if (offsetForFirstIndex < offsetForSecondIndex) {
                offsetForFirstIndex = 0;
                offsetForSecondIndex = 0;
                offset++;
            }

            if (offsetForFirstIndex == offsetForSecondIndex) {
                offsetForFirstIndex += offset;
            } else if(offsetForFirstIndex > offsetForSecondIndex) {
                offsetForFirstIndex -= offset;
                offsetForSecondIndex += offset;
            }

            firstIndex = offsetForFirstIndex;
            secondIndex = size - 1 - offsetForSecondIndex;
        }

        return parents;
    }
}
