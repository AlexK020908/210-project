package model.investment;

import model.CookGroupSubscriptionEntry;

import static model.EntryType.CookGroup;

//this class focuses on the cook group purchases and put them in a list, it extends the generic class SupportEntryList
public class CookGroupPurchaseList extends SupportEntryList<CookGroupSubscriptionEntry> {

    public CookGroupPurchaseList() {
        super(CookGroup);
    }

}
