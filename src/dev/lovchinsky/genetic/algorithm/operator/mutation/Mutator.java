package dev.lovchinsky.genetic.algorithm.operator.mutation;

import dev.lovchinsky.genetic.algorithm.component.Chromosome;

public interface Mutator {
    void mutate(Chromosome chromosome);
}
