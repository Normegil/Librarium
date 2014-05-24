package be.normegil.librarium;

import org.apache.commons.lang3.Validate;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class AssertHelper {

    public static void assertCollectionsEquals(Collection firstCollection, Collection secondCollection) {
        if (firstCollection != null || secondCollection != null) {
            Validate.notNull(firstCollection);
            Validate.notNull(secondCollection);


            assertEquals(firstCollection.size(), secondCollection.size());

            for (Object o : firstCollection) {
                boolean foundObject = false;
                for (Object o2 : secondCollection) {
                    if (o2.equals(o)) {
                        foundObject = true;
                    }
                }
                if (!foundObject) {
                    throw new AssertionError("Collections not equals (Element not found : " + o + ") :\n" +
                            "\tCollection 1 = " + firstCollection.toString() +
                            "\tCollection 2 = " + secondCollection.toString());
                }
            }
        }
    }

}
