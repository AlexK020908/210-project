package persistance;

import model.SneakerEntry;
import model.investment.SneakerPurchaseList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads the sneaker entry list from JSON data stored in file
public class JsonReaderForSneaker {
    private String source;

    //EFFECT: constructs reader to read from source file
    public JsonReaderForSneaker(String source) {
        this.source = source;
    }

    // EFFECTS: reads sneaker entry list from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public SneakerPurchaseList read() throws IOException {
        String sneakerList = readFile(source);
        //after reading the file into a string containing all the list, we need to turn it into  JSON
        JSONObject sneakerEntryListJsonObject = new JSONObject(sneakerList);
        //now we need to parse the object
        return parseSneakerJsonObject(sneakerEntryListJsonObject);
    }

    //EFFECT: parse the sneaker list Json object into a readable sneaker purchase entry list
    private SneakerPurchaseList parseSneakerJsonObject(JSONObject sneakerEntryListJsonObject) {
        //in order to parse we need to create a new sneaker purchase list that we are putting the object in
        SneakerPurchaseList sneakerPurchaseList = new SneakerPurchaseList();
        addSneakerEntries(sneakerPurchaseList, sneakerEntryListJsonObject);
        return sneakerPurchaseList;
    }

    //MODIFIES: sneakerPurchaseList
    //EFFECT: add each sneaker entry in the  sneakerEntryListJsonObject entry
    // to an empty sneaker purchase list.
    private SneakerPurchaseList addSneakerEntries(
            SneakerPurchaseList sneakerPurchaseList, JSONObject sneakerEntryListJsonObject) {
        JSONArray listOfSneakerEntriesArray = sneakerEntryListJsonObject.getJSONArray("sneakers");
        for (Object next : listOfSneakerEntriesArray) {
            JSONObject nextSneaker = (JSONObject) next;
            addSneakerEntry(nextSneaker, sneakerPurchaseList);
        }
        return sneakerPurchaseList;
    }

    //MODIFIES:  SneakerPurchaseList
    //EFFECT; add the next sneaker entry (JSON object) to the thirdPartyCaptchaSolversPurchaseList
    private void addSneakerEntry(JSONObject nextSneaker, SneakerPurchaseList sneakerPurchaseList) {
        String name = nextSneaker.getString("name");
        double price = nextSneaker.getDouble("retailPrice");
        int quantityBought = nextSneaker.getInt("quantity");
        SneakerEntry sneakerEntry = new SneakerEntry(name, price, quantityBought);
        sneakerPurchaseList.addEntry(sneakerEntry);
    }


    // EFFECTS: reads source file for sneaker entry list as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> builder.append(s));
        }
        return builder.toString();

    }
}
