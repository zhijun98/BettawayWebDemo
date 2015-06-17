/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bettaway.bettawaywebdemo.common;

import java.util.ArrayList;
import java.util.List;

/**
 * This enum value is used to label UIComponents to support relevant logic 
 * in the code. In this way, avoid hard-coding it in both of JSF page and Java code.
 *
 * @author Zhijun Zhang
 */
public enum UserEditViewComID {
    UserFirstNameComID("UserFirstNameComID"),
    UserLastNameComID("UserLastNameComID"),
    UserBirthdayComID("UserBirthdayComID"),
    UNKNOWN("Unknown");

    private final String value;
    UserEditViewComID(String value){
        this.value = value;
    }

    public static List<UserEditViewComID> getUserEditViewComIDList(boolean includeUnknownValue) {
        List<UserEditViewComID> result = new ArrayList<UserEditViewComID>();
        UserEditViewComID[] valueArray = UserEditViewComID.values();
        if (valueArray != null){
            for (UserEditViewComID valueObj : valueArray){
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
        UserEditViewComID[] valueArray = UserEditViewComID.values();
        if (valueArray != null){
            for (UserEditViewComID valueObj : valueArray){
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
        UserEditViewComID[] valueArray = UserEditViewComID.values();
        if (valueArray != null){
            for (UserEditViewComID valueObj : valueArray){
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

    public static UserEditViewComID convertEnumValueToType(String value){
        return convertEnumValueToType(value, false);
    }

    public static UserEditViewComID convertEnumValueToType(String value, boolean allowNullReturned){
        UserEditViewComID result = null;
        if (value != null){
            UserEditViewComID[] valueArray = UserEditViewComID.values();
            for (UserEditViewComID valueObj : valueArray){
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

    public static UserEditViewComID convertEnumNameToType(String value){
        return convertEnumNameToType(value, false);
    }

    public static UserEditViewComID convertEnumNameToType(String name, boolean allowNullReturned){
        UserEditViewComID result = null;
        if (name != null){
            UserEditViewComID[] valueArray = UserEditViewComID.values();
            for (UserEditViewComID valueObj : valueArray){
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
