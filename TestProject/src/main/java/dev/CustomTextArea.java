package dev;

import dev.aronba.nyx.di.annotations.Candidate;

import javax.swing.*;

@Candidate
public class CustomTextArea extends JTextArea {
    CustomTextArea(Fileloader fileloader){
        this.setText(fileloader.loadFile());
    }
}
