package model.investment;

import model.CookGroupSubscriptionEntry;

import java.util.List;
import java.util.Map;

import static model.investment.EntryType.CookGroup;

//this class focuses on the cook group purchases and put them in a list
public class CookGroupPurchaseList extends SupportEntryList<CookGroupSubscriptionEntry> {

    public CookGroupPurchaseList() {
        super(CookGroup);
    }

}
