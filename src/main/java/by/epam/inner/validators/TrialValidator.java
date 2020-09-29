package by.epam.inner.validators;

import by.epam.inner.beans.ExtraTrial;
import by.epam.inner.beans.Trial;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Optional;

import static by.epam.inner.constants.Constants.*;

public class TrialValidator {
    private final Trial trial;

    public TrialValidator(Class<? extends Trial> trialClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        this.trial = trialClass.getConstructor().newInstance();
    }

    public Optional<Trial> getValidTrial(JsonElement element){
        JsonObject jsonObject = element.getAsJsonObject();

        if (!checkArgsAndSetFields(jsonObject)){
            LOGGER.warn(INCORRECT_DATA_IN_ARGS + EXCEPTION_DELIMITER + jsonObject);
            return Optional.empty();
        }else {
            return Optional.of(getTrial());
        }

    }

    protected boolean checkArgsAndSetFields(JsonObject args) {
        JsonElement account = args.get(ACCOUNT_FIELD);
        JsonElement mark1 = args.get(MARK1_FIELD);
        JsonElement mark2 = args.get(MARK1_FIELD);
        if (Objects.isNull(account) || Objects.isNull(mark1) || Objects.isNull(mark2)) {
            LOGGER.error(EMPTY_DATA_IN_JSONOBJECT + EXCEPTION_DELIMITER + args);
            return false;
        }
        if (args.size() > TRIAL_FIELDS_NUMBER) {//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            LOGGER.info(EXTRA_DATA_IN_JSONOBJECT + EXCEPTION_DELIMITER + args);
        }
        if (!account.getAsString().isEmpty()
                && isMarkValid(mark1.getAsInt())
                && isMarkValid(mark2.getAsInt())) {
            trial.setAccount(account.getAsString());
            trial.setMark1(mark1.getAsInt());
            trial.setMark2(mark2.getAsInt());
            return true;
        }else {
            LOGGER.error(WRONG_TRIAL + EXCEPTION_DELIMITER + args);
            return false;
        }
    }

    protected Trial getTrial(){
        return trial;
    }

    protected static boolean isMarkValid(int mark){
        return mark >= 0 && mark <= 100;
    }
}
