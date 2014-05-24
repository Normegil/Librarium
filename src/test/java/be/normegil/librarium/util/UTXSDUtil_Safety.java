package be.normegil.librarium.util;

import org.junit.Before;
import org.junit.Test;

public class UTXSDUtil_Safety {

    private XSDUtil xsdUtil;

    @Before
    public void setUp() throws Exception {
        xsdUtil = new XSDUtil();
    }

    @Test(expected = NullPointerException.class)
    public void testNullParameter() throws Exception {
        xsdUtil.getSchema(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testClassWithoutAnnotation() throws Exception {
        xsdUtil.getSchema(this.getClass());
    }
}
