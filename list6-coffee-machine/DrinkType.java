public class DrinkType {
    private String name;
    private int waterNeeded;
    private int milkNeeded;
    private int sugarNeeded;
    private int coffeeNeeded;

    DrinkType(String name, int waterNeeded, int milkNeeded, int sugarNeeded, int coffeeNeeded) {
        this.name = name;
        this.waterNeeded = waterNeeded;
        this.milkNeeded = milkNeeded;
        this.sugarNeeded = sugarNeeded;
        this.coffeeNeeded = coffeeNeeded;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public int getWaterNeeded() {
        return waterNeeded;
    }

    public int getMilkNeeded() {
        return milkNeeded;
    }

    public int getSugarNeeded() {
        return sugarNeeded;
    }

    public int getCoffeeNeeded() {
        return coffeeNeeded;
    }
}
