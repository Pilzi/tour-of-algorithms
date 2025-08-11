import java.util.concurrent.atomic.AtomicInteger;

public class SleepSortResultArray<T extends Number> {
    private final T[] queue;
    // Using an Atomic integer instead of synchronizing the produce function to optimize performance
    private final AtomicInteger index = new AtomicInteger(0);

    public SleepSortResultArray(T[] queue) {
        this.queue = queue;
    }

    public T[] getResult() {
        return this.queue;
    }

    public void produce(T item) {
        int i = index.getAndIncrement();
        queue[i] = item;
    }
}
