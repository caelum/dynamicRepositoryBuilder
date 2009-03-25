package org.repositorybuilder.executer;

import java.io.Serializable;

import org.hibernate.Session;
import org.repositorybuilder.operation.RepositoryOperation;


public class UniqueResultExecuter implements ResultExecuter {

    private final RepositoryOperation operation;
    private final Session session;

    public UniqueResultExecuter(RepositoryOperation operation, Session session) {
        this.operation = operation;
        this.session = session;
    }

    public Object execute() {
        return session.load(operation.getResultType(), (Serializable) operation.getArguments()[0]);
    }

}
