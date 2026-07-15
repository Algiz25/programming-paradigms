public class Task3 {
    public static void main(String[] args) throws InterruptedException {
        Philosopher[] philosophers = new Philosopher[5];
        Fork[] forks = new Fork[5];

        Thread[] threads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            forks[i] = new Fork();
            philosophers[i] = new Philosopher(i, forks);
        }

        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(philosophers[i]);
            threads[i].start();
        }
    }

    // This task was very cool :)
}
