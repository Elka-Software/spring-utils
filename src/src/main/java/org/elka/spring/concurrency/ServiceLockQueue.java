package org.elka.spring.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
public class ServiceLockQueue implements ServiceLock {
    private final ConcurrentLinkedQueue<Runnable> requestQueue = new ConcurrentLinkedQueue<>();
    private final ThreadLocal<Boolean> isRequestQueued = ThreadLocal.withInitial(() -> false);

    @Override
    public void lock() {
        ServiceLock.super.lock();
    }

    @Override
    public void unlock() throws IllegalMonitorStateException {
        try {
            processQueue();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isLocked() {
        return ServiceLock.super.isLocked();
    }

    public void queueRequest(Runnable request) {
        if (!isRequestQueued.get()) {
            isRequestQueued.set(true);
            requestQueue.add(() -> {
                try {
                    request.run();
                } finally {
                    isRequestQueued.set(false);
                }
            });
            log.info("Request queued: {}", request);
        } else {
            log.info("Request already queued: {}", request);
        }
    }

    private void processQueue() {
        while (!requestQueue.isEmpty()) {
            Runnable request = requestQueue.poll();
            if (request != null) {
                log.info("Request run: {}", request);
                request.run();
            }
        }
    }
}
