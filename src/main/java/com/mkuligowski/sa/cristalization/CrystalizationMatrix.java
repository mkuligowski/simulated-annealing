package com.mkuligowski.sa.cristalization;

import com.mkuligowski.sa.State;



public class CrystalizationMatrix implements State {


    private int[][] array;

    @Override
    public double getEnergy() {
        double currentEnergy = 0.0;

        for(int x1 =0;x1<array.length;x1++){
            for(int y1 =0;y1<array.length;y1++){

                currentEnergy = getEnergyFotStickedObjects(currentEnergy, x1, y1);
            }
        }

        return currentEnergy*(-1);
    }

    private double getEnergyFotStickedObjects(double currentEnergy, int x1, int y1) {
        for(int x2=0;x2<array.length;x2++){
            for(int y2=0;y2<array.length;y2++){
                double dist = Math.abs(x1-x2)+Math.abs(y1-y2); //distance in taxicab metric: https://en.wikipedia.org/wiki/Taxicab_geometry
                double colorMod = array[x1][y1]==array[x2][y2] ? 1.0 : -1.0;

                if(dist>4) currentEnergy += 0.0;
                else currentEnergy += colorMod * (Math.abs(dist-4)-1);
            }
        }
        return currentEnergy;
    }

    private double getEnergyWith4Neighbours(int x, int y) {
        double energyForPoint = 8.0;


        energyForPoint -= getEnergyForPoint(array[x][y], x+1, y);
        energyForPoint -= getEnergyForPoint(array[x][y], x-1, y);
        energyForPoint -= getEnergyForPoint(array[x][y], x, y+1);
        energyForPoint -= getEnergyForPoint(array[x][y], x, y-1);
        return energyForPoint;
    }

    private double getEnergyWith2Neighbours(int x, int y) {
        double energyForPoint = 8.0;


        energyForPoint -= getEnergyForPoint(array[x][y], x+1, y);
        energyForPoint -= getEnergyForPoint(array[x][y], x-1, y);
        return energyForPoint;
    }

    private double getEnergyWith2NeighboursVert(int x, int y) {
        double energyForPoint = 8.0;


        energyForPoint -= getEnergyForPoint(array[x][y], x, y+1);
        energyForPoint -= getEnergyForPoint(array[x][y], x, y-1);
        return energyForPoint;
    }

    private double getEnergyForPoint(int i, int neigbourX, int neigbourY) {
        if(neigbourX == -1)neigbourX=array.length-1;
        if(neigbourY == -1)neigbourY=array.length-1;
        if(neigbourX == array.length)neigbourX=0;
        if(neigbourY == array.length)neigbourY=0;

        if(i == array[neigbourX][neigbourY]){
            return 2;
        }
        return 0;
    }

    @Override
    public void visualiseResult() {
        CrystalizationSystemDrawer.draw(array);
    }

    @Override
    public State getNeighbourState() {
        int[][] dest = new int[array.length][array.length];

        for(int x=0;x<array.length;x++){
            for(int y=0;y<array.length;y++){
                dest[x][y] = array[x][y];
            }
        }

        int xVal1 = getRandomPosition(array.length);
        int yVal1 = getRandomPosition(array.length);
        int xVal2 = getRandomPosition(array.length);
        int yVal2 = getRandomPosition(array.length);


        int value1 = dest[xVal1][yVal1];
        int value2 = dest[xVal2][yVal2];

        dest[xVal1][yVal1] = value2;
        dest[xVal2][yVal2] = value1;

        CrystalizationMatrix matrix = new CrystalizationMatrix();
        matrix.setArray(dest);
        return matrix;
    }

    public void setArray(int[][] array) {
        this.array = array;
    }

    private int getRandomPosition(int length) {
        return (int) (length * Math.random());
    }

}
