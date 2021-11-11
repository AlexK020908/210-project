package ui;

import model.Revenue;
import model.SneakerEntry;

import javax.swing.*;
import java.awt.*;

//This class represents a cell renderer for revenue list
public class RevenueCellRenderer extends DefaultListCellRenderer {

    //EFFECT: show each revenue entry as its number in terms of double
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                                                  boolean cellHasFocus) {
        if (value == null) {
            value = "";
        } else {
            Revenue entry = (Revenue) value;
            value = entry.getRevenue();
        }
        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    }
}
