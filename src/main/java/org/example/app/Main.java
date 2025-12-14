package org.example.app;

import org.example.controller.CharacterController;
import org.example.view.CharacterView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CharacterView view = new CharacterView();
            new CharacterController(view);
            view.setVisible(true);
        });
    }
}
