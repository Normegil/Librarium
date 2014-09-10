package be.normegil.librarium.util;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ClassHelper {

	public Collection<Field> getAllFields(@NotNull final Class aClass) {
		List<Field> fields = new ArrayList<>();
		fields.addAll(Arrays.asList(aClass.getDeclaredFields()));
		fields.addAll(Arrays.asList(aClass.getFields()));
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
