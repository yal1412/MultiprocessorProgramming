package ru.sbt.edu.concurrency.locks.theory;

import ru.sbt.edu.concurrency.locks.ILock;
import ru.sbt.edu.concurrency.util.ThreadID;

public class FilterLock implements ILock {
    private volatile int[] victim;
    private final int[] level;
    private final int n;

    public FilterLock(int n) {
        this.n = n;
        level = new int[n];
        victim = new int[n];
        for (int i = 0; i < n; i++) {
            level[i] = 0;
        }
    }

    @Override
    public void lock() {
        int me = ThreadID.get();
        for (int i = 1; i < n; i++) {
            level[me] = i;
            victim[i] = me;
            victim = victim;
            for (int k = 0; k < n; k++) {
                while (k != me && level[k] >= i && victim[i] == me) {
                }
            }
        }
    }

    @Override
    public void unlock() {
        int me = ThreadID.get();
        level[me] = 0;
    }
}
