package au.stroyer.stafftest.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Send {
    protected static final String prefix = ChatColor.GOLD + "Staff" + ChatColor.YELLOW + "Test" + ChatColor.DARK_RED + ": " + ChatColor.RED;
    public static void player(Player player, String message){
        player.sendMessage(prefix + message);
    }

    public static void console(String message) {
        Bukkit.getLogger().info("[StaffTest] " + message);
    }
}
