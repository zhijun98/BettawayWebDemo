/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bettaway.bettawaywebdemo.common;

import java.util.ArrayList;
import java.util.List;

/**
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

    public static List<BettawayWebParamName> getBettawayWebParamNameList(boolean includeUnknownValue) {
        List<BettawayWebParamName> result = new ArrayList<BettawayWebParamName>();
        BettawayWebParamName[] valueArray = BettawayWebParamName.values();
        if (valueArray != null){
            for (BettawayWebParamName valueObj : valueArray){
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
        BettawayWebParamName[] valueArray = BettawayWebParamName.values();
        if (valueArray != null){
            for (BettawayWebParamName valueObj : valueArray){
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
        BettawayWebParamName[] valueArray = BettawayWebParamName.values();
        if (valueArray != null){
            for (BettawayWebParamName valueObj : valueArray){
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

    public static BettawayWebParamName convertEnumValueToType(String value){
        return convertEnumValueToType(value, false);
    }

    public static BettawayWebParamName convertEnumValueToType(String value, boolean allowNullReturned){
        BettawayWebParamName result = null;
        if (value != null){
            BettawayWebParamName[] valueArray = BettawayWebParamName.values();
            for (BettawayWebParamName valueObj : valueArray){
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

    public static BettawayWebParamName convertEnumNameToType(String value){
        return convertEnumNameToType(value, false);
    }

    public static BettawayWebParamName convertEnumNameToType(String name, boolean allowNullReturned){
        BettawayWebParamName result = null;
        if (name != null){
            BettawayWebParamName[] valueArray = BettawayWebParamName.values();
            for (BettawayWebParamName valueObj : valueArray){
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
