package ui;

import model.SneakerEntry;
import model.investment.SneakerPurchaseList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//this class represents an action listener for the remove button for sneaker entries
public class RemoveSneakerListener implements ActionListener {
    private JButton removeButton;
    private DefaultListModel<SneakerEntry> defaultListModel;
    private SneakerPurchaseList sneakerPurchaseList;
    private JList<SneakerEntry> sneakersJlist;

    //EFFECT: Initializes remove sneaker listener constructor
    public RemoveSneakerListener(DefaultListModel<SneakerEntry> defaultListModel,
                                 JList<SneakerEntry> sneakerEntryJList, JButton removeButton,
                                 SneakerPurchaseList sneakerPurchaseList) {
        this.removeButton = removeButton;
        this.defaultListModel = defaultListModel;
        this.sneakerPurchaseList = sneakerPurchaseList;
        this.sneakersJlist = sneakerEntryJList;
    }

    @Override
    //MODIFIES: this
    //EFFECT: REMOVE THE selected element at the selected index, if size is 0 after, disable the remove button
    public void actionPerformed(ActionEvent e) {
        int index = sneakersJlist.getSelectedIndex();
        if (index != -1) {
            sneakerPurchaseList.removeEntry(defaultListModel.get(index));
            defaultListModel.remove(index);
        }

        int size = defaultListModel.size();
        if (size == 0) {
            removeButton.setEnabled(false);
        } else {
            sneakersJlist.setSelectedIndex(index--);
        }




    }
}
