package org.repositorybuilder.builder.hibernate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.hibernate.Session;
import org.repositorybuilder.builder.RepositoryBuilder;
import org.repositorybuilder.hibernate.HibernateRepositoryBuilderHandler;

/**
 * @author leonardobessa
 * 
 */
public class HibernateRepositoryBuilder implements RepositoryBuilder {

    private Session session;

    public HibernateRepositoryBuilder(Session session) {
        this.session = session;
    }

    @SuppressWarnings("unchecked")
    public <T> T getRepository(Class<T> clazz) {
        InvocationHandler handler = new HibernateRepositoryBuilderHandler(session);
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[] { clazz }, handler);
    }

    public <T> T getConditions(Class<T> clazz) {
        // TODO Auto-generated method stub
        return null;
    }

}
