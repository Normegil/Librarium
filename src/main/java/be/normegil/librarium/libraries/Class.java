package be.normegil.librarium.libraries;

import be.normegil.librarium.Constants;
import be.normegil.librarium.util.ClassHelper;
import be.normegil.librarium.validation.constraint.NotEmpty;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collection;

public class Class<E> implements Comparable<Class<E>> {

	private java.lang.Class<E> aClass;

	public Class(java.lang.Class<E> aClass) {
		this.aClass = aClass;
	}

	public Constructor<E> getConstructor(java.lang.Class<?>... parameterClasses) {
		try {
			return aClass.getConstructor(parameterClasses);
		} catch (NoSuchMethodException e) {
			try {
				return aClass.getDeclaredConstructor(parameterClasses);
			} catch (NoSuchMethodException e1) {
				throw new be.normegil.librarium.util.exception.NoSuchMethodException(e1);
			}
		}
	}

	public Method getMethod(@NotEmpty String methodName, java.lang.Class<?>... parameterClasses) {
		try {
			return aClass.getMethod(methodName, parameterClasses);
		} catch (NoSuchMethodException e) {
			try {
				return aClass.getDeclaredMethod(methodName, parameterClasses);
			} catch (NoSuchMethodException e1) {
				throw new be.normegil.librarium.util.exception.NoSuchMethodException(e1);
			}
		}
	}

	public FieldWrapper getField(@NotEmpty String fieldName) {
		for (FieldWrapper field : getAllFields()) {
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}
		throw new be.normegil.librarium.util.exception.NoSuchFieldException("Field not found [Name=" + fieldName + "]");
	}

	public Collection<FieldWrapper> getAllFields() {
		return new ClassHelper().getAllFields(aClass);
	}

	public String getSimpleName() {
		return aClass.getSimpleName();
	}

	public String getCanonicalName() {
		return aClass.getCanonicalName();
	}

	@Override
	public int compareTo(final Class<E> o) {
		if (o != null) {
			return new CompareToBuilder()
					.append(getSimpleName(), o.getSimpleName())
					.append(getCanonicalName(), o.getCanonicalName())
					.toComparison();
		} else {
			return Constants.Comparator.PRIORITY_SECOND;
		}
	}
}
