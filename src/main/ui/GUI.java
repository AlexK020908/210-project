package ui;


import model.*;
import model.investment.*;
import persistance.*;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


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
    private JMenuBar menubar;

    private DefaultListModel<SneakerEntry> defaultListModelSneaker;
    private JList<SneakerEntry> sneakerEntryJList;
    private JPanel sneakerPanel;
    private JButton addSneakerEntryButton;
    private JButton removeSneakerEntryButton;

    private DefaultListModel<Revenue> revenueListModel;
    private JList<Revenue> revenueJList;
    private JButton removeRevenueButton;
    private DefaultListModel<SupportEntry> proxyDefualtPurchaseList;
    private DefaultListModel<SupportEntry> cookDefaultPurchaseList;
    private DefaultListModel<SupportEntry> thirdPartyDefaultPurchaseList;
    private JButton proxyRemoveButton;
    private JButton cookRemoveButton;
    private JButton thirdPartyRemoveButton;

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
        frame.setJMenuBar(menubar);
        JPanel mainPanel = initializeMainPanel();
        initializeAllEntries(mainPanel);
        addSaveLoadMenu();
        addSupportEntriesMenu();
        addSneakerEntriesMenu();
        addRevenueMenu();



    }

    //MODIFIES: THIS
    //EFFECT: add the revenue menu to the menu bar, a revenue menu should have the option to add a new revenue entry
    private void addRevenueMenu() {
        JMenu revenue = new JMenu("revenues");
        JMenuItem addNewRevenue = new JMenuItem("add new revenue");
        JMenuItem viewRevenue = new JMenuItem("view current revenue");
        menubar.add(revenue);
        revenue.add(addNewRevenue);
        addNewRevenue.addActionListener(new AddRevenueListener(revenueList, revenueListModel, removeRevenueButton));
        revenue.add(viewRevenue);
        viewRevenue.addActionListener(new ViewRevenueListener(revenueList));
    }

    //MODIFIES:this
    //EFFECT: add the sneaker menu to the menu bar, a sneaker menu should have the option to add a new sneaker entry
    private void addSneakerEntriesMenu() {
        JMenu sneakers = new JMenu("sneaker entries");
        JMenuItem addSneakerEntry = new JMenuItem("add new sneaker entry");
        sneakers.add(addSneakerEntry);
        menubar.add(sneakers);
        addSneakerEntry.addActionListener(new AddSneakerListener(sneakerPurchaseList, defaultListModelSneaker,
                removeSneakerEntryButton));
    }

    //MODIFIES: this
    //EFFECT: initialize a support entry menu item where the user can add a new entry that's part of the three
    //        support entries
    private void addSupportEntriesMenu() {
        JMenu supportEntries = new JMenu("Support entries");
        menubar.add(supportEntries);
        JMenuItem addProxy = new JMenuItem("add proxy entry");
        JMenuItem addCookGroup = new JMenuItem("add cook group entry entry");
        JMenuItem addThirdPartySolver = new JMenuItem("add third party solver entry");
        supportEntries.add(addProxy);
        supportEntries.add(addCookGroup);
        supportEntries.add(addThirdPartySolver);
        addProxy.addActionListener(new SupportEntryActionListener("proxy", proxyPurchaseList,
                proxyDefualtPurchaseList, proxyRemoveButton));
        addCookGroup.addActionListener(new SupportEntryActionListener("cook group", cookGroupPurchaseList,
                cookDefaultPurchaseList, cookRemoveButton));
        addThirdPartySolver.addActionListener(new SupportEntryActionListener("Third party Solver",
                cookGroupPurchaseList, cookDefaultPurchaseList, thirdPartyRemoveButton));
    }

    //MODIFIES: this
    //EFFECT: create a new menu item on the menu bar that allows the user to load and save the progress they made
    private void addSaveLoadMenu() {
        JMenu file = new JMenu("file");
        menubar.setBackground(Color.WHITE);
        menubar.add(file);
        JMenuItem saveButton = new JMenuItem("save");
        JMenuItem loadButton = new JMenuItem("load");
        file.add(saveButton);
        file.add(loadButton);
        saveButton.addActionListener(new SaveActionListener(proxyPurchaseList, cookGroupPurchaseList,
                thirdPartyCaptchaSolversPurchaseList, sneakerPurchaseList, revenueList, jsonWriterForProxyEntries,
                jsonWriterForCookGroupEntries, jsonWriterForThirdPartySolverEntries,
                jsonWriteForSneakers, jsonWriterForRevenueList));
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAllSavedProgress();
            }
        });
    }

    //MODIFIES: this
    //EFFECT: create main PANEL where all component entries will pasted on to
    private JPanel initializeMainPanel() {
        JPanel mainPanel = new JPanel(new FlowLayout());
        add(mainPanel);
        mainPanel.setBorder(BorderFactory.createTitledBorder("All Entries"));
        return mainPanel;
    }

    //MODIFIES: this
    //EFFECT: create a new Jframe with the title "profit or Loss calculator.
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

    //MODIFIES: this
    //EFFECT: all type of entries with its own corresponding JList, panel, scroll pane and Default list Model
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


    //MODIFIES: this
    //EFFECT: load all saved progress into the gui, and allow the user to see how much they spent in each entry
    private void loadAllSavedProgress() {
        try {
            readingProxyEntryList();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"can not load proxy entry list from" + PROXY_STORE);
        }
        try {
            readingThirdPartySolverEntryList();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"can not load third party solver entry list from"
                    + THIRD_PARTY_SOLVER_STORE);
        }
        try {
            readingCookGroupEntryList();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"can not load cook group entry list from" + COOK_GROUP_STORE);
        }
        try {
            readingSneakersEntryList();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"can not load sneaker entry list from" + COOK_GROUP_STORE);
        }
        tryReadingRevenueList();

    }

    //MODIFIES: this
    //EFFECT: try reading the Revenue list from file , tell the user can not load from its file location if
    //file can not be read
    // This [class/method] references code from GitHub
    // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    private void tryReadingRevenueList() {
        try {
            readRevenueList();
            JOptionPane.showMessageDialog(null, "your revenues have been loaded, you currently have"
                    + " " + revenueList.calculateTotalRevenue() + " " + "dollars");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "can not load sneaker entry list from" + REVENUE_STORE);
        }
    }

    //EFFECT: read revenue list from file and assign it as a revenue list
    // This [class/method] references code from GitHub
    // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    private void readRevenueList() throws IOException {
        revenueList = jsonReaderForRevenueList.read();
        for (Revenue r : revenueList.getRevenues()) {
            revenueListModel.addElement(r);
        }
        JOptionPane.showMessageDialog(null, "your revenue entry list has been loaded");
    }

    //EFFECT: read sneaker list from file and assign it as a sneaker purchase list
    // This [class/method] references code from GitHub
    // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    private void readingSneakersEntryList() throws IOException {
        sneakerPurchaseList = jsonReaderForSneaker.read();
        for (SneakerEntry e : sneakerPurchaseList.getEntries()) {
            defaultListModelSneaker.addElement(e);
        }
        JOptionPane.showMessageDialog(null, "your sneaker entry list has been loaded, your total money spent comes to "
                + sneakerPurchaseList.getTotalMoneySpent());
    }

    //EFFECT: read cook group purchase list from file and assign it as a cook group purchase list
    // This [class/method] references code from GitHub
    // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    private void readingCookGroupEntryList() throws IOException {
        cookGroupPurchaseList = jsonReaderForCookGroup.read(); // need to assign the result to the parameter
        for (SupportEntry e : cookGroupPurchaseList.getEntries()) {
            cookDefaultPurchaseList.addElement(e);
        }
        JOptionPane.showMessageDialog(null, "your cook group entry list has been loaded, "
                + "your total moeny spent comes to" + " " + cookGroupPurchaseList.getTotalMoneySpent());
    }

    //EFFECT: read third part solver purchase list from file and assign it as a third part solver entry list
    // This [class/method] references code from GitHub
    // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    private void readingThirdPartySolverEntryList() throws IOException {
        thirdPartyCaptchaSolversPurchaseList =  jsonReaderForThirdPartySolvers.read();
        for (SupportEntry e : thirdPartyCaptchaSolversPurchaseList.getEntries()) {
            thirdPartyDefaultPurchaseList.addElement(e);
        }
        JOptionPane.showMessageDialog(null, "your third party solver entry list has been loaded"
                + " " + "your total money spent comes to " + thirdPartyCaptchaSolversPurchaseList.getTotalMoneySpent());
    }

    //EFFECT: read proxy entry purchase list from file and assign it as a proxy purchjase list
    // This [class/method] references code from GitHub
    // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    private void readingProxyEntryList() throws IOException {
        proxyPurchaseList = jsonReaderForProxy.read();
        for (SupportEntry e : proxyPurchaseList.getEntries()) {
            proxyDefualtPurchaseList.addElement(e);
        }
        JOptionPane.showMessageDialog(null, "your proxy entry list has been loaded, your total money spent is "
                + proxyPurchaseList.getTotalMoneySpent());

    }

    //MODIFIES: this
    //EFFECT: create a new revenue panel on the main panel. On the Revenue Panel, there should be a remove and add entry
    // button, and a place to show all the existing revenue entries
    private void initializeNewRevenuePanel(RevenueList revenues, JPanel mainPanel) {
        revenueListModel = new DefaultListModel<>();
        revenueJList = new JList<>(revenueListModel);
        revenueJList.setCellRenderer(new RevenueCellRenderer());
        revenueJList.setVisibleRowCount(2);

        JPanel revenuePanel = new JPanel(new BorderLayout());
        revenuePanel.setPreferredSize(new Dimension(600, 200));
        JPanel buttonPanel = new JPanel();
        revenuePanel.setBorder(BorderFactory.createTitledBorder("revenue"));
        mainPanel.add(revenuePanel);
        revenuePanel.add(buttonPanel, BorderLayout.SOUTH);
        JButton addRevenueButton = new JButton("add revenue");
        removeRevenueButton = new JButton("remove revenue");
        JButton viewOverallRevenueButton = new JButton("view revenue");
        buttonPanel.add(addRevenueButton);
        buttonPanel.add(removeRevenueButton);
        buttonPanel.add(viewOverallRevenueButton);
        revenuePanel.add(revenueJList);
        revenuePanel.add(new JScrollPane(revenueJList, VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_AS_NEEDED));
        addRevenueButton.addActionListener(new AddRevenueListener(revenues, revenueListModel, removeRevenueButton));
        removeRevenueButton.addActionListener(new RemoveButtonListener(revenueJList, revenueListModel,
                revenues, removeRevenueButton));
        viewOverallRevenueButton.addActionListener(new ViewRevenueListener(revenues));

    }

    //MODIFIES: this
    //EFFECT: create a new sneaker panel on the main panel. On the Revenue Panel, there should be a remove and add entry
    // button, and a place to show all the existing sneaker entries
    private void initializeNewSneakerPanel(SneakerPurchaseList sneakerPurchaseList, String sneaker, JPanel mainPanel) {
        defaultListModelSneaker = new DefaultListModel<>();
        sneakerEntryJList = new JList<>(defaultListModelSneaker);
        sneakerEntryJList.setCellRenderer(new SneakerCellRenderer());
        sneakerEntryJList.setVisibleRowCount(3);

        sneakerPanel = new JPanel(new BorderLayout());
        sneakerPanel.setPreferredSize(new Dimension(600, 200));
        sneakerPanel.setBorder(BorderFactory.createTitledBorder("sneaker enrty"));
        JPanel buttonPanel = new JPanel();
        addSneakerEntryButton = new JButton("add" + " " + sneaker + " " + "entry");
        removeSneakerEntryButton = new JButton("remove" + " " + sneaker + " " + "entry");

        mainPanel.add(sneakerPanel);
        buttonPanel.add(addSneakerEntryButton);
        buttonPanel.add(removeSneakerEntryButton);
        sneakerPanel.add(buttonPanel, BorderLayout.SOUTH);
        sneakerPanel.add(sneakerEntryJList);
        sneakerPanel.add(new JScrollPane(sneakerEntryJList, VERTICAL_SCROLLBAR_ALWAYS,
                HORIZONTAL_SCROLLBAR_AS_NEEDED));
        addSneakerEntryButton.addActionListener(new AddSneakerListener(sneakerPurchaseList,
                defaultListModelSneaker, removeSneakerEntryButton));
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
        assignAppropriatePurchaseList(supportEntryList, defaultListModelForSupportEntries);
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
        JButton removeEntryButton = new JButton("remove" + " " + "entry");
        assignAppropriateRemoveButton(supportEntryList, removeEntryButton);
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


        addEntryButton.addActionListener(new SupportEntryActionListener(typeOfEntry, supportEntryList,
                 defaultListModelForSupportEntries, removeEntryButton));
        removeEntryButton.addActionListener(new RemoveListener(defaultListModelForSupportEntries,
                entryJlist, removeEntryButton, supportEntryList));
    }

    //MODIFIES: this
    //EFFECT: assign the remove button corresponding type of the support entry list as each type needs its own remove
    //button
    private void assignAppropriateRemoveButton(SupportEntryList<? extends SupportEntry> supportEntryList,
                                               JButton removeEntryButton) {
        if (supportEntryList.getType() == EntryType.PROXY) {
            proxyRemoveButton = removeEntryButton;
        } else if (supportEntryList.getType() == EntryType.CookGroup) {
            cookRemoveButton = removeEntryButton;
        } else {
            thirdPartyRemoveButton = removeEntryButton;
        }
    }

    //Modifies: this
    //EFFECT: assign the corresponding purchase list that has the same type as the support entry list.
    private void assignAppropriatePurchaseList(SupportEntryList<? extends SupportEntry> supportEntryList,
                                               DefaultListModel<SupportEntry> defaultListModelForSupportEntries) {
        if (supportEntryList.getType() == EntryType.PROXY) {
            proxyDefualtPurchaseList = defaultListModelForSupportEntries;
        } else if (supportEntryList.getType() == EntryType.CookGroup) {
            cookDefaultPurchaseList = defaultListModelForSupportEntries;
        } else {
            thirdPartyDefaultPurchaseList = defaultListModelForSupportEntries;
        }
    }


    //MODIFIES: this
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

