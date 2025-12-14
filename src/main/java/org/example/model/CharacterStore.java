package org.example.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CharacterStore {
    private final List<DndCharacter> characters = new ArrayList<>();

    public void add(DndCharacter character) {
        characters.add(character);
    }

    public List<DndCharacter> getAll() {
        return Collections.unmodifiableList(characters);
    }
}
