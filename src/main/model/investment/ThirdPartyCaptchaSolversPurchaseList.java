package model.investment;

import model.EntryType;
import model.SupportEntry;
import model.ThirdPartyCaptchaSolverEntry;
import org.json.JSONArray;
import org.json.JSONObject;

//this class focuses on the Third Party Captcha Solvers and put them in a list, this class extends  supportEntryList
public class ThirdPartyCaptchaSolversPurchaseList extends SupportEntryList<ThirdPartyCaptchaSolverEntry> {
   
    public ThirdPartyCaptchaSolversPurchaseList() {
        super(EntryType.ThirdPartSolver);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("type", EntryType.ThirdPartSolver);
        json.put("Third Party Captcha Solver", solversListToJson());
        return json;

    }

    private JSONArray solversListToJson() {
        JSONArray jsonArray = new JSONArray();
        for (SupportEntry next : typePurchaseList) {
            jsonArray.put(next.toJson());
        }
        return jsonArray;
    }
}
