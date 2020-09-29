package by.epam.inner.deserializers;

import by.epam.inner.beans.ExtraTrial;
import by.epam.inner.beans.LightTrial;
import by.epam.inner.beans.StrongTrial;
import by.epam.inner.beans.Trial;
import by.epam.inner.validators.ExtraTrialValidator;
import by.epam.inner.validators.TrialValidator;
import com.google.gson.*;
import static by.epam.inner.constants.Constants.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Optional;

public class TrialDeserializer implements JsonDeserializer<Trial> {

    private enum TrialKind {
            TRIAL(getTrialValidator(Trial.class).orElseThrow(IllegalArgumentException::new)),
            LIGHTTRIAL(getTrialValidator(LightTrial.class).orElseThrow(IllegalArgumentException::new)),
            STRONGTRIAL(getTrialValidator(StrongTrial.class).orElseThrow(IllegalArgumentException::new)),  //new TrialValidator<StrongTrial>(StrongTrial.class)
            EXTRATRIAL(getTrialValidator(ExtraTrial.class).orElseThrow(IllegalArgumentException::new));

        private final TrialValidator<? extends Trial> validator;

        TrialKind(TrialValidator<? extends Trial> validator) {
                this.validator = validator;
        }

        Trial getTrial(JsonElement element) {
            return validator.getValidTrial(element).orElseThrow(() -> new IllegalArgumentException(WRONG_TRIAL));
        }
    }


    @Override
    public Trial deserialize(JsonElement element,
                             Type type,
                             JsonDeserializationContext context) throws JsonParseException {

        String[] fullClassName = type.getTypeName().split("\\.");
        String trialKind = fullClassName[fullClassName.length - 1].toUpperCase();
        /*JsonObject jsonObject = element.getAsJsonObject();
        String trialKind = jsonObject.get(CLASS_FIELD).getAsString().toUpperCase();*/
        return TrialKind.valueOf(trialKind).getTrial(element);
    }

    private static Optional<TrialValidator<? extends Trial>> getTrialValidator(Class<? extends Trial> trialClass) {
        try {
            if (!trialClass.getSimpleName().equals(EXTRA_TRIAL_NAME)) {
                return Optional.of((TrialValidator<? extends Trial>) TrialValidator.class.getConstructor(Class.class).newInstance(trialClass));
            } else {
                return  Optional.of((TrialValidator<? extends Trial>) ExtraTrialValidator.class.getConstructor(Class.class).newInstance(trialClass));
            }

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            return Optional.empty();
        }
    }
}
