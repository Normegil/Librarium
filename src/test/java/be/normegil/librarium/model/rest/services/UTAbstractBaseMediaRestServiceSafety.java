package be.normegil.librarium.model.rest.services;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.ClassWrapper;
import be.normegil.librarium.model.dao.DAO;
import be.normegil.librarium.model.data.BaseMedia;
import be.normegil.librarium.model.data.Entity;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import be.normegil.librarium.tool.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

import javax.ws.rs.ext.ContextResolver;
import javax.xml.bind.Marshaller;

public class UTAbstractBaseMediaRestServiceSafety {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BaseMedia> BASE_MEDIA_FACTORY = FactoryRepository.get(BaseMedia.class);
	private static final ClassWrapper<AbstractBaseMediaRestService> CLASS = new ClassWrapper<>(AbstractBaseMediaRestService.class);
	private static final java.lang.reflect.Method UPDATE_NULL_CHECK_METHOD = CLASS.getMethod("updateNullCheck", BaseMedia.class, BaseMedia.class);
	private static final java.lang.reflect.Method UPDATE_METHOD = CLASS.getMethod("update", BaseMedia.class, BaseMedia.class);

	private AbstractBaseMediaRestService entity;

	@Before
	public void setUp() throws Exception {
		entity = new AbstractBaseMediaRestService() {
			@Override
			protected void update(final Entity loadedEntity, final Entity entity) {

			}

			@Override
			protected void updateNullCheck(final Entity loadedEntity, final Entity entity) {

			}

			@Override
			protected DAO getDao() {
				return null;
			}

			@Override
			public void setDAO(final DAO dao) {

			}

			@Override
			protected Logger getLogger() {
				return null;
			}

			@Override
			public void setLogger(final Logger log) {

			}

			@Override
			protected ContextResolver<Marshaller> getContextResolver() {
				return null;
			}

			@Override
			public void setContextResolver(final ContextResolver contextResolver) {

			}

			@Override
			public Class getSupportedClass() {
				return null;
			}
		};
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testUpdate_NullToUpdate() throws Exception {
		Validator.validate(entity, UPDATE_METHOD, null, BASE_MEDIA_FACTORY.getNew());
	}

	@Test
	public void testUpdate_NullGivenEntity() throws Exception {
		Validator.validate(entity, UPDATE_METHOD, BASE_MEDIA_FACTORY.getNew(), null);
	}

	@Test
	public void testUpdateNullCheck_NullToUpdate() throws Exception {
		Validator.validate(entity, UPDATE_NULL_CHECK_METHOD, BASE_MEDIA_FACTORY.getNew(), null);
	}

	@Test
	public void testUpdateNullCheck_NullGivenEntity() throws Exception {
		Validator.validate(entity, UPDATE_NULL_CHECK_METHOD, null, BASE_MEDIA_FACTORY.getNew());
	}
}
