package ru.sbt.edu.concurrency.locks.theory;

import ru.sbt.edu.concurrency.locks.ILock;
import ru.sbt.edu.concurrency.util.ThreadID;
import ru.sbt.edu.concurrency.util.TwoThreadIds;

public class LockTwo implements ILock {
    private int victim;

    @Override
    public void lock() {
        int i = ThreadID.get();
        System.out.println("Thread " + i);
        victim = i;
        System.out.println("Victim " + i);
        while (victim == i) {}
    }


    @Override
    public void unlock() {
    }
}
