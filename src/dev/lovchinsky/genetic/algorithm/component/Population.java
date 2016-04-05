package dev.lovchinsky.genetic.algorithm.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class Population {
    private List<Chromosome> chromosomes = new ArrayList<>();

    public Population() {

    }

    public Population(int[] genes, int size) {
        for (int i = 0; i < size; i++) {
            Chromosome chromosome = new Chromosome(genes);
            chromosome.shuffleGenes();
            add(chromosome);
        }
    }

    public void sortByBetterAdapting() {
        chromosomes.sort((o1, o2) -> {
            if(o1.getFitnessValue() < o2.getFitnessValue()) {
                return 1;
            } else if(o1.getFitnessValue() > o2.getFitnessValue()) {
                return -1;
            } else {
                return 0;
            }
        });
    }

    public void forEach(Consumer<Chromosome> action) {
        chromosomes.forEach(action);
    }

    public void shuffle() {
        Collections.shuffle(chromosomes);
    }

    public Chromosome get(int index) {
        return chromosomes.get(index);
    }

    public void add(Chromosome chromosome) {
        chromosomes.add(chromosome);
    }

    public void remove(int index) {
        chromosomes.remove(index);
    }

    public int size() {
        return chromosomes.size();
    }

    public void update(Population population) {
        chromosomes = new ArrayList<>();
        for (int i = 0, size = population.size(); i < size; i++) {
            add(population.get(i));
        }
    }
}
