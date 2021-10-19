package persistance;

import model.SupportEntry;
import model.investment.ProxyPurchaseList;
import model.investment.SupportEntryList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonWriterForSupportEntries {
    protected final int tab = 4;
    protected PrintWriter writer;
    protected String destination;


    //constructor --> a writer that writes to destination
    public JsonWriterForSupportEntries(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this
    //EFFECT: open the writer --> make a new writer of the destination file
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    public void write(SupportEntryList<? extends SupportEntry> purchaseList) {
        JSONObject list = purchaseList.toJson();
        writer.print(list.toString(tab));
    }

    //closing the writer
    public void close() {
        writer.close();

    }





}
