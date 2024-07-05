package dev.aronba.nyx.banner;

import dev.aronba.nyx.enviroment.Environment;

import java.io.*;

public class ResourceBanner implements Banner {

    private final String resourceLocation;

    ResourceBanner(String location) throws Exception {
        throw new Exception("Not Implemented");
    }

    @Override
    public void printBanner(Environment env, PrintStream out) {
        out.println("This is a not implemented resource banner.");
    }
}
