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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alexis
 */
@Entity
@Table(name = "PURCHASE", catalog = "", schema = "APPSHOP")
@NamedQueries({
    @NamedQuery(name = "Purchase.findAll", query = "SELECT p FROM Purchase p"),
    @NamedQuery(name = "Purchase.findByPurchaseId", query = "SELECT p FROM Purchase p WHERE p.purchaseId = :purchaseId"),
    @NamedQuery(name = "Purchase.findByPurchaseDate", query = "SELECT p FROM Purchase p WHERE p.purchaseDate = :purchaseDate"),
    @NamedQuery(name = "Purchase.findByPurchasePrice", query = "SELECT p FROM Purchase p WHERE p.purchasePrice = :purchasePrice")})
public class Purchase implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @TableGenerator(name = "SEQ_PURCHASE", schema="APPSHOP", table = "SEQUENCE", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_PURCHASE", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.TABLE, generator="SEQ_PURCHASE")
    @Basic(optional = false)
    @Column(name = "PURCHASE_ID", nullable = false)
    private Integer purchaseId;
    @Column(name = "PURCHASE_DATE")
    @Temporal(TemporalType.DATE)
    private Date purchaseDate;
    @Column(name = "PURCHASE_PRICE", precision = 52)
    private Double purchasePrice;
    @JoinColumn(name = "PURCHASE_USER_ID", referencedColumnName = "USER_ID", nullable = false)
    @ManyToOne(optional = false)
    private Users purchaseUser;
    @JoinColumn(name = "PURCHASE_APPLICATION_ID", referencedColumnName = "APPLICATION_ID", nullable = false)
    @ManyToOne(optional = false)
    private Application purchaseApplication;

    public Purchase() {
    }

    public Purchase(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Users getPurchaseUser() {
        return purchaseUser;
    }

    public void setPurchaseUser(Users purchaseUser) {
        this.purchaseUser = purchaseUser;
    }

    public Application getPurchaseApplication() {
        return purchaseApplication;
    }

    public void setPurchaseApplication(Application purchaseApplication) {
        this.purchaseApplication = purchaseApplication;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (purchaseId != null ? purchaseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Purchase)) {
            return false;
        }
        Purchase other = (Purchase) object;
        if ((this.purchaseId == null && other.purchaseId != null) || (this.purchaseId != null && !this.purchaseId.equals(other.purchaseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.iut.javaee.appshop.commons.Purchase[ purchaseId=" + purchaseId + " ]";
    }
    
}
