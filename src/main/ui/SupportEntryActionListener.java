package ui;

import model.CookGroupSubscriptionEntry;
import model.ProxyEntry;
import model.SupportEntry;
import model.ThirdPartyCaptchaSolverEntry;
import model.investment.SupportEntryList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//       a helper class that create the action of asking the user to create a new entry when the button is
//        pressed.
public class SupportEntryActionListener implements ActionListener {
    String typeOfEntry;
    SupportEntryList<? extends SupportEntry> supportEntryList;
    DefaultListModel<SupportEntry> defaultListModel;
    JPanel entryPanel;
    JButton removeButton;

    //Constructs an action listener with the specified support entry list, name of the support entry, a default list
    // model and an entry panel for this type of entry
    public SupportEntryActionListener(String typeOfEntry, SupportEntryList<? extends SupportEntry> supportEntryList,
                                      DefaultListModel<SupportEntry> defaultListModel, JButton removeButton) {
        this.typeOfEntry = typeOfEntry;
        this.supportEntryList = supportEntryList;
        this.defaultListModel = defaultListModel;
        this.removeButton = removeButton;

    }

    @Override
    //MODIFIES: this
    //EFFECT: Asks the user to enter the name and the price of the corresponding support netry
    //        If name is empty or null, prompts a message box that tells the user to enter a valid name
    //        If the price is invalid (0), prompts the user with a message box that tells the user to enter
    //        a valid price
    public void actionPerformed(ActionEvent e) {
        String entryName = JOptionPane.showInputDialog(null, typeOfEntry + " " + "name?",
                "Enter" + " " + typeOfEntry + " " + "name", JOptionPane.QUESTION_MESSAGE);
        if (entryName == null || entryName.length() == 0) {
            JOptionPane.showMessageDialog(entryPanel, "please enter a valid name");
        } else {
            addNewEntry(entryName, supportEntryList, defaultListModel, entryPanel);

        }
    }


    //EFFECT: this helper function adds the new entry to the entry panel , there are three type of
    //         Support entry, if the type of the entry matches one of the three types, create a new entry
    //          of that type and add it to the default list model so it would be displayed on Jlist in the
    //          entry panel
    private <T extends SupportEntry> void addNewEntry(String entryName, SupportEntryList<T> supportEntryList,
                                                      DefaultListModel<SupportEntry> defaultListModel,
                                                      JPanel entryPanel) {
        double entryPrice = Double.parseDouble(JOptionPane.showInputDialog(null, entryName + "'s price?",
                "Enter price", JOptionPane.QUESTION_MESSAGE));
        if (entryPrice == 0) {
            JOptionPane.showMessageDialog(entryPanel, "please enter a valid price");
        }

        switch (supportEntryList.getType()) {
            case PROXY:
                ProxyEntry proxyEntry = new ProxyEntry(entryName, entryPrice);
                entryHelper(supportEntryList, proxyEntry, defaultListModel);
                break;

            case CookGroup:
                CookGroupSubscriptionEntry cookEntry = new CookGroupSubscriptionEntry(entryName, entryPrice);
                entryHelper(supportEntryList, cookEntry, defaultListModel);
                break;

            case ThirdPartSolver: ThirdPartyCaptchaSolverEntry solverEntry =
                    new ThirdPartyCaptchaSolverEntry(entryName, entryPrice);
                entryHelper(supportEntryList, solverEntry, defaultListModel);
                break;
        }

    }

    //MODIFIES: supportEntryList, Default list model and this.
    // EFFECT: If the newly added entry is already in the list, simply update the quantity and have the size of
    // default list model unchanged, otherwise add the new element into the default list model/
    private <T extends SupportEntry> void entryHelper(SupportEntryList<T> supportEntryList, SupportEntry supportEntry,
                                                      DefaultListModel<SupportEntry> defaultListModel) {
        if (supportEntryList.addEntry((T)supportEntry)) {
            defaultListModel.addElement(supportEntry);
            int size = defaultListModel.size();
            if (size > 0) {
                removeButton.setEnabled(true);
            }
        }
    }

    //either it is true or not, the expression inside
    //the if statement is operated --> this means if it is true, one needs to add the new entry
    //to the defaultListModel(using addElement), otherwise if it returns false, it means that the
    //object whose quantity has  been updated changed the reference object inb the defaultListModel
    //default list, and so we do not need to do anything at all

}

