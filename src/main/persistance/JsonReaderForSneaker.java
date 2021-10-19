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

public class JsonReaderForSneaker {
    //you are reading it from an existing detination or source

    private String source;

    public JsonReaderForSneaker(String source) {
        this.source = source;
    }

    //read a file and returning it
    public SneakerPurchaseList read() throws IOException {
        String sneakerList = readFile(source);
        //after reading the file into a string containing all the list, we need to turn it into  JSON
        JSONObject sneakerEntryListJsonObject = new JSONObject(sneakerList);
        //now we need to parse the object
        return parseSneakerJsonObject(sneakerEntryListJsonObject);
    }

    private SneakerPurchaseList parseSneakerJsonObject(JSONObject sneakerEntryListJsonObject) {
        //in order to parse we need to create a new sneaker purchase list that we are putting the object in
        SneakerPurchaseList sneakerPurchaseList = new SneakerPurchaseList();
        addSneakerEntries(sneakerPurchaseList, sneakerEntryListJsonObject);
        return sneakerPurchaseList;
    }

    private SneakerPurchaseList addSneakerEntries(SneakerPurchaseList sneakerPurchaseList, JSONObject sneakerEntryListJsonObject) {
        JSONArray listOfSneakerEntriesArray = sneakerEntryListJsonObject.getJSONArray("sneakers");
        for (Object next : listOfSneakerEntriesArray) {
            JSONObject nextSneaker = (JSONObject) next;
            addSneakerEntry(nextSneaker, sneakerPurchaseList);
        }
        return sneakerPurchaseList;
    }

    private void addSneakerEntry(JSONObject nextSneaker, SneakerPurchaseList sneakerPurchaseList) {
        String name = nextSneaker.getString("name");
        Double price = nextSneaker.getDouble("retailPrice");
        int quantityBought = nextSneaker.getInt("quantity");
        SneakerEntry sneakerEntry = new SneakerEntry(name, price, quantityBought);
        sneakerPurchaseList.addEntry(sneakerEntry);
    }


    private String readFile(String source) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> builder.append(s));
        }
        return builder.toString();

    }
}
