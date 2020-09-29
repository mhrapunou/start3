package by.epam.inner.validators;

import by.epam.inner.beans.Trial;

import java.lang.reflect.InvocationTargetException;

public class StrongTrialValidator extends TrialValidator {
    public StrongTrialValidator(Class<? extends Trial> trialClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        super(trialClass);
    }
}
