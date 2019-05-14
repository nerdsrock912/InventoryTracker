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

import java.util.Scanner;

/**
 *
 * @author video
 */
public class InventoryTracker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Inventory inv;
        
        inv = getNewInventory();
        processInventory(inv);
        saveInventory(inv);
    }
    
    public static Inventory getNewInventory() {
        Scanner kbd = new Scanner(System.in);
        Inventory inv;
        String invName;
        
        System.out.print("Enter a name for the inventory: ");
        invName = kbd.nextLine();
        inv = new Inventory(invName);
        
        return inv;
    }
    
    public static void processInventory(Inventory inv) {
        
    }
    
    public static void showMenu() {
        
    }
    
    public static void performChoice(Inventory inv, int userChoice) {
        
    }
    
    public static void saveInventory(Inventory inv) {
        
    }
}
