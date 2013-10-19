package com.alessandrodonato.elledia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ordini database table.
 * 
 */
@Entity
@Table(name="ordini")
public class Ordine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, name="id" )
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column (name="data")
	private Date data;

	@Column(length=255, name="disegno")
	private String disegno;

	@Column( name="id_cliente" )
	private java.math.BigInteger idCliente;

	@Column(length=1024, name="descrizione" )
	private String descrizione;

	@Column(length=255, name="lotto" )
	private String lotto;

	@Column(length=45, name="numero" )
	private String numero;

	public Ordine() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDisegno() {
		return this.disegno;
	}

	public void setDisegno(String disegno) {
		this.disegno = disegno;
	}

	public java.math.BigInteger getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(java.math.BigInteger idCliente) {
		this.idCliente = idCliente;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getLotto() {
		return this.lotto;
	}

	public void setLotto(String lotto) {
		this.lotto = lotto;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	@Override
	public String toString () {
		return "Numero [" + numero + "] - Data [" + data + "] - [" + descrizione + "]";
	}

}