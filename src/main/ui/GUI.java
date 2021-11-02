package ui;

import model.CookGroupSubscriptionEntry;
import model.ProxyEntry;
import model.SneakerEntry;
import model.SupportEntry;
import model.investment.CookGroupPurchaseList;
import model.investment.ProxyPurchaseList;
import model.investment.SneakerPurchaseList;
import model.investment.SupportEntryList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

public class GUI extends JPanel implements ListSelectionListener {
    private SneakerPurchaseList sneakerPurchaseList = new SneakerPurchaseList();
    private ProxyPurchaseList proxyPurchaseList = new ProxyPurchaseList();
    private CookGroupPurchaseList cookGroupPurchaseList = new CookGroupPurchaseList();
    private ProxyEntry s1 = new ProxyEntry("Oculus", 12);
    private CookGroupSubscriptionEntry c1 = new CookGroupSubscriptionEntry("capmonster", 12);

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    public GUI() {
        proxyPurchaseList.addEntry(s1);
        SupportEntryCellRenderer cellRenderer = new SupportEntryCellRenderer();
        setLayout(new GridLayout(1, 0));
        DefaultListModel<ProxyEntry> proxies = new DefaultListModel<>();
        List<ProxyEntry> proxyEntryList = proxyPurchaseList.getEntries();
        for (ProxyEntry p : proxyEntryList) {
            proxies.addElement(p);
        }

        //make the default list model proxies into a jlist
        JList<ProxyEntry> proxyEntryJlist = new JList<>(proxies);
        //we can see 10 elements
        proxyEntryJlist.setVisibleRowCount(10);
        //show how it is rendered, in this case it is string
        proxyEntryJlist.setCellRenderer(cellRenderer);
        //adding a scroll panel
        JScrollPane scrollPaneProxy = new JScrollPane(proxyEntryJlist);
        scrollPaneProxy.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        JPanel container = new JPanel(new BorderLayout());
        container.add(proxyEntryJlist);
        container.setBorder(BorderFactory.createTitledBorder("proxy list"));
        add(container);



    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Profit or Loss calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //initalizing the gui and adding it to eh window
        JComponent newContentPane = new GUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    //This class represents each support entry as its string
    private class SupportEntryCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                                                      boolean cellHasFocus) {
            if (value == null) {
                value = "";
            } else {
                SupportEntry entry = (SupportEntry) value;
                value = entry.toString();
            }
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }

    }
}
