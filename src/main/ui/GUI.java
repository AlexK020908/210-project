package ui;


import model.*;
import model.investment.*;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;



import static javax.swing.ScrollPaneConstants.*;

//this class represents the GUI of this project
public class GUI extends JPanel {
    private ProxyPurchaseList proxyPurchaseList;
    private CookGroupPurchaseList cookGroupPurchaseList;
    private ThirdPartyCaptchaSolversPurchaseList thirdPartyCaptchaSolversPurchaseList;
    private SneakerPurchaseList sneakerPurchaseList;
    private RevenueList revenueList;





    //EFFECT: constructs a mainPanel where JPnale will be attached to; initialize a proxy, cook group , third
    //        party solver , sneakers and revenue panels that will be the part of the main panel.
    //        components should be laid out horizontally from left to right.
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

        sneakerPurchaseList = new SneakerPurchaseList();
        initializeNewSneakerPanel(sneakerPurchaseList, "sneaker", mainPanel);

        revenueList = new RevenueList();
        initializeNewRevenuePanel(revenueList, mainPanel);

        JButton calculateButton = new JButton("calculate profit or loss");
        calculateButton.setPreferredSize(new Dimension(800, 100));

        mainPanel.add(calculateButton);

    }

    private void initializeNewRevenuePanel(RevenueList revenueList, JPanel mainPanel) {
        DefaultListModel<Revenue> defaultListModel = new DefaultListModel<>();
        JList<Revenue> revenueJList = new JList<>(defaultListModel);
        revenueJList.setCellRenderer(new RevenueCellRenderer());
        revenueJList.setVisibleRowCount(3);

        JPanel revenuePanel = new JPanel(new BorderLayout());
        revenuePanel.setPreferredSize(new Dimension(700, 200));
        JPanel buttonPanel = new JPanel();
        revenuePanel.setBorder(BorderFactory.createTitledBorder("revenue"));
        mainPanel.add(revenuePanel);
        revenuePanel.add(buttonPanel, BorderLayout.SOUTH);
        JButton addRevenueButton = new JButton("add revenue");
        JButton removeRevenueButton = new JButton("remove revenue");
        removeRevenueButton.setEnabled(false);
        JButton viewOverallRevenueButton = new JButton("view revenue");
        buttonPanel.add(addRevenueButton);
        buttonPanel.add(removeRevenueButton);
        buttonPanel.add(viewOverallRevenueButton);
        revenuePanel.add(revenueJList);
        revenuePanel.add(new JScrollPane(revenueJList, VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_AS_NEEDED));
        addRevenueButton.addActionListener(new AddRevenueListener(revenueList, defaultListModel, removeRevenueButton));
        removeRevenueButton.addActionListener(new RemoveButtonListener(revenueJList, defaultListModel, revenueList,
                removeRevenueButton));
        viewOverallRevenueButton.addActionListener(new ViewRevenueListener(revenueList));



    }

    private void initializeNewSneakerPanel(SneakerPurchaseList sneakerPurchaseList, String sneaker, JPanel mainPanel) {
        DefaultListModel<SneakerEntry> defaultListModelSneaker = new DefaultListModel<>();
        JList<SneakerEntry> sneakerEntryJList = new JList<>(defaultListModelSneaker);
        sneakerEntryJList.setCellRenderer(new SneakerCellRenderer());
        sneakerEntryJList.setVisibleRowCount(3);

        JPanel sneakerPanel = new JPanel(new BorderLayout());
        sneakerPanel.setPreferredSize(new Dimension(700, 200));
        sneakerPanel.setBorder(BorderFactory.createTitledBorder("sneaker enrty"));
        JPanel buttonPanel = new JPanel();
        JButton addSneakerEntryButton = new JButton("add" + " " + sneaker + " " + "entry");
        JButton removeSneakerEntryButton = new JButton("remove" + " " + sneaker + " " + "entry");
        removeSneakerEntryButton.setEnabled(false);

        mainPanel.add(sneakerPanel);
        buttonPanel.add(addSneakerEntryButton);
        buttonPanel.add(removeSneakerEntryButton);
        sneakerPanel.add(buttonPanel, BorderLayout.SOUTH);
        sneakerPanel.add(sneakerEntryJList);
        sneakerPanel.add(new JScrollPane(sneakerEntryJList, VERTICAL_SCROLLBAR_ALWAYS,
                HORIZONTAL_SCROLLBAR_AS_NEEDED));
        addSneakerEntryButton.addActionListener(new AddSneakerListener(sneakerPurchaseList,
                mainPanel, sneaker, defaultListModelSneaker, removeSneakerEntryButton));
        removeSneakerEntryButton.addActionListener(new RemoveSneakerListener(defaultListModelSneaker, sneakerEntryJList,
                removeSneakerEntryButton, sneakerPurchaseList));


    }


    //MODIFIES: mainPanel, this
    //EFFECT: create an entry panel of the corresponding support entry list type. In the entry panel, it should have
    //        a button that allows the user to add a corresponding entry type name and price. If the user
    //        enters an invalid price(like 0) a pop up message should notify the user to fix their inputs.
    //        If the user enters an empty name , a pop-up message should notify the user to fix their name input.
    private void initializeNewPanel(SupportEntryList<? extends SupportEntry> supportEntryList, String typeOfEntry,
                                                             JPanel mainPanel) {
        DefaultListModel<SupportEntry> defaultListModel = new DefaultListModel<>();
        //make the default list model defaultListModel into a jlist
        JList<SupportEntry> entryJlist = new JList<>(defaultListModel);
        //show how it is rendered, in this case it is string
        entryJlist.setCellRenderer(new SupportEntryCellRenderer());
        entryJlist.setFixedCellHeight(10);
        entryJlist.setFixedCellWidth(130);
        //button
        JButton addEntryButton = new JButton("add" + " " + typeOfEntry + " " + "entry");
        //how big is it? may use addEntryButton.setbounds(), but if u have manager, no need to do it.
        //all the panels
        JButton removeEntryButton = new JButton("remove" + typeOfEntry + "entry");
        removeEntryButton.setEnabled(false);
        JPanel buttonPanel = new JPanel();
        JPanel entryPanel = new JPanel(new BorderLayout());
        //but this doesnt work why> entryPanel.add(proxyEntryJlist)
        //adding button to pannel
        entryPanel.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(addEntryButton);
        buttonPanel.add(removeEntryButton);
        entryPanel.setBorder(BorderFactory.createTitledBorder(typeOfEntry + " " + "Entries"));
        entryPanel.setPreferredSize(new Dimension(600, 200));
        mainPanel.add(entryPanel);
        entryPanel.add(entryJlist);
        entryPanel.add(new JScrollPane(entryJlist, VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_AS_NEEDED));


        addEntryButton.addActionListener(new SupportEntryActionListener(typeOfEntry, supportEntryList, defaultListModel,
                entryPanel, removeEntryButton));
        removeEntryButton.addActionListener(new RemoveListener(defaultListModel,
                entryJlist, removeEntryButton, supportEntryList));
    }






    /**
     * MODIFIES : this
     * EFFECT:  Create the GUI and show it.
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


    //This
    //EFFECT: This class represents each support entry as its string
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

