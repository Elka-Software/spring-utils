package org.elka.spring.concurrency;

import java.util.concurrent.locks.ReentrantLock;

public interface ServiceLock extends Lock {
    ReentrantLock lock = new ReentrantLock();

    default void lock() {
        lock.lock();
    }

    default void unlock() throws IllegalMonitorStateException {
        lock.unlock();
    }

    default boolean isLocked() {
        return lock.isLocked();
    }
}
