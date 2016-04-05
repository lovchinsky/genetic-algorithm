package dev.lovchinsky.genetic.algorithm.operator.selection;

import dev.lovchinsky.genetic.algorithm.component.Population;

import java.util.ArrayList;
import java.util.List;

public class RouletteWheelSelector implements Selector {

    private class Sector {
        private final int index;
        private final double beginning;
        private final double end;

        public Sector(int index, double beginning, double end) {
            this.index = index;
            this.beginning = beginning;
            this.end = end;
        }

        public int getIndex() {
            return index;
        }

        public double getBeginning() {
            return beginning;
        }

        public double getEnd() {
            return end;
        }
    }

    private class Wheel {
        private final List<Sector> sectors = new ArrayList<>();

        public int getSectorIndex(double value) {
            int index = -1;
            for(int i = 0, size = sectors.size(); i < size; i++) {
                Sector sector = sectors.get(i);
                if(sector.getBeginning() <= value && value < sector.getEnd()) {
                    index = sector.getIndex();
                    sectors.remove(i);
                    break;
                }
            }
            return index;
        }

        private void addSector(double probability) {
            double beginning;
            double end;
            int size = sectors.size();

            if(size == 0) {
                beginning = 0;
                end = probability;
            } else {
                beginning = sectors.get(size - 1).getEnd();
                end = beginning + probability;
            }
            sectors.add(new Sector(size, beginning, end));
        }
    }

    @Override
    public void select(Population population) {
        Population populationSelected = new Population();
        Wheel wheel = new Wheel();
        int size = population.size();
        double amountFitnessValues = 0.0d;

        for (int i = 0; i < size; i++) {
            amountFitnessValues += population.get(i).getFitnessValue();
        }

        for (int i = 0; i < size; i++) {
            wheel.addSector(population.get(i).getFitnessValue() / amountFitnessValues);
        }

        int index;
        for (int i = 0; i < size; i++) {
            index = wheel.getSectorIndex(Math.random());
            if(index != -1) {
                populationSelected.add(population.get(index));
            }
        }

        population.update(populationSelected);
    }
}
