package dev.aronba.nyx.banner;

import dev.aronba.nyx.NyxVersion;
import dev.aronba.nyx.enviroment.Environment;

import java.io.PrintStream;

public class NyxDefaultBanner implements Banner {

    @Override
    public void printBanner(Environment env, PrintStream out) {
        out.println("  ██╗    ███╗   ██╗██╗   ██╗██╗  ██╗    ██╗  \n" + " ██╔╝    ████╗  ██║╚██╗ ██╔╝╚██╗██╔╝    ╚██╗ \n" + "██╔╝     ██╔██╗ ██║ ╚████╔╝  ╚███╔╝      ╚██╗\n" + "╚██╗     ██║╚██╗██║  ╚██╔╝   ██╔██╗      ██╔╝\n" + " ╚██╗    ██║ ╚████║   ██║   ██╔╝ ██╗    ██╔╝ \n" + "  ╚═╝    ╚═╝  ╚═══╝   ╚═╝   ╚═╝  ╚═╝    ╚═╝  ");
        out.println("-:- Nyx -:-                            " + NyxVersion.version);
    }
}
