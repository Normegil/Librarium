package be.normegil.librarium;

import org.junit.Before;
import org.junit.Test;

public class AssertHelperTestAssertDifferentInstance {

    private Object firstObject;
    private Object secondObject;

    @Before
    public void setUp() throws Exception {
        firstObject = new Long(0L);
        secondObject = new Long(0L);
    }

    @Test(expected = AssertionError.class)
    public void testAssertDifferentInstance_SameObject() throws Exception {
        AssertHelper.assertDifferentInstance(firstObject, firstObject);
    }

    @Test(expected = AssertionError.class)
    public void testAssertDifferentInstance_SameString() throws Exception {
        AssertHelper.assertDifferentInstance("", "");
    }

    @Test(expected = AssertionError.class)
    public void testAssertDifferentInstance_SameValuePrimitiveInteger() throws Exception {
        int i = 0;
        AssertHelper.assertDifferentInstance(i, i);
    }

    @Test
    public void testAssertDifferentInstance_DifferentObject() throws Exception {
        AssertHelper.assertDifferentInstance(firstObject, secondObject);
    }
}
