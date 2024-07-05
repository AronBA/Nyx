package dev.aronba.nyx;

import dev.aronba.nyx.banner.Banner;
import dev.aronba.nyx.banner.Banner.Mode;
import dev.aronba.nyx.banner.NyxBannerPrinter;
import dev.aronba.nyx.di.NyxApplicationContext;
import dev.aronba.nyx.enviroment.ConfiguredEnvironment;
import dev.aronba.nyx.enviroment.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NyxApplication {
    private static final Logger LOG = LoggerFactory.getLogger(NyxApplication.class);

    private Banner.Mode bannerMode;
    private Banner banner;
    private NyxApplicationContext applicationContext;
    private Environment environment;

    public NyxApplication() {
        this.bannerMode = Mode.CONSOLE;
    }

    public static void run(String[] args) {
        new NyxApplication().start(args);
    }

    public void start(String[] args) {
        Environment environment = this.prepareEnviroment(args);
        this.printBanner(environment);
        this.applicationContext = this.createNyxApplicationContext();
        this.applicationContext.run();
    }

    private Environment prepareEnviroment(String[] args) {
        //todo -> some more stuff
        return this.createOrGetConfiguredEnviroment(args);
    }

    private Environment createOrGetConfiguredEnviroment(String[] args) {
        if (this.environment != null) {
            return this.environment;
        }
        //todo -> some more stuff
        return new ConfiguredEnvironment();
    }

    private NyxApplicationContext createNyxApplicationContext() {
        return new NyxApplicationContext();
    }

    private void printBanner(Environment environment) {
        if (null == this.bannerMode || bannerMode == Mode.OFF) return;
        NyxBannerPrinter nyxBannerPrinter = new NyxBannerPrinter(this.banner);
        switch (this.bannerMode) {
            case LOG -> nyxBannerPrinter.print(environment, LOG);
            case CONSOLE -> nyxBannerPrinter.print(environment, System.out);
        }
    }
}
