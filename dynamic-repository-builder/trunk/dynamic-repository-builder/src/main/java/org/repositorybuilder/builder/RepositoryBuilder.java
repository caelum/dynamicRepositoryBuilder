package org.repositorybuilder.builder;

public interface RepositoryBuilder {

    public <T> T getRepository(Class<T> clazz);

}