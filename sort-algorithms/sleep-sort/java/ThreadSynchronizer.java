/**
 * Thread waiting until it gets unblocked.
 * Other threads can wait for it to complete to run in sync.
 */
public class ThreadSynchronizer extends Thread {

    private final Object lock = new Object();
    private volatile boolean unblocked = false;

    public void unblock() {
        synchronized (lock) {
            if (!unblocked) {
                unblocked = true;
                lock.notifyAll();
            }
        }
    }

    @Override
    public void run() {
        synchronized (lock) {
            while (!unblocked) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }
}
