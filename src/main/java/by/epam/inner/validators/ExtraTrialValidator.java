package by.epam.inner.validators;

import by.epam.inner.beans.ExtraTrial;
import by.epam.inner.beans.Trial;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import static by.epam.inner.constants.Constants.*;

public class ExtraTrialValidator extends TrialValidator {
    private  ExtraTrial extraTrial;

    public ExtraTrialValidator(Class<? extends ExtraTrial> trialClass)  {
        super(trialClass);
        try {
            this.extraTrial = trialClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalArgumentException(WRONG_CLASS_NAME);
        }
    }

    @Override
    protected boolean checkArgsAndSetFields(JsonObject args){
        super.checkArgsAndSetFields(args);
        JsonElement mark3 = args.get(MARK3_FIELD);
        if (!isJsonElementValid(mark3)) {
            LOGGER.error(EMPTY_DATA_IN_JSONOBJECT + EXCEPTION_DELIMITER + args);
            return false;
        }

        if (args.size() > EXTRA_TRIAL_FIELDS_NUMBER) {//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            LOGGER.info(EXTRA_DATA_IN_JSONOBJECT + EXCEPTION_DELIMITER + args);

        }

        if (isMarkValid(mark3.getAsInt())) {
            extraTrial = new ExtraTrial(super.getTrial(), mark3.getAsInt());
            return true;
        }else {
            LOGGER.error(WRONG_TRIAL + EXCEPTION_DELIMITER + args);
            return false;
        }
    }

    protected Trial getTrial(){
        return extraTrial;
    }

}
