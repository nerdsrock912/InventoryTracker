/*
 * Copyright (C) 2019 Dr. Creeperstone Agency
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.dcreeperstone;

import java.io.File;
import java.util.Scanner;

/**
 * The {@code InventoryHandler} class is designed to handle all actions dealing
 * with an {@code Inventory} instance, including updating its contents and
 * saving it to a file.
 * @author Dr. Hoss
 */
public class InventoryHandler {

    private InventoryHandler() {}

    /**
     * Provides the main functionality for processing many {@code Inventory}
     * instances.
     */
    public static void run() {
        Inventory inv;
        Scanner kbd = new Scanner(System.in);
        char runAgain;
        
        do {
            inv = getNewInventory();
            if (inv != null) {
                processInventory(inv);
                saveInventory(inv);
            }
            else
                System.out.println("Error generating new inventory.");
            System.out.print("Process a new inventory? (Y/N) ");
            runAgain = kbd.nextLine().toUpperCase().charAt(0);
        } while (runAgain == 'Y');
    }
    
    /**
     * Gets an {@code Inventory} instance from either a file or creates a new
     * instance.
     * @return A new {@code Inventory} instance based on the user's choice.
     */
    public static Inventory getNewInventory() {
        Inventory inv;
        char startChoice;
        
        startChoice = getStartChoice();
        inv = performStartChoice(startChoice);
        
        return inv;
    }
    
    private static void showStartMenu() {
        System.out.println("START MENU OPTIONS:");
        System.out.println("1. (L)oad an existing inventory from a file.");
        System.out.println("2. (C)reate a new inventory.");
        System.out.println("3. (Q)uit.");
    }
    
    /**
     * Gets the user choice to load an existing {@code Inventory}, create a new
     * {@code Inventory}, or quit the program.
     * @return The user's inventory generation choice.
     */
    public static char getStartChoice() {
        Scanner kbd = new Scanner(System.in);
        char startChoice;
        
        showStartMenu();
        do {
            System.out.print("Enter your choice: ");
            startChoice = kbd.nextLine().toUpperCase().charAt(0);
        } while (startChoice != 'Q' 
                && startChoice != 'L' && startChoice != 'C');
        
        return startChoice;
    }
    
    /**
     * Gets a new {@code Inventory} instance from a file or creates one.
     * @param startChoice The user's inventory generation choice.
     * @return A new {@code Inventory} instance based on the choice provided.
     */
    public static Inventory performStartChoice(char startChoice) {
        Inventory inv;
        
        inv = null;
        switch (startChoice) {
            case 'L':   inv = loadExistingInventory();
                        break;
            case 'C':   inv = createNewInventory();
                        break;
            case 'Q':   System.exit(0);
                        break;
        }
        
        return inv;
    }
    
    /**
     * Generates a new {@code Inventory} instance using the user specified path.
     * @return An {@code Inventory} instance with contents loaded from a file.
     */
    public static Inventory loadExistingInventory() {
        File invFile;
        Scanner kbd = new Scanner(System.in);
        Inventory inv;
        String invPath;
        
        inv = null;
        System.out.print("Enter the path where the inventory is found: ");
        invPath = kbd.nextLine();
        invFile = new File(invPath);
        if (invFile.exists())
            inv = new Inventory(invFile);
        
        return inv;
    }
    
    /**
     * Generates a new {@code Inventory} instance using the user specified name.
     * @return A new {@code Inventory} with the specified name.
     */
    public static Inventory createNewInventory() {
        Scanner kbd = new Scanner(System.in);
        Inventory inv;
        String invName;
        
        System.out.print("Enter a name for the inventory: ");
        invName = kbd.nextLine();
        inv = new Inventory(invName);
        
        return inv;
    }
    
    /**
     * Repeatedly gets the user's choice to manipulate the provided 
     * {@code Inventory} instance.
     * @param inv The {@code Inventory} to process continually.
     */
    public static void processInventory(Inventory inv) {
        int userChoice;
        
        do {
            userChoice = getUserChoice();
            performChoice(inv, userChoice);
        } while (userChoice != 10);
    }
    
    private static void showOperationMenu() {
        System.out.println("Inventory operations are as follows:");
        System.out.println("1.  Add a new item to the inventory.");
        System.out.println("2.  Add a quantity to an existing item.");
        System.out.println("3.  Remove a quantity from an existing item.");
        System.out.println("4.  Remove an existing item from the inventory.");
        System.out.println("5.  Reset an existing item quantity.");
        System.out.println("6.  Reset all item quantities.");
        System.out.println("7.  Clear all items from the inventory.");
        System.out.println("8.  Display all existing items in the inventory.");
        System.out.println("9.  Search for an existing item.");
        System.out.println("10.  Exit and save inventory to a file.");
    }
    
