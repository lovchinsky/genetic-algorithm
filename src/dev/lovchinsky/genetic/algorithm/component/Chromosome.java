package dev.lovchinsky.genetic.algorithm.component;

import java.util.Arrays;

public class Chromosome {
    private int numberOfGenes;
    private int[] genes;
    private double fitnessValue;

    public Chromosome(int[] genes) {
        numberOfGenes = genes.length;
        this.genes = Arrays.copyOf(genes, numberOfGenes);
    }

    public Chromosome(Chromosome chromosome) {
        numberOfGenes = chromosome.getNumberOfGenes();
        this.genes = Arrays.copyOf(chromosome.getGenes(), numberOfGenes);
        this.fitnessValue = chromosome.getFitnessValue();
    }

    public void shuffleGenes() {
        for(int i = getNumberOfGenes() - 1; i > 0; i--) {
            int index = (int)(Math.random() * (i + 1));
            int gene = genes[index];
            genes[index] = genes[i];
            genes[i] = gene;
        }
    }

    public void swapGenes(int firstIndex, int secondIndex) {
        int tempGene = genes[firstIndex];
        genes[firstIndex] = genes[secondIndex];
        genes[secondIndex] = tempGene;
    }

    public int getNumberOfGenes() {
        return numberOfGenes;
    }

    public int[] getGenes() {
        return genes;
    }

    public void setGenes(int[] genes) {
        this.genes = genes;
    }

    public double getFitnessValue() {
        return fitnessValue;
    }

    public void setFitnessValue(double fitnessValue) {
        this.fitnessValue = fitnessValue;
    }
}
