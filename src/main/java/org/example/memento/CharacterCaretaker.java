package org.example.memento;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

public class CharacterCaretaker {
    private final Deque<CharacterMemento> stack = new ArrayDeque<>();

    public void save(CharacterMemento memento) {
        stack.push(memento);
    }

    public Optional<CharacterMemento> restoreLast() {
        if (stack.isEmpty()) return Optional.empty();
        return Optional.of(stack.pop());
    }

    public boolean hasSavedState() {
        return !stack.isEmpty();
    }
}
