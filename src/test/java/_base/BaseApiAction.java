package _base;

import _support.context.UserContext;

public class BaseApiAction {

    protected UserContext userContext;

    protected BaseApiAction(UserContext context) {
        this.userContext = context;
    }

    public <T extends BaseRestful> T getClient(Class<T> apiClassName) {
        return userContext.getClient(apiClassName);
    }
}