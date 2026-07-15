import java.util.concurrent.Callable;

public class Sum2 implements Callable<Integer> {
    private int start;
    private int end;
    private int[] arr;

    // Constructor remains the same
    Sum2(int start, int end, int[] arr) {
        this.start = start;
        this.end = end;
        this.arr = arr;
    }

    @Override
    public Integer call() throws Exception {
        int partialSum = 0;
        for (int i = start; i <= end; i++) {
            partialSum += arr[i];
        }

        return partialSum;
    }
}