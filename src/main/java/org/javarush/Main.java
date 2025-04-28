package org.javarush;

import org.javarush.services.islandServises.StopIslandSimulationService;

public class Main {
    public static void main(String[] args) {
        Island island = new Island(100,20);
        island.initIsland();
        island.showGameField();
        int i = 1;
        while (true) {
            System.out.println("DAY: "+i++);
            island.period();
            if(StopIslandSimulationService.stopSimulation(island.getGameField())) break;
            island.showGameField();
            System.out.println("New period======================================================================");
        }
    }
}