package be.normegil.librarium.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTCollectionComparatorSafety.class,
		UTCollectionComparator.class,
		UTMapComparator.class
})
public class CollectionComparatorTestSuite {
}