/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bettaway.bettawaywebdemo.common;

/**
 * This enum data contains all the page names in one place.
 * 
 * This is a way to avoid explicitly hard-coded the web page in the code.
 *
 * @author Zhijun Zhang
 */
public enum BettawayWebPageName {
    WelcomePage("welcome"),
    UserListPage("userList"),
    CreateUserPage("createUser"),
    UNKNOWN("Unknown");

    private final String value;
    BettawayWebPageName(String value){
        this.value = value;
    }
    
    public static String getWebPageName(BettawayWebPageName pageName, boolean withExtension){
        if (withExtension){
            return pageName + ".xhtml";
        }else{
            return pageName.toString();
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
