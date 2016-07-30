package com.mkuligowski.sa;

import com.mkuligowski.sa.tsp.TravellingSalesmanSystem;

public class Simulation {

    private static final double DEFAULT_MIN_SYSTEM_TEMP = 1.0;
    private static final double DEFAULT_COOLING_RATE = 0.00001 ;
    private static final double DEFAULT_INITIAL_TEMP = 1000;
    private static final double DEFAULT_PROBABILITY = 0.8;

    public static void main(String[] args) {



        SimulatedSystem system = new TravellingSalesmanSystem();
        simulate(system);



    }

    public static void simulate(SimulatedSystem simulatedSystem){
        double temp = DEFAULT_INITIAL_TEMP;
        State currentState = simulatedSystem.getInitialState();
        State bestState = currentState;
        bestState.visualiseResult();

        while(systemIsNotCooled(temp)){
            State neighbourState = currentState.getNeighbourState();
            if(newStateShouldBeAccepted(currentState,neighbourState,temp)){
                currentState = neighbourState;
            }
            if(currentStateIsBetterThenBestState(currentState,bestState)){
                bestState = currentState;
            }
            temp *= 1-DEFAULT_COOLING_RATE;
        }
        bestState.visualiseResult();
    }

    private static boolean systemIsNotCooled(double temp) {
        return temp > DEFAULT_MIN_SYSTEM_TEMP;
    }

    private static boolean newStateShouldBeAccepted(State currentState, State neighbourState, double temp) {
        return calculateAcceptanceProbability(currentState.getEnergy(), neighbourState.getEnergy(),temp) > Math.random();
    }

    private static double calculateAcceptanceProbability(double currentEnergy, double newEnergy, double temperature) {
        if (newEnergy < currentEnergy)
            return DEFAULT_PROBABILITY;

        return Math.exp((currentEnergy - newEnergy) / temperature);
    }

    private static boolean currentStateIsBetterThenBestState(State currentState, State bestState) {
        return currentState.getEnergy() < bestState.getEnergy();
    }

}
