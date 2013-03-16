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
@Table(name = "DOWNLOAD", catalog = "", schema = "APPSHOP")
@NamedQueries({
    @NamedQuery(name = "Download.findAll", query = "SELECT d FROM Download d"),
    @NamedQuery(name = "Download.findByDownloadId", query = "SELECT d FROM Download d WHERE d.downloadId = :downloadId"),
    @NamedQuery(name = "Download.findByDownloadDate", query = "SELECT d FROM Download d WHERE d.downloadDate = :downloadDate")})
public class Download implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @TableGenerator(name = "SEQ_DOWNLOAD", schema="APPSHOP", table = "SEQUENCE", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_DOWNLOAD", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.TABLE, generator="SEQ_DOWNLOAD")
    @Basic(optional = false)
    @Column(name = "DOWNLOAD_ID", nullable = false)
    private Integer downloadId;
    @Basic(optional = false)
    @Column(name = "DOWNLOAD_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date downloadDate;
    @JoinColumn(name = "DOWNLOAD_USER_ID", referencedColumnName = "USER_ID", nullable = false)
    @ManyToOne(optional = false)
    private Users downloadUser;
    @JoinColumn(name = "DOWNLOAD_APPLICATION_ID", referencedColumnName = "APPLICATION_ID", nullable = false)
    @ManyToOne(optional = false)
    private Application downloadApplication;

    public Download() {
    }

    public Download(Integer downloadId) {
        this.downloadId = downloadId;
    }

    public Download(Integer downloadId, Date downloadDate) {
        this.downloadId = downloadId;
        this.downloadDate = downloadDate;
    }

    public Integer getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(Integer downloadId) {
        this.downloadId = downloadId;
    }

    public Date getDownloadDate() {
        return downloadDate;
    }

    public void setDownloadDate(Date downloadDate) {
        this.downloadDate = downloadDate;
    }

    public Users getDownloadUser() {
        return downloadUser;
    }

    public void setDownloadUser(Users downloadUser) {
        this.downloadUser = downloadUser;
    }

    public Application getDownloadApplication() {
        return downloadApplication;
    }

    public void setDownloadApplication(Application downloadApplication) {
        this.downloadApplication = downloadApplication;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (downloadId != null ? downloadId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Download)) {
            return false;
        }
        Download other = (Download) object;
        if ((this.downloadId == null && other.downloadId != null) || (this.downloadId != null && !this.downloadId.equals(other.downloadId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.iut.javaee.appshop.commons.Download[ downloadId=" + downloadId + " ]";
    }
    
}
