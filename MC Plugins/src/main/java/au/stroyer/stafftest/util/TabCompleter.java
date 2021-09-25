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

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        String[] subCommands = {
                "generate",
                "listtokens",
                "start"
        };

        String[] generateTypeArgs = {
                "trial-staff",
                "jr-staff",
                "staff",
                "builder",
                "developer"
        };

        if (command.getName().equalsIgnoreCase("stafftest") || command.getName().equalsIgnoreCase("st") || alias.equalsIgnoreCase("test")) { // checking if my command is the one i'm after

            List<String> autoCompletes = new ArrayList<>();

            if (args.length == 1) {

                for (int i = 0; i < subCommands.length; i++) {

                    autoCompletes.add(subCommands[i]);

                }

                return autoCompletes;

            }

            if (args.length == 3) {

                autoCompletes = new ArrayList<>();

                if(args[0].equalsIgnoreCase("generate")){
                    for (int i = 0; i < generateTypeArgs.length; i++) {

                        autoCompletes.add(generateTypeArgs[i]);

                    }
                    return autoCompletes;

                }


            }

        }

        return null;
    }
}

