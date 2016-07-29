package br.com.vo;

import java.io.Serializable;

public class TelefoneVO implements Serializable{
	private static final long serialVersionUID = -66324182547718872L;
	
	public TelefoneVO(){
		super();
	}
	
	private String ddd;
	private String numero;
	private String contato;
	
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}
}
