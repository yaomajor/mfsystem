package br.com.vo;

import java.io.Serializable;
import java.util.List;

public class ProdutorRuralVO implements Serializable{
	private static final long serialVersionUID = 9058425019090721593L;

	public ProdutorRuralVO(){
		super();
	}
	
	private String codigo;
	private String razaoSocial;
	private String cnpj;
	private String ie;
	private String codigoPropriedade;
	private String nomePropriedade;
	private String dataCadastro;
	private String nomeFantasia;
	private List<TelefoneVO> listaTelefone;
	private String uf;
	private String cidade;
	private String cep;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String numero;
	
	

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getIe() {
		return ie;
	}
	public void setIe(String ie) {
		this.ie = ie;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public List<TelefoneVO> getListaTelefone() {
		return listaTelefone;
	}
	public void setListaTelefone(List<TelefoneVO> listaTelefone) {
		this.listaTelefone = listaTelefone;
	}
	public String getCodigoPropriedade() {
		return codigoPropriedade;
	}
	public void setCodigoPropriedade(String codigoPropriedade) {
		this.codigoPropriedade = codigoPropriedade;
	}
	public String getNomePropriedade() {
		return nomePropriedade;
	}
	public void setNomePropriedade(String nomePropriedade) {
		this.nomePropriedade = nomePropriedade;
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
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
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
