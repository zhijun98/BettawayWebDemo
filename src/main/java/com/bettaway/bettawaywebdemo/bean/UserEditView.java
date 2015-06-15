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
    
    private String targetFirstName;
    private String targetLastName;
    private Date targetBirthday;

    @EJB(beanName="bettawayUserFacade")
    private BettawayUserFacadeLocal aBettawayUserFacade;
    
    /**
     * Creates a new instance of UserEditView
     */
    public UserEditView() {
    }

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
            System.out.println("Great");
        }
        
        BettawayUser aBettawayUser = new BettawayUser();
        aBettawayUser.setBirthday(new Date());
        aBettawayUser.setFirstName("TEST_01");
        aBettawayUser.setLastName("TEST_02");
        aBettawayUser.setUuid("ID-4678578");
        aBettawayUserFacade.create(aBettawayUser);
        try {
            BettaWebUtil.redirect("userList.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(UserEditView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String storeTargetUser(){
        
        //NOTE-1: the front-end input does the basic validation
        
        //NOTE-2: the uniqueness of the web user was not validated due to its demo nature
        
        BettawayUser aBettawayUser = new BettawayUser();
        aBettawayUser.setUuid(UUID.randomUUID().toString());
        aBettawayUser.setBirthday(targetBirthday);
        aBettawayUser.setFirstName(targetFirstName);
        aBettawayUser.setLastName(targetLastName);
        try{
            aBettawayUserFacade.create(aBettawayUser);
        }catch (Exception ex){
            //NOTE-3: just show the error handling in case that the JPA operations are very complicated
            //In this demo case, this is not necessary
            BettaWebUtil.addErrorMessage(ex, ex.getMessage());
            return null;
        }
        return "userList";
    }
    
    public String cancelStoringTargetUser(){
        return "welcome";
    }
    
}
