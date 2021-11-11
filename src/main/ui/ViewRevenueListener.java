package ui;

import model.RevenueList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//this class represents an action listener for the view button for revenue list
public class ViewRevenueListener implements ActionListener {
    private RevenueList revenueList;

    //Initializes a view renvenue listener constructor
    public ViewRevenueListener(RevenueList revenueList) {
        this.revenueList = revenueList;
    }

    //EFFECT:when the button is clicked, simply show a dialog of the current revenue total
    @Override
    public void actionPerformed(ActionEvent e) {
        double revenueTotal = revenueList.calculateTotalRevenue();
        JOptionPane.showMessageDialog(null, "your total revenue is" + " " + revenueTotal + " " + " dollars");

    }
}
