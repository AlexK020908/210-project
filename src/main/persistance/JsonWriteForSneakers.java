package persistance;

import model.investment.SneakerPurchaseList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonWriteForSneakers {
    private final int tab = 4;
    private PrintWriter writer;
    private String destination;

    public JsonWriteForSneakers(String destination) {
        this.destination = destination;
    }

    //open the writer
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }


    //Writing the sneaker entry list to a file
    public void write(SneakerPurchaseList purchaseList) {
        //turn the purchase list into a json object
        JSONObject sneakerPurchaseList = purchaseList.toJson();
        //write it to a file
        writer.print(sneakerPurchaseList.toString(tab));

    }

    //close the writer
    public void close() {
        writer.close();
    }

}

