package ui;

import model.*;
import model.investment.*;

import java.util.Scanner;


public class GrossCalc {

    private CookGroupPurchaseList cookGroupPurchaseList;
    private ProxyPurchaseList proxyPurchaseList;
    private SneakerPurchaseList sneakerPurchaseList = new SneakerPurchaseList();
    private ThirdPartyCaptchaSolversPurchaseList solversPurchaseList = new ThirdPartyCaptchaSolversPurchaseList();
    private RevenueList revenueList;
    private Scanner input;

    // EFFECTS: runs the profit or loss calculator app
    public GrossCalc() {
        runProfitOrLoss();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runProfitOrLoss() {
        boolean keepGoing = true;
        String command = null;

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
    private void processCommand(String command) {
        if (command.equals("i")) {
            addSupportInvestment();
        } else if (command.equals("s")) {
            addSneakerMainEntry();
        } else if (command.equals("r")) {
            runRevenue();
        } else if (command.equals("v")) {
            viewRevenue();
        } else if (command.equals("c")) {
            calculateProfitOrLoss();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void calculateProfitOrLoss() {
        double moneySpentOnCookGroups = cookGroupPurchaseList.getTotalMoneySpent();
        double moneySpentOnSneakers = sneakerPurchaseList.getTotalMoneySpent();
        double moneySpentOnProxies = proxyPurchaseList.getTotalMoneySpent();
        double moneySpentOnThirdPartCaptchaSolvers = solversPurchaseList.getTotalMoneySpent();

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

    private void viewRevenue() {
        System.out.println("your total revenue is: " + revenueList.calculateTotalRevenue());
    }

    private void runRevenue() {
        System.out.println("please enter the new revenue made");
        double revenueMade = input.nextDouble();
        Revenue revenue = new Revenue(revenueMade);
        revenueList.addNewRevenue(revenue);
        System.out.println("your new revenue has been added to your revenue list");
        System.out.println("your total revenue is now: " + revenueList.calculateTotalRevenue());

    }


    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        cookGroupPurchaseList = new CookGroupPurchaseList();
        proxyPurchaseList = new ProxyPurchaseList();
        sneakerPurchaseList = new SneakerPurchaseList();
        solversPurchaseList = new ThirdPartyCaptchaSolversPurchaseList();
        revenueList = new RevenueList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from the following entry list types:");
        System.out.println("\ti -> add Support Investment");
        System.out.println("\ts -> add Sneaker Investment");
        System.out.println("\tr -> add revenue");
        System.out.println("\tv -> view revenue");
        System.out.println("\tc -> calculate Profit or Loss");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: conducts a deposit transaction.
    private void addSupportInvestment() {
        SupportEntryList selectedPurchaseTypeList = purchaseListTypeSelection();
        if (selectedPurchaseTypeList.getType() == EntryType.PROXY) {
            addProxyEntry(selectedPurchaseTypeList);
        } else if (selectedPurchaseTypeList.getType() == EntryType.CookGroup) {
            addCookGroupEntry(selectedPurchaseTypeList);
        } else if (selectedPurchaseTypeList.getType() == EntryType.ThirdPartSolver) {
            addThirdPartySolverEntry(selectedPurchaseTypeList);
        }


        printList(selectedPurchaseTypeList);
        System.out.println("your total price paid is " + ": " + selectedPurchaseTypeList.getTotalMoneySpent());
    }

    private void addSneakerMainEntry() {
        SneakerPurchaseList sneakerPurchaseList = sneakerEntrySelection();
        addSneakerEntry(sneakerPurchaseList);
        printList(sneakerPurchaseList);
        System.out.println("your total price paid is " + ": " + sneakerPurchaseList.getTotalMoneySpent());
    }

    private void addSneakerEntry(SneakerPurchaseList sneakerPurchaseList) {
        System.out.print("enter name of sneaker: ");
        String entryName = input.next();
        System.out.println("enter price of sneaker: ");
        double price = input.nextDouble();
        System.out.print("enter quantity of sneaker: ");
        int quantity = input.nextInt();
        SneakerEntry entry = new SneakerEntry(entryName, price, quantity);
        if (entry.getQuantityBought() <= 0) {
            System.out.println("please enter a valid quantity");
        } else {
            sneakerPurchaseList.addEntry(entry);
            System.out.println("your sneaker entry list has been updated");
        }
    }

    private SneakerPurchaseList sneakerEntrySelection() {
        String selection = "";  // force entry into loop

        while (!(selection.equals("s"))) {
            System.out.println("s sneakerPurchaseList");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        return sneakerPurchaseList;

    }


    private void addThirdPartySolverEntry(SupportEntryList selectedPurchaseType) {
        System.out.print("enter name of Third Party Solver: ");
        String entryName = input.next();
        System.out.println("enter price of Third Party Solver: ");
        double price = input.nextDouble();
        ThirdPartySolverEntry thirdPartySolverEntry = new ThirdPartySolverEntry(entryName, price);
        selectedPurchaseType.addEntry(thirdPartySolverEntry);
        System.out.println("your third party solver entry list has been updated");
    }

    private void addCookGroupEntry(SupportEntryList selectedPurchaseType) {
        System.out.print("enter name of Cook Group: ");
        String entryName = input.next();
        System.out.println("enter price of Cook Group: ");
        double price = input.nextDouble();
        CookGroupSubscriptionEntry cg = new CookGroupSubscriptionEntry(entryName, price);
        selectedPurchaseType.addEntry(cg);
        System.out.println("your cook group entry list has been updated");
    }

    private void addProxyEntry(SupportEntryList selectedPurchaseType) {
        System.out.print("enter name of proxy: ");
        String entryName = input.next();
        System.out.println("enter price of proxy: ");
        double price = input.nextDouble();
        ProxyEntry proxy = new ProxyEntry(entryName, price);
        selectedPurchaseType.addEntry(proxy);
        System.out.println("your proxy group entry list has been updated");
    }

    private void printList(SupportEntryList selectedPurchaseType) {
        System.out.println("purchaseList" + ":  " + selectedPurchaseType.toString());
    }

    private void printList(SneakerPurchaseList sneakerPurchaseList) {
        System.out.println("purchaseList" + ":  " + sneakerPurchaseList.toString());

    }


    // EFFECTS: selecting either remove or add will return the the proxy purchase list
    private SupportEntryList purchaseListTypeSelection() {
        String selection = "";  // force entry into loop

        while (!(selection.equals("p") || selection.equals("t") || selection.equals("c"))) {
            System.out.println("p proxyPurchaseList");
            System.out.println("t thirdPartSolverPurchaseList");
            System.out.println("c cookGroupPurchaseList");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("p")) {
            return proxyPurchaseList;
        } else if (selection.equals("t")) {
            return solversPurchaseList;
        }
        return cookGroupPurchaseList;

    }
}







