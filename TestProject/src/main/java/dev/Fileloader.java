package dev;

import dev.aronba.nyx.di.annotations.Candidate;

@Candidate
public class Fileloader {
    public String loadFile(){
        return "this is some content from a loaded File";
    }
}
