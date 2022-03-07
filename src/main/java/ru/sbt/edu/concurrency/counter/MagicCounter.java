package ru.sbt.edu.concurrency.counter;

import ru.sbt.edu.concurrency.locks.theory.FilterLock;

public class MagicCounter implements Counter{
    private long count = 0;
    private final FilterLock filterLock;

    public MagicCounter(int n) {
        filterLock = new FilterLock(n);
    }

    @Override
    public void increment() {
        filterLock.lock();
        try {
            long temp = count;
            count = temp + 1;
        } finally {
            filterLock.unlock();
        }
    }

    @Override
    public long getValue() {
        return count;
    }
}
