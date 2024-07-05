package dev;

import dev.aronba.nyx.di.annotations.Candidate;

@Candidate
public class RestService {
    public String getUserInformation(){
        return "John Doa";
    }
}
