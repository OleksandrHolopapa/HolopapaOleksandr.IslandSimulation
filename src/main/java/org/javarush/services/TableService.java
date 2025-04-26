package org.javarush.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TableService {
    private static final List<String> eatingPossibilitiesTable;
    private static final List<String> initialStatsOfCreatures;

    static {
        try {
            eatingPossibilitiesTable = Files.readAllLines(Path.of("src/main/resources/eatingPossibilitiesTable.txt"));
            initialStatsOfCreatures= Files.readAllLines(Path.of("src/main/resources/initialStatsOfCreatures.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] getCreatureInitStats(String creatureSpecies){
        String[] initParameters = null;
        for (String string : initialStatsOfCreatures) {
            if(string.startsWith(creatureSpecies)) initParameters = string.split("\\|");
        }
        return initParameters;
    }

    private static int getVictimPositionInTableTitle(String victim) {
        String tittle = eatingPossibilitiesTable.getFirst();
        int creaturePositionInTittle = 0;
        String[] creaturesInTittle = tittle.split("\\|");
        while (!creaturesInTittle[creaturePositionInTittle].equals(victim)) creaturePositionInTittle++;
        return creaturePositionInTittle;
    }

    public static int getPossibilityToEatValue(String hunter, String victim) {
        int possibilityToEatValue = 0;
        for (String string : eatingPossibilitiesTable) {
            if(string.startsWith(hunter)){
                String[] stringSplit = string.split("\\|");
                possibilityToEatValue = Integer.parseInt(stringSplit[getVictimPositionInTableTitle(victim)].trim());
                break;
            }
        }
        return possibilityToEatValue;
    }
}
