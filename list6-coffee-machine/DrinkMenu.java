import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DrinkMenu {
    DrinkType espresso = new DrinkType("Espresso",25, 0, 0, 8);
    DrinkType doubleEspresso = new DrinkType("DoubleEspresso",50, 0, 0, 16);
    DrinkType americano = new DrinkType("Americano",150, 0, 1, 8);
    DrinkType cappuccino = new DrinkType("Cappuccino",30, 120, 1, 8);
    DrinkType latte = new DrinkType("Latte",30, 200, 1, 8);
    DrinkType flatWhite = new DrinkType("FlatWhite",60, 150, 0, 16);
    DrinkType macchiato = new DrinkType("Macchiato",30, 20, 0, 16);

    //make an array
    private List<DrinkType> drinks;
    public DrinkMenu() {
        drinks = new ArrayList<>(Arrays.asList(espresso, doubleEspresso, americano, cappuccino, latte, flatWhite, macchiato));
    }

    public void addDrink(DrinkType drink) {
        drinks.add(drink);
    }

    public List<DrinkType> getDrinkList() {
        return drinks;
    }
}
