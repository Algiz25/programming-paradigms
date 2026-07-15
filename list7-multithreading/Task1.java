import java.util.Random;

public class Task1 {
    public static void main(String[] args) throws InterruptedException {
        int n = 1000_000;
        int threadNumber = 5;
        int sum = 0;
        int chunkSize = n/threadNumber;
        int[] array = new int[n];
        Sum1[] sumParts = new Sum1[threadNumber];
        Thread[] threads = new Thread[threadNumber];

        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            array[i] = rand.nextInt(0,10) + 1;
        }

        for (int i = 0; i < threadNumber; i++) {
            int start = i * chunkSize;
            int end;
            if (i == threadNumber - 1) {
                end = n - 1;
            }
            else {
                end = (i + 1) * chunkSize - 1;
            }


            Sum1 sumPart = new Sum1(start, end, array);
            sumParts[i] = sumPart;
            threads[i] = new Thread(sumPart);
            threads[i].start();
        }

        for (int i = 0; i < threadNumber; i++) {
            threads[i].join();
            sum += sumParts[i].getSum();
        }

        System.out.println(sum);
    }
}
