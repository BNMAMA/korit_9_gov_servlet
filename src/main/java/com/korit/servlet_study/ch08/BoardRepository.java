package com.korit.servlet_study.ch08;

import java.util.Objects;

public class BoardRepository {
    private static BoardRepository instance;

    public static BoardRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new BoardRepository();
        }
        return instance;
    }

}
