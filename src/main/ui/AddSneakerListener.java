package ui;

import model.AmountException;
import model.NameException;
import model.QuantityException;
import model.SneakerEntry;
import model.investment.SneakerPurchaseList;

import javax.swing.*;
import javax.xml.soap.Name;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//This class represents an action listener for the add sneaker enrty button
public class AddSneakerListener implements ActionListener {
    SneakerPurchaseList sneakerPurchaseList;
    DefaultListModel<SneakerEntry> defaultListModel;
    JButton removeButton;

    public AddSneakerListener(SneakerPurchaseList sneakerPurchaseList,
                              DefaultListModel<SneakerEntry> defaultListModelSneaker, JButton removeButton) {
        this.sneakerPurchaseList = sneakerPurchaseList;;
        this.defaultListModel = defaultListModelSneaker;
        this.removeButton = removeButton;

    }

    //MODIFIES: this
    //EFFECT: prompts the user to enter info about the new sneaker entry. If sneaker name or price is invalid,
    // inform the user otherwise add the new sneaker entry to the puchase list and the list model
    //if size > 0, enable the remove button
    @Override
    public void actionPerformed(ActionEvent e) {
        String sneakerName = JOptionPane.showInputDialog(null, "Please Enter the Sneaker name", null);
        double sneakerPrice = Double.parseDouble(JOptionPane.showInputDialog(null, "Please enter the price", null));
        int quantity = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "how many" + " " + sneakerName + " " + "did you buy", null));

        try {
            SneakerEntry sneakerEntry = new SneakerEntry(sneakerName, sneakerPrice, quantity);
            if (sneakerPurchaseList.addEntry(sneakerEntry)) {
                defaultListModel.addElement(sneakerEntry);
            }
        } catch (NameException ex) {
            JOptionPane.showMessageDialog(null, "Please try entering the name again");
        } catch (AmountException ex) {
            JOptionPane.showMessageDialog(null, "you entered an invalid price");
        } catch (QuantityException ex) {
            JOptionPane.showMessageDialog(null, "you entered an invalid quantity");
        }
        checkSizeEnableButton();
    }



    //MODIFIES: this
    //EFFECT: if after adding the new entry the size is > 0, enable the remove button
    private void checkSizeEnableButton() {
        int size = defaultListModel.size();
        if (size > 0) {
            removeButton.setEnabled(true);
        }
    }
}
