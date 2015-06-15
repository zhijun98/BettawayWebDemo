/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettaway.bettawaywebdemo.storage;

import com.bettaway.bettawaywebdemo.storage.entity.BettawayUser;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Zhijun Zhang
 */
@Local
public interface BettawayUserFacadeLocal {

    void create(BettawayUser bettawayUser);

    void edit(BettawayUser bettawayUser);

    void remove(BettawayUser bettawayUser);

    BettawayUser find(Object id);

    List<BettawayUser> findAll();

    List<BettawayUser> findRange(int[] range);

    int count();
    
}
