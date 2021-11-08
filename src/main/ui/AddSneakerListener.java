package ui;

import model.SneakerEntry;
import model.investment.SneakerPurchaseList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSneakerListener implements ActionListener {
    SneakerPurchaseList sneakerPurchaseList;
    JPanel mainPanel;
    String sneaker;
    DefaultListModel<SneakerEntry> defaultListModel;
    JButton removeButton;

    public AddSneakerListener(SneakerPurchaseList sneakerPurchaseList, JPanel mainPanel, String sneaker,
                              DefaultListModel<SneakerEntry> defaultListModelSneaker, JButton removeButton) {
        this.sneakerPurchaseList = sneakerPurchaseList;
        this.mainPanel = mainPanel;
        this.sneaker = sneaker;
        this.defaultListModel = defaultListModelSneaker;
        this.removeButton = removeButton;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String sneakerName = JOptionPane.showInputDialog(null, "Please Enter the Sneaker name",
                null);
        if (sneakerName == null) {
            JOptionPane.showMessageDialog(null, "Please try entering the name again");
        } else {
            double sneakerPrice = Double.parseDouble(JOptionPane.showInputDialog(null,
                    "Please enter the price", null));

            if (sneakerPrice == 0 || sneakerPrice < 0) {
                JOptionPane.showMessageDialog(null, "you entered an invalid price");
            } else {
                int quantity = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "how many" + " " + sneakerName + " " + "did you buy", null));
                if (quantity == 0 || quantity < 0) {
                    JOptionPane.showMessageDialog(null, "you entered an invalid quantity");
                } else {
                    SneakerEntry sneakerEntry = new SneakerEntry(sneakerName, sneakerPrice, quantity);
                    if (sneakerPurchaseList.addEntry(sneakerEntry)) {
                        defaultListModel.addElement(sneakerEntry);
                    }
                    int size = defaultListModel.size();
                    if (size > 0) {
                        removeButton.setEnabled(true);
                    }
                }
            }
        }
    }
}
