package be.normegil.librarium.tool.comparator;

import be.normegil.librarium.Constants;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.lang.reflect.Field;
import java.util.Comparator;

public class FieldComparator implements Comparator<Field> {
	@Override
	public int compare(final Field o1, final Field o2) {
		int comparison = new CompareToBuilder()
				.append(o1.getModifiers(), o2.getModifiers())
				.append(o1.getType(), o2.getType())
				.append(o1.getName(), o2.getName())
				.toComparison();

		if (Constants.Comparator.EQUALS == comparison && !o1.equals(o2)) {
			return Constants.Comparator.PRIORITY_FIRST;
		}

		return comparison;
	}
}
