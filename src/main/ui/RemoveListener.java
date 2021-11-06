package ui;

import model.EntryType;
import model.SupportEntry;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveListener implements ActionListener {
    private DefaultListModel<SupportEntry> defaultListModel;
    private JList<SupportEntry> entryJList;
    private JButton removeButton;

    public RemoveListener(DefaultListModel<SupportEntry> defaultListModel, JList entryJList, JButton removeButton) {
        this.defaultListModel = defaultListModel;
        this.entryJList = entryJList;
        this.removeButton = removeButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = entryJList.getSelectedIndex();
        //remove it from the default list model
        defaultListModel.remove(index);
        //getting index of the selected index
        int size = defaultListModel.size();
        //if size is equal to zero --> disable the button
        if (size == 0) {
            removeButton.setEnabled(false);
        } else {
            entryJList.setSelectedIndex(index--);
        }
    }


}

