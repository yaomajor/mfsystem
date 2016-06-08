package br.com.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="PRODUTOR_RURAL")
public class ProdutorRural implements Serializable{
	private static final long serialVersionUID = -8166916966483884975L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@NotNull
	@Size(min = 1, max = 1)
	@Column(name = "CLIENTE_CONTABILIDADE")
	private String clienteContalidade;
	
	@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PESSOA_JURIDICA")
	private PessoaJuridica pessoaJuridica;
	
	@NotNull
	@Version
	@Column(name = "JVERSION")
	private Long jversion;
	
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "CODIGO")
	private String codigo;
	
	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "NOME_PROPRIEDADE")
	private String nomePropriedade;
	
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "CODIGO_PROPRIEDADE")
	private String codigoPropriedade;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClienteContalidade() {
		return clienteContalidade;
	}

	public void setClienteContalidade(String clienteContalidade) {
		this.clienteContalidade = clienteContalidade;
	}

	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}

	public Long getJversion() {
		return jversion;
	}

	public void setJversion(Long jversion) {
		this.jversion = jversion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNomePropriedade() {
		return nomePropriedade;
	}

	public void setNomePropriedade(String nomePropriedade) {
		this.nomePropriedade = nomePropriedade;
	}

	public String getCodigoPropriedade() {
		return codigoPropriedade;
	}

	public void setCodigoPropriedade(String codigoPropriedade) {
		this.codigoPropriedade = codigoPropriedade;
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
		ProdutorRural other = (ProdutorRural) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}