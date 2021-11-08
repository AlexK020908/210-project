package ui;

import model.Revenue;
import model.SneakerEntry;

import javax.swing.*;
import java.awt.*;

public class RevenueCellRenderer extends DefaultListCellRenderer {
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
