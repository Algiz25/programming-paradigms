public class Fork {
    boolean isInUse;

    Fork(){
        isInUse = false;
    }

    public synchronized void take() throws InterruptedException {
        while (isInUse) {
            wait();
        }
        isInUse = true;
    }

    public synchronized void putDown() {
        isInUse = false;
        notifyAll();
    }

    public synchronized boolean isInUse(){
        return isInUse;
    }
}
