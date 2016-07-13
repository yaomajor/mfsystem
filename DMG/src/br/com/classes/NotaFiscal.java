package br.com.classes;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import br.com.util.AN;
import br.com.util.Conexao;

public class NotaFiscal {
	public static Connection oConn = null;
	public static PreparedStatement stmtP = null;
	public static Statement stmt = null;
	public static ResultSet rs = null;

	
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
	public boolean numeroNotaJaAdd(int nf, int clienteId, int compVendId) {
		boolean ret = false;	
		try {
			oConn = (Connection) Conexao.abrirConexao();
			stmt = (Statement) oConn.createStatement();
			rs = (ResultSet) stmt.executeQuery("SELECT numero FROM nota_fiscal where numero="+nf+" "
					+ "and cliente_id="+clienteId+" and comp_vend_id="+compVendId);			
			if(rs.next()) {
				ret = true;
			}			
		} catch (Exception e) {
			e.printStackTrace();			
		}finally{
			Conexao.fecharConexao();
			try{rs.close();}catch(Exception e){}
			try{oConn.close();}catch(Exception e){}
			try{stmt.close();}catch(Exception e){}
		}
		return ret;
	}
	//
	public boolean incluir(String cod) {
		try {
			oConn = (Connection) Conexao.abrirConexao();						
			stmt = (Statement) oConn.createStatement();
			boolean rs = stmt.execute("INSERT INTO NOTA_FISCAL(CLIENTE_ID, COMP_VEND_ID, numero, qtd_ent, qtd_sai, total, data_em, "
					+ "data_ent, razao_transp, tipo, nat_op) VALUES ('" + cod+ "','S','S')");
			return rs;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}finally{			
			Conexao.fecharConexao();
			try{oConn.close();}catch(Exception e){}
			try{stmt.close();}catch(Exception e){}
		}
	}
	
}
