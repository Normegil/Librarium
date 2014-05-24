package be.normegil.librarium.rest;

import be.normegil.librarium.ApplicationProperties;
import be.normegil.librarium.WarningTypes;
import be.normegil.librarium.model.dao.GameDAO;
import be.normegil.librarium.model.dao.GameDatabaseDAO;
import be.normegil.librarium.model.data.Game;
import be.normegil.librarium.util.LoggerProducer;
import org.apache.http.HttpStatus;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.MavenResolverSystem;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import static be.normegil.librarium.AssertHelper.assertCollectionsEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class UTGameREST_RESTReponses {

    public static final String SERVICE_PATH = "rest/games";
    public static final String GET_ALL = "/all";
    public static final String NAME = "TestName";

    @ArquillianResource
    @SuppressWarnings(WarningTypes.UNUSED)
    private URL deployementURL;

    private Collection<Game> gameInDatabase = new ArrayList<Game>();

    @After
    public void tearDown() throws Exception {
        Collection<Game> gamesFromREST = getGamesFromREST();
        for (Game game : gamesFromREST) {
            getRESTServices()
                    .path("/" + game.getId())
                    .request()
                    .delete();
        }
    }

    @Deployment
    public static WebArchive createTestArchive() {
        MavenResolverSystem resolver = Maven.resolver();
        resolver.loadPomFromFile("pom.xml");

        return ShrinkWrap.create(WebArchive.class)
                .addClass(GameREST.class)
                .addClass(ApplicationProperties.class)
                .addClass(GameDatabaseDAO.class)
                .addClass(GameDAO.class)
                .addClass(Game.class)
                .addClass(LoggerProducer.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsLibraries(resolver.resolve("org.apache.commons:commons-lang3:3.3.1").withTransitivity().asFile());
    }

    @Test
    @RunAsClient
    public void testGetAll() throws Exception {
        insertData();
        Response response = getRESTServices()
                .path(GET_ALL)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();
        checkResponse(response);

        GenericType<Collection<Game>> gameCollectionType = new GenericType<Collection<Game>>() {
        };
        Collection<Game> gamesFromREST = response.readEntity(gameCollectionType);

        assertCollectionsEquals(gameInDatabase, gamesFromREST);
    }

    @Test
    @RunAsClient
    public void testGetSpecific() throws Exception {
        insertData();
        Collection<Game> gamesList = getGamesFromREST();
        Game game = gamesList.iterator().next();

        Response response = getRESTServices()
                .path("/" + game.getId())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();
        checkResponse(response);

        Game gameFromREST = response.readEntity(Game.class);

        assertEquals(HttpStatus.SC_OK, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());
        assertEquals(game.getId(), gameFromREST.getId());
    }

    @Test
    @RunAsClient
    public void testCreateGame() throws Exception {
        Game game = new Game(NAME);
        WebTarget restServices = getRESTServices();

        Collection<Game> gamesFromREST = getGamesFromREST();
        gameInDatabase.addAll(gamesFromREST);

        Response response = restServices
                .request()
                .put(Entity.entity(game, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(HttpStatus.SC_OK, response.getStatus());
        response.close();

        gameInDatabase.add(game);
        Collection<Game> gamesFromRESTAfterCreation
                = getGamesFromREST();
        assertCollectionsEquals(gameInDatabase, gamesFromRESTAfterCreation);
    }

    @Test
    @RunAsClient
    public void testUpdateGame() throws Exception {
        insertData();
        WebTarget restServices = getRESTServices();

        Collection<Game> games = getGamesFromREST();
        Game game = games.iterator().next();

        game.setName(NAME);
        Response response = restServices
                .path("/" + game.getId())
                .request()
                .post(Entity.entity(
                        game,
                        MediaType.APPLICATION_JSON_TYPE
                ));
        assertEquals(HttpStatus.SC_OK, response.getStatus());
        response.close();

        response = restServices
                .path("/" + game.getId())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();
        checkResponse(response);
        Game gameFromREST = response.readEntity(Game.class);
        response.close();

        assertEquals(game.getId(), gameFromREST.getId());
        assertEquals(game.getName(), gameFromREST.getName());
    }

    @Test
    @RunAsClient
    public void testDeleteGame() throws Exception {
        insertData();
        Collection<Game> gamesFromREST = getGamesFromREST();

        Game game = gamesFromREST.iterator().next();
        WebTarget restServices = getRESTServices();
        Response response = restServices
                .path("/" + game.getId())
                .request()
                .delete();
        assertEquals(HttpStatus.SC_OK, response.getStatus());
        response.close();

        response = restServices
                .path("/" + game.getId())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();
        assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatus()); // Game not Found send BAD_REQUEST code. See testGet_DoesntExist()
    }

    @Test
    @RunAsClient
    public void testGetAll_NoData() throws Exception {
        Response response = getRESTServices()
                .path(GET_ALL)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();
        checkResponse(response);

        GenericType<Collection<Game>> gameCollectionType = new GenericType<Collection<Game>>() {
        };
        Collection<Game> gamesFromREST = response.readEntity(gameCollectionType);

        assertTrue(gamesFromREST.isEmpty());
    }

    @Test
    @RunAsClient
    public void testGet_DoesntExist() throws Exception {
        Response response = getRESTServices()
                .path("/-1")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();
        assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatus());
    }

    @Test
    @RunAsClient
    public void testCreateGame_WithID() throws Exception {
        Game game = new Game(-1L, NAME);
        WebTarget restServices = getRESTServices();

        Response response = restServices
                .request()
                .put(Entity.entity(game, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatus());
    }

    @Test
    @RunAsClient
    public void testUpdateGame_NotExisting() throws Exception {
        WebTarget restServices = getRESTServices();

        Game game = new Game(-1L, NAME + "-1");
        Response response = restServices
                .path("/-1")
                .request()
                .post(Entity.entity(
                        game,
                        MediaType.APPLICATION_JSON_TYPE
                ));
        assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatus());
    }

    @Test
    @RunAsClient
    public void testUpdateGame_DifferentID() throws Exception {
        WebTarget restServices = getRESTServices();

        Game game = new Game(-1L, NAME + "-1");
        Response response = restServices
                .path("/-2")
                .request()
                .post(Entity.entity(
                        game,
                        MediaType.APPLICATION_JSON_TYPE
                ));
        assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatus());
    }

    @Test
    @RunAsClient
    public void testDeleteGame_NotExisting() throws Exception {
        WebTarget restServices = getRESTServices();
        Response response = restServices
                .path("/-1")
                .request()
                .delete();
        assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatus());
    }

    private WebTarget getRESTServices() {
        Client client = ClientBuilder.newClient();
        return client.target(deployementURL.toString() + SERVICE_PATH);
    }

    private void insertData() {
        WebTarget restServices = getRESTServices();
        for (long i = 0; i < 5; i++) {
            Game game = new Game("Name" + i);
            Response response = restServices
                    .request()
                    .put(Entity.entity(game, MediaType.APPLICATION_JSON_TYPE));
            response.close();
            gameInDatabase.add(game);
        }
    }

    private Collection<Game> getGamesFromREST() {
        Response response = getRESTServices()
                .path(GET_ALL)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();
        GenericType<Collection<Game>> gameCollectionType = new GenericType<Collection<Game>>() {
        };
        Collection<Game> games = response.readEntity(gameCollectionType);
        response.close();
        return games;
    }

    private void checkResponse(Response response) {
        assertEquals(HttpStatus.SC_OK, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());
    }
}
