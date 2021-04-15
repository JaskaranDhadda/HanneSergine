package controllers;

import javax.swing.*;
import java.awt.*;

public class BaseController {

    /**
     * Brings up a dialog that displays an information message.
     *
     * @param message The message itself.
     * @param title The title of the dialog windows.
     */
    public void displayInfo(String message, String title) {
        JOptionPane.showMessageDialog(new JFrame(), message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Brings up a dialog that displays an error message.
     *
     * @param message The message itself.
     * @param title The title of the dialog windows.
     */
    public void displayError(String message, String title) {
        JOptionPane.showMessageDialog(new JFrame(), message, title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Brings up a dialog that asks the user for confirmation.
     *
     * @param message The message itself.
     * @param title The title of the dialog windows.
     */
    public boolean getConfirmation(String message, String title) {
        int dialogResult = JOptionPane.showConfirmDialog(new JFrame(), message, title, JOptionPane.YES_NO_OPTION);
        return dialogResult == JOptionPane.YES_OPTION;
    }

    /**
     * Brings up a dialog that get the user input.
     *
     * @param message The message itself.
     */
    public String getUserInput(String message) {
        String userInput = "";
        try {
            userInput = JOptionPane.showInputDialog(message);

            if(userInput == null) {
                userInput = "";
            }
        } catch (HeadlessException e) {
            this.displayError("Apologies, an error occurred while getting the user input.", "Get USer Input Error");
            e.printStackTrace();
        }

        return userInput;
    }
}
