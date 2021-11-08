package ui;

import model.RevenueList;
import model.investment.CookGroupPurchaseList;
import model.investment.ProxyPurchaseList;
import model.investment.SneakerPurchaseList;
import model.investment.ThirdPartyCaptchaSolversPurchaseList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculateListener implements ActionListener {
    CookGroupPurchaseList cookGroupPurchaseList;
    ProxyPurchaseList proxyPurchaseList;
    ThirdPartyCaptchaSolversPurchaseList thirdPartyCaptchaSolversPurchaseList;
    SneakerPurchaseList sneakerPurchaseList;
    RevenueList revenueList;

    public CalculateListener(CookGroupPurchaseList cookGroupPurchaseList, ProxyPurchaseList proxyPurchaseList,
                             ThirdPartyCaptchaSolversPurchaseList thirdPartyCaptchaSolversPurchaseList,
                             SneakerPurchaseList sneakerPurchaseList, RevenueList revenueList) {
        this.cookGroupPurchaseList = cookGroupPurchaseList;
        this.proxyPurchaseList = proxyPurchaseList;
        this.thirdPartyCaptchaSolversPurchaseList = thirdPartyCaptchaSolversPurchaseList;
        this.sneakerPurchaseList = sneakerPurchaseList;
        this.revenueList = revenueList;

    }

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

