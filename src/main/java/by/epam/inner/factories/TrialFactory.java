package by.epam.inner.factories;

import by.epam.inner.beans.ExtraTrial;
import by.epam.inner.beans.LightTrial;
import by.epam.inner.beans.StrongTrial;
import by.epam.inner.beans.Trial;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import static by.epam.inner.constants.Constants.*;

public class TrialFactory {
    private static final Logger LOGGER = Logger.getLogger("logfile");

    public static Optional<Trial> getTrialFromFactory(JsonObject jsonObject) {
        try {
            Gson gson = new Gson();
            String className = jsonObject.get(CLASS_FIELD).getAsString();
            JsonObject trialJSON = jsonObject.get(ARGS_FIELD).getAsJsonObject();

            Class<Trial> trialClass = (Class<Trial>) Class.forName("by.epam.inner.beans." + className);

            if (!isNumberOfFieldsValid(jsonObject, trialJSON)) {
                LOGGER.warn(EXTRA_DATA + ARRAY_DELIMITER + jsonObject.toString());
                //return Optional.empty();
            }

            return Optional.ofNullable(gson.fromJson(trialJSON, trialClass))
                    .filter(TrialFactory::isFieldsValid);


           /*String account = trialJSON.get(ACCOUNT_FIELD).getAsString();
            int mark1 = trialJSON.get(MARK1_FIELD).getAsInt();
            int mark2 = trialJSON.get(MARK2_FIELD).getAsInt();

            if (account.equals(EMPTY_STRING)) {
                LOGGER.error(EMPTY_NAME + ARRAY_DELIMITER + jsonObject.toString());
                return Optional.empty();
            }

            if (!isMarkValid(mark1)) {
                LOGGER.error(WRONG_MARK1 + ARRAY_DELIMITER + jsonObject.toString());
                return Optional.empty();
            }

            if (!isMarkValid(mark2)) {
                LOGGER.error(WRONG_MARK2 + ARRAY_DELIMITER + jsonObject.toString());
                return Optional.empty();
            }

            switch (className) {
                case TRIAL:
                    return Optional.of(new Trial(account, mark1, mark2));
                case LIGHT_TRIAL:
                    return Optional.of(new LightTrial(account, mark1, mark2));
                case STRONG_TRIAL:
                    return Optional.of(new StrongTrial(account, mark1, mark2));
                case EXTRA_TRIAL:
                    int mark3 = trialJSON.get(MARK3_FIELD).getAsInt();
                    if (!isMarkValid(mark3)) {
                        LOGGER.error(WRONG_MARK3 + ARRAY_DELIMITER + jsonObject.toString());
                        return Optional.empty();
                    }

                    return Optional.of(new ExtraTrial(account, mark1, mark2, mark3));
                default:
                    LOGGER.error(WRONG_CLASS_NAME + ARRAY_DELIMITER + jsonObject.toString());
                    return Optional.empty();

            }*/
        }catch (ClassNotFoundException e){
            LOGGER.error(WRONG_CLASS_NAME + ARRAY_DELIMITER + jsonObject.toString());
            return Optional.empty();
        }
    }



    private static boolean isNumberOfFieldsValid(JsonObject jsonObject, JsonObject trialJson) {
        if (jsonObject.entrySet().size() == JSON_FIELDS_NUMBER) {
            if (!jsonObject.get(CLASS_FIELD).getAsString().equals(EXTRA_TRIAL)) {
                return trialJson.entrySet().size() == TRIAL_FIELDS_NUMBER;
            } else {
                return trialJson.entrySet().size() == EXTRA_TRIAL_FIELDS_NUMBER;
            }
        }else return false;
    }

    private static boolean isFieldsValid(Trial trial){
        return !trial.getAccount().isEmpty()
                && isMarkValid(trial.getMark1())
                && isMarkValid(trial.getMark2())
                && (!(trial instanceof ExtraTrial) || isMarkValid(((ExtraTrial)trial).getMark3()));
    }

    private static boolean isMarkValid(int mark){
        return mark >= 0 && mark <= 100;
    }
}
