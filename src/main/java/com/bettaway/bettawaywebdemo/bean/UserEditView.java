/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettaway.bettawaywebdemo.bean;

import com.bettaway.bettawaywebdemo.storage.BettawayUserFacadeLocal;
import com.bettaway.bettawaywebdemo.storage.entity.BettawayUser;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Zhijun Zhang
 */
@Named(value = "userEditView")
@RequestScoped
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
    
}
