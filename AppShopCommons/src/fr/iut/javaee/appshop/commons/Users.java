/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.commons;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alexis
 */
@Entity
@Table(name = "USERS", catalog = "", schema = "APPSHOP")
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId"),
    @NamedQuery(name = "Users.findByUserLastname", query = "SELECT u FROM Users u WHERE u.userLastname = :userLastname"),
    @NamedQuery(name = "Users.findByUserUsername", query = "SELECT u FROM Users u WHERE u.userUsername = :userUsername"),
    @NamedQuery(name = "Users.findByUserEmail", query = "SELECT u FROM Users u WHERE u.userEmail = :userEmail"),
    @NamedQuery(name = "Users.findByUserBirthdate", query = "SELECT u FROM Users u WHERE u.userBirthdate = :userBirthdate"),
    @NamedQuery(name = "Users.findByUserFirstname", query = "SELECT u FROM Users u WHERE u.userFirstname = :userFirstname"),
    @NamedQuery(name = "Users.findByUserPassword", query = "SELECT u FROM Users u WHERE u.userPassword = :userPassword"),
    @NamedQuery(name = "Users.findByUserGroupName", query = "SELECT u FROM Users u WHERE u.userGroupName = :userGroupName")})
public class Users implements Serializable
{
    @Id
    @TableGenerator(name = "SEQ_USERS", schema="APPSHOP", table = "SEQUENCE", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_USERS", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.TABLE, generator="SEQ_USERS")
    @Basic(optional = false)
    @Column(name = "USER_ID", nullable = false)
    private Integer userId;
    @Column(name = "USER_LASTNAME", length = 255)
    private String userLastname;
    @Basic(optional = false)
    @Column(name = "USER_USERNAME", nullable = false, length = 255)
    private String userUsername;
    @Basic(optional = false)
    @Column(name = "USER_EMAIL", nullable = false, length = 255)
    private String userEmail;
    @Column(name = "USER_BIRTHDATE")
    @Temporal(TemporalType.DATE)
    private Date userBirthdate;
    @Basic(optional = false)
    @Column(name = "USER_FIRSTNAME", nullable = false, length = 255)
    private String userFirstname;
    @Basic(optional = false)
    @Column(name = "USER_PASSWORD", nullable = false, length = 255)
    private String userPassword;
    @Basic(optional = false)
    @Column(name = "USER_GROUP_NAME", nullable = false, length = 50)
    private String userGroupName;
    @OneToMany(mappedBy = "rateUser")
    private List<Rate> rateList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchaseUser")
    private List<Purchase> purchaseList;
    @OneToMany(mappedBy = "commentUser")
    private List<Comment> commentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "downloadUser")
    private List<Download> downloadList;
    @OneToMany(mappedBy = "messageUser")
    private List<Message> messageList;

    public Users() {
    }

    public Users(Integer userId) {
        this.userId = userId;
    }

    public Users(Integer userId, String userUsername, String userEmail, String userFirstname, String userPassword, String userGroupName) {
        this.userId = userId;
        this.userUsername = userUsername;
        this.userEmail = userEmail;
        this.userFirstname = userFirstname;
        this.userPassword = userPassword;
        this.userGroupName = userGroupName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getUserBirthdate() {
        return userBirthdate;
    }

    public void setUserBirthdate(Date userBirthdate) {
        this.userBirthdate = userBirthdate;
    }

    public String getUserFirstname() {
        return userFirstname;
    }

    public void setUserFirstname(String userFirstname) {
        this.userFirstname = userFirstname;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }

    @XmlTransient
    public List<Rate> getRateList() {
        return rateList;
    }

    public void setRateList(List<Rate> rateList) {
        this.rateList = rateList;
    }

    @XmlTransient
    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(List<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
    }

    @XmlTransient
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @XmlTransient
    public List<Download> getDownloadList() {
        return downloadList;
    }

    public void setDownloadList(List<Download> downloadList) {
        this.downloadList = downloadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.iut.javaee.appshop.commons.Users[ userId=" + userId + " ]";
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

}
