package br.com.classes;

public class NotaFiscal {
	private int notaFiscalId = 0;
	private int codCli = 0;
	private int numero = 0;
	private int codCompVend = 0;
	private double qtdEnt = 0;
	private double qtdSai = 0;
	private double total = 0;
	private String dataEm = "0000-00-00";
	private String dataEnt = "0000-00-00";
	private String razaoTransp = "";
	private String tipo = "";
	public int getNotaFiscalId() {
		return notaFiscalId;
	}
	public void setNotaFiscalId(int notaFiscalId) {
		this.notaFiscalId = notaFiscalId;
	}
	public int getCodCli() {
		return codCli;
	}
	public void setCodCli(int codCli) {
		this.codCli = codCli;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getCodCompVend() {
		return codCompVend;
	}
	public void setCodCompVend(int codCompVend) {
		this.codCompVend = codCompVend;
	}
	public double getQtdEnt() {
		return qtdEnt;
	}
	public void setQtdEnt(double qtdEnt) {
		this.qtdEnt = qtdEnt;
	}
	public double getQtdSai() {
		return qtdSai;
	}
	public void setQtdSai(double qtdSai) {
		this.qtdSai = qtdSai;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getDataEm() {
		return dataEm;
	}
	public void setDataEm(String dataEm) {
		this.dataEm = dataEm;
	}
	public String getDataEnt() {
		return dataEnt;
	}
	public void setDataEnt(String dataEnt) {
		this.dataEnt = dataEnt;
	}
	public String getRazaoTransp() {
		return razaoTransp;
	}
	public void setRazaoTransp(String razaoTransp) {
		this.razaoTransp = razaoTransp;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	//
	
	
}
