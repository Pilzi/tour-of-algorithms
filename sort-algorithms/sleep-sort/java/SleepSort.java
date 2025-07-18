import java.util.concurrent.CountDownLatch;

public class SleepSort {

    /**
     * Sleep sort implementation supporting any {@link Number} type array running in the main thread <br/>
     *
     * As it's not possible to guarantee a result for large data sets, it should be used with another algorithm for correction <br/>
     *
     * However, 100 floats with a total difference of 10 ms work quite well,
     * so this remains experimental.
     *
     * @param arr the array to sort including any {@link} type
     *
     * @return T[] sorted array of the same type as the input type
     */
    public static <T extends Number> T[] sort(T[] arr) {
        SleepThread<T>[] producer = ArrayUtils.createArray(SleepThread.class, arr.length);
        // When a thread wakes up, it tells the consumer, to add the thread value to the sleepSortResultArray
        SleepSortResultArray<T> queue = new SleepSortResultArray<>(ArrayUtils.createArray(arr[0].getClass(), arr.length));
        // TODO Check if its possible to prevent using CountDownLetch without losing performance
        CountDownLatch startSignal = new CountDownLatch(1);
        for (int i = 0; i < arr.length; i++) {
            // Using double because it's the most precised value I can support
            SleepThread<T> thread = new SleepThread<T>(queue, arr[i], startSignal);
            producer[i] = thread;
        }

        // Try to start each thread at the same time
        for (SleepThread<T> thread : producer) {
            thread.start();
        }
        startSignal.countDown();
        // Wait until the last thread finished
        for (SleepThread<T> thread : producer) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return queue.getResult();
    }
}
