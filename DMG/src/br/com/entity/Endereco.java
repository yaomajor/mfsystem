package br.com.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="ENDERECO")
public class Endereco implements Serializable{
	private static final long serialVersionUID = -5317177038321874639L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@NotNull(message="Informe o Logradouro!")
	@Size(min = 1, max = 100)
	@Column(name = "LOGRADOURO")
	private String logradouro;
	
	@NotNull(message="Informe o Numero(endere�o)!")
	@Column(name = "NUMERO")
	@Size(min = 1, max = 20)
	private String numero;

	@Column(name = "COMPLEMENTO")
	@Size(min = 0, max = 50)
	private String complemento;

	@NotNull(message="Informe o Bairro!")
	@Column(name = "BAIRRO")
	@Size(min = 1, max = 100)
	private String bairro;

	@NotNull(message="Informe a Cidade!")
	@Column(name = "CIDADE")
	@Size(min = 1, max = 100)
	private String cidade;

	@NotNull(message="Informe a UF!")
	@Column(name = "UF")
	@Size(min = 1, max = 2)
	private String uf;

	@NotNull(message="Informe o CEP!")
	@Column(name = "CEP")
	@Size(min = 1, max = 9)
	private String cep;
	
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PESSOA")
	private Pessoa pessoa;
	
	@Version
	@Column(name = "JVERSION")
	private Long jversion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Long getJversion() {
		return jversion;
	}

	public void setJversion(Long jversion) {
		this.jversion = jversion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", logradouro=" + logradouro + ", numero=" + numero + ", complemento="
				+ complemento + ", bairro=" + bairro + ", cidade=" + cidade + ", uf=" + uf + ", cep=" + cep
				+ ", pessoa=" + pessoa + ", jversion=" + jversion + "]";
	}

}
