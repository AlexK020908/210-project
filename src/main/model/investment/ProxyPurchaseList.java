package model.investment;

import model.EntryType;
import model.ProxyEntry;

//this class focuses on the proxy purchases and put them in a list, this class extends the support entry  Generic class
public class ProxyPurchaseList extends SupportEntryList<ProxyEntry> {

    //EFFECT: initialize an empty proxy purchase linked list
    public ProxyPurchaseList() {
        super(EntryType.PROXY);
    }


}

