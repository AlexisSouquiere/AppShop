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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 *
 * @author Alexis
 */
@Entity
@Table(name = "EDITOR", schema = "APPSHOP")
@NamedQueries({
    @NamedQuery(name = "Editor.findAll", query = "SELECT e FROM Editor e"),
    @NamedQuery(name = "Editor.findByEditorId", query = "SELECT e FROM Editor e WHERE e.editorId = :editorId"),
    @NamedQuery(name = "Editor.findByEditorName", query = "SELECT e FROM Editor e WHERE e.editorName = :editorName"),
    @NamedQuery(name = "Editor.findByEditorDescription", query = "SELECT e FROM Editor e WHERE e.editorDescription = :editorDescription")})
public class Editor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @TableGenerator(name = "SEQ_EDITOR", schema="APPSHOP", table = "SEQUENCE", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_EDITOR", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.TABLE, generator="SEQ_EDITOR")
    @Basic(optional = false)
    @Column(name = "EDITOR_ID", nullable = false)
    private Integer editorId;
    @Basic(optional = false)
    @Column(name = "EDITOR_NAME", nullable = false, length = 50)
    private String editorName;
    @Column(name = "EDITOR_DESCRIPTION", length = 500)
    private String editorDescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "editor")
    private List<Application> applicationList;

    public Editor() {
    }

    public Editor(Integer editorId) {
        this.editorId = editorId;
    }

    public Editor(Integer editorId, String editorName) {
        this.editorId = editorId;
        this.editorName = editorName;
    }

    public Integer getEditorId() {
        return editorId;
    }

    public void setEditorId(Integer editorId) {
        this.editorId = editorId;
    }

    public String getEditorName() {
        return editorName;
    }

    public void setEditorName(String editorName) {
        this.editorName = editorName;
    }

    public String getEditorDescription() {
        return editorDescription;
    }

    public void setEditorDescription(String editorDescription) {
        this.editorDescription = editorDescription;
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
        hash += (editorId != null ? editorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Editor)) {
            return false;
        }
        Editor other = (Editor) object;
        if ((this.editorId == null && other.editorId != null) || (this.editorId != null && !this.editorId.equals(other.editorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.iut.javaee.appshop.commons.Editor[ editorId=" + editorId + " ]";
    }
    
}
