package by.epam.inner.validators;

import by.epam.inner.beans.Trial;

import java.lang.reflect.InvocationTargetException;

public class LightTrialValidator extends TrialValidator {
    public LightTrialValidator(Class<? extends Trial> trialClass) {
        super(trialClass);
    }
}
