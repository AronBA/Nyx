package dev.aronba.nyx.di;

import dev.aronba.nyx.di.annotations.Candidate;
import dev.aronba.nyx.di.annotations.Inject;
import dev.aronba.nyx.di.annotations.Primary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.HashSet;


public class NyxApplicationContext {

    private final static Logger LOG = LoggerFactory.getLogger(NyxApplicationContext.class);
    private final HashMap<Class<?>, Object> candidateInstances = new HashMap<>();
    private HashSet<Class<?>> candidateClasses = new HashSet<>();


    public void run() {
        findDiCandidates();
        instantiateCandidates();
    }


    private void instantiateCandidates() {
        for (Class<?> candidate : candidateClasses) {
            Object object = instantiateCandidate(candidate);
            //TODO -> execute life cycle methods
            LOG.debug("created Instance of: {}", candidate.getName());
        }
    }

    private Object instantiateCandidate(Class<?> candidate) {
        try {
            return createInstance(findValidConstructor(candidate));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException exception) {
            LOG.error(exception.getMessage(), exception);
            return null;
        }
    }

    private Object createInstance(Constructor<?> validConstructor) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        int parameterCount = validConstructor.getParameterCount();
        validConstructor.setAccessible(true);
        if (parameterCount == 0) return validConstructor.newInstance();

        Object[] args = new Object[parameterCount];
        validConstructor.setAccessible(true);
        Parameter[] parameters = validConstructor.getParameters();
        for (int i = 0; i < parameterCount; i++) {
            Parameter parameter = parameters[i];
            args[i] = getDependencies(parameter);

        }
        return validConstructor.newInstance(args);
    }

    private Object getDependencies(Parameter parameter) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Class<?> parameterType = parameter.getType();

        if (!parameterType.isAnnotationPresent(Candidate.class))
            throw new IllegalArgumentException("Dependency " + parameterType.getName() + " is not a DI Candidate \n hint: Annotate requested dependency class with @Candidate");

        Object singleton = candidateInstances.get(parameterType);
        if (requiresSingleton(parameter, singleton)) {
            singleton = createInstance(findValidConstructor(parameterType));
            LOG.debug("created Instance of dependency: {}", singleton.getClass().getName());
        }
        candidateInstances.put(parameter.getType(), singleton);
        return singleton;
    }

    private boolean requiresSingleton(Parameter parameter, Object singleton) {
        if (singleton != null) return false;

        Inject annotation = parameter.getAnnotation(Inject.class);
        if (annotation == null) return true;

        return annotation.required();
    }

    private Constructor<?> findValidConstructor(Class<?> candidate) throws NoSuchMethodException {
        Constructor<?>[] constructors = candidate.getDeclaredConstructors();
        if (constructors.length == 0) return candidate.getConstructor();
        if (constructors.length == 1) return constructors[0];
        for (Constructor<?> constructor : constructors) {
            if (constructor.isAnnotationPresent(Primary.class)) return constructor;
        }
        return constructors[0];
    }


    private void findDiCandidates() {
        try{
            candidateClasses = ClassPathScanner.getAllClassesInPackage("dev");
            LOG.debug("Found {} candidates for dependency Injection", candidateClasses.size());
        }catch (Exception e){
            LOG.error(e.getMessage(), e);
        }
    }

}
