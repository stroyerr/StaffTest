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

package au.stroyer.stafftest.commands;

import au.stroyer.stafftest.Main;
import au.stroyer.stafftest.authentication.AuthToken;
import au.stroyer.stafftest.player.TestUser;
import au.stroyer.stafftest.player.TokenPlayer;
import au.stroyer.stafftest.util.Send;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GeneralCommand implements CommandExecutor {
    private final Main main;
    public GeneralCommand(Main main){this.main = main;}
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)){Send.console("StaffTest by Stroyer_"); return true;}
        Player p = (Player) sender;
        switch (args.length){
            case 0:
                Send.player(p, "StaffTest by Stroyer_");
                return true;
            case 1:
                switch(args[0]){
                    case "generate":
                        Send.player(p, "Incorrect syntax. Use /st generate <Player> <Rank>");
                        return true;
                    case "listtokens":
                        List<String> tokenList = new ArrayList<>();
                        List<TextComponent> tcs = new ArrayList<>();
                        for(AuthToken at : AuthToken.getTokens()){
                            String s = ChatColor.GOLD + "Username: " + ChatColor.YELLOW + Bukkit.getOfflinePlayer(at.getPlayerUUID()).getName();
                            String s1 = ChatColor.GOLD + "UUID: " + ChatColor.YELLOW + at.getPlayerUUID();
                            String s3 = ChatColor.GOLD + "ID: " + ChatColor.YELLOW +AuthToken.getTokens().indexOf(at);
                            String s2 = ChatColor.GOLD + "Test Type: " + ChatColor.YELLOW + at.getType().getName();
                            String sep = ChatColor.GRAY + "---------------------------------------------";
                            tokenList.add(s);
                            tokenList.add(s1);
                            tokenList.add(s3);
                            tokenList.add(s2);
                            tokenList.add(sep);
                            TextComponent tc = new TextComponent(ChatColor.AQUA + "Auth Key for ID: " + ChatColor.BOLD + AuthToken.getTokens().indexOf(at) + ChatColor.YELLOW + " Click for Auth Key" );
                            tc.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, at.getTokenUUID().toString()));
                            String hover = ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + at.getTokenUUID().toString() + " " + ChatColor.RED + " Click to copy";
                            tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(hover)));
                            tcs.add(tc);
                        }
                        Send.playerMultipleLines(p, "Auth Tokens (DO NOT SHARE THIS)", tokenList);
                        for(TextComponent tc : tcs){
                            p.spigot().sendMessage(tc);
                        }
                        return true;
                }
            case 2:
                switch(args[0]){
                    case "generate":
                        Send.player(p, "Incorrect syntax. Use /st generate <Player> <Rank>");
                        return true;
                    case "start":
                        TestUser.openAuthMenu(p, args[1]);
                        return true;
                }
            case 3:
                switch(args[0]){
                    case "generate":
                        if(args.length < 3){Send.player(p, "Invalid syntax."); return true;}
                        TokenPlayer.attemptCreateToken(p, args[1], args[2]);
                        return true;
                }
        }

        Send.player(p, "Unkown arguments.");
        return true;
    }
}
