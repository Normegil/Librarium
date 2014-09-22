package be.normegil.librarium.libraries;

import be.normegil.librarium.Constants;
import be.normegil.librarium.util.ClassHelper;
import be.normegil.librarium.validation.constraint.NotEmpty;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collection;

public class ClassWrapper<E> implements Comparable<ClassWrapper<E>> {

	private java.lang.Class<E> entityClass;

	public ClassWrapper(java.lang.Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	public Constructor<E> getConstructor(java.lang.Class<?>... parameterClasses) {
		try {
			return entityClass.getConstructor(parameterClasses);
		} catch (NoSuchMethodException e) {
			try {
				return entityClass.getDeclaredConstructor(parameterClasses);
			} catch (NoSuchMethodException e1) {
				throw new be.normegil.librarium.util.exception.NoSuchMethodException(e1);
			}
		}
	}

	public Method getMethod(@NotEmpty String methodName, java.lang.Class<?>... parameterClasses) {
		try {
			return entityClass.getMethod(methodName, parameterClasses);
		} catch (NoSuchMethodException e) {
			try {
				return entityClass.getDeclaredMethod(methodName, parameterClasses);
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
		return new ClassHelper().getAllFields(entityClass);
	}

	public String getSimpleName() {
		return entityClass.getSimpleName();
	}

	public String getCanonicalName() {
		return entityClass.getCanonicalName();
	}

	@Override
	public int compareTo(final ClassWrapper<E> o) {
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
