package model.investment;

import model.EntryType;
import model.ProxyEntry;
import model.SupportEntry;
import org.json.JSONArray;
import org.json.JSONObject;

//this class focuses on the proxy purchases and put them in a list, this class extends the support entry  Generic class
public class ProxyPurchaseList extends SupportEntryList<ProxyEntry> {


    //EFFECT: initialize an empty proxy purchase linked list
    public ProxyPurchaseList() {
        super(EntryType.PROXY);
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("type", EntryType.PROXY);
        json.put("proxies", proxyPurchaseListTojSON());
        return json;

    }

    private JSONArray proxyPurchaseListTojSON() {
        JSONArray jsonArray = new JSONArray();
        for (ProxyEntry next : typePurchaseList) {
            jsonArray.put(next.toJson());
        }
        return jsonArray;
    }
}


