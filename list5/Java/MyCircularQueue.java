public class MyCircularQueue {
    private int defaultSize = 10;
    private int[] arr;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    MyCircularQueue(){
        capacity = defaultSize;
        arr = new int[defaultSize];
        front = 0;
        rear = -1;
        size = 0;
    }

    MyCircularQueue(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    boolean isEmpty(){
        return size == 0;
    }

    boolean isFull(){
        return size == capacity;
    }

    void enqueue(int value){
        if (isFull()){
            throw new RuntimeException("Queue is full");
        }
        rear = (rear + 1) % capacity;
        arr[rear] = value;
        size++;
    }

    int dequeue(){
        if (isEmpty()){
            throw new RuntimeException("Queue is empty");
        }
        int value = arr[front];
        front = (front + 1) % capacity;  // wrap around
        size--;
        return value;
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return arr[front];
    }


}
