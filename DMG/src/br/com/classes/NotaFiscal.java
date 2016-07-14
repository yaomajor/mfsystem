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

	private String[][] prods = null;
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
	private String natOp = "";
	
	public String[][] getProds() {
		return prods;
	}
	public void setProds(String[][] prods) {
		this.prods = prods;
	}
	public String getNatOp() {
		return natOp;
	}
	public void setNatOp(String natOp) {
		this.natOp = natOp;
	}
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
	public boolean excluirNotaFiscal(int nf, int codCli) {
		try {
			oConn = Conexao.abrirConexao();
			stmt = oConn.createStatement();
			boolean rs = stmt.execute("DELETE FROM nota_fiscal WHERE numero="+nf+" and cliente_id="+codCli);
			 rs = stmt.execute("DELETE FROM produto_nf WHERE cliente_id="+codCli+" and nota_fiscal="+nf);
			 rs = stmt.execute("DELETE FROM animais WHERE nota_fiscal="+nf+" and cliente_id="+codCli);
			return true;
		} catch (Exception e) {
			System.out.println("Erro ao Excluir o Cliente do Romaneio do BD!");
			return false;
		} finally {
			Conexao.fecharConexao();
			try {
				oConn.close();
			} catch (Exception e) {
			}
			// try{rs.close();}catch(Exception e){}
			try {
				stmt.close();
			} catch (Exception e) {
			}
		}

	}
	//
	public boolean incluir() {
		try {
			oConn = (Connection) Conexao.abrirConexao();
			oConn.setAutoCommit(false);
			stmt = (Statement) oConn.createStatement();
			boolean rs = stmt.execute("INSERT INTO NOTA_FISCAL(CLIENTE_ID, COMP_VEND_ID, numero, qtd_ent, qtd_sai, total, data_em, "
					+ "data_ent, razao_transp, tipo, nat_op) VALUES (" + codCli+ ","+codCompVend+","+numero+","+qtdEnt+","+qtdSai+","+total+
					", '"+dataEm+"', '"+dataEnt+"','','"+tipo+"','"+natOp+"' )");
			
			for(int i=0; i<prods.length;i++){
				rs = stmt.execute("INSERT INTO produto_nf(produto_id, qtd, vlr_unit, total, nota_fiscal, cliente_id, sexo, un) "
						+ "VALUES ('" + prods[i][0]+ "',"+AN.stringPDouble( prods[i][2])+","+AN.stringPDouble( prods[i][3])+
						","+AN.stringPDouble( prods[i][5])+","+numero+","+codCli+",'"+prods[i][4]+"','"+prods[i][1]+"' )");
				String sexo = prods[i][4];
				double qtdEst = AN.stringPDouble( prods[i][2]);
				if(tipo.equals("SAÍDA")){
					qtdEst *= -1;
				}
				if(prods[i][0].equals("0-3 meses") && sexo.equals("M")){
					rs = stmt.execute("INSERT INTO animais(cliente_id, zero_a_tres_m, zero_a_tres_f, tres_a_oito_m, tres_a_oito_f, oito_a_doze_m, oito_a_doze_f,"
							+ "doze_a_vinte_q_m, doze_a_vinte_q_f, vinte_q_a_trinta_s_m, vinte_q_a_trinta_s_f, acima_trinta_s_m, acima_trinta_s_f, nota_fiscal, data_mov) "
							+ "VALUES ("+codCli+","+qtdEst+", 0,0,0,0,0, 0,0,0,0,0, 0, "+numero+",'"+dataEm+"' )");
				}else if(prods[i][0].equals("0-3 meses") && sexo.equals("F")){
					rs = stmt.execute("INSERT INTO animais(cliente_id, zero_a_tres_m, zero_a_tres_f, tres_a_oito_m, tres_a_oito_f, oito_a_doze_m, oito_a_doze_f,"
							+ "doze_a_vinte_q_m, doze_a_vinte_q_f, vinte_q_a_trinta_s_m, vinte_q_a_trinta_s_f, acima_trinta_s_m, acima_trinta_s_f, nota_fiscal, data_mov) "
							+ "VALUES ("+codCli+",0,"+qtdEst+", 0,0,0,0,0, 0,0,0,0,0, "+numero+",'"+dataEm+"')");
				}else if(prods[i][0].equals("3-8 meses") && sexo.equals("M")){
					rs = stmt.execute("INSERT INTO animais(cliente_id, zero_a_tres_m, zero_a_tres_f, tres_a_oito_m, tres_a_oito_f, oito_a_doze_m, oito_a_doze_f,"
							+ "doze_a_vinte_q_m, doze_a_vinte_q_f, vinte_q_a_trinta_s_m, vinte_q_a_trinta_s_f, acima_trinta_s_m, acima_trinta_s_f, nota_fiscal, data_mov) "
							+ "VALUES ("+codCli+",0,0,"+qtdEst+", 0,0,0,0,0, 0,0,0,0, "+numero+",'"+dataEm+"')");
				}else if(prods[i][0].equals("3-8 meses") && sexo.equals("F")){
					rs = stmt.execute("INSERT INTO animais(cliente_id, zero_a_tres_m, zero_a_tres_f, tres_a_oito_m, tres_a_oito_f, oito_a_doze_m, oito_a_doze_f,"
							+ "doze_a_vinte_q_m, doze_a_vinte_q_f, vinte_q_a_trinta_s_m, vinte_q_a_trinta_s_f, acima_trinta_s_m, acima_trinta_s_f, nota_fiscal, data_mov) "
							+ "VALUES ("+codCli+",0,0,0,"+qtdEst+", 0,0,0,0,0, 0,0,0, "+numero+",'"+dataEm+"')");
				}else if(prods[i][0].equals("8-12 meses") && sexo.equals("M")){
					rs = stmt.execute("INSERT INTO animais(cliente_id, zero_a_tres_m, zero_a_tres_f, tres_a_oito_m, tres_a_oito_f, oito_a_doze_m, oito_a_doze_f,"
							+ "doze_a_vinte_q_m, doze_a_vinte_q_f, vinte_q_a_trinta_s_m, vinte_q_a_trinta_s_f, acima_trinta_s_m, acima_trinta_s_f, nota_fiscal, data_mov) "
							+ "VALUES ("+codCli+",0,0,0,0,"+qtdEst+", 0,0,0,0,0, 0,0, "+numero+",'"+dataEm+"')");
				}else if(prods[i][0].equals("8-12 meses") && sexo.equals("F")){
					rs = stmt.execute("INSERT INTO animais(cliente_id, zero_a_tres_m, zero_a_tres_f, tres_a_oito_m, tres_a_oito_f, oito_a_doze_m, oito_a_doze_f,"
							+ "doze_a_vinte_q_m, doze_a_vinte_q_f, vinte_q_a_trinta_s_m, vinte_q_a_trinta_s_f, acima_trinta_s_m, acima_trinta_s_f, nota_fiscal, data_mov) "
							+ "VALUES ("+codCli+",0,0,0,0,0,"+qtdEst+", 0,0,0,0,0, 0, "+numero+",'"+dataEm+"')");
				}else if(prods[i][0].equals("12-24 meses") && sexo.equals("M")){
					rs = stmt.execute("INSERT INTO animais(cliente_id, zero_a_tres_m, zero_a_tres_f, tres_a_oito_m, tres_a_oito_f, oito_a_doze_m, oito_a_doze_f,"
							+ "doze_a_vinte_q_m, doze_a_vinte_q_f, vinte_q_a_trinta_s_m, vinte_q_a_trinta_s_f, acima_trinta_s_m, acima_trinta_s_f, nota_fiscal, data_mov) "
							+ "VALUES ("+codCli+",0,0,0,0,0,0,"+qtdEst+", 0,0,0,0,0, "+numero+",'"+dataEm+"')");
				}else if(prods[i][0].equals("12-24 meses") && sexo.equals("F")){
					rs = stmt.execute("INSERT INTO animais(cliente_id, zero_a_tres_m, zero_a_tres_f, tres_a_oito_m, tres_a_oito_f, oito_a_doze_m, oito_a_doze_f,"
							+ "doze_a_vinte_q_m, doze_a_vinte_q_f, vinte_q_a_trinta_s_m, vinte_q_a_trinta_s_f, acima_trinta_s_m, acima_trinta_s_f, nota_fiscal, data_mov) "
							+ "VALUES ("+codCli+",0,0,0,0,0,0,0,"+qtdEst+", 0,0,0,0, "+numero+",'"+dataEm+"')");
				}else if(prods[i][0].equals("24-36 meses") && sexo.equals("M")){
					rs = stmt.execute("INSERT INTO animais(cliente_id, zero_a_tres_m, zero_a_tres_f, tres_a_oito_m, tres_a_oito_f, oito_a_doze_m, oito_a_doze_f,"
							+ "doze_a_vinte_q_m, doze_a_vinte_q_f, vinte_q_a_trinta_s_m, vinte_q_a_trinta_s_f, acima_trinta_s_m, acima_trinta_s_f, nota_fiscal, data_mov) "
							+ "VALUES ("+codCli+",0,0,0,0,0,0,0,0,"+qtdEst+", 0,0,0, "+numero+",'"+dataEm+"')");
				}else if(prods[i][0].equals("24-36 meses") && sexo.equals("F")){
					rs = stmt.execute("INSERT INTO animais(cliente_id, zero_a_tres_m, zero_a_tres_f, tres_a_oito_m, tres_a_oito_f, oito_a_doze_m, oito_a_doze_f,"
							+ "doze_a_vinte_q_m, doze_a_vinte_q_f, vinte_q_a_trinta_s_m, vinte_q_a_trinta_s_f, acima_trinta_s_m, acima_trinta_s_f, nota_fiscal, data_mov) "
							+ "VALUES ("+codCli+",0,0,0,0,0,0,0,0,0,"+qtdEst+", 0,0, "+numero+",'"+dataEm+"')");
				}else if(prods[i][0].equals("Acima de 36 meses") && sexo.equals("M")){
					rs = stmt.execute("INSERT INTO animais(cliente_id, zero_a_tres_m, zero_a_tres_f, tres_a_oito_m, tres_a_oito_f, oito_a_doze_m, oito_a_doze_f,"
							+ "doze_a_vinte_q_m, doze_a_vinte_q_f, vinte_q_a_trinta_s_m, vinte_q_a_trinta_s_f, acima_trinta_s_m, acima_trinta_s_f, nota_fiscal, data_mov) "
							+ "VALUES ("+codCli+",0,0,0,0,0,0,0,0,0,0,"+qtdEst+", 0, "+numero+",'"+dataEm+"')");
				}else if(prods[i][0].equals("Acima de 36 meses") && sexo.equals("F")){
					rs = stmt.execute("INSERT INTO animais(cliente_id, zero_a_tres_m, zero_a_tres_f, tres_a_oito_m, tres_a_oito_f, oito_a_doze_m, oito_a_doze_f,"
							+ "doze_a_vinte_q_m, doze_a_vinte_q_f, vinte_q_a_trinta_s_m, vinte_q_a_trinta_s_f, acima_trinta_s_m, acima_trinta_s_f, nota_fiscal, data_mov) "
							+ "VALUES ("+codCli+",0,0,0,0,0,0,0,0,0,0,0,"+qtdEst+", "+numero+",'"+dataEm+"')");
				}
				//				
			}
			
			return true;
		} catch (Exception e) {
			try {
				oConn.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			AN.jOptionPaneError("Erro ao Incluir a Nota Fiscal, contate o Administrador!\n"+e.getMessage());
			System.out.println(e.getMessage());
			return false;
		}finally{		
			try {
				oConn.setAutoCommit(true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Conexao.fecharConexao();
			try{oConn.close();}catch(Exception e){}
			try{stmt.close();}catch(Exception e){}
		}
	}
	//
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
				ret = new String[i][7];
				i = 0;
				//String[] cab = {"Código","Faturamento", "Cliente", "Valor","Data"};
				while (rs.next()) {
					ret[i][0] = AN.oitoDigitos(rs.getString("nf.numero"));					
					String cliente = rs.getString("pj.razao_social");
					ret[i][1] = AN.seisDigitos(rs.getString("pj.id_pessoa")) + " - " + cliente;
					ret[i][2] = rs.getString("datap");
					ret[i][3] = rs.getString("datae");					
					ret[i][4] = String.valueOf(rs.getInt("qtd"));
					ret[i][5] = rs.getString("nf.tipo");
					ret[i][6] = AN.doublePStringRS(rs.getDouble("total"));
					
					
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
	public String[] buscarDados(int nf, int codCli) {
		String[] ret = null;
		try {
			oConn = (Connection) Conexao.abrirConexao();
			stmt = (Statement) oConn.createStatement();
			String sql = "SELECT nf.numero, nf.nat_op, pj2.id_pessoa, pj2.razao_social, DATE_FORMAT(nf.data_em, '%d/%m/%Y') as datap, "
						+ "DATE_FORMAT(nf.data_ent, '%d/%m/%Y') as datae, "
		+ "if(nf.qtd_ent>0, nf.qtd_ent,nf.qtd_sai) as qtd, nf.tipo, "
		+ "nf.total FROM nota_fiscal nf "
		+ "left join pessoa_juridica pj on pj.id_pessoa=nf.cliente_id "
		+ " left join pessoa_juridica pj2 on pj2.id_pessoa=nf.comp_vend_id "
		+ "where nf.cliente_id="+codCli+" and nf.numero="+nf;
			rs = (ResultSet) stmt.executeQuery(sql);
			System.out.println(sql);
			if (rs.next()) {
				ret = new String[8];
				ret[0] = AN.seisDigitos(rs.getString("nf.numero"));					
				String cliente = rs.getString("pj2.razao_social");
				ret[1] = AN.seisDigitos(rs.getString("pj2.id_pessoa")) + " - " + cliente;
				ret[2] = rs.getString("datap");
				ret[3] = rs.getString("datae");					
				ret[4] = String.valueOf(rs.getInt("qtd"));
				ret[5] = rs.getString("nf.tipo");
				ret[6] = AN.doublePStringRS(rs.getDouble("total"));
				ret[7] = rs.getString("nf.nat_op");
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
	public String[][] buscarDadosProd(int nf, int codCli) {
		String[][] ret = null;
		try {
			oConn = (Connection) Conexao.abrirConexao();
			stmt = (Statement) oConn.createStatement();
			String sql1 = "select count(produto_id) as total from produto_nf where cliente_id="+codCli+" and nota_fiscal="+nf;
			String sql2 = "select * from produto_nf where cliente_id="+codCli+" and nota_fiscal="+nf;
			
			rs = (ResultSet) stmt.executeQuery(sql1);
			int i=0;
			if (rs.next()) {
				i = rs.getInt("total");
				if(i>0){
					ret=new String[i][6];
					i=0;
					rs = (ResultSet) stmt.executeQuery(sql2);
					while(rs.next()){
						ret[i][0] = rs.getString("produto_id");
						ret[i][1] = rs.getString("un");
						ret[i][2] = rs.getString("qtd");
						ret[i][3] = AN.doublePStringRS(rs.getDouble("vlr_unit"));
						ret[i][4] = rs.getString("sexo");
						ret[i][5] = AN.doublePStringRS(rs.getDouble("total"));
						
						i++;
					}
					
					
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
}
