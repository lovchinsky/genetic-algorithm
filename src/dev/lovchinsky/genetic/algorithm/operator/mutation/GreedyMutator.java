package dev.lovchinsky.genetic.algorithm.operator.mutation;

import dev.lovchinsky.genetic.algorithm.LocalEvaluator;
import dev.lovchinsky.genetic.algorithm.component.Chromosome;

import java.util.*;

public class GreedyMutator implements Mutator {
    private LocalEvaluator localEvaluator;

    private class OptimalGene {
        private final int index;
        private final double value;

        private OptimalGene(int index, double value) {
            this.index = index;
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public double getValue() {
            return value;
        }
    }

    public GreedyMutator(LocalEvaluator localEvaluator) {
        this.localEvaluator = localEvaluator;
    }

    @Override
    public void mutate(Chromosome chromosome) {
        int numberOfGenes = chromosome.getNumberOfGenes();

        int startPoint = (int) (Math.random() * numberOfGenes);
        int endPoint;
        do {
            endPoint = (int) (Math.random() * numberOfGenes);
        } while (startPoint == endPoint || Math.abs(startPoint - endPoint) < 2);

        if (startPoint > endPoint) {
            startPoint = startPoint + endPoint;
            endPoint = startPoint - endPoint;
            startPoint = startPoint - endPoint;
        }

        for (int i = startPoint; i < endPoint - 1; i++) {
            int[] genes = chromosome.getGenes();

            List<OptimalGene> optimalGenes = new ArrayList<>(endPoint - startPoint);

            for (int j = i + 1; j < endPoint; j++) {
                optimalGenes.add(new OptimalGene(j,localEvaluator.evaluate(genes[i], genes[j])));
            }

            optimalGenes.sort((o1, o2) -> {
                if(o1.getValue() < o2.getValue()) {
                    return 1;
                } else if(o1.getValue() > o2.getValue()) {
                    return -1;
                } else {
                    return 0;
                }
            });

            chromosome.swapGenes(i + 1, optimalGenes.get(0).getIndex());
        }
    }
}
