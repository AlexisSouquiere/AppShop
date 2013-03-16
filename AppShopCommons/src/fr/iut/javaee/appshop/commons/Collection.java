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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "COLLECTION", catalog = "", schema = "APPSHOP")
@NamedQueries({
    @NamedQuery(name = "Collection.findAll", query = "SELECT c FROM Collection c"),
    @NamedQuery(name = "Collection.findByCollectionId", query = "SELECT c FROM Collection c WHERE c.collectionId = :collectionId"),
    @NamedQuery(name = "Collection.findByCollectionName", query = "SELECT c FROM Collection c WHERE c.collectionName = :collectionName"),
    @NamedQuery(name = "Collection.findByCollectionUsersId", query = "SELECT c FROM Collection c WHERE c.collectionUsers.userId = :collectionUsersId")})
public class Collection implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE, generator="SEQ_COLLECTION")    
    @TableGenerator(name = "SEQ_COLLECTION", schema="APPSHOP", table = "SEQUENCE", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_COLLECTION", allocationSize = 1)
    @Column(name = "COLLECTION_ID", nullable = false)
    private Integer collectionId;
    @Basic(optional = false)
    @Column(name = "COLLECTION_NAME", nullable = false, length = 25)
    private String collectionName;
    @JoinColumn(name = "COLLECTION_USERS_ID", referencedColumnName = "USER_ID", nullable = false)
    @ManyToOne(optional = false)
    private Users collectionUsers;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "applicationCollectionCollection")
    private List<ApplicationCollection> applicationCollectionList;

    public Collection() {
    }

    public Collection(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public Collection(Integer collectionId, String collectionName, Users collectionUsers) {
        this.collectionId = collectionId;
        this.collectionName = collectionName;
        this.collectionUsers = collectionUsers;
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

    public Users getCollectionUsers() {
        return collectionUsers;
    }

    @XmlTransient
    public List<ApplicationCollection> getApplicationCollectionList() {
        return applicationCollectionList;
    }

    public void setApplicationCollectionList(List<ApplicationCollection> applicationCollectionList) {
        this.applicationCollectionList = applicationCollectionList;
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

    public void setCollectionUsers(Users collectionUsers) {
        this.collectionUsers = collectionUsers;
    }
    
}
