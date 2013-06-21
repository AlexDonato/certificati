package com.alessandrodonato.webapp.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "certificato", catalog = "elledia_appfuse", schema = "")
@NamedQueries({
  @NamedQuery(name = "Certificato.findAll", query = "SELECT c FROM Certificato c order by c.numero"),
  @NamedQuery(name = "Certificato.findById", query = "SELECT c FROM Certificato c WHERE c.id = :id"),
  @NamedQuery(name = "Certificato.findByDataOriginale", query = "SELECT c FROM Certificato c WHERE c.dataOriginale = :dataOriginale"),})
public class Certificato implements Serializable {

  @Transient
  private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Long id;

  @OneToMany (mappedBy = "certificato", cascade = CascadeType.PERSIST)
  private Set<Materiale> materiali = new HashSet<Materiale>();
  
  @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FORNITORE_ID", nullable = false)
  private Fornitore fornitore;
  
  @Column (name = "numero")
  private String numero;
  
  @Temporal(javax.persistence.TemporalType.DATE)
  @Column (name = "data_originale")
  private Date dataOriginale;
  
  @Column (name = "note", nullable = true)
  private String note;
  
  @Column (name = "filename", nullable = true)
  private String filename;
  
  @Column (name = "content", nullable = false)
  private byte[] content;

  @Column (name = "content_type", nullable = false)
  private String contentType;

  public PropertyChangeSupport getChangeSupport() {
    return changeSupport;
  }

  public void setChangeSupport(PropertyChangeSupport changeSupport) {
    this.changeSupport = changeSupport;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    String oldContentType = this.contentType;
    this.contentType = contentType;
    changeSupport.firePropertyChange("contentType", oldContentType, contentType);
  }
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    Long oldId = this.id;
    this.id = id;
    changeSupport.firePropertyChange("id", oldId, id);
  }
  
  /**
   * @return the materiali
   */
  public Set<Materiale> getMateriali() {
    return materiali;
  }

  /**
   * @param materiali the materiali to set
   */
  public void setMateriali(Set<Materiale> materiali) {
    this.materiali = materiali;
  }

  /**
   * @return the fornitore
   */
  public Fornitore getFornitore() {
    return fornitore;
  }

  /**
   * @param fornitore the fornitore to set
   */
  public void setFornitore(Fornitore fornitore) {
    Fornitore oldFornitore = this.fornitore;
    this.fornitore = fornitore;
    changeSupport.firePropertyChange("fornitore", oldFornitore, fornitore);
  }

  /**
   * @return the numero
   */
  public String getNumero() {
    return numero;
  }

  /**
   * @param numero the numero to set
   */
  public void setNumero(String numero) {
    String oldNumero = this.numero;
    this.numero = numero;
    changeSupport.firePropertyChange("numero", oldNumero, numero);
  }

  /**
   * @return the dataOriginale
   */
  public Date getDataOriginale() {
    return dataOriginale;
  }

  /**
   * @param dataOriginale the dataOriginale to set
   */
  public void setDataOriginale(Date dataOriginale) {
    Date oldDataOriginale = this.dataOriginale;
    this.dataOriginale = dataOriginale;
    changeSupport.firePropertyChange("dataOriginale", oldDataOriginale, dataOriginale);
  }

  /**
   * @return the note
   */
  public String getNote() {
    return note;
  }

  /**
   * @param note the note to set
   */
  public void setNote(String note) {
    String oldNote = this.note;
    this.note = note;
    changeSupport.firePropertyChange("note", oldNote, note);
  }

  /**
   * @return the filename
   */
  public String getFilename() {
    return filename;
  }

  /**
   * @param filename the filename to set
   */
  public void setFilename(String filename) {
    String oldFilename = this.filename;
    this.filename = filename;
    changeSupport.firePropertyChange("filename", oldFilename, filename);
  }

  /**
   * @return the content
   */
  public byte[] getContent() {
    return content;
  }

  /**
   * @param content the content to set
   */
  public void setContent(byte[] content) {
    byte[] oldContent = this.content;
    this.content = content;
    changeSupport.firePropertyChange("content", oldContent, content);
  }
}
