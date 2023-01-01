package core;

import java.util.HashMap;
import java.util.Map;

public class DataSaver {

    private Map<String, Object> coreMap;

    public DataSaver() {
        coreMap = new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> type) {
        return (T) coreMap.get(type.getCanonicalName());
    }

    public void put(String str, Object object) {
        coreMap.put(str, object);
    }
}