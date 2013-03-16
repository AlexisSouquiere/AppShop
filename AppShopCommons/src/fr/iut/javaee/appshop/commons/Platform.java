/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.commons;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alexis
 */
@Entity
@Table(name = "PLATFORM", catalog = "", schema = "APPSHOP")
@NamedQueries({
    @NamedQuery(name = "Platform.findAll", query = "SELECT p FROM Platform p"),
    @NamedQuery(name = "Platform.findByPlatformId", query = "SELECT p FROM Platform p WHERE p.platformId = :platformId"),
    @NamedQuery(name = "Platform.findByPlatformName", query = "SELECT p FROM Platform p WHERE p.platformName = :platformName"),
    @NamedQuery(name = "Platform.findByPlatformVersion", query = "SELECT p FROM Platform p WHERE p.platformVersion = :platformVersion")})
public class Platform implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @TableGenerator(name = "SEQ_PLATFORM", schema="APPSHOP", table = "SEQUENCE", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_PLATFORM", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.TABLE, generator="SEQ_PLATFORM")
    @Basic(optional = false)
    @Column(name = "PLATFORM_ID", nullable = false)
    private Integer platformId;
    @Basic(optional = false)
    @Column(name = "PLATFORM_NAME", nullable = false, length = 255)
    private String platformName;
    @Basic(optional = false)
    @Column(name = "PLATFORM_VERSION", nullable = false, length = 255)
    private String platformVersion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "applicationPlatform")
    private List<Application> applicationList;

    public Platform() {
    }

    public Platform(Integer platformId) {
        this.platformId = platformId;
    }

    public Platform(Integer platformId, String platformName, String platformVersion) {
        this.platformId = platformId;
        this.platformName = platformName;
        this.platformVersion = platformVersion;
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    @XmlTransient
    public List<Application> getApplicationList() {
        return applicationList;
    }

    public void setApplicationList(List<Application> applicationList) {
        this.applicationList = applicationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (platformId != null ? platformId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Platform)) {
            return false;
        }
        Platform other = (Platform) object;
        if ((this.platformId == null && other.platformId != null) || (this.platformId != null && !this.platformId.equals(other.platformId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.iut.javaee.appshop.commons.Platform[ platformId=" + platformId + " ]";
    }
    
}
