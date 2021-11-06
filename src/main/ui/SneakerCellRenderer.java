package ui;

import model.SneakerEntry;
import model.SupportEntry;

import javax.swing.*;
import java.awt.*;

public class SneakerCellRenderer extends DefaultListCellRenderer {
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

