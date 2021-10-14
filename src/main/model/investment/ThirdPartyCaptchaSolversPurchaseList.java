package model.investment;

import model.ThirdPartySolverEntry;

import java.util.List;

//this class focuses on the Third Party Captcha Solvers and put them in a list
public class ThirdPartyCaptchaSolversPurchaseList extends SupportEntryList<ThirdPartySolverEntry> {
   
    public ThirdPartyCaptchaSolversPurchaseList() {
        super(EntryType.ThirdPartSolver);
    }

}
