package dev;

import dev.aronba.nyx.di.annotations.Candidate;

import javax.swing.*;

@Candidate
public class TextEditor extends JFrame {
    public TextEditor(CustomTextArea customTextArea) {
        this.setTitle("Text Editor");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);

        this.add(customTextArea);
        this.setVisible(true);

    }
}
