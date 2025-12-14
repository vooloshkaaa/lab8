package org.example.view;

import org.example.model.DndCharacter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterListModel extends AbstractListModel<String> {
    private final List<DndCharacter> data = new ArrayList<>();

    public void add(DndCharacter c) {
        data.add(c);
        int idx = data.size() - 1;
        fireIntervalAdded(this, idx, idx);
    }

    public List<DndCharacter> getAll() {
        return List.copyOf(data);
    }

    @Override
    public int getSize() {
        return data.size();
    }

    @Override
    public String getElementAt(int index) {
        return data.get(index).toDisplayString();
    }
}
