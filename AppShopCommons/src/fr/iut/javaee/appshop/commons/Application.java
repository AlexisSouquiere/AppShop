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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "APPLICATION", catalog = "", schema = "APPSHOP")
@NamedQueries({
    @NamedQuery(name = "Application.findAll", query = "SELECT a FROM Application a ORDER BY a.applicationId DESC"),
    @NamedQuery(name = "Application.findByApplicationId", query = "SELECT a FROM Application a WHERE a.applicationId = :applicationId"),
    @NamedQuery(name = "Application.findByApplicationReleaseDate", query = "SELECT a FROM Application a WHERE a.applicationReleaseDate = :applicationReleaseDate"),
    @NamedQuery(name = "Application.findByApplicationWebsite", query = "SELECT a FROM Application a WHERE a.applicationWebsite = :applicationWebsite"),
    @NamedQuery(name = "Application.findByApplicationName", query = "SELECT a FROM Application a WHERE a.applicationName LIKE:applicationName"),
    @NamedQuery(name = "Application.findByApplicationVersion", query = "SELECT a FROM Application a WHERE a.applicationVersion = :applicationVersion"),
    @NamedQuery(name = "Application.findByApplicationPrice", query = "SELECT a FROM Application a WHERE a.applicationPrice = :applicationPrice")})
public class Application implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id  
    @TableGenerator(name = "SEQ_APPLICATION", schema="APPSHOP", table = "SEQUENCE", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_APPLICATION", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.TABLE, generator="SEQ_APPLICATION")
    @Column(name = "APPLICATION_ID", nullable = false)
    private Integer applicationId;
    @Column(name = "APPLICATION_RELEASE_DATE")
    @Temporal(TemporalType.DATE)
    private Date applicationReleaseDate;
    @Column(name = "APPLICATION_WEBSITE", length = 255)
    private String applicationWebsite;
    @Basic(optional = false)
    @Column(name = "APPLICATION_NAME", nullable = false, length = 255)
    private String applicationName;
    @Basic(optional = false)
    @Column(name = "APPLICATION_VERSION", nullable = false, length = 255)
    private String applicationVersion;
    @Column(name = "APPLICATION_PRICE", precision = 52)
    private Double applicationPrice;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rateApplication")
    private List<Rate> rateList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "applicationCollectionApplication")
    private List<ApplicationCollection> applicationCollectionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchaseApplication")
    private List<Purchase> purchaseList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commentApplication")
    private List<Comment> commentList;
    @JoinColumn(name = "APPLICATION_PLATFORM_ID", referencedColumnName = "PLATFORM_ID", nullable = false)
    @ManyToOne(optional = false)
    private Platform applicationPlatform;
    @JoinColumn(name = "APPLICATION_EDITOR_ID", referencedColumnName = "EDITOR_ID", nullable = false)
    @ManyToOne(optional = false)
    private Editor applicationEditor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "downloadApplication")
    private List<Download> downloadList;

    public Application() {
    }

    public Application(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Application(Integer applicationId, String applicationName, String applicationVersion) {
        this.applicationId = applicationId;
        this.applicationName = applicationName;
        this.applicationVersion = applicationVersion;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Date getApplicationReleaseDate() {
        return applicationReleaseDate;
    }

    public void setApplicationReleaseDate(Date applicationReleaseDate) {
        this.applicationReleaseDate = applicationReleaseDate;
    }

    public String getApplicationWebsite() {
        return applicationWebsite;
    }

    public void setApplicationWebsite(String applicationWebsite) {
        this.applicationWebsite = applicationWebsite;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationVersion() {
        return applicationVersion;
    }

    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    public Double getApplicationPrice() {
        return applicationPrice;
    }

    public void setApplicationPrice(Double applicationPrice) {
        this.applicationPrice = applicationPrice;
    }

    @XmlTransient
    public List<Rate> getRateList() {
        return rateList;
    }

    public void setRateList(List<Rate> rateList) {
        this.rateList = rateList;
    }

    @XmlTransient
    public List<ApplicationCollection> getApplicationCollectionList() {
        return applicationCollectionList;
    }

    public void setApplicationCollectionList(List<ApplicationCollection> applicationCollectionList) {
        this.applicationCollectionList = applicationCollectionList;
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

    public Platform getApplicationPlatform() {
        return applicationPlatform;
    }

    public void setApplicationPlatform(Platform applicationPlatformId) {
        this.applicationPlatform = applicationPlatformId;
    }

    public Editor getApplicationEditor() {
        return applicationEditor;
    }

    public void setApplicationEditor(Editor applicationEditorId) {
        this.applicationEditor = applicationEditorId;
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
        hash += (applicationId != null ? applicationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Application)) {
            return false;
        }
        Application other = (Application) object;
        if ((this.applicationId == null && other.applicationId != null) || (this.applicationId != null && !this.applicationId.equals(other.applicationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.iut.javaee.appshop.commons.Application[ applicationId=" + applicationId + " ]";
    }
    
}
