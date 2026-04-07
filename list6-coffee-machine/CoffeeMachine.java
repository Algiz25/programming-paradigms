public class CoffeeMachine {
    private int maxWaterAmount;
    private int maxMilkAmount;
    private int maxSugarAmount;
    private int maxCoffeeAmount;

    private int waterAmount;
    private int milkAmount;
    private int sugarAmount;
    private int coffeeAmount;

    public enum BrewResult {
        SUCCESS,
        NOT_ENOUGH_WATER,
        NOT_ENOUGH_MILK,
        NOT_ENOUGH_SUGAR,
        NOT_ENOUGH_COFFEE
    }

    public CoffeeMachine(int waterAmount, int maxWaterAmount, int milkAmount, int maxMilkAmount,
                         int sugarAmount, int maxSugarAmount, int coffeeAmount, int maxCoffeeAmount) {
        this.waterAmount = waterAmount;
        this.maxWaterAmount = maxWaterAmount;
        this.milkAmount = milkAmount;
        this.maxMilkAmount = maxMilkAmount;
        this.sugarAmount = sugarAmount;
        this.maxSugarAmount = maxSugarAmount;
        this.coffeeAmount = coffeeAmount;
        this.maxCoffeeAmount = maxCoffeeAmount;
    }

    public BrewResult makeCoffee(DrinkType drink) {
        boolean isGood = true;
        if (waterAmount < drink.getWaterNeeded()){
            return BrewResult.NOT_ENOUGH_WATER;
        }
        if (milkAmount < drink.getMilkNeeded()){
            return BrewResult.NOT_ENOUGH_MILK;
        }
        if (sugarAmount < drink.getSugarNeeded()){
            return BrewResult.NOT_ENOUGH_SUGAR;
        }
        if (coffeeAmount < drink.getCoffeeNeeded()){
            return BrewResult.NOT_ENOUGH_COFFEE;
        }

        //making coffee
        waterAmount -= drink.getWaterNeeded();
        milkAmount -= drink.getMilkNeeded();
        sugarAmount -= drink.getSugarNeeded();
        coffeeAmount -= drink.getCoffeeNeeded();
        return BrewResult.SUCCESS;
    }

    public void addSupplies(int water, int milk, int sugar, int coffee) {
        if (waterAmount + water >= maxWaterAmount){
            waterAmount = maxWaterAmount;
        }
        else {
            waterAmount += water;
        }

        if (milkAmount + milk >= maxMilkAmount){
            milkAmount = maxMilkAmount;
        }
        else {
            milkAmount += milk;
        }

        if (sugarAmount + sugar >= maxSugarAmount){
            sugarAmount = maxSugarAmount;
        }
        else {
            sugarAmount += sugar;
        }

        if (coffeeAmount + coffee >= maxCoffeeAmount){
            coffeeAmount = maxCoffeeAmount;
        }
        else {
            coffeeAmount += coffee;
        }
    }

    public void showSupplies(){
        System.out.println("Water amount: " + waterAmount);
        System.out.println("Milk amount: " + milkAmount);
        System.out.println("Sugar amount: " + sugarAmount);
        System.out.println("Coffee amount: " + coffeeAmount);
    }
}
