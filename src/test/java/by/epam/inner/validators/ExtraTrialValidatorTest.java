package by.epam.inner.validators;

import by.epam.inner.beans.ExtraTrial;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import junit.framework.TestCase;

public class ExtraTrialValidatorTest extends TestCase {

    JsonObject argsCorrect;
    JsonObject argsWrong;
    ExtraTrialValidator<ExtraTrial> extraTrialValidator;

    public void setUp()  {
        Gson gson = new Gson();
        String argsStrCorrect = "{\"account\":\"Right\",\"mark1\":97,\"mark2\":85,\"mark3\":88}";
        String argsStrWrong = "{\"account\":\"Wrong\",\"mark1\":97,\"mark2\":85,\"mark3\":188}";
        argsCorrect = gson.fromJson(argsStrCorrect, JsonObject.class);
        argsWrong = gson.fromJson(argsStrWrong, JsonObject.class);
        extraTrialValidator = new ExtraTrialValidator<>(ExtraTrial.class);
    }

    public void tearDown() throws Exception {
        argsCorrect = null;
        argsWrong = null;
        extraTrialValidator = null;
    }

    public void testCheckArgsAndSetFields() {
        assertTrue(extraTrialValidator.checkArgsAndSetFields(argsCorrect));
    }

    public void testCheckArgsAndSetFieldsWrong() {
        assertFalse(extraTrialValidator.checkArgsAndSetFields(argsWrong));
    }
}