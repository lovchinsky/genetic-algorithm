package dev.lovchinsky.genetic.algorithm.operator.mutation;

import dev.lovchinsky.genetic.algorithm.component.Chromosome;

public class SinglePointMutator implements Mutator {

    @Override
    public void mutate(Chromosome chromosome) {
        int numberOfGenes = chromosome.getNumberOfGenes();

        int firstIndex = (int) (Math.random() * numberOfGenes);
        int secondIndex;
        do {
            secondIndex = (int) (Math.random() * numberOfGenes);
        } while (firstIndex == secondIndex);

        chromosome.swapGenes(firstIndex, secondIndex);
    }
}
