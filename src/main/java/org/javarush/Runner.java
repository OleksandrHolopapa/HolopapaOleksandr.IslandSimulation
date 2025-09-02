package org.javarush;

import org.javarush.services.islandServises.StopIslandSimulationService;

public class Runner {
    void startSimulation(int width, int height){
        Island island = new Island(width,height);
        island.initIsland();
        int i = 1;
        while (true) {
            System.out.println("DAY: "+i++);
            island.period();
            island.show();
            if(StopIslandSimulationService.stopSimulation(island.getGameField())) break;
        }
    }
}