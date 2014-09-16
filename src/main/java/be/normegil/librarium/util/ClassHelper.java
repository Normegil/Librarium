package be.normegil.librarium.util;

import be.normegil.librarium.libraries.FieldWrapper;

import javax.validation.constraints.NotNull;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ClassHelper {

	public Collection<FieldWrapper> getAllFields(@NotNull final Class aClass) {
		List<FieldWrapper> fields = new ArrayList<>();
		java.lang.reflect.Field[] declaredFields = aClass.getDeclaredFields();
		java.lang.reflect.Field[] classFields = aClass.getFields();

		for (java.lang.reflect.Field field : declaredFields) {
			fields.add(new FieldWrapper(field));
		}
		for (java.lang.reflect.Field field : classFields) {
			fields.add(new FieldWrapper(field));
		}

		Class superclass = aClass.getSuperclass();
		if (superclass != null) {
			fields.addAll(getAllFields(superclass));
		}
		return fields;
	}

	public List<Type> getClassParameters(@NotNull final Class aClass) {
		ParameterizedType genericSuperclass = (ParameterizedType) aClass.getGenericSuperclass();
		Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
		return Arrays.asList(actualTypeArguments);
	}
}
