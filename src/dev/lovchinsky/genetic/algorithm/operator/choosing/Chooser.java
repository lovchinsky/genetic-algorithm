package dev.lovchinsky.genetic.algorithm.operator.choosing;

import dev.lovchinsky.genetic.algorithm.component.Parents;
import dev.lovchinsky.genetic.algorithm.component.Population;

public interface Chooser {
    Parents[] choose(Population population, int requiredSize);
}
