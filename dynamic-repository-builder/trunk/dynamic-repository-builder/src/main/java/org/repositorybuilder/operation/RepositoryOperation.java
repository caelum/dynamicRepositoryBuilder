package org.repositorybuilder.operation;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class RepositoryOperation {

    private final Class<?> resultType;
    private final String name;
    private final Object[] args;

    public RepositoryOperation(Method method, Object[] args) {
        this.args = args;
        this.resultType = this.getEntity(method);
        this.name = method.getName();
    }

    public Class<?> getResultType() {
        return resultType;
    }

    public String getName() {
        return name;
    }

    private final Class<?> getEntity(Method method) {
        Type type = method.getGenericReturnType();
        if (ParameterizedType.class.isAssignableFrom(type.getClass())) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return (Class<?>) parameterizedType.getActualTypeArguments()[0];
        } else {
            return (Class<?>) type;
        }
    }

    public Object[] getArguments() {
        return args;
    }

}
