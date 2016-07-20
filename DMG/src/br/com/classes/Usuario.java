package br.com.classes;

import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import br.com.util.AN;
import br.com.util.Conexao;

public class Usuario {
	private int usuario_id = 0;
	private String login = "";
	private String senha = "";
	private boolean pode_mudar_era = false;
	//
	
	//
	
	//
	//
	
	//
	public static Connection oConn = null;
	public static PreparedStatement stmtP = null;
	public static Statement stmt = null;
	public static ResultSet rs = null;
	/*
	 * COMEÇANDO METODOS
	 * 
	 */
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
				sql1 = "select count(usuario_id) as total from usuario where login like '%"+desc+"%' ";
				sql2 = "select usuario_id, login, senha, pode_mudar_era, pode_lanc_est from usuario c where login like '%"+desc+"%' ";
			}else{
				sql1 = "select count(usuario_id) as total from usuario where usuario_id="+codigo;
				sql2 = "select usuario_id, login, senha, pode_mudar_era, pode_lanc_est from usuario c where usuario_id="+codigo;
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
					//String nome = rs.getString("tipo_fiscal").equals("F")?rs.getString("razao_nome")+" "+rs.getString("fantasia_sobrenome"):rs.getString("razao_nome");
					String nome = rs.getString("usuario_id");
					ret = rs.getString("login")+" - "+nome;
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
	public String[][] buscarTodos(String where){
		String[][] ret = null;
		try{
			oConn = (Connection) Conexao.abrirConexao();			
			stmt = (Statement) oConn.createStatement();			
			rs = stmt.executeQuery("Select count(login) as total from usuario c where login is not null "+where);
			int total =0;
			if(rs.next()){
				total = rs.getInt("total");				
			}
			if(total>0){
				ret = new String[total][4];
				rs = stmt.executeQuery("Select c.usuario_id, c.login, senha, pode_mudar_era, pode_lanc_est  from usuario c where login  is not null "+where);
				int i=0;
				while(rs.next()){
					ret[i][0] = rs.getString("usuario_id");
					ret[i][1] = rs.getString("login");
					ret[i][2] = rs.getString("pode_mudar_era");
					ret[i][3] = rs.getString("pode_lanc_est");
					i++;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			Conexao.fecharConexao();
			try{rs.close();}catch(Exception e){}
			try{stmt.close();}catch(Exception e){}
			try{oConn.close();}catch(Exception e){}
		}
		
		return ret;
	}
	//
	public String[][] buscarUsuarios(){
		String[][] ret = null;
		try{
			oConn = (Connection) Conexao.abrirConexao();			
			stmt = (Statement) oConn.createStatement();			
			rs = stmt.executeQuery("Select count(login) as total from usuario c ");
			int total =0;
			if(rs.next()){
				total = rs.getInt("total");				
			}
			if(total>0){
				ret = new String[total][4];
				rs = stmt.executeQuery("Select login, senha, pode_mudar_era, pode_lanc_est  from usuario c ");
				int i=0;
				while(rs.next()){
					ret[i][0] = rs.getString("login");
					ret[i][1] = rs.getString("senha");
					ret[i][2] = rs.getString("pode_mudar_era");
					ret[i][3] = rs.getString("pode_lanc_est");
					i++;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			Conexao.fecharConexao();
			try{rs.close();}catch(Exception e){}
			try{stmt.close();}catch(Exception e){}
			try{oConn.close();}catch(Exception e){}
		}
		
		return ret;
	}
	public String pxoID(){
		String ret = "000001";
		try{
			oConn = (Connection) Conexao.abrirConexao();			
			stmt = (Statement) oConn.createStatement();			
			rs = stmt.executeQuery("Select usuario_id  from usuario where login is not null order by usuario_id desc limit 1");
			int total =0;
			if(rs.next()){
				total = rs.getInt("usuario_id");	
				total++;
				ret= AN.seisDigitos(String.valueOf(total));
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			Conexao.fecharConexao();
			try{rs.close();}catch(Exception e){}
			try{stmt.close();}catch(Exception e){}
			try{oConn.close();}catch(Exception e){}
		}
		
		return ret;
	}
	
	public boolean existeID(String id) {
		boolean ret = false;	
		try {
			String sql = "select usuario_id from usuario where usuario_id="+id;
			System.out.println("sql "+sql);
			oConn = (Connection) Conexao.abrirConexao();
			stmt = (Statement) oConn.createStatement();			
			rs = (ResultSet) stmt.executeQuery(sql);
			if(rs.next()) {
				ret=true;
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
	public boolean existeUsuario(String desc) {
		boolean ret = false;	
		try {
			String sql = "select usuario_id from usuario where login='"+desc+"'";
			System.out.println(sql);
			oConn = (Connection) Conexao.abrirConexao();
			stmt = (Statement) oConn.createStatement();			
			rs = (ResultSet) stmt.executeQuery(sql);
			if(rs.next()) {
				ret=true;
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
	/*
	 * 
	 * Fim BUSCAS
	 * 
	 */
	//
	//
	public boolean incluirUsuario(int id, String login, String senha, String podeMudarEra, String podeLancEst){
		try{
			oConn = (Connection) Conexao.abrirConexao();			
			stmtP = (PreparedStatement) oConn.prepareStatement("INSERT INTO USUARIO(USUARIO_ID, LOGIN, SENHA, PODE_MUDAR_ERA, PODE_LANC_EST) VALUES(?, ?,?,?,?)");
			stmtP.setInt(1, id);
			stmtP.setString(2, login);
			stmtP.setString(3, senha);
			stmtP.setString(4, podeMudarEra);
			stmtP.setString(5, podeLancEst);
			stmtP.execute();
			return true;
		}catch(Exception e){
			return false;
		}finally{
			Conexao.fecharConexao();
			try{rs.close();}catch(Exception e){}
			try{stmtP.close();}catch(Exception e){}
			try{oConn.close();}catch(Exception e){}
		}		
	}
	/*
	 * Alterar
	 */
	
	public boolean alterar(int id, String login, String podeMudarEra, String podeLancEst){
		try{
			oConn = (Connection) Conexao.abrirConexao();			
			stmtP = (PreparedStatement) oConn.prepareStatement("UPDATE usuario SET LOGIN=?, PODE_MUDAR_ERA=?, PODE_LANC_EST=?  WHERE USUARIO_ID=?");
			stmtP.setString(1, login);
			stmtP.setString(2, podeMudarEra);
			stmtP.setString(3, podeLancEst);
			stmtP.setInt(4, id);			
			stmtP.execute();
			return true;
		}catch(Exception e){
			return false;
		}finally{
			Conexao.fecharConexao();
			try{rs.close();}catch(Exception e){}
			try{stmtP.close();}catch(Exception e){}
			try{oConn.close();}catch(Exception e){}
		}	
	}	
	public boolean alterarSenha(int id, String senha){
		try{
			oConn = (Connection) Conexao.abrirConexao();			
			stmtP = (PreparedStatement) oConn.prepareStatement("UPDATE usuario SET SENHA=? WHERE USUARIO_ID=?");
			stmtP.setString(1, senha);
			stmtP.setInt(2, id);
			
			stmtP.execute();
			return true;
		}catch(Exception e){
			return false;
		}finally{
			Conexao.fecharConexao();
			try{rs.close();}catch(Exception e){}
			try{stmtP.close();}catch(Exception e){}
			try{oConn.close();}catch(Exception e){}
		}	
	}
	/*
	 * Excluir
	 * 
	 */
	public boolean excluirUsuario(int id){
		boolean ret = false;
		try{
			oConn = (Connection) Conexao.abrirConexao();			
			stmt = (Statement) oConn.createStatement();
			boolean rs = stmt.execute("DELETE FROM USUARIO WHERE USUARIO_ID="+id);
			ret= true;
		}catch(Exception e){
			System.out.println("Erro ao Excluir o Usuario do BD!");
			
		}finally{
			Conexao.fecharConexao();
			try{rs.close();}catch(Exception e){}
			try{stmt.close();}catch(Exception e){}
			try{oConn.close();}catch(Exception e){}
		}
		return ret;
		
	}
	
	
	
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
//					"CÓDIGO", "CLIENTE", "FANT/SOBRE", "CIDADE", "UF",
//					"TELEFONE","FAX","CELULAR"};
					ret[i][0] = AN.quatroDigitos(rs.getString("usuario_id"));					
					ret[i][1] = rs.getString("login");	
					
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
//	public JasperPrint imprimirRelCadCCusto(String ordem) {
//		JasperPrint rel = null;
//		String sql = "select * from centro_de_custo " + ordem;
//		rs = null;
//		stmt = null;
//		oConn = Conexao.abrirConexao();
//		try{	
//			stmt = oConn.createStatement();
//			rs = stmt.executeQuery(sql);
//			// Manda a nova Query para IReport
//			JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
//			// Map - Para mandar algum parametro ao relatório
//			String arquivoJasper = "centroCusto.jasper";
//			Map parameters = new HashMap();
//			parameters.put("empresa", Principal.nomeFantasia);
//			parameters.put("cidade", Principal.municipio);
//			// /
//			InputStream caminhoRelatorio = this.getClass().getClassLoader()
//					.getResourceAsStream("relatorio/" + arquivoJasper);// caminho
//																		// do
//																		// arquivo
//																		// dentro
//																		// dos
//																		// pacotes
//			rel = JasperFillManager.fillReport(caminhoRelatorio, parameters,
//					jrRS);
//
//			JasperViewer viewer = new JasperViewer(rel, false);
//			viewer.setTitle("Relatório Cadastral de Centro de Custo");
//			viewer.setExtendedState(viewer.MAXIMIZED_BOTH);
//			viewer.setVisible(true);
//
//		} catch (Exception e) {
//			AN.jOptionPaneError("Não foi possível Imprimir o Relatório!");
//		} finally {
//			Conexao.fecharConexao();
//			try{rs.close();}catch(Exception e){}
//			try{stmt.close();}catch(Exception e){}
//			try{oConn.close();}catch(Exception e){}
//		}
//		return rel;
//	}
}

