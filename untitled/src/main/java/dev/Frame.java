package dev;

import dev.aronba.nyx.di.annotations.Candidate;

import javax.swing.*;

@Candidate
public class Frame extends JFrame {
    private final RestService restService;
    Frame(RestService restService){
        this.restService = restService;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(300,300);
        this.add(new JLabel(restService.getUserInformation()));
        this.setVisible(true);
    }
}
