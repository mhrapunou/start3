package by.epam.inner.deserializers;

import by.epam.inner.beans.ExtraTrial;
import by.epam.inner.beans.LightTrial;
import by.epam.inner.beans.StrongTrial;
import by.epam.inner.beans.Trial;
import by.epam.inner.validators.ExtraTrialValidator;
import by.epam.inner.validators.TrialValidator;
import com.google.gson.*;
import static by.epam.inner.constants.Constants.*;

import java.lang.reflect.Type;

public class TrialDeserializer implements JsonDeserializer<Trial> {

    private enum TrialKind {
            TRIAL(new TrialValidator<Trial>(Trial.class)),
            LIGHTTRIAL(new TrialValidator<LightTrial>(LightTrial.class)),
            STRONGTRIAL(new TrialValidator<StrongTrial>(StrongTrial.class)),
            EXTRATRIAL(new ExtraTrialValidator<ExtraTrial>(ExtraTrial.class));

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
}
