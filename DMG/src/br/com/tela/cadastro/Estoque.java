package br.com.tela.cadastro;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import br.com.classes.Animais;
import br.com.util.AN;
import br.com.util.JTextData2;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Estoque extends JInternalFrame {
	private static JTextField textDataDe;
	private static JTextField textDataA;
	private static JTable table;
	public static int linhaSel = 0;
	public static int colSel = 0;
	public static int totalL = 0;
	static JComboBox comboBox;
	static JLabel lblMovimentaoDeEstoque;
	static JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */
	
	static JPanel panel;

	/**
	 * Create the frame.
	 */
	static int codCli = 0;
	public Estoque(int codCli) {
		this.codCli=codCli;
		getContentPane().setBackground(Color.WHITE);
		setTitle("Movimenta\u00E7\u00E3o de Estoque");
		setFrameIcon(new ImageIcon(Estoque.class.getResource("/image/icEstoq.png")));
		setIconifiable(true);
		setBounds(100, 100, 989, 459);
		getContentPane().setLayout(null);
		
		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProduto.setHorizontalAlignment(SwingConstants.TRAILING);
		lblProduto.setBounds(0, 11, 72, 21);
		getContentPane().add(lblProduto);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualizaTable();
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Todos", "0-3 meses", "3-8 meses", "8-12 meses", "12-24 meses", "24-36 meses", "Acima de 36 meses"}));
		comboBox.setBounds(75, 11, 475, 21);
		getContentPane().add(comboBox);
		
		JLabel lblPerodoDe = new JLabel("Per\u00EDodo de:");
		lblPerodoDe.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPerodoDe.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPerodoDe.setBounds(551, 11, 107, 21);
		getContentPane().add(lblPerodoDe);
		
		textDataDe = new JTextData2();
		textDataDe.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(!textDataDe.getText().equals("  /  /    ")){
					textDataDe.setText(AN.retData(textDataDe.getText()));
				}
			}
		});
		textDataDe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					textDataA.requestFocus();
				}
			}
		});
		textDataDe.setBounds(660, 11, 78, 20);
		getContentPane().add(textDataDe);
		textDataDe.setColumns(10);
		
		JLabel lblA = new JLabel("a");
		lblA.setHorizontalAlignment(SwingConstants.CENTER);
		lblA.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblA.setBounds(737, 11, 32, 21);
		getContentPane().add(lblA);
		
		textDataA = new JTextData2();
		textDataA.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(!textDataA.getText().equals("  /  /    ")){
					textDataA.setText(AN.retData(textDataA.getText()));
				}
			}
		});
		textDataA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					atualizaTable();
				}
			}
		});
		textDataA.setColumns(10);
		textDataA.setBounds(768, 11, 78, 20);
		getContentPane().add(textDataA);
		
		lblMovimentaoDeEstoque = new JLabel("Movimenta\u00E7\u00E3o de Estoque (0)");
		lblMovimentaoDeEstoque.setForeground(new Color(102, 0, 102));
		lblMovimentaoDeEstoque.setHorizontalAlignment(SwingConstants.CENTER);
		lblMovimentaoDeEstoque.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMovimentaoDeEstoque.setBounds(66, 57, 897, 21);
		getContentPane().add(lblMovimentaoDeEstoque);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(66, 77, 897, 267);
		getContentPane().add(scrollPane);
		
		
		scrollPane.setViewportView(getJTable());
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 369, 618, 35);
		getContentPane().add(panel);
		
		JButton btnMudarEra = new JButton("Mudar \u00C9ra");
		btnMudarEra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mudarDeEra();
			}
		});
		btnMudarEra.setIcon(new ImageIcon(Estoque.class.getResource("/image/icAlt.png")));
		btnMudarEra.setBounds(148, 0, 135, 32);
		panel.add(btnMudarEra);
		
		JButton btnRelatorio = new JButton("Relat\u00F3rio");
		btnRelatorio.setIcon(new ImageIcon(Estoque.class.getResource("/image/icRel.png")));
		btnRelatorio.setBounds(286, 0, 135, 32);
		panel.add(btnRelatorio);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		btnFechar.setIcon(new ImageIcon(Estoque.class.getResource("/image/icSair.png")));
		btnFechar.setBounds(424, 0, 135, 32);
		panel.add(btnFechar);
		
		JButton btnIncluir = new JButton("Lan\u00E7ar");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lancar();
			}
		});
		btnIncluir.setIcon(new ImageIcon(Estoque.class.getResource("/image/icLanc.png")));
		btnIncluir.setBounds(10, 0, 135, 32);
		panel.add(btnIncluir);
		
		JLabel lblTotais = new JLabel("Totais:");
		lblTotais.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTotais.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotais.setBounds(0, 344, 62, 21);
		getContentPane().add(lblTotais);
		
		text03M = new JTextField();
		text03M.setText("0");
		text03M.setHorizontalAlignment(SwingConstants.CENTER);
		text03M.setEditable(false);
		text03M.setBounds(66, 345, 74, 20);
		getContentPane().add(text03M);
		text03M.setColumns(10);
		
		text03F = new JTextField();
		text03F.setText("0");
		text03F.setHorizontalAlignment(SwingConstants.CENTER);
		text03F.setEditable(false);
		text03F.setColumns(10);
		text03F.setBounds(140, 345, 75, 20);
		getContentPane().add(text03F);
		
		text38M = new JTextField();
		text38M.setText("0");
		text38M.setHorizontalAlignment(SwingConstants.CENTER);
		text38M.setEditable(false);
		text38M.setColumns(10);
		text38M.setBounds(215, 345, 74, 20);
		getContentPane().add(text38M);
		
		text38F = new JTextField();
		text38F.setText("0");
		text38F.setHorizontalAlignment(SwingConstants.CENTER);
		text38F.setEditable(false);
		text38F.setColumns(10);
		text38F.setBounds(289, 345, 74, 20);
		getContentPane().add(text38F);
		
		text812M = new JTextField();
		text812M.setText("0");
		text812M.setHorizontalAlignment(SwingConstants.CENTER);
		text812M.setEditable(false);
		text812M.setColumns(10);
		text812M.setBounds(363, 345, 74, 20);
		getContentPane().add(text812M);
		
		text812F = new JTextField();
		text812F.setText("0");
		text812F.setHorizontalAlignment(SwingConstants.CENTER);
		text812F.setEditable(false);
		text812F.setColumns(10);
		text812F.setBounds(437, 345, 74, 20);
		getContentPane().add(text812F);
		
		text1224M = new JTextField();
		text1224M.setText("0");
		text1224M.setHorizontalAlignment(SwingConstants.CENTER);
		text1224M.setEditable(false);
		text1224M.setColumns(10);
		text1224M.setBounds(511, 345, 74, 20);
		getContentPane().add(text1224M);
		
		text1224F = new JTextField();
		text1224F.setText("0");
		text1224F.setHorizontalAlignment(SwingConstants.CENTER);
		text1224F.setEditable(false);
		text1224F.setColumns(10);
		text1224F.setBounds(585, 345, 74, 20);
		getContentPane().add(text1224F);
		
		text2436M = new JTextField();
		text2436M.setText("0");
		text2436M.setHorizontalAlignment(SwingConstants.CENTER);
		text2436M.setEditable(false);
		text2436M.setColumns(10);
		text2436M.setBounds(659, 345, 74, 20);
		getContentPane().add(text2436M);
		
		text2436F = new JTextField();
		text2436F.setText("0");
		text2436F.setHorizontalAlignment(SwingConstants.CENTER);
		text2436F.setEditable(false);
		text2436F.setColumns(10);
		text2436F.setBounds(733, 345, 74, 20);
		getContentPane().add(text2436F);
		
		text36M = new JTextField();
		text36M.setText("0");
		text36M.setHorizontalAlignment(SwingConstants.CENTER);
		text36M.setEditable(false);
		text36M.setColumns(10);
		text36M.setBounds(807, 345, 74, 20);
		getContentPane().add(text36M);
		
		text36F = new JTextField();
		text36F.setText("0");
		text36F.setHorizontalAlignment(SwingConstants.CENTER);
		text36F.setEditable(false);
		text36F.setColumns(10);
		text36F.setBounds(881, 345, 74, 20);
		getContentPane().add(text36F);
		
		JLabel lblSubtotal = new JLabel("Subtotal:");
		lblSubtotal.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSubtotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSubtotal.setBounds(660, 368, 152, 21);
		getContentPane().add(lblSubtotal);
		
		textTotal = new JTextField();
		textTotal.setHorizontalAlignment(SwingConstants.CENTER);
		textTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		textTotal.setText("0");
		textTotal.setForeground(new Color(255, 255, 0));
		textTotal.setBackground(new Color(0, 0, 128));
		textTotal.setEditable(false);
		textTotal.setColumns(10);
		textTotal.setBounds(817, 368, 138, 20);
		getContentPane().add(textTotal);
		atualizaTable();
	}
	public static void atualizaTable() {
		dados();
		scrollPane.setViewportView(getJTable());
	}
	public void fechar(){
		dispose();
		Inicio.limparTela(Inicio.estoque);
		Inicio.estoque=null;
		Inicio.setBotaoEstoque(true);
	}
	static MudarDeEra mudaDeEra = null;
	public void mudarDeEra(){
		mudaDeEra = new MudarDeEra();
		Inicio.addTela(mudaDeEra);
		mudaDeEra.setVisible(true);
		try{
			mudaDeEra.setSelected(true);
		}catch(Exception e){}		
		setBotoes(false);
	}
	//
	static LancarEstoque lancarEstoque =null;
	public void lancar(){
		lancarEstoque = new LancarEstoque();
		Inicio.addTela(lancarEstoque);
		lancarEstoque.setVisible(true);
		try{
			lancarEstoque.setSelected(true);
		}catch(Exception e){}		
		setBotoes(false);
	}
	static String[][] matriz=null;
	public static JTable getJTable(){
		if (table == null) {
			matriz = new String[1][12];
			matriz[0][0] = "";//
			matriz[0][1] = "";//
			matriz[0][2] = "";//
			matriz[0][3] = "";
			matriz[0][4] = "";	
			matriz[0][5] = "";
			matriz[0][6] = "";	
			matriz[0][7] = "";//
			matriz[0][8] = "";
			matriz[0][9] = "";	
			matriz[0][10] = "";
			matriz[0][11] = "";
			
		}
		String[] cab = {"0-3 (M)","0-3 (F)", "3-8 (M)", "3-8 (F)","8-12 (M)","8-12 (F)",
				"12-24 (M)","12-24 (F)","24-36 (M)","24-36 (F)","Acima 36 (M)","Acima 36 (F)"};
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
		table.getColumnModel().getColumn(0).setPreferredWidth(73);//Nota
		table.getColumnModel().getColumn(1).setPreferredWidth(73);//Produtor
		table.getColumnModel().getColumn(2).setPreferredWidth(73);//Emissao
		table.getColumnModel().getColumn(3).setPreferredWidth(73);//Entrega
		table.getColumnModel().getColumn(4).setPreferredWidth(73);//Qtd
		table.getColumnModel().getColumn(5).setPreferredWidth(73);//tipo
		table.getColumnModel().getColumn(6).setPreferredWidth(73);//Total
		table.getColumnModel().getColumn(7).setPreferredWidth(73);//Nota
		table.getColumnModel().getColumn(8).setPreferredWidth(73);//Produtor
		table.getColumnModel().getColumn(9).setPreferredWidth(73);//Emissao
		table.getColumnModel().getColumn(10).setPreferredWidth(73);//Entrega
		table.getColumnModel().getColumn(11).setPreferredWidth(73);//Qtd
		
		
		// --------------------------
		DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
		direita.setHorizontalAlignment(SwingConstants.RIGHT);
		DefaultTableCellRenderer  centro= new DefaultTableCellRenderer();
		centro.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centro);
		table.getColumnModel().getColumn(1).setCellRenderer(centro);
		table.getColumnModel().getColumn(2).setCellRenderer(centro);
		table.getColumnModel().getColumn(3).setCellRenderer(centro);
		table.getColumnModel().getColumn(4).setCellRenderer(centro);
		table.getColumnModel().getColumn(5).setCellRenderer(centro);
		table.getColumnModel().getColumn(6).setCellRenderer(centro);
		table.getColumnModel().getColumn(7).setCellRenderer(centro);
		table.getColumnModel().getColumn(8).setCellRenderer(centro);
		table.getColumnModel().getColumn(9).setCellRenderer(centro);
		table.getColumnModel().getColumn(10).setCellRenderer(centro);
		table.getColumnModel().getColumn(11).setCellRenderer(centro);
		
		
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
	public static String cargBusca(){
		String ret = "";
		String combo = comboBox.getSelectedItem().toString();
		String data = "";
		if(textDataDe.getText().equals("  /  /    ") || textDataA.getText().equals("  /  /    ")){
			data="";
		}else{
			data = " and data_mov between '"+AN.dataPMySQL(textDataDe.getText())+"' and '"+AN.dataPMySQL(textDataA.getText())+"' ";
		}

		
		if(!combo.equals("Todos")){
			if(combo.equals("0-3 meses")){
				combo=" and zero_a_tres_m>0 or zero_a_tres_f>0 ";
			}else if(combo.equals("3-8 meses")){
				combo=" and tres_a_oito_m>0 or tres_a_oito_f>0 ";
			}else if(combo.equals("8-12 meses")){
				combo=" and oito_a_doze_m>0 or oito_a_doze_f>0 ";
			}else if(combo.equals("12-24 meses")){
				combo=" and doze_a_vinte_q_m>0 or doze_a_vinte_q_f>0 ";
			}else if(combo.equals("24-36 meses")){
				combo=" and vinte_q_a_trinta_s_m>0 or vinte_q_a_trinta_s_f>0 ";
			}else if(combo.equals("Acima de 36 meses")){
				combo=" and acima_trinta_s_m>0 or acima_trinta_s_f>0 ";
			}						
		}else{
			combo="";
		}
		ret = data+combo;
//		
		
		return ret;
		
	}
	static Animais animais = null;
	private static JTextField text03M;
	private static JTextField text03F;
	private static JTextField text38M;
	private static JTextField text38F;
	private static JTextField text812M;
	private static JTextField text812F;
	private static JTextField text1224M;
	private static JTextField text1224F;
	private static JTextField text2436M;
	private static JTextField text2436F;
	private static JTextField text36M;
	private static JTextField text36F;
	private static JTextField textTotal;
	public static Animais a(){
		if(animais==null){
			animais = new Animais();
		}
		return animais;
	}
	public static String[][] dados() {
		//{"Código","Faturamento", "Cliente", "Valor","Data"};
		
		
		//if(!text.equals("")){		//	
				String where = cargBusca();
				String sql1 = "SELECT count(cliente_id) as total FROM animais where cliente_id="+codCli+where;
				String sql2 = "SELECT * FROM animais where cliente_id="+codCli+where;		
						
				//System.out.println(sql2);
				String[][] ret = a().buscarPassandoMatrizCad(sql1, sql2);
				if(ret != null){
					matriz = ret;
					lblMovimentaoDeEstoque.setText("Movimenta\u00E7\u00E3o de Estoque ("+ret.length+")");
					String sqlTotais = "select sum(zero_a_tres_m) as zero_a_tres_m, "
							+ "(select sum(zero_a_tres_f) from animais where cliente_id="+codCli+where+" ) as zero_a_tres_f, "
							+ "(select sum(tres_a_oito_m) from animais where cliente_id="+codCli+where+" ) as tres_a_oito_m, "
							+ "(select sum(tres_a_oito_f) from animais where cliente_id="+codCli+where+" ) as tres_a_oito_f, "
							+ "(select sum(oito_a_doze_m) from animais where cliente_id="+codCli+where+" ) as oito_a_doze_m, "
							+ "(select sum(oito_a_doze_f) from animais where cliente_id="+codCli+where+" ) as oito_a_doze_f, "
							+ "(select sum(doze_a_vinte_q_m) from animais where cliente_id="+codCli+where+" ) as doze_a_vinte_q_m, "
							+ "(select sum(doze_a_vinte_q_f) from animais where cliente_id="+codCli+where+" ) as doze_a_vinte_q_f, "
							+ "(select sum(vinte_q_a_trinta_s_m) from animais where cliente_id="+codCli+where+" ) as vinte_q_a_trinta_s_m, "
							+ "(select sum(vinte_q_a_trinta_s_f) from animais where cliente_id="+codCli+where+" ) as vinte_q_a_trinta_s_f, "
							+ "(select sum(acima_trinta_s_m) from animais where cliente_id="+codCli+where+" ) as acima_trinta_s_m, "
							+ "(select sum(acima_trinta_s_f) from animais where cliente_id="+codCli+where+" ) as acima_trinta_s_f "
							+ "from animais where cliente_id="+codCli+where;
					String[] totais = a().buscarTotais(sqlTotais);
					text03M.setText(totais[0]);
					text03F.setText(totais[1]);
					text38M.setText(totais[2]);
					text38F.setText(totais[3]);
					text812M.setText(totais[4]);
					text812F.setText(totais[5]);
					text1224M.setText(totais[6]);
					text1224F.setText(totais[7]);
					text2436M.setText(totais[8]);
					text2436F.setText(totais[9]);
					text36M.setText(totais[10]);
					text36F.setText(totais[11]);
					textTotal.setText(String.valueOf(AN.stringPInt(totais[0])+
							AN.stringPInt(totais[1])+
							AN.stringPInt(totais[2])+
							AN.stringPInt(totais[3])+
							AN.stringPInt(totais[4])+
							AN.stringPInt(totais[5])+
							AN.stringPInt(totais[6])+
							AN.stringPInt(totais[7])+
							AN.stringPInt(totais[8])+
							AN.stringPInt(totais[9])+
							AN.stringPInt(totais[10])+
							AN.stringPInt(totais[11])));
					
				}else{
					lblMovimentaoDeEstoque.setText("Movimenta\u00E7\u00E3o de Estoque (0)");
					matriz = new String[1][12];
					matriz[0][0] = "";//
					matriz[0][1] = "";//
					matriz[0][2] = "";//
					matriz[0][3] = "";
					matriz[0][4] = "";	
					matriz[0][5] = "";
					matriz[0][6] = "";	
					matriz[0][7] = "";//
					matriz[0][8] = "";
					matriz[0][9] = "";	
					matriz[0][10] = "";
					matriz[0][11] = "";
					text03M.setText("0");
					text03F.setText("0");
					text38M.setText("0");
					text38F.setText("0");
					text812M.setText("0");
					text812F.setText("0");
					text1224M.setText("0");
					text1224F.setText("0");
					text2436M.setText("0");
					text2436F.setText("0");
					text36M.setText("0");
					text36F.setText("0");
					textTotal.setText("0");
				}	
		
		return matriz;	
	}
	
	//
	public static void setBotoes(boolean a){
		panel.setVisible(a);
	}
}
