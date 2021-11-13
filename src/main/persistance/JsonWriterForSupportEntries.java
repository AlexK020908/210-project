package persistance;

import model.SupportEntry;
import model.investment.ProxyPurchaseList;
import model.investment.SupportEntryList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//Represents a writer that writes JSON representation of A support entry list to file
// This [class] references code from GitHub
// Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
public class JsonWriterForSupportEntries {
    protected final int tab = 4;
    protected PrintWriter writer;
    protected String destination;


    //EFFECT: Constructs a writer for support investment entries with a destination.
    public JsonWriterForSupportEntries(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this
    //EFFECT: open the writer with for a support entry list at the destination
    //        Throws file not found exception if the destination file can not  be opened.
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    //MODIFIES: this
    //EFFECT: write JSON representation of support list entries to file
    //        support entries include: proxy entries, Cook group entries and Third party solver entries
    public void write(SupportEntryList<? extends SupportEntry> purchaseList) {
        if (purchaseList != null) {
            JSONObject list = purchaseList.toJson();
            writer.print(list.toString(tab));
        }
    }

    //MODIFIES
    //EFFECT: closes the writer
    public void close() {
        writer.close();

    }





}
