package org.example.model;

import java.util.Objects;

public final class DndCharacter {
    private final String name;
    private final CharacterRace race;
    private final CharacterClass characterClass;
    private final Stats stats;

    public DndCharacter(String name, CharacterRace race, CharacterClass characterClass, Stats stats) {
        this.name = name;
        this.race = race;
        this.characterClass = characterClass;
        this.stats = stats;
    }

    public String getName() {
        return name;
    }

    public CharacterRace getRace() {
        return race;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public Stats getStats() {
        return stats;
    }

    public String toDisplayString() {
        return name + " | " + race + " | " + characterClass
                + " | STR=" + stats.getStrength()
                + " AGI=" + stats.getAgility()
                + " INT=" + stats.getIntelligence();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DndCharacter that)) return false;
        return Objects.equals(name, that.name) && race == that.race && characterClass == that.characterClass && Objects.equals(stats, that.stats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, race, characterClass, stats);
    }
}
