/*

Copyright (c) 2021 stroyerr.

        This project is licensed under the

        GNU GENERAL PUBLIC LICENSE
        Version 3, 29 June 2007

        Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/>
        Everyone is permitted to copy and distribute verbatim copies
        of this license document, but changing it is not allowed.

        Essentially, do what you like with this software, but I claim no responsibility, warranty
        or liability for it. You must include this license with credit to me on all modifications,
        publications, distributions etc. All software built upon this project must be open source
        with credit to me and this license must be present.

*/


        package au.stroyer.stafftest.util;

import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class Send {
    protected static final String prefix = ChatColor.GOLD + "Staff" + ChatColor.YELLOW + "Test" + ChatColor.DARK_RED + ": " + ChatColor.RED;
    public static void player(Player player, String message){
        player.sendMessage(prefix + message);
    }
    public static void player(Player player, TextComponent textComponent) {
        player.sendMessage(textComponent);
    }

    public static void console(String message) {
        Bukkit.getLogger().info("[StaffTest] " + message);
    }

    public static void playerMultipleLines(Player player, String firstLine, List<String> messages){
        player(player, firstLine);
        for(String s : messages){
            player.sendMessage(s);
        }
    }
}
