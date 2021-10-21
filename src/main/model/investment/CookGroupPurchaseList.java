package model.investment;

import model.CookGroupSubscriptionEntry;
import model.EntryType;
import model.SupportEntry;
import org.json.JSONArray;
import org.json.JSONObject;

import static model.EntryType.CookGroup;

//this class focuses on the cook group purchases and put them in a list, it extends the generic class SupportEntryList
public class CookGroupPurchaseList extends SupportEntryList<CookGroupSubscriptionEntry> {

    public CookGroupPurchaseList() {
        super(CookGroup);
    }

    @Override
    //EFFECT: returns the cook group purchase list as a Json object
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", CookGroup);
        jsonObject.put("Cook Group List", cookGroupListToJson());
        return jsonObject;
    }

    //EFFECT: returns the cook group purchase list as a json array
    private JSONArray cookGroupListToJson() {
        JSONArray array = new JSONArray();
        for (CookGroupSubscriptionEntry next : typePurchaseList) {
            array.put(next.toJson());
        }
        return array;
    }
}
