package by.epam.inner.factories;

import by.epam.inner.beans.ExtraTrial;
import by.epam.inner.beans.LightTrial;
import by.epam.inner.beans.StrongTrial;
import by.epam.inner.beans.Trial;
import com.google.gson.JsonObject;

import java.util.Optional;

public class TrialFactory {
    public static Optional<Trial> getTrialFromFactory(JsonObject jsonObject) {
        String className = jsonObject.get("class").getAsString();
        JsonObject trialJSON = jsonObject.get("args").getAsJsonObject();
        String account = trialJSON.get("account").getAsString();
        int mark1 = trialJSON.get("mark1").getAsInt();
        int mark2 = trialJSON.get("mark2").getAsInt();

        if (account.equals("")) {
            return Optional.empty();
        }

        if (!isMarkValid(mark1)) {
            return Optional.empty();
        }

        if (!isMarkValid(mark2)) {
            return Optional.empty();
        }

        switch (className) {
            case "Trial":
                return Optional.of(new Trial(account, mark1, mark2));
            case "LightTrial":
                return  Optional.of(new LightTrial(account, mark1, mark2));
            case "StrongTrial":
                return Optional.of(new StrongTrial(account, mark1, mark2));
            case "ExtraTrial":
                int mark3 = trialJSON.get("mark3").getAsInt();
                if (!isMarkValid(mark3)) {
                    return Optional.empty();
                }

                return Optional.of(new ExtraTrial(account, mark1, mark2, mark3));
            default:
                return Optional.empty();

        }
    }

    private static boolean isMarkValid(int mark){
        return mark >= 0 && mark <= 100;
    }
}
