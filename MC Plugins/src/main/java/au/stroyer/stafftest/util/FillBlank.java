/*
 * Copyright (c) 2021 stroyerr.
 *
 * This project is licensed under the
 *
 * GNU GENERAL PUBLIC LICENSE
 *                        Version 3, 29 June 2007
 *
 *  Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/>
 *  Everyone is permitted to copy and distribute verbatim copies
 *  of this license document, but changing it is not allowed.
 *
 *  Essentially, do what you like with this software, but I claim no responsibility, warranty
 *  or liability for it. You must include this license with credit to me on all modifications,
 *  publications, distributions etc. All software built upon this project must be open source
 *  with credit to me and this license must be present.
 *
 *
 */

package au.stroyer.stafftest.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class FillBlank {
    public static Inventory updateInventory(Inventory inv){

        Inventory newInv = inv;
        ItemStack blank = NewItem.createGuiItem(Material.BLACK_STAINED_GLASS_PANE, " ");
        ItemStack watermark = NewItem.createGuiItem(Material.BLACK_STAINED_GLASS_PANE, " "); //disabled this as i do not need to credit myself
        for(int i = 0; i < inv.getSize(); i++){
            if(inv.getItem(i) == null){
                if(i % 2 == 0){
                    newInv.setItem(i, blank);
                }else{
                    newInv.setItem(i, watermark);
                }

            }
        }

        return newInv;

    }
}