package org.javarush;

import org.javarush.Animals.Animal;
import org.javarush.services.islandServises.FoodService;
import org.javarush.services.islandServises.InitializationService;
import org.javarush.services.islandServises.RelocationService;
import org.javarush.services.islandServises.ReproductionService;

import java.util.*;

public class Island {
    private Map<Coordinates, Map<Class<? extends Creature>, List<Creature>>> gameField;
    private final int length;
    private final int height;

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

    //TODO розмноження раз на декілька циклів
    void period(){
        FoodService.timeToEat(gameField);
        FoodService.deathByStarvation(gameField);
        ReproductionService.reproductionOnTheIsland(gameField);
        RelocationService.movingOnTheIsland(gameField, length, height);
        System.out.println("After relocation-----------------------------------------------------------------------");
    }
}