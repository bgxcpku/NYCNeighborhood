import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lbo.foursquare.model.Checkin;
import models.ny.ConnectedUser;
import org.junit.*;
import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;
import models.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ApplicationTest extends FunctionalTest {

    @Test
    public void testThatIndexPageWorks() {
        Response response = GET("/");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset(play.Play.defaultWebEncoding, response);
    }

    @Test
    public void testThatPolicyPageWorks() {
        Response response = GET("/policy");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset(play.Play.defaultWebEncoding, response);
    }

    @Test
    public void testThatConnectedPageWorks() {
        Response response = GET("/connected");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset(play.Play.defaultWebEncoding, response);
    }

    @Test
    public void testThatAboutPageWorks() {
        Response response = GET("/about");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset(play.Play.defaultWebEncoding, response);
    }

    @Test
    public void testThatChangedMindPageWorks() {
        Response response = GET("/changedMind");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset(play.Play.defaultWebEncoding, response);
    }

    @Test
    public void testPushWithEligibleCheckin() throws IOException {

        ConnectedUser.all().delete();

        BufferedReader in = null;
        in = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("/eligibleCheckin.json")));

        JsonReader reader = new JsonReader(in);
        Checkin checkin = new Gson().fromJson(reader, Checkin.class);

        ConnectedUser user = new ConnectedUser(new Long("949340"), "FAKE_TOKEN");
        user.save();

        Response response = GET("/psh?checkin="+new Gson().toJson(checkin)+"&secret=SOME_SECRET");

        if (in != null) {
            in.close();
        }
        in = null;

        assertStatus(200, response);
    }

    @Test
    public void testPushWithFoursquareTestCheckin() throws IOException {

        ConnectedUser.all().delete();

        BufferedReader in = null;
        in = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("/testCheckinFromFoursquare.json")));

        JsonReader reader = new JsonReader(in);
        Checkin checkin = new Gson().fromJson(reader, Checkin.class);

        ConnectedUser user = new ConnectedUser(new Long("949340"), "FAKE_TOKEN");
        user.save();

        Response response = GET("/psh?checkin="+new Gson().toJson(checkin)+"&secret=SOME_SECRET");

        if (in != null) {
            in.close();
        }
        in = null;

        assertStatus(200, response);
    }
}