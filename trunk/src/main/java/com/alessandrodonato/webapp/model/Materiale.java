/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alessandrodonato.webapp.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "materiale", catalog = "elledia_appfuse", schema = "")
@NamedQueries({
  @NamedQuery(name = "Materiale.findAll", query = "SELECT m FROM Materiale m"),
  @NamedQuery(name = "Materiale.findById", query = "SELECT m FROM Materiale m WHERE m.id = :id"),
  @NamedQuery(name = "Materiale.findByColata", query = "SELECT m FROM Materiale m WHERE m.colata = :colata"),
  @NamedQuery(name = "Materiale.findByDimensione", query = "SELECT m FROM Materiale m WHERE m.dimensione = :dimensione"),
  @NamedQuery(name = "Materiale.findBySpecifica", query = "SELECT m FROM Materiale m WHERE m.specifica = :specifica"),
  @NamedQuery(name = "Materiale.findByUnitaMisura", query = "SELECT m FROM Materiale m WHERE m.unitaMisura = :unitaMisura"),
  @NamedQuery(name = "Materiale.findByVersion", query = "SELECT m FROM Materiale m WHERE m.version = :version"),
  @NamedQuery(name = "Materiale.findByCertificato", query = "SELECT m FROM Materiale m WHERE m.certificato = :certificato")})
public class Materiale implements Serializable {
  @Transient
  private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Long id;
  
  @Basic(optional = false)
  @Column(name = "colata")
  private String colata;
  
  @Basic(optional = false)
  @Column(name = "dimensione")
  private String dimensione;
  
  @Basic(optional = false)
  @Column(name = "specifica")
  private String specifica;
  
  @Basic(optional = false)
  @Column(name = "tipo_materiale")
  private String tipo;
  
  @Basic(optional = false)
  @Column(name = "unita_misura")
  private String unitaMisura;
  
  @Column(name = "version")
  private Integer version;
  
  @ManyToOne (fetch = FetchType.LAZY)
  @JoinColumn (name = "certificato_id")
  private Certificato certificato;

  public Materiale() {
  }

  public Materiale(Long id) {
    this.id = id;
  }

  public Materiale(Long id, String colata, String dimensione, String specifica, String unitaMisura, Certificato certificato) {
    this.id = id;
    this.colata = colata;
    this.dimensione = dimensione;
    this.specifica = specifica;
    this.unitaMisura = unitaMisura;
    this.tipo = tipo;
    this.certificato = certificato;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    Long oldId = this.id;
    this.id = id;
    changeSupport.firePropertyChange("id", oldId, id);
  }

  public String getColata() {
    return colata;
  }

  public void setColata(String colata) {
    String oldColata = this.colata;
    this.colata = colata;
    changeSupport.firePropertyChange("colata", oldColata, colata);
  }

  public String getDimensione() {
    return dimensione;
  }

  public void setDimensione(String dimensione) {
    String oldDimensione = this.dimensione;
    this.dimensione = dimensione;
    changeSupport.firePropertyChange("dimensione", oldDimensione, dimensione);
  }

  public String getSpecifica() {
    return specifica;
  }

  public void setSpecifica(String specifica) {
    String oldSpecifica = this.specifica;
    this.specifica = specifica;
    changeSupport.firePropertyChange("specifica", oldSpecifica, specifica);
  }

  public String getUnitaMisura() {
    return unitaMisura;
  }

  public void setUnitaMisura(String unitaMisura) {
    String oldUnitaMisura = this.unitaMisura;
    this.unitaMisura = unitaMisura;
    changeSupport.firePropertyChange("unitaMisura", oldUnitaMisura, unitaMisura);
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    Integer oldVersion = this.version;
    this.version = version;
    changeSupport.firePropertyChange("version", oldVersion, version);
  }
  
  public String getTipo() {
    return tipo;
  }

  public void setTipo (String tipo) {
    String oldTipo = this.tipo;
    this.tipo = tipo;
    changeSupport.firePropertyChange("tipo", oldTipo, tipo);
  }

  public Certificato getCertificato() {
    return certificato;
  }

  public void setCertificato(Certificato certificato) {
    Certificato oldCertificato = this.certificato;
    this.certificato = certificato;
    changeSupport.firePropertyChange("certificato", oldCertificato, certificato);
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
    if (!(object instanceof Materiale)) {
      return false;
    }
    Materiale other = (Materiale) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.alessandrodonato.elledia.certificati.gui.Materiale[ id=" + id + " ]";
  }

  public void addPropertyChangeListener(PropertyChangeListener listener) {
    changeSupport.addPropertyChangeListener(listener);
  }

  public void removePropertyChangeListener(PropertyChangeListener listener) {
    changeSupport.removePropertyChangeListener(listener);
  }
  
}
