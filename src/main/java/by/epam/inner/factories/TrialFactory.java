package by.epam.inner.factories;

import by.epam.inner.beans.ExtraTrial;
import by.epam.inner.beans.LightTrial;
import by.epam.inner.beans.StrongTrial;
import by.epam.inner.beans.Trial;
import by.epam.inner.deserializers.TrialDeserializer;
import com.google.gson.*;

import java.util.*;

import static by.epam.inner.constants.Constants.*;

public class TrialFactory {
    private static final TrialDeserializer TRIAL_DESERIALIZER = new TrialDeserializer();

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Trial.class, TRIAL_DESERIALIZER)
            .registerTypeAdapter(LightTrial.class, TRIAL_DESERIALIZER)
            .registerTypeAdapter(StrongTrial.class, TRIAL_DESERIALIZER)
            .registerTypeAdapter(ExtraTrial.class, TRIAL_DESERIALIZER)
            .create();

    public static Optional<Trial> getTrialFromFactory(JsonObject jsonObject) {

        try {
            String className = jsonObject.get(CLASS_FIELD).getAsString();
            JsonObject args = jsonObject.get(ARGS_FIELD).getAsJsonObject();
            Class<Trial> classType = (Class<Trial>) Class.forName(PACKAGE_NAME + className);
            return Optional.of(GSON.fromJson(args, classType));
        } catch (IllegalArgumentException | ClassNotFoundException e) {
            LOGGER.error(e.getMessage() + ARRAY_DELIMITER + jsonObject);
            return Optional.empty();
        }

    }


}
