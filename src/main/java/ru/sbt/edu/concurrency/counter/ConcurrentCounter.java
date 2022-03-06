package ru.sbt.edu.concurrency.counter;
import java.util.concurrent.*;

public class ConcurrentCounter implements Counter{
    private long count;
    Semaphore semaphore;

    public ConcurrentCounter() {
        this.semaphore = new Semaphore(1, true);
        this.count = 0;
    }

    @Override
    public void increment() {
        try {
            semaphore.acquire();
            count++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    @Override
    public long getValue() {
        return count;
    }
}

