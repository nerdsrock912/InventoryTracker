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
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Dr. Hoss
 */
public class Inventory {

    private String name;
    private HashSet<Item> inventory;

    private static final int MAX_LINE_LENGTH = 80;

    /**
     *
     * @param inName
     */
    public Inventory(String inName) {
        inventory = new HashSet<>();
        name = inName;
        logChanges("'" + inName + "' inventory created.");
    }

    /**
     * 
     * @param invFile
     */
    public Inventory(File invFile) {
        try {
            String invName;
            Scanner invSC = new Scanner(invFile);
            
            invName = invSC.nextLine();
            inventory = new HashSet<>();
            name = invName;
            logChanges("'" + invName + "' inventory loaded from '" 
                    + invFile.getPath() + "'.");
            loadInventory(invSC);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void loadInventory(Scanner invSC) {
        Item tempItem;
        String tempInfo;
        int tempQty;

        while (invSC.hasNext()) {
            tempInfo = invSC.nextLine();
            tempQty = Integer.parseInt(invSC.nextLine());
            tempItem = new Item(tempInfo, tempQty);
            addNewItem(tempItem);
        }
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param inItem
     */
    public void addNewItem(Item inItem) {
        Item searchItem;
        
        searchItem = search(inItem.getInfo());
        if (searchItem != null)
            inventory.forEach(item -> {
                if (item.getInfo().equalsIgnoreCase(searchItem.getInfo())) {
                    item.addToQuantity(inItem.getQuantity());
                    logChanges("Added " + inItem.getQuantity() + " to item '"
                        + inItem.getInfo()
                        + "' to inventory due to prior existance.");
                }
            });
        else {
            inventory.add(inItem);
            logChanges("Added item '" + inItem.getInfo() + "' with quantity "
                    + inItem.getQuantity() + " to inventory.");
        }
    }

    /**
     *
     * @param key
     * @param amount
     */
    public void addToItem(String key, int amount) {
        Item searchItem;

        searchItem = search(key);
        if (searchItem != null) {
            inventory.forEach(item -> {
                if (item.getInfo().equalsIgnoreCase(key))
                    item.addToQuantity(amount);
            });
            logChanges("Attempted to add " + amount
                    + " to quantity of item '" + searchItem.getInfo()
                    + "'.");
        } else
            System.out.println("Item could not be found in the inventory.");
    }

    /**
     *
     * @param key
     */
    public void removeItem(String key) {
        Item searchItem;

        searchItem = search(key);
        if (searchItem != null) {
            inventory.remove(searchItem);
            logChanges("Removed item '" + searchItem.getInfo()
                    + "' from inventory.");
        } else {
            System.out.println("Item could not be found in the inventory.");
        }
    }

    /**
     *
     * @param key
     * @param amount
     */
    public void removeFromItem(String key, int amount) {
        Item searchItem;
        
        searchItem = search(key);
        if (searchItem != null)
            inventory.forEach(item -> {
                if (item.getInfo().equalsIgnoreCase(searchItem.getInfo())) {
                    item.removeFromQuantity(amount);
                    logChanges("Attempted to remove " + amount
                        + " from quantity of item '" + searchItem.getInfo()
                        + "'.");
                }
            });
        else
            System.out.println("Item could not be found in the inventory.");
    }

    /**
     * 
     * @param key
     * @return 
     */
    public Item search(String key) {
        Item searchItem = null;
        boolean itemFound = false;
        Iterator<Item> it = inventory.iterator();

        while (it.hasNext() && !itemFound) {
            searchItem = it.next();
            if (searchItem.getInfo().equalsIgnoreCase(key))
                itemFound = true;
            else
                searchItem = null;
        }

        return searchItem;
    }

    /**
     *
     * @param key
     */
    public void resetItem(String key) {
        Item searchItem;
        
        searchItem = search(key);
        if (searchItem != null)
            inventory.forEach(item -> {
                if (item.getInfo().equalsIgnoreCase(searchItem.getInfo())) {
                    item.resetQuantity();
                    logChanges("Reset item '" + searchItem.getInfo()
                        + "' quantity to 0.");
                }
            });
        else
            System.out.println("Item could not be found in the inventory.");
    }

    /**
     *
     */
    public void resetAllItems() {
        inventory.forEach(Item::resetQuantity);
        logChanges("Reset all item quantites to 0.");
    }

    /**
     *
     */
    public void clearInventory() {
        inventory.clear();
        logChanges("Removed all items from '" + name + "' inventory.");
    }

    /**
     *
     */
    public void displayAllItems() {
        Iterator<Item> it = inventory.iterator();
        Item currItem;

        if (it.hasNext()) {
            for (int i = 0; i < (MAX_LINE_LENGTH - name.length()) / 2; i++) {
                System.out.print("=");
            }
            System.out.print(name);
            if (name.length() % 2 == 0) {
                for (int i = 0; i < (MAX_LINE_LENGTH - name.length()) / 2; i++)
                    System.out.print("=");
            } else {
                for (int i = 0; i < (MAX_LINE_LENGTH - name.length()) / 2 + 1;
                        i++) {
                    System.out.print("=");
                }
            }
            System.out.println();
            System.out.format("%-40s%40s\n", "ITEM", "QUANTITY");
            for (int i = 0; i < MAX_LINE_LENGTH; i++) {
                System.out.print("-");
            }
            System.out.println();
            while (it.hasNext()) {
                currItem = it.next();
                System.out.format("%-40s%40d\n", currItem.getInfo(),
                        currItem.getQuantity());
            }
        } 
        else
            System.out.println("There are currently no items in the "
                    + "inventory.");
        System.out.println();
    }

    /**
     * 
     * @param path 
     */
    public void saveDataTo(String directory, String fileName) {
        PrintWriter invPW;
        String path;
        
        path = directory + "/" + fileName;
        
        try {
            File file = new File(directory);
            if (!file.exists())
                file.mkdir();
            invPW = new PrintWriter(path);
            invPW.println(name);
            inventory.forEach(item -> {
                invPW.println(item.getInfo());
                invPW.println(item.getQuantity());
            });
            invPW.close();
            logChanges("Inventory saved to '" + path + "'.");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void logChanges(String message) {
        FileWriter logFile;
        PrintWriter logPW;
        try {
            logFile = new FileWriter("log.txt", true);
            logPW = new PrintWriter(logFile);
            logPW.println(message);
            logPW.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
