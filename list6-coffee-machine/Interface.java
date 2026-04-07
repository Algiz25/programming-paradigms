import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Interface {
    Scanner scanner = new Scanner(System.in);
    CoffeeMachine coffeeMachine = new CoffeeMachine(0,1000,0,1000, 0, 100, 0, 1000);
    DrinkMenu drinkMenu = new DrinkMenu();

    Interface(Scanner sc) {
        scanner = sc;
    }

    void turnOnCoffeeMachine() throws InterruptedException {
        System.out.println("Coffee machine is on!");
        System.out.println("Enter '?' to see available coffee types");
        System.out.println("Enter coffee type to make such coffee");
        System.out.println("Enter 'show supplies' to see available supplies or 'add supplies' to add supplies");
        System.out.println("Enter 'exit' to turn off coffee machine");
        while (true) {
            String input = scanner.nextLine();
            CoffeeMachine.BrewResult brewResult = null;

            if (input.equals("?")) {
                displayCoffeeTypes();
                continue;
            }
            if (input.equalsIgnoreCase("add supplies")) {
                System.out.println("Water amount: ");
                int waterAmount = scanner.nextInt();
                System.out.println("Coffee amount: ");
                int coffeeAmount = scanner.nextInt();
                System.out.println("Milk amount: ");
                int milkAmount = scanner.nextInt();
                System.out.println("Sugar amount: ");
                int sugarAmount = scanner.nextInt();

                scanner.nextLine();

                coffeeMachine.addSupplies(waterAmount, milkAmount, sugarAmount, coffeeAmount);
                System.out.println("Supplies added");
                continue;
            }
            if (input.equalsIgnoreCase("show supplies")) {
                coffeeMachine.showSupplies();
                continue;
            }
            if (input.equals("exit")) {
                return;
            }
            if (input.equalsIgnoreCase("add custom")) {
                System.out.println("Water amount: ");
                int waterAmount = scanner.nextInt();
                System.out.println("Coffee amount: ");
                int coffeeAmount = scanner.nextInt();
                System.out.println("Milk amount: ");
                int milkAmount = scanner.nextInt();
                System.out.println("Sugar amount: ");
                int sugarAmount = scanner.nextInt();

                System.out.println("Save as a preset? y/n");
                char answer = scanner.next().charAt(0);
                DrinkType newType;
                if (answer == 'y') {
                    System.out.println("Enter name: ");
                    String name = scanner.nextLine();
                    newType = new DrinkType(name, waterAmount, milkAmount, sugarAmount, coffeeAmount);
                    drinkMenu.addDrink(newType);
                }
                else {
                    newType = new DrinkType("temp", waterAmount, milkAmount, sugarAmount, coffeeAmount);
                }

                scanner.nextLine();

                brewResult = coffeeMachine.makeCoffee(newType);
            }
            else{
                boolean isInputCorrect = false;
                for (DrinkType coffee : drinkMenu.getDrinkList()) {
                    if (coffee.getName().equalsIgnoreCase(input)) {
                        brewResult = coffeeMachine.makeCoffee(coffee);
                        isInputCorrect = true;
                        break;
                    }
                }

                if (!isInputCorrect) {
                    System.out.println("Invalid input");
                    continue;
                }
            }

            switch (brewResult) {
                case SUCCESS -> {
                    System.out.println("Making Coffee...");
                    System.out.println("Please wait");
                    Thread.sleep(5000);
                    System.out.println("Coffee is ready!");
                    brewResult = null;
                    break;
                }
                case NOT_ENOUGH_WATER ->  {
                    System.out.println("Not enough water!");
                }
                case NOT_ENOUGH_MILK ->  {
                    System.out.println("Not enough milk!");
                }
                case NOT_ENOUGH_SUGAR ->  {
                    System.out.println("Not enough sugar!");
                }
                case NOT_ENOUGH_COFFEE ->   {
                    System.out.println("Not enough coffee!");
                }
            }
        }
    }

    void displayCoffeeTypes(){
        for (DrinkType drinkType : drinkMenu.getDrinkList()) {
            System.out.println(drinkType.toString());
        }
    }
}
