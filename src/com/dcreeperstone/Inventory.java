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

import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author Dr. Hoss
 */
public class Inventory {
    private final String name;
    private final HashSet<Item> inventory;
    
    private static final int MAX_LINE_LENGTH = 80;
    
    public Inventory(String inName) {
        inventory = new HashSet<>();
        name = inName;
    }
    
    public String getName() {
        return name;
    }
    
    public void addNewItem(Item inItem) {
        Item searchItem;
        Iterator<Item> it = inventory.iterator();
        boolean itemFound = false;
        while (it.hasNext()) {
            searchItem = it.next();
            if (searchItem.getInfo().equalsIgnoreCase(inItem.getInfo())) {
                inventory.forEach(item -> {
                    if (item.getInfo().equalsIgnoreCase(inItem.getInfo()))
                        item.addToQuantity(1);
                });
                itemFound = true;
            }
        }
        if (!itemFound)
            inventory.add(inItem);
    }
    
    public void addToItem(String key, int amount) {
        Item searchItem;
        boolean itemFound;
        
        itemFound = false;
        Iterator<Item> it = inventory.iterator();
        while (it.hasNext() && !itemFound) {
            searchItem = it.next();
            if (searchItem.getInfo().equalsIgnoreCase(key)) {
                inventory.forEach(item -> {
                    if (item.getInfo().equalsIgnoreCase(key))
                        item.addToQuantity(amount);
                });
                itemFound = true;
            }
        }
        if (!itemFound)
            System.out.println("Item could not be found in the inventory.");
    }
    
    public void removeItem(String key) {
        Item searchItem;
        boolean itemFound;
        
        itemFound = false;
        Iterator<Item> it = inventory.iterator();
        while (it.hasNext() && !itemFound) {
            searchItem = it.next();
            if (searchItem.getInfo().equalsIgnoreCase(key)) {
                inventory.remove(searchItem);
                itemFound = true;
            }
        }
        if (!itemFound)
            System.out.println("Item could not be found in the inventory.");
    }
    
    public void removeFromItem(String key, int amount) {
        Item searchItem;
        boolean itemFound;
        
        itemFound = false;
        Iterator<Item> it = inventory.iterator();
        while (it.hasNext() && !itemFound) {
            searchItem = it.next();
            if (searchItem.getInfo().equalsIgnoreCase(key)) {
                inventory.forEach(item -> {
                    if (item.getInfo().equalsIgnoreCase(key))
                        item.removeFromQuantity(amount);
                });
                itemFound = true;
            }
        }
        if (!itemFound)
            System.out.println("Item could not be found in the inventory.");
    }
    
    public void displayAllItems() {
        Iterator<Item> it = inventory.iterator();
        Item currItem;
        
        if (it.hasNext()) {
            for (int i = 0; i < (MAX_LINE_LENGTH - name.length()) / 2; i++)
                System.out.print("=");
            System.out.print(name);
            if (name.length() % 2 == 0)
                for (int i = 0; i < (MAX_LINE_LENGTH - name.length()) / 2; i++)
                    System.out.print("=");
            else
                for (int i = 0; i < (MAX_LINE_LENGTH - name.length()) / 2 + 1; 
                        i++)
                    System.out.print("=");
            System.out.println();
            System.out.format("%-40s%40s\n", "ITEM", "QUANTITY");
            for (int i = 0; i < MAX_LINE_LENGTH; i++)
                System.out.print("-");
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
}
