package utilities;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonNode;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestDataReader {
    private static final String FILE_PATH = "src/test/resources/loginData.json";

    public static JsonObject getLoginData() throws FileNotFoundException {
        Gson gson = new Gson();
            return gson.fromJson(new FileReader(FILE_PATH), JsonObject.class);

    }
    public static String getDataValue(String key) throws IOException {
        JsonElement data = getLoginData().get(key);
        if (data != null) {
            return data.getAsString();
        } else {
            throw new IOException("Key '" + key + "' not found in login data");
        }
    }
    public static String getFormattedDate(String key, String inputPattern, String outputPattern) throws IOException {
        String dateValue = getDataValue(key);

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(inputPattern);
        LocalDate date = LocalDate.parse(dateValue, inputFormatter);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(outputPattern);
        return date.format(outputFormatter);
    }
}
