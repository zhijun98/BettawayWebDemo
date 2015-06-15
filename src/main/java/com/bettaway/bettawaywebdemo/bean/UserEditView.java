/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettaway.bettawaywebdemo.bean;

import com.bettaway.bettawaywebdemo.storage.BettawayUserFacadeLocal;
import com.bettaway.bettawaywebdemo.storage.entity.BettawayUser;
import com.bettaway.bettawaywebdemo.util.BettaWebUtil;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Zhijun Zhang
 */
@ManagedBean(name = "userEditView")
@ViewScoped
public class UserEditView {

    @EJB(beanName="bettawayUserFacade")
    private BettawayUserFacadeLocal aBettawayUserFacade;
    
    /**
     * Creates a new instance of UserEditView
     */
    public UserEditView() {
    }
    
    public List<BettawayUser> getUserList(){
        return aBettawayUserFacade.findAll();
    }
     
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("User Edited", ((BettawayUser) event.getObject()).getFirstName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((BettawayUser) event.getObject()).getFirstName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    public void onValueQuantityChange(ValueChangeEvent event) {
        event.getNewValue();
        event.getOldValue();
    }
    
    public void onCellEditTableComplete(CellEditEvent event) {
        DataTable dataTable = (DataTable) event.getSource();
        Object objUser = dataTable.getRowData();
        if (objUser instanceof BettawayUser){
            BettawayUser aBettawayUser = (BettawayUser)objUser;
            BettawayUser pBettawayUser = aBettawayUserFacade.find(aBettawayUser.getUuid());
            if (pBettawayUser == null){
                BettaWebUtil.addErrorMessage("Cannot find this user record.");
            }else{
                //upadte fields here
                aBettawayUserFacade.edit(pBettawayUser);
                try {
                    BettaWebUtil.redirect("userList.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(UserEditView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
