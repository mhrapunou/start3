package by.epam.inner.validators;

import by.epam.inner.beans.ExtraTrial;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExtraTrialValidatorTest {

    JsonObject argsCorrect;
    JsonObject argsWrong;
    ExtraTrialValidator<ExtraTrial> extraTrialValidator;

    @Before
    public void setUp()  {
        Gson gson = new Gson();
        String argsStrCorrect = "{\"account\":\"Right\",\"mark1\":97,\"mark2\":85,\"mark3\":88}";
        String argsStrWrong = "{\"account\":\"Wrong\",\"mark1\":97,\"mark2\":85,\"mark3\":188}";
        argsCorrect = gson.fromJson(argsStrCorrect, JsonObject.class);
        argsWrong = gson.fromJson(argsStrWrong, JsonObject.class);
        extraTrialValidator = new ExtraTrialValidator<>(ExtraTrial.class);
    }

    @After
    public void tearDown() {
        argsCorrect = null;
        argsWrong = null;
        extraTrialValidator = null;
    }

    @Test
    public void testCheckArgsAndSetFields() {
        Assert.assertTrue(extraTrialValidator.checkArgsAndSetFields(argsCorrect));
    }

    @Test
    public void testCheckArgsAndSetFieldsWrong() {
        Assert.assertFalse(extraTrialValidator.checkArgsAndSetFields(argsWrong));
    }
}