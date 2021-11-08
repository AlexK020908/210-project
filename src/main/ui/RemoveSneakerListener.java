package ui;

import model.SneakerEntry;
import model.investment.SneakerPurchaseList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveSneakerListener implements ActionListener {
    private JButton removeButton;
    private DefaultListModel<SneakerEntry> defaultListModel;
    private SneakerPurchaseList sneakerPurchaseList;
    private JList<SneakerEntry> sneakersJlist;

    public RemoveSneakerListener(DefaultListModel<SneakerEntry> defaultListModel,
                                 JList<SneakerEntry> sneakerEntryJList, JButton removeButton,
                                 SneakerPurchaseList sneakerPurchaseList) {
        this.removeButton = removeButton;
        this.defaultListModel = defaultListModel;
        this.sneakerPurchaseList = sneakerPurchaseList;
        this.sneakersJlist = sneakerEntryJList;
    }

    @Override
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
