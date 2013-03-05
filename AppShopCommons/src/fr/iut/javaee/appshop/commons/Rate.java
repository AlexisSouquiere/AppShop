/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.commons;

import java.io.Serializable;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 *
 * @author Alexis
 */
@Entity
@Table(name = "RATE", schema = "APPSHOP")
@NamedQueries({
    @NamedQuery(name = "Rate.findAll", query = "SELECT r FROM Rate r"),
    @NamedQuery(name = "Rate.findByRateId", query = "SELECT r FROM Rate r WHERE r.rateId = :rateId"),
    @NamedQuery(name = "Rate.findByRate", query = "SELECT r FROM Rate r WHERE r.rate = :rate")})
public class Rate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @TableGenerator(name = "SEQ_RATE", schema="APPSHOP", table = "SEQUENCE", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_RATE", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.TABLE, generator="SEQ_RATE")
    @Basic(optional = false)
    @Column(name = "RATE_ID", nullable = false)
    private Integer rateId;
    @Basic(optional = false)
    @Column(name = "RATE", nullable = false)
    private int rate;
    @JoinColumn(name = "RATE_MEMBER_ID", referencedColumnName = "MEMBER_ID")
    @ManyToOne
    private Member1 member;
    @JoinColumn(name = "RATE_APPLICATION_ID", referencedColumnName = "APPLICATION_ID", nullable = false)
    @ManyToOne(optional = false)
    private Application application;

    public Rate() {
    }

    public Rate(Integer rateId) {
        this.rateId = rateId;
    }

    public Rate(Integer rateId, int rate) {
        this.rateId = rateId;
        this.rate = rate;
    }

    public Integer getRateId() {
        return rateId;
    }

    public void setRateId(Integer rateId) {
        this.rateId = rateId;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Member1 getRateMemberId() {
        return member;
    }

    public void setRateMemberId(Member1 member) {
        this.member = member;
    }

    public Application getRateApplicationId() {
        return application;
    }

    public void setRateApplicationId(Application application) {
        this.application = application;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rateId != null ? rateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rate)) {
            return false;
        }
        Rate other = (Rate) object;
        if ((this.rateId == null && other.rateId != null) || (this.rateId != null && !this.rateId.equals(other.rateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.iut.javaee.appshop.commons.Rate[ rateId=" + rateId + " ]";
    }
    
}
