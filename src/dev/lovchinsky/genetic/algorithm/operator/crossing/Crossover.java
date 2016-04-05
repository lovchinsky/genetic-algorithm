package dev.lovchinsky.genetic.algorithm.operator.crossing;

import dev.lovchinsky.genetic.algorithm.component.Chromosome;

public interface Crossover {
    void cross(Chromosome firstChromosome, Chromosome secondChromosome);
}