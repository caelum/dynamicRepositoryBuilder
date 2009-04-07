package org.repositorybuilder.operation;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * @author leonardobessa
 * 
 */
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

    public boolean expectsList() {
        return getName().startsWith("findAll");
    }

    public boolean expectsUniqueResult() {
        return !expectsList() && getName().startsWith("find");
    }

    public boolean usesBy() {
        String replacedName = this.name.replaceFirst("find(All)?", "");
        return replacedName.startsWith("By");
    }

    public Map<String, Object> getFindByMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        String replacedName = this.name.replaceFirst("find(All)?By", "");
        String[] strings = replacedName.split("And");
        for (int i=0; i<strings.length ; i++) {
            String attribute = strings[i].substring(0, 1).toLowerCase() + strings[i].substring(1);
            Object value = args[i];
            map.put(attribute, value);
        }
        return map;
    }

}
