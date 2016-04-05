package dev.lovchinsky.genetic.algorithm.operator.crossing;

import dev.lovchinsky.genetic.algorithm.component.Chromosome;

public class ReducedSurrogateCrossover  extends PointCrossover implements Crossover {

    @Override
    public void cross(Chromosome firstChromosome, Chromosome secondChromosome) {
        int numberOfGenes = firstChromosome.getNumberOfGenes();
        int startPoint = -1;
        int endPoint;

        for (int i = 0; i < numberOfGenes; i++) {
            if(firstChromosome.getGenes()[i] != secondChromosome.getGenes()[i]) {
                startPoint = i;
                break;
            }
        }

        if(startPoint != - 1) {
            endPoint = (int) (Math.random() * (numberOfGenes - startPoint) + startPoint);
            crossPoints(firstChromosome, secondChromosome, startPoint, endPoint);
        }
    }
}
