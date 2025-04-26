package org.javarush;

import org.javarush.Animals.Animal;
import org.javarush.Animals.Herbivorous.Rabbit;
import org.javarush.Animals.Predators.Wolf;
import org.javarush.Plants.Plant;
import org.javarush.services.TableService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Island {
    private Map<Coordinates, Map<Class<? extends Creature>, List<Creature>>> gameField;
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

    public Map<Coordinates, Map<Class<? extends Creature>, List<Creature>>> getGameField() {
        return gameField;
    }

    //ймовірність появи тварини в клітинці - 66 %
    private boolean creatureIsPresentInCell() {
        return (!(random.nextInt(3) == 0));
    }

    private Creature creatureFactory(Creatures creature) {
        String[] creatureInitStats = TableService.getCreatureInitStats(creature.toString());
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

    private Map<Class<? extends Creature>, List<Creature>> fillCellWithCreatures(){
        Map<Class<? extends Creature>, List<Creature>> creaturesInCell = new HashMap<>();
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

    void showGameField(){
        for (Map.Entry<Coordinates, Map<Class<? extends Creature>, List<Creature>>> entry : gameField.entrySet()) {
            System.out.println(entry.getKey());
            for (Map.Entry<Class<? extends Creature>, List<Creature>> entryMap : entry.getValue().entrySet()) {
                System.out.println("\tCreature type "+entryMap.getKey()+" Population = "+entryMap.getValue().size()+". Creatures:");
                for (Creature creature: entryMap.getValue()) {
                    System.out.println("\t\t"+creature);
                    if(creature instanceof Animal animal) System.out.println("\t\t"+animal.getGender());
                }
            }
        }
    }
    //TODO зробити окремі сервіси foodService, reproductionService,  relocationService, initializationService
    // додати метод die(),
    // розмноження раз на декілька циклів
    void period(){
        for (Map.Entry<Coordinates, Map<Class<? extends Creature>, List<Creature>>> entry : gameField.entrySet()) {
            for (Map.Entry<Class<? extends Creature>, List<Creature>> entryMap : entry.getValue().entrySet()) {
                for (Creature creature : entryMap.getValue()) {
                    if(creature instanceof Animal animal) animal.setMoved(false);
                }
            }
        }

        //харчування
        for (Map.Entry<Coordinates, Map<Class<? extends Creature>, List<Creature>>> entry : gameField.entrySet()) {
            for (Map.Entry<Class<? extends Creature>, List<Creature>> entryMap : entry.getValue().entrySet()) {
                for (Creature creature : entryMap.getValue()) {
                    if(creature instanceof Animal animal) animal.eat(entry.getValue());
                }
            }
        }

        System.out.println("------------------------------AFTER-EATING------------------------------------------");
        showGameField();

        for (Map<Class<? extends Creature>, List<Creature>> value : gameField.values()) {
            die(value);
        }

        //reproduction
        for (Map.Entry<Coordinates, Map<Class<? extends Creature>, List<Creature>>> entry : gameField.entrySet()) {
            for (Map.Entry<Class<? extends Creature>, List<Creature>> entryMap : entry.getValue().entrySet()) {
                if(!entryMap.getValue().isEmpty()){
                    int numberOfNewCreatures;
                    if(entryMap.getKey().equals(Plant.class)) numberOfNewCreatures = Plant.reproduction(entryMap.getValue());
                    else numberOfNewCreatures = Animal.reproduction(entryMap.getValue());
                    String speciesName = entryMap.getKey().getSimpleName().toUpperCase();
                    for (int i = 0; i < numberOfNewCreatures; i++) {
                        entryMap.getValue().add(creatureFactory(Creatures.valueOf(speciesName)));
                    }
                }
            }
        }
        System.out.println("------------------------------AFTER-REPRODUCTION------------------------------------");
        showGameField();

        //тварини мандруватимуть лише після споживання їжі та розмноження
        //moving
        for (Map.Entry<Coordinates, Map<Class<? extends Creature>, List<Creature>>> entry : gameField.entrySet()) {
            for (Map.Entry<Class<? extends Creature>, List<Creature>> entryMap : entry.getValue().entrySet()) {
                for (int i = 0; i<entryMap.getValue().size(); i++) {
                    Creature creature = entryMap.getValue().get(i);
                    if((creature instanceof Animal animal)&&(!animal.getMoved())) {
                        Coordinates destinationCell = animal.move(entry.getKey(), length, height);
                        if((!destinationCell.equals(entry.getKey()))&&canGoToDestinationCell(destinationCell, animal)) {
                            animal.setMoved(true);
                            moving(entry.getKey(), destinationCell, animal);
                            System.out.println(animal+" "+animal.getGender()+" go from "+entry.getKey()+" to "+destinationCell);
                            i--;
                        }
                    }
                }
            }
        }
        System.out.println("------------------------------AFTER-MOVING------------------------------------------");
    }

    boolean canGoToDestinationCell(Coordinates destinationCell, Animal animal){
        Map<Class<? extends Creature>, List<Creature>> creaturesInDestinationCell = gameField.get(destinationCell);
        List<Creature> sameAnimalsInDestinationCell = creaturesInDestinationCell.get(animal.getClass());
        return sameAnimalsInDestinationCell==null||sameAnimalsInDestinationCell.size()<animal.getMaxPopulation();
    }

    void moving(Coordinates currentCell, Coordinates destinationCell, Animal animal){
        Map<Class<? extends Creature>, List<Creature>> creaturesInCurrentCell = gameField.get(currentCell);
        List<Creature> sameAnimalsInCurrentCell = creaturesInCurrentCell.get(animal.getClass());
        sameAnimalsInCurrentCell.remove(animal);
        Map<Class<? extends Creature>, List<Creature>> creaturesInDestinationCell = gameField.get(destinationCell);
        if(creaturesInDestinationCell.containsKey(animal.getClass())){
            List<Creature> sameAnimalsInDestinationCell = creaturesInDestinationCell.get(animal.getClass());
            sameAnimalsInDestinationCell.add(animal);
        }
        else {
            List<Creature> sameAnimalsInDestinationCell = new ArrayList<>();
            sameAnimalsInDestinationCell.add(animal);
            creaturesInDestinationCell.put(animal.getClass(), sameAnimalsInDestinationCell);
        }
    }

    void die(Map<Class<? extends Creature>, List<Creature>> creaturesInCell){
        for (Map.Entry<Class<? extends Creature>, List<Creature>> entry : creaturesInCell.entrySet()) {
            if(!entry.getKey().equals(Plant.class)){
                List<Creature> creatures = entry.getValue();
                for (int i = 0; i < creatures.size(); i++) {
                    Creature creature = creatures.get(i);
                    if(creature.getWeight()<=0) {
                        System.out.println(creature+" IS DEAD");
                        creatures.remove(creature);
                        i--;
                    }
                }
            }
        }
    }

}