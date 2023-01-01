package _support.context;

import _base.BaseApiAction;
import _base.BaseRestful;
import core.DataSaver;
import core.ObjectHierarchy;

public class UserContext {

    private final ObjectHierarchy objectHierarchy;
    protected final DataSaver dataSaver;

    public UserContext() {
        this.objectHierarchy = new ObjectHierarchy();
        this.dataSaver = new DataSaver();
    }

    @SuppressWarnings("unchecked")
    public <T extends BaseApiAction> T use(Class<T> pageName) {
        return (T) this.objectHierarchy.getObjectInstance(pageName, this);
    }

    @SuppressWarnings("unchecked")
    public <T extends BaseRestful> T getClient(Class<T> clientName) {
        return (T) this.objectHierarchy.getObjectInstance(clientName);
    }

    public <T> T load(Class<T> className) {
        return dataSaver.get(className);
    }

    public <T> void save(T instance) {
        dataSaver.put(instance.getClass().getCanonicalName(), instance);
    }
}