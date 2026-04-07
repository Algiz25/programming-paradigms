import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        Interface machineUI = new Interface(sc);
        machineUI.turnOnCoffeeMachine();
    }
}