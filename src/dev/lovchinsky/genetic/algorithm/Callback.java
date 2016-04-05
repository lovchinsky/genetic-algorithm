package dev.lovchinsky.genetic.algorithm;

import dev.lovchinsky.genetic.algorithm.component.Population;

public interface Callback {
    void call(Population population);
}
