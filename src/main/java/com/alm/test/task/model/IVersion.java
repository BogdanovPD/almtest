package com.alm.test.task.model;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

public interface IVersion {

    enum CHANGE_TYPE {

        ADDITION("$new$"), REMOVING("$remove$"), EDITING("$edit$");

        @Getter
        private String type;

        CHANGE_TYPE(String type) {
            this.type = type;
        }
    }


    int addChange(int idx, LinkedHashMap<Integer, String> newElements);

    int getCurrentVersion();

    default String buildAdditionChange(String change) {
        return CHANGE_TYPE.ADDITION.getType().concat(change);
    }

    default String buildEditingChange(String change) {
        return CHANGE_TYPE.REMOVING.getType().concat(change);
    }

    default String buildRemoveChange() {
        return CHANGE_TYPE.REMOVING.getType();
    }

}
