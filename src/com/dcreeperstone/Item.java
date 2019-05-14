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
 *
 * @author Dr. Hoss
 */
public class Item {
    private final String info;
    private int quantity;
    
    /**
     * 
     * @param inInfo 
     */
    public Item(String inInfo) {
        this(inInfo, 0);
    }
    
    /**
     * 
     * @param inInfo
     * @param inQuantity 
     */
    public Item(String inInfo, int inQuantity) {
        if (quantity < 0)
            throw new IllegalArgumentException("Cannot have a negative number "
                    + "of items.");
        info = inInfo;
        quantity = inQuantity;
    }
    
    /**
     * 
     * @return 
     */
    public String getInfo() {
        return info;
    }
    
    /**
     * 
     * @return 
     */
    public int getQuantity() {
        return quantity;
    }
    
    /**
     * 
     * @param amount 
     */
    public void addToQuantity(int amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Cannot add negative number of "
                    + "items.");
        quantity += amount;
    }
    
    /**
     * 
     * @param amount 
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
     * 
     */
    public void resetQuantity() {
        quantity = 0;
    }
}
