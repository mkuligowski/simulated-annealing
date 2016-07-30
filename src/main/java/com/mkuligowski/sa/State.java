package com.mkuligowski.sa;
public interface State {
   double getEnergy();
   void visualiseResult();
   State getNeighbourState();

}
