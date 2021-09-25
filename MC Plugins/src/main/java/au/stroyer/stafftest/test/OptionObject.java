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

package au.stroyer.stafftest.test;

import au.stroyer.stafftest.util.NewItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class OptionObject {
    private ItemStack itemStack;
    private String option;
    public OptionObject(String option){
        this.option = option;
        this.itemStack = NewItem.createGuiItem(Material.GOLD_BLOCK, ChatColor.GOLD + option);
    }

    public ItemStack getItemStack(){
        return this.itemStack;
    }

    public String getOption(){
        return this.option;
    }
}
