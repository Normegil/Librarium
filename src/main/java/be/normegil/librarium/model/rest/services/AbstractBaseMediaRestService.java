package be.normegil.librarium.model.rest.services;

import be.normegil.librarium.libraries.URL;
import be.normegil.librarium.model.data.BaseMedia;
import be.normegil.librarium.model.data.DownloadLink;

import java.util.Set;

public abstract class AbstractBaseMediaRestService<E extends BaseMedia> extends BasicRESTService<E> {

	@Override
	protected void update(final BaseMedia loadedEntity, final BaseMedia entity) {
		loadedEntity.setTitle(entity.getTitle());
		loadedEntity.setOfficialWebsite(entity.getOfficialWebsite());
		loadedEntity.setDescription(entity.getDescription());
		loadedEntity.setWikipediaPage(entity.getWikipediaPage());

		loadedEntity.clearTags();
		loadedEntity.addAllTags(entity.getTags());

		loadedEntity.clearStores();
		loadedEntity.addAllStores(entity.getStores());

		loadedEntity.clearDownloadLinks();
		loadedEntity.addAllDownloadLinks(entity.getDownloadLinks());
	}

	@Override
	protected void updateNullCheck(final BaseMedia loadedEntity, final BaseMedia entity) {
		String title = entity.getTitle();
		if (title != null) {
			loadedEntity.setTitle(title);
		}

		URL officialWebsite = entity.getOfficialWebsite();
		if (officialWebsite != null) {
			loadedEntity.setOfficialWebsite(officialWebsite);
		}

		String description = entity.getDescription();
		if (description != null) {
			loadedEntity.setDescription(description);
		}

		URL wikipediaPage = entity.getWikipediaPage();
		if (wikipediaPage != null) {
			loadedEntity.setWikipediaPage(wikipediaPage);
		}

		Set<String> tags = entity.getTags();
		if (tags != null) {
			loadedEntity.clearTags();
			loadedEntity.addAllTags(tags);
		}

		Set<URL> stores = entity.getStores();
		if (stores != null) {
			loadedEntity.clearStores();
			loadedEntity.addAllStores(stores);
		}

		Set<DownloadLink> downloadLinks = entity.getDownloadLinks();
		if (downloadLinks != null) {
			loadedEntity.clearDownloadLinks();
			loadedEntity.addAllDownloadLinks(downloadLinks);
		}
	}
}
