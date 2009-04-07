package br.com.caelum.recorder.hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.repositorybuilder.builder.hibernate.HibernateRepositoryBuilder;

import br.com.caelum.recorder.modelo.City;
import br.com.caelum.recorder.modelo.Restaurant;
import br.com.caelum.recorder.modelo.RestaurantConditions;
import br.com.caelum.recorder.modelo.RestaurantRepository;

/**
 * @author leonardobessa
 * 
 */
public class HibernateRestrictionInTest {

    @Test
    public void listAllRestaurantsInSelectedCities() {
        Mockery mockery = new Mockery();
        final Session session = mockery.mock(Session.class);
        final Collection<Restaurant> expectedResult = new ArrayList<Restaurant>();
        final Criteria criteria = mockery.mock(Criteria.class);
        City sp = new City("Sao Paulo");
        City rj = new City("Rio");
        final List<City> cities = Arrays.asList(sp, rj);
        mockery.checking(new Expectations() {
            {
                one(session).createCriteria(with(equal(Restaurant.class)));
                will(returnValue(criteria));
                one(criteria).add(with(new SimpleExpressionMatcher(Restrictions.in("city", cities))));
                will(returnValue(criteria));
                one(criteria).list();
                will(returnValue(expectedResult));
            }
        });
        HibernateRepositoryBuilder recorder = new HibernateRepositoryBuilder(session);
        RestaurantRepository repository = recorder.getRepository(RestaurantRepository.class);

        RestaurantConditions where = recorder.getConditions(RestaurantConditions.class);
        List<Restaurant> list = repository.findAll(where.cityIsIn(cities));

        Assert.assertEquals(expectedResult, list);
        mockery.assertIsSatisfied();
    }

}
