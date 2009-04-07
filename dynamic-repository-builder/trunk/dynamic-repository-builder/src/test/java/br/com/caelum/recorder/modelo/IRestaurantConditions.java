package br.com.caelum.recorder.modelo;

import java.util.Collection;

import org.repositorybuilder.builder.hibernate.CriterionConditions;


public interface IRestaurantConditions extends CriterionConditions {

    IRestaurantConditions cityIsIn(Collection<City> cities);

}
