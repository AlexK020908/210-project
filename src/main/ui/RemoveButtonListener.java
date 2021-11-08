package ui;

import model.Revenue;
import model.RevenueList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveButtonListener implements ActionListener, ListSelectionListener {
    JList<Revenue> revenueJList;
    DefaultListModel<Revenue> defaultListModel;
    RevenueList revenueList;
    JButton removeButton;

    public RemoveButtonListener(JList<Revenue> revenueJList, DefaultListModel<Revenue> defaultListModel,
                                RevenueList revenueList, JButton removeButton) {
        this.revenueJList =  revenueJList;
        this.defaultListModel = defaultListModel;
        this.revenueList = revenueList;
        this.removeButton = removeButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int index = revenueJList.getSelectedIndex();
        revenueList.removeRevenue(defaultListModel.get(index));
        defaultListModel.remove(index);



        int size = defaultListModel.size();
        if (size == 0) {
            removeButton.setEnabled(false);
        } else {
            revenueJList.setSelectedIndex(index--);
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (revenueJList.getSelectedIndex() == -1) {
                removeButton.setEnabled(false);
            } else {
                removeButton.setEnabled(true);
            }
        }
    }
}