    /**
     * Gets the user's choice to add/remove an {@code Item}, add/remove a 
     * quantity to/from an {@code Item}, reset {@code Item} instances, clear the
     * {@code Inventory} list, display all {@code Item} instances, search for
     * an {@code Item} instance, or exit and save the {@code Inventory} contents
     * to a file.
     * @return The user manipulation choice.
     */
    public static int getUserChoice() {
        Scanner kbd = new Scanner(System.in);
        int userChoice;
        
        showOperationMenu();
        do {
            System.out.print("Enter your choice: ");
            userChoice = kbd.nextInt();
        } while (userChoice < 1 || userChoice > 10);
        
        return userChoice;
    }
    
    /**
     * Performs the desired choice provided by the user on the given 
     * {@code Inventory} instance.
     * @param inv The {@code Inventory} to process.
     * @param userChoice The operation chosen by the user.
     */
    public static void performChoice(Inventory inv, int userChoice) {
        switch (userChoice) {
            case 1:     addNewItemToInventory(inv);
                        break;
            case 2:     addToInventoryItem(inv);
                        break;
            case 3:     removeFromInventoryItem(inv);
                        break;
            case 4:     removeInventoryItem(inv);
                        break;
            case 5:     resetInventoryItem(inv);
                        break;
            case 6:     inv.resetAllItems();
                        break;
            case 7:     inv.clearInventory();
                        break;
            case 8:     inv.displayAllItems();
                        break;
            case 9:     searchForInventoryItem(inv);
                        break;
            case 10:    saveInventory(inv);
                        System.exit(0);
                        break;
        }
    }
    
    /**
     * Saves the contents of the {@code Inventory} to a file.
     * @param inv The {@code Inventory} instance to be processed.
     */
    public static void saveInventory(Inventory inv) {
        Scanner kbd = new Scanner(System.in);
        String directory, fileName;
        
        System.out.print("Enter the save directory for the inventory: ");
        directory = kbd.nextLine();
        System.out.print("Enter the save name for the inventory: ");
        fileName = kbd.nextLine();
        inv.saveDataTo(directory, fileName);
    }

    /**
     * Searches the list of the {@code Inventory} instance for a desired
     * {@code Item}.
     * @param inv 
     */
    public static void searchForInventoryItem(Inventory inv) {
        Scanner kbd = new Scanner(System.in);
        Item searchItem;
        String key;
        
        System.out.print("What item do you want to search for? ");
        key = kbd.nextLine();
        searchItem = inv.search(key);
        if (searchItem != null)
            System.out.println("Item found: \n" + searchItem);
        else
            System.out.println("Item could not be found in the inventory.");
    }

    /**
     * Allows the user to add a new {@code Item} instance to the list of the
     * {@code Inventory} instance.
     * @param inv The {@code Inventory} to be processed.
     */
    public static void addNewItemToInventory(Inventory inv) {
        Scanner kbd = new Scanner(System.in);
        Item inItem;
        String inInfo;
        int inQuantity;
        
        System.out.print("Enter the item's info: ");
        inInfo = kbd.nextLine();
        System.out.print("Enter the item quantity: ");
        inQuantity = kbd.nextInt();
        inItem = new Item(inInfo, inQuantity);
        inv.addNewItem(inItem);
    }

    /**
     * Allows the user to add a specified quantity to a desired {@code Item}
     * instance in the list if it exists.
     * @param inv The {@code Inventory} to be processed.
     */
    public static void addToInventoryItem(Inventory inv) {
        Scanner kbd = new Scanner(System.in);
        String key;
        int inQuantity;
        
        System.out.print("Enter the item name to add a quantity to: ");
        key = kbd.nextLine();
        System.out.print("Enter the amount to add to the item: ");
        inQuantity = kbd.nextInt();
        inv.addToItem(key, inQuantity);
    }

    /**
     * Allows the user to remove a specified quantity from a desired 
     * {@code Item} instance in the list if it exists.
     * @param inv The {@code Inventory} to be processed.
     */
    public static void removeFromInventoryItem(Inventory inv) {
        Scanner kbd = new Scanner(System.in);
        String key;
        int inQuantity;
        
        System.out.print("Enter the item name to remove a quantity from: ");
        key = kbd.nextLine();
        System.out.print("Enter the amount to remove the item: ");
        inQuantity = kbd.nextInt();
        inv.removeFromItem(key, inQuantity);
    }

    /**
     * Allows the user to remove a desired {@code Item} instance from the list 
     * if it exists.
     * @param inv The {@code Inventory} to be processed.
     */
    public static void removeInventoryItem(Inventory inv) {
        Scanner kbd = new Scanner(System.in);
        String key;
        
        System.out.print("Enter the item name to remove from the inventory: ");
        key = kbd.nextLine();
        inv.removeItem(key);
    }

    /**
     * Allows the user to reset the quantity of a desired {@code Item} instance
     * in the list if it exists.
     * @param inv The {@code Inventory} to be processed.
     */
    public static void resetInventoryItem(Inventory inv) {
        Scanner kbd = new Scanner(System.in);
        String key;
        
        System.out.print("Enter the item name to reset the quantity of: ");
        key = kbd.nextLine();
        inv.resetItem(key);
    }
}
