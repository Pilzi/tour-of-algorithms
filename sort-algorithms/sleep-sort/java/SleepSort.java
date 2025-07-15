import java.util.function.Consumer;

public class SleepSort {

    public <T extends Number> T[] sort(T[] arr) {
        SleepThread<T>[] sleepThreads = new SleepThread[arr.length];
        // When a thread wakes up, it tells the consumer, to add the thread value to the sleepSortResultArray
        T[] sleepSortResult = ArrayUtils.createArray(arr[0].getClass(), arr.length);

        Consumer<T> task = (T sleepTime) -> {
            // Find the next empty place to insert the number and insert it
            int index = 0;
            while (sleepSortResult[index] != null) {
                index++;
            }
            sleepSortResult[index] = sleepTime;
        };

        for (int i = 0; i < arr.length; i++) {
            SleepThread thread = new SleepThread<T>(i, (T) NumberUtils.convertNumber(Double.class, arr[i]), task);
            sleepThreads[i] = thread;
        }

        // Try to start each thread at the same time
        for (SleepThread thread : sleepThreads) {
            thread.start();
        }

        // Wait until the last thread finished
        for (SleepThread<T> thread : sleepThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            Thread.sleep(1000, 0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return sleepSortResult;
    }
}
