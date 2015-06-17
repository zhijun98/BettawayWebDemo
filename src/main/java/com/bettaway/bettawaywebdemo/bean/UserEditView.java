/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettaway.bettawaywebdemo.bean;

import com.bettaway.bettawaywebdemo.common.BettawayWebPageName;
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
 * For userList.xhtml
 *
 * @author Zhijun Zhang
 */
@ManagedBean(name = "userEditView")
@ViewScoped
public class UserEditView {
    
    /**
     * Hold the current cell value change from the users
     */
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
    
    /**
     * Get the latest user list every time the view is constructed 
     * @return 
     */
    public List<BettawayUser> getUserList(){
        return aBettawayUserFacade.findAll();
    }
    
    /**
     * When user change the cell value on the JSF page, this method is invoked
     * @param event 
     */
    public void onValueObjectChange(ValueChangeEvent event) {
        Object objComponent = event.getSource();
        if (objComponent instanceof UIComponent){
            aUserRecordChange = new UserRecordChange(UserEditViewComID.convertEnumValueToType(((UIComponent)objComponent).getId()),
                                                    event.getOldValue(), event.getNewValue());
        }else{
            aUserRecordChange = null;
        }
    }
    
    /**
     * After user stop editing and move to others, this method is invoked.
     * @param event 
     */
    public void onCellEditTableComplete(CellEditEvent event) {
        if (aUserRecordChange == null){
            //quietly return.
            return;
        }
        Object objTable = event.getSource();
        if (objTable instanceof DataTable){
            DataTable dataTable = (DataTable)objTable;
            Object objUser = dataTable.getRowData();
            if (objUser instanceof BettawayUser){
                BettawayUser aBettawayUser = (BettawayUser)objUser;
                BettawayUser pBettawayUser = aBettawayUserFacade.find(aBettawayUser.getUuid());
                if (pBettawayUser == null){
                    //it should rarely happen. if it happened for any reason, just 
                    //show friendly message to the front end and avoid infrastructure 
                    //crashed. But use a logger to log it into Glassfish system
                    Logger.getLogger(UserEditView.class.getName()).log(Level.SEVERE, null, new Exception("This should never be happened. Check it out and fix it."));
                    BettaWebUtil.addErrorMessage("Cannot find this user record.");
                }else{
                    //update the changed field for the user entity
                    aUserRecordChange.updateUserRecord(pBettawayUser);
                    //erase this temp instance
                    aUserRecordChange = null; 
                    //save it into the storage 
                    aBettawayUserFacade.edit(pBettawayUser);
                    try {
                        //redirect to this view so that this view can be refreshed (this bean used @ViewScope for editing in-place)
                        BettaWebUtil.redirect(BettawayWebPageName.getWebPageName(BettawayWebPageName.UserListPage, true));
                    } catch (IOException ex) {
                        Logger.getLogger(UserEditView.class.getName()).log(Level.SEVERE, null, ex);
                        BettaWebUtil.addErrorMessage("Cannot complete this operation right now. Please try it later.");
                    }
                }
            }//if-objUser
        }//if-objTable
    }
    
    /**
     * serve for DELETE request from users
     * @return 
     */
    public String deleteBettawayUser(){
        String userUuid = BettaWebUtil.getRequestParamValue(BettawayWebParamName.UserUuid.toString());
        if (userUuid == null){
            BettaWebUtil.addErrorMessage("Cannot find this user record.");
            return null;
        }
        BettawayUser pBettawayUser = aBettawayUserFacade.find(userUuid);
        if (pBettawayUser == null){
            BettaWebUtil.addWarningMessage("This user record was removed: " + pBettawayUser.getFirstName() + " "  + pBettawayUser.getLastName() );
        }else{
            aBettawayUserFacade.remove(pBettawayUser);
            BettaWebUtil.addInfoMessage("Successfully delete this user record: " + pBettawayUser.getFirstName() + " "  + pBettawayUser.getLastName() );
        }
        try {
            //redirect to this view so that this view can be refreshed (this bean used @ViewScope for editing in-place)
            BettaWebUtil.redirect(BettawayWebPageName.getWebPageName(BettawayWebPageName.UserListPage, true));
        } catch (IOException ex) {
            //Use Glassfish server's logging to record it
            Logger.getLogger(UserEditView.class.getName()).log(Level.SEVERE, null, ex);
            BettaWebUtil.addErrorMessage("Cannot complete this operation right now. Please try it later");
        }
        return null;
    }
    
    /**
     * A data structure hold which column and value changes
     */
    private class UserRecordChange{
        private final UserEditViewComID comID;
        private final Object oldValue;  //reserved for the future more complicated logic
        private final Object newValue;

        UserRecordChange(UserEditViewComID comID, Object oldValue, Object newValue) {
            this.comID = comID;
            this.oldValue = oldValue;
            this.newValue = newValue;
        }

        /**
         * Update the changed property of the entity instance
         * @param aBettawayUser 
         */
        void updateUserRecord(BettawayUser aBettawayUser){
            if (aBettawayUser == null){
                //return quietly
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
