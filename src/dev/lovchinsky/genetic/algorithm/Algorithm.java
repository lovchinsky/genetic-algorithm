package dev.lovchinsky.genetic.algorithm;

import dev.lovchinsky.genetic.algorithm.component.*;
import dev.lovchinsky.genetic.algorithm.operator.choosing.*;
import dev.lovchinsky.genetic.algorithm.operator.crossing.*;
import dev.lovchinsky.genetic.algorithm.operator.mutation.*;
import dev.lovchinsky.genetic.algorithm.operator.selection.*;

public class Algorithm {
    private Evaluator evaluator;
    private LocalEvaluator localEvaluator;

    private Selector selector;
    private Chooser chooser;
    private Crossover crossover;
    private Mutator mutator;

    private int sizeOfPopulation;
    private int numberOfIteration;
    private double probabilityOfMutation;

    public enum Selection {ELITE, ROULETTE_WHEEL, TOURNAMENT}
    public enum Choosing {INBREEDING, OUTBREEDING, PANXIMIA}
    public enum Crossing {REDUCED_SURROGATE, SINGLE_POINT, TWO_POINT}
    public enum Mutation {SINGLE_POINT, GREEDY}

    public Algorithm(int sizeOfPopulation, int numberOfIteration, double probabilityOfMutation,
                     Selection selection, Choosing choosing, Crossing crossing, Mutation mutation,
                     Evaluator evaluator, LocalEvaluator localEvaluator) {
        this.sizeOfPopulation = sizeOfPopulation;
        this.numberOfIteration = numberOfIteration;
        this.probabilityOfMutation = probabilityOfMutation;
        this.evaluator = evaluator;
        this.localEvaluator = localEvaluator;

        switch (selection) {
            case ELITE:
                selector = new EliteSelector();
            case ROULETTE_WHEEL:
                selector = new RouletteWheelSelector();
                break;
            case TOURNAMENT:
                selector = new TournamentSelector();
                break;
        }

        switch (choosing) {
            case INBREEDING:
                chooser = new Inbreeding();
                break;
            case OUTBREEDING:
                chooser = new Outbreeding();
                break;
            case PANXIMIA:
                chooser = new Panximia();
                break;
        }

        switch (crossing) {
            case REDUCED_SURROGATE:
                crossover = new ReducedSurrogateCrossover();
            case SINGLE_POINT:
                crossover = new SinglePointCrossover();
                break;
            case TWO_POINT:
                crossover = new TwoPointCrossover();
        }

        switch (mutation) {
            case SINGLE_POINT:
                mutator = new SinglePointMutator();
                break;
            case GREEDY:
                mutator = new GreedyMutator(localEvaluator);
                break;
        }
    }

    public void start(int[] values, Callback callback) {
        generate(new Population(values, sizeOfPopulation), callback);
    }

    private void generate(Population population, Callback callback) {
        for (int i = 0; i < numberOfIteration; i++) {
            evaluation(population);
            selection(population);
            reproduction(population);
            mutation(population);
        }
        callback.call(population);
    }

    private void evaluation(Population population) {
        population.forEach(chromosome -> chromosome.setFitnessValue(evaluator.evaluate(chromosome.getGenes())));
    }

    private void selection(Population population) {
        selector.select(population);
    }

    private void reproduction(Population population) {
        Parents[] parentsPool = chooser.choose(population, sizeOfPopulation);
        Population populationOfChildren = new Population();

        for (Parents parents : parentsPool) {
            Chromosome firstChromosome = new Chromosome(parents.getFirstChromosome());
            Chromosome secondChromosome = new Chromosome(parents.getSecondChromosome());

            crossover.cross(firstChromosome, secondChromosome);

            populationOfChildren.add(firstChromosome);
            populationOfChildren.add(secondChromosome);
        }

        population.update(populationOfChildren);
    }


    private void mutation(Population population) {
        population.forEach(chromosome -> {
            if(Math.random() < probabilityOfMutation) {
                mutator.mutate(chromosome);
            }
        });
    }
}
