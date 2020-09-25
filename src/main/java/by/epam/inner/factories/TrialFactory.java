package by.epam.inner.factories;

import by.epam.inner.beans.ExtraTrial;
import by.epam.inner.beans.LightTrial;
import by.epam.inner.beans.StrongTrial;
import by.epam.inner.beans.Trial;
import com.google.gson.JsonObject;

import java.util.Optional;
import static by.epam.inner.constants.Constants.*;

public class TrialFactory {
    public static Optional<Trial> getTrialFromFactory(JsonObject jsonObject) {
        String className = jsonObject.get(CLASS_FIELD).getAsString();
        JsonObject trialJSON = jsonObject.get(ARGS_FIELD).getAsJsonObject();

        if (!isNumberOfFieldsValid(jsonObject, trialJSON)) {
            return Optional.empty();
        }

        String account = trialJSON.get(ACCOUNT_FIELD).getAsString();
        int mark1 = trialJSON.get(MARK1_FIELD).getAsInt();
        int mark2 = trialJSON.get(MARK2_FIELD).getAsInt();

        if (account.equals(EMPTY_STRING)) {
            return Optional.empty();
        }

        if (!isMarkValid(mark1)) {
            return Optional.empty();
        }

        if (!isMarkValid(mark2)) {
            return Optional.empty();
        }

        switch (className) {
            case TRIAL:
                return Optional.of(new Trial(account, mark1, mark2));
            case LIGHT_TRIAL:
                return  Optional.of(new LightTrial(account, mark1, mark2));
            case STRONG_TRIAL:
                return Optional.of(new StrongTrial(account, mark1, mark2));
            case EXTRA_TRIAL:
                int mark3 = trialJSON.get(MARK3_FIELD).getAsInt();
                if (!isMarkValid(mark3)) {
                    return Optional.empty();
                }

                return Optional.of(new ExtraTrial(account, mark1, mark2, mark3));
            default:
                return Optional.empty();

        }
    }

    private static boolean isMarkValid(int mark){
        return mark >= 0 && mark <= 100;
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
}
