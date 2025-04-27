package org.javarush;

import org.javarush.Plants.Plant;
import org.javarush.services.islandServises.FoodService;
import org.javarush.services.islandServises.InitializationService;
import org.javarush.services.islandServises.RelocationService;
import org.javarush.services.islandServises.ReproductionService;

import java.util.*;

public class Island {
    private Map<Coordinates, Map<Class<? extends Creature>, List<Creature>>> gameField;
    private final int length;
    private final int height;

    private static int timeToReproduction = 3;
    public boolean stopSimulation;
    Island(int length, int height) {
        this.length = length;
        this.height = height;
    }

    public void initIsland() {
        gameField = InitializationService.initIslandGameField(length, height);
    }

    public void showGameField(){
        for (Map.Entry<Coordinates, Map<Class<? extends Creature>, List<Creature>>> entry : gameField.entrySet()) {
            System.out.println(entry.getKey());
            for (Map.Entry<Class<? extends Creature>, List<Creature>> entryMap : entry.getValue().entrySet()) {
                System.out.println(entryMap.getKey().getSimpleName()+" : population = "+entryMap.getValue().size());
            }
        }
    }

    //TODO створити StopSimulationService, закинути timeToReproduction до ReproductionService
    void period(){
        stopSimulation = true;
        FoodService.timeToEat(gameField);
        FoodService.deathByStarvation(gameField);
        timeToReproduction--;
        if(timeToReproduction==0){
            ReproductionService.reproductionOnTheIsland(gameField);
            System.out.println("------------------------------REPRODUCED");
            timeToReproduction=3;
        }
        RelocationService.movingOnTheIsland(gameField, length, height);

        for (Map.Entry<Coordinates, Map<Class<? extends Creature>, List<Creature>>> entry : gameField.entrySet()) {
            for (Map.Entry<Class<? extends Creature>, List<Creature>> listEntry : entry.getValue().entrySet()) {
                if(!listEntry.getKey().equals(Plant.class)) {
                    if(!listEntry.getValue().isEmpty()) stopSimulation = false;
                }
            }
        }
        if(stopSimulation) {
            System.out.println("!!!!!!!!!!!!!!!!!Simulation STOPS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }
}