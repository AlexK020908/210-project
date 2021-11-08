package ui;

import com.sun.org.apache.bcel.internal.generic.NEW;
import model.Revenue;
import model.RevenueList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRevenueListener implements ActionListener {
    RevenueList revenueList;
    DefaultListModel<Revenue> defaultListModel;
    JButton removeButton;

    public AddRevenueListener(RevenueList revenueList, DefaultListModel<Revenue> defaultListModel,
                             JButton removeButton) {
        this.revenueList = revenueList;
        this.defaultListModel = defaultListModel;
        this.removeButton = removeButton;
    }

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
