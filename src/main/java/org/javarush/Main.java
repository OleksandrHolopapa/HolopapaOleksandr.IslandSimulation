package org.javarush;

public class Main {
    public static void main(String[] args) {
        Island island = new Island(100,20);
        island.initIsland();
        int i = 1;
        while (true) {
            System.out.println("DAY: "+i++);
            island.period();
            island.showGameField();
            if(island.stopSimulation) break;
            System.out.println("New period======================================================================");
        }
    }
}