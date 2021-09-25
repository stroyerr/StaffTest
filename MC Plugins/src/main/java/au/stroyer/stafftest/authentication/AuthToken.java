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

package au.stroyer.stafftest.authentication;

import au.stroyer.stafftest.test.testtype.TestType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
This class can be instantiated to create an authentication token which a player
uses to start a test. It holds the auth token and the UUID of the player assigned
to that token.
 */

public class AuthToken implements Serializable {
    private UUID playerUUID;
    private UUID tokenUUID;
    private TestType testType;
    private static List<AuthToken> tokens = new ArrayList<>();
    public AuthToken(UUID playerUUID, TestType testType){
        this.playerUUID = playerUUID;
        this.testType = testType;
        UUID random = UUID.randomUUID();
        for(AuthToken token : tokens){
            if(token.getTokenUUID().equals(random)){
                random = UUID.randomUUID();
            }
        }

        this.tokenUUID = random;

        tokens.add(this);
    }

    public UUID getTokenUUID(){
        return this.tokenUUID;
    }

    public static List<AuthToken> getTokens(){
        return tokens;
    }

    public static void setTokens(List<AuthToken> newTokens){
        tokens = newTokens;
    }

    public UUID getPlayerUUID() {
        return this.playerUUID;
    }

    public TestType getType() {
        return this.testType;
    }
}
