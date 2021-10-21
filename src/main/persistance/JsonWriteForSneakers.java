package persistance;

import model.investment.SneakerPurchaseList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//Represents a writer that writes JSON representation of sneaker entry list to file
public class JsonWriteForSneakers {
    private final int tab = 4;
    private PrintWriter writer;
    private String destination;

    //EFFECT: Constructs a writer for sneaker investment entries with a destination.
    public JsonWriteForSneakers(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this
    //EFFECT: opens the writer for sneaker entry list at the given destination
    //        throws fileNotFoundException if file can not be opened
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }


    //MODIFIES: this
    //EFFECT: WRITES json representation of sneaker purchase at the given file ocation
    public void write(SneakerPurchaseList purchaseList) {
        //turn the purchase list into a json object
        JSONObject sneakerPurchaseListObject = purchaseList.toJson();
        //write it to a file
        writer.print(sneakerPurchaseListObject.toString(tab));

    }

    //MODIFIES: this
    //EFFECT: closes the writer
    public void close() {
        writer.close();
    }

}

