package org.elka.spring.concurrency;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Slf4j
@RequiredArgsConstructor
public class LockLogger <T extends Lock> {

    private final T lock;

    /**
     * Acquires the assigned lock.
     *
     * <p>Other than acquiring the assigned lock, this logger also prints the statement
     * in this format</p>
     *
     * <p>[LOGGER NAME] locked in: [CLASS NAME] - at [DATE OF LOCKING]</p>
     */
    public void lock(@NotNull Class<?> debugClass) {
        lock.lock();
        log.info("{} locked in: {} - at {}", lock.getClass().getSimpleName(), debugClass.getSimpleName(), new Date());
    }

    /**
     * Attempts to release the assigned  lock.
     *
     * <p>Other than releasing the assigned lock, this logger also prints the statement
     * in this format</p>
     *
     * <p>[LOGGER NAME] unlocked in: [CLASS NAME] - at [DATE OF LOCKING]</p>
     */
    public void unlock(@NotNull Class<?> debugClass) throws IllegalMonitorStateException {
        lock.unlock();
        log.info("{} unlocked in: {} - at {}", lock.getClass().getSimpleName(), debugClass.getSimpleName(), new Date());
    }

}
