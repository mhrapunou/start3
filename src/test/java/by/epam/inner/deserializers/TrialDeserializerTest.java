package by.epam.inner.deserializers;

import by.epam.inner.beans.Trial;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;
import static by.epam.inner.constants.Constants.*;

public class TrialDeserializerTest {
    private static final TrialDeserializer TRIAL_DESERIALIZER = new TrialDeserializer();
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Trial.class, TRIAL_DESERIALIZER)
            .create();
    private JsonObject trialJsonObject;
    private Trial expectedTrial;

    @Before
    public void setUp() throws Exception {
        String trialJsonString = "{\"class\":\"Trial\","
                + "\"args\":{"
                + "\"account\":\"Cool\","
                + "\"mark1\":80,"
                + "\"mark2\":90}}";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        trialJsonObject = gson.fromJson(trialJsonString, JsonObject.class);
        expectedTrial = new Trial("Cool", 80, 90);
    }

    @After
    public void tearDown() throws Exception {
        trialJsonObject = null;
        expectedTrial = null;
    }

    @Test
    public void testDeserialize() throws ClassNotFoundException {
        String className = trialJsonObject.get(CLASS_FIELD).getAsString();
        JsonObject args = trialJsonObject.get(ARGS_FIELD).getAsJsonObject();
        Class<Trial> classType = (Class<Trial>) Class.forName(PACKAGE_NAME + className);
        Trial actualTrial = GSON.fromJson(args, classType);
        assertTrue(expectedTrial.getAccount().equals(actualTrial.getAccount())
                    && expectedTrial.getMark1() == actualTrial.getMark1()
                    && expectedTrial.getMark2() == actualTrial.getMark2());

    }
}