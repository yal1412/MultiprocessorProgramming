package ru.sbt.edu.concurrency.counter;

public class MutexCounter implements Counter{
    private long value;
    @Override
    public void increment() {
        synchronized (this) {
            value++;
        }
    }

    @Override
    public long getValue() {
        synchronized (this) {
            return value;
        }
    }
}
