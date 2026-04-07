public class MyStack {
    private int defaultSize = 10;

    private int[] arr;
    private int topIndex = -1;

    public MyStack() {
        arr = new int[defaultSize];
    }

    public MyStack(int size) {
        arr = new int[size];
    }

    public boolean isEmpty() {
        return arr.length == 0;
    }

    public int top(){
        if (!isEmpty()) {
            return arr[topIndex];
        }

        throw new RuntimeException("Stack is empty");
    }

    public void push(int data) {
        if (topIndex == arr.length) {
            throw new RuntimeException("Stack is full");
        }
        arr[topIndex + 1] = data;
        topIndex++;
    }

    public int pop(){
        if (!isEmpty()) {
            int val = arr[topIndex];
            arr[topIndex] = 0;
            topIndex--;
            return val;
        }
        throw new RuntimeException("Stack is empty");
    }
}
