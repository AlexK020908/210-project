package model.investment;

import model.EntryType;
import model.ThirdPartySolverEntry;

//this class focuses on the Third Party Captcha Solvers and put them in a list, this class extends  supportEntryList
public class ThirdPartyCaptchaSolversPurchaseList extends SupportEntryList<ThirdPartySolverEntry> {
   
    public ThirdPartyCaptchaSolversPurchaseList() {
        super(EntryType.ThirdPartSolver);
    }

}
