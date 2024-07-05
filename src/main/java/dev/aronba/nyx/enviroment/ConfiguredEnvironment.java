package dev.aronba.nyx.enviroment;

public class ConfiguredEnvironment implements Environment{
    @Override
    public boolean containsProperty(String key) {
        return false;
    }

    @Override
    public String getProperty(String key) {
        return "";
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        return "";
    }

    @Override
    public <T> T getProperty(String key, Class<T> targetType) {
        return null;
    }

    @Override
    public <T> T getProperty(String key, Class<T> targetType, T defaultValue) {
        return null;
    }

    @Override
    public String getRequiredProperty(String key) throws IllegalStateException {
        return "";
    }

    @Override
    public <T> T getRequiredProperty(String key, Class<T> targetType) throws IllegalStateException {
        return null;
    }

    @Override
    public String resolvePlaceholders(String text) {
        return "";
    }

    @Override
    public String resolveRequiredPlaceholders(String text) throws IllegalArgumentException {
        return "";
    }
}
