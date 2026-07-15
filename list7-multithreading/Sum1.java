public class Sum1 implements Runnable {
    private int sum = 0;
    private int start;
    private int end;
    private int[] arr;

    Sum1(int start, int end, int[] arr) {
        this.start = start;
        this.end = end;
        this.arr = arr;
    }

    @Override
    public void run() {
        for (int i = start; i <= end; i++) {
            sum += arr[i];
        }
    }

    public int getSum() {
        return sum;
    }
}
