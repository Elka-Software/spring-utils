package org.elka.spring.concurrency;

import java.util.concurrent.locks.ReentrantLock;

public class FairLock implements Lock {
    private final ReentrantLock lock = new ReentrantLock(true); // true for fair mode

    @Override
    public void lock() {
        lock.lock();
    }

    @Override
    public void unlock() throws IllegalMonitorStateException {
        lock.unlock();
    }

    @Override
    public boolean isLocked() {
        return lock.isLocked();
    }
}