package persistance;

import model.investment.ProxyPurchaseList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonWriterForProxy {
    private final int tab = 4;
    private PrintWriter writer;
    private String destination;


    //constructor --> a writer that writes to destination
    public JsonWriterForProxy(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this
    //EFFECT: open the writer --> make a new writer of the destination file
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }


    //write json of proxy to file
    public void write(ProxyPurchaseList ppl) {
        JSONObject proxyList = ppl.toJson();
        writer.print(proxyList.toString(tab));
    }



    //closing the writer
    public void close() {
        writer.close();
    }


}
