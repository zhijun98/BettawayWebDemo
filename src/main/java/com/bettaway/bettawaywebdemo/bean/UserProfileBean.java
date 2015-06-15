/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettaway.bettawaywebdemo.bean;

import com.bettaway.bettawaywebdemo.storage.BettawayUserFacadeLocal;
import com.bettaway.bettawaywebdemo.storage.entity.BettawayUser;
import com.bettaway.bettawaywebdemo.util.BettaWebUtil;
import java.util.Date;
import java.util.UUID;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * This CDI backing bean works for createUser.xhtml
 *
 * @author Zhijun Zhang
 */
@Named(value = "userProfileBean")
@RequestScoped
public class UserProfileBean {
    
    private String targetFirstName;
    private String targetLastName;
    private Date targetBirthday;

    @EJB(beanName="bettawayUserFacade")
    private BettawayUserFacadeLocal aBettawayUserFacade;

    public String getTargetFirstName() {
        return targetFirstName;
    }

    public void setTargetFirstName(String targetFirstName) {
        this.targetFirstName = targetFirstName;
    }

    public String getTargetLastName() {
        return targetLastName;
    }

    public void setTargetLastName(String targetLastName) {
        this.targetLastName = targetLastName;
    }

    public Date getTargetBirthday() {
        return targetBirthday;
    }

    public void setTargetBirthday(Date targetBirthday) {
        this.targetBirthday = targetBirthday;
    }
    
    public String createTargetUser(){
        
        //NOTE-1: the front-end input does the basic validation
        
        //NOTE-2: the "uniqueness" of the web user was not validated due to its demo nature
        
        BettawayUser aBettawayUser = new BettawayUser();
        aBettawayUser.setUuid(UUID.randomUUID().toString());
        aBettawayUser.setBirthday(targetBirthday);
        aBettawayUser.setFirstName(targetFirstName);
        aBettawayUser.setLastName(targetLastName);
        try{
            aBettawayUserFacade.create(aBettawayUser);
        }catch (Exception ex){
            //NOTE-3: just show the error handling in case that the JPA operations are very complicated. (In this specific demo case, this is not necessary)
            BettaWebUtil.addErrorMessage(ex, ex.getMessage());
            return null;
        }
        return "userList";
    }
    
    public String cancelOperatingOnTargetUser(){
        return "welcome";
    }
    
}
