package be.normegil.librarium.validation.constraint;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.Entity;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class UTExistingID {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Entity> ENTITY_FACTORY = FactoryRepository.get(Entity.class);
	private ExistingIDValidator entity = new ExistingIDValidator();

	@Mock
	private ConstraintValidatorContextImpl context;

	@Test
	public void testNull() throws Exception {
		assertFalse(entity.isValid(null, context));
	}

	@Test
	public void testNullID() throws Exception {
		Entity toTest = ENTITY_FACTORY.getNew(true, false);
		assertFalse(entity.isValid(toTest, context));
	}

	@Test
	public void testWithID() throws Exception {
		Entity toTest = ENTITY_FACTORY.getNew(true, true);
		assertTrue(entity.isValid(toTest, context));
	}
}
