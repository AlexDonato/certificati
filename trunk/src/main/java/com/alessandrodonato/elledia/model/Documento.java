package com.alessandrodonato.elledia.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


/**
 * The persistent class for the documento database table.
 * 
 */
@Entity
@Table(name="documento")
public class Documento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String codice;
	private Date dataDocumento;
	private Cliente cliente;
	private String ordine;
	private Date dataOrdine;
	private String lotto;
	private String disegno;
	private String descrizione;
	
	private List <Materiale> listaMateriali;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column (name = "codice")
	public String getCodice () {
		return codice;
	}
	
	public void setCodice (String codice) {
		this.codice = codice;
	}
	
	@Column (name = "data")
	public Date getDataDocumento() {
		return dataDocumento;
	}

	public void setDataDocumento(Date dataDocumento) {
		this.dataDocumento = dataDocumento;
	}

	@ManyToOne (fetch= FetchType.LAZY)
	@JoinColumn (name = "id_cliente")
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Column (name = "ordine")
	public String getOrdine() {
		return ordine;
	}

	public void setOrdine(String ordine) {
		this.ordine = ordine;
	}

	@Column (name = "data_ordine")
	public Date getDataOrdine() {
		return dataOrdine;
	}

	public void setDataOrdine(Date dataOrdine) {
		this.dataOrdine = dataOrdine;
	}

	@Column (name = "lotto")
	public String getLotto() {
		return lotto;
	}

	public void setLotto(String lotto) {
		this.lotto = lotto;
	}

	@Column (name = "disegno")
	public String getDisegno() {
		return disegno;
	}

	public void setDisegno(String disegno) {
		this.disegno = disegno;
	}

	@Column (name = "descrizione")
	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@ManyToMany
	@JoinTable (name = "documenti_materiali",
							joinColumns={ @JoinColumn (name = "id_documento", referencedColumnName = "id")},
							inverseJoinColumns= { @JoinColumn (name = "id_materiale", referencedColumnName = "id")})
	public List<Materiale> getListaMateriali() {
		return listaMateriali;
	}

	public void setListaMateriali(List<Materiale> listaMateriali) {
		this.listaMateriali = listaMateriali;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}