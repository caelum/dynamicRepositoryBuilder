package br.com.caelum.recorder.modelo;

import java.util.Collection;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.repositorybuilder.builder.hibernate.CriterionConditions;

public class RestaurantCondition implements CriterionConditions {

    private Criterion lastCriterion;

    public RestaurantCondition cityIsIn(Collection<City> cities) {
        lastCriterion = Restrictions.in("city", cities);
        return this;
    }

    public Criterion getCriterion() {
        return this.lastCriterion;
    }

}
