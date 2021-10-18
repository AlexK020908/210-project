package persistance;

import org.json.JSONObject;

import javax.swing.*;

public interface Writable {
    //effectL returns this as JSON object
    JSONObject toJson();
}
