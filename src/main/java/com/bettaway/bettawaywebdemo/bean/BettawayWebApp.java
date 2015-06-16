/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettaway.bettawaywebdemo.bean;

import com.bettaway.bettawaywebdemo.common.BettawayWebParamName;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Zhijun Zhang
 */
@Named(value = "bettawayWebApp")
@ApplicationScoped
public class BettawayWebApp {

    /**
     * Creates a new instance of BettawayWebApp
     */
    public BettawayWebApp() {
    }
  
    /**
     * A common parameter-key for the HTTP query string
     * @return 
     */
    public String getBettawayUserUuidParamName(){
        return BettawayWebParamName.UserUuid.toString();
    }
}
