public class Philosopher implements Runnable {
    int philosopherNumber;
    Fork[] forks;

    boolean hasLeftFork = false;
    boolean hasRightFork = false;

    Philosopher(int philosopherNumber, Fork[] forks) {
        this.philosopherNumber = philosopherNumber;
        this.forks = forks;
    }


    @Override
    public void run() {
        try {
            if (philosopherNumber == 0) {
                forks[forks.length - 1].take();
                System.out.println("Philosopher " + philosopherNumber + " grabbed left fork");

                forks[philosopherNumber].take();
                System.out.println("Philosopher " + philosopherNumber + " grabbed right fork");
            } else {
                forks[philosopherNumber].take();
                System.out.println("Philosopher " + philosopherNumber + " grabbed right fork");

                forks[philosopherNumber - 1].take();
                System.out.println("Philosopher " + philosopherNumber + " grabbed left fork");
            }

            System.out.println("Philosopher " + philosopherNumber + " has eaten!!!");

            forks[philosopherNumber].putDown();
            int leftForkIndex = (philosopherNumber == 0) ? forks.length - 1 : philosopherNumber - 1;
            forks[leftForkIndex].putDown();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
