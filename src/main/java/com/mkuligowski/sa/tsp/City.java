package com.mkuligowski.sa.tsp;


public class City {

    private int x;
    private int y;


    public City(int x, int y){
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double distanceTo(City destinationCity) {
        int xDistance = Math.abs(getX() - destinationCity.getX());
        int yDistance = Math.abs(getY() - destinationCity.getY());
        double distance = Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));

        return distance;
    }
}
