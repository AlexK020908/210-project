package ui;

import model.Revenue;
import model.RevenueList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//This class reprsents an action listener for the remove button for revenue list
public class RemoveButtonListener implements ActionListener, ListSelectionListener {
    JList<Revenue> revenueJList;
    DefaultListModel<Revenue> defaultListModel;
    RevenueList revenueList;
    JButton removeButton;

    //EFFECT: initialize a remove button listiner construtor
    public RemoveButtonListener(JList<Revenue> revenueJList, DefaultListModel<Revenue> defaultListModel,
                                RevenueList revenueList, JButton removeButton) {
        this.revenueJList =  revenueJList;
        this.defaultListModel = defaultListModel;
        this.revenueList = revenueList;
        this.removeButton = removeButton;
    }

    //MODIFIES: this
    //EFFECT: get the selected index and remve that index from the list model and list. If size then is = 0 ;
    //        make the remove button false
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

    //MODIFIES: this
    //EFFECT: if the user is not changing their selection;
    //         if list has no remaining entry, set remove button to false --> otherwise set it to true
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
