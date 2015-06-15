/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bettaway.bettawaywebdemo.storage.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Zhijun Zhang
 */
@Entity
@Table(name = "bettaway_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BettawayUser.findAll", query = "SELECT b FROM BettawayUser b"),
    @NamedQuery(name = "BettawayUser.findByUuid", query = "SELECT b FROM BettawayUser b WHERE b.uuid = :uuid"),
    @NamedQuery(name = "BettawayUser.findByFirstName", query = "SELECT b FROM BettawayUser b WHERE b.firstName = :firstName"),
    @NamedQuery(name = "BettawayUser.findByLastName", query = "SELECT b FROM BettawayUser b WHERE b.lastName = :lastName"),
    @NamedQuery(name = "BettawayUser.findByBirthday", query = "SELECT b FROM BettawayUser b WHERE b.birthday = :birthday")})
public class BettawayUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "uuid")
    private String uuid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "last_name")
    private String lastName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "birthday")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;

    public BettawayUser() {
    }

    public BettawayUser(String uuid) {
        this.uuid = uuid;
    }

    public BettawayUser(String uuid, String firstName, String lastName, Date birthday) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uuid != null ? uuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BettawayUser)) {
            return false;
        }
        BettawayUser other = (BettawayUser) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bettaway.bettawaywebdemo.storage.entity.BettawayUser[ uuid=" + uuid + " ]";
    }
    
}
