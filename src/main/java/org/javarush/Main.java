package org.javarush;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Island island = new Island(2,4);
        island.initIsland();
        Map<Coordinates, Map<Type, List<Creature>>> gameField = island.getGameField();

        //System.out.println(gameField);

    }
}