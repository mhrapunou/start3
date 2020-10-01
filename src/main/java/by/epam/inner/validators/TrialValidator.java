package by.epam.inner.validators;

import by.epam.inner.beans.Trial;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Optional;

import static by.epam.inner.constants.Constants.*;

public class TrialValidator <T extends Trial> {
    private final T trial;

    public TrialValidator(Class<T> trialClass) {
        try {
            this.trial = trialClass.getConstructor().newInstance();
            trialClass.cast(this.trial);

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalArgumentException(WRONG_CLASS_NAME );
        }
    }

    public Optional<Trial> getValidTrial(JsonElement element){
        JsonObject jsonObject = element.getAsJsonObject();

        if (!checkArgsAndSetFields(jsonObject)){
            return Optional.empty();
        }else {
            return Optional.of(getTrial());
        }
    }

    protected boolean checkArgsAndSetFields(JsonObject args) {
        JsonElement account = args.get(ACCOUNT_FIELD);
        JsonElement mark1 = args.get(MARK1_FIELD);
        JsonElement mark2 = args.get(MARK2_FIELD);
        if (!isJsonElementValid(account) || !isJsonElementValid(mark1) || !isJsonElementValid(mark2)) {
            return false;
        }

        if (args.size() > TRIAL_FIELDS_NUMBER && !trial.getClass().getSimpleName().equals(EXTRA_TRIAL_NAME)) {
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
            return false;
        }
    }

    protected static boolean isJsonElementValid(JsonElement element){
        return Objects.nonNull(element) && element.isJsonPrimitive();
    }

    protected static boolean isMarkValid(int mark){
        return mark >= 0 && mark <= 100;
    }

    protected T getTrial(){
        return trial;
    }
}
