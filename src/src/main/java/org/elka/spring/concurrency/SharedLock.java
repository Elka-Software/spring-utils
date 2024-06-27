package org.elka.spring.concurrency;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SharedLock implements Lock {
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Override
    public void lock() {
        readWriteLock.writeLock().lock();
    }

    @Override
    public void unlock() throws IllegalMonitorStateException {
        readWriteLock.writeLock().unlock();
    }

    @Override
    public boolean isLocked() {
        return readWriteLock.isWriteLocked() || readWriteLock.getReadLockCount() > 0;
    }

    public void readLock() {
        readWriteLock.readLock().lock();
    }

    public void readUnlock() {
        readWriteLock.readLock().unlock();
    }
}
