/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettaway.bettawaywebdemo.bean;

import com.bettaway.bettawaywebdemo.common.BettawayWebParamName;
import com.bettaway.bettawaywebdemo.common.UserEditViewComID;
import com.bettaway.bettawaywebdemo.storage.BettawayUserFacadeLocal;
import com.bettaway.bettawaywebdemo.storage.entity.BettawayUser;
import com.bettaway.bettawaywebdemo.util.BettaWebUtil;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author Zhijun Zhang
 */
@ManagedBean(name = "userEditView")
@ViewScoped
public class UserEditView {
    
    private UserRecordChange aUserRecordChange;
    
    @EJB(beanName="bettawayUserFacade")
    private BettawayUserFacadeLocal aBettawayUserFacade;
    
    /**
     * Creates a new instance of UserEditView
     */
    public UserEditView() {
    }
    
    public String getUserFirstNameComID(){
        return UserEditViewComID.UserFirstNameComID.toString();
    }
    
    public String getUserLastNameComID(){
        return UserEditViewComID.UserLastNameComID.toString();
    }
    
    public String getUserBirthdayNameComID(){
        return UserEditViewComID.UserBirthdayComID.toString();
    }
    
    public List<BettawayUser> getUserList(){
        return aBettawayUserFacade.findAll();
    }
    
    public void onValueQuantityChange(ValueChangeEvent event) {
        Object objComponent = event.getSource();
        if (objComponent instanceof UIComponent){
            aUserRecordChange = new UserRecordChange(UserEditViewComID.convertEnumValueToType(((UIComponent)objComponent).getId()),
                                                    event.getOldValue(), event.getNewValue());
        }else{
            aUserRecordChange = null;
        }
    }
    
    public void onCellEditTableComplete(CellEditEvent event) {
        if (aUserRecordChange == null){
            return;
        }
        DataTable dataTable = (DataTable) event.getSource();
        Object objUser = dataTable.getRowData();
        if (objUser instanceof BettawayUser){
            BettawayUser aBettawayUser = (BettawayUser)objUser;
            BettawayUser pBettawayUser = aBettawayUserFacade.find(aBettawayUser.getUuid());
            if (pBettawayUser == null){
                BettaWebUtil.addErrorMessage("Cannot find this user record.");
            }else{
                //upadte fields here
                aUserRecordChange.updateUserRecord(pBettawayUser);
                aUserRecordChange = null;   //clear its value
                aBettawayUserFacade.edit(pBettawayUser);
                try {
                    BettaWebUtil.redirect("userList.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(UserEditView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public String deleteBettawayUser(){
        String userUuid = BettaWebUtil.getRequestParamValue(BettawayWebParamName.UserUuid.toString());
        if (userUuid == null){
            BettaWebUtil.addErrorMessage("Cannot find this user record.");
            return null;
        }
        BettawayUser pBettawayUser = aBettawayUserFacade.find(userUuid);
        if (pBettawayUser != null){
            aBettawayUserFacade.remove(pBettawayUser);
            BettaWebUtil.addInfoMessage("Successfully delete this user record: " + pBettawayUser.getFirstName() + " "  + pBettawayUser.getLastName() );
        }else{
            BettaWebUtil.addWarningMessage("This user record was removed: " + pBettawayUser.getFirstName() + " "  + pBettawayUser.getLastName() );
        }
        return null;
    }
    
    /**
     * A data structure hold which column and value changes
     */
    private class UserRecordChange{
        private final UserEditViewComID comID;
        private final Object oldValue;
        private final Object newValue;

        UserRecordChange(UserEditViewComID comID, Object oldValue, Object newValue) {
            this.comID = comID;
            this.oldValue = oldValue;
            this.newValue = newValue;
        }

        void updateUserRecord(BettawayUser aBettawayUser){
            if (aBettawayUser == null){
                return;
            }
            switch (comID){
                case UserBirthdayComID:
                    if (newValue instanceof Date){
                        aBettawayUser.setBirthday((Date)newValue);
                    }
                    break;
                case UserFirstNameComID:
                    if (newValue instanceof String){
                        aBettawayUser.setFirstName(newValue.toString());
                    }
                    break;
                case UserLastNameComID:
                    if (newValue instanceof String){
                        aBettawayUser.setLastName(newValue.toString());
                    }
                    break;
            }
        }
    
    }
}
