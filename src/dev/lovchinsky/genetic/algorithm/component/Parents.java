package dev.lovchinsky.genetic.algorithm.component;

public class Parents {
    private final Chromosome firstChromosome;
    private final Chromosome secondChromosome;

    private Parents(Chromosome firstChromosome, Chromosome secondChromosome) {
        this.firstChromosome = firstChromosome;
        this.secondChromosome = secondChromosome;
    }

    public static Parents of(Chromosome firstChromosome, Chromosome secondChromosome) {
        return new Parents(firstChromosome, secondChromosome);
    }

    public Chromosome getFirstChromosome() {
        return firstChromosome;
    }

    public Chromosome getSecondChromosome() {
        return secondChromosome;
    }
}
