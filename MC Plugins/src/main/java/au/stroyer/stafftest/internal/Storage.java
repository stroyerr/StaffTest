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

package au.stroyer.stafftest.internal;

import au.stroyer.stafftest.authentication.AuthToken;
import au.stroyer.stafftest.util.Send;

import java.io.*;
import java.util.List;

public class Storage {
    public static void load() throws IOException, ClassNotFoundException {
        File f = new File("./plugins/StaffTest/tokens.dat");
        if(!f.exists()){
            f.createNewFile();
            Send.console("Creating tokens.dat file...");
            Send.console("Unless this is your first startup with StaffTest, this is a bug!");
        }else{
            FileInputStream fIn = new FileInputStream("./plugins/StaffTest/tokens.dat");
            ObjectInputStream oIn = new ObjectInputStream(fIn);
            AuthToken.setTokens((List<AuthToken>) oIn.readObject());
            Send.console("Successfuly loaded tokens.dat with " + AuthToken.getTokens().size() + " auth tokens.");
        }
    }

    public static void save() throws IOException {
        FileOutputStream fOut = new FileOutputStream("./plugins/StaffTest/tokens.dat");
        ObjectOutputStream oOut = new ObjectOutputStream(fOut);
        oOut.writeObject(AuthToken.getTokens());
        Send.console("Saved tokens.dat with updated auth tokens. Goodbye!");
    }
}
