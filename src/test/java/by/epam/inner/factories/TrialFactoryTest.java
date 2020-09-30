package by.epam.inner.factories;

import by.epam.inner.beans.Trial;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static by.epam.inner.constants.Constants.PACKAGE_NAME;
import static org.junit.Assert.*;

public class TrialFactoryTest {
    JsonObject trialJsonObject;
    Gson gson;

    @Before
    public void setUp() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        String trialJsonString = "{\"class\":\"Trial\","
                + "\"args\":{"
                + "\"account\":\"Cool\","
                + "\"mark1\":80,"
                + "\"mark2\":90}}";
        trialJsonObject = gson.fromJson(trialJsonString, JsonObject.class);
    }

    @After
    public void tearDown() {
        gson = null;
        trialJsonObject = null;
    }

    @Test
    public void testGetTrialFromFactory() throws Exception {
        Trial expectedTrial = new Trial("Cool", 80, 90);
        Trial actualTrial = TrialFactory.getTrialFromFactory(trialJsonObject).orElse(new Trial());
        assertTrue(expectedTrial.getAccount().equals(actualTrial.getAccount())
                && expectedTrial.getMark1() == actualTrial.getMark1()
                && expectedTrial.getMark2() == actualTrial.getMark2());
    }

   @Test(expected = NullPointerException.class)
    public void testToIllegalException() {
       String wrongTrialJsonString = "{\"class\":\"Trial\","
               + "\"arguments\":{"
               + "\"account\":\"Cool\","
               + "\"mark1\":80,"
               + "\"mark2\":90}}";

       JsonObject wrongJsonObject = gson.fromJson(wrongTrialJsonString, JsonObject.class).get("args").getAsJsonObject();
    }

    @Test(expected = JsonSyntaxException.class)
    public void testToJsonSyntaxException() {
        String wrongTrialJsonString = "{\"class\":\"Trial\","
                + "\"args\":{"
                + "\"account\":\"Cool\","
                + "\"mark1\":,"
                + "\"mark2\":90}}";

        JsonObject wrongJsonObject = gson.fromJson(wrongTrialJsonString, JsonObject.class);
    }

    @Test(expected = ClassNotFoundException.class)
    public void testToClassNotFoundException() throws ClassNotFoundException {
        Class<Trial> trialClass = (Class<Trial>) Class.forName(PACKAGE_NAME + "Kitchen");
    }

}