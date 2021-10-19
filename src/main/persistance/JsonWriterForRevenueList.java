package persistance;

import model.RevenueList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class JsonWriterForRevenueList {
    private final int tab = 4;
    private PrintWriter writer;
    private String destination;

    public JsonWriterForRevenueList(String destination) {
        this.destination = destination;
    }

    //open the writer
    public void open() throws FileNotFoundException {
        //initialize the writer to a file of destination
        writer = new PrintWriter(new File(destination));
    }


    //write on destination file
    public void write(RevenueList revenueList) {
        //turn the revenue List into a json object
        //then write revenue list as to array on the destination
        JSONObject jsonObject = revenueList.toJson();
        writer.print(jsonObject.toString(tab));

    }

    //close writer
    public void close() {
        writer.close();
    }



}
