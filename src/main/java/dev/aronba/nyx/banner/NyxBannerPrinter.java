package dev.aronba.nyx.banner;

import dev.aronba.nyx.enviroment.Environment;
import org.slf4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class NyxBannerPrinter {
    private static final Banner DEFAULT_BANNER = new NyxDefaultBanner();
    private Banner fallbackBanner;
    private Banner banner;

    public NyxBannerPrinter(Banner banner) {
        this.fallbackBanner = banner;
    }

    public void print(Environment environment, PrintStream out) {
        Banner banner = this.getBanner(environment);
        banner.printBanner(environment, out);
    }

    public void print(Environment environment, Logger logger) {
        this.banner = this.getBanner(environment);
        try {
            logger.info(this.createStringFromBanner(environment,banner));
        } catch (Exception ex) {
            logger.warn("Failed to create a String for banner", ex);
        }
    }

    private Banner getBanner(Environment environment) {
        Banner textBanner = this.getTextBanner(environment);
        if (textBanner != null) {
            return textBanner;
        }
        return this.fallbackBanner != null ? this.fallbackBanner : DEFAULT_BANNER;
    }

    private String createStringFromBanner(Environment environment, Banner banner) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(byteArrayOutputStream, false, StandardCharsets.UTF_8);

        try {
            banner.printBanner(environment, out);
        } catch (Exception e) {
            try {
                out.close();
            } catch (Exception ex) {
                e.addSuppressed(ex);
            }
            throw e;
        }

        out.close();
        return byteArrayOutputStream.toString(StandardCharsets.UTF_8);
    }

    private Banner getTextBanner(Environment environment) {
        String bannerLocation = environment.getProperty("nyx.banner.location", "banner.txt");
        try {
            return new ResourceBanner(bannerLocation);
        } catch (Exception ignored) {
        }
        return null;
    }


}
