/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alessandrodonato.webapp.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "fornitore", catalog = "elledia_appfuse", schema = "")
public class Fornitore implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  private Long id;
  
  private String piva;
  
  private String ragioneSociale;
  
  private String indirizzo;
  
  private String cap;
  
  private String citta;
  
  private String provincia;
  
  private String paese;

  private String telefono;
  
  private String note;
  
//  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fornitore")
//  private List<Certificato> certificates;

  public Fornitore() {
  }

  public Fornitore(Long id) {
    this.id = id;
  }

  public Fornitore(Long id, String ragioneSociale) {
    this.id = id;
    this.ragioneSociale = ragioneSociale;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "piva")
  public String getPiva() {
    return piva;
  }

  public void setPiva(String piva) {
    this.piva = piva;
  }

  @Basic(optional = false)
  @Column(name = "ragione_sociale", nullable = false)
  public String getRagioneSociale() {
    return ragioneSociale;
  }

  public void setRagioneSociale(String ragioneSociale) {
    this.ragioneSociale = ragioneSociale;
  }

  @Column(name = "telefono")
  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  @Column(name = "note")
  public String getNote() {
    return note;
  }

  public void setNote (String note) {
    this.note = note;
  }

  @Override
  public String toString() {
    return "Fornitore id = " + id + " - ragione sociale " + ragioneSociale;
  }

  @Column(name = "indirizzo")
	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	@Column(name = "cap")
	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	@Column(name = "citta")
	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	@Column(name = "provincia", length = 2)
	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	@Column(name = "paese")
	public String getPaese() {
		return paese;
	}

	public void setPaese(String paese) {
		this.paese = paese;
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
    if (!(object instanceof Fornitore)) {
      return false;
    }
    Fornitore other = (Fornitore) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }	

//  public List<Certificato> getCertificates() {
//    return certificates;
//  }
//
//  public void setCertificates(List<Certificato> certificates) {
//    this.certificates = certificates;
//  }
  
}
