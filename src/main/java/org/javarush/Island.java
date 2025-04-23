package org.javarush;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Island {
    private Map<Coordinates, Map<Type, List<Creature>>> gameField;
    private final Random random = new Random();
    private int length;
    private int height;
    private List<String> initCreaturesStats;


    Island(int length, int height){
        this.length = length;
        this.height = height;
    }

    private void initIsland(int length, int height) {
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= height; j++) {
                gameField.put(new Coordinates(i, j), new HashMap<>());
            }
        }
    }




}
