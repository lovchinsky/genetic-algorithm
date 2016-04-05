package dev.lovchinsky.genetic.algorithm.operator.selection;

import dev.lovchinsky.genetic.algorithm.component.Population;

public interface Selector {

    void select(Population population);
}
