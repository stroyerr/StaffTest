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

import au.stroyer.stafftest.player.TestUser;
import au.stroyer.stafftest.test.testtype.TestType;
import au.stroyer.stafftest.util.FillBlank;
import au.stroyer.stafftest.util.NewItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Test {
    private List<Inventory> inventories = new ArrayList<>();
    private TestUser testUser;
    private List<Test> activeTests = new ArrayList<>();
    private int question = 0;
    private List<OptionObject> currentOptions = new ArrayList<>();

    private static ItemStack next = NewItem.createGuiItem(Material.EMERALD_BLOCK, ChatColor.GREEN + "Next");

    public Test(TestUser testUser){
        this.testUser = testUser;
        activeTests.add(this);
    }

    public void start(){
        this.question = 1;
        this.nextQuestion();
    }

    public void nextQuestion(){
        Inventory question = Bukkit.createInventory(null, 54, "Question " + this.question);
        for(String[] opts : this.testUser.testType().getOptions()){
            OptionObject oo = new OptionObject(opts[this.question - 1]);
            currentOptions.add(oo);
            int itemplus = 0;
            question.setItem(44 + itemplus, oo.getItemStack());
            if(itemplus == this.testUser.testType().getOptions().length -1){
                itemplus = 0; break;
            }else{
                itemplus ++;
            }
        }
        ItemStack questionItem = NewItem.createGuiItem(Material.BOOK, this.testUser.testType().getQuestions()[this.question-1]);
        question.setItem(49, questionItem);
        question = FillBlank.updateInventory(question);
        this.testUser.getPlayer().openInventory(question);
    }
}
