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

package au.stroyer.stafftest.test.testtype;

public class JrStaff extends TestType {
    public JrStaff(){
        this.name = "jr-staff";
        this.messages = new String[]{
                "A player is found to be using fly hacks, or otherwise cheating. What is your response?",
                "Another staff member is harassing a player. What do you do?",
                "What is the simplest way to find if a player is hacking?",
                "SURVEY QUESTION: Do you think this staff test is a good way of giving out ranks?"
        };
        this.options = new String[][]{
                {"Warn the player","Notify other staff","Kick the player","Temp-Ban the player","Ban the player"},
                {"Begin an altercation with the staff member","Give the player creative as an apology","Report the staff member","Laugh and go along with it"},
                {"Ask for them to send you a screenshot","Assume they're hacking","Get them to say '.hi' in chat"},
                {"Yes","No"}
        };

        this.correctIndexes = new int[]{
                4,2,2
        };
    }
}
