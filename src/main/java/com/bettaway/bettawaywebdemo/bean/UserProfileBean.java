/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettaway.bettawaywebdemo.bean;

import com.bettaway.bettawaywebdemo.common.BettawayWebPageName;
import com.bettaway.bettawaywebdemo.storage.BettawayUserFacadeLocal;
import com.bettaway.bettawaywebdemo.storage.entity.BettawayUser;
import com.bettaway.bettawaywebdemo.util.BettaWebUtil;
import java.util.Date;
import java.util.UUID;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 * This CDI backing bean works for createUser.xhtml 
 *
 * @author Zhijun Zhang
 */
@Named(value = "userProfileBean")
@RequestScoped
public class UserProfileBean {
    @Getter @Setter
    private String targetFirstName;
    @Getter @Setter
    private String targetLastName;
    @Getter @Setter
    private Date targetBirthday;

    @EJB(beanName="bettawayUserFacade")
    private BettawayUserFacadeLocal aBettawayUserFacade;
    
    public String createTargetUser(){
        if (!validateInput()){
            return null;
        }
        //NOTE-2: the "uniqueness" of the web user record was not checked here due to its demo nature
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
        return BettawayWebPageName.getWebPageName(BettawayWebPageName.UserListPage, false);
    }
    
    public String cancelOperatingOnTargetUser(){
        return BettawayWebPageName.getWebPageName(BettawayWebPageName.UserListPage, false);
    }

    private boolean validateInput() {
        //NOTE-1: the front-end input does the basic validation
        //BettaWebUtil.addErrorMessage("Please check your input which was not valide.");
        return true;
    }
}
