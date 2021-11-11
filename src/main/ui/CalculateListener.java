package ui;

import model.RevenueList;
import model.investment.CookGroupPurchaseList;
import model.investment.ProxyPurchaseList;
import model.investment.SneakerPurchaseList;
import model.investment.ThirdPartyCaptchaSolversPurchaseList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//This class represents an action listener for the calculate button
public class CalculateListener implements ActionListener {
    CookGroupPurchaseList cookGroupPurchaseList;
    ProxyPurchaseList proxyPurchaseList;
    ThirdPartyCaptchaSolversPurchaseList thirdPartyCaptchaSolversPurchaseList;
    SneakerPurchaseList sneakerPurchaseList;
    RevenueList revenueList;

    //EFFECT: initlaize a calculate listener constructor
    public CalculateListener(CookGroupPurchaseList cookGroupPurchaseList, ProxyPurchaseList proxyPurchaseList,
                             ThirdPartyCaptchaSolversPurchaseList thirdPartyCaptchaSolversPurchaseList,
                             SneakerPurchaseList sneakerPurchaseList, RevenueList revenueList) {
        this.cookGroupPurchaseList = cookGroupPurchaseList;
        this.proxyPurchaseList = proxyPurchaseList;
        this.thirdPartyCaptchaSolversPurchaseList = thirdPartyCaptchaSolversPurchaseList;
        this.sneakerPurchaseList = sneakerPurchaseList;
        this.revenueList = revenueList;

    }

    //MODIFIES: this
    //EFFECT: if total money spent is greater than revenue, prompts the user with the notifcation that he/she is losing
    // money and also specify how much they are losing
    //        if total money spent = revenue, notify the user that they are breaking even
    //        If total money < revenue , notify the user that they are making positive income, and also how much
    //         MONEY they are making
    @Override
    public void actionPerformed(ActionEvent e) {
        double moneySpentOnCookGroups = cookGroupPurchaseList.getTotalMoneySpent();
        double moneySpentOnSneakers = sneakerPurchaseList.getTotalMoneySpent();
        double moneySpentOnProxies = proxyPurchaseList.getTotalMoneySpent();
        double moneySpentOnThirdPartCaptchaSolvers = thirdPartyCaptchaSolversPurchaseList.getTotalMoneySpent();
        double revenueMadeInTotal = revenueList.calculateTotalRevenue();
        double moneySpentTotal = moneySpentOnCookGroups
                + moneySpentOnSneakers + moneySpentOnProxies
                + moneySpentOnThirdPartCaptchaSolvers;

        if (moneySpentTotal > revenueMadeInTotal) {
            JOptionPane.showMessageDialog(null,
                    "you are money" + "you are in a net negative profit of " + " "
                            + (moneySpentTotal - revenueMadeInTotal));
        } else if (moneySpentTotal < revenueMadeInTotal) {
            JOptionPane.showMessageDialog(null, "you are making a net positive profit of" + " "
                    + (revenueMadeInTotal - moneySpentTotal));

        } else if (moneySpentTotal == revenueMadeInTotal) {
            JOptionPane.showMessageDialog(null, "you are breaking even, making a net prorit "
                    + "0 dollars");
        }


    }

}

