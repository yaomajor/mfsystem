package br.com.util;

import java.sql.Connection;       
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



public class Conexao {
	/**
	 * 
	 * 
	 * (M) COMMERCE
	 * 
	 * 
	 * 
	 */
	public static ResultSet rs = null;
	public static Statement stmt = null;
	public static PreparedStatement stmtP = null;
	
	public static Connection oConn = null;
	static String se0 = "ad";
	
	static String se2 = "554";
	static String porta = "3306";//"9623";
	//--
	//--
	public static Connection getOConn() {
		return oConn;
	}
	//
	static String url = "";
	public static String getUrl(){
		return url;
	}
	public static String emp = "";

	
	///
	////
	
	public static void fecharConexao(){
		try{
			oConn.close();
		}catch(Exception e){}
		
	}
	static String se1 = "min4";
	public static Connection abrirConexao() {		
		//
		emp= "ESCBRASILIA";
		//
		//    \\-----------//\\-----------
		 try {//--- --- ---\\//--- --- ---\\//--- --- ---\\
			Class.forName("com.mysql.jdbc.Driver");
			if(emp.equals("ESCBRASILIA")){
				url = "jdbc:mysql://localhost:3306/dmg";//192.168.0.150 
				oConn = DriverManager.getConnection(url, "root", se0 + se1 + se2);
				//////////////////////////////////////////////////////////////////				
			}
			
			//	
			return (oConn);
		} catch (Exception ex) {
			AN.jOptionPaneError("Falha na rede!");
			return (null);//759514
		}
	}
	//
	public static void setGlobalLcTimes() {						
		try{			
			oConn = abrirConexao();
			stmt = oConn.createStatement();
			boolean rs = stmt.execute("SET GLOBAL lc_time_names='pt_BR';");
		}catch(Exception e){			
		}finally{
			fecharConexao();
			try{oConn.close();}catch(Exception e){}
			try{stmt.close();}catch(Exception e){}
		}
	}
	public static void incluirEspiao(String usuario, String obs, Connection oConn) throws Exception{
		boolean rs = false;				
		stmt = oConn.createStatement();
		rs = stmt.execute("INSERT INTO ESPIAO(USUARIO, DATALANC, OBS) VALUES ('" + usuario+ "','"+AN.dataAtualSql()+" "+AN.horaAtualHHMMSS()+"', '"+obs+"')");
	}
	//---/---/---
	public static String[] getUsuSenhaEmail(int id) {
		stmt = null;
		rs = null;
		String[] ret = new String[4];
		try {
			oConn = Conexao.abrirConexao();
			
			stmt = oConn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM EMAIL where emailid="+id);
			if(rs != null){
				if(rs.next()){
					ret[0] = rs.getString("US");
					ret[1] = rs.getString("SENHA");
					ret[2] = rs.getString("SMTP");	
					ret[3] = rs.getString("IMAP");	
				}
				return ret;
			}else{
				return null;
			}
		} catch (Exception e) {
			return null;
		}finally{
			try{oConn.close();}catch(Exception e){}
			try{rs.close();}catch(Exception e){}
			try{stmt.close();}catch(Exception e){}
		}
	}
	public static  boolean alterarEmail(String  email1, String senha1, String smtp1, String imap1,
			String  email2, String senha2, String smtp2, String imap2,
			String  email3, String senha3, String smtp3, String imap3) {
		boolean ret = true;
		try {
			oConn = (Connection) Conexao.abrirConexao();

			try{
				boolean rs = false;

				stmtP = (PreparedStatement) oConn
						.prepareStatement("UPDATE EMAIL SET us=?, SENHA=?, SMTP=?, IMAP=?  WHERE emailid=?");//
				stmtP.setString(1, email1);
				stmtP.setString(2, senha1);
				stmtP.setString(3, smtp1);
				stmtP.setString(4, imap1);
				stmtP.setInt(5, 1);
				stmtP.execute();// Grava cliente
			}catch(Exception e){e.printStackTrace();ret = false;}
			try{
				boolean rs = false;

				stmtP = (PreparedStatement) oConn
						.prepareStatement("UPDATE EMAIL SET us=?, SENHA=?, SMTP=?, IMAP=?  WHERE emailid=?");//
				stmtP.setString(1, email2);
				stmtP.setString(2, senha2);
				stmtP.setString(3, smtp2);
				stmtP.setString(4, imap2);
				stmtP.setInt(5, 2);
				stmtP.execute();// Grava cliente
			}catch(Exception e){e.printStackTrace();ret = false;}
			try{
				boolean rs = false;

				stmtP = (PreparedStatement) oConn
						.prepareStatement("UPDATE EMAIL SET us=?, SENHA=?, SMTP=?, IMAP=?  WHERE emailid=?");//
				stmtP.setString(1, email3);
				stmtP.setString(2, senha3);
				stmtP.setString(3, smtp3);
				stmtP.setString(4, imap3);
				stmtP.setInt(5, 3);
				stmtP.execute();// Grava cliente
			}catch(Exception e){e.printStackTrace();ret = false;}
			
			
			
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret=false;
		} finally {
			
			try {
				stmtP.close();
			} catch (Exception e) {
			}
			try {
				oConn.close();
			} catch (Exception e) {
			}
		}
		return ret;

	}
	public static String[][] buscarEmail() {
		String[][] ret = null;
		try {
			oConn = (Connection) Conexao.abrirConexao();
			String sql ="SELECT * FROM EMAIL "; 
			stmt = (Statement) oConn.createStatement();
			rs = (ResultSet) stmt.executeQuery(sql);
			int i=0;
			ret = new String[3][4];
			while (rs.next()) {				
				ret[i][0] = rs.getString("us");
				ret[i][1] = rs.getString("senha");
				ret[i][2] = rs.getString("smtp");
				ret[i][3] = rs.getString("imap");
				i++;
			
			} 
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
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
	
	
	
	public static boolean  validarSenha() {
		boolean ret = false;
		try {
			String mesA = AN.dataMMatualSql();
			String anoA = AN.dataAAAAatualSql();
			
			String chave1  = "x";
			String chave2  = "x";
			String chave3  = "x";
			String chave4  = "x";		
			String chave5  = "yA2mmskol";
			String chave6  = "yBblancX1";
			String chave7  = "kv2x860z";
			String chave8  = "md5zk66";
			String chave9  = "xt19985";
			String chave10 = "9685asdr1";
			String chave11 = "atrdfasr90@";
			String chave12 = "46sdpOY661";
			oConn = (Connection) Conexao.abrirConexao();
			String sql ="SELECT * FROM config_adm "; 
			stmt = (Statement) oConn.createStatement();
			rs = (ResultSet) stmt.executeQuery(sql);
			
			
			while (rs.next()) {		
				String mesFor = AN.doisDigitos(rs.getString("mes"));
				String anoFor = AN.quatroDigitos(rs.getString("ano"));
				String chaveFor = rs.getString("chave");
				if(mesFor.equals(mesA) && anoFor.equals(anoA)){
					String chaveComp ="";
					if(mesA.equals("01")){
						chaveComp = chave1;
					}else if(mesA.equals("02")){
						chaveComp = chave2;
					}else if(mesA.equals("03")){
						chaveComp = chave3;
					}else if(mesA.equals("04")){
						chaveComp = chave4;
					}else if(mesA.equals("05")){
						chaveComp = chave5;
					}else if(mesA.equals("06")){
						chaveComp = chave6;
					}else if(mesA.equals("07")){
						chaveComp = chave7;
					}else if(mesA.equals("08")){
						chaveComp = chave8;
					}else if(mesA.equals("09")){
						chaveComp = chave9;
					}else if(mesA.equals("10")){
						chaveComp = chave10;
					}else if(mesA.equals("11")){
						chaveComp = chave11;
					}else if(mesA.equals("12")){
						chaveComp = chave12;
					}
					if(chaveFor.equals(chaveComp)){
						ret=true;
					}else{
						stmtP = (PreparedStatement) oConn
								.prepareStatement("UPDATE config_adm  SET chave=?");//
						stmtP.setString(1, "xPAggNull");
						
						stmtP.execute();
					}
				}
				
				
			
			} 
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
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
			try {
				stmtP.close();
			} catch (Exception e) {
			}
		}
		return ret;
	}
}