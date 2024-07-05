package dev.aronba.nyx.di;

import dev.aronba.nyx.di.annotations.Candidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.util.*;

public class ClassPathScanner {
    private static final Logger LOG = LoggerFactory.getLogger(ClassPathScanner.class);

    public static HashSet<Class<?>> getAllClassesInPackage(String packageName) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        HashSet<Class<?>> classes = new HashSet<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    private static List<Class<?>> findClasses(File directory, String packageName) throws Exception {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                Class<?> klass = Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6));
                if (klass.isAnnotationPresent(Candidate.class)){
                    classes.add(klass);
                    LOG.trace("found class {}", klass.getName());
                }
            }
        }
        return classes;
    }

}