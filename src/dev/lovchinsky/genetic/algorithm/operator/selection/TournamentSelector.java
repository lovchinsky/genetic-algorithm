package dev.lovchinsky.genetic.algorithm.operator.selection;

import dev.lovchinsky.genetic.algorithm.component.Population;

public class TournamentSelector implements Selector {

    @Override
    public void select(Population population) {
        Population populationSelected = new Population();
        int size = population.size();
        int firstIndex;
        int secondIndex;
        double firstFitnessValue;
        double secondFitnessValue;

        while(size > 1) {
            firstIndex = (int) (Math.random() * size);
            do {
                secondIndex = (int) (Math.random() * size);
            } while (firstIndex == secondIndex);

            firstFitnessValue = population.get(firstIndex).getFitnessValue();
            secondFitnessValue = population.get(secondIndex).getFitnessValue();

            if (firstFitnessValue > secondFitnessValue) {
                populationSelected.add(population.get(firstIndex));
            } else if (firstFitnessValue < secondFitnessValue) {
                populationSelected.add(population.get(secondIndex));
            } else {
                if(Math.random() < 0.5d) {
                    populationSelected.add(population.get(firstIndex));
                } else {
                    populationSelected.add(population.get(secondIndex));
                }
            }

            if(firstIndex < secondIndex) {
                population.remove(firstIndex);
                population.remove(secondIndex - 1);
            } else {
                population.remove(secondIndex);
                population.remove(firstIndex - 1);
            }

            size = population.size();
        }

        population.update(populationSelected);
    }
}