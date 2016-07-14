package br.com.tela.cadastro;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import br.com.classes.Cliente;
import br.com.classes.NotaFiscal;
import br.com.util.AN;
import br.com.util.FixedLengthDocument;
import br.com.util.JMoneyField;
import br.com.util.JTextData;
import br.com.util.JTextFieldFocu;
import br.com.util.SomenteNumeros;

import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class LancNota extends JInternalFrame {
	static JTextField textVend;
	static JTextField textComp;
	private JTextField textNF;
	private JTextField textNatOp;
	private JTextField textEm;
	private JTextField textEnt;
	private JTextField textUN;
	private JTextField textVlrUnit;
	private JTextField textQtd;
	private JTextField textTotal;
	
	//////-------------------
	String dataAtual = AN.dataAtualText();
	/**
	 * Launch the application.
	 */
	DefaultTableModel modelo = new DefaultTableModel();
	int linhaSel = -1;
	
	JComboBox comboProd;
	JComboBox comboSexo;
	/**
	 * Create the frame.
	 */
	JButton btnInc; 
	String op = "";
	int codCli = 0;
	int nf = 0;
	
	public LancNota(String op, int nf) {
		this.nf=nf;
		this.op=op;
		codCli = AN.stringPInt(AN.retAteTraco(Inicio.labelCliente.getText()));
		String title = op.equals("Incluir")?"INCLUIR - Nota Fiscal":op.equals("Alterar")?"ALTERAR - Nota Fiscal":"VISUALIZAR - Nota Fiscal";
		setTitle(title);
		setFrameIcon(new ImageIcon(LancNota.class.getResource("/image/icRel.png")));
		getContentPane().setBackground(Color.WHITE);
		setIconifiable(true);
		setBounds(100, 100, 596, 433);
		getContentPane().setLayout(null);
		
		JLabel lblVendedor = new JLabel("Vendedor:");
		lblVendedor.setHorizontalAlignment(SwingConstants.TRAILING);
		lblVendedor.setBounds(10, 58, 74, 20);
		getContentPane().add(lblVendedor);
		
		textVend = new JTextFieldFocu();
		textVend.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(comboTipo.getSelectedItem().toString().equals("ENTRADA")){
					tentaBuscaVend();
				}
			}
		});
		textVend.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					textComp.requestFocus();
				}else if(arg0.getKeyCode() == KeyEvent.VK_F9){
					if(comboTipo.getSelectedItem().toString().equals("ENTRADA")){
						f9Vendedor("");
					}
				}
			}
		});
		textVend.setColumns(10);
		textVend.setBounds(85, 58, 415, 20);
		getContentPane().add(textVend);
		
		JLabel label_1 = new JLabel("(F9)");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.GRAY);
		label_1.setBounds(500, 58, 34, 20);
		getContentPane().add(label_1);
		
		JLabel lblComprador = new JLabel("Comprador:");
		lblComprador.setHorizontalAlignment(SwingConstants.TRAILING);
		lblComprador.setBounds(10, 81, 74, 20);
		getContentPane().add(lblComprador);
		
		textComp = new JTextFieldFocu();
		textComp.setText(Inicio.labelCliente.getText());
		textComp.setEditable(false);
		textComp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					comboProd.requestFocus();
				}else if(arg0.getKeyCode() == KeyEvent.VK_F9){
					if(comboTipo.getSelectedItem().toString().equals("SAÍDA")){
						f9Comprador("");
					}
				}
			}
		});
		textComp.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(comboTipo.getSelectedItem().toString().equals("SAÍDA")){
					tentaBuscaComp();
				}
			}
		});
		textComp.setColumns(10);
		textComp.setBounds(85, 81, 415, 20);
		getContentPane().add(textComp);
		
		JLabel label_2 = new JLabel("(F9)");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.GRAY);
		label_2.setBounds(500, 81, 34, 20);
		getContentPane().add(label_2);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Produto(s) da Nota", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 205)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 106, 567, 221);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setHorizontalAlignment(SwingConstants.TRAILING);
		lblProduto.setBounds(10, 25, 74, 20);
		panel.add(lblProduto);
		
		comboProd = new JComboBox();
		comboProd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					textQtd.requestFocus();
				}
			}
		});
		comboProd.setModel(new DefaultComboBoxModel(new String[] {"0-3 meses", "3-8 meses", "8-12 meses", "12-24 meses", "24-36 meses", "Acima de 36 meses"}));
		comboProd.setBounds(85, 25, 302, 20);
		panel.add(comboProd);
		
		JLabel lblUn = new JLabel("UN:");
		lblUn.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUn.setBounds(396, 25, 44, 20);
		panel.add(lblUn);
		
		textUN = new JTextField();
		textUN.setEditable(false);
		textUN.setText("cb\u00E7");
		textUN.setColumns(10);
		textUN.setBounds(443, 25, 68, 20);
		panel.add(textUN);
		
		JLabel lblVlrUnitrio = new JLabel("Vlr.: Unit\u00E1rio:");
		lblVlrUnitrio.setHorizontalAlignment(SwingConstants.TRAILING);
		lblVlrUnitrio.setBounds(133, 50, 83, 20);
		panel.add(lblVlrUnitrio);
		
		textVlrUnit = new JMoneyField();
		textVlrUnit.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				total();
			}
		});
		textVlrUnit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					comboSexo.requestFocus();
				}
			}
		});
		textVlrUnit.setHorizontalAlignment(SwingConstants.TRAILING);
		textVlrUnit.setText("0");
		textVlrUnit.setColumns(10);
		textVlrUnit.setBounds(218, 50, 68, 20);
		panel.add(textVlrUnit);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setHorizontalAlignment(SwingConstants.TRAILING);
		lblQuantidade.setBounds(10, 50, 74, 20);
		panel.add(lblQuantidade);
		
		textQtd = new JTextFieldFocu();
		textQtd.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				total();
			}
		});
		textQtd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					textVlrUnit.requestFocus();
				}
			}
		});
		textQtd.setHorizontalAlignment(SwingConstants.TRAILING);
		textQtd.setDocument(new SomenteNumeros());
		textQtd.setText("0");
		textQtd.setColumns(10);
		textQtd.setBounds(85, 50, 47, 20);
		panel.add(textQtd);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTotal.setBounds(396, 50, 44, 20);
		panel.add(lblTotal);
		
		textTotal = new JTextField();
		textTotal.setBackground(Color.BLUE);
		textTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		textTotal.setForeground(Color.YELLOW);
		textTotal.setText("0,00");
		textTotal.setHorizontalAlignment(SwingConstants.TRAILING);
		textTotal.setEditable(false);
		textTotal.setColumns(10);
		textTotal.setBounds(443, 50, 68, 20);
		panel.add(textTotal);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSexo.setBounds(278, 50, 60, 20);
		panel.add(lblSexo);
		
		comboSexo = new JComboBox();
		comboSexo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					btnInc.requestFocus();
				}
			}
		});
		comboSexo.setModel(new DefaultComboBoxModel(new String[] {"M", "F"}));
		comboSexo.setBounds(342, 50, 45, 20);
		panel.add(comboSexo);
		
		btnInc = new JButton("Incluir");
		btnInc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addProd();
			}
		});
		btnInc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					addProd();
				}
			}
		});
		btnInc.setToolTipText("Incluir Item na Nota");
		btnInc.setIcon(new ImageIcon(LancNota.class.getResource("/image/icMaisPQO.png")));
		btnInc.setBounds(85, 73, 100, 23);
		panel.add(btnInc);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alterarProd();
			}
		});
		btnAlterar.setToolTipText("Alterar Item da Nota");
		btnAlterar.setIcon(new ImageIcon(LancNota.class.getResource("/image/icAlt.png")));
		btnAlterar.setBounds(186, 73, 100, 23);
		panel.add(btnAlterar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				excluirProd();
			}
		});
		btnExcluir.setToolTipText("Excluir Item da Nota");
		btnExcluir.setIcon(new ImageIcon(LancNota.class.getResource("/image/lixoPQNO.png")));
		btnExcluir.setBounds(287, 73, 100, 23);
		panel.add(btnExcluir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 98, 547, 118);
		panel.add(scrollPane);
		
		
		scrollPane.setViewportView(getJTable());
		
		JLabel lblNaturezaDeOp = new JLabel("N\u00BA Nota Fiscal:");
		lblNaturezaDeOp.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNaturezaDeOp.setBounds(0, 11, 84, 20);
		getContentPane().add(lblNaturezaDeOp);
		
		textNF = new JTextFieldFocu();
		textNF.setDocument(new SomenteNumeros());
		textNF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				
			}
		});
		textNF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					textNatOp.requestFocus();
				}
			}
		});
		textNF.setColumns(10);
		textNF.setBounds(85, 11, 67, 20);
		getContentPane().add(textNF);
		
		JLabel lblNaturezaDeOperao = new JLabel("Natureza de Opera\u00E7\u00E3o:");
		lblNaturezaDeOperao.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNaturezaDeOperao.setBounds(152, 11, 139, 20);
		getContentPane().add(lblNaturezaDeOperao);
		
		textNatOp = new JTextFieldFocu();
		textNatOp.setDocument(new FixedLengthDocument(45));
		textNatOp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					textEm.requestFocus();
				}
			}
		});
		textNatOp.setColumns(10);
		textNatOp.setBounds(293, 11, 207, 20);
		getContentPane().add(textNatOp);
		
		JLabel lblEmisso = new JLabel("Emiss\u00E3o:");
		lblEmisso.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEmisso.setBounds(0, 35, 84, 20);
		getContentPane().add(lblEmisso);
		
		textEm = new JTextData();
		textEm.setText(dataAtual);
		textEm.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					textEnt.requestFocus();
				}
			}
		});
		textEm.setColumns(10);
		textEm.setBounds(85, 35, 67, 20);
		getContentPane().add(textEm);
		
		JLabel lblEntrega = new JLabel("Entrega:");
		lblEntrega.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEntrega.setBounds(152, 35, 74, 20);
		getContentPane().add(lblEntrega);
		
		textEnt = new JTextData();
		textEnt.setText(dataAtual);
		textEnt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					comboTipo.requestFocus();
				}
			}
		});
		textEnt.setColumns(10);
		textEnt.setBounds(227, 35, 67, 20);
		getContentPane().add(textEnt);
		
		btnIncluir = new JButton("Gravar");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gravar();
			}
		});
		btnIncluir.setIcon(new ImageIcon(LancNota.class.getResource("/image/icSalvar2.png")));
		btnIncluir.setBounds(175, 363, 115, 29);
		getContentPane().add(btnIncluir);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		btnFechar.setIcon(new ImageIcon(LancNota.class.getResource("/image/icSair.png")));
		btnFechar.setBounds(294, 363, 115, 29);
		getContentPane().add(btnFechar);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTipo.setBounds(293, 35, 57, 20);
		getContentPane().add(lblTipo);
		
		comboTipo = new JComboBox();
		comboTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tipo();
			}
		});
		comboTipo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					textVend.requestFocus();
				}
			}
		});
		comboTipo.setModel(new DefaultComboBoxModel(new String[] {"ENTRADA", "SA\u00CDDA"}));
		comboTipo.setBounds(352, 35, 148, 20);
		getContentPane().add(comboTipo);
		
		textSubTotal = new JTextField();
		textSubTotal.setText("0,00");
		textSubTotal.setHorizontalAlignment(SwingConstants.TRAILING);
		textSubTotal.setForeground(Color.YELLOW);
		textSubTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		textSubTotal.setEditable(false);
		textSubTotal.setColumns(10);
		textSubTotal.setBackground(Color.BLUE);
		textSubTotal.setBounds(466, 328, 102, 20);
		getContentPane().add(textSubTotal);
		
		JLabel lblTotalDaNota = new JLabel("Total da Nota Fiscal:");
		lblTotalDaNota.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTotalDaNota.setBounds(326, 328, 139, 20);
		getContentPane().add(lblTotalDaNota);

	}
	private JTable getJTable() {
		if (table == null) {
			modelo.addColumn("Produto");
			modelo.addColumn("UN");
			modelo.addColumn("Qtd");
			modelo.addColumn("Vlr. Unit.");
			modelo.addColumn("Sexo");
			modelo.addColumn("Total");
			table = new JTable(modelo) {
				public boolean isCellEditable(int row, int column) {
					if (column >= 0) {
						return false;
					} else {
						return true;
					}
				}
			};
			table.setRowHeight(17);
			table.setFont(new Font("Dialog", Font.PLAIN, 13));
			table.setColumnSelectionAllowed(false);
			table.setRowSelectionAllowed(true);
			table.setShowGrid(true);
			table.setShowHorizontalLines(true);
			table.setShowVerticalLines(true);
			table.getColumnModel().getColumn(0).setPreferredWidth(250);// produto
			table.getColumnModel().getColumn(1).setPreferredWidth(35);// UN
			table.getColumnModel().getColumn(2).setPreferredWidth(55);// QTD
			table.getColumnModel().getColumn(3).setPreferredWidth(70);// Vlr. Unitário
			table.getColumnModel().getColumn(4).setPreferredWidth(33);// Sexo
			table.getColumnModel().getColumn(5).setPreferredWidth(80);// Total

			// Qtd linhas a selecionar
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			// Fim qtd de linhas a selecionar
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.setSelectionBackground(new Color(255, 204, 153));
			table.setSelectionForeground(Color.black);
			table.setGridColor(new Color(153, 153, 153));
			table.setVisible(true);
			table.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					linhaSel = table.getSelectedRow();
					preencher();
				}
			});
			table.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent e) {
					linhaSel = table.getSelectedRow();
					preencher();
				}
			});
		}
		return table;
	}
	public void preencher(){
		String prod = table.getValueAt(linhaSel, 0).toString();
		String un = table.getValueAt(linhaSel, 1).toString();
		String qtd = table.getValueAt(linhaSel, 2).toString();
		String vlrUnit = table.getValueAt(linhaSel, 3).toString();
		String sexo = table.getValueAt(linhaSel, 4).toString();
		String total = table.getValueAt(linhaSel, 5).toString();
		comboProd.setSelectedItem(prod);
		textUN.setText(un);
		textQtd.setText(qtd);
		textVlrUnit.setText(vlrUnit);
		comboSexo.setSelectedItem(sexo);
		textTotal.setText(total);
		
	}
	JComboBox comboTipo;
	JButton btnIncluir ;
	public void tipo(){
		String tp = comboTipo.getSelectedItem().toString();
		if(tp.equals("ENTRADA")){
			textVend.setEditable(true);
			textVend.setText("");
			textComp.setText(Inicio.labelCliente.getText());
			textComp.setEditable(false);
		}else{
			textComp.setEditable(true);
			textComp.setText("");
			textVend.setText(Inicio.labelCliente.getText());
			textVend.setEditable(false);
		}
	}
	public void fechar(){
		dispose();
		try{
			Inicio.limparTela(MovNotaFiscal.lancNota);
			MovNotaFiscal.lancNota=null;
			Inicio.movNotaFiscal.setSelected(true);
			MovNotaFiscal.setBotoes(true);
			limparCampos();
		}catch(Exception e){}
		
	}
	public void total(){
		int qtd = AN.stringPInt(textQtd.getText());
		double vlrUnit = AN.stringPDouble(textVlrUnit.getText());
		textTotal.setText(AN.doublePStringRS(qtd*vlrUnit));
		
	}
	NotaFiscal nF = null;
	public NotaFiscal nF(){
		if(nF==null){
			nF=new NotaFiscal();
		}
		return nF;
	}
	public boolean nfJaUsada(){
		boolean ret = false;
		String tp = comboTipo.getSelectedItem().toString();
		int codCli = 0;
		int codCompVend = 0;
		boolean jaAdd = false;//Metodo confere no Banco
		if(tp.equals("ENTRADA")){
			codCli = AN.stringPInt(AN.retAteTraco(textVend.getText()));
			codCompVend = AN.stringPInt(AN.retAteTraco(textComp.getText()));
			jaAdd = nF().numeroNotaJaAdd(AN.stringPInt(textNF.getText()), codCompVend,codCli);
		}else{
			codCli = AN.stringPInt(AN.retAteTraco(textComp.getText()));
			codCompVend = AN.stringPInt(AN.retAteTraco(textVend.getText()));
			jaAdd = nF().numeroNotaJaAdd(AN.stringPInt(textNF.getText()), codCli,codCompVend);
		}
		if(jaAdd == true){
			ret = true;
		}		
		return ret;
		
		
		
		
		
	}
	Cliente c = null;
	public Cliente c(){
		if(c==null){
			c=new Cliente();
		}
		return c;
	}
	
	public void tentaBuscaVend(){
		String txt = textVend.getText();

		if (!txt.equals("")) {			
			String ret = c().buscarTentaBuscaCod(txt);
			if (!ret.equals("-%-")) {
				String comp = textComp.getText();
				if(!ret.equals(comp)){
					textVend.setText(ret);	
				}else{
					textVend.setText("");
				}
				
			} else {
				f9Vendedor(txt);
			}
		}
	}
	public void tentaBuscaComp(){
		String txt = textComp.getText();

		if (!txt.equals("")) {			
			String ret = c().buscarTentaBuscaCod(txt);
			if (!ret.equals("-%-")) {
				String vend = textVend.getText();
				if(!ret.equals(vend)){
					textComp.setText(ret);	
				}else{
					textComp.setText("");
				}
			} else {
				f9Comprador(txt);
			}
		}
	}
	F9Cliente f9Cliente = null;
	private JTable table;
	private JTextField textSubTotal;
	
	public void f9Vendedor(String txt) {
		try {
			f9Cliente = new F9Cliente(null, txt, "LancNotaVend", false);
			f9Cliente.setVisible(true);
			f9Cliente.setModal(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//
	public void f9Comprador(String txt) {
		try {
			f9Cliente = new F9Cliente(null, txt, "LancNotaComp", false);
			f9Cliente.setVisible(true);
			f9Cliente.setModal(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void addProd(){
		String prod = comboProd.getSelectedItem().toString();
		String un = textUN.getText();
		String qtd = textQtd.getText();
		String vlrUnit = textVlrUnit.getText();
		String sexo = comboSexo.getSelectedItem().toString();
		String total = textTotal.getText();
		boolean jaAdd = prodJaAdd(prod, sexo);
		if(jaAdd==false){
			double tot = AN.stringPDouble(total);
			if(tot>0){
				int cont = AN.jOptionPaneQuestion("Confirma a Inclusão do Produto: "+prod+"?");
				if(cont==0){
					modelo.addRow(new Object[] {prod, un, qtd, vlrUnit, sexo, total});
					totalNota();
					textQtd.setText("0");
					textVlrUnit.setText("0");
					textTotal.setText("0,00");
					comboProd.requestFocus();
				}
			}else{
				AN.jOptionPaneError("Verifique o(s) Valor(es)!");
			}
		}else{
			AN.jOptionPaneAlert("Produto já Adicionado!");
		}
		
		
		
		
		
		
	}
	////
	public void excluirProd(){
		if(linhaSel >=0){
			String prod = table.getValueAt(linhaSel, 0).toString();
			int x = AN.jOptionPaneQuestion("Confirma a Exclusão do Produto: "+prod+" ?");
			if(x==0){
				modelo.removeRow(linhaSel);
				totalNota();
			}
		}else{
			AN.jOptionPaneInformation("Selecione um Produto para Excluir!");
		}
	}
	public void alterarProd(){
		if(linhaSel >=0){
			String prodx = table.getValueAt(linhaSel, 0).toString();
			int x = AN.jOptionPaneQuestion("Confirma a Alteração do Produto: "+prodx+" ?");
			if(x==0){
				String prod = comboProd.getSelectedItem().toString();
				String un = textUN.getText();
				String qtd = textQtd.getText();
				String vlrUnit = textVlrUnit.getText();
				String sexo = comboSexo.getSelectedItem().toString();
				String total = textTotal.getText();
				boolean jaAdd = prodJaAddAlt(prod);
				if(jaAdd==false){
					table.setValueAt(prod, linhaSel, 0);
					table.setValueAt(un, linhaSel, 1);
					table.setValueAt(qtd, linhaSel, 2);
					table.setValueAt(vlrUnit, linhaSel, 3);
					table.setValueAt(sexo, linhaSel, 4);
					table.setValueAt(total, linhaSel, 5);
					totalNota();
				}
				
			}
		}else{
			AN.jOptionPaneInformation("Selecione um Produto para Alterar!");
		}
	}
	public void totalNota(){
		double total = 0;
		for(int i=0; i<table.getRowCount(); i++){
			total += AN.stringPDouble(table.getValueAt(i, 5).toString());
		}
		textSubTotal.setText(AN.doublePStringRS(total));
	}
	public boolean prodJaAdd(String prod, String sexo){
		boolean ret = false;
		for(int i=0; i<table.getRowCount(); i++){
			String prodFor = table.getValueAt(i, 0).toString();
			String sexoTab = table.getValueAt(i, 4).toString();
			if(prod.equals(prodFor) && sexo.equals(sexoTab)){
				ret=true;
				break;
			}			
		}		
		return ret;
	}
	public boolean prodJaAddAlt(String prod){
		boolean ret = false;
		
		textComp.getText();
		textVend.getText();
		for(int i=0; i<table.getRowCount(); i++){
			if(i==linhaSel){
				continue;
			}
			String prodFor = table.getValueAt(i, 0).toString();
			if(prod.equals(prodFor)){
				ret=true;
				break;
			}			
		}		
		return ret;
	}
	public void gravar(){
		int totProd = table.getRowCount();
		String[][] prods = null;
		double totalQtd = 0;
		if(totProd>0){
			prods = new String[totProd][6];
			for(int i=0; i<totProd; i++){
				prods[i][0] = table.getValueAt(i, 0).toString();
				prods[i][1] = table.getValueAt(i, 1).toString();
				prods[i][2] = table.getValueAt(i, 2).toString();
				totalQtd += AN.stringPDouble(prods[i][2]);
				prods[i][3] = table.getValueAt(i, 3).toString();
				prods[i][4] = table.getValueAt(i, 4).toString();
				prods[i][5] = table.getValueAt(i, 5).toString();				
			}
		}
		double totalNota = AN.stringPDouble(textSubTotal.getText());
		
		boolean nfJaUsou = true;
		int codComVend = 0;
		
		int nf = AN.stringPInt(textNF.getText());
		if(textVend.isEditable()){
			codComVend = AN.stringPInt(AN.retAteTraco(textVend.getText()));
			
		}else{
			codComVend = AN.stringPInt(AN.retAteTraco(textComp.getText()));
		}
		if(nf>0){
			if(op.equals("Incluir")){
				nfJaUsou = nF().numeroNotaJaAdd(nf, codCli, codComVend);
			}else{
				if(this.nf==nf){
					nfJaUsou = false;
				}else{
					nfJaUsou = nF().numeroNotaJaAdd(nf, codCli, codComVend);
				}
			}
			if(totalNota>0){
				if(nfJaUsou==false){
					if(prods !=null){
						String natOp = textNatOp.getText();
						String dataEm = AN.dataPMySQL(textEm.getText());
						String dataEn = AN.dataPMySQL(textEm.getText());
						String tipo = comboTipo.getSelectedItem().toString();
						//
						nF().setCodCli(codCli);
						nF().setCodCompVend(codComVend);
						nF().setNumero(nf); 
						if(tipo.equals("ENTRADA")){
							nF().setQtdEnt(totalQtd);
							nF().setQtdSai(0);
						}else{
							nF().setQtdEnt(0);
							nF().setQtdSai(totalQtd);
						}
						nF().setTotal(totalNota);
						nF().setDataEm(dataEm);
						nF().setDataEnt(dataEn);
						nF().setTipo(tipo);
						nF().setNatOp(natOp);
						nF().setProds(prods);
						boolean inc = nF().incluir();
						if(inc==true){
							AN.jOptionPaneInformation("Nota Fiscal Inclusa com Sucesso!");
							if(AN.jOptionPaneQuestion("Deseja Continuar Incluindo?")==0){
								limparCampos();
								textNF.requestFocus();
							}else{
								fechar();
							}
						}
					}else{
						AN.jOptionPaneError("Informe pelo menos 1 item da Nota Fiscal!");
					}	
				}else{
					AN.jOptionPaneError("Número de Nota Fiscal já Informada!");
				}							
			}else{
				AN.jOptionPaneError("O Total da Nota não ser Igual a 0!");
			}
		}else{
			AN.jOptionPaneError("Informe um Número de Nota Fiscal!");
		}		
	}
	public void limparCampos(){
		textNF.setText("");
		textNatOp.setText("");
		textEm.setText(dataAtual);
		textEnt.setText(dataAtual);
		comboTipo.setSelectedItem("ENTRADA");
		tipo();
		textQtd.setText("");
		textVlrUnit.setText("0");
		comboSexo.setSelectedItem("M");
		textTotal.setText("0,00");
		textSubTotal.setText("0,00");
		limparTable();
		
	}
	public void limparTable() {
		try {
			table.removeEditor();
			int modelos = table.getRowCount();
			int i = 0;
			if (modelos > 0) {
				do {
					modelo.removeRow(i);
				} while (i < modelos && table.getRowCount() != 0);
				i++;
			}
			
			
		} catch (Exception e) {
		}
	}
}
