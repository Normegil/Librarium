package be.normegil.librarium.model.data.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.model.data.people.Person;
import be.normegil.librarium.model.data.people.Responsible;
import be.normegil.librarium.model.data.people.Role;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.time.Duration;
import java.util.Collection;

public class UTVideoSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Video> FACTORY = FactoryRepository.get(Video.class);
	private static final ClassWrapper<Video> CLASS = new ClassWrapper<>(Video.class);
	private Video entity;

	@Before
	public void setUp() throws Exception {
		entity = FACTORY.getDefault();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testBuilderConstructor_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(Video.Init.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testCopyConstructor_Null() throws Exception {
		Validator.validate(CLASS.getConstructor(Video.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetDuration_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setDuration", Duration.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testSetDuration_Negative() throws Exception {
		Validator.validate(entity, CLASS.getMethod("setDuration", Duration.class), Duration.ofMinutes(-1));
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllProducers_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllProducers", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddProducer_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addProducer", Responsible.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveAllProducers_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeAllProducers", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveProducer_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeProducer", Responsible.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllDirectors_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllDirectors", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddDirector_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addDirector", Responsible.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveAllDirectors_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeAllDirectors", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveDirector_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeDirector", Responsible.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllComposers_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllComposers", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddComposer_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addComposer", Responsible.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveAllComposers_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeAllComposers", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveComposer_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeComposer", Responsible.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllScenarists_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllScenarists", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddScenarist_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addScenarist", Responsible.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveAllScenarists_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeAllScenarists", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveScenarist_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeScenarist", Responsible.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllOtherStaffMembers_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllOtherStaffMembers", Collection.class), new Object[]{null});
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void testAddOtherStaffMember_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addOtherStaffMember", Responsible.class), new Object[]{null});
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void testRemoveAllOtherStaffMembers_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeAllOtherStaffMembers", Collection.class), new Object[]{null});
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void testRemoveOtherStaffMember_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeOtherStaffMember", Responsible.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testGetActorsForRole_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("getActorsForRole", Person.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testGetRoleForActor_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("getRoleForActor", Person.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAllActors_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllActors", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddActor_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addActor", Role.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveAllActors_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeAllActors", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveActor_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeActor", Person.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testRemoveRole_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("removeRole", Person.class), new Object[]{null});
	}
}