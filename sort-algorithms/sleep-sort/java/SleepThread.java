import java.util.concurrent.CountDownLatch;

class SleepThread<T extends Number> extends Thread {
    private final SleepSortResultArray<T> queue;
    private final T sleepTimeSec;
    private final long sleepTimeMS;
    private final CountDownLatch startSignal;


    public SleepThread(SleepSortResultArray<T> queue, T sleepTimeSec, CountDownLatch startSignal) {
        // TODO clarify and simplifies this calculations made to maximize performance inside the run method
        Double exactTimeToSleep = NumberUtils.convertNumber(Double.class, sleepTimeSec) * 1000;
        this.sleepTimeMS = NumberUtils.convertNumber( Long.class, exactTimeToSleep);
        this.queue = queue;
        this.sleepTimeSec = sleepTimeSec;
        this.startSignal = startSignal;
    }

    @Override
    public void run() {
        try {
            startSignal.await();

            final long targetTime = System.nanoTime() + this.sleepTimeMS * 1_000_000L;

            final long spinThreshold = 5_000_000L; // 5ms

            // Spin using sleep until we get close before executing
            while (targetTime - System.nanoTime() > spinThreshold) {
                Thread.sleep(1);
            }
            // Optimize this thread timing because it'll be executed in a few milliseconds
            while (System.nanoTime() < targetTime) {
                Thread.onSpinWait();
            }
            // Thread finished add to queue
            queue.produce(this.sleepTimeSec);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
