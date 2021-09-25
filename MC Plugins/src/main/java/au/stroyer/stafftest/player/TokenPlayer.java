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
import au.stroyer.stafftest.test.testtype.TestType;
import au.stroyer.stafftest.util.Send;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.A;

import java.util.Objects;
import java.util.UUID;

public class TokenPlayer {
    private UUID playerUUID;
    private String username;

    public TokenPlayer(String username){

    }

    public static void attemptCreateToken(Player host, String usernameToAttempt, String rank){
        for(Player p : Bukkit.getOnlinePlayers()){
            if(p.getName().equalsIgnoreCase(usernameToAttempt)){
                Send.player(host, "Found user " + usernameToAttempt);
                if(TestType.getType(rank) == null){
                    Send.player(host, "Unkown rank \"" + rank + "\"");
                }else{
                    Send.player(host, "Generating auth token for player " + ChatColor.DARK_RED + usernameToAttempt + ChatColor.RED + " with UUID " + ChatColor.DARK_RED + p.getUniqueId() + ChatColor.RED + " for test type " + ChatColor.DARK_RED + rank.toUpperCase());
                    AuthToken newToken = new AuthToken(p.getUniqueId(), TestType.getType(rank));
                    TextComponent tc = new TextComponent(ChatColor.AQUA + "" + ChatColor.BOLD + "Click me for the Auth Token");
                    tc.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, newToken.getTokenUUID().toString()));
                    String hover = ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + newToken.getTokenUUID().toString() + " " + ChatColor.RED + " Click to copy";
                    tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(hover)));
                    host.spigot().sendMessage(tc);
                }
                return;
            }
        }
        for(OfflinePlayer p : Bukkit.getOfflinePlayers()){
            if(Objects.requireNonNull(p.getName()).equalsIgnoreCase(usernameToAttempt)){
                Send.player(host, "Found offline user " + usernameToAttempt);
                if(TestType.getType(rank) == null){
                    Send.player(host, "Unkown rank \"" + rank + "\"");
                    return;
                }else{
                    Send.player(host, "Generating auth token for player " + ChatColor.DARK_RED + usernameToAttempt + ChatColor.RED + " with UUID " + ChatColor.DARK_RED + p.getUniqueId() + ChatColor.RED + " for test type " + ChatColor.DARK_RED + rank.toUpperCase());
                    AuthToken newToken = new AuthToken(p.getUniqueId(), TestType.getType(rank));
                    TextComponent tc = new TextComponent(ChatColor.AQUA + "" + ChatColor.BOLD + "Click me for the Auth Token");
                    tc.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, newToken.getTokenUUID().toString()));
                    String hover = ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + newToken.getTokenUUID().toString() + " " + ChatColor.RED + " Click to copy";
                    tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(hover)));
                    host.spigot().sendMessage(tc);
                    return;
                }
            }
        }
        Send.player(host, "Could not find user " + usernameToAttempt + ". If you are certain this player has joined the server before, attempt to create their token using their UUID NOT their username. Find their UUID at " + ChatColor.DARK_RED + "https://namemc.com/profile/" + usernameToAttempt);
    }
}
