package br.com.caelum.recorder.hibernate;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.SimpleExpression;

/**
 * @author leonardobessa
 * 
 */
public class SimpleExpressionMatcher extends BaseMatcher<SimpleExpression> {

    private final Criterion object;

    public SimpleExpressionMatcher(Criterion equalArg) {
        object = equalArg;
    }

    public boolean matches(Object arg) {
        return areEqual(object, arg);
    }

    public void describeTo(Description description) {
        description.appendValue(object);
    }

    private static boolean areEqual(Object o1, Object o2) {
        if (o1 == null || o2 == null) {
            return false;
        }
        if (o1.equals(o2)) {
            return true;
        } else {
            return o1.toString().equals(o2.toString());
        }
    }

}
