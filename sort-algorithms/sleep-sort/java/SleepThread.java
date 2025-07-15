import java.util.function.Consumer;

public class SleepThread extends Thread{

    volatile long id;
    volatile Integer sleepTimeSec;
    volatile Consumer<Integer> consumer;

    SleepThread(long id, Integer sleepTimeSec, Consumer<Integer> consumer) {
        this.id = id;
        this.sleepTimeSec = sleepTimeSec;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(this.sleepTimeSec * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        consumer.accept(this.sleepTimeSec);
    }
}
