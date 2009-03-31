package org.repositorybuilder.executer;

import java.io.Serializable;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.repositorybuilder.builder.hibernate.CriteriaBuilder;
import org.repositorybuilder.operation.RepositoryOperation;

/**
 * @author leonardobessa
 * 
 */
public class UniqueResultExecuter implements ResultExecuter {

    private final RepositoryOperation operation;
    private final Session session;

    public UniqueResultExecuter(RepositoryOperation operation, Session session) {
        this.operation = operation;
        this.session = session;
    }

    public Object execute() {
        Object result;
        Class<?> resultType = operation.getResultType();
        if (operation.getName().equals("find")) {
            result = session.load(resultType, (Serializable) operation.getArguments()[0]);
        } else {
            Map<String, Object> map = new ConditionsMapBuilder().getMap(operation);
            Criteria criteria = new CriteriaBuilder(session).getCriteria(map, resultType);
            result = criteria.uniqueResult();
        }
        return result;
    }

}
