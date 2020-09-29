package by.epam.inner.validators;

import by.epam.inner.beans.ExtraTrial;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.InvocationTargetException;

import static by.epam.inner.constants.Constants.*;

public class ExtraTrialValidator<T extends ExtraTrial> extends TrialValidator<ExtraTrial> {

    public ExtraTrialValidator(Class<ExtraTrial> trialClass)  {
        super(trialClass);
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
            super.getTrial().setMark3(mark3.getAsInt());
            return true;
        }else {
            LOGGER.error(WRONG_TRIAL + EXCEPTION_DELIMITER + args);
            return false;
        }
    }

}
