package by.epam.inner.validators;

import by.epam.inner.beans.Trial;
import by.epam.inner.constants.Constants;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TrialValidatorTest {
    JsonObject argsCorrect;
    JsonObject argsWrong;
    TrialValidator<Trial> trialValidator;
    Trial emptyTrial;
    Trial rightTrial;

    @Before
    public void setUp() {
        Gson gson = new Gson();
        String argsStrCorrect = "{\"account\":\"Right\",\"mark1\":97,\"mark2\":85}";
        String argsStrWrong = "{\"account\":\"Wrong\",\"mark1\":97,\"mark2\":185}";
        argsCorrect = gson.fromJson(argsStrCorrect, JsonObject.class);
        argsWrong = gson.fromJson(argsStrWrong, JsonObject.class);
        trialValidator = new TrialValidator<>(Trial.class);
        emptyTrial = new Trial();
        rightTrial = new Trial("Right", 97, 85);
    }

    @After
    public void tearDown() {
        argsCorrect = null;
        argsWrong = null;
        trialValidator = null;
        emptyTrial = null;
        rightTrial = null;
    }


    @Test
    public void testGetValidTrial() {
        Assert.assertEquals(rightTrial.getAccount()
                , trialValidator.getValidTrial(argsCorrect).get().getAccount());
    }

    @Test
    public void testCheckArgsAndSetFields() {
        Assert.assertTrue(trialValidator.checkArgsAndSetFields(argsCorrect));
    }

    @Test
    public void testCheckArgsAndSetFieldsWrong() {
        trialValidator.checkArgsAndSetFields(argsWrong);
    }

    @Test
    public void testGetTrial() {
        Assert.assertEquals(emptyTrial.getClass(), trialValidator.getTrial().getClass());
    }

    @Test
    public void testIsJsonElementValid() {
        Assert.assertTrue(TrialValidator.isJsonElementValid(argsCorrect.get(Constants.ACCOUNT_FIELD)));
    }

    @Test
    public void testIsMarkValid() {
        Assert.assertTrue(TrialValidator.isMarkValid(15));
    }
}