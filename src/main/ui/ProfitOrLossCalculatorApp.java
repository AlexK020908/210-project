package ui;

import model.*;
import model.investment.*;
import persistance.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;



//Profit or Loss calculator application
public class ProfitOrLossCalculatorApp {

    public static final String SNEAKER_STORE = "./data/sneakerInvestment.json";
    public static final String PROXY_STORE = "./data/proxyEntryList.json";
    public static final String COOK_GROUP_STORE = "./data/cookGroupEntryList.json";
    public static final String THIRD_PARTY_SOLVER_STORE = "./data/thirdPartySolversEntryList.json";
    public static final String REVENUE_STORE = "./data/revenueEntryList.json";
    //declare lists that will be used in the profit or loss logger
    private CookGroupPurchaseList cookGroupPurchaseList;
    private ProxyPurchaseList proxyPurchaseList;
    private SneakerPurchaseList sneakerPurchaseList = new SneakerPurchaseList();
    private ThirdPartyCaptchaSolversPurchaseList thirdPartyCaptchaSolversPurchaseList =
            new ThirdPartyCaptchaSolversPurchaseList();
    private RevenueList revenueList;
    private Scanner input;
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

    // EFFECTS: runs the profit or loss calculator app
    public ProfitOrLossCalculatorApp() {
        runProfitOrLoss();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    // This [class/method] references code from this [repo/website]
    // Link: [https://github.students.cs.ubc.ca/CPSC210/TellerApp]
    private void runProfitOrLoss() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    // This [class/method] references code from GitHub
    // Link: [https://github.students.cs.ubc.ca/CPSC210/TellerApp]
    private void processCommand(String command) {
        switch (command) {
            case "p" : addProxyEntry(proxyPurchaseList);
            break;

            case"k" : addCookGroupEntry(cookGroupPurchaseList);
            break;

            case"t" : addThirdPartySolverEntry(thirdPartyCaptchaSolversPurchaseList);
            break;

            case"s" : addSneakerMainEntry();
            break;

            case"r" : runRevenue();
            break;

            case"v" : viewRevenue();
            break;

            case"c" : calculateProfitOrLoss();
            break;

            case "save" : saveProgress();
            break;

            case "load" : loadAllSavedProgress();
            break;

            default :
                System.out.println("please enter the correct command");
        }
    }

    //MODIFIES: this
    //EFFECT: load all support entry lists, sneaker purchase list and revenue list from files.
    // This [class/method] references code from GitHub
    // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    private void loadAllSavedProgress() {
        try {
            readingProxyEntryList();
        } catch (IOException e) {
            System.out.println("can not load proxy entry list from" + PROXY_STORE);
        }
        try {
            readingThirdPartySolverEntryList();
        } catch (IOException e) {
            System.out.println("can not load Third party solver entry list from" + THIRD_PARTY_SOLVER_STORE);
        }
        try {
            readingCookGroupEntryList();
        } catch (IOException e) {
            System.out.println("can not load cook group entry list from" + COOK_GROUP_STORE);
        }
        tryReadingSneakerEntryList();
        tryReadingRevenueList();

    }

    //EFFECT: read the sneaker entry list and catch the exceptions.
    private void tryReadingSneakerEntryList() {
        try {
            readingSneakersEntryList();
        } catch (IOException e) {
            System.out.println("can not load sneaker entry list from" + COOK_GROUP_STORE);
        } catch (NameException e) {
            System.out.println("name is invalid");
        } catch (AmountException e) {
            System.out.println("amount is invalid");
        } catch (QuantityException e) {
            System.out.println("quantity is invallid");
        }
    }

    //MODIFIES: this
    //EFFECT: try reading the Revenue list from file , tell the user can not load from its file location if
    //file can not be read
    // This [class/method] references code from GitHub
    // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    private void tryReadingRevenueList() {
        try {
            readRevenueList();
        } catch (IOException e) {
            System.out.println("can not load sneaker entry list from" + REVENUE_STORE);
        } catch (AmountException e) {
            System.out.println("invalid revenue");
        }
    }

    //EFFECT: read revenue list from file and assign it as a revenue list
    // This [class/method] references code from GitHub
    // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    private void readRevenueList() throws IOException, AmountException {
        revenueList = jsonReaderForRevenueList.read();
        System.out.println("your revenue List has been loaded from : " + REVENUE_STORE);
        System.out.println("your revenue total is : " + revenueList.calculateTotalRevenue());
    }

    //EFFECT: read sneaker list from file and assign it as a sneaker purchase list
    // This [class/method] references code from GitHub
    // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    private void readingSneakersEntryList() throws IOException, NameException, AmountException, QuantityException {
        sneakerPurchaseList = jsonReaderForSneaker.read();
        System.out.println("your sneaker entry list  has been loaded from  " + SNEAKER_STORE);
        printList(sneakerPurchaseList);
    }

    //EFFECT: read cook group purchase list from file and assign it as a cook group purchase list
    // This [class/method] references code from GitHub
    // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    private void readingCookGroupEntryList() throws IOException {
        cookGroupPurchaseList = jsonReaderForCookGroup.read(); // need to assign the result to the parameter
        System.out.println("your Cook group entry list  has been loaded from " + COOK_GROUP_STORE);
        printList(cookGroupPurchaseList);
    }

    //EFFECT: read third part solver purchase list from file and assign it as a third part solver entry list
    // This [class/method] references code from GitHub
    // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    private void readingThirdPartySolverEntryList() throws IOException {
        thirdPartyCaptchaSolversPurchaseList =  jsonReaderForThirdPartySolvers.read();
        System.out.println("your Third part solver list has been loaded from " + THIRD_PARTY_SOLVER_STORE);
        printList(thirdPartyCaptchaSolversPurchaseList);
    }

    //EFFECT: read proxy entry purchase list from file and assign it as a proxy purchjase list
    // This [class/method] references code from GitHub
    // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    private void readingProxyEntryList() throws IOException {
        proxyPurchaseList = jsonReaderForProxy.read();
        System.out.println("your proxy entry list has been loaded from " + PROXY_STORE);
        printList(proxyPurchaseList);
    }


    //EFFECT: write all progress in support invesmtnet, sneaker investment and revenue to corresponding files
    // This [class/method] references code from GitHub
    // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    private void saveProgress() {
        try {
            saveFile(proxyPurchaseList);
        } catch (FileNotFoundException e) {
            System.out.println("can not find and write on file  :" + PROXY_STORE);
        }
        try {
            saveFile(thirdPartyCaptchaSolversPurchaseList);
        } catch (FileNotFoundException e) {
            System.out.println("can not find and write on file  :" + THIRD_PARTY_SOLVER_STORE);
        }
        try {
            saveFile(cookGroupPurchaseList);
        } catch (FileNotFoundException e) {
            System.out.println("can not find and write on file  :" + COOK_GROUP_STORE);
        }
        try {
            saveFile(sneakerPurchaseList);
        } catch (FileNotFoundException e) {
            System.out.println("can not find and write on file  :" + SNEAKER_STORE);
        }
        tryToSaveRevenueList();
    }

    //EFFECT: trying to write revenue list to the corresponding file destination
    // This [class/method] references code from GitHub
    // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    private void tryToSaveRevenueList() {
        try {
            saveFile(revenueList);
        } catch (FileNotFoundException e) {
            System.out.println("can not find and write on file  :" + REVENUE_STORE);
        }
    }

    //EFFECT: writing revenue List to the corresponding file
    // This [class/method] references code from GitHub
    // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    private void saveFile(RevenueList revenueList) throws FileNotFoundException {
        jsonWriterForRevenueList.open();
        jsonWriterForRevenueList.write(revenueList);
        jsonWriterForRevenueList.close();
        System.out.println("saved your revenue list to " + REVENUE_STORE);

    }

    //EFFECT: writing sneaker List to the corresponding file
    // This [class/method] references code from GitHub
    // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    private void saveFile(SneakerPurchaseList sneakerPurchaseList) throws FileNotFoundException {
        jsonWriteForSneakers.open();
        jsonWriteForSneakers.write(sneakerPurchaseList);
        jsonWriteForSneakers.close();
        System.out.println("saved your sneaker purchase entry list" + "to" + SNEAKER_STORE);
    }

    //EFFECT: writing proxy entry List to the corresponding file
    // This [class/method] references code from GitHub
    // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    private void saveFile(ProxyPurchaseList proxyPurchaseList) throws FileNotFoundException {
        jsonWriterForProxyEntries.open();
        jsonWriterForProxyEntries.write(proxyPurchaseList);
        jsonWriterForProxyEntries.close();
        System.out.println("saved your proxy purchase entry list" + "to" + PROXY_STORE);
    }

    //EFFECT: writing cook group entry List to the corresponding file
    // This [class/method] references code from GitHub
    // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    private void saveFile(CookGroupPurchaseList cookGroupPurchaseList) throws FileNotFoundException {
        jsonWriterForCookGroupEntries.open();
        jsonWriterForCookGroupEntries.write(cookGroupPurchaseList);
        jsonWriterForCookGroupEntries.close();
        System.out.println("saved your cook group purchase entry list" + "to" + COOK_GROUP_STORE);

    }

    //EFFECT: writing third pary solver entry List to the corresponding file
    // This [class/method] references code from GitHub
    // Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
    private void saveFile(ThirdPartyCaptchaSolversPurchaseList
                                  thirdPartyCaptchaSolversPurchaseList) throws FileNotFoundException {
        jsonWriterForThirdPartySolverEntries.open();
        jsonWriterForThirdPartySolverEntries.write(thirdPartyCaptchaSolversPurchaseList);
        jsonWriterForThirdPartySolverEntries.close();
        System.out.println("saved your third party  entry list" + "to"
                + THIRD_PARTY_SOLVER_STORE);
    }

    //MODIFIES: this
    //EFFECT: add a new sneaker entry and print out the total price paid for all the sneakers.
    private void addSneakerMainEntry() {
        SneakerPurchaseList sneakerPurchaseList = sneakerEntrySelection();
        addSneakerEntry(sneakerPurchaseList);
        printList(sneakerPurchaseList);
        System.out.println("your total price paid so far is " + ": " + sneakerPurchaseList.getTotalMoneySpent());
    }

    //MODIFIES : this
    //EFFECT: prompts user to enter new sneaker entry adds the newly entered sneaker entry to the list of entries,
    //if quantity or price isn't valid, inform the user.
    private void addSneakerEntry(SneakerPurchaseList sneakerPurchaseList) {
        System.out.print("enter name of sneaker: ");
        String entryName = input.next();
        System.out.println("enter price of sneaker: ");
        double price = input.nextDouble();
        System.out.print("enter quantity of sneaker: ");
        int quantity = input.nextInt();
        try {
            SneakerEntry entry = new SneakerEntry(entryName, price, quantity);
            sneakerPurchaseList.addEntry(entry);
        } catch (NameException e) {
            System.out.println("invalid name");
        } catch (AmountException e) {
            System.out.println("invalid amount");
        } catch (QuantityException e) {
            System.out.println("invalid quantity");
        }

    }


    //EFFECT: prompts the user to press s to access to his/her sneaker purchase list
    private SneakerPurchaseList sneakerEntrySelection() {
        String selection = "";  // force entry into loop

        while (!(selection.equals("s"))) {
            System.out.println("s -> select sneakerPurchaseList");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        return sneakerPurchaseList;

    }

    //EFFECT: print out the money the user made without their investment taken into account
    private void viewRevenue() {
        System.out.println("your total revenue so far is: " + revenueList.calculateTotalRevenue());
    }

    //MODIFIES: this
    //EFFECT: CONDUCTS a new revenue entry, and print out the new revenue total
    private void runRevenue() {
        System.out.println("please enter the new revenue made");
        double revenueMade = input.nextDouble();
        Revenue revenue = null;
        try {
            revenue = new Revenue(revenueMade);
        } catch (AmountException e) {
            System.out.println("the revenue is invalid");
        }
        revenueList.addNewRevenue(revenue);
        System.out.println("your new revenue has been added to your revenue list");
        System.out.println("your total revenue so far is now: " + revenueList.calculateTotalRevenue());

    }

    //MODIFIES: this
    //EFFECTS: calculate if the person is making money or not. If they are making money print out their profit and a
    // statement to inform they are making money otherwise print out their loss and a statement indicating they
    // are losing money. If the person is breaking even, inform him/her as well.
    private void calculateProfitOrLoss() {
        double moneySpentOnCookGroups = cookGroupPurchaseList.getTotalMoneySpent();
        double moneySpentOnSneakers = sneakerPurchaseList.getTotalMoneySpent();
        double moneySpentOnProxies = proxyPurchaseList.getTotalMoneySpent();
        double moneySpentOnThirdPartCaptchaSolvers = thirdPartyCaptchaSolversPurchaseList.getTotalMoneySpent();

        double revenueMadeInTotal = revenueList.calculateTotalRevenue();
        double moneySpentTotal = moneySpentOnCookGroups
                + moneySpentOnSneakers + moneySpentOnProxies
                + moneySpentOnThirdPartCaptchaSolvers;

        if (moneySpentTotal > revenueMadeInTotal) {
            System.out.println("you are losing money!");
            System.out.println("you are in a net negative profit of: " + (moneySpentTotal - revenueMadeInTotal));
        } else if (moneySpentTotal < revenueMadeInTotal) {
            System.out.println("congrats! you are making money");
            System.out.println("you are making a positive net profit of: " + (revenueMadeInTotal - moneySpentTotal));
        } else if (moneySpentTotal == revenueMadeInTotal) {
            System.out.println("you are breaking even, you need more sales!");
            System.out.println("you are making a net profit of " + 0);
        }


    }

    // MODIFIES: this
    // EFFECTS: initializes all purchase lists and also the revenue list
    private void init() {
        cookGroupPurchaseList = new CookGroupPurchaseList();
        proxyPurchaseList = new ProxyPurchaseList();
        sneakerPurchaseList = new SneakerPurchaseList();
        thirdPartyCaptchaSolversPurchaseList = new ThirdPartyCaptchaSolversPurchaseList();
        revenueList = new RevenueList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
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
    }

    // EFFECTS: displays menu of options to user.
    private void displayMenu() {
        System.out.println("\nSelect from the following entry list types:");
        System.out.println("\tp -> add Proxy Investment");
        System.out.println("\tk -> add cook group Investment");
        System.out.println("\tt -> add third party solver Investment");
        System.out.println("\ts -> add Sneaker Investment");
        System.out.println("\tr -> add revenue");
        System.out.println("\tv -> view revenue");
        System.out.println("\tc -> calculate Profit or Loss");
        System.out.println("\tsave -> save progress");
        System.out.println("\tload -> load progress");
        System.out.println("\tq -> quit");
    }



    //MODIFIES: this
    //EFFECT: PROMTpS the user to enter a NEW third party solver entry and add it to the corresponding purchase list.
    //if  price isn't valid, inform the user.
    private void addThirdPartySolverEntry(SupportEntryList<ThirdPartyCaptchaSolverEntry> selectedPurchaseType) {
        System.out.print("enter name of Third Party Solver: ");
        String entryName = input.next();
        System.out.println("enter price of Third Party Solver: ");
        double price = input.nextDouble();
        ThirdPartyCaptchaSolverEntry thirdPartySolverEntry = new ThirdPartyCaptchaSolverEntry(entryName, price);
        if (price <= 0) {
            System.out.println("please enter a valid price");
        } else {
            selectedPurchaseType.addEntry(thirdPartySolverEntry);
            System.out.println("your third party solver entry list has been updated");
        }
        printList(thirdPartyCaptchaSolversPurchaseList);
        System.out.println("your total price paid so far"
                + " is " + ": " + thirdPartyCaptchaSolversPurchaseList.getTotalMoneySpent());
    }

    //MODIFIES: this
    //EFFECT: PROMTpS the user to enter a NEW cook group entry and add it to the corresponding purchase list
    //if  price isn't valid, inform the user.
    private void addCookGroupEntry(SupportEntryList<CookGroupSubscriptionEntry> selectedPurchaseType) {
        System.out.print("enter name of Cook Group: ");
        String entryName = input.next();
        System.out.println("enter price of Cook Group: ");
        double price = input.nextDouble();
        CookGroupSubscriptionEntry cg = new CookGroupSubscriptionEntry(entryName, price);
        if (price <= 0) {
            System.out.println("please enter a valid price");
        } else {
            selectedPurchaseType.addEntry(cg);
            System.out.println("your cook group entry list has been updated");
        }
        printList(cookGroupPurchaseList);
        System.out.println("your total price paid so far"
                + " is " + ": " + cookGroupPurchaseList.getTotalMoneySpent());
    }

    //MODIFIES: this
    //EFFECT: PROMTpS the user to enter a NEW proxy entry and add it to the corresponding purchase list
    //if  price isn't valid, inform the user.
    private void addProxyEntry(SupportEntryList<ProxyEntry> selectedPurchaseType) {
        System.out.print("enter name of proxy: ");
        String entryName = input.next();
        System.out.println("enter price of proxy: ");
        double price = input.nextDouble();
        if (price <= 0) {
            System.out.println("please enter a valid price");
        } else {
            ProxyEntry proxy = new ProxyEntry(entryName, price);
            selectedPurchaseType.addEntry(proxy);
            System.out.println("your proxy group entry list has been updated");
        }
        printList(proxyPurchaseList);
        System.out.println("your total price paid so far"
                + " is " + ": " + proxyPurchaseList.getTotalMoneySpent());
    }



    //EFFECT: print out support purchase list on the screen
    private void printList(CookGroupPurchaseList cookGroupPurchaseList) {
        System.out.println("Cook Group Purchase List" + ":  " + cookGroupPurchaseList.toString());
    }

    //EFFECT: print out support purchase list on the screen
    private void printList(ThirdPartyCaptchaSolversPurchaseList thirdPartyCaptchaSolversPurchaseList) {
        System.out.println("Third part captcha solvers list" + ":  " + thirdPartyCaptchaSolversPurchaseList.toString());
    }

    //EFFECT: print out support purchase list on the screen
    private void printList(ProxyPurchaseList proxyPurchaseList) {
        System.out.println("proxy purchase list" + ":  " + proxyPurchaseList.toString());
    }

    //EFFECT: print out sneaker purchase list on the screen
    private void printList(SneakerPurchaseList sneakerPurchaseList) {
        System.out.println("Sneaker purchase list" + ":  " + sneakerPurchaseList.toString());

    }
}









