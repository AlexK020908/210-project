package ui;

import model.RevenueList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewRevenueListener implements ActionListener {
    private RevenueList revenueList;

    public ViewRevenueListener(RevenueList revenueList) {
        this.revenueList = revenueList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double revenueTotal = revenueList.calculateTotalRevenue();
        JOptionPane.showMessageDialog(null, "your total revenue is" + " " + revenueTotal + " " + " dollars");

    }
}
