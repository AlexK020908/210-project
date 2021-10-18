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

public class JsonReaderForProxy {
    private String source;

    //EFFECT: constructs reader to read from source file
    public JsonReaderForProxy(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ProxyPurchaseList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkRoom(jsonObject);
    }

    //parsing means breaking one string into more easil processed components
    private ProxyPurchaseList parseWorkRoom(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ProxyPurchaseList ppl = new ProxyPurchaseList();
        addEntries(ppl, jsonObject);
        return ppl;
    }

    private void addEntries(ProxyPurchaseList ppl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("proxies");
        for (Object json : jsonArray) {
            JSONObject nextProxy = (JSONObject) json;
            addEntry(ppl, nextProxy);
        }
    }

    private void addEntry(ProxyPurchaseList ppl, JSONObject nextProxy) {
        String name = nextProxy.getString("name");
        double pricePaid = nextProxy.getDouble("pricePaid");
        ProxyEntry proxyEntry = new ProxyEntry(name, pricePaid);
        ppl.addEntry(proxyEntry);
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


