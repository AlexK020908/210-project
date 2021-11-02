package ui;

import static ui.GUI.createAndShowGUI;

public class Main {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
        new ProfitOrLossCalculatorApp();
    }
}
