/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettaway.bettawaywebdemo.storage;

import com.bettaway.bettawaywebdemo.storage.entity.BettawayUser;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Zhijun Zhang
 */
@Stateless(name="bettawayUserFacade")
public class BettawayUserFacade extends AbstractFacade<BettawayUser> implements BettawayUserFacadeLocal {
    @PersistenceContext(unitName = "com.bettaway_BettawayWebDemo_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BettawayUserFacade() {
        super(BettawayUser.class);
    }
    
}
