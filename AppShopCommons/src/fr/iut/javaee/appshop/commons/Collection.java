/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.iut.javaee.appshop.commons;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "COLLECTION", schema = "APPSHOP")
@NamedQueries({
    @NamedQuery(name = "Collection.findAll", query = "SELECT c FROM Collection c"),
    @NamedQuery(name = "Collection.findByCollectionId", query = "SELECT c FROM Collection c WHERE c.collectionId = :collectionId"),
    @NamedQuery(name = "Collection.findByCollectionName", query = "SELECT c FROM Collection c WHERE c.collectionName = :collectionName"),
    @NamedQuery(name = "Collection.findByCollectionMemberId", query = "SELECT c FROM Collection c WHERE c.collectionMemberId = :collectionMemberId")})
public class Collection implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE, generator="SEQ_COLLECTION")    
    @TableGenerator(name = "SEQ_COLLECTION", schema="APPSHOP", table = "SEQUENCE", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COLLECTION", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "COLLECTION_ID", nullable = false)
    private Integer collectionId;
    @Basic(optional = false)
    @Column(name = "COLLECTION_NAME", nullable = false, length = 25)
    private String collectionName;
    @Basic(optional = false)
    @Column(name = "COLLECTION_MEMBER_ID", nullable = false)
    private int collectionMemberId;
    @ManyToMany(mappedBy = "collectionList")
    private List<Application> applicationList;

    public Collection() {
    }

    public Collection(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public Collection(Integer collectionId, String collectionName, int collectionMemberId) {
        this.collectionId = collectionId;
        this.collectionName = collectionName;
        this.collectionMemberId = collectionMemberId;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public int getCollectionMemberId() {
        return collectionMemberId;
    }

    public void setCollectionMemberId(int collectionMemberId) {
        this.collectionMemberId = collectionMemberId;
    }

    public List<Application> getApplicationList() {
        return applicationList;
    }

    public void setApplicationList(List<Application> applicationList) {
        this.applicationList = applicationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (collectionId != null ? collectionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Collection)) {
            return false;
        }
        Collection other = (Collection) object;
        if ((this.collectionId == null && other.collectionId != null) || (this.collectionId != null && !this.collectionId.equals(other.collectionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.iut.javaee.appshop.commons.Collection[ collectionId=" + collectionId + " ]";
    }
    
}
