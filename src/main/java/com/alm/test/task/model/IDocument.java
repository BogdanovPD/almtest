package com.alm.test.task.model;

import java.util.Map;

public interface IDocument {

    Map<Integer, String> tailNewText(String text, int version);
    Map<Integer, String> editText(String text, int index, int version,  int position);
    Map<Integer, String> deleteText(int index, int version,  int position, int size);

}
