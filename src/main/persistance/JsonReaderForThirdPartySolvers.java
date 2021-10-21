package persistance;

import model.ThirdPartyCaptchaSolverEntry;
import model.investment.ThirdPartyCaptchaSolversPurchaseList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads the Third part solver list from JSON data stored in file
public class JsonReaderForThirdPartySolvers {
    private String source;

    //EFFECT: constructs reader to read from source file
    public JsonReaderForThirdPartySolvers(String source) {
        this.source = source;
    }

    // EFFECTS: reads Third Party captcha list from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public ThirdPartyCaptchaSolversPurchaseList read() throws IOException {
        String thirdPartySolverListData = readFile(source);  //reading the file into a string
        JSONObject thirdPartySolverListJsonObject = new JSONObject(thirdPartySolverListData);
        // turning the string into a json object
        return parseProxyList(thirdPartySolverListJsonObject); //parsing the object
    }

    //EFFECT: parse the third party captcha Json object into a readable third part captcha solver list
    private ThirdPartyCaptchaSolversPurchaseList parseProxyList(JSONObject thirdPartySolverListJsonObject)  {
        //parameter is the String of all of our proxy
        ThirdPartyCaptchaSolversPurchaseList thirdPartyCaptchaSolversPurchaseList =
                new ThirdPartyCaptchaSolversPurchaseList();
        addEntries(thirdPartyCaptchaSolversPurchaseList, thirdPartySolverListJsonObject);
        return thirdPartyCaptchaSolversPurchaseList;
    }


    //MODIFIES: thirdPartyCaptchaSolversPurchaseList
    //EFFECT: add each third party captcha entry in the thirdPartySolverListJsonObject
    // to an empty third part captcha solvers list
    private void addEntries(ThirdPartyCaptchaSolversPurchaseList thirdPartyCaptchaSolversPurchaseList,
                            JSONObject jsonObject) {
        //the object here is our string of list
        JSONArray jsonArray = jsonObject.getJSONArray("solvers");
        for (Object json : jsonArray) {
            JSONObject nextSolver = (JSONObject) json;
            addEntry(thirdPartyCaptchaSolversPurchaseList, nextSolver);
            //for every json object in the proxy array, we are adding the each object to the proxyPurchaseList
        }
    }

    //MODIFIES:  thirdPartyCaptchaSolversPurchaseList
    //EFFECT; add the next solver (JSON object) to the thirdPartyCaptchaSolversPurchaseList
    private void addEntry(ThirdPartyCaptchaSolversPurchaseList thirdPartyCaptchaSolversPurchaseList,
                          JSONObject nextSolver) {
        String name = nextSolver.getString("name");
        //for every single proxy entry we are getting the name and price paid
        double pricePaid = nextSolver.getDouble("pricePaid");
        ThirdPartyCaptchaSolverEntry solverEntry = new
                ThirdPartyCaptchaSolverEntry(name, pricePaid); //making the instance
        thirdPartyCaptchaSolversPurchaseList.addEntry(solverEntry); //adding it to the purchase list
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
