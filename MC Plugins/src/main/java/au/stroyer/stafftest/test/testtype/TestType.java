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

import java.io.Serializable;

public class TestType implements Serializable {
    protected String name;
    protected String[] messages;
    protected String[][] options;
    protected int[] correctIndexes;
    public TestType(){
        this.name = "";
    }

    public static TestType trialStaff = new TrialStaff();
    public static TestType jrStaff = new JrStaff();
    public static TestType staff = new Staff();
    public static TestType builder = new Builder();
    public static TestType developer = new Developer();

    private static final TestType[] types = new TestType[]{
            trialStaff,
            jrStaff,
            staff,
            builder,
            developer
    };

    public static TestType[] getTypes(){
        return types;
    }

    public static TestType getType(String name){
        for(TestType type : getTypes()){
            if(type.getName().equalsIgnoreCase(name)){
                return type;
            }
        }
        return null;
    }

    public String getName() {
        return this.name;
    }

    public String[][] getOptions() {
        return this.options;
    }

    public String[] getQuestions() {
        return this.messages;
    }
}
