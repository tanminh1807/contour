package _base;

import _support.context.UserContext;
import core.Global;

public class BaseStep {
    private final UserContext userContext;

    public BaseStep() {
        this.userContext = Global.get(UserContext.class);
    }

    public UserContext currentUserContext() {
        return this.userContext;
    }

    protected <T extends BaseApiAction> T use(Class<T> apiName) {
        return currentUserContext().use(apiName);
    }

    public <T> void save(T instance){
        currentUserContext().save(instance);
    }

    public <T> T load(Class<T> className){
        return currentUserContext().load(className);
    }
}