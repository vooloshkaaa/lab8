package org.example.controller;

import org.example.memento.CharacterCaretaker;
import org.example.memento.CharacterMemento;
import org.example.model.*;
import org.example.service.CharacterGenerator;
import org.example.service.JsonFileWriter;
import org.example.view.CharacterView;

import java.nio.file.Path;

public class CharacterController {

    private final CharacterView view;
    private final CharacterGenerator generator;
    private final JsonFileWriter jsonWriter;
    private final CharacterStore store;
    private final CharacterCaretaker caretaker;

    private DndCharacter current;

    public CharacterController(CharacterView view) {
        this.view = view;
        this.generator = new CharacterGenerator();
        this.jsonWriter = new JsonFileWriter();
        this.store = new CharacterStore();
        this.caretaker = new CharacterCaretaker();
        bind();
    }

    private void bind() {
        view.getGenerateButton().addActionListener(e -> onGenerateStats());
        view.getSaveStateButton().addActionListener(e -> onSaveState());
        view.getRestoreButton().addActionListener(e -> onRestore());
        view.getAddButton().addActionListener(e -> onAddCharacter());
        view.getExportJsonButton().addActionListener(e -> onExportJson());
    }

    private void onGenerateStats() {
        String name = view.getNameInput();
        if (name.isBlank()) {
            view.showError("Enter character name.");
            return;
        }
        CharacterRace race = view.getRaceSelected();
        CharacterClass cls = view.getClassSelected();

        Stats stats = generator.generateStats();
        current = new DndCharacter(name, race, cls, stats);
        view.setStats(stats.getStrength(), stats.getAgility(), stats.getIntelligence());
    }

    private void onSaveState() {
        if (current == null) {
            current = buildFromFields();
        }
        if (current == null) return;
        caretaker.save(new CharacterMemento(current));
        view.showInfo("State saved.");
    }

    private void onRestore() {
        var restored = caretaker.restoreLast();
        if (restored.isEmpty()) {
            view.showError("No saved state to restore.");
            return;
        }
        current = restored.get().getSnapshot();
        view.setStats(current.getStats().getStrength(), current.getStats().getAgility(), current.getStats().getIntelligence());
        view.showInfo("State restored.");
    }

    private void onAddCharacter() {
        DndCharacter toAdd = current != null ? current : buildFromFields();
        if (toAdd == null) return;

        store.add(toAdd);
        view.getListModel().add(toAdd);
        view.showInfo("Character added.");
    }

    private void onExportJson() {
        try {
            Path out = Path.of("output", "characters.json");
            jsonWriter.write(out, store.getAll());
            view.showInfo("Exported to " + out.toString());
        } catch (Exception ex) {
            view.showError("Export failed: " + ex.getMessage());
        }
    }

    private DndCharacter buildFromFields() {
        String name = view.getNameInput();
        if (name.isBlank()) {
            view.showError("Enter character name.");
            return null;
        }
        Integer str = view.getStr();
        Integer agi = view.getAgi();
        Integer intel = view.getIntelligence();

        if (str == null || agi == null || intel == null) {
            view.showError("Generate stats first (or restore saved state).");
            return null;
        }

        return new DndCharacter(
                name,
                view.getRaceSelected(),
                view.getClassSelected(),
                new Stats(str, agi, intel)
        );
    }
}
