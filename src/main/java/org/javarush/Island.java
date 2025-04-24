package org.javarush;

import org.javarush.Animals.Herbivorous.Rabbit;
import org.javarush.Animals.Predators.Wolf;
import org.javarush.Plants.Plant;
import org.javarush.services.TableService;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Island {
    private Map<Coordinates, Map<Type, List<Creature>>> gameField;
    private final Random random = new Random();
    private int length;
    private int height;
    private List<String> initCreaturesStats;

    {
        try {
            initCreaturesStats = Files.readAllLines(Path.of("src/main/resources/initialStatsOfCreatures.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    Island(int length, int height) {
        this.length = length;
        this.height = height;
    }

    public void initIsland() {
        gameField = new HashMap<>();
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= height; j++) {
                gameField.put(new Coordinates(i, j), fillCellWithCreatures());
            }
        }
    }

    public Map<Coordinates, Map<Type, List<Creature>>> getGameField() {
        return gameField;
    }

    //ймовірність появи тварини в клітинці - 66 %
    private boolean creatureIsPresentInCell() {
        return (!(random.nextInt(3) == 0));
    }

    private Creature creatureFactory(Creatures creature) {
        String[] creatureInitStats = TableService.readRowElement(initCreaturesStats, creature.toString());
        double maxWeight = Double.parseDouble(creatureInitStats[1].trim());
        int maxPopulation = Integer.parseInt(creatureInitStats[2].trim());
        int maxSpeedOfMovement = Integer.parseInt(creatureInitStats[3].trim());
        double maxCanEat = Double.parseDouble(creatureInitStats[4].trim());
        Creature islandCreature = switch (creature) {
            case WOLF -> new Wolf(maxWeight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            /*case BOA -> new Boa();
            case FOX -> new Fox();
            case BEAR -> new Bear();
            case EAGLE -> new Eagle();
            case HORSE -> new Horse();
            case DEER -> new Deer();*/
            case RABBIT -> new Rabbit(maxWeight, maxPopulation, maxSpeedOfMovement, maxCanEat);
            /*case MOUSE -> new Mouse();
            case GOAT -> new Goat();
            case SHEEP -> new Sheep();
            case BOAR -> new Boar();
            case BUFFALO -> new Buffalo();
            case DUCK -> new Duck();
            case CATERPILLAR -> new Caterpillar();*/
            case PLANT -> new Plant(maxWeight, maxPopulation);
        };
        return islandCreature;
    }

    private Map<Type, List<Creature>> fillCellWithCreatures(){
        Map<Type, List<Creature>> creaturesInCell = new HashMap<>();
        for(Creatures enumValue : Creatures.values()){
            if(creatureIsPresentInCell()){
                Creature islandCreature = creatureFactory(enumValue);
                List<Creature> sameTypeCreaturesInCell = getCreaturesList(islandCreature, enumValue);
                creaturesInCell.put(islandCreature.getClass(), sameTypeCreaturesInCell);
            }
        }
        return creaturesInCell;
    }

    private List<Creature> getCreaturesList(Creature islandCreature, Creatures enumValue){
        List<Creature> creatureList = new ArrayList<>();
        creatureList.add(islandCreature);
        for (int i = 1; i < getCreaturePopulationInCell(islandCreature.getMaxPopulation()); i++) {
            creatureList.add(creatureFactory(enumValue));
        }
        return creatureList;
    }

    private int getCreaturePopulationInCell(int maxPopulation){
        return random.nextInt(maxPopulation);
    }

    void period(){

    }

}