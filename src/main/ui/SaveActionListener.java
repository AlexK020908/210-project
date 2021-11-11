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

//This class represents an action listener for the save button
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

    //EFFECT: initializes the save action listener constructor
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

    //MODIFIES: this
    //EFFECT: save each entry to Json fle
    @Override
    public void actionPerformed(ActionEvent e) {
        saveProxyList();
        saveThirdPartySolversList();
        saveCookGroupList();
        saveSneakersList();
        saveRevenueList();

    }

    //MODIFIES: this
    //EFFECT: save and write the sneaker entry list into the corresponding json file.
    private void saveSneakersList() {
        try {
            jsonWriteForSneakers.open();
            jsonWriteForSneakers.write(sneakerPurchaseList);
            jsonWriteForSneakers.close();
            JOptionPane.showMessageDialog(null, "your sneaker entry list has been saved");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "no file was found for sneaker entries");
        }
    }

    //MODIFIES: this
    //EFFECT: save and write the COOK GROUP entry list into the corresponding json file.
    private void saveCookGroupList() {
        try {
            jsonWriterForCookGroupEntries.open();
            jsonWriterForCookGroupEntries.write(cookGroupPurchaseList);
            jsonWriterForCookGroupEntries.close();
            JOptionPane.showMessageDialog(null, "your cook group entry list has been saved");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "no file was found for cook group entries");
        }
    }

    //MODIFIES: this
    //EFFECT: save and write the third part solver entry list into the corresponding json file.
    private void saveThirdPartySolversList() {
        try {
            jsonWriterForThirdPartySolverEntries.open();
            jsonWriterForThirdPartySolverEntries.write(thirdPartyCaptchaSolversPurchaseList);
            jsonWriterForThirdPartySolverEntries.close();
            JOptionPane.showMessageDialog(null, "your third party entry list has been saved");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "no file was found for third party entries");
        }
    }

    //MODIFIES: this
    //EFFECT: save and write the proxy entry list into the corresponding json file.
    private void saveProxyList() {
        try {
            jsonWriterForProxyEntries.open();
            jsonWriterForProxyEntries.write(proxyPurchaseList);
            jsonWriterForProxyEntries.close();
            JOptionPane.showMessageDialog(null, "your proxy list has been saved");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "no file was found for proxy entries");
        }
    }

    //MODIFIES: this
    //EFFECT: save and write the revenue  list into the corresponding json file.
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
