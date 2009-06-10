package org.repositorybuilder.builder.hibernate;

import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
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
        } else if (operation.usesConditions()) {
            Criterion criterion = this.getCriterionFrom(operation);
            criteria.add(criterion);
        }
        return criteria;
    }

    private Criterion getCriterionFrom(RepositoryOperation operation) {
        Criterion result = null;
        Object[] args = operation.getArguments();
        for (Object arg : args) {
            if (CriterionConditions.class.isAssignableFrom(arg.getClass())) {
                CriterionConditions cc = (CriterionConditions) arg;
                result = cc.getCriterion();
            }
        }
        if (result == null){
            throw new IllegalArgumentException("Missing conditions.");
        }
        return result;
    }

}
