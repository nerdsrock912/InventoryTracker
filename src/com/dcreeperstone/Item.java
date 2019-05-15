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

/**
 * The {@code Item} class is designed to hold attributes pertaining to a 
 * real-world item object. Each {@code Item} is given a description and a 
 * quantity count for usage in the {@code Inventory} class.
 * @author Dr. Hoss
 */
public class Item {
    private final String info;
    private int quantity;
    
    /**
     * Constructor that initializes the {@code info} attribute with the 
     * specified value and initializes the {@code quantity} attribute to 0.
     * @param inInfo The information describing the {@code Item}.
     */
    public Item(String inInfo) {
        this(inInfo, 0);
    }
    
    /**
     * Constructor that initializes a new {@code Item} instance with the 
     * specified info and quantity properties.
     * @param inInfo The information describing the {@code Item}.
     * @param inQuantity The initial quantity of the {@code Item}.
     */
    public Item(String inInfo, int inQuantity) {
        if (quantity < 0)
            throw new IllegalArgumentException("Cannot have a negative number "
                    + "of items.");
        info = inInfo;
        quantity = inQuantity;
    }
    
    /**
     * Gets the value of the {@code info} attribute.
     * @return The {@code info} attribute.
     */
    public String getInfo() {
        return info;
    }
    
    /**
     * Gets the value of the {@code quantity} attribute.
     * @return The {@code quantity} attribute.
     */
    public int getQuantity() {
        return quantity;
    }
    
    /**
     * Adds the specified amount to the {@code quantity} attribute.
     * @param amount The amount to add to the {@code quantity} attribute.
     */
    public void addToQuantity(int amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Cannot add negative number of "
                    + "items.");
        quantity += amount;
    }
    
    /**
     * Removes the specified amount from the {@code quantity} attribute 
     * if possible.
     * @param amount The amount to remove from the {@code quantity} attribute.
     */
    public void removeFromQuantity(int amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Cannot remove negative number "
                    + "of items.");
        if (amount > quantity)
            throw new IllegalStateException("Cannot remove more items than "
                    + "exists.");
        quantity -= amount;
    }
    
    /**
     * Resets the {@code quantity} attribute to 0.
     */
    public void resetQuantity() {
        quantity = 0;
    }
    
    @Override
    public String toString() {
        String res;
        
        res = String.format("Item info:  %s\nQuantity:  %d\n", info, quantity);
        
        return res;
    }
}
