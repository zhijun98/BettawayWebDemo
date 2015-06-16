/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bettaway.bettawaywebdemo.common;

import java.util.ArrayList;
import java.util.List;

/**
 * This enum data contains all the page names in one place
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

    public static List<BettawayWebPageName> getBettawayWebPageNameList(boolean includeUnknownValue) {
        List<BettawayWebPageName> result = new ArrayList<BettawayWebPageName>();
        BettawayWebPageName[] valueArray = BettawayWebPageName.values();
        if (valueArray != null){
            for (BettawayWebPageName valueObj : valueArray){
                if (includeUnknownValue){
                    result.add(valueObj);
                }else{
                    if (!(valueObj.equals(UNKNOWN))){
                        result.add(valueObj);
                    }
                }
            }//for
        }//if
        return result;
    }

    public static List<String> getEnumValueList(boolean includeUnknownValue){
        List<String> result = new ArrayList<String>();
        BettawayWebPageName[] valueArray = BettawayWebPageName.values();
        if (valueArray != null){
            for (BettawayWebPageName valueObj : valueArray){
                if (includeUnknownValue){
                    result.add(valueObj.toString());
                }else{
                    if (!(valueObj.toString().equalsIgnoreCase(UNKNOWN.toString()))){
                        result.add(valueObj.toString());
                    }
                }
            }//for
        }//if
        return result;
    }

    public static List<String> getEnumNameList(boolean includeUnknownName){
        List<String> result = new ArrayList<String>();
        BettawayWebPageName[] valueArray = BettawayWebPageName.values();
        if (valueArray != null){
            for (BettawayWebPageName valueObj : valueArray){
                if (includeUnknownName){
                    result.add(valueObj.name());
                }else{
                    if (!(valueObj.name().equalsIgnoreCase(UNKNOWN.name()))){
                        result.add(valueObj.name());
                    }
                }
            }//for
        }//if
        return result;
    }

    public static BettawayWebPageName convertEnumValueToType(String value){
        return convertEnumValueToType(value, false);
    }

    public static BettawayWebPageName convertEnumValueToType(String value, boolean allowNullReturned){
        BettawayWebPageName result = null;
        if (value != null){
            BettawayWebPageName[] valueArray = BettawayWebPageName.values();
            for (BettawayWebPageName valueObj : valueArray){
                if (valueObj.toString().equalsIgnoreCase(value)){
                    result = valueObj;
                    break;
                }
            }
        }
        if (!allowNullReturned){
            if (result == null){
                result = UNKNOWN;
            }
        }
        return result;
    }

    public static BettawayWebPageName convertEnumNameToType(String value){
        return convertEnumNameToType(value, false);
    }

    public static BettawayWebPageName convertEnumNameToType(String name, boolean allowNullReturned){
        BettawayWebPageName result = null;
        if (name != null){
            BettawayWebPageName[] valueArray = BettawayWebPageName.values();
            for (BettawayWebPageName valueObj : valueArray){
                if (valueObj.name().equalsIgnoreCase(name)){
                    result = valueObj;
                    break;
                }
            }
        }
        if (!allowNullReturned){
            if (result == null){
                result = UNKNOWN;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return value;
    }
}
