package persistance;

import org.json.JSONObject;

import javax.swing.*;

//Interface Writable to convert objects to a Json object
public interface Writable {
    //effectL returns this as JSON object
    JSONObject toJson();
}
