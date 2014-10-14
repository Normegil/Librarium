package be.normegil.librarium.model.data.game;

import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.data.Media;
import be.normegil.librarium.model.data.people.Responsible;
import be.normegil.librarium.model.data.people.StaffMember;
import be.normegil.librarium.model.data.people.StaffRole;
import be.normegil.librarium.tool.DataFactory;
import be.normegil.librarium.tool.EntityHelper;
import be.normegil.librarium.tool.FactoryRepository;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.UUID;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UTGameSafety.class,
		UTGame.class,
		UTGameEquality.class,
		UTGameComparator.class,
		UTGameBuilderSafety.class,
		UTGameBuilder.class,
		UTGameDigestSafety.class,
		UTGameDigest.class,
		UTGameDigestEquality.class,
		UTGameDigestJSONSupport.class,
		UTGameDatabaseSupport.class
})
public class GameTestSuite implements DataFactory<Game> {

	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Media> MEDIA_FACTORY = FactoryRepository.get(Media.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<GameSerie> GAME_SERIE_FACTORY = FactoryRepository.get(GameSerie.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<Responsible> RESPONSIBLE_FACTORY = FactoryRepository.get(Responsible.class);
	@SuppressWarnings(WarningTypes.UNCHECKED_CAST)
	private static final DataFactory<StaffMember> STAFF_MEMBER_FACTORY = FactoryRepository.get(StaffMember.class);
	private static final UUID DEFAULT_ID = UUID.fromString("ddde0e8f-67d2-4907-85ea-bf3eb3143c13");
	private static final UUID DEVELOPER_ID = UUID.fromString("cdec7af5-8ea8-41d3-9ecd-696740dd1342");
	private static final UUID EDITOR_ID = UUID.fromString("cdec7af5-8ea8-41d3-9ecd-696740dd1342");
	private static final UUID COMPOSER_ID = UUID.fromString("2b71b51c-edc9-4ad4-8546-b5ac8c38c9e7");

	@Override
	public Game getDefault() {
		return getDefault(true, false);
	}

	@Override
	public Game getNew() {
		return getNew(true, false);
	}

	@Override
	public Game getDefault(final boolean withLink, final boolean withIds) {
		Game.Builder builder = Game.builder()
				.from(MEDIA_FACTORY.getDefault(withLink, withIds));
		EntityHelper helper = new EntityHelper();
		if (withLink) {
			StaffMember developer = STAFF_MEMBER_FACTORY.getDefault(false, withIds);
			developer.setRole(StaffRole.DEVELOPER);

			StaffMember editor = STAFF_MEMBER_FACTORY.getDefault(false, withIds);
			editor.setRole(StaffRole.EDITOR);

			StaffMember composer = STAFF_MEMBER_FACTORY.getDefault(false, withIds);
			composer.setRole(StaffRole.COMPOSER);

			if (withIds) {
				helper.setId(developer, DEVELOPER_ID);
				helper.setId(editor, EDITOR_ID);
				helper.setId(composer, COMPOSER_ID);
			}

			builder
					.addStaffMember(developer)
					.addStaffMember(editor)
					.addStaffMember(composer)
					.setSerie(GAME_SERIE_FACTORY.getDefault(false, withIds));
		}
		Game game = builder.build();
		if (withIds) {
			helper.setId(game, DEFAULT_ID);
		}
		return game;
	}

	@Override
	public Game getNew(final boolean withLink, final boolean withIds) {
		Game.Builder builder = Game.builder()
				.from(MEDIA_FACTORY.getNew(withLink, withIds));
		if (withLink) {
			StaffMember developer = STAFF_MEMBER_FACTORY.getNew(false, withIds);
			developer.setRole(StaffRole.DEVELOPER);

			StaffMember editor = STAFF_MEMBER_FACTORY.getNew(false, withIds);
			editor.setRole(StaffRole.EDITOR);

			StaffMember composer = STAFF_MEMBER_FACTORY.getNew(false, withIds);
			composer.setRole(StaffRole.COMPOSER);

			builder
					.addStaffMember(developer)
					.addStaffMember(editor)
					.addStaffMember(composer)
					.setSerie(GAME_SERIE_FACTORY.getNew(false, withIds));
		}
		Game game = builder.build();
		if (withIds) {
			new EntityHelper().setId(game, UUID.randomUUID());
		}
		return game;
	}
}