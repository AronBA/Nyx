package dev.aronba.nyx.banner;

import dev.aronba.nyx.enviroment.Environment;

import java.io.PrintStream;

@FunctionalInterface
public interface Banner {
    void printBanner(Environment env, PrintStream out);

    enum Mode {
        OFF,
        CONSOLE,
        LOG;
    }
}