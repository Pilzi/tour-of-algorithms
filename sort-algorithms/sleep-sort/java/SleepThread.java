class SleepThread<T extends Number> extends Thread {
    private final SleepSortResultArray<T> queue;
    private final T sleepTimeSec;
    private final long sleepTimeNS;
    private final ThreadSynchronizer synchronizer;


    public SleepThread(SleepSortResultArray<T> queue, T sleepTimeSec, ThreadSynchronizer synchronizer) {
        // Convert the input type into double, because its most precised value we support and calculate the nanoseconds
        this.sleepTimeNS = (long) (NumberUtils.convertNumber(Double.class, sleepTimeSec) * 1e+9);
        this.queue = queue;
        this.sleepTimeSec = sleepTimeSec;
        this.synchronizer = synchronizer;

    }

    @Override
    public void run() {
        try {
            synchronizer.join();

            // Calculate exact time to sleep
            final long targetTime = System.nanoTime() + this.sleepTimeNS;

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
