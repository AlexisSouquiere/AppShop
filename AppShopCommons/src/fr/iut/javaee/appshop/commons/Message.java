/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.commons;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Alexis
 */
@Entity
@Table(name = "MESSAGE", catalog = "", schema = "APPSHOP")
@NamedQueries({
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m")})
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @TableGenerator(name = "SEQ_MESSAGES", schema="APPSHOP", table = "SEQUENCE", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_MESSAGES", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.TABLE, generator="SEQ_MESSAGES")
    @Basic(optional = false)
    @Column(name = "MESSAGE_ID", nullable = false)
    private Integer messageId;
    @Basic(optional = false)
    @Column(name = "MESSAGE_BODY", nullable = false, length = 500)
    private String messageBody;
    @Column(name = "MESSAGE_DATE")
    @Temporal(TemporalType.DATE)
    private Date messageDate;
    @JoinColumn(name = "MESSAGE_USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne
    private Users messageUser;

    public Message() {
    }

    public Message(Integer messageId) {
        this.messageId = messageId;
    }

    public Message(Integer messageId, String messageBody) {
        this.messageId = messageId;
        this.messageBody = messageBody;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public Users getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(Users messageUser) {
        this.messageUser = messageUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messageId != null ? messageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.messageId == null && other.messageId != null) || (this.messageId != null && !this.messageId.equals(other.messageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.iut.javaee.appshop.commons.Message[ messageId=" + messageId + " ]";
    }
    
}
