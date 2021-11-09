package ui;

import model.RevenueList;
import model.investment.*;
import persistance.JsonWriteForSneakers;
import persistance.JsonWriterForRevenueList;
import persistance.JsonWriterForSupportEntries;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

public class SaveActionListener implements ActionListener {

    private ProxyPurchaseList proxyPurchaseList;
    private CookGroupPurchaseList cookGroupPurchaseList;
    private ThirdPartyCaptchaSolversPurchaseList thirdPartyCaptchaSolversPurchaseList;
    private SneakerPurchaseList sneakerPurchaseList;
    private RevenueList revenueList;
    private JsonWriterForSupportEntries jsonWriterForProxyEntries;
    private JsonWriterForSupportEntries jsonWriterForCookGroupEntries;
    private JsonWriterForSupportEntries jsonWriterForThirdPartySolverEntries;
    private JsonWriteForSneakers jsonWriteForSneakers;
    private JsonWriterForRevenueList jsonWriterForRevenueList;

    public SaveActionListener(ProxyPurchaseList proxyPurchaseList,
                              CookGroupPurchaseList cookGroupPurchaseList,
                              ThirdPartyCaptchaSolversPurchaseList thirdPartyCaptchaSolversPurchaseList,
                              SneakerPurchaseList sneakerPurchaseList, RevenueList revenueList,
                              JsonWriterForSupportEntries jsonWriterForProxyEntries,
                              JsonWriterForSupportEntries jsonWriterForCookGroupEntries,
                              JsonWriterForSupportEntries jsonWriterForThirdPartySolverEntries,
                              JsonWriteForSneakers jsonWriteForSneakers,
                              JsonWriterForRevenueList jsonWriterForRevenueList) {
        this.proxyPurchaseList = proxyPurchaseList;
        this.cookGroupPurchaseList = cookGroupPurchaseList;
        this.thirdPartyCaptchaSolversPurchaseList = thirdPartyCaptchaSolversPurchaseList;
        this.sneakerPurchaseList = sneakerPurchaseList;
        this.revenueList = revenueList;
        this.jsonWriterForProxyEntries = jsonWriterForProxyEntries;
        this.jsonWriterForCookGroupEntries = jsonWriterForCookGroupEntries;
        this.jsonWriterForThirdPartySolverEntries = jsonWriterForThirdPartySolverEntries;
        this.jsonWriteForSneakers = jsonWriteForSneakers;
        this.jsonWriterForRevenueList = jsonWriterForRevenueList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        saveProxyList();
        saveThirdPartySolversList();
        saveCookGroupList();
        saveSneakersList();
        saveRevenueList();

    }

    private void saveSneakersList() {
        try {
            jsonWriteForSneakers.open();
            jsonWriteForSneakers.write(sneakerPurchaseList);
            jsonWriteForSneakers.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "no file was found for sneaker entries");
        }
    }

    private void saveCookGroupList() {
        try {
            jsonWriterForCookGroupEntries.open();
            jsonWriterForCookGroupEntries.write(cookGroupPurchaseList);
            jsonWriterForCookGroupEntries.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "no file was found for cook group entries");
        }
    }

    private void saveThirdPartySolversList() {
        try {
            jsonWriterForThirdPartySolverEntries.open();
            jsonWriterForThirdPartySolverEntries.write(thirdPartyCaptchaSolversPurchaseList);
            jsonWriterForThirdPartySolverEntries.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "no file was found for third party entries");
        }
    }

    private void saveProxyList() {
        try {
            jsonWriterForProxyEntries.open();
            jsonWriterForProxyEntries.write(proxyPurchaseList);
            jsonWriterForProxyEntries.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "no file was found for proxy entries");
        }
    }

    private void saveRevenueList() {
        try {
            jsonWriterForRevenueList.open();
            jsonWriterForRevenueList.write(revenueList);
            jsonWriterForRevenueList.close();
            JOptionPane.showMessageDialog(null, "your revenue list has been saved");

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "no file was found for revenue list");
        }
    }
}
