package br.com.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import br.com.util.AN;
import br.com.util.Conexao;

public class Animais {
	public static Connection oConn = null;
	public static PreparedStatement stmtP = null;
	public static Statement stmt = null;
	public static ResultSet rs = null;
	
	private int animaisId = 0;
	private double zeroATresM = 0d;
	private double zeroATresF = 0d;
	private double tresAOitoM = 0d;
	private double tresAOitoF = 0d;
	private double oitoADozeM = 0d;
	private double oitoADozeF = 0d;
	private double dozeAVinteQM = 0d;
	private double dozeAVinteQF = 0d;
	private double vinteQATrintaSM = 0d;
	private double vinteQATrintaSF = 0d;
	private double acimaDeTrintaSM = 0d;
	private double acimaDeTrintaSF = 0d;
	private int codCliente = 0;
	
	//
	public int getAnimaisId() {
		return animaisId;
	}
	public void setAnimaisId(int animaisId) {
		this.animaisId = animaisId;
	}
	public double getZeroATresM() {
		return zeroATresM;
	}
	public void setZeroATresM(double zeroATresM) {
		this.zeroATresM = zeroATresM;
	}
	public double getZeroATresF() {
		return zeroATresF;
	}
	public void setZeroATresF(double zeroATresF) {
		this.zeroATresF = zeroATresF;
	}
	public double getTresAOitoM() {
		return tresAOitoM;
	}
	public void setTresAOitoM(double tresAOitoM) {
		this.tresAOitoM = tresAOitoM;
	}
	public double getTresAOitoF() {
		return tresAOitoF;
	}
	public void setTresAOitoF(double tresAOitoF) {
		this.tresAOitoF = tresAOitoF;
	}
	public double getOitoADozeM() {
		return oitoADozeM;
	}
	public void setOitoADozeM(double oitoADozeM) {
		this.oitoADozeM = oitoADozeM;
	}
	public double getOitoADozeF() {
		return oitoADozeF;
	}
	public void setOitoADozeF(double oitoADozeF) {
		this.oitoADozeF = oitoADozeF;
	}
	public double getDozeAVinteQM() {
		return dozeAVinteQM;
	}
	public void setDozeAVinteQM(double dozeAVinteQM) {
		this.dozeAVinteQM = dozeAVinteQM;
	}
	public double getDozeAVinteQF() {
		return dozeAVinteQF;
	}
	public void setDozeAVinteQF(double dozeAVinteQF) {
		this.dozeAVinteQF = dozeAVinteQF;
	}
	public double getVinteQATrintaSM() {
		return vinteQATrintaSM;
	}
	public void setVinteQATrintaSM(double vinteQATrintaSM) {
		this.vinteQATrintaSM = vinteQATrintaSM;
	}
	public double getVinteQATrintaSF() {
		return vinteQATrintaSF;
	}
	public void setVinteQATrintaSF(double vinteQATrintaSF) {
		this.vinteQATrintaSF = vinteQATrintaSF;
	}
	public double getAcimaDeTrintaSM() {
		return acimaDeTrintaSM;
	}
	public void setAcimaDeTrintaSM(double acimaDeTrintaSM) {
		this.acimaDeTrintaSM = acimaDeTrintaSM;
	}
	public double getAcimaDeTrintaSF() {
		return acimaDeTrintaSF;
	}
	public void setAcimaDeTrintaSF(double acimaDeTrintaSF) {
		this.acimaDeTrintaSF = acimaDeTrintaSF;
	}
	public int getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}
	public String[][] buscarPassandoMatrizCad(String sql1, String sql2) {
		String[][] ret = null;
		try {
			oConn = (Connection) Conexao.abrirConexao();
			stmt = (Statement) oConn.createStatement();
			rs = (ResultSet) stmt.executeQuery(sql1);
			int i = 0;
			if (rs.next()) {
				i = rs.getInt("total");
			}
			rs.close();
			if (i > 0) {
				rs = (ResultSet) stmt.executeQuery(sql2);
				ret = new String[i][12];
				i = 0;
				//String[] cab = {"Código","Faturamento", "Cliente", "Valor","Data"};
				while (rs.next()) {
					ret[i][0] = String.valueOf(rs.getInt("zero_a_tres_m"));
					ret[i][1] = String.valueOf(rs.getInt("zero_a_tres_f"));
					ret[i][2] = String.valueOf(rs.getInt("tres_a_oito_m"));
					ret[i][3] = String.valueOf(rs.getInt("tres_a_oito_f"));
					ret[i][4] = String.valueOf(rs.getInt("oito_a_doze_m"));
					ret[i][5] = String.valueOf(rs.getInt("oito_a_doze_f"));
					ret[i][6] = String.valueOf(rs.getInt("doze_a_vinte_q_m"));
					ret[i][7] = String.valueOf(rs.getInt("doze_a_vinte_q_f"));
					ret[i][8] = String.valueOf(rs.getInt("vinte_q_a_trinta_s_m"));
					ret[i][9] = String.valueOf(rs.getInt("vinte_q_a_trinta_s_f"));
					ret[i][10] = String.valueOf(rs.getInt("acima_trinta_s_m"));
					ret[i][11] = String.valueOf(rs.getInt("acima_trinta_s_f"));
					i++;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexao.fecharConexao();
			try {
				rs.close();
			} catch (Exception e) {
			}
			try {
				oConn.close();
			} catch (Exception e) {
			}
			try {
				stmt.close();
			} catch (Exception e) {
			}
		}
		return ret;
	}
	//
	public String[] buscarTotais(String sql) {
		String[] ret = null;
		try {
			oConn = (Connection) Conexao.abrirConexao();
			stmt = (Statement) oConn.createStatement();
			rs = (ResultSet) stmt.executeQuery(sql);
			int i = 0;
			if (rs.next()) {
				ret = new String[12];
				ret[0] = String.valueOf(rs.getInt("zero_a_tres_m"));
				ret[1] = String.valueOf(rs.getInt("zero_a_tres_f"));
				ret[2] = String.valueOf(rs.getInt("tres_a_oito_m"));
				ret[3] = String.valueOf(rs.getInt("tres_a_oito_f"));
				ret[4] = String.valueOf(rs.getInt("oito_a_doze_m"));
				ret[5] = String.valueOf(rs.getInt("oito_a_doze_f"));
				ret[6] = String.valueOf(rs.getInt("doze_a_vinte_q_m"));
				ret[7] = String.valueOf(rs.getInt("doze_a_vinte_q_f"));
				ret[8] = String.valueOf(rs.getInt("vinte_q_a_trinta_s_m"));
				ret[9] = String.valueOf(rs.getInt("vinte_q_a_trinta_s_f"));
				ret[10] = String.valueOf(rs.getInt("acima_trinta_s_m"));
				ret[11] = String.valueOf(rs.getInt("acima_trinta_s_f"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexao.fecharConexao();
			try {
				rs.close();
			} catch (Exception e) {
			}
			try {
				oConn.close();
			} catch (Exception e) {
			}
			try {
				stmt.close();
			} catch (Exception e) {
			}
		}
		return ret;
	}
}
