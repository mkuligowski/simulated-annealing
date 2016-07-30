package com.mkuligowski.sa.tsp;

import com.mkuligowski.sa.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

public class Tour implements State {


    private List<City> plannedTour;
    private static int screenshotCount;


    public static Tour getRandomTour(List<City> cities){
        Tour tour = new Tour();
        tour.plannedTour = new ArrayList<>();


        cities.stream().forEach(c ->
                tour.plannedTour.add(new City(c.getX(),c.getY()))
        );

        Collections.shuffle(tour.plannedTour);
        return tour;

    }



    @Override
    public double getEnergy() {
        double tourDistance = 0.0;
        for (int cityIndex=0; cityIndex < plannedTour.size(); cityIndex++) {
            City fromCity = getCity(cityIndex);
            City destinationCity;
            if(cityIndex+1 < plannedTour.size()){
                destinationCity = getCity(cityIndex+1);
            }
            else{
                destinationCity = getCity(0);
            }
            tourDistance += fromCity.distanceTo(destinationCity);
        }
        return tourDistance;
    }

    @Override
    public void visualiseResult() {
       Graph graph = new SingleGraph("Tour");
        int edgeId = 0;
        City previousNode = null;
        int nodeId = 0;


        for(City city:plannedTour){

            String currentNodeId = String.format("Node%d",nodeId);
            String prevNodeId = String.format("Node%d",nodeId-1);
            Node node = graph.addNode(currentNodeId);
            node.setAttribute("x",city.getX());
            node.setAttribute("y",city.getY());

            if(previousNode != null){
                System.out.printf("%s - %s",currentNodeId,prevNodeId);
                graph.addEdge(""+edgeId++,currentNodeId,prevNodeId);

            }
            previousNode = city;
            if(city == plannedTour.get(plannedTour.size()-1)){
                graph.addEdge("last",currentNodeId,"Node0");
            }
            nodeId++;

        }


        graph.addAttribute("ui.screenshot", String.format("/home/mkuligowski/screenshot%d.png",screenshotCount++));

        Viewer v = graph.display(false);

        try {
            Thread.currentThread().sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Override
    public State getNeighbourState() {
        Tour tour = new Tour();

        tour.plannedTour = new ArrayList<>();
        plannedTour.stream().forEach( c->
                tour.plannedTour.add(new City(c.getX(),c.getY()))
        );


        int randomPosition1 = getRandomPositionOfTheTour(tour);
        int randomPosition2 = getRandomPositionOfTheTour(tour);


        City cityToSwap = tour.getCity(randomPosition1);
        City cityToSwap2 = tour.getCity(randomPosition2);


        tour.setCity(randomPosition2, cityToSwap);
        tour.setCity(randomPosition1, cityToSwap2);

        return tour;
    }



    private void setCity(int randomPosition, City cityToSwap) {
        plannedTour.set(randomPosition,cityToSwap);
    }

    private City getCity(int randomPosition) {
        return plannedTour.get(randomPosition);
    }

    private int getRandomPositionOfTheTour(Tour newSolution) {
        return (int) (newSolution.plannedTour.size() * Math.random());
    }
}
