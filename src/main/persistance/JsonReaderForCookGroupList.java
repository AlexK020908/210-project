package persistance;

import model.CookGroupSubscriptionEntry;
import model.investment.CookGroupPurchaseList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//This class represents a Json Reader for cook group purchase list
// This [class] references code from GitHub
// Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
public class JsonReaderForCookGroupList {
    private String source;

    //EFFECT: constructs reader to read from source file
    public JsonReaderForCookGroupList(String source) {
        this.source = source;
    }

    // EFFECTS: reads cook group entry list from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public CookGroupPurchaseList read() throws IOException {
        String cookGroupEntryListString = readFile(source);  //reading the file into a string
        JSONObject cookGroupEntryListJsonObject =
                new JSONObject(cookGroupEntryListString); // turning the string into a json object
        return parseCookGroupList(cookGroupEntryListJsonObject); //parsing the object
    }

    //EFFECT: parse the cook group Json object into a readable cook group list
    private CookGroupPurchaseList parseCookGroupList(JSONObject cookGroupEntryListJsonObject)  {
        //parameter is the String of all of our cook group entries
        CookGroupPurchaseList cookGroupPurchaseList = new CookGroupPurchaseList();
        addEntries(cookGroupPurchaseList, cookGroupEntryListJsonObject);
        return cookGroupPurchaseList;
    }

    //MODIFIES: cookGroupPurchaseList
    //EFFECT: add each cook group entry in the  cookGroupEntryListJsonObject
    // to an empty cook group entry list.
    private void addEntries(CookGroupPurchaseList cookGroupPurchaseList, JSONObject cookGroupEntryListJsonObject) {
        //the object here is our string of list
        JSONArray jsonArray = cookGroupEntryListJsonObject.getJSONArray("Cook Group List");
        //we are turning the proxies into an array
        //we are getting the array of the string of proxies
        for (Object json : jsonArray) {
            JSONObject nextCookGroup = (JSONObject) json;
            addEntry(cookGroupPurchaseList, nextCookGroup);
        }
    }

    //MODIFIES:  cookGroupPurchaseList
    //EFFECT; add the next cook group entry (JSON object) to the cook group purchase entry list
    private void addEntry(CookGroupPurchaseList cookGroupPurchaseList, JSONObject nextCookGroup) {
        String name = nextCookGroup.getString("name");
        //for every single proxy entry we are getting the name and price paid
        double pricePaid = nextCookGroup.getDouble("pricePaid");
        CookGroupSubscriptionEntry nextCookGroupSubscriptionEntry = new CookGroupSubscriptionEntry(name, pricePaid);
        //making the instance
        cookGroupPurchaseList.addEntry(nextCookGroupSubscriptionEntry); //adding it to the purchase list
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws  IOException {
        StringBuilder builder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> builder.append(s));
        }

        return builder.toString();
    }
}
