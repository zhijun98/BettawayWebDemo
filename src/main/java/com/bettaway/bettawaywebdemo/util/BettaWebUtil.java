/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettaway.bettawaywebdemo.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Zhijun Zhang
 */
public class BettaWebUtil {
    
    private BettaWebUtil(){}
    
    /**
     * This method demands JSF runtime 
     * @param paramKey - which is located in HTTP-Request header
     * @return 
     */
    public static String getRequestParamValue(String paramKey){
        Map<String, String> pValues = getFacesContext().getExternalContext().getRequestParameterMap();
        if (pValues == null){
            return null;
        }else{
            return pValues.get(paramKey);
        }
    }

    /**
     * Redirect to redirectingUrl which is an absolute URL
     * @param redirectingUrl
     * @throws java.io.IOException
     */
    public static void redirect(String redirectingUrl) throws IOException {
        if (redirectingUrl == null){
            return;
        }
        getFacesContext().getExternalContext().redirect(redirectingUrl);
    }

    protected static FacesContext getFacesContext(){
        return FacesContext.getCurrentInstance();
    }
    
    public static void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }

    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        getFacesContext().addMessage(null, facesMsg);
    }

    public static void addInfoMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        getFacesContext().addMessage(null, facesMsg);
    }

    public static void addWarningMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg);
        getFacesContext().addMessage(null, facesMsg);
    }

    public static void addFatalMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg);
        getFacesContext().addMessage(null, facesMsg);
    }
    
}
