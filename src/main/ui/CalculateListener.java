package ui;

import model.RevenueList;
import model.investment.CookGroupPurchaseList;
import model.investment.ProxyPurchaseList;
import model.investment.SneakerPurchaseList;
import model.investment.ThirdPartyCaptchaSolversPurchaseList;

import javax.swing.*;
import java.awt.*;
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

            promtpUserWithResult("UH OH!","./data/losing.png",
                    "you are losing money" + ", " + "you are in a net negative profit of " + " "
                            + (moneySpentTotal - revenueMadeInTotal));

        } else if (moneySpentTotal < revenueMadeInTotal) {

            promtpUserWithResult("WOW!","./data/stonks.jpg",
                    "you are making a net positive profit of" + " "
                            + (revenueMadeInTotal - moneySpentTotal));


        } else if (moneySpentTotal == revenueMadeInTotal) {

            promtpUserWithResult("balancing","./data/balance.png",
                    "you are breaking even, making a net profit "
                            + "0 dollars");

        }


    }

    //EFFECT: prompts user with pop up window that shows them how much money they are making or losing
    //        corresponding to an image.
    private void promtpUserWithResult(String title,
                                      String filePath, String message) {
        ImageIcon stonks = new ImageIcon(filePath); //by using get class, you are telling program
        Image imageResized = stonks.getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT);
        //to search for image from the starting class, it can not be traced back
        ImageIcon resizedIcon = new ImageIcon(imageResized, title);

        JOptionPane.showMessageDialog(null,
                message, title,
                JOptionPane.WARNING_MESSAGE,
                resizedIcon);
    }

}

