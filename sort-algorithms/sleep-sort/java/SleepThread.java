import java.util.function.Consumer;

public class SleepThread<T extends Number> extends Thread{

    volatile long id;
    volatile T sleepTimeSec;
    volatile Consumer<T> consumer;

    SleepThread(long id, T sleepTimeSec, Consumer<T> consumer) {
        this.id = id;
        this.sleepTimeSec = sleepTimeSec;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        Double exactTimeToSleep = NumberUtils.convertNumber(Double.class,this.sleepTimeSec) * 1000;
        try {
            Thread.sleep(NumberUtils.convertNumber( Long.class, exactTimeToSleep), 0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        consumer.accept(this.sleepTimeSec);
    }
}
