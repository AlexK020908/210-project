package model.investment;

import model.ProxyEntry;
import model.ProxyType;

import java.util.LinkedList;
import java.util.List;

//this class focuses on the proxy purchases and put them in a list, this class cannot extend the Investment Generic
//class because each proxy entry have a proxy type
public class ProxyPurchaseList extends SupportEntryList<ProxyEntry> {

    //EFFECT: initialize an empty proxy purchase linked list
    public ProxyPurchaseList() {
        super(EntryType.PROXY);
    }


}

