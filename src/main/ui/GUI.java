package ui;


import model.ProxyEntry;
import model.SupportEntry;
import model.investment.ProxyPurchaseList;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JPanel implements ListSelectionListener {
    private ProxyPurchaseList proxyPurchaseList;



    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    public GUI() {

        proxyPurchaseList = new ProxyPurchaseList();
        SupportEntryCellRenderer cellRenderer = new SupportEntryCellRenderer();
        setLayout(new GridLayout(1, 5));
        DefaultListModel<ProxyEntry> proxies = new DefaultListModel<>();
        //make the default list model proxies into a jlist

        JList<ProxyEntry> proxyEntryJlist = new JList<>(proxies);
        //we can see 10 elements
        proxyEntryJlist.setVisibleRowCount(3);
        //show how it is rendered, in this case it is string
        proxyEntryJlist.setCellRenderer(cellRenderer);

        //button
        JButton addProxyButton = new JButton("add proxy entry");
        //how big is it? may use addProxyButton.setbounds(), but if u have manager, no need to do it.

        //all the panels
        JPanel mainPanel = new JPanel(new FlowLayout());
        JPanel proxyPanel = new JPanel();
        //but this doesnt work why> proxyPanel.add(proxyEntryJlist)
        proxyEntryJlist.setAlignmentX(CENTER_ALIGNMENT);

        //adding button to pannel
        proxyPanel.add(addProxyButton, Component.BOTTOM_ALIGNMENT);


        add(mainPanel);
        mainPanel.setBorder(BorderFactory.createTitledBorder("Support Entries"));
        proxyPanel.setBorder(BorderFactory.createTitledBorder("proxy Entries"));

        proxyPanel.setPreferredSize(new Dimension(400, 200));
        mainPanel.add(proxyPanel);
        proxyPanel.add(proxyEntryJlist);
        proxyPanel.add(new JScrollPane(proxyEntryJlist));

        addProxyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String proxyName = JOptionPane.showInputDialog(null,
                        "Proxy name?",
                        "Enter proxy name",
                        JOptionPane.QUESTION_MESSAGE);

                double proxyPrice = Double.parseDouble(JOptionPane.showInputDialog(null, proxyName + "'s price?",
                        "Enter price",
                        JOptionPane.QUESTION_MESSAGE));



                    if (proxyName != null) {
                        ProxyEntry proxyEntry = new ProxyEntry(proxyName, proxyPrice);
                        if (proxyPurchaseList.addEntry(proxyEntry)) { //either it is true or not, the expression inside
                            //the if statement is operated --> this means if it is true, one needs to add the new entry
                            //to the proxies(using addElement), otherwise if it returns false, it means that the
                            //object whose quantity has  been updated changed the reference object inb the proxies
                            //default list, and so we do not need to do anything at all
                            proxies.addElement(proxyEntry);
                        }

                    }
            }
        });
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
    private static class SupportEntryCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                                                      boolean cellHasFocus) {
            if (value == null) {
                value = "";
            } else {
                SupportEntry entry = (SupportEntry) value;
                value = entry.getName() + " " + entry.getPricePaid();
            }
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }

    }
}
