package be.normegil.librarium.model.data.video;

import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.model.data.fake.FakeVideo;
import be.normegil.librarium.model.data.people.Responsible;
import be.normegil.librarium.model.data.people.Role;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.time.Duration;
import java.util.Collection;

public class UTVideoBuilderSafety {

	private static final ClassWrapper<FakeVideo.Builder> CLASS = new ClassWrapper<>(FakeVideo.Builder.class);
	private FakeVideo.Builder entity;

	@Before
	public void setUp() throws Exception {
		entity = FakeVideo.builder();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test(expected = ConstraintViolationException.class)
	public void testFrom_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("from", Video.class), new Object[]{null});
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
	public void testAddAllDirectors_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllDirectors", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddDirector_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addDirector", Responsible.class), new Object[]{null});
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
	public void testAddAllScenarists_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllScenarists", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddScenarist_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addScenarist", Responsible.class), new Object[]{null});
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
	public void testAddAllActors_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addAllActors", Collection.class), new Object[]{null});
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddActor_Null() throws Exception {
		Validator.validate(entity, CLASS.getMethod("addActor", Role.class), new Object[]{null});
	}
}