package persistance;

import model.AmountException;
import model.Revenue;
import model.RevenueList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// Represents a reader that reads the sneaker entry list from JSON data stored in file
// This [class] references code from GitHub
// Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
public class JsonReaderForRevenueList {
    private String source;

    //EFFECT: constructs reader to read from source file
    public JsonReaderForRevenueList(String source) {
        this.source = source;
    }

    // EFFECTS: reads revenue list from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public RevenueList read() throws IOException, AmountException {
        //first turn the file into a stream of string
        String revenueStream = readFile(source);
        //then turn the string into json object
        JSONObject jsonObjectRevenues = new JSONObject(revenueStream);
        //then parse the object into meaningful components
        return parseJsonObject(jsonObjectRevenues);

    }

    //EFFECT: parse the revenue list Json object into a readable revenue list
    private RevenueList parseJsonObject(JSONObject jsonObjectRevenues) throws AmountException {
        RevenueList revenueList = new RevenueList();
        addEntries(revenueList, jsonObjectRevenues);
        return revenueList;
    }

    //MODIFIES: revenueList
    //EFFECT: add each revenue entry in the  jsonObjectRevenues
    // to an empty revenueList.
    private RevenueList addEntries(RevenueList revenueList, JSONObject jsonObjectRevenues) throws AmountException {
        JSONArray revenuesJsonObjectInArray = jsonObjectRevenues.getJSONArray("revenues");

        for (Object revenue : revenuesJsonObjectInArray) {
            JSONObject jsonObjectRevenue = (JSONObject) revenue;
            addEntry(jsonObjectRevenue, revenueList);
        }
        return revenueList;
    }

    //MODIFIES:  revenueList
    //EFFECT; add the next revenue entry (JSON object) to the revenueList
    private void addEntry(JSONObject jsonObjectRevenue, RevenueList revenueList) throws AmountException {
        double revenue = jsonObjectRevenue.getDouble("revenue");
        Revenue newRevenue = new Revenue(revenue);
        revenueList.addNewRevenue(newRevenue);
    }

    //EFFECT: reads source file for revenue list as a single string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> builder.append(s));
        }
        return builder.toString();
    }


}
