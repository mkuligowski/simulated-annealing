package com.mkuligowski.sa.cristalization;

import com.mkuligowski.sa.SimulatedSystem;
import com.mkuligowski.sa.State;

import java.util.Random;

/**
 * Created by mkuligowski on 06.05.16.
 */
public class CrystalizationSystem implements SimulatedSystem {
    @Override
    public State getInitialState() {


        CrystalizationMatrix matrix = new CrystalizationMatrix();

        int[][] array = new int[50][50];


        Random random = new Random();

        for (int outerIndex = 0; outerIndex < array.length; outerIndex++) {
            System.out.println(outerIndex);
            for (int innerIndex = 0; innerIndex < array[outerIndex].length; innerIndex++) {
                if (random.nextDouble() < 0.8) {
                    array[outerIndex][innerIndex] = 1;
                } else {
                    array[outerIndex][innerIndex] = 0;
                }
            }
        }





        matrix.setArray(array);
        return matrix;






    }
}
