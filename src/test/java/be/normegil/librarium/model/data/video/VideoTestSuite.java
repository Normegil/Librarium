package be.normegil.librarium.model.data.video;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.Media;
import be.normegil.librarium.model.data.fake.FakeVideo;
import be.normegil.librarium.model.data.people.Responsible;
import be.normegil.librarium.model.data.people.Role;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.time.Duration;
import java.util.UUID;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTVideoSafety.class,
		UTVideo.class,
		UTVideoEquality.class,
		UTVideoComparator.class,
		UTVideoBuilderSafety.class,
		UTVideoBuilder.class,
})
public class VideoTestSuite implements DataFactory<Video> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> MEDIA_FACTORY = FactoryRepository.get(Media.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Role> ROLE_FACTORY = FactoryRepository.get(Role.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Responsible> RESPONSIBLE_FACTORY = FactoryRepository.get(Responsible.class);
	private static final UUID DEFAULT_ID = UUID.fromString("");
	private static long index = 0L;

	@Override
	public Video getDefault() {
		return getDefault(true, false);
	}

	@Override
	public Video getNew() {
		return getNew(true, false);
	}

	@Override
	public Video getDefault(final boolean withLink, final boolean withIds) {
		FakeVideo.Builder builder = FakeVideo.builder()
				.from(MEDIA_FACTORY.getDefault(withLink, withIds))
				.setDuration(Duration.ofMinutes(90));
		if (withLink) {
			builder.addActor(ROLE_FACTORY.getDefault(false, withIds))
					.addProducer(RESPONSIBLE_FACTORY.getDefault(false, withIds))
					.addDirector(RESPONSIBLE_FACTORY.getDefault(false, withIds))
					.addComposer(RESPONSIBLE_FACTORY.getDefault(false, withIds))
					.addScenarist(RESPONSIBLE_FACTORY.getDefault(false, withIds))
					.addOtherStaffMember(RESPONSIBLE_FACTORY.getDefault(false, withIds));
		}

		Video video = builder.build();
		if (withIds) {
			new EntityHelper().setId(video, DEFAULT_ID);
		}
		return video;
	}

	@Override
	public Video getNew(final boolean withLink, final boolean withIds) {
		FakeVideo.Builder builder = FakeVideo.builder()
				.from(MEDIA_FACTORY.getNew(withLink, withIds))
				.setDuration(Duration.ofMinutes(index));

		if (withLink) {
			builder.addActor(ROLE_FACTORY.getNew(false, withIds))
					.addProducer(RESPONSIBLE_FACTORY.getNew(false, withIds))
					.addProducer(RESPONSIBLE_FACTORY.getNew(false, withIds))
					.addDirector(RESPONSIBLE_FACTORY.getNew(false, withIds))
					.addDirector(RESPONSIBLE_FACTORY.getNew(false, withIds))
					.addComposer(RESPONSIBLE_FACTORY.getNew(false, withIds))
					.addComposer(RESPONSIBLE_FACTORY.getNew(false, withIds))
					.addScenarist(RESPONSIBLE_FACTORY.getNew(false, withIds))
					.addScenarist(RESPONSIBLE_FACTORY.getNew(false, withIds))
					.addOtherStaffMember(RESPONSIBLE_FACTORY.getNew(false, withIds))
					.addOtherStaffMember(RESPONSIBLE_FACTORY.getNew(false, withIds));
		}

		index += 1;
		Video video = builder.build();
		if (withIds) {
			new EntityHelper().setId(video, DEFAULT_ID);
		}
		return video;
	}
}