/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bettaway.bettawaywebdemo.common;

/**
 * This enum value is used as "parameter-key" in the HTTP request query
 *
 * Its purpose is to "hide" the meaningful text from the query string for 
 * better security and also standardize the query parameter keys in the 
 * system range.
 * 
 * @author Zhijun Zhang
 */
public enum BettawayWebParamName {
    UserUuid("fsdaghdsaghf757656"),
    UNKNOWN("Unknown");

    private final String value;
    BettawayWebParamName(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
