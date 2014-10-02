package be.normegil.librarium.model.data;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.fake.FakeMedia;
import be.normegil.librarium.model.rest.RESTHelper;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UTMediaDigest {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> MEDIA_FACTORY = FactoryRepository.get(Media.class);
	private static final String REST_URI = "http://localhost:8080/rest";

	@Mock
	private RESTHelper helper;

	private Media.MediaDigest entity;

	@Before
	public void setUp() throws Exception {
		entity = new Media.MediaDigest();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFromBase_Universes() throws Exception {
		Media media = callFromBase();
		Collection<URI> expected = new ArrayList<>();
		for (Universe universe : media.getUniverses()) {
			expected.add(new RESTHelper().getRESTUri(URI.create(REST_URI), Universe.class, universe));
		}
		assertEquals(expected, entity.universes);
	}

	@Test
	public void testFromBase_ReleaseDate() throws Exception {
		Media media = callFromBase();
		Collection<URI> expected = new ArrayList<>();
		for (ReleaseDate date : media.releaseDates) {
			expected.add(new RESTHelper().getRESTUri(URI.create(REST_URI), ReleaseDate.class, date));
		}
		assertEquals(expected, entity.downloadLinks);
	}

	private Media callFromBase() {
		URI baseUri = URI.create(REST_URI);
		Media media = MEDIA_FACTORY.getNext();
		entity.fromBase(baseUri, media);
		URI expected = URI.create(REST_URI + Constants.URL.PATH_SEPARATOR + media.getId());
		when(helper.getRESTUri(baseUri, Media.class, media))
				.thenReturn(expected);
		return media;
	}

	@Test
	public void testToBase_Universes() throws Exception {
		Media media = callToBase();
		Collection<URI> toTest = new ArrayList<>();
		for (Universe universe : media.getUniverses()) {
			toTest.add(new RESTHelper().getRESTUri(URI.create(REST_URI), Universe.class, universe));
		}
		assertEquals(entity.universes, toTest);
	}

	@Test
	public void testToBase_ReleaseDate() throws Exception {
		Media media = callToBase();
		Collection<URI> toTest = new ArrayList<>();
		for (ReleaseDate date : media.releaseDates) {
			toTest.add(new RESTHelper().getRESTUri(URI.create(REST_URI), ReleaseDate.class, date));
		}
		assertEquals(entity.releaseDates, toTest);
	}

	public Media callToBase() throws Exception {
		FakeMedia.Builder builder = FakeMedia.builder();
		Media media = MEDIA_FACTORY.getNext();
		entity.fromBase(URI.create(REST_URI), media);
		entity.toBase(builder);
		return builder.build();
	}
}
