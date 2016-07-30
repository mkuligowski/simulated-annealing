package com.mkuligowski.sa.tsp;


import com.mkuligowski.sa.SimulatedSystem;
import com.mkuligowski.sa.State;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TravellingSalesmanSystem implements SimulatedSystem {

    private List<City> cities;


    public TravellingSalesmanSystem(){

        cities = new ArrayList<>();

        generateAgglomeration(-500, -300, 300, 500);
        generateAgglomeration(300, 500, 300, 500);
        generateAgglomeration(300, 500, -500, -300);


    }


    public TravellingSalesmanSystem(List<City> cities){
        this.cities = cities;
    }


    private void generateAgglomeration(int minX, int maxX, int minY, int maxY) {
        for(int i=0;i<20;i++){
            cities.add(new City(getRandom(minX, maxX), getRandom(minY, maxY)));
        }
    }

    private int getRandom(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min,max+1);
    }


    @Override
    public State getInitialState() {
        return Tour.getRandomTour(cities);
    }

}
