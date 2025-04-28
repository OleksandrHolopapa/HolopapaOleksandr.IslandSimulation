package org.javarush.services.islandServises;

import org.javarush.Animals.Predators.Predator;
import org.javarush.Coordinates;
import org.javarush.Creature;

import java.util.List;
import java.util.Map;

public class StopIslandSimulationService {
    private static boolean stopSimulation;

    public static boolean stopSimulation(Map<Coordinates, Map<Class<? extends Creature>, List<Creature>>> gameField){
        stopSimulation = true;
        for (Map.Entry<Coordinates, Map<Class<? extends Creature>, List<Creature>>> entry : gameField.entrySet()) {
            for (Map.Entry<Class<? extends Creature>, List<Creature>> listEntry : entry.getValue().entrySet()) {
                if((!listEntry.getValue().isEmpty())&&(listEntry.getValue().getLast() instanceof Predator)) stopSimulation = false;
                //if((!listEntry.getValue().isEmpty())&&(listEntry.getValue().getLast() instanceof Animal)) stopSimulation = false;
            }
        }
        if(stopSimulation) {
            System.out.println("!!!!!!!!!!!!!!!!!Simulation STOPS!!!!!!!!!!!!!!!!!!!!");
        }
        return stopSimulation;
    }
}