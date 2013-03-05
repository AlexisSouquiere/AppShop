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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Alexis
 */
@Entity
@Table(name = "MEMBER", schema = "APPSHOP")
@NamedQueries({
    @NamedQuery(name = "Member1.findAll", query = "SELECT m FROM Member1 m"),
    @NamedQuery(name = "Member1.findByMemberId", query = "SELECT m FROM Member1 m WHERE m.memberId = :memberId"),
    @NamedQuery(name = "Member1.findByMemberLastname", query = "SELECT m FROM Member1 m WHERE m.memberLastname = :memberLastname"),
    @NamedQuery(name = "Member1.findByMemberUsername", query = "SELECT m FROM Member1 m WHERE m.memberUsername = :memberUsername"),
    @NamedQuery(name = "Member1.findByMemberEmail", query = "SELECT m FROM Member1 m WHERE m.memberEmail = :memberEmail"),
    @NamedQuery(name = "Member1.findByMemberBirthdate", query = "SELECT m FROM Member1 m WHERE m.memberBirthdate = :memberBirthdate"),
    @NamedQuery(name = "Member1.findByMemberFirstname", query = "SELECT m FROM Member1 m WHERE m.memberFirstname = :memberFirstname"),
    @NamedQuery(name = "Member1.findByMemberPassword", query = "SELECT m FROM Member1 m WHERE m.memberPassword = :memberPassword")})
public class Member1 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @TableGenerator(name = "SEQ_MEMBER", schema="APPSHOP", table = "SEQUENCE", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_MEMBER", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.TABLE, generator="SEQ_MEMBER")
    @Basic(optional = false)
    @Column(name = "MEMBER_ID", nullable = false)
    private Integer memberId;
    @Column(name = "MEMBER_LASTNAME", length = 255)
    private String memberLastname;
    @Basic(optional = false)
    @Column(name = "MEMBER_USERNAME", nullable = false, length = 255)
    private String memberUsername;
    @Basic(optional = false)
    @Column(name = "MEMBER_EMAIL", nullable = false, length = 255)
    private String memberEmail;
    @Column(name = "MEMBER_BIRTHDATE")
    @Temporal(TemporalType.DATE)
    private Date memberBirthdate;
    @Basic(optional = false)
    @Column(name = "MEMBER_FIRSTNAME", nullable = false, length = 255)
    private String memberFirstname;
    @Basic(optional = false)
    @Column(name = "MEMBER_PASSWORD", nullable = false, length = 255)
    private String memberPassword;
    @OneToMany(mappedBy = "member")
    private List<Rate> rateList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private List<Purchase> purchaseList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private List<Comment> commentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private List<Download> downloadList;

    public Member1() {
    }

    public Member1(Integer memberId) {
        this.memberId = memberId;
    }

    public Member1(Integer memberId, String memberUsername, String memberEmail, String memberFirstname, String memberPassword) {
        this.memberId = memberId;
        this.memberUsername = memberUsername;
        this.memberEmail = memberEmail;
        this.memberFirstname = memberFirstname;
        this.memberPassword = memberPassword;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberLastname() {
        return memberLastname;
    }

    public void setMemberLastname(String memberLastname) {
        this.memberLastname = memberLastname;
    }

    public String getMemberUsername() {
        return memberUsername;
    }

    public void setMemberUsername(String memberUsername) {
        this.memberUsername = memberUsername;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public Date getMemberBirthdate() {
        return memberBirthdate;
    }

    public void setMemberBirthdate(Date memberBirthdate) {
        this.memberBirthdate = memberBirthdate;
    }

    public String getMemberFirstname() {
        return memberFirstname;
    }

    public void setMemberFirstname(String memberFirstname) {
        this.memberFirstname = memberFirstname;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public List<Rate> getRateList() {
        return rateList;
    }

    public void setRateList(List<Rate> rateList) {
        this.rateList = rateList;
    }

    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(List<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Download> getDownloadList() {
        return downloadList;
    }

    public void setDownloadList(List<Download> downloadList) {
        this.downloadList = downloadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (memberId != null ? memberId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Member1)) {
            return false;
        }
        Member1 other = (Member1) object;
        if ((this.memberId == null && other.memberId != null) || (this.memberId != null && !this.memberId.equals(other.memberId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.iut.javaee.appshop.commons.Member1[ memberId=" + memberId + " ]";
    }
    
}
