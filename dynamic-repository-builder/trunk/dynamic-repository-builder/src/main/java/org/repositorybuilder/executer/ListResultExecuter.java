package org.repositorybuilder.executer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.repositorybuilder.operation.RepositoryOperation;

/**
 * @author leonardobessa
 * 
 */
public class ListResultExecuter implements ResultExecuter {

    private final RepositoryOperation operation;
    private final Session session;

    public ListResultExecuter(RepositoryOperation operation, Session session) {
        this.operation = operation;
        this.session = session;
    }

    public Object execute() {
        String replacedName = this.operation.getName().replaceFirst("findAllBy", "");

        String[] strings = replacedName.split("And");
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < strings.length; i++) {
            String attribute = strings[i].substring(0, 1).toLowerCase() + strings[i].substring(1);
            map.put(attribute, this.operation.getArguments()[i]);
        }
        try {
            return findAllBy(map, operation.getResultType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Object findAllBy(Map<String, Object> map, Class<?> type) {
        Criteria criteria = session.createCriteria(type);
        Set<String> attributes = map.keySet();
        for (String propertyName : attributes) {
            criteria.add(Restrictions.eq(propertyName, map.get(propertyName)));
        }
        return criteria.list();
    }

}
