package core;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ObjectHierarchy<T> {
    private final Map<Class<T>, T> objectHierarchy;

    public ObjectHierarchy() {
        objectHierarchy = new HashMap<>();
    }

    public T getObjectInstance(Class<T> objectClassName) {
        T objectInstance;
        try {
            if (objectHierarchy.get(objectClassName) != null)
                objectInstance = objectHierarchy.get(objectClassName);
            else {
                objectInstance = objectClassName.getDeclaredConstructor().newInstance();
                objectHierarchy.put(objectClassName, objectInstance);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(String.format("Object: %s is not found | ERROR: %s ", objectClassName.toString(), e.getCause()));
        }
        return objectInstance;
    }

    public T getObjectInstance(Class<T> objectClassName, Object constructorParameter) {
        T objectInstance;
        try {
            if (objectHierarchy.get(objectClassName) != null)
                objectInstance = objectHierarchy.get(objectClassName);
            else {
                objectInstance = objectClassName.getDeclaredConstructor(constructorParameter.getClass()).newInstance(constructorParameter);
                objectHierarchy.put(objectClassName, objectInstance);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(String.format("Object: %s is not found | ERROR: %s ", objectClassName.toString(), e.getCause()));
        }
        return objectInstance;
    }
}