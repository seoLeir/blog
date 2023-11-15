package io.seoLeir.blog.dto.publication;

import lombok.Getter;

public enum ActionType {
    UPDATE("update"),
    CREATE("create"),
    DELETE("delete");

    @Getter
    private final String value;

    ActionType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
