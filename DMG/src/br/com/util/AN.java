package br.com.util;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AN {
	public static ResultSet rs = null;
	public static Statement stmt = null;
	public static PreparedStatement stmtP = null;
	
	public static Connection oConn = null;
	public static String oitoDigitos(String num) {
		String ret = "";
		try {
			if (num.length() == 1) {
				ret = "0000000" + num;
			} else if (num.length() == 2) {
				ret = "000000" + num;
			} else if (num.length() == 3) {
				ret = "00000" + num;
			} else if (num.length() == 4) {
				ret = "0000" + num;
			} else if (num.length() == 5) {
				ret = "000" + num;
			} else if (num.length() == 6) {
				ret = "00" + num;
			} else if (num.length() == 7) {
				ret = "0" + num;
			} else {
				ret = num;
			}
		} catch (Exception e) {
		}
		return ret;

	}
	public static String retData(String date) {
		String ret = "";
		Date data = new Date();
		String d = "";
		String di = "";
		String me = "";
		String an = "";
		int diaAtual = 0;
		int mesAtual = 0;
		int anoAtual = 0;
		// --
		String diaSS = "";
		String mesSS = "";
		String anoSS = "";
		int diaSet = 0;
		int mesSet = 0;
		int anoSet = 0;
		try {
			diaSS = date.substring(0, 2);
		} catch (Exception e) {
		}
		try {
			mesSS = date.substring(3, 5);
		} catch (Exception e) {
		}
		try {
			anoSS = date.substring(6, 10);
		} catch (Exception e) {
		}
		try {
			diaSet = Integer.parseInt(diaSS);
		} catch (Exception e) {
		}
		try {
			mesSet = Integer.parseInt(mesSS);
		} catch (Exception e) {
		}
		try {
			anoSet = Integer.parseInt(anoSS);
		} catch (Exception e) {
		}

		SimpleDateFormat dataC = new SimpleDateFormat("ddMMyyyy");
		SimpleDateFormat dia = new SimpleDateFormat("dd");
		SimpleDateFormat mes = new SimpleDateFormat("MM");
		SimpleDateFormat ano = new SimpleDateFormat("yyyy");
		d = dataC.format(data);
		di = dia.format(data);
		me = mes.format(data);
		an = ano.format(data);
		try {
			diaAtual = Integer.parseInt(di);
		} catch (Exception e) {
		}
		try {
			mesAtual = Integer.parseInt(me);
		} catch (Exception e) {
		}
		try {
			anoAtual = Integer.parseInt(an);
		} catch (Exception e) {
		}
		if (diaSet <= 0 || diaSet > 31) {
			diaSet = diaAtual;
		}
		if (mesSet <= 0 || mesSet > 12) {
			mesSet = mesAtual;
		}
		if (anoSet < 1900) {
			anoSet = anoAtual;
		}
		di = String.valueOf(diaSet);
		me = String.valueOf(mesSet);
		an = String.valueOf(anoSet);
		if (di.length() == 1) {
			di = "0" + di;
		}
		if (me.length() == 1) {
			me = "0" + me;
		}

		ret = di + me + an;
		ret = dataCorreta(di + "/" + me + "/" + an);
		return ret;
	}
	public static int stringPInt(String val) {
		int ret = 0;
		try {
			val = val.replace("-", "");
		} catch (Exception e) {
		}
		try {
			val = val.replace(".", "");
		} catch (Exception e) {
		}
		try {
			val = val.replace(",", ".");
		} catch (Exception e) {
		}
		try {
			ret = Integer.parseInt(val);
		} catch (Exception e) {
		}
		return ret;
	}
	public static String dataCorreta(String data) {
		String ret = "00/00/0000";
		String diaS = "";
		int dia = 1;
		try {
			diaS = data.substring(0, 2);
		} catch (Exception e) {
		}
		dia = stringPInt(diaS);
		//
		String mesS = "";
		try {
			mesS = data.substring(3, 5);
		} catch (Exception e) {
		}
		//
		String anoS = "";
		try {
			anoS = data.substring(6, 10);
		} catch (Exception e) {
		}
		//
		String dataUltimoDia = retDataUltimoDiaMes("01" + "/" + mesS + "/"
				+ anoS);
		int ultimoDiaMes = stringPInt(dataUltimoDia.substring(0, 2));
		if (ultimoDiaMes < dia) {
			dia = ultimoDiaMes;
		}
		diaS = String.valueOf(dia);
		diaS = dia < 10 ? "0" + diaS : diaS;
		ret = diaS + "/" + mesS + "/" + anoS;
		return ret;
	}
	public static String dataAtualText() {
		// cria o formatador
		String ret = "";
		try {
			oConn = (Connection) Conexao.abrirConexao();
			stmt = (Statement) oConn.createStatement();
			rs = (ResultSet) stmt.executeQuery("SELECT DATE_FORMAT(curdate(), '%d/%m/%Y') as data_atual");
			
			if(rs.next()) {
				ret = rs.getString("data_atual");
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
	public static String retDataUltimoDiaMes(String dataA) {
		String ret = "";
		try {
			SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
			//Date date = sd.parse(dataA);
			int ano = Integer.parseInt(dataA.substring(6, 10));
			int mes = Integer.parseInt(dataA.substring(3, 5));
			String mesS = dataA.substring(3, 5);
			int dia = Integer.parseInt(dataA.substring(0, 2));
			// dataA = sd.format(date);
			Calendar c = new GregorianCalendar(ano, mes - 1, dia);
			for (int i = 0; i < 32; i++) { //

				// c.add(Calendar.DATE, dias);//Adiciona os dias na data
				c.add(Calendar.DATE, 1);
				// c.set(ano, mes, dia);
				ret = sd.format(c.getTime());
				String mesFor = ret.substring(3, 5);
				if (!mesS.equals(mesFor)) {
					break;
				}
			}
			c.add(Calendar.DATE, -1);
			ret = sd.format(c.getTime());
			// System.out.println("ULTIMO DIA: "+ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	public static String retHora(String hora, boolean bcoTrue) {
		String ret = "";
		try {
			hora = hora.replace(":", "");
		} catch (Exception e) {
		}
		String h = "";
		String m = "";
		boolean hor = false;
		boolean min = false;
		if (hora.equals("    ") && bcoTrue == true) {
			ret = "  :  ";
		} else {
			if (hora.length() == 4) {
				h = hora.substring(0, 2);
				m = hora.substring(2, 4);
				int hi = -1;
				int mi = -1;
				try {
					hi = Integer.parseInt(h);
				} catch (Exception e) {
				}
				try {
					mi = Integer.parseInt(m);
				} catch (Exception e) {
				}
				// ---------------------------\\
				if (hi >= 0 && hi <= 23) {
					hor = true;
				} else {
					h = hhAtualText();
				}
				if (mi >= 0 && mi <= 59) {
					min = true;
				} else {
					m = mmAtualText();
				}
				if (hor == true && min == true) {
					ret = h + ":" + m;
				} else {
					ret = horaAtualText();
				}
			} else {
				ret = horaAtualText();
			}
			if (hora.equals("  :  ") && bcoTrue == true) {
				ret = "  :  ";
			}
		}

		return ret;
	}
	public static String hhAtualText() {
		// cria o formatador
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("HH");
		// cria a string
		String dataA = formatador.format(data);
		return dataA;
	}

	public static String mmAtualText() {
		// cria o formatador
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("mm");
		// cria a string
		String dataA = formatador.format(data);
		return dataA;
	}
	public static String horaAtualText() {
		// cria o formatador
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
		// cria a string
		String dataA = formatador.format(data);
		return dataA;
	}
	public static String horaAtualHHMM() {
		// cria o formatador
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("hh:mm");
		// cria a string
		String dataA = formatador.format(data);
		return dataA;
	}

	public static String horaAtualHH() {
		// cria o formatador
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("hh");
		// cria a string
		String dataA = formatador.format(data);
		return dataA;
	}

	public static String horaAtualMM() {
		// cria o formatador
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("mm");
		// cria a string
		String dataA = formatador.format(data);
		return dataA;
	}
	
	
	public static int jOptionPaneQuestion(String msg) {
		int ret = 1;
		JLabel input = new JLabel();
		String[] options = { "Sim", "Não" };
		Object[] message = { msg, input };
		int result = JOptionPane.showOptionDialog(null, message, "ATENÇÃO!",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				options, options[0]);
		switch (result) {
		case 0: // SIM
			ret = 0;
			// ...
			break;
		case 1: // NAO
			ret = 1;
			break;
		}
		return ret;
	}

	public static int jOptionPaneInformation(String msg) {
		int ret = 1;
		JLabel input = new JLabel();
		String[] options = { "Ok" };
		Object[] message = { msg, input };
		int result = JOptionPane.showOptionDialog(null, message, "ATENÇÃO!",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
				null, options, options[0]);
		switch (result) {
		case 0: // SIM
			ret = 0;
			// ...
			break;
		case 1: // NAO
			ret = 1;
			break;
		}
		return ret;
	}

	public static int jOptionPaneError(String msg) {
		int ret = 1;
		JLabel input = new JLabel();
		String[] options = { "Ok" };
		Object[] message = { msg, input };
		int result = JOptionPane.showOptionDialog(null, message,
				"ATENÇÃO!", JOptionPane.YES_NO_OPTION,
				JOptionPane.ERROR_MESSAGE, null, options, options[0]);
		switch (result) {
		case 0: // SIM
			ret = 0;
			// ...
			break;
		case 1: // NAO
			ret = 1;
			break;
		}
		return ret;
	}

	public static int jOptionPaneAlert(String msg) {
		int ret = 1;
		JLabel input = new JLabel();
		String[] options = { "Ok" };
		Object[] message = { msg, input };
		int result = JOptionPane.showOptionDialog(null, message,
				"ATENÇÃO!", JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE, null, options, options[0]);
		switch (result) {
		case 0: // SIM
			ret = 0;
			// ...
			break;
		case 1: // NAO
			ret = 1;
			break;
		}
		return ret;
	}

	public static int jOptionPanePlain(String msg) {
		int ret = 1;
		JLabel input = new JLabel();
		String[] options = { "Ok" };
		Object[] message = { msg, input };
		int result = JOptionPane.showOptionDialog(null, message,
				"ATENÇÃO!", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		switch (result) {
		case 0: // SIM
			ret = 0;
			// ...
			break;
		case 1: // NAO
			ret = 1;
			break;
		}
		return ret;
	}

	public static boolean jOptionPanePassword(String msg, String senhaCorreta) {
		boolean ret = false;
		JPasswordField password = new JPasswordField(10);
		password.setEchoChar('*');
		// Cria um rótulo para o campo
		JLabel rotulo = new JLabel(msg);
		// Coloca o rótulo e a caixa de entrada numa JPanel:
		JPanel entUsuario = new JPanel();
		entUsuario.add(rotulo);
		entUsuario.add(password);
		JOptionPane.showMessageDialog(null, entUsuario,
				"Comando restrito!", JOptionPane.INFORMATION_MESSAGE);
		String senha = password.getText();
		if (senha.equals(senhaCorreta)) {
			ret = true;
		}
		return ret;
	}

	public static String jOptionPanePassword2(String msg) {
		String ret = "";
		JPasswordField password = new JPasswordField(10);
		password.setEchoChar('*');
		// Cria um rótulo para o campo
		JLabel rotulo = new JLabel(msg);
		// Coloca o rótulo e a caixa de entrada numa JPanel:
		JPanel entUsuario = new JPanel();
		entUsuario.add(rotulo);
		entUsuario.add(password);
		JOptionPane.showMessageDialog(null, entUsuario,
				"Comando restrito!", JOptionPane.INFORMATION_MESSAGE);
		ret = password.getText();
		return ret;
	}

	public static int jOptionPaneTipoImpressora() {
		int ret = -1;
		String msg = "Selecione o Tipo de Impressora:";
		JComboBox combo = new JComboBox();
		combo.addItem("Laser");
		combo.addItem("Matricial");
		// Cria um rótulo para o campo
		JLabel rotulo = new JLabel(msg);
		// Coloca o rótulo e a caixa de entrada numa JPanel:
		JPanel entUsuario = new JPanel();
		entUsuario.add(rotulo);
		entUsuario.add(combo);
		JOptionPane.showMessageDialog(null, entUsuario, "Imprimir!",
				JOptionPane.INFORMATION_MESSAGE);
		String tip = combo.getSelectedItem().toString();
		if (tip.equals("Laser")) {
			ret = 0;
		} else {
			ret = 1;
		}
		return ret;
	}
	public static String quatroDigitos(String num) {
		String ret = "";
		try {
			if (num.length() == 1) {
				ret = "000" + num;
			} else if (num.length() == 2) {
				ret = "00" + num;
			} else if (num.length() == 3) {
				ret = "0" + num;
			} else {
				ret = num;
			}
		} catch (Exception e) {
		}
		return ret;

	}

	//
	public static String cincoDigitos(String num) {
		String ret = "";
		try {
			if (num.length() == 1) {
				ret = "0000" + num;
			} else if (num.length() == 2) {
				ret = "000" + num;
			} else if (num.length() == 3) {
				ret = "00" + num;
			} else if (num.length() == 4) {
				ret = "0" + num;
			} else {
				ret = num;
			}
		} catch (Exception e) {
		}
		return ret;

	}

	//
	public static String seisDigitos(String num) {
		String ret = "";
		try {
			if (num.length() == 1) {
				ret = "00000" + num;
			} else if (num.length() == 2) {
				ret = "0000" + num;
			} else if (num.length() == 3) {
				ret = "000" + num;
			} else if (num.length() == 4) {
				ret = "00" + num;
			} else if (num.length() == 5) {
				ret = "0" + num;
			} else {
				ret = num;
			}
		} catch (Exception e) {
		}
		return ret;
	}
	public static String seteDigitos(String num) {
		String ret = "";
		try {
			if (num.length() == 1) {
				ret = "000000" + num;
			} else if (num.length() == 2) {
				ret = "00000" + num;
			} else if (num.length() == 3) {
				ret = "0000" + num;
			} else if (num.length() == 4) {
				ret = "000" + num;
			} else if (num.length() == 5) {
				ret = "00" + num;
			} else if (num.length() == 6) {
				ret = "0" + num;
			} else {
				ret = num;
			}
		} catch (Exception e) {
		}
		return ret;
	}

	public static String noveDigitos(String num) {
		String ret = "";
		try {
			if (num.length() == 1) {
				ret = "00000000" + num;
			} else if (num.length() == 2) {
				ret = "0000000" + num;
			} else if (num.length() == 3) {
				ret = "000000" + num;
			} else if (num.length() == 4) {
				ret = "00000" + num;
			} else if (num.length() == 5) {
				ret = "0000" + num;
			} else if (num.length() == 6) {
				ret = "000" + num;
			} else if (num.length() == 7) {
				ret = "00" + num;
			} else if (num.length() == 8) {
				ret = "0" + num;
			} else {
				ret = num;
			}
		} catch (Exception e) {
		}
		return ret;
	}

	public static String treisDigitos(String num) {
		String ret = "";
		try {
			if (num.length() == 1) {
				ret = "00" + num;
			} else if (num.length() == 2) {
				ret = "0" + num;
			} else {
				ret = num;
			}
		} catch (Exception e) {
		}
		return ret;
	}
	public static String horaAtualHHMMSS() {
		// cria o formatador
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("hh:mm:ss");
		// cria a string
		String dataA = formatador.format(data);
		return dataA;
	}
	public static String dataAtualSql() {
		// cria o formatador
		String ret = "";
		try {
			oConn = (Connection) Conexao.abrirConexao();
			stmt = (Statement) oConn.createStatement();
			rs = (ResultSet) stmt.executeQuery("SELECT current_date as data_atual");
			
			if(rs.next()) {
				ret = rs.getString("data_atual");
			}			
		} catch (Exception e) {
			//e.printStackTrace();			
		}finally{
			Conexao.fecharConexao();
			try{rs.close();}catch(Exception e){}
			try{oConn.close();}catch(Exception e){}
			try{stmt.close();}catch(Exception e){}
		}
		return ret;
	}

	public static String dezDigitos(String num) {
		String ret = "";
		try {
			if (num.length() == 1) {
				ret = "000000000" + num;
			} else if (num.length() == 2) {
				ret = "00000000" + num;
			} else if (num.length() == 3) {
				ret = "0000000" + num;
			} else if (num.length() == 4) {
				ret = "000000" + num;
			} else if (num.length() == 5) {
				ret = "00000" + num;
			} else if (num.length() == 6) {
				ret = "0000" + num;
			} else if (num.length() == 7) {
				ret = "000" + num;
			} else if (num.length() == 8) {
				ret = "00" + num;
			} else if (num.length() == 9) {
				ret = "0" + num;
			} else {
				ret = num;
			}
		} catch (Exception e) {
		}
		return ret;
	}

	public static String onzeDigitos(String num) {
		String ret = "";
		try {
			if (num.length() == 1) {
				ret = "0000000000" + num;
			} else if (num.length() == 2) {
				ret = "000000000" + num;
			} else if (num.length() == 3) {
				ret = "00000000" + num;
			} else if (num.length() == 4) {
				ret = "0000000" + num;
			} else if (num.length() == 5) {
				ret = "000000" + num;
			} else if (num.length() == 6) {
				ret = "00000" + num;
			} else if (num.length() == 7) {
				ret = "0000" + num;
			} else if (num.length() == 8) {
				ret = "000" + num;
			} else if (num.length() == 9) {
				ret = "00" + num;
			} else if (num.length() == 10) {
				ret = "0" + num;
			} else {
				ret = num;
			}
		} catch (Exception e) {
		}
		return ret;
	}
	public static String quinzeDigitos(String num) {
		String ret = "";
		try {
			if (num.length() == 1) {
				ret = "00000000000000" + num;
			} else if (num.length() == 2) {
				ret = "0000000000000" + num;
			} else if (num.length() == 3) {
				ret = "000000000000" + num;
			} else if (num.length() == 4) {
				ret = "00000000000" + num;
			} else if (num.length() == 5) {
				ret = "0000000000" + num;
			} else if (num.length() == 6) {
				ret = "000000000" + num;
			} else if (num.length() == 7) {
				ret = "00000000" + num;
			} else if (num.length() == 8) {
				ret = "0000000" + num;
			} else if (num.length() == 9) {
				ret = "000000" + num;
			} else if (num.length() == 10) {
				ret = "00000" + num;
			} else if (num.length() == 11) {
				ret = "0000" + num;
			} else if (num.length() == 12) {
				ret = "000" + num;
			} else if (num.length() == 13) {
				ret = "00" + num;
			}else if (num.length() == 14) {
				ret = "0" + num;
			} else {
				ret = num;
			}
		} catch (Exception e) {
		}
		return ret;
	}
	public static String dozeDigitos(String num) {
		String ret = "";
		try {
			if (num.length() == 1) {
				ret = "00000000000" + num;
			} else if (num.length() == 2) {
				ret = "0000000000" + num;
			} else if (num.length() == 3) {
				ret = "000000000" + num;
			} else if (num.length() == 4) {
				ret = "00000000" + num;
			} else if (num.length() == 5) {
				ret = "0000000" + num;
			} else if (num.length() == 6) {
				ret = "000000" + num;
			} else if (num.length() == 7) {
				ret = "00000" + num;
			} else if (num.length() == 8) {
				ret = "0000" + num;
			} else if (num.length() == 9) {
				ret = "000" + num;
			} else if (num.length() == 10) {
				ret = "00" + num;
			} else if (num.length() == 11) {
				ret = "0" + num;
			} else {
				ret = num;
			}
		} catch (Exception e) {
		}
		return ret;
	}
	public static String quatorzeDigitos(String num) {
		String ret = "";
		try {
			if (num.length() == 1) {
				ret = "0000000000000" + num;
			} else if (num.length() == 2) {
				ret = "000000000000" + num;
			} else if (num.length() == 3) {
				ret = "00000000000" + num;
			} else if (num.length() == 4) {
				ret = "0000000000" + num;
			} else if (num.length() == 5) {
				ret = "000000000" + num;
			} else if (num.length() == 6) {
				ret = "00000000" + num;
			} else if (num.length() == 7) {
				ret = "0000000" + num;
			} else if (num.length() == 8) {
				ret = "000000" + num;
			} else if (num.length() == 9) {
				ret = "00000" + num;
			} else if (num.length() == 10) {
				ret = "0000" + num;
			} else if (num.length() == 11) {
				ret = "000" + num;
			} else if (num.length() == 12) {
				ret = "00" + num;
			} else if (num.length() == 13) {
				ret = "0" + num;
			} else {
				ret = num;
			}
		} catch (Exception e) {
		}
		return ret;
	}

	public static String dezesseteDigitos(String num) {
		String ret = "";
		try {
			if (num.length() == 1) {
				ret = "0000000000000000" + num;
			} else if (num.length() == 2) {
				ret = "000000000000000" + num;
			} else if (num.length() == 3) {
				ret = "00000000000000" + num;
			} else if (num.length() == 4) {
				ret = "0000000000000" + num;
			} else if (num.length() == 5) {
				ret = "000000000000" + num;
			} else if (num.length() == 6) {
				ret = "00000000000" + num;
			} else if (num.length() == 7) {
				ret = "0000000000" + num;
			} else if (num.length() == 8) {
				ret = "000000000" + num;
			} else if (num.length() == 9) {
				ret = "00000000" + num;
			} else if (num.length() == 10) {
				ret = "0000000" + num;
			} else if (num.length() == 11) {
				ret = "000000" + num;
			} else if (num.length() == 12) {
				ret = "00000" + num;
			} else if (num.length() == 13) {
				ret = "0000" + num;
			} else if (num.length() == 14) {
				ret = "000" + num;
			} else if (num.length() == 15) {
				ret = "00" + num;
			} else if (num.length() == 16) {
				ret = "0" + num;
			} else {
				ret = num;
			}
		} catch (Exception e) {
		}
		return ret;
	}

	public static String doisDigitos(String num) {
		String ret = "";
		try {
			if (num.length() == 1) {
				ret = "0" + num;
			} else {
				ret = num;
			}

		} catch (Exception e) {
		}
		return ret;
	}
	public static String dataAAAAatualSql() {
		// cria o formatador
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy");
		// cria a string
		String dataA = formatador.format(data);
		return dataA;
	}
	public static String jOptionPaneCTextValor(String msg, String msg2,
			String msg3) {
		String ret = "";
		JTextField text = new JTextField();
		text.setLocation(new Point(90, 6));
		text.setDocument(new MonetarioDocumentVirg());
		text.setHorizontalAlignment(SwingConstants.TRAILING);
		text.setSize(new Dimension(80, 21));
		text.setText("0");
		JLabel rotulo = new JLabel(msg);
		rotulo.setLocation(new Point(0, 6));
		rotulo.setSize(new Dimension(80, 21));
		rotulo.setHorizontalAlignment(SwingConstants.LEADING);
		JLabel rotulo2 = new JLabel(msg2);
		rotulo.setLocation(new Point(0, 6));
		rotulo.setSize(new Dimension(80, 21));
		rotulo.setHorizontalAlignment(SwingConstants.LEADING);
		JLabel rotulo3 = new JLabel(msg3);
		rotulo.setLocation(new Point(0, 6));
		rotulo.setSize(new Dimension(80, 21));
		rotulo.setHorizontalAlignment(SwingConstants.LEADING);
		// Coloca o rótulo e a caixa de entrada numa JPanel:
		JPanel entUsuario = new JPanel();
		entUsuario.setSize(500, 500);
		// entUsuario.setLayout(null);
		GridLayout gridLayout = new GridLayout();
		gridLayout.setRows(4);
		entUsuario.setLayout(gridLayout);
		entUsuario.add(rotulo);
		entUsuario.add(rotulo2);
		entUsuario.add(rotulo3);
		entUsuario.add(text);
		JOptionPane.showMessageDialog(null, entUsuario, "Atenção!",
				JOptionPane.INFORMATION_MESSAGE);

		ret = text.getText();
		return ret;
	}
	public static String dataMMatualSql() {
		// cria o formatador
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("MM");
		// cria a string
		String dataA = formatador.format(data);
		return dataA;
	}
	public static String jOptionPaneCTextSomenteNumeros(String msg, String msg2,
			String msg3) {
		String ret = "";
		JTextField text = new JTextField();
		text.setLocation(new Point(90, 6));
		text.setDocument(new SomenteNumeros8());
		text.setHorizontalAlignment(SwingConstants.TRAILING);
		text.setSize(new Dimension(80, 21));
		text.setText("0");
		JLabel rotulo = new JLabel(msg);
		rotulo.setLocation(new Point(0, 6));
		rotulo.setSize(new Dimension(80, 21));
		rotulo.setHorizontalAlignment(SwingConstants.LEADING);
		JLabel rotulo2 = new JLabel(msg2);
		rotulo.setLocation(new Point(0, 6));
		rotulo.setSize(new Dimension(80, 21));
		rotulo.setHorizontalAlignment(SwingConstants.LEADING);
		JLabel rotulo3 = new JLabel(msg3);
		rotulo.setLocation(new Point(0, 6));
		rotulo.setSize(new Dimension(80, 21));
		rotulo.setHorizontalAlignment(SwingConstants.LEADING);
		// Coloca o rótulo e a caixa de entrada numa JPanel:
		JPanel entUsuario = new JPanel();
		entUsuario.setSize(500, 500);
		// entUsuario.setLayout(null);
		GridLayout gridLayout = new GridLayout();
		gridLayout.setRows(4);
		entUsuario.setLayout(gridLayout);
		entUsuario.add(rotulo);
		entUsuario.add(rotulo2);
		entUsuario.add(rotulo3);
		entUsuario.add(text);
		JOptionPane.showMessageDialog(null, entUsuario, "Atenção!",
				JOptionPane.INFORMATION_MESSAGE);

		ret = text.getText();
		return ret;
	}

	public static String jOptionPaneJComboBox(String[] dados, String msg) {
		String ret = "";
		JComboBox combo = new JComboBox();
		for (int i = 0; i < dados.length; i++) {
			combo.addItem(dados[i]);
		}
		
		// Cria um rótulo para o campo
		JLabel rotulo = new JLabel(msg);
		// Coloca o rótulo e a caixa de entrada numa JPanel:
		JPanel entUsuario = new JPanel();
		entUsuario.add(rotulo);
		entUsuario.add(combo);
		JOptionPane.showMessageDialog(null, entUsuario, "Atenção!",
				JOptionPane.INFORMATION_MESSAGE);
		ret = combo.getSelectedItem().toString();
		return ret;
	}
	public static String jOptionPaneJComboBox(String[] dados, String msg, String select) {
		String ret = "";
		JComboBox combo = new JComboBox();
		for (int i = 0; i < dados.length; i++) {
			combo.addItem(dados[i]);
		}
		combo.setSelectedItem(select);
		// Cria um rótulo para o campo
		JLabel rotulo = new JLabel(msg);
		// Coloca o rótulo e a caixa de entrada numa JPanel:
		JPanel entUsuario = new JPanel();
		entUsuario.add(rotulo);
		entUsuario.add(combo);
		JOptionPane.showMessageDialog(null, entUsuario, "Atenção!",
				JOptionPane.INFORMATION_MESSAGE);
		ret = combo.getSelectedItem().toString();
		return ret;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
