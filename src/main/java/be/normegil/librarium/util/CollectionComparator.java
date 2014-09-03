package be.normegil.librarium.util;

import be.normegil.librarium.Constants;

import javax.validation.constraints.NotNull;
import java.util.*;

public class CollectionComparator implements Comparator<Collection> {

	private static final String ERROR_MESSAGE = "Elements of the collection should be another Collection, a Map or Comparable Elements";

	public int compareMaps(final Map map1, final Map map2) {
		int result;
		if (map1 != null && map2 != null) {
			result = map1.size() - map2.size();
			if (result == Constants.Comparator.EQUALS) {
				TreeMap treeMap1 = new TreeMap(map1);
				TreeMap treeMap2 = new TreeMap(map2);

				result = compare(treeMap1.keySet(), treeMap2.keySet());
				if (result == Constants.Comparator.EQUALS) {
					Set keys = new TreeSet(treeMap1.keySet());
					for (Object key : keys) {
						result = compareObjects(map1.get(key), map2.get(key));
						if (result != Constants.Comparator.EQUALS) {
							break;
						}
					}
				}
			}
		} else if (map1 == null && map2 == null) {
			result = Constants.Comparator.EQUALS;
		} else if (map1 == null) {
			result = Constants.Comparator.PRIORITY_FIRST;
		} else {
			result = Constants.Comparator.PRIORITY_SECOND;
		}
		return result;
	}

	@Override
	public int compare(@NotNull final Collection collection1, @NotNull final Collection collection2) {
		int result;
		if (collection1 != null && collection2 != null) {
			Set<Object> firstSet = new TreeSet<>();
			Set<Object> secondSet = new TreeSet<>();

			result = collection1.size() - collection2.size();
			if (result == Constants.Comparator.EQUALS) {
				firstSet.addAll(collection1);
				secondSet.addAll(collection2);

				Iterator firstIterator = firstSet.iterator();
				Iterator secondIterator = secondSet.iterator();
				result = compareCollectionElements(firstIterator, secondIterator);
			}
		} else if (collection1 == null && collection2 == null) {
			result = Constants.Comparator.EQUALS;
		} else if (collection1 == null) {
			result = Constants.Comparator.PRIORITY_FIRST;
		} else {
			result = Constants.Comparator.PRIORITY_SECOND;
		}

		return result;
	}

	private int compareCollectionElements(final Iterator firstIterator, final Iterator secondIterator) {
		int result = Constants.Comparator.EQUALS;
		while (firstIterator.hasNext() && secondIterator.hasNext() && result == Constants.Comparator.EQUALS) {
			Object o1 = firstIterator.next();
			Object o2 = secondIterator.next();

			result = compareObjects(o1, o2);
		}
		return result;
	}

	private int compareObjects(final Object o1, final Object o2) {
		int compareToResult;
		if (o1 instanceof Comparable && o2 instanceof Comparable) {
			Comparable comparable1 = (Comparable) o1;
			Comparable comparable2 = (Comparable) o2;
			compareToResult = comparable1.compareTo(comparable2);
		} else if (Collection.class.isAssignableFrom(o1.getClass()) && Collection.class.isAssignableFrom(o2.getClass())) {
			compareToResult = compare((Collection) o1, (Collection) o2);
		} else if (Map.class.isAssignableFrom(o1.getClass()) && Map.class.isAssignableFrom(o2.getClass())) {
			compareToResult = compareMaps((Map) o1, (Map) o2);
		} else {
			throw new IllegalArgumentException(ERROR_MESSAGE + " [ElementType=" + o1.getClass() + "]");
		}
		return compareToResult;
	}
}
