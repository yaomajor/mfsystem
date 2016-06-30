package br.com.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import br.com.util.AN;
import br.com.util.Conexao;

public class Cliente extends Pessoa {
	public static Connection oConn = null;
	public static PreparedStatement stmtP = null;
	public static Statement stmt = null;
	public static ResultSet rs = null;
	
	
	
	
	public String[][] buscarPassandoMatrizCad(String sql1, String sql2) {
		String[][] ret = null;	
		try {
			oConn = (Connection) Conexao.abrirConexao();
			stmt = (Statement) oConn.createStatement();
			rs = (ResultSet) stmt.executeQuery(sql1);
			int i=0;
			if(rs.next()) {
				i = rs.getInt("total");
			}
			rs.close();
			if(i>0){				
				rs = (ResultSet) stmt.executeQuery(sql2);
				ret = new String[i][2];	
				i=0;
				while (rs.next()) {
					ret[i][0] = AN.seisDigitos(rs.getString("p.id"));					
					ret[i][1] = rs.getString("pj.razao_social");	
					
					i++;
				} 
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
	public String buscarTentaBuscaCod(String desc) {
		String ret = "";	
		try {
			String sql1 = "";
			String sql2 = "";
			oConn = (Connection) Conexao.abrirConexao();
			stmt = (Statement) oConn.createStatement();
			
			int codigo = 0;
			try{codigo = AN.stringPInt(desc);}catch(Exception e){}
			if(codigo<=0){
				try{codigo= AN.stringPInt(AN.retAteTraco(desc));}catch(Exception e){}
			}
			if(codigo<=0){
				sql1 = "SELECT count(p.id) as total FROM pessoa p "
						+ "left join pessoa_juridica pj on pj.id_pessoa = p.id "
						+" left join produtor_rural pr on pr.id_pessoa_juridica=pj.id "
						+ " where pr.cliente_contabilidade='S' and pj.razao_social like '%"+desc+"%'";
				sql2 = "SELECT p.id, pj.razao_social FROM pessoa p "
						+ "left join pessoa_juridica pj on pj.id_pessoa = p.id "
						+" left join produtor_rural pr on pr.id_pessoa_juridica=pj.id "
						+ " where pr.cliente_contabilidade='S' and pj.razao_social like '%"+desc+"%'";
			}else{
				sql1 = "SELECT count(p.id) as total FROM pessoa p "		
						+ "left join pessoa_juridica pj on pj.id_pessoa = p.id "
						+" left join produtor_rural pr on pr.id_pessoa_juridica=pj.id "
						+ " where pr.cliente_contabilidade='S' and p.id is not null and p.id="+codigo;
				sql2 = "SELECT p.id, pj.razao_social FROM pessoa p "
						+ "left join pessoa_juridica pj on pj.id_pessoa = p.id "
						+" left join produtor_rural pr on pr.id_pessoa_juridica=pj.id "
						+ " where pr.cliente_contabilidade='S' and p.id is not null and p.id="+codigo;
			}
			int i = 0;
			rs = (ResultSet) stmt.executeQuery(sql1);
			if(rs.next()) {
				i = rs.getInt("total");
			}
			if(i>1){
				ret = "-%-";
			}else{
				rs = (ResultSet) stmt.executeQuery(sql2);
				if(rs.next()) {
					String nome =  rs.getString("pj.razao_social");
					ret = AN.seisDigitos(rs.getString("p.id"))+" - "+nome;				
				}
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
}
