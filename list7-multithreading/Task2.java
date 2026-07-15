import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Task2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int n = 1000_000;
        int threadNumber = 5;
        int sum = 0;
        int chunkSize = n/threadNumber;
        int[] array = new int[n];

        Sum2[] sumParts = new Sum2[threadNumber];
        ExecutorService service = Executors.newFixedThreadPool(threadNumber);
        ArrayList<Future<Integer>> futures = new ArrayList<>();

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


            Sum2 sumPart = new Sum2(start, end, array);
            sumParts[i] = sumPart;
            Future future = service.submit(sumPart);
            futures.add(future);
        }

        for (Future<Integer> future : futures) {
            sum += future.get();
        }
        service.shutdown();

        System.out.println(sum);
    }
}
