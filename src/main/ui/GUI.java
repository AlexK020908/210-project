package ui;


import model.*;
import model.investment.*;
import persistance.*;


import javax.swing.*;

import java.awt.*;


import static javax.swing.ScrollPaneConstants.*;

//this class represents the GUI of this project
public class GUI extends JPanel {

    public static final String SNEAKER_STORE = "./data/sneakerInvestment.json";
    public static final String PROXY_STORE = "./data/proxyEntryList.json";
    public static final String COOK_GROUP_STORE = "./data/cookGroupEntryList.json";
    public static final String THIRD_PARTY_SOLVER_STORE = "./data/thirdPartySolversEntryList.json";
    public static final String REVENUE_STORE = "./data/revenueEntryList.json";
    private ProxyPurchaseList proxyPurchaseList;
    private CookGroupPurchaseList cookGroupPurchaseList;
    private ThirdPartyCaptchaSolversPurchaseList thirdPartyCaptchaSolversPurchaseList;
    private SneakerPurchaseList sneakerPurchaseList;
    private RevenueList revenueList;
    private JsonWriterForSupportEntries jsonWriterForProxyEntries;
    private JsonWriterForSupportEntries jsonWriterForCookGroupEntries;
    private JsonWriterForSupportEntries jsonWriterForThirdPartySolverEntries;
    private JsonWriteForSneakers jsonWriteForSneakers;
    private JsonReaderForSneaker jsonReaderForSneaker;
    private JsonReaderForProxy jsonReaderForProxy;
    private JsonReaderForCookGroupList jsonReaderForCookGroup;
    private JsonReaderForThirdPartySolvers jsonReaderForThirdPartySolvers;
    private JsonWriterForRevenueList jsonWriterForRevenueList;
    private JsonReaderForRevenueList jsonReaderForRevenueList;
    JMenuBar menubar;

    DefaultListModel<SupportEntry> defaultListModelForSupportEntries;

    //EFFECT: constructs a mainPanel where JPnale will be attached to; initialize a proxy, cook group , third
    //        party solver , sneakers and revenue panels that will be the part of the main panel.
    //        components should be laid out horizontally from left to right.
    public GUI() {
        jsonWriteForSneakers = new JsonWriteForSneakers(SNEAKER_STORE);
        jsonReaderForSneaker = new JsonReaderForSneaker(SNEAKER_STORE);
        jsonWriterForProxyEntries = new JsonWriterForSupportEntries(PROXY_STORE);
        jsonReaderForProxy = new JsonReaderForProxy(PROXY_STORE);
        jsonWriterForCookGroupEntries = new JsonWriterForSupportEntries(COOK_GROUP_STORE);
        jsonReaderForCookGroup = new JsonReaderForCookGroupList(COOK_GROUP_STORE);
        jsonWriterForThirdPartySolverEntries = new JsonWriterForSupportEntries(THIRD_PARTY_SOLVER_STORE);
        jsonReaderForThirdPartySolvers = new JsonReaderForThirdPartySolvers(THIRD_PARTY_SOLVER_STORE);
        jsonWriterForRevenueList = new JsonWriterForRevenueList(REVENUE_STORE);
        jsonReaderForRevenueList = new JsonReaderForRevenueList(REVENUE_STORE);

        menubar = new JMenuBar();
        menubar.setOpaque(true);
        menubar.setBackground(Color.WHITE);
        //Create and set up the window.
        JFrame frame = initializeJFrame();
        JPanel mainPanel = initializePanel();
        initializeAllEntries(mainPanel);
        addSaveLoadMenu(frame);
        addSupportEntriesMenu(frame);


    }


    private JPanel initializePanel() {
        JPanel mainPanel = new JPanel(new FlowLayout());
        add(mainPanel);
        mainPanel.setBorder(BorderFactory.createTitledBorder("Support Entries"));
        return mainPanel;
    }

    private JFrame initializeJFrame() {
        JFrame frame = new JFrame("Profit or Loss calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setOpaque(true); //content panes must be opaque
        frame.setContentPane(this);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        return frame;
    }

    private void initializeAllEntries(JPanel mainPanel) {
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
        calculateButton.addActionListener(new CalculateListener(cookGroupPurchaseList, proxyPurchaseList,
                thirdPartyCaptchaSolversPurchaseList, sneakerPurchaseList, revenueList));
    }

    private void addSupportEntriesMenu(JFrame frame) {
        JMenu supportEntries = new JMenu("Support entries");
        menubar.add(supportEntries);
        JMenuItem addProxy = new JMenuItem("add proxy entry");
        JMenuItem addCookGroup = new JMenuItem("add cook group entry entry");
        JMenuItem addThirdPartySolver = new JMenuItem("add third party solver entry");
        supportEntries.add(addProxy);
        supportEntries.add(addCookGroup);
        supportEntries.add(addThirdPartySolver);
    }

    private void addSaveLoadMenu(JFrame frame) {
        JMenu file = new JMenu("file");
        menubar.setBackground(Color.WHITE);
        menubar.add(file);
        frame.setJMenuBar(menubar);
        JMenuItem saveButton = new JMenuItem("save");
        JMenuItem loadButton = new JMenuItem("load");
        file.add(saveButton);
        file.add(loadButton);
        saveButton.addActionListener(new SaveActionListener(proxyPurchaseList, cookGroupPurchaseList,
                thirdPartyCaptchaSolversPurchaseList, sneakerPurchaseList, revenueList, jsonWriterForProxyEntries,
                jsonWriterForCookGroupEntries, jsonWriterForThirdPartySolverEntries,
                jsonWriteForSneakers, jsonWriterForRevenueList));
        loadButton.addActionListener(new LoadActionListener());
    }

    private void initializeNewRevenuePanel(RevenueList revenueList, JPanel mainPanel) {
        DefaultListModel<Revenue> defaultListModel = new DefaultListModel<>();
        JList<Revenue> revenueJList = new JList<>(defaultListModel);
        revenueJList.setCellRenderer(new RevenueCellRenderer());
        revenueJList.setVisibleRowCount(3);

        JPanel revenuePanel = new JPanel(new BorderLayout());
        revenuePanel.setPreferredSize(new Dimension(600, 200));
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
        sneakerPanel.setPreferredSize(new Dimension(600, 200));
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
        DefaultListModel<SupportEntry> defaultListModelForSupportEntries = new DefaultListModel<>();
        //make the default list model defaultListModelForSupportEntries into a jlist
        JList<SupportEntry> entryJlist = new JList<>(defaultListModelForSupportEntries);
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
        entryPanel.setPreferredSize(new Dimension(400, 200));
        mainPanel.add(entryPanel);
        entryPanel.add(entryJlist);
        entryPanel.add(new JScrollPane(entryJlist, VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_AS_NEEDED));


        addEntryButton.addActionListener(new SupportEntryActionListener(typeOfEntry, supportEntryList, defaultListModelForSupportEntries,
                entryPanel, removeEntryButton));
        removeEntryButton.addActionListener(new RemoveListener(defaultListModelForSupportEntries,
                entryJlist, removeEntryButton, supportEntryList));
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

