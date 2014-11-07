package be.normegil.librarium.model.rest.services;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.model.dao.DAO;
import be.normegil.librarium.model.data.Entity;
import be.normegil.librarium.model.data.game.Game;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.Test;
import org.mockito.Mock;
import org.slf4j.Logger;

import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ContextResolver;
import javax.xml.bind.Marshaller;

public class UTBasicRESTService {

	private static final DataFactory<Game> FACTORY = FactoryRepository.get(Game.class);
	private BasicRESTService entity = new BasicRESTService() {
		@Override
		protected void update(final Entity loadedEntity, final Entity entity) {
		}

		@Override
		protected void updateNullCheck(final Entity loadedEntity, final Entity entity) {

		}

		@Override
		public DAO getDao() {
			return null;
		}

		@Override
		public void setDAO(final DAO dao) {

		}

		@Override
		public Logger getLogger() {
			return null;
		}

		@Override
		public void setLogger(final Logger log) {

		}

		@Override
		public ContextResolver<Marshaller> getContextResolver() {
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

	@Mock
	private UriInfo uriInfo;

	@Test(expected = UnsupportedOperationException.class)
	public void testGetAll_NegativeOffset() throws Exception {
		entity.getAll(uriInfo, -1l, ApplicationProperties.REST.DEFAULT_LIMIT);
	}

	@Test
	public void testGetAll_NegativeLimit() throws Exception {
		throw new UnsupportedOperationException();
	}

	@Test
	public void testGetAll_ZeroLimit() throws Exception {
		throw new UnsupportedOperationException();
	}

	@Test
	public void testGetAll_LimitHigherThanMaximum() throws Exception {
		throw new UnsupportedOperationException();
	}

	@Test
	public void testGetAll_NoOffset() throws Exception {
		throw new UnsupportedOperationException();
	}

	@Test
	public void testGetAll_NoLimit() throws Exception {
		throw new UnsupportedOperationException();
	}

	@Test
	public void testGetAll_WithOffsetAndLimit() throws Exception {
		throw new UnsupportedOperationException();
	}

	@Test
	public void testGetAll_OffsetHigherThanNumberOfElements() throws Exception {
		throw new UnsupportedOperationException();
	}

	@Test
	public void testGetAll_LimitHigherThanNumberOfElements() throws Exception {
		throw new UnsupportedOperationException();
	}

	@Test
	public void testGet_InexistingUUID() throws Exception {
		throw new UnsupportedOperationException();
	}

	@Test
	public void testGet() throws Exception {
		throw new UnsupportedOperationException();
	}

	@Test
	public void testCreate() throws Exception {
		throw new UnsupportedOperationException();
	}

	@Test
	public void testUpdateByPUT() throws Exception {
		throw new UnsupportedOperationException();
	}

	@Test
	public void testUpdateByPUT_Inexisting() throws Exception {
		throw new UnsupportedOperationException();
	}

	@Test
	public void testUpdateByPUT_NullObjectID() throws Exception {
		throw new UnsupportedOperationException();
	}

	@Test
	public void testUpdateByPOST_Inexisting() throws Exception {
		throw new UnsupportedOperationException();
	}

	@Test
	public void testUpdateByPOST_PartialObject() throws Exception {
		throw new UnsupportedOperationException();
	}

	@Test
	public void testUpdateByPOST_NullObjectID() throws Exception {
		throw new UnsupportedOperationException();
	}

	@Test
	public void testDelete_Inexisting() throws Exception {
		throw new UnsupportedOperationException();
	}

	@Test
	public void testDelete() throws Exception {
		throw new UnsupportedOperationException();
	}
}
