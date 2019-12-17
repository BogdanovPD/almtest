package com.alm.test.task.model.impl;

import com.alm.test.task.model.IVersion;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
@Scope("prototype")
public class Version implements IVersion {

    private AtomicInteger currentVersion = new AtomicInteger(0);
    private final Map<Integer, LinkedHashMap<Integer, String>> changes = new HashMap<>();
    private final Lock lock = new ReentrantLock();

    @Override
    public int addChange(int idx, LinkedHashMap<Integer, String> newElements) {
        lock.lock();
        try {
            newElements.forEach((k, v) -> {
                newElements.put(k, buildAdditionChange(v));
            });
            changes.put(currentVersion.incrementAndGet(), newElements);
            return currentVersion.get();
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public int getCurrentVersion() {
        lock.lock();
        try {
            return currentVersion.get();
        }
        finally {
            lock.unlock();
        }
    }

}
