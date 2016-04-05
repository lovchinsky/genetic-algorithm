package dev.lovchinsky.genetic.algorithm.operator.selection;

import dev.lovchinsky.genetic.algorithm.component.Population;

public class EliteSelector implements Selector {

    @Override
    public void select(Population population) {
        Population populationSelected = new Population();
        int size = population.size();

        population.sortByBetterAdapting();

        for (int i = 0; i < size / 2; i++) {
            populationSelected.add(population.get(i));
        }

        population.update(populationSelected);
    }
}
