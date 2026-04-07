public class MyLinkedListQueue {
    private Node head;
    private Node tail;

    public MyLinkedListQueue() {
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void enqueue(int value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        }
        else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        int value = head.value;
        if (head == tail) { //if is the last element
            head = null;
            tail = null;
            return value;
        }
        head = head.next;
        return value;
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return head.value;
    }
}
