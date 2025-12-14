package org.example.memento;

import org.example.model.DndCharacter;

public final class CharacterMemento {
    private final DndCharacter snapshot;

    public CharacterMemento(DndCharacter snapshot) {
        this.snapshot = snapshot;
    }

    public DndCharacter getSnapshot() {
        return snapshot;
    }
}
