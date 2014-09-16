package be.normegil.librarium.util;

import be.normegil.librarium.libraries.FieldWrapper;
import be.normegil.librarium.model.data.BaseMedia;
import be.normegil.librarium.model.data.Entity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UTClassHelper {

	private ClassHelper entity;

	@Before
	public void setUp() throws Exception {
		entity = new ClassHelper();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testGetAllFields() throws Exception {
		Class<Entity> aClass = Entity.class;

		List<Field> expected = new ArrayList<>();
		expected.addAll(Arrays.asList(aClass.getFields()));
		expected.addAll(Arrays.asList(aClass.getDeclaredFields()));

		Collection<FieldWrapper> allFields = entity.getAllFields(aClass);
		List<Field> toTest = new ArrayList<>();
		for (FieldWrapper field : allFields) {
			toTest.add(field.getOriginalField());
		}
		assertEquals(expected, toTest);
	}

	@Test
	public void testGetAllFieldsIncludingSuperClass() throws Exception {
		Class<BaseMedia> aClass = BaseMedia.class;

		List<Field> expected = new ArrayList<>();
		expected.addAll(Arrays.asList(aClass.getFields()));
		expected.addAll(Arrays.asList(aClass.getDeclaredFields()));

		expected.addAll(Arrays.asList(aClass.getSuperclass().getFields()));
		expected.addAll(Arrays.asList(aClass.getSuperclass().getDeclaredFields()));

		Collection<FieldWrapper> allFields = entity.getAllFields(aClass);
		List<Field> foundFieldsList = new ArrayList<>();
		for (FieldWrapper field : allFields) {
			foundFieldsList.add(field.getOriginalField());
		}
		assertEquals(expected, foundFieldsList);
	}
}
