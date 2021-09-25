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


package au.stroyer.stafftest;

import au.stroyer.stafftest.commands.GeneralCommand;
import au.stroyer.stafftest.internal.Storage;
import au.stroyer.stafftest.listeners.InventoryClick;
import au.stroyer.stafftest.util.Send;
import au.stroyer.stafftest.util.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getCommand("stafftest").setExecutor(new GeneralCommand(this));
        getCommand("stafftest").setTabCompleter(new TabCompleter());
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);

        Path path = Paths.get("./plugins/StaffTest");
        if(Files.exists(path)){
            try {
                Storage.load();
            } catch (IOException | ClassNotFoundException e) {}
        }else{
            Send.console("Plugin path does not exist! Unless this is your first startup with StaffTest, this is a bug!");
            try {
                Files.createDirectory(path);
                Storage.load();
            } catch (IOException | ClassNotFoundException e) {}
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try {
            Storage.save();
        } catch (IOException e) {}
    }
}
