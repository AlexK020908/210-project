package ui;

import com.sun.org.apache.bcel.internal.generic.NEW;
import model.Revenue;
import model.RevenueList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//this class is an action listener when the add button is pressed on the revenue panel
public class AddRevenueListener implements ActionListener {
    RevenueList revenueList;
    DefaultListModel<Revenue> defaultListModel;
    JButton removeButton;

    //Effect: Initialize a listener constructor
    public AddRevenueListener(RevenueList revenueList, DefaultListModel<Revenue> defaultListModel,
                             JButton removeButton) {
        this.revenueList = revenueList;
        this.defaultListModel = defaultListModel;
        this.removeButton = removeButton;
    }

    //Modifies: this
    //EFFECT: prompt the user to enter the new revenue he/she wants to add, also update the list model's corresponding
    // revenue list. List model and revenue list should have the same length at all times.
    // enable remove button if size of the list is larger than 0
    @Override
    public void actionPerformed(ActionEvent e) {
        double newRevenue = Double.parseDouble(JOptionPane.showInputDialog(null, "enter the revenue made", null));
        if (newRevenue == 0 || newRevenue < 0) {
            JOptionPane.showMessageDialog(null, "please enter a correct revenue");
        } else {
            Revenue revenue = new Revenue(newRevenue);

            revenueList.addNewRevenue(revenue);
            defaultListModel.addElement(revenue);

            int size = defaultListModel.size();
            if (size > 0) {
                removeButton.setEnabled(true);
            }

        }
    }
}
