package org.example.view;

import org.example.model.CharacterClass;
import org.example.model.CharacterRace;

import javax.swing.*;
import java.awt.*;

public class CharacterView extends JFrame {

    private final JTextField nameField = new JTextField(18);
    private final JComboBox<CharacterRace> raceBox = new JComboBox<>(CharacterRace.values());
    private final JComboBox<CharacterClass> classBox = new JComboBox<>(CharacterClass.values());

    private final JTextField strField = new JTextField(4);
    private final JTextField agiField = new JTextField(4);
    private final JTextField intField = new JTextField(4);

    private final JButton generateButton = new JButton("Generate Stats");
    private final JButton saveStateButton = new JButton("Save State");
    private final JButton restoreButton = new JButton("Restore");
    private final JButton addButton = new JButton("Add Character");
    private final JButton exportJsonButton = new JButton("Export JSON");

    private final CharacterListModel listModel = new CharacterListModel();
    private final JList<String> characterList = new JList<>(listModel);

    public CharacterView() {
        super("DnD Character Generator (MVC + Memento + JSON)");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(860, 520);
        setLocationRelativeTo(null);

        strField.setEditable(false);
        agiField.setEditable(false);
        intField.setEditable(false);

        setLayout(new BorderLayout(12, 12));
        add(buildLeftPanel(), BorderLayout.WEST);
        add(buildRightPanel(), BorderLayout.CENTER);
    }

    private JPanel buildLeftPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        p.add(row("Name:", nameField));
        p.add(row("Race:", raceBox));
        p.add(row("Class:", classBox));

        p.add(Box.createVerticalStrut(10));
        p.add(new JLabel("Stats"));
        p.add(row("STR:", strField));
        p.add(row("AGI:", agiField));
        p.add(row("INT:", intField));

        p.add(Box.createVerticalStrut(10));
        p.add(generateButton);

        p.add(Box.createVerticalStrut(10));
        JPanel mem = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mem.add(saveStateButton);
        mem.add(restoreButton);
        p.add(mem);

        p.add(Box.createVerticalStrut(10));
        p.add(addButton);

        p.add(Box.createVerticalStrut(10));
        p.add(exportJsonButton);

        return p;
    }

    private JPanel buildRightPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        p.add(new JLabel("Created characters:"), BorderLayout.NORTH);
        p.add(new JScrollPane(characterList), BorderLayout.CENTER);
        return p;
    }

    private JPanel row(String label, JComponent comp) {
        JPanel r = new JPanel(new FlowLayout(FlowLayout.LEFT));
        r.add(new JLabel(label));
        r.add(comp);
        return r;
    }

    public String getNameInput() {
        return nameField.getText().trim();
    }

    public CharacterRace getRaceSelected() {
        return (CharacterRace) raceBox.getSelectedItem();
    }

    public CharacterClass getClassSelected() {
        return (CharacterClass) classBox.getSelectedItem();
    }

    public void setStats(int str, int agi, int intel) {
        strField.setText(String.valueOf(str));
        agiField.setText(String.valueOf(agi));
        intField.setText(String.valueOf(intel));
    }

    public Integer getStr() {
        return parseIntOrNull(strField.getText());
    }

    public Integer getAgi() {
        return parseIntOrNull(agiField.getText());
    }

    public Integer getIntelligence() {
        return parseIntOrNull(intField.getText());
    }

    private Integer parseIntOrNull(String s) {
        try {
            if (s == null || s.isBlank()) return null;
            return Integer.parseInt(s.trim());
        } catch (Exception e) {
            return null;
        }
    }

    public void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showInfo(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public JButton getGenerateButton() {
        return generateButton;
    }

    public JButton getSaveStateButton() {
        return saveStateButton;
    }

    public JButton getRestoreButton() {
        return restoreButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getExportJsonButton() {
        return exportJsonButton;
    }

    public CharacterListModel getListModel() {
        return listModel;
    }
}
