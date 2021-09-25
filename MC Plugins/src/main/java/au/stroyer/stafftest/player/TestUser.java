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

package au.stroyer.stafftest.player;

import au.stroyer.stafftest.authentication.AuthToken;
import au.stroyer.stafftest.test.Test;
import au.stroyer.stafftest.test.testtype.TestType;
import au.stroyer.stafftest.util.FillBlank;
import au.stroyer.stafftest.util.NewItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.text.SimpleDateFormat;
import java.util.*;

public class TestUser {

    public static List<TestUser> activeUsers = new ArrayList<>();
    public static void openAuthMenu(Player player, String authToken){
        for(AuthToken t : AuthToken.getTokens()){
            if(t.getTokenUUID().toString().equals(authToken)){
                if(t.getPlayerUUID().equals(player.getUniqueId())){
                    TestUser tu = new TestUser(player, t.getType());
                    tu.startTest();
                    return;
                }else{
                    player.kickPlayer(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Attempting to authenticate somebody else's token can result in a permanent ban for both parties. Do not attempt this again.");
                }
            }
        }
    }

    private Player player;
    private TestType type;
    private Inventory activeInventory = null;
    private TestUser(Player player, TestType type){
        this.player = player;
        this.type = type;
        activeUsers.add(this);
    }

    public static List<Inventory> inventories = new ArrayList<>();

    public static void event(InventoryClickEvent e) {
        for(TestUser tu : activeUsers){
            if(tu.activeInventory.equals(e.getInventory())){
                tu.objectEvent(e);
                return;
            }
        }
    }

    public void objectEvent(InventoryClickEvent e){
        if(e.getCurrentItem().equals(details)){
            Test test = new Test(this);
            test.start();
        }
    }

    private ItemStack details;
    private void startTest(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(cal.getTime());
        Inventory inv = Bukkit.createInventory(null, 27, this.getType().getName().toUpperCase() + " Test");
        details = NewItem.createGuiItem(Material.PLAYER_HEAD, ChatColor.GOLD + "" + ChatColor.BOLD + this.getPlayer().getDisplayName());
        SkullMeta sm = (SkullMeta) details.getItemMeta();
        sm.setOwner(this.getPlayer().getName());
        String[] lore ={
                ChatColor.GOLD + "Application type: " + ChatColor.YELLOW + this.getType().getName().toUpperCase(),
        ChatColor.GOLD + "Date // Time: " + ChatColor.YELLOW + time,
                ChatColor.RED + "All these details will be recorded.",
                ChatColor.GREEN + "" + ChatColor.BOLD + "Left click to begin test!",
                ChatColor.DARK_GREEN + "You will not be able to pause",
                ChatColor.DARK_GREEN + "once you begin. If you exit",
                ChatColor.DARK_GREEN + "during the test, your",
                ChatColor.DARK_GREEN + "application will be closed."
        };
        sm.setLore(Arrays.asList(lore));
        details.setItemMeta(sm);
        inv.setItem(13, details);
        inv = FillBlank.updateInventory(inv);
        inventories.add(inv);
        this.activeInventory = inv;
        this.getPlayer().openInventory(inv);
    }


    private TestType getType(){
        return this.type;
    }

    public Player getPlayer(){
        return this.player;
    }

    public TestType testType() {
        return this.type;
    }
}
