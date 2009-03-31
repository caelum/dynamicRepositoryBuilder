package org.repositorybuilder.builder.hibernate;

import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * @author leonardobessa
 * 
 */
public class CriteriaBuilder {

    private Session session;

    public CriteriaBuilder(Session session) {
        this.session = session;
    }

    public Criteria getCriteria(Map<String, Object> map, Class<?> type) {
        Criteria criteria = session.createCriteria(type);
        Set<String> attributes = map.keySet();
        for (String propertyName : attributes) {
            criteria.add(Restrictions.eq(propertyName, map.get(propertyName)));
        }
        return criteria;
    }

}
