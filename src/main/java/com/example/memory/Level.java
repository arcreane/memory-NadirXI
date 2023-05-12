package com.example.memory;

public enum Level {
    EASY(12),
    MEDIUM(18),
    HARD(24);

    private final int duos;

    private Level(int duos) {
        this.duos = duos;
    }

    public int getDuos() {
        return duos;
    }
}