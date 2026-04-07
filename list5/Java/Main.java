public class Main {
    public static void main(String[] args) {
        //stack
        MyStack myStack = new MyStack(5);
        myStack.push(5);
        myStack.push(3);
        myStack.push(6);
        System.out.println(myStack.pop());
        System.out.println(myStack.top());
        System.out.println(myStack.pop());


        //circular queue
        System.out.println('\n' +"Circular Queue");
        MyCircularQueue myQueue1 = new MyCircularQueue(5);
        myQueue1.enqueue(1);
        myQueue1.enqueue(2);
        myQueue1.enqueue(3);
        myQueue1.enqueue(4);
        myQueue1.enqueue(5);
        System.out.println(myQueue1.dequeue());
        System.out.println(myQueue1.dequeue());
        System.out.println(myQueue1.dequeue());

        //linked list queue
        System.out.println('\n' +"Linked list Queue");
        MyLinkedListQueue myQueue2 = new MyLinkedListQueue();
        myQueue2.enqueue(1);
        myQueue2.enqueue(2);
        myQueue2.enqueue(3);
        myQueue2.enqueue(4);
        myQueue2.enqueue(5);
        System.out.println(myQueue2.dequeue());
        System.out.println(myQueue2.dequeue());
        System.out.println(myQueue2.dequeue());

        //performance tests
        int n = 100000;
        MyCircularQueue cQueue = new MyCircularQueue(n);
        MyLinkedListQueue lQueue = new MyLinkedListQueue();


        long start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            cQueue.enqueue(i);
        }

        for (int i = 0; i < n; i++) {
            cQueue.dequeue();
        }

        long end = System.nanoTime();
        long duration = end - start;

        System.out.println("Circular Queue 100k enqueue/dequeue operations took: " + duration / 1_000_000.0 + " ms");

        //linked list test

        long start2 = System.nanoTime();

        for (int i = 0; i < n; i++) {
            lQueue.enqueue(i);
        }

        for (int i = 0; i < n; i++) {
            lQueue.dequeue();
        }
        long end2 = System.nanoTime();
        long duration2 = end2 - start;

        System.out.println("Linked List Queue 100k enqueue/dequeue operations took: " + duration2 / 1_000_000.0 + " ms");

    }
}