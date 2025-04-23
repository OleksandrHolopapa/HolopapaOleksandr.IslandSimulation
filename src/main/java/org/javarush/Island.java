package org.javarush;

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

    private void initIsland(int length, int height) {
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= height; j++) {
                gameField.put(new Coordinates(i, j), new HashMap<>());
            }
        }
    }

    void read(){
        String[] strings = TableService.readRowElement(initCreaturesStats, "WOLF");
        System.out.println(Arrays.toString(strings));
    }







}
