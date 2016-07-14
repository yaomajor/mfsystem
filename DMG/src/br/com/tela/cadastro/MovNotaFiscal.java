package br.com.tela.cadastro;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import br.com.classes.NotaFiscal;
import br.com.util.AN;
import br.com.util.JTextData;
import br.com.util.JTextData2;
import br.com.util.JTextFieldFocu;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class MovNotaFiscal extends JInternalFrame {
	private static JTextField textField;
	private static JTable table;
	static JScrollPane scrollPane;
	private static JTextField textDataDe;
	private static JTextField textDataA;
	
	public static int linhaSel = 0;
	public static int colSel = 0;
	public static int totalL = 0;
	static JComboBox comboBox;
	static JPanel panel;
	/**
	 * Launch the application.
	 */
	
	String dataAtual = "";
	/**
	 * Create the frame.
	 */
	static JLabel lblNotasFiscais;
	static int codCli = 0;
	public MovNotaFiscal(int codCli) {
		setFrameIcon(new ImageIcon(MovNotaFiscal.class.getResource("/image/icNFEE.png")));
		setTitle("Movimentação de Notas Fiscais - "+Inicio.labelCliente.getText());
		this.codCli = codCli;
		dataAtual = AN.dataAtualText();
		getContentPane().setBackground(Color.WHITE);
		setIconifiable(true);
		setBounds(100, 100, 791, 479);
		getContentPane().setLayout(null);
		
		JLabel lblCliente = new JLabel("Produtor:");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCliente.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCliente.setBounds(0, 11, 67, 21);
		getContentPane().add(lblCliente);
		
		textField = new JTextFieldFocu();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					atualizaTable();
				}
			}
		});
		textField.setBounds(72, 11, 330, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 93, 755, 293);
		getContentPane().add(scrollPane);
		
		
		scrollPane.setViewportView(getJTable());
		
		lblNotasFiscais = new JLabel("Notas Fiscais (0)");
		lblNotasFiscais.setForeground(SystemColor.textHighlight);
		lblNotasFiscais.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotasFiscais.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNotasFiscais.setBounds(0, 61, 765, 28);
		getContentPane().add(lblNotasFiscais);
		
		JLabel lblPerodo = new JLabel("Per\u00EDodo de:");
		lblPerodo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPerodo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPerodo.setBounds(435, 11, 76, 21);
		getContentPane().add(lblPerodo);
		
		textDataDe = new JTextData();
		textDataDe.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
			}
		});
		textDataDe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
					
					textDataA.requestFocus();
				}
			}
		});
		textDataDe.setText("01"+dataAtual.substring(2, 10));
		textDataDe.setColumns(10);
		textDataDe.setBounds(513, 11, 67, 20);
		getContentPane().add(textDataDe);
		
		JLabel lblA = new JLabel("a");
		lblA.setHorizontalAlignment(SwingConstants.CENTER);
		lblA.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblA.setBounds(581, 11, 27, 21);
		getContentPane().add(lblA);
		
		textDataA = new JTextData();
		textDataA.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				atualizaTable();
			}
		});
		textDataA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
					textField.requestFocus();
					
					
				}
			}
		});
		textDataA.setText(dataAtual);
		textDataA.setColumns(10);
		textDataA.setBounds(607, 11, 67, 20);
		getContentPane().add(textDataA);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 403, 701, 35);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnLanar = new JButton("Lan\u00E7ar");
		btnLanar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lancar();
			}
		});
		btnLanar.setBounds(10, 0, 135, 32);
		panel.add(btnLanar);
		btnLanar.setIcon(new ImageIcon(MovNotaFiscal.class.getResource("/image/icLanc.png")));
		
		JButton btnAlterar = new JButton("Alterar/Ver");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alterar();
			}
		});
		btnAlterar.setBounds(148, 0, 135, 32);
		panel.add(btnAlterar);
		btnAlterar.setIcon(new ImageIcon(MovNotaFiscal.class.getResource("/image/icAlt.png")));
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();
			}
		});
		btnExcluir.setBounds(286, 0, 135, 32);
		panel.add(btnExcluir);
		btnExcluir.setIcon(new ImageIcon(MovNotaFiscal.class.getResource("/image/icExcluir.png")));
		
		JButton btnRelatrio = new JButton("Relat\u00F3rio");
		btnRelatrio.setBounds(424, 0, 135, 32);
		panel.add(btnRelatrio);
		btnRelatrio.setIcon(new ImageIcon(MovNotaFiscal.class.getResource("/image/icRel.png")));
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		btnFechar.setBounds(562, 0, 135, 32);
		panel.add(btnFechar);
		btnFechar.setIcon(new ImageIcon(MovNotaFiscal.class.getResource("/image/icSair.png")));
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTipo.setBounds(0, 36, 67, 21);
		getContentPane().add(lblTipo);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualizaTable();
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "ENTRADA", "SAÍDA"}));
		comboBox.setBounds(72, 36, 115, 21);
		getContentPane().add(comboBox);
		atualizaTable();
	}
	static NotaFiscal nF = null;
	public static NotaFiscal nF(){
		if(nF==null){
			nF=new NotaFiscal();
		}
		return nF;
	}
	public static String cargBusca(){
		String ret = "";
		String combo = comboBox.getSelectedItem().toString();
		String txt = textField.getText();
		String data = " and nf.data_em between '"+AN.dataPMySQL(textDataDe.getText())+"' and '"+AN.dataPMySQL(textDataA.getText())+"' ";
		if(!txt.equals("")){
			int cod = 0;
			try{cod = AN.stringPInt(txt);}catch(Exception e){}
			if(cod>0){
				txt = " and pj2.id_pessoa="+txt+" ";
			}else{
				txt = " and pj2.razao_social like '%"+txt+"%' ";
			}
			
		}
		if(combo.equals("ENTRADA")){
			combo = " and nf.tipo='ENTRADA' ";
		}else if(combo.equals("SAÍDA")){
			combo = " and nf.tipo='SAÍDA' ";
		}else{
			combo="";
		}
		ret = data+txt+combo;
//		Faturamento
//		Romaneio
//		Cliente
		
		
		return ret;
		
	}
	public static void atualizaTable() {
		dados();
		scrollPane.setViewportView(getJTable());
	}
	public static String[][] dados() {
		//{"Código","Faturamento", "Cliente", "Valor","Data"};
		
		
		//if(!text.equals("")){		//	
				String where = cargBusca();
				String sql1 = "SELECT count(nf.numero) as total FROM nota_fiscal nf "
						+ "left join pessoa_juridica pj on pj.id_pessoa=nf.cliente_id "
						+ "left join pessoa_juridica pj2 on pj2.id_pessoa=nf.comp_vend_id "
						+ "where nf.cliente_id="+codCli+where;
				String sql2 = "SELECT nf.numero, pj.id_pessoa, pj.razao_social, DATE_FORMAT(nf.data_em, '%d/%m/%Y') as datap, "
						+ "DATE_FORMAT(nf.data_ent, '%d/%m/%Y') as datae, "
						+ "if(nf.qtd_ent>0, nf.qtd_ent,nf.qtd_sai) as qtd, nf.tipo, "
						+ "nf.total FROM nota_fiscal nf "
						+ "left join pessoa_juridica pj on pj.id_pessoa=nf.cliente_id "
						+ "left join pessoa_juridica pj2 on pj2.id_pessoa=nf.comp_vend_id "
						+ "where nf.cliente_id="+codCli+where;		
						
				//System.out.println(sql2);
				String[][] ret = nF().buscarPassandoMatrizCad(sql1, sql2);
				if(ret != null){
					matriz = ret;
					lblNotasFiscais.setText("Notas Fiscais ("+ret.length+")");
					
				}else{
					lblNotasFiscais.setText("Nota Fiscal (0)");
					matriz = new String[1][7];
					matriz[0][0] = "";//
					matriz[0][1] = "Nenhuma Nota Fiscal Encontrada!";//
					matriz[0][2] = "";//
					matriz[0][3] = "";
					matriz[0][4] = "";	
					matriz[0][5] = "";
					matriz[0][6] = "";	
					
				}	
		
		return matriz;	
	}
	
	
	static String[][] matriz=null;
	public static JTable getJTable(){
		if (table == null) {
			matriz = new String[1][7];
			matriz[0][0] = "";//
			matriz[0][1] = "Nenhuma Nota Fiscal Encontrada!";//
			matriz[0][2] = "";//
			matriz[0][3] = "";
			matriz[0][4] = "";	
			matriz[0][5] = "";
			matriz[0][6] = "";	
			
		}
		String[] cab = {"Nota Fiscal","Produtor", "Emissão", "Entrega","Qtd","Tipo","Total"};
		DefaultTableModel modelo = new DefaultTableModel(matriz, cab);
		table = new JTable(modelo) {
			public boolean isCellEditable(int row, int column) {
				if (column >= 0) {
					return false;
				} else {
					return true;
				}
			}			
		};
		table.getTableHeader().setReorderingAllowed(false);
		//table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//Selecionar apenas uma linha
		table.setAutoCreateRowSorter(true);
		table.setAutoCreateColumnsFromModel(true);
		table.setSize(new Dimension(555, 80));
		table.setBackground(new Color(249, 255, 249));
		table.setFont(new Font("Dialog", Font.PLAIN, 13));
		table.setForeground(Color.BLACK);
		table.setAutoResizeMode(table.AUTO_RESIZE_OFF);
		table.setColumnSelectionAllowed(false);
		table.setGridColor(new Color(0, 153, 102));
		table.setRowHeight(20);
		table.setRowSelectionAllowed(true);
		table.setSelectionBackground(new Color(204, 255, 204));
		table.setShowGrid(true);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.setComponentOrientation(ComponentOrientation.UNKNOWN);
		//"Nota Fiscal","Produtor", "Emissão", "Entrega","Qtd","Tipo","Total"
		table.getColumnModel().getColumn(0).setPreferredWidth(68);//Nota
		table.getColumnModel().getColumn(1).setPreferredWidth(300);//Produtor
		table.getColumnModel().getColumn(2).setPreferredWidth(75);//Emissao
		table.getColumnModel().getColumn(3).setPreferredWidth(75);//Entrega
		table.getColumnModel().getColumn(4).setPreferredWidth(50);//Qtd
		table.getColumnModel().getColumn(5).setPreferredWidth(65);//tipo
		table.getColumnModel().getColumn(6).setPreferredWidth(90);//Total
		
		// --------------------------
		DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
		direita.setHorizontalAlignment(SwingConstants.RIGHT);
		DefaultTableCellRenderer  centro= new DefaultTableCellRenderer();
		centro.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(2).setCellRenderer(centro);
		table.getColumnModel().getColumn(4).setCellRenderer(direita);
		table.getColumnModel().getColumn(5).setCellRenderer(centro);
		table.getColumnModel().getColumn(6).setCellRenderer(direita);
		
		
		//
		table.addKeyListener(new java.awt.event.KeyAdapter() {   
			public void keyPressed(java.awt.event.KeyEvent e) {    
				if(e.getKeyCode() == KeyEvent.VK_F5){
				
				}else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
					table.removeRowSelectionInterval(0, table.getRowCount()-1);
				}else if ((e.getKeyCode() == KeyEvent.VK_DELETE)
						&& e.getModifiers() == KeyEvent.CTRL_MASK) {
					//Cancelar 
					
				}
				
				
			}   		
			public void keyReleased(java.awt.event.KeyEvent e) {
				linhaSel = table.getSelectedRow();
				colSel = table.getSelectedColumn();
				totalL = table.getRowCount();
				
			}
		});
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent e) {
				linhaSel = table.getSelectedRow();
				colSel = table.getSelectedColumn();
				totalL = table.getRowCount();
				
			}
			public void mouseReleased(java.awt.event.MouseEvent e) {
				linhaSel = table.getSelectedRow();
				colSel = table.getSelectedColumn();
				totalL = table.getRowCount();
				
			}
		});	
		return table;
	}
	
	
	static LancNota lancNota = null;
	
	public void lancar(){
		lancNota = new LancNota("Incluir",0);
		Inicio.addTela(lancNota);
		lancNota.setVisible(true);
		try{
			lancNota.setSelected(true);
		}catch(Exception e){}
		setBotoes(false);
	}
	public void alterar(){
		int nf = 0;
		try{nf = AN.stringPInt(table.getValueAt(linhaSel, 0).toString());}catch(Exception e){}
		if(nf>0){
			lancNota = new LancNota("Alterar",nf);
			Inicio.addTela(lancNota);
			lancNota.setVisible(true);
			try{
				lancNota.setSelected(true);
			}catch(Exception e){}
			setBotoes(false);
		}else{
			AN.jOptionPaneAlert("Selecione uma Nota Fiscal para Alterar/Ver!");
		}
		
		
	}
	public void fechar(){
		dispose();
		Inicio.limparTela(Inicio.movNotaFiscal);
		Inicio.movNotaFiscal=null;
		Inicio.setBotaoMovNotaFiscal(true);
	}
	public static void setBotoes(boolean a){
		panel.setVisible(a);
	}
	
	public void excluir(){
		int nf = 0;
		try{nf = AN.stringPInt(table.getValueAt(linhaSel, 0).toString());}catch(Exception e){}
		if(nf>0){
			if(AN.jOptionPaneQuestion("Deseja Realmente Excluir a Nota Fiscal: "+nf+"?")==0){
				if(AN.jOptionPaneQuestion("Isso afetará a Movimentação do Estoque, deseja Continuar ?")==0){
					boolean a = nF().excluirNotaFiscal(nf, codCli);
					if(a==true){
						atualizaTable();
						AN.jOptionPaneInformation("Nota Fiscal Excluída com Sucesso!");
					}
				}
			}
		}else{
			AN.jOptionPaneAlert("Selecione uma Nota Fiscal para Excluir!");
		}
		
		
	}
}
