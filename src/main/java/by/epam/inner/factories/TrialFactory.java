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
            String className = getValidJsonElement(jsonObject, CLASS_FIELD)
                    .orElseThrow(IllegalArgumentException::new)
                    .getAsString();
            JsonObject args = getValidJsonElement(jsonObject, ARGS_FIELD)
                    .orElseThrow(IllegalArgumentException::new)
                    .getAsJsonObject();

            if (jsonObject.size() != JSON_FIELDS_NUMBER) {
                LOGGER.warn(EXTRA_DATA_IN_JSONOBJECT + ARRAY_DELIMITER + jsonObject);
            }

            Class<Trial> classType = (Class<Trial>) Class.forName(PACKAGE_NAME + className);
            return Optional.of(GSON.fromJson(args, classType));

        } catch (IllegalArgumentException | ClassNotFoundException e) {
            LOGGER.error(e.getMessage() + ARRAY_DELIMITER + jsonObject);
            return Optional.empty();
        }

    }

    private static Optional<JsonElement> getValidJsonElement(JsonObject jsonObject, String argsName){
        return Optional.ofNullable(jsonObject.get(argsName));
    }

}
