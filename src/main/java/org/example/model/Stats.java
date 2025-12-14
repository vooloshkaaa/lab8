package org.example.model;

import java.util.Objects;

public final class Stats {
    private final int strength;
    private final int agility;
    private final int intelligence;

    public Stats(int strength, int agility, int intelligence) {
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stats stats)) return false;
        return strength == stats.strength && agility == stats.agility && intelligence == stats.intelligence;
    }

    @Override
    public int hashCode() {
        return Objects.hash(strength, agility, intelligence);
    }
}
