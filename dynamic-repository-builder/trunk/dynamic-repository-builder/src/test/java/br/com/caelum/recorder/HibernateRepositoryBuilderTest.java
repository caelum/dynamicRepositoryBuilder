package br.com.caelum.recorder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.repositorybuilder.builder.hibernate.HibernateRepositoryBuilder;

import br.com.caelum.recorder.modelo.Restaurante;
import br.com.caelum.recorder.modelo.RestauranteRepository;
import br.com.caelum.recorder.test.SimpleExpressionMatcher;

/**
 * @author leonardobessa
 * 
 */
public class HibernateRepositoryBuilderTest {

    @Test
    public void listEntityWithOneAttribute() {
        Mockery mockery = new Mockery();
        final Session session = mockery.mock(Session.class);
        final List<Restaurante> expectedResult = new ArrayList<Restaurante>();
        final Criteria criteria = mockery.mock(Criteria.class);
        mockery.checking(new Expectations() {
            {
                one(session).createCriteria(with(equal(Restaurante.class)));
                will(returnValue(criteria));
                one(criteria).add(with(new SimpleExpressionMatcher(Restrictions.eq("nome", "Fasano"))));
                will(returnValue(criteria));
                one(criteria).list();
                will(returnValue(expectedResult));
            }
        });
        HibernateRepositoryBuilder recorder = HibernateRepositoryBuilder.newInstance(session);
        RestauranteRepository repository = recorder.getRepository(RestauranteRepository.class);
        List<Restaurante> list = repository.findAllByNome("Fasano");
        Assert.assertEquals(expectedResult, list);
        mockery.assertIsSatisfied();
    }

    @Test
    public void shouldListEntityByTwoAttributes() {
        Mockery mockery = new Mockery();
        final Session session = mockery.mock(Session.class);
        final List<Restaurante> expectedResult = new ArrayList<Restaurante>();
        final Criteria criteria = mockery.mock(Criteria.class);
        mockery.checking(new Expectations() {
            {
                one(session).createCriteria(with(equal(Restaurante.class)));
                will(returnValue(criteria));
                one(criteria).add(with(new SimpleExpressionMatcher(Restrictions.eq("nome", "Fasano"))));
                will(returnValue(criteria));
                one(criteria).add(with(new SimpleExpressionMatcher(Restrictions.eq("especialidade", "Italiana"))));
                will(returnValue(criteria));
                one(criteria).list();
                will(returnValue(expectedResult));
            }
        });
        HibernateRepositoryBuilder recorder = HibernateRepositoryBuilder.newInstance(session);
        RestauranteRepository repository = recorder.getRepository(RestauranteRepository.class);
        List<Restaurante> list = repository.findAllByNomeAndEspecialidade("Fasano", "Italiana");
        Assert.assertEquals(expectedResult, list);
        mockery.assertIsSatisfied();
    }

    @Test
    public void shouldFindEntityById() {
        Mockery mockery = new Mockery();
        final Session session = mockery.mock(Session.class);
        final Restaurante expectedResult = new Restaurante();
        final Serializable id = mockery.mock(Serializable.class);
        mockery.checking(new Expectations() {
            {
                one(session).load(with(equal(Restaurante.class)), with(equal(id)));
                will(returnValue(expectedResult));
            }
        });
        HibernateRepositoryBuilder recorder = HibernateRepositoryBuilder.newInstance(session);
        RestauranteRepository repository = recorder.getRepository(RestauranteRepository.class);
        Restaurante restaurante = repository.find(id);
        Assert.assertEquals(expectedResult, restaurante);
        mockery.assertIsSatisfied();
    }

    @Test
    public void shouldFindEntityByNome() {
        Mockery mockery = new Mockery();
        final Session session = mockery.mock(Session.class);
        final Restaurante expectedResult = new Restaurante();
        final Criteria criteria = mockery.mock(Criteria.class);
        mockery.checking(new Expectations() {
            {
                one(session).createCriteria(with(equal(Restaurante.class)));
                will(returnValue(criteria));
                one(criteria).add(with(new SimpleExpressionMatcher(Restrictions.eq("nome", "Nakombi"))));
                will(returnValue(criteria));
                one(criteria).uniqueResult();
                will(returnValue(expectedResult));
            }
        });
        HibernateRepositoryBuilder recorder = HibernateRepositoryBuilder.newInstance(session);
        RestauranteRepository repository = recorder.getRepository(RestauranteRepository.class);
        Restaurante restaurante = repository.findByNome("Nakombi");
        Assert.assertEquals(expectedResult, restaurante);
        mockery.assertIsSatisfied();
    }
}
