package ui;

import model.SneakerEntry;
import model.SupportEntry;

import javax.swing.*;
import java.awt.*;

//This class is a cell renderer for sneaker entries
public class SneakerCellRenderer extends DefaultListCellRenderer {

    //EFFECT: show each sneaker entry as name, price and quantity in a readable form
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                                                  boolean cellHasFocus) {
        if (value == null) {
            value = "";
        } else {
            SneakerEntry entry = (SneakerEntry) value;
            value = entry.getName() + ",  " + "price: " + entry.getRetailPrice() + ",  " + "quantity: "
                    + entry.getQuantityBought();
        }
        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    }

}

