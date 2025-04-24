package org.javarush;

import org.javarush.Animals.Predators.Wolf;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Island island = new Island(4,4);
        island.initIsland();
        island.showGameField();
        System.out.println();
        island.period();
        island.showGameField();
    }
}