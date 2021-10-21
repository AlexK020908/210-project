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
    //EFFECT: return the Third Party purchase list as a Json object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("type", EntryType.ThirdPartSolver);
        json.put("solvers", solversListToJson());
        return json;

    }

    //EFFECT: return the Third Party purchase list as a Json array
    private JSONArray solversListToJson() {
        JSONArray jsonArray = new JSONArray();
        for (ThirdPartyCaptchaSolverEntry next : typePurchaseList) {
            jsonArray.put(next.toJson());
        }
        return jsonArray;
    }
}
