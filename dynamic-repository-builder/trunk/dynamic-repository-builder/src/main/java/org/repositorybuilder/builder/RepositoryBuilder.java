package org.repositorybuilder.builder;

/**
 * @author leonardobessa
 * 
 */
public interface RepositoryBuilder {

    public <T> T getRepository(Class<T> clazz);

}