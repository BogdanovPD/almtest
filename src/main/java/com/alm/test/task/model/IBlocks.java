package com.alm.test.task.model;

import java.util.List;
import java.util.Map;

public interface IBlocks {

    int BLOCK_SIZE = 300;
    int BLOCK_EDIT_BUFFER = 100;

    void tailNewBlocks(String text);
    void editBlocks(int index, int position, String text);
    void deleteBlocks(int index, int position, int size);
    List<String> getAllBlocks();
    Map<Integer, String> getUpdatedBlocks(int versionFrom);

}
