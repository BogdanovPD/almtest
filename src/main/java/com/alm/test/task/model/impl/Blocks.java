package com.alm.test.task.model.impl;

import com.alm.test.task.model.IBlocks;
import com.alm.test.task.model.IVersion;
import com.google.common.base.Splitter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@Scope("prototype")
public class Blocks implements IBlocks {

    private final List<String> blocks = new LinkedList<>();
    private final IVersion version = new Version();

    private final Lock lock = new ReentrantLock();

    @Override
    public void tailNewBlocks(String text) {
        lock.lock();
        try {
            List<String> blocksInput;
            int rest = 0;
            if (!blocks.isEmpty()) {
                int lastIdx = blocks.size() - 1;
                String lastBlock = blocks.get(lastIdx);
                rest = (IBlocks.BLOCK_SIZE - IBlocks.BLOCK_EDIT_BUFFER) - lastBlock.length();
                if (rest > 0) {
                    blocks.set(lastIdx, lastBlock.concat(text.substring(0, rest)));
                }
            }
            Iterable<String> blocksIter = Splitter.fixedLength(IBlocks.BLOCK_SIZE).split(
                    rest > 0 ? text.substring(rest) : text);
            blocksInput = StreamSupport.stream(blocksIter.spliterator(), false)
                    .collect(Collectors.toList());
            for (int i = 0; i < blocksInput.size(); i++) {
                StringBuilder sb = new StringBuilder();
                sb.append(blocksInput.get(i));
                sb.append(IBlocks.BLOCK_SIZE);
                blocksInput.set(i, sb.toString());
            }

            blocks.addAll(blocksInput);
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public void editBlocks(int index, int position, String text) {
        lock.lock();
        try {

        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public void deleteBlocks(int index, int position, int size) {
        lock.lock();
        try {

        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public List<String> getAllBlocks() {
        return blocks;
    }

    @Override
    public Map<Integer, String> getUpdatedBlocks(int versionFrom) {
        return null;
    }

}
