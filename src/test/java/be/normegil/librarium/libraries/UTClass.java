package be.normegil.librarium.libraries;

import be.normegil.librarium.model.data.Entity;
import be.normegil.librarium.model.data.Media;
import be.normegil.librarium.model.data.Universe;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.model.data.people.Responsible;
import be.normegil.librarium.model.data.people.StaffMember;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UTClass {

	private static final java.lang.Class<Media> BASE_CLASS = Media.class;
	private ClassWrapper<Media> entity;

	@Before
	public void setUp() throws Exception {
		entity = new ClassWrapper<>(BASE_CLASS);
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testGetPrivateConstructor() throws Exception {
		Constructor<Media> expected = BASE_CLASS.getDeclaredConstructor();
		Constructor<Media> toTest = entity.getConstructor();
		assertEquals(expected, toTest);
	}

	@Test
	public void testGetPublicConstructor() throws Exception {
		java.lang.Class<Game> aClass = Game.class;
		java.lang.Class[] parameters = new java.lang.Class[]{aClass};
		Constructor<Game> expected = aClass.getConstructor(parameters);
		Constructor<Game> toTest = new ClassWrapper<>(aClass).getConstructor(parameters);
		assertEquals(expected, toTest);
	}

	@Test
	public void testGetMethod() throws Exception {
		String methodName = "addUniverse";
		java.lang.Class[] parameters = new java.lang.Class[]{Universe.class};
		Method expected = BASE_CLASS.getMethod(methodName, parameters);
		Method toTest = entity.getMethod(methodName, parameters);
		assertEquals(expected, toTest);
	}

	@Test
	public void testGetSuperclassMethod() throws Exception {
		String methodName = "getId";
		Method expected = BASE_CLASS.getMethod(methodName);
		Method toTest = entity.getMethod(methodName);
		assertEquals(expected, toTest);
	}

	@Test
	public void testGetProtectedMethod() throws Exception {
		String methodName = "isResponsible";
		java.lang.Class[] parameters = new java.lang.Class[]{Responsible.class, StaffMember.class};
		Method expected = BASE_CLASS.getDeclaredMethod(methodName, parameters);
		Method toTest = entity.getMethod(methodName, parameters);
		assertEquals(expected, toTest);
	}

	@Test
	public void testGetPrivateField() throws Exception {
		String fieldName = "universes";
		Field expected = BASE_CLASS.getDeclaredField(fieldName);
		FieldWrapper toTest = entity.getField(fieldName);
		assertEquals(expected, toTest.getOriginalField());
	}

	@Test
	public void testGetSuperclassField() throws Exception {
		String fieldName = "id";
		Field expected = Entity.class.getDeclaredField(fieldName);
		FieldWrapper toTest = entity.getField(fieldName);
		assertEquals(expected, toTest.getOriginalField());
	}

	@Test
	public void testGetAllFields() throws Exception {
		List<Field> expected = getAllFields(BASE_CLASS);
		Collection<FieldWrapper> allFields = entity.getAllFields();
		List<Field> toTest = new ArrayList<>();
		for (FieldWrapper field : allFields) {
			toTest.add(field.getOriginalField());
		}
		assertEquals(expected, toTest);
	}

	@Test
	public void testGetSimpleName() throws Exception {
		assertEquals(BASE_CLASS.getSimpleName(), entity.getSimpleName());
	}

	@Test
	public void testGetCanonicalName() throws Exception {
		assertEquals(BASE_CLASS.getCanonicalName(), entity.getCanonicalName());
	}

	private List<Field> getAllFields(java.lang.Class aClass) {
		List<Field> fields = new ArrayList<>();
		fields.addAll(Arrays.asList(aClass.getDeclaredFields()));
		fields.addAll(Arrays.asList(aClass.getFields()));
		java.lang.Class superclass = aClass.getSuperclass();
		if (superclass != null) {
			fields.addAll(getAllFields(superclass));
		}
		return fields;
	}
}
