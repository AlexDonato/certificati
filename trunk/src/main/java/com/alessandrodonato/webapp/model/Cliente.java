/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alessandrodonato.webapp.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "cliente", catalog = "elledia_appfuse", schema = "")
@NamedQueries({
  @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c order by c.ragioneSociale"),
  @NamedQuery(name = "Cliente.findById", query = "SELECT c FROM Cliente c WHERE c.id = :id"),
  @NamedQuery(name = "Cliente.findByRagioneSociale", query = "SELECT c FROM Cliente c WHERE c.ragioneSociale = :ragioneSociale"),
})
public class Cliente implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column (name = "id")
  private Long id;
  
  @Column (name = "ragione_sociale")
  private String ragioneSociale;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRagioneSociale() {
    return ragioneSociale;
  }

  public void setRagioneSociale(String ragioneSociale) {
    this.ragioneSociale = ragioneSociale;
  }
  
  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Cliente)) {
      return false;
    }
    Cliente other = (Cliente) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.alessandrodonato.elledia.certificati.domain.Cliente[ id=" + id + " ]";
  }
  
}
