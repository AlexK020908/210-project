package ui;


import model.*;
import model.investment.CookGroupPurchaseList;
import model.investment.ProxyPurchaseList;
import model.investment.SupportEntryList;
import model.investment.ThirdPartyCaptchaSolversPurchaseList;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import static javax.swing.ScrollPaneConstants.*;

public class GUI extends JPanel implements ListSelectionListener {
    private ProxyPurchaseList proxyPurchaseList;
    private CookGroupPurchaseList cookGroupPurchaseList;
    private ThirdPartyCaptchaSolversPurchaseList thirdPartyCaptchaSolversPurchaseList;



    @Override
    public void valueChanged(ListSelectionEvent e) {

    }


    public GUI() {
        JPanel mainPanel = new JPanel(new FlowLayout());
        add(mainPanel);
        mainPanel.setBorder(BorderFactory.createTitledBorder("Support Entries"));
        proxyPurchaseList = new ProxyPurchaseList();
        setLayout(new GridLayout(1, 5));
        initializeNewPanel(proxyPurchaseList, "Proxy", mainPanel);
        cookGroupPurchaseList = new CookGroupPurchaseList();
        initializeNewPanel(cookGroupPurchaseList, "Cook group", mainPanel);
        thirdPartyCaptchaSolversPurchaseList = new ThirdPartyCaptchaSolversPurchaseList();
        initializeNewPanel(thirdPartyCaptchaSolversPurchaseList, "Third party captcha solver", mainPanel);
    }


    private void initializeNewPanel(SupportEntryList<? extends SupportEntry> supportEntryList, String typeOfEntry,
                                                             JPanel mainPanel) {
        DefaultListModel<SupportEntry> defaultListModel = new DefaultListModel<>();
        //make the default list model defaultListModel into a jlist
        JList<SupportEntry> entryJlist = new JList<>(defaultListModel);
        //show how it is rendered, in this case it is string
        entryJlist.setCellRenderer(new SupportEntryCellRenderer());
        //button
        JButton addEntryButton = new JButton("add" + " " + typeOfEntry + " " + "entry");
        //how big is it? may use addEntryButton.setbounds(), but if u have manager, no need to do it.
        //all the panels
        JPanel entryPanel = new JPanel();
        //but this doesnt work why> entryPanel.add(proxyEntryJlist)
        //adding button to pannel
        entryPanel.add(addEntryButton, Component.BOTTOM_ALIGNMENT);

        entryPanel.setBorder(BorderFactory.createTitledBorder(typeOfEntry + " " + "Entries"));

        entryPanel.setPreferredSize(new Dimension(400, 200));
        mainPanel.add(entryPanel);
        entryPanel.add(entryJlist);
        entryPanel.add(new JScrollPane(entryJlist,VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED));

        addEntryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String entryName = JOptionPane.showInputDialog(null, typeOfEntry + " " + "name?",
                        "Enter" + " " + typeOfEntry + " " + "name", JOptionPane.QUESTION_MESSAGE);
                if (entryName == null || entryName.length() == 0) {
                    JOptionPane.showMessageDialog(entryPanel, "please enter a valid name");
                } else {
                    addNewEntry(entryName, supportEntryList, defaultListModel);

                }
            }
        });
    }

    private <T extends SupportEntry> void addNewEntry(String entryName, SupportEntryList<T> supportEntryList,
                             DefaultListModel<SupportEntry> defaultListModel) {
        double entryPrice = Double.parseDouble(JOptionPane.showInputDialog(null, entryName + "'s price?",
                "Enter price", JOptionPane.QUESTION_MESSAGE));

        switch (supportEntryList.getType()) {
            case PROXY:
                ProxyEntry proxyEntry = new ProxyEntry(entryName, entryPrice);
                entryHelper(supportEntryList, proxyEntry, defaultListModel);
                break;

            case CookGroup:
                CookGroupSubscriptionEntry cookEntry = new CookGroupSubscriptionEntry(entryName, entryPrice);
                entryHelper(supportEntryList, cookEntry, defaultListModel);
                break;

            case ThirdPartSolver: ThirdPartyCaptchaSolverEntry solverEntry =
                    new ThirdPartyCaptchaSolverEntry(entryName, entryPrice);
                entryHelper(supportEntryList, solverEntry, defaultListModel);
            break;
        }

    }


    //either it is true or not, the expression inside
    //the if statement is operated --> this means if it is true, one needs to add the new entry
    //to the defaultListModel(using addElement), otherwise if it returns false, it means that the
    //object whose quantity has  been updated changed the reference object inb the defaultListModel
    //default list, and so we do not need to do anything at all

    private <T extends SupportEntry> void entryHelper(SupportEntryList<T> supportEntryList, SupportEntry supportEntry,
                                                      DefaultListModel<SupportEntry> defaultListModel) {
        if (supportEntryList.addEntry((T)supportEntry)) {
            defaultListModel.addElement(supportEntry);
        }
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
        JMenuBar menubar = new JMenuBar();
        JMenuItem item = new JMenuItem("Calculate");
        item.setOpaque(true);
        item.setBackground(Color.WHITE);
        menubar.add(item);
        frame.setJMenuBar(menubar);

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
