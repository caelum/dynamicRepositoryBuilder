package org.repositorybuilder.builder.hibernate;

import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.repositorybuilder.operation.RepositoryOperation;

/**
 * @author leonardobessa
 * 
 */
public class CriteriaBuilder {

    private Session session;

    public CriteriaBuilder(Session session) {
        this.session = session;
    }

    public Criteria getCriteria(RepositoryOperation operation) {
        Criteria criteria = session.createCriteria(operation.getResultType());
        if (operation.usesBy()) {
            Map<String, Object> map = operation.getFindByMap();
            for (String key : map.keySet()) {
                criteria.add(Restrictions.eq(key, map.get(key)));
            }
        }
        return criteria;
    }

}
