package persistance;

import model.RevenueList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

//Represents a Json writer for revenue List that writes it to file.
// This [class] references code from GitHub
// Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
public class JsonWriterForRevenueList {
    private final int tab = 4;
    private PrintWriter writer;
    private String destination;

    //EFFECT: constructs a Json writer for Revenue list at the given destination
    public JsonWriterForRevenueList(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this
    //EFFECT: opens the writer for revenue list at the given destination
    //        throws fileNotFoundException if file can not be opened
    public void open() throws FileNotFoundException {
        //initialize the writer to a file of destination
        writer = new PrintWriter(new File(destination));
    }


    //MODIFIES: this
    //EFFECT: write the Json representation of revenueList to the file at given location.
    public void write(RevenueList revenueList) {
        //turn the revenue List into a json object
        //then write revenue list as to array on the destination
        JSONObject jsonObject = revenueList.toJson();
        writer.print(jsonObject.toString(tab));

    }

    //MODIFIES: this
    //EFFECT: closes the writer
    public void close() {
        writer.close();
    }



}
