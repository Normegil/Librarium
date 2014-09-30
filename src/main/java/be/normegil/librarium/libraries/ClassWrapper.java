package be.normegil.librarium.libraries;

import be.normegil.librarium.Constants;
import be.normegil.librarium.util.ClassHelper;
import be.normegil.librarium.util.exception.InterfaceNotFoundException;
import be.normegil.librarium.util.exception.NoSuchFieldRuntimeException;
import be.normegil.librarium.util.exception.NoSuchMethodRuntimeException;
import be.normegil.librarium.validation.constraint.NotEmpty;
import org.apache.commons.lang3.builder.CompareToBuilder;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
				throw new NoSuchMethodRuntimeException(e1);
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
				throw new NoSuchMethodRuntimeException(e1);
			}
		}
	}

	public FieldWrapper getField(@NotEmpty String fieldName) {
		for (FieldWrapper field : getAllFields()) {
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}
		throw new NoSuchFieldRuntimeException("Field not found [Name=" + fieldName + "]");
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

	public Collection<Class<?>> getInterfaces() {
		Class<?>[] interfacesTable = entityClass.getInterfaces();
		Set<Class<?>> interfaces = new HashSet<>();
		for (Class<?> anInterface : interfacesTable) {
			interfaces.add(anInterface);
		}
		return interfaces;
	}

	public Class<?> getInterface(@NotNull Class<?> aClass) {
		Collection<Class<?>> interfaces = getInterfaces();
		for (Class<?> anInterface : interfaces) {
			if (aClass.isAssignableFrom(anInterface)) {
				return anInterface;
			}
		}
		throw new InterfaceNotFoundException("Interface not found for " + aClass);
	}

	public Set<Class> getClassParameters() {

//		ParameterizedType genericSuperclass = (ParameterizedType) entityClass.getGenericSuperclass();
//		entityClass = (Class<Entity>) genericSuperclass.getActualTypeArguments()[0];

		TypeVariable<Class<E>>[] typeParameters = entityClass.getTypeParameters();
		Set<Class> parameters = new HashSet<>();
		for (TypeVariable<Class<E>> typeParameter : typeParameters) {
			parameters.add(typeParameter.getClass());
		}
		return parameters;
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
