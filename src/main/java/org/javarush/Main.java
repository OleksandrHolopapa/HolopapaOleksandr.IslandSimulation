package org.javarush;

public class Main {
    public static void main(String[] args) {
        Island island = new Island(3,3);
        island.initIsland();
        for (int i = 0; i < 10; i++) {
            island.period();
            island.showGameField();
            System.out.println("New period======================================================================");
        }
    }
}