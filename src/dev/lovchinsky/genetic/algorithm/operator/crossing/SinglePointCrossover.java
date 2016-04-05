package dev.lovchinsky.genetic.algorithm.operator.crossing;

import dev.lovchinsky.genetic.algorithm.component.Chromosome;

public class SinglePointCrossover extends PointCrossover implements Crossover {

    @Override
    public void cross(Chromosome firstChromosome, Chromosome secondChromosome) {
        int numberOfGenes = firstChromosome.getNumberOfGenes();
        int point = (int) (Math.random() * numberOfGenes - 1) + 1;

        crossPoints(firstChromosome, secondChromosome, 0, point);
    }
}
