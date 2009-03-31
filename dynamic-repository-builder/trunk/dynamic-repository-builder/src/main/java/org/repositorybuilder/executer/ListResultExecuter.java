package org.repositorybuilder.executer;

import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.repositorybuilder.builder.hibernate.CriteriaBuilder;
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
        Map<String, Object> map = new ConditionsMapBuilder().getMap(operation);
        try {
            return findAllBy(map, operation.getResultType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Object findAllBy(Map<String, Object> map, Class<?> type) {
        Criteria criteria = new CriteriaBuilder(session).getCriteria(map, type);
        return criteria.list();
    }

}
