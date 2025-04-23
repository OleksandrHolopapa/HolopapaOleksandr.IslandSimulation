package org.javarush;

import org.javarush.Animals.Animal;
import org.javarush.Animals.Predators.Wolf;

public class Main {
    public static void main(String[] args) {
       Creature animal = new Wolf(1,1,1,1);
       Island island = new Island(1,1);
       island.read();

    }
}