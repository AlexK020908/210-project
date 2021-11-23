package ui;

import model.EntryType;
import model.SupportEntry;
import model.investment.SneakerPurchaseList;
import model.investment.SupportEntryList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//this class represetns an action listner for the remove button for support entries
public class RemoveSupportEntryListener implements ActionListener, ListSelectionListener {
    private DefaultListModel<SupportEntry> defaultListModel;
    private JList<SupportEntry> entryJList;
    private JButton removeButton;
    private SupportEntryList<? extends SupportEntry> supportEntryList;

    //EFFECT: intialize a remove listener constructor
    public RemoveSupportEntryListener(DefaultListModel<SupportEntry> defaultListModel, JList entryJList,
                                      JButton removeButton,
                                      SupportEntryList<? extends SupportEntry> supportEntryList) {
        this.defaultListModel = defaultListModel;
        this.entryJList = entryJList;
        this.removeButton = removeButton;
        this.supportEntryList = supportEntryList;
    }

    //MODIFIES: this
    //EFFECT: REMOVE THE selected element at the selected index, if size is 0 after, disable the remove button
    @Override
    public void actionPerformed(ActionEvent e) {
        int index = entryJList.getSelectedIndex();
        //remove it from the default list model
        supportEntryList.removeEntry(defaultListModel.get(index));
        defaultListModel.remove(index);
        //getting index of the selected index
        //if size is equal to zero --> disable the button
        int size = defaultListModel.size();
        if (size == 0) {
            removeButton.setEnabled(false);
        } else {
            entryJList.setSelectedIndex(index--);
        }
    }


    //MODIFIES: this
    //EFFECT: if user is not adjusting the selection anymore, disable or enable the remove button depending if there
    //is any element left (disable if there is no element left)
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (entryJList.getSelectedIndex() == -1) {
                removeButton.setEnabled(false);
            } else {
                removeButton.setEnabled(true);
            }
        }
    }
}

