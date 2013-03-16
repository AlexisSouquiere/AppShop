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
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alexis
 */
@Entity
@Table(name = "APPLICATION_COLLECTION", catalog = "", schema = "APPSHOP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApplicationCollection.findAll", query = "SELECT a FROM ApplicationCollection a"),
    @NamedQuery(name = "ApplicationCollection.findByApplicationCollectionId", query = "SELECT a FROM ApplicationCollection a WHERE a.applicationCollectionId = :applicationCollectionId")})
public class ApplicationCollection implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id  
    @TableGenerator(name = "SEQ_APPLICATION_COLLECTION", schema="APPSHOP", table = "SEQUENCE", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_APPLICATION_COLLECTION", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.TABLE, generator="SEQ_APPLICATION_COLLECTION")
    @Column(name = "APPLICATION_COLLECTION_ID", nullable = false)
    private Integer applicationCollectionId;
    @JoinColumn(name = "APPLICATION_COLLECTION_COLLECTION_ID", referencedColumnName = "COLLECTION_ID", nullable = false)
    @ManyToOne(optional = false)
    private Collection applicationCollectionCollection;
    @JoinColumn(name = "APPLICATION_COLLECTION_APPLICATION_ID", referencedColumnName = "APPLICATION_ID", nullable = false)
    @ManyToOne(optional = false)
    private Application applicationCollectionApplication;

    public ApplicationCollection() {
    }

    public ApplicationCollection(Integer applicationCollectionId) {
        this.applicationCollectionId = applicationCollectionId;
    }

    public Integer getApplicationCollectionId() {
        return applicationCollectionId;
    }

    public void setApplicationCollectionId(Integer applicationCollectionId) {
        this.applicationCollectionId = applicationCollectionId;
    }

    public Collection getApplicationCollectionCollectionId() {
        return applicationCollectionCollection;
    }

    public void setApplicationCollectionCollection(Collection applicationCollectionCollection) {
        this.applicationCollectionCollection = applicationCollectionCollection;
    }

    public Application getApplicationCollectionApplication() {
        return applicationCollectionApplication;
    }

    public void setApplicationCollectionApplication(Application applicationCollectionApplication) {
        this.applicationCollectionApplication = applicationCollectionApplication;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (applicationCollectionId != null ? applicationCollectionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApplicationCollection)) {
            return false;
        }
        ApplicationCollection other = (ApplicationCollection) object;
        if ((this.applicationCollectionId == null && other.applicationCollectionId != null) || (this.applicationCollectionId != null && !this.applicationCollectionId.equals(other.applicationCollectionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.iut.javaee.appshop.commons.ApplicationCollection[ applicationCollectionId=" + applicationCollectionId + " ]";
    }
    
}
