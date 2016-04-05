package dev.lovchinsky.genetic.algorithm.operator.crossing;

import dev.lovchinsky.genetic.algorithm.component.Chromosome;

import java.util.Arrays;

public class PointCrossover {

    protected void crossPoints(Chromosome firstChromosome, Chromosome secondChromosome, int startPoint, int endPoint) {
        int[] firstParentGenes = Arrays.copyOf(firstChromosome.getGenes(), firstChromosome.getNumberOfGenes());
        int[] secondParentGenes = Arrays.copyOf(secondChromosome.getGenes(),secondChromosome.getNumberOfGenes());

        for (int i = startPoint; i <= endPoint; i++) {
            firstChromosome.swapGenes(i,indexOf(secondParentGenes,secondParentGenes[i]));
            secondChromosome.swapGenes(i, indexOf(firstParentGenes, firstParentGenes[i]));
        }
    }

    private int indexOf(int[] genes, int gene) {
        int index = - 1;

        for (int i = 0, length = genes.length; i < length; i++) {
            if(genes[i] == gene) {
                index = i;
                break;
            }
        }

        return index;
    }
}
