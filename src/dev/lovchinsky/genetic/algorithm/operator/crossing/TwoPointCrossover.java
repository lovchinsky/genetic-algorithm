package dev.lovchinsky.genetic.algorithm.operator.crossing;

import dev.lovchinsky.genetic.algorithm.component.Chromosome;

public class TwoPointCrossover extends PointCrossover implements Crossover {

    @Override
    public void cross(Chromosome firstChromosome, Chromosome secondChromosome) {
        int numberOfGenes = firstChromosome.getNumberOfGenes();

        int startPoint = (int) (Math.random() * numberOfGenes);
        int endPoint;
        do {
            endPoint = (int) (Math.random() * numberOfGenes);
        } while (startPoint == endPoint);

        if (startPoint > endPoint) {
            startPoint = startPoint + endPoint;
            endPoint = startPoint - endPoint;
            startPoint = startPoint - endPoint;
        }

        crossPoints(firstChromosome, secondChromosome, startPoint, endPoint);
    }
}
