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

public class JsonReaderForCookGroup {
    private String source;

    //EFFECT: constructs reader to read from source file
    public JsonReaderForCookGroup(String source) {
        this.source = source;
    }

    // EFFECTS: reads purchase list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CookGroupPurchaseList read() throws IOException {
        String jsonData = readFile(source);  //reading the file into a string
        JSONObject jsonObject = new JSONObject(jsonData); // turning the string into a json object
        return parseCookGroupList(jsonObject); //parsing the object
    }

    //parsing means breaking one string into meaning ful parts, like what parsa did with my credit card info
    private CookGroupPurchaseList parseCookGroupList(JSONObject jsonObject)  {
        //parameter is the String of all of our proxy
        CookGroupPurchaseList cookGroupPurchaseList = new CookGroupPurchaseList();
        addEntries(cookGroupPurchaseList, jsonObject);
        return cookGroupPurchaseList;
    }

    private void addEntries(CookGroupPurchaseList cookGroupPurchaseList, JSONObject jsonObject) {
        //the object here is our string of list
        JSONArray jsonArray = jsonObject.getJSONArray("Cook Group List"); //we are turning the proxies into an array
        //we are getting the array of the string of proxies
        for (Object json : jsonArray) {
            JSONObject nextCookGroup = (JSONObject) json;
            addEntry(cookGroupPurchaseList, nextCookGroup);
            //for every json object in the proxy array, we are adding the each object to the proxyPurchaseList
        }
    }

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
