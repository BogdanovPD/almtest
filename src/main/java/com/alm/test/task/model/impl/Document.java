package com.alm.test.task.model.impl;

import com.alm.test.task.model.IBlocks;
import com.alm.test.task.model.IDocument;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class Document implements IDocument {

    private static int counter = 0;

    @Getter
    private final IBlocks blocks;

    @Getter
    private final int id = counter++;


    @Override
    public Map<Integer, String> tailNewText(String text, int version) {
        return null;
    }

    @Override
    public Map<Integer, String> editText(String text, int index, int version, int position) {
        return null;
    }

    @Override
    public Map<Integer, String> deleteText(int index, int version, int position, int size) {
        return null;
    }
}
