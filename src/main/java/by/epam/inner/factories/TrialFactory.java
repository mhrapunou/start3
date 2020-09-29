package by.epam.inner.factories;

import by.epam.inner.beans.ExtraTrial;
import by.epam.inner.beans.Trial;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

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
                LOGGER.warn(EXTRA_DATA_IN_JSONOBJECT + EXCEPTION_DELIMITER + jsonObject.toString());
            }

            Class<Trial> trialClass = (Class<Trial>) Class.forName(PACKAGE_NAME + className);



            Trial trial = GSON.fromJson(argslJSON, trialClass);

            if (!isCorrectNumberOfArgs(trialClass, argslJSON)){
                LOGGER.warn(INCORRECT_DATA_IN_ARGS + EXCEPTION_DELIMITER + argslJSON.toString());
            }

            return Optional.ofNullable(trial)
                    .filter(TrialFactory::isTrialValid);

        }catch (ClassNotFoundException e){
            LOGGER.error(WRONG_CLASS_NAME + EXCEPTION_DELIMITER + jsonObject);
            return Optional.empty();
        }catch (IllegalArgumentException e){
            LOGGER.error(EMPTY_DATA_IN_JSONOBJECT + EXCEPTION_DELIMITER + jsonObject);
            return Optional.empty();
        }
        catch (JsonSyntaxException e){
            LOGGER.error(WRONG_JSON_SYNTAX + EXCEPTION_DELIMITER + jsonObject);
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
            LOGGER.error(WRONG_TRIAL + EXCEPTION_DELIMITER + trial);
        }
        return isValid;
    }

    private static boolean isMarkValid(int mark){
        return mark >= 0 && mark <= 100;
    }
}
