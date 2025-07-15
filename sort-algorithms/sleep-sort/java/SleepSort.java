import java.util.function.Consumer;

public class SleepSort {

    public Integer[] sort(Integer[] arr){
        SleepThread[] sleepThreads = new SleepThread[arr.length];
        // When a thread wakes up, it tells the consumer, to add the thread value to the sleepSortResultArray
        Integer[] sleepSortResult = new Integer[arr.length];

        Consumer<Integer> task = (Integer sleepTime) -> {
            // Find the next empty place to insert the number and insert it
            int index = 0;
            while (sleepSortResult[index] != null) {
                index++;
            }
            sleepSortResult[index] = sleepTime;
        };

        for (int i = 0; i < arr.length; i++) {
            SleepThread thread = new SleepThread(i, NumberUtils.convertNumber(Integer.class, arr[i]), task);
            sleepThreads[i] = thread;
        }

        // Try to start each thread at the same time
        for (SleepThread thread : sleepThreads) {
            thread.start();
        }

        // Wait until the last thread finished
        for (SleepThread thread : sleepThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return sleepSortResult;
    }
}
