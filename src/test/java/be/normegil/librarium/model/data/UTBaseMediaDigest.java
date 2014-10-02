package be.normegil.librarium.model.data;

import be.normegil.librarium.Constants;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.data.fake.FakeBaseMedia;
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
public class UTBaseMediaDigest {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<BaseMedia> BASE_MEDIA_FACTORY = FactoryRepository.get(BaseMedia.class);
	private static final String REST_URI = "http://localhost:8080/rest";

	@Mock
	private RESTHelper helper;

	private BaseMedia.BaseMediaDigest entity;

	@Before
	public void setUp() throws Exception {
		entity = new BaseMedia.BaseMediaDigest();
	}

	@After
	public void tearDown() throws Exception {
		entity = null;
	}

	@Test
	public void testFromBase_Title() throws Exception {
		BaseMedia originalBaseMedia = callFromBase();
		assertEquals(originalBaseMedia.getTitle(), entity.title);
	}

	@Test
	public void testFromBase_Description() throws Exception {
		BaseMedia originalBaseMedia = callFromBase();
		assertEquals(originalBaseMedia.getDescription(), entity.description);
	}

	@Test
	public void testFromBase_Tags() throws Exception {
		BaseMedia originalBaseMedia = callFromBase();
		assertEquals(originalBaseMedia.getTags(), entity.tags);
	}

	@Test
	public void testFromBase_OfficialWebsite() throws Exception {
		BaseMedia originalBaseMedia = callFromBase();
		assertEquals(originalBaseMedia.getOfficialWebsite(), new URL(entity.officialWebsite));
	}

	@Test
	public void testFromBase_WikipediaPage() throws Exception {
		BaseMedia originalBaseMedia = callFromBase();
		assertEquals(originalBaseMedia.getWikipediaPage(), new URL(entity.wikipediaPage));
	}

	@Test
	public void testFromBase_Stores() throws Exception {
		BaseMedia originalBaseMedia = callFromBase();
		Collection<URL> toTest = new ArrayList<>();
		for (URI store : entity.stores) {
			toTest.add(new URL(store));
		}
		assertEquals(originalBaseMedia.getStores(), toTest);
	}

	@Test
	public void testFromBase_DownloadLinks() throws Exception {
		BaseMedia originalBaseMedia = callFromBase();
		Collection<URI> expected = new ArrayList<>();
		for (DownloadLink downloadLink : originalBaseMedia.getDownloadLinks()) {
			expected.add(new RESTHelper().getRESTUri(URI.create(REST_URI), DownloadLink.class, downloadLink));
		}
		assertEquals(expected, entity.downloadLinks);
	}

	private BaseMedia callFromBase() {
		URI baseUri = URI.create(REST_URI);
		BaseMedia baseMedia = BASE_MEDIA_FACTORY.getNext();
		entity.fromBase(baseUri, baseMedia);
		URI expected = URI.create(REST_URI + Constants.URL.PATH_SEPARATOR + baseMedia.getId());
		when(helper.getRESTUri(baseUri, FakeBaseMedia.class, baseMedia))
				.thenReturn(expected);
		return baseMedia;
	}

	@Test
	public void testToBase_Title() throws Exception {
		BaseMedia baseMedia = callToBase();
		assertEquals(entity.title, baseMedia.getTitle());
	}

	@Test
	public void testToBase_Description() throws Exception {
		BaseMedia baseMedia = callToBase();
		assertEquals(entity.description, baseMedia.getDescription());
	}

	@Test
	public void testToBase_Tags() throws Exception {
		BaseMedia baseMedia = callToBase();
		assertEquals(entity.tags, baseMedia.getTags());
	}

	@Test
	public void testToBase_OfficialWebsite() throws Exception {
		BaseMedia baseMedia = callToBase();
		assertEquals(new URL(entity.title), baseMedia.getOfficialWebsite());
	}

	@Test
	public void testToBase_WikipediaPage() throws Exception {
		BaseMedia baseMedia = callToBase();
		assertEquals(new URL(entity.wikipediaPage), baseMedia.getWikipediaPage());
	}

	@Test
	public void testToBase_Store() throws Exception {
		BaseMedia baseMedia = callToBase();
		Collection<URL> expected = new ArrayList<>();
		for (URI store : entity.stores) {
			expected.add(new URL(store));
		}
		assertEquals(expected, baseMedia.getStores());
	}

	@Test
	public void testToBase_DownloadLinks() throws Exception {
		BaseMedia baseMedia = callToBase();
		Collection<URI> toTest = new ArrayList<>();
		for (DownloadLink downloadLink : baseMedia.getDownloadLinks()) {
			toTest.add(new RESTHelper().getRESTUri(URI.create(REST_URI), DownloadLink.class, downloadLink));
		}
		assertEquals(entity.downloadLinks, toTest);
	}

	public BaseMedia callToBase() throws Exception {
		FakeBaseMedia.Builder builder = FakeBaseMedia.builder();
		BaseMedia baseMedia = BASE_MEDIA_FACTORY.getNext();
		entity.fromBase(URI.create(REST_URI), baseMedia);
		entity.toBase(builder);
		return builder.build();
	}
}
