package by.epam.inner.factories;

import by.epam.inner.beans.ExtraTrial;
import by.epam.inner.beans.Trial;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.*;

import static by.epam.inner.constants.Constants.*;

public class TrialFactory {

    public static Optional<Trial> getTrialFromFactory(JsonObject jsonObject) {
        try {
            String className = getValidJsonElement(jsonObject, CLASS_FIELD)
                    .orElseThrow(IllegalArgumentException::new)
                    .getAsString();
            JsonObject argslJSON = getValidJsonElement(jsonObject, ARGS_FIELD)
                    .orElseThrow(IllegalArgumentException::new)
                    .getAsJsonObject();

            if (jsonObject.size() != JSON_FIELDS_NUMBER) {
                LOGGER.warn(EXTRA_DATA_IN_JSONOBJECT + ARRAY_DELIMITER + jsonObject.toString());
            }

            Class<Trial> trialClass = (Class<Trial>) Class.forName(PACKAGE_NAME + className);

            Trial trial = GSON.fromJson(argslJSON, trialClass);

            if (!isCorrectNumberOfArgs(trialClass, argslJSON)){
                LOGGER.warn(INCORRECT_DATA_IN_ARGS + ARRAY_DELIMITER + argslJSON.toString());
            }

            return Optional.ofNullable(trial)
                    .filter(TrialFactory::isTrialValid);



           /*String account = argslJSON.get(ACCOUNT_FIELD).getAsString();
            int mark1 = argslJSON.get(MARK1_FIELD).getAsInt();
            int mark2 = argslJSON.get(MARK2_FIELD).getAsInt();

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
                    int mark3 = argslJSON.get(MARK3_FIELD).getAsInt();
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
        }catch (IllegalArgumentException e){
            LOGGER.error(EMPTY_DATA_IN_JSONOBJECT + ARRAY_DELIMITER + jsonObject.toString());
            return Optional.empty();
        }
    }

    private static boolean isCorrectNumberOfArgs(Class<? extends Trial> trialClass, JsonObject args) {
        int argsNumber = args.size();
        return (trialClass == ExtraTrial.class
                 && argsNumber == EXTRA_TRIAL_FIELDS_NUMBER)
                || (trialClass != ExtraTrial.class
                && argsNumber == TRIAL_FIELDS_NUMBER);
    }

    private static Optional<JsonElement> getValidJsonElement(JsonObject jsonObject, String argsName){
        return Optional.ofNullable(jsonObject.get(argsName));
    }

    private static boolean isTrialValid(Trial trial){
        boolean isValid =  Objects.nonNull(trial.getAccount())
                &&!trial.getAccount().isEmpty()
                && isMarkValid(trial.getMark1())
                && isMarkValid(trial.getMark2())
                && (!(trial instanceof ExtraTrial) || isMarkValid(((ExtraTrial)trial).getMark3()));
        if (!isValid){
            LOGGER.error(WRONG_TRIAL + ARRAY_DELIMITER + trial);
        }
        return isValid;
    }

    private static boolean isMarkValid(int mark){
        return mark >= 0 && mark <= 100;
    }
}
