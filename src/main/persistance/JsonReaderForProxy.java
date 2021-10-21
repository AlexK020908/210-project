package persistance;

import model.ProxyEntry;
import model.investment.ProxyPurchaseList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// Represents a reader that reads the proxy entry list from JSON data stored in file
public class JsonReaderForProxy {
    private String source;

    //EFFECT: constructs reader to read from source file
    public JsonReaderForProxy(String source) {
        this.source = source;
    }


    // EFFECTS: reads proxy list from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public ProxyPurchaseList read() throws IOException {
        String proxyListData = readFile(source);  //reading the file into a string
        JSONObject proxyListDataJsonObject = new JSONObject(proxyListData);
        // turning the string into a json object
        return parseProxyList(proxyListDataJsonObject); //parsing the object
    }

    //EFFECT: parse the proxy list Json object into a readable proxy list
    private ProxyPurchaseList parseProxyList(JSONObject proxyListDataJsonObject)  {
        //parameter is the String of all of our proxy
        ProxyPurchaseList proxyPurchaseList = new ProxyPurchaseList();
        addEntries(proxyPurchaseList, proxyListDataJsonObject);
        return proxyPurchaseList;
    }

    //MODIFIES: proxyPurchaseList
    //EFFECT: add each proxy entry in the  proxyListDataJsonObject
    // to an empty revenueList.
    private void addEntries(ProxyPurchaseList proxyPurchaseList, JSONObject proxyListDataJsonObject) {
        //the object here is our string of list
        JSONArray jsonArray = proxyListDataJsonObject.getJSONArray(
                "proxies"); //we are turning the proxies into an array
        //we are getting the array of the string of proxies
        for (Object json : jsonArray) {
            JSONObject nextProxy = (JSONObject) json;
            addEntry(proxyPurchaseList, nextProxy);
            //for every json object in the proxy array, we are adding the each object to the proxyPurchaseList
        }
    }

    //MODIFIES:  proxyPurchaseList
    //EFFECT; add the next proxy entry (JSON object) to the proxy entry list
    private void addEntry(ProxyPurchaseList proxyPurchaseList, JSONObject nextProxy) {
        String name = nextProxy.getString("name");
        //for every single proxy entry we are getting the name and price paid
        double pricePaid = nextProxy.getDouble("pricePaid");
        ProxyEntry proxyEntry = new ProxyEntry(name, pricePaid);
        //making the instance
        proxyPurchaseList.addEntry(proxyEntry); //adding it to the purchase list
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> builder.append(s));
        }

        return builder.toString();
    }

}



