package br.com.caelum.recorder.modelo;

import java.util.Collection;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class RestaurantCondition implements IRestaurantConditions {

    private Criterion lastCriterion;

    public IRestaurantConditions cityIsIn(Collection<City> cities) {
        lastCriterion = Restrictions.in("city", cities);
        return this;
    }

    public Criterion getCriterion() {
        return this.lastCriterion;
    }

}
