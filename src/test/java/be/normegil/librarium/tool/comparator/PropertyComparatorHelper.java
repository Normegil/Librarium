package be.normegil.librarium.tool.comparator;

import be.normegil.librarium.Constants;

public class PropertyComparatorHelper {

	public boolean testComparatorResult(int expectedResult, int comparatorResult) {
		if (expectedResult == Constants.Comparator.EQUALS) {
			return expectedResult == comparatorResult;
		} else if (expectedResult == Constants.Comparator.PRIORITY_FIRST) {
			return comparatorResult <= Constants.Comparator.PRIORITY_FIRST;
		} else if (expectedResult == Constants.Comparator.PRIORITY_SECOND) {
			return comparatorResult >= Constants.Comparator.PRIORITY_SECOND;
		}
		return false;
	}
}
