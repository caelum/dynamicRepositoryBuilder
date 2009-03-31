package org.repositorybuilder.hibernate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.hibernate.Session;
import org.repositorybuilder.executer.ListResultExecuter;
import org.repositorybuilder.executer.ResultExecuter;
import org.repositorybuilder.executer.UniqueResultExecuter;
import org.repositorybuilder.operation.RepositoryOperation;

/**
 * @author leonardobessa
 * 
 */
public class HibernateRepositoryBuilderHandler implements InvocationHandler {

    private Session session;

    public HibernateRepositoryBuilderHandler(Session session) {
        this.session = session;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RepositoryOperation operation = new RepositoryOperation(method, args);
        ResultExecuter analizer = this.getResultAnalizer(operation);
        return analizer.execute();
    }

    private ResultExecuter getResultAnalizer(RepositoryOperation operation) {
        if (operation.getName().startsWith("findAll")) {
            return new ListResultExecuter(operation, session);
        } else if (operation.getName().startsWith("find")) {
            return new UniqueResultExecuter(operation, session);
        }
        throw new IllegalArgumentException("Invalid mehtod name:" + operation.getName());
    }

}
