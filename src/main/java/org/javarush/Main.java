package org.javarush;

public class Main {
    public static void main(String[] args) {
        Island island = new Island(3,3);
        island.initIsland();
        System.out.println("------------------------------------INIT--------------------------------------------");
        island.showGameField();
        System.out.println("------------------------------------DAY-1-------------------------------------------");
        island.period();
        island.showGameField();
        System.out.println("------------------------------------Day-2-------------------------------------------");
        island.period();
        island.showGameField();

    }
}