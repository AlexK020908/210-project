package persistance;

import model.Revenue;
import model.RevenueList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReaderForRevenueList {
    private String source;

    public JsonReaderForRevenueList(String source) {
        this.source = source;
    }

    public RevenueList read() throws IOException {
        //first turn the file into a stream of string
        String revenueStream = readFile(source);
        //then turn the string into json object
        JSONObject jsonObjectRevenues = new JSONObject(revenueStream);
        //then parse the object into meaningful components
        return parseJsonObject(jsonObjectRevenues);

    }

    private RevenueList parseJsonObject(JSONObject jsonObjectRevenues) {
        RevenueList revenueList = new RevenueList();
        addEntries(revenueList, jsonObjectRevenues);
        return revenueList;
    }

    private RevenueList addEntries(RevenueList revenueList, JSONObject jsonObjectRevenues) {
        JSONArray revenuesJsonObjectInArray = jsonObjectRevenues.getJSONArray("revenues");

        for (Object revenue : revenuesJsonObjectInArray) {
            JSONObject jsonObjectRevenue = (JSONObject) revenue;
            addEntry(jsonObjectRevenue, revenueList);
        }
        return revenueList;
    }

    private void addEntry(JSONObject jsonObjectRevenue, RevenueList revenueList) {
        double revenue = jsonObjectRevenue.getDouble("revenue");
        Revenue newRevenue = new Revenue(revenue);
        revenueList.addNewRevenue(newRevenue);
    }

    private String readFile(String source) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> builder.append(s));
        }
        return builder.toString();
    }


}
