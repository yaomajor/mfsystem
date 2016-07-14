package br.com.tela.cadastro;

import javax.swing.JPanel;                 
import java.awt.Frame;

import javax.swing.JDialog;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.InputMap;

import javax.swing.JButton;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.lang.StringUtils;

import br.com.classes.Cliente;
import br.com.entity.ProdutorRural;
import br.com.util.AN;

import javax.swing.JTextField;



import java.awt.event.KeyAdapter;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
 


public class F9Cliente extends JDialog {
	int[] titsSel = null;  //  @jve:decl-index=0:
	static String tituloSel = "";
	int totalTit = -1;
	String titSel = "";  //  @jve:decl-index=0:
	int linhaTit = -1;
	int colTit = -1;
	//
	static String codFor = "";  //  @jve:decl-index=0:
	static String descFor = "";
	
	static DefaultTableModel modelo = new DefaultTableModel();
	
	//
	
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JButton jButton1 = null;
	private JScrollPane jScrollPane = null;
	private static JTable jTable = null;
	private JLabel jLabel41 = null;
	private static JTextField jTextField = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JButton jButton = null;
	JButton btnGravar = null;
	private JButton btnCarregar;
	
	/**
	 * @param owner
	 * @param tela 
	 */
	String tela = "";
	static boolean sohCliente = false;
	public F9Cliente(Frame owner, String desc, String tela, boolean sohCliente) {
		super(owner);
		this.sohCliente = sohCliente;
		initialize();
		if(!desc.equals("")){
			jTextField.setText(desc);
			atualizaTable();
		}
		this.tela = tela;
		
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(523, 323);
		this.setTitle("Cadastro - CLIENTES");
		this.setModal(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		//this.setUndecorated(true); // RETIRA AS BORDAS DO FRAME
		this.setContentPane(getJContentPane());
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				limparCampos();
			}
		});
		atualizaTable();
	}
	public void limparCampos(){
		codFor = "";
		descFor = "";		
		jTextField.setText("");
		for (int i = (jTable.getRowCount() - 1); i >= 0; --i) {
		     modelo.removeRow(i);//remove todas as linhas selecionadas
		}
		modelo.setRowCount(0);
	}
	
	
	
	
		/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel41 = new JLabel();
			jLabel41.setBounds(new Rectangle(3, 28, 78, 21));
			jLabel41.setHorizontalAlignment(SwingConstants.TRAILING);
			jLabel41.setText("Nome:");
			jLabel41.setPreferredSize(new Dimension(174, 18));
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(3, 1, 511, 28));
			jLabel.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
			jLabel.setForeground(new Color(51, 0, 102));
			jLabel.setText("Consulta de Cliente");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setBackground(Color.white);
			jContentPane.add(jLabel, null);
			jContentPane.add(getJButton1(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(jLabel41, null);
			jContentPane.add(getJTextField(), null);
			
			btnCarregar = new JButton();
			btnCarregar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					carregar();				
				}
			});
			btnCarregar.setIcon(new ImageIcon(F9Cliente.class.getResource("/image/icLanc.png")));
			btnCarregar.setText("Carregar");
			btnCarregar.setPreferredSize(new Dimension(116, 28));
			btnCarregar.setMnemonic('F');
			btnCarregar.setFont(new Font("Dialog", Font.BOLD, 13));
			btnCarregar.setBounds(new Rectangle(205, 238, 116, 28));
			btnCarregar.setBounds(129, 241, 125, 28);
			jContentPane.add(btnCarregar);
			
			JButton button = new JButton("");
			button.setIcon(new ImageIcon(F9Cliente.class.getResource("/image/icSetar.png")));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					atualizaTable();
				}
			});

			button.setBounds(470, 27, 26, 24);
			jContentPane.add(button);
		}
		return jContentPane;
	}

	public void selTudoTitulos(){
		jTable.selectAll();
		titsSel = jTable.getSelectedRows();
	}
	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {}
			jButton1 = new JButton();
			jButton1.setMnemonic('F');
			jButton1.setBounds(new Rectangle(260, 241, 125, 28));
			jButton1.setIcon(new ImageIcon(F9Cliente.class.getResource("/image/icSair.png")));
			jButton1.setText("Fechar");
			jButton1.setPreferredSize(new Dimension(116, 28));
			jButton1.setFont(new Font("Dialog", Font.BOLD, 13));
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					fechar();
					
				}
			});
		return jButton1;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(13, 60, 494, 160));
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}
	static Cliente c = null;
	public static void atualizaTable(){
		if(c==null){
			c=new Cliente();		
		}
		String[][] dados = null;
		String sql1 = "";
		String sql2 = "";
		
		limparTable();
		String ad = sohCliente==true?" and pr.cliente_contabilidade='S' ":"";
		String text = jTextField.getText();
		int codigo = 0;
		try{codigo = AN.stringPInt(text);}catch(Exception e){}
		if(codigo>0){
			sql1 = "SELECT count(p.id) as total FROM pessoa p "		
					+ "left join pessoa_juridica pj on pj.id_pessoa = p.id "
					+" left join produtor_rural pr on pr.id_pessoa_juridica=pj.id "
					+ " where p.id is not null and p.id="+text+ad;
			sql2 = "SELECT p.id, pj.razao_social FROM pessoa p "
					+ "left join pessoa_juridica pj on pj.id_pessoa = p.id "
					+" left join produtor_rural pr on pr.id_pessoa_juridica=pj.id "
					+ " where  p.id is not null and p.id="+text+ad;
			dados = c.buscarPassandoMatrizCad(sql1, sql2);
		}
		if(dados == null){
			sql1 = "SELECT count(p.id) as total FROM pessoa p "
					+ "left join pessoa_juridica pj on pj.id_pessoa = p.id "
					+" left join produtor_rural pr on pr.id_pessoa_juridica=pj.id "
					+ " where   pj.razao_social like '%"+text+"%'"+ad;
			sql2 = "SELECT p.id, pj.razao_social FROM pessoa p "
					+ "left join pessoa_juridica pj on pj.id_pessoa = p.id "
					+" left join produtor_rural pr on pr.id_pessoa_juridica=pj.id "
					+ " where   pj.razao_social like '%"+text+"%'"+ad;
			dados = c.buscarPassandoMatrizCad(sql1, sql2);
		}
		
		if(dados != null){
			for(int i=0; i<dados.length; i++){
				modelo.addRow(new Object[] {dados[i][0], dados[i][1]});
			}
		}
		else{
			modelo.addRow(new Object[] {"","Nenhum Cliente Encontrado!!!"});
		}
	}
	//
	public static void limparTable(){
		try{
			int modelos = jTable.getRowCount();
			int i = 0;
			if(modelos > 0){
				do{
					modelo.removeRow(i);
				}
				while(i < modelos && jTable.getRowCount() != 0);
				i++;	
			}
			
		}catch(Exception e){}
	}
	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable() {
		if (jTable == null) {
			modelo.addColumn("CÓDIGO");
			modelo.addColumn("CLIENTE");
			
			//
			modelo.addRow(new Object[] {"","Cadastro de Cliente"});
		}
		jTable = new JTable(modelo){
			public boolean isCellEditable(int row, int column) {
				if (column >= 0) {
					return false;
				} else {
					return true;
				}
			}
		};
		jTable.getTableHeader().setReorderingAllowed(false); // Não move as colunas jTable
		jTable.setFont(new Font("Dialog", Font.PLAIN, 13));
		jTable.setColumnSelectionAllowed(false);
		jTable.setRowHeight(20);
		jTable.setRowSelectionAllowed(true);
		//jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jTable.setShowGrid(true);
		jTable.setShowHorizontalLines(true);
		jTable.setShowVerticalLines(true);
		jTable.setVisible(true);
		jTable.setAutoCreateColumnsFromModel(true);
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTable.setSelectionForeground(Color.black);
		jTable.setGridColor(new Color(0, 153, 51));
		jTable.setSelectionBackground(new Color(204, 255, 204));
//		"CÓDIGO", "CLIENTE", "FANT/SOBRE", "CIDADE", "UF",
//		"TELEFONE","FAX","CELULAR"
		jTable.getColumnModel().getColumn(0).setPreferredWidth(95);// Código
		jTable.getColumnModel().getColumn(1).setPreferredWidth(380);// Cliente
		
		//
		//ENTER
		KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
		InputMap map = jTable.getInputMap(JTable.WHEN_FOCUSED);
		map.put(enter, "selectNextColumnCell");	
		
		jTable.addKeyListener(new java.awt.event.KeyListener() {
			public void keyReleased(java.awt.event.KeyEvent e) {
				totalTit = jTable.getRowCount();
				linhaTit = jTable.getSelectedRow();
				colTit = jTable.getSelectedColumn();					
				titSel = "";
				try{titSel = jTable.getValueAt(linhaTit, 0).toString();}catch(Exception e2){}
				titsSel = new int[totalTit];
				titsSel = jTable.getSelectedRows();
			}
			public void keyTyped(java.awt.event.KeyEvent e) {
			}
			public void keyPressed(java.awt.event.KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					linhaTit = jTable.getSelectedRow();
					String a = "";
					try{a = jTable.getValueAt(linhaTit, 0).toString();}catch(Exception e2){}
					if(!a.equals("")){
						carregar();
					}
					
				}if(e.getKeyCode() == KeyEvent.VK_F5){
					jTextField.requestFocus();
				}
			}
		});
		jTable.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				totalTit = jTable.getRowCount();
				linhaTit = jTable.getSelectedRow();
				colTit = jTable.getSelectedColumn();					
				titSel = "";
				try{titSel = jTable.getValueAt(linhaTit, 0).toString();}catch(Exception e2){}
				titsSel = new int[totalTit];
				titsSel = jTable.getSelectedRows();
				if(e.getClickCount() == 2){
					String a = "";
					try{a = jTable.getValueAt(linhaTit, 0).toString();}catch(Exception e2){}
					try{a = jTable.getValueAt(linhaTit, 0).toString();}catch(Exception e2){}
					if(!a.equals("")){
						carregar();
					}
				}
			}
			public void mouseReleased(MouseEvent arg0) {
				totalTit = jTable.getRowCount();
				linhaTit = jTable.getSelectedRow();
				colTit = jTable.getSelectedColumn();					
				titSel = "";
				try{titSel = jTable.getValueAt(linhaTit, 0).toString();}catch(Exception e2){}
				titsSel = new int[totalTit];
				titsSel = jTable.getSelectedRows();
			}
		});
		
		return jTable;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {}
			jTextField = new JTextField();
			jTextField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER){
						atualizaTable();
						String a = "";
						try{a = jTable.getValueAt(0, 0).toString();}catch(Exception e2){}
						if(!a.equals("")){
							jTable.requestFocus();
							jTable.changeSelection(0, 0, true, false);
						}else{
							jTextField.selectAll();
						}
						
					}
					
				}
			});
			
			jTextField.setBackground(Color.white);
			jTextField.setFont(new Font("Dialog", Font.BOLD, 13));
			jTextField.setBounds(new Rectangle(83, 28, 382, 22));
		return jTextField;
	}
	//
	public void carregar(){
		int cod = 0;
		try{cod = AN.stringPInt(jTable.getValueAt(linhaTit, 0).toString());}catch(Exception e){}
		
			if(tela.equals("Inicio")){
				try{
					String txt = jTable.getValueAt(linhaTit, 0).toString()+" - "+jTable.getValueAt(linhaTit, 1).toString();
					if(cod>0){
						Inicio.labelCliente.setText(txt);	
						Inicio.setEntrou(true);
					}else{
						Inicio.labelCliente.setText("");
						Inicio.setEntrou(false);
					}												
					dispose();				
				}catch(Exception e){}
			}else if(tela.equals("LancNotaVend")){
				try{
					String txt = jTable.getValueAt(linhaTit, 0).toString()+" - "+jTable.getValueAt(linhaTit, 1).toString();
					if(cod>0){
						LancNota.textVend.setText(txt);							
					}else{
						LancNota.textVend.setText("");						
					}					
					LancNota.textVend.requestFocus();
					dispose();				
				}catch(Exception e){}
			}else if(tela.equals("LancNotaComp")){
				try{
					String txt = jTable.getValueAt(linhaTit, 0).toString()+" - "+jTable.getValueAt(linhaTit, 1).toString();
					if(cod>0){
						LancNota.textComp.setText(txt);							
					}else{
						LancNota.textComp.setText("");						
					}					
					LancNota.textComp.requestFocus();
					dispose();				
				}catch(Exception e){}
			}			
		
		
		
	}
	public void fechar(){		
		if(tela.equals("Inicio")){
			try{
				dispose();				
			}catch(Exception e){}
		}else if(tela.equals("LancNotaVend")){
			try{
				MovNotaFiscal.lancNota.setSelected(true);
				LancNota.textVend.requestFocus();
				dispose();				
			}catch(Exception e){}
		}else if(tela.equals("LancNotaComp")){
			try{
				MovNotaFiscal.lancNota.setSelected(true);
				LancNota.textComp.requestFocus();
				dispose();				
			}catch(Exception e){}
		}
		sohCliente=false;
	}
	
}

