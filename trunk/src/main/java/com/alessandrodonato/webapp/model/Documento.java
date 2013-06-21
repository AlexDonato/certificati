/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alessandrodonato.webapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "documento", catalog = "elledia_appfuse", schema = "")
@NamedQueries({
  @NamedQuery(name = "Documento.findAll", 
        query = "SELECT d FROM Documento d order by d.numero"),
  @NamedQuery(name = "Documento.findById", 
        query = "SELECT d FROM Documento d WHERE d.id = :id")
})
public class Documento implements Serializable {
  
    private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column (name = "id")
  private Long id;
  
  @ManyToOne (optional = true)
  @JoinTable (name = "documento_cliente", joinColumns = {
    @JoinColumn (name = "id_documento")
  }, inverseJoinColumns = {
    @JoinColumn (name = "id_cliente")
  })
  private Cliente cliente;
  
  @Column (name = "numero")
  private String numero;
  
  @Temporal(javax.persistence.TemporalType.DATE)
  @Column (name = "data")
  private Date data; 
  
  @Column (name = "lotto")
  private String lotto;
  
  @Column (name = "disegno")
  private String disegno;
  
  @Column (name = "installazione")
  private String installazione;

  // Da trasformare in ONE 2 MANY perchè ad un documento devono esserci associati + certificati da mandare in stampa.
  @ManyToMany(cascade=CascadeType.ALL)
  @JoinTable(name = "documento_certificato",
    joinColumns = {
      @JoinColumn(name="authorId") 
    },
    inverseJoinColumns = {
      @JoinColumn(name="bookId")
    }
  )
  private Set<Materiale> materiali = new HashSet<Materiale>();
  
  
  
  
}
