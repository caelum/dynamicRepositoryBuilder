package org.repositorybuilder.executer;

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
        Object result;
        Criteria criteria = new CriteriaBuilder(session).getCriteria(operation);
        result = criteria.list();
        return result;
    }

}
