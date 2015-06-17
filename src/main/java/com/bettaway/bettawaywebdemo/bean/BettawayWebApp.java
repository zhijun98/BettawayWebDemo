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
 * Initial settings set up if necessary and common settings for the web site
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
    
    //todo: @PostConstruct
    //todo: @PreDestroy
  
    /**
     * A common parameter-key for the HTTP query string. Its purpose is to 
     * "hide" the meaningful text from the query string for better security and 
     * also standardize the query parameter keys in the system range.
     * @return 
     */
    public String getBettawayUserUuidParamName(){
        return BettawayWebParamName.UserUuid.toString();
    }
}
