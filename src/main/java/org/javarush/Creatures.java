package org.javarush;

public enum Creatures {
    WOLF(30),
    BOA(30),
    FOX(30),
    BEAR(5),
    EAGLE(20),
    HORSE(20),
    DEER(20),
    RABBIT(150),
    MOUSE(500),
    GOAT(140),
    SHEEP(140),
    BOAR(50),
    BUFFALO(10),
    DUCK(200),
    CATERPILLAR(1000),
    PLANT(200);

    private final int maxPopulation;

    Creatures(int maxPopulation) {
        this.maxPopulation = maxPopulation;
    }

    public int getMaxPopulation() {
        return maxPopulation;
    }
}
