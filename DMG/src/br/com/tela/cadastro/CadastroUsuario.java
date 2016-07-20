package br.com.tela.cadastro;

import java.awt.EventQueue; 

import javax.swing.JInternalFrame;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import br.com.classes.Usuario;
import br.com.util.AN;
import br.com.util.JTextFieldFocu;
import br.com.util.MD5;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CadastroUsuario extends JInternalFrame {
	private JTextField textID;
	private JTextField textLogin;
	private JPasswordField textSenha;
	private JTable jTable;
	JScrollPane scrollPane;
	private JRadioButton radioEraSim;
	private JRadioButton radioEraNao;
	private ButtonGroup  r1 = new ButtonGroup(); 
	
	public JRadioButton radioEstSim;
	public JRadioButton radioEstNao;
	private ButtonGroup  r2 = new ButtonGroup(); 
	//
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroUsuario frame = new CadastroUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastroUsuario() {
		setTitle("Cadastro de Usu\u00E1rio");
		
		setFrameIcon(new ImageIcon(CadastroUsuario.class.getResource("/image/icUsuarioPeq.png")));
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblId.setHorizontalAlignment(SwingConstants.TRAILING);
		lblId.setBounds(10, 11, 87, 21);
		getContentPane().add(lblId);
		
		textID = new JTextFieldFocu();
		textID.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
			}
			public void focusLost(FocusEvent arg0) {
				verCodigo();
			}
		});
		textID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					textLogin.requestFocus();
				}
			}
		});
		textID.setBounds(103, 11, 86, 20);
		getContentPane().add(textID);
		textID.setColumns(10);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUsurio.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsurio.setBounds(10, 34, 87, 21);
		getContentPane().add(lblUsurio);
		
		textLogin = new JTextFieldFocu();
		textLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					textSenha.requestFocus();
				}
			}
		});
		textLogin.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
			}
			public void focusLost(FocusEvent arg0) {
				
			}
		});
		textLogin.setColumns(10);
		textLogin.setBounds(103, 34, 223, 20);
		getContentPane().add(textLogin);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSenha.setBounds(10, 57, 87, 21);
		getContentPane().add(lblSenha);
		
		textSenha = new JPasswordField();
		textSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					btnPesq.requestFocus();
				}
			}
		});
		textSenha.setBounds(103, 57, 87, 21);
		getContentPane().add(textSenha);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 112, 770, 234);
		getContentPane().add(scrollPane);
		
		
		scrollPane.setViewportView(getJTable());
		
		JLabel lblUsurios = new JLabel("Usu\u00E1rios (0)");
		lblUsurios.setForeground(new Color(139, 0, 139));
		lblUsurios.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsurios.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsurios.setBounds(0, 89, 790, 23);
		getContentPane().add(lblUsurios);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 363, 735, 35);
		getContentPane().add(panel);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				incluir(); 
			}
		});
		btnIncluir.setIcon(new ImageIcon(CadastroUsuario.class.getResource("/image/icLanc.png")));
		btnIncluir.setBounds(10, 0, 135, 32);
		panel.add(btnIncluir);
		
		JButton button_1 = new JButton("Alterar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterar();
			}
		});
		button_1.setIcon(new ImageIcon(CadastroUsuario.class.getResource("/image/icAlt.png")));
		button_1.setBounds(148, 0, 135, 32);
		panel.add(button_1);
		
		JButton button_2 = new JButton("Excluir");
		button_2.setIcon(new ImageIcon(CadastroUsuario.class.getResource("/image/icExcluir.png")));
		button_2.setBounds(286, 0, 135, 32);
		panel.add(button_2);
		
		JButton button_3 = new JButton("Relat\u00F3rio");
		button_3.setIcon(new ImageIcon(CadastroUsuario.class.getResource("/image/icRel.png")));
		button_3.setBounds(424, 0, 135, 32);
		panel.add(button_3);
		
		JButton button_4 = new JButton("Fechar");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		button_4.setIcon(new ImageIcon(CadastroUsuario.class.getResource("/image/icSair.png")));
		button_4.setBounds(562, 0, 135, 32);
		panel.add(button_4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Permiss\u00F5es", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(336, 5, 444, 76);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblPermiteMudarEra = new JLabel("Permite Mudar Era:");
		lblPermiteMudarEra.setBounds(31, 22, 134, 21);
		panel_1.add(lblPermiteMudarEra);
		lblPermiteMudarEra.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPermiteMudarEra.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		radioEraSim = new JRadioButton("Sim");
		r1.add(radioEraSim);
		radioEraSim.setBounds(169, 21, 56, 23);
		panel_1.add(radioEraSim);
		radioEraSim.setBackground(Color.WHITE);
		
		radioEraNao = new JRadioButton("N\u00E3o");
		r1.add(radioEraNao);
		radioEraNao.setBounds(227, 21, 77, 23);
		panel_1.add(radioEraNao);
		radioEraNao.setSelected(true);
		radioEraNao.setBackground(Color.WHITE);
		
		JLabel lblLanaManualEstoque = new JLabel("Lan\u00E7a Manual Estoque:");
		lblLanaManualEstoque.setHorizontalAlignment(SwingConstants.TRAILING);
		lblLanaManualEstoque.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLanaManualEstoque.setBounds(21, 48, 144, 21);
		panel_1.add(lblLanaManualEstoque);
		
		radioEstSim = new JRadioButton("Sim");
		r2.add(radioEstSim);
		radioEstSim.setBackground(Color.WHITE);
		radioEstSim.setBounds(169, 46, 56, 23);
		panel_1.add(radioEstSim);
		
		radioEstNao = new JRadioButton("N\u00E3o");
		r2.add(radioEstNao);
		radioEstNao.setSelected(true);
		radioEstNao.setBackground(Color.WHITE);
		radioEstNao.setBounds(227, 46, 77, 23);
		panel_1.add(radioEstNao);
		
		btnPesq = new JButton("Pesquisar");
		btnPesq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizaTable();
			}
		});
		btnPesq.setIcon(new ImageIcon(CadastroUsuario.class.getResource("/image/lupaNo02.png")));
		btnPesq.setBounds(197, 56, 129, 23);
		getContentPane().add(btnPesq);
		setIconifiable(true);
		setBounds(100, 100, 806, 454);

	}
	JButton btnPesq;
	public String[][] matriz;
	public int linhaSel = -1;
	//
	Usuario u = null;
	public String[][] dados(){
		String[][] rs = null;
		if(u == null){
			u = new Usuario();
		}
		String text = textLogin.getText();		
		if(!text.equals("")){
			int cod = 0;
			try{cod = AN.stringPInt(text);}catch(Exception e){}
			if(cod>0){
				text= " and id="+text;
			}else{
				text = " and LOGIN like '%"+text+"%' ";
			}
		}		
		rs = u.buscarTodos(text);
				
		if(rs != null){
			matriz = rs;			
		}else{
			matriz = new String[1][4];
			matriz[0][0] = "";
			matriz[0][1] = "Nenhum Usuário Cadastrado!";
			matriz[0][2] = "";
			matriz[0][3] = "";
			
		}
		return matriz;
	}
	
	
	public void atualizaTable(){
		dados();
		scrollPane.setViewportView(getJTable());
	}
	private JTable getJTable() {
		if (jTable == null) {
			matriz = new String[1][4];
			matriz[0][0] = "";
			matriz[0][1] = "Nenhum Usuário Cadastrado!";
			matriz[0][2] = "";
			matriz[0][3] = "";
		}
		String[] cab = {"ID","Usuário", "Pode Mudar Era", "Pode Lançar Estoque"};
		jTable = new JTable(matriz, cab) {
			public boolean isCellEditable(int row, int column) {
				if (column>=0) {
					return false;
				} else {
					return true;
				}
			}
		};
		jTable.getTableHeader().setReorderingAllowed(false);
		jTable.setAutoCreateRowSorter(true);
		jTable.setAutoCreateColumnsFromModel(true);
		jTable.setSize(new Dimension(555, 80));
		jTable.setBackground(new Color(249, 255, 249));
		jTable.setFont(new Font("Dialog", Font.PLAIN, 15));
		jTable.setForeground(Color.black);
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTable.setColumnSelectionAllowed(false);
		jTable.setGridColor(new Color(0, 153, 102));
		jTable.setRowHeight(25);
		jTable.setRowSelectionAllowed(true);
		jTable.setSelectionBackground(new Color(204, 255, 204));
		jTable.setShowGrid(true);
		jTable.setShowHorizontalLines(true);
		jTable.setShowVerticalLines(true);
		jTable.setComponentOrientation(ComponentOrientation.UNKNOWN);
		jTable.setShowVerticalLines(true);
		jTable.addKeyListener(new java.awt.event.KeyAdapter() {   
			public void keyPressed(java.awt.event.KeyEvent e) {    
				if(e.getKeyCode() == KeyEvent.VK_F5){
					
				}
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					//ver();
				}
			}   
		
			public void keyReleased(java.awt.event.KeyEvent e) {
				linhaSel = jTable.getSelectedRow();
				ver();
			}
		});
		jTable.addMouseListener(new java.awt.event.MouseAdapter() {   
			public void mouseClicked(java.awt.event.MouseEvent e) {    
				if(e.getClickCount() == 2){
					ver();
				}
			}
			public void mousePressed(java.awt.event.MouseEvent e) {
				linhaSel = jTable.getSelectedRow();
				ver();
			}
			
			
			
		});
		jTable.getColumnModel().getColumn(0).setPreferredWidth(55);//ID
		jTable.getColumnModel().getColumn(1).setPreferredWidth(350);//Usuário
		jTable.getColumnModel().getColumn(2).setPreferredWidth(150);//Pode Mudar Era
		jTable.getColumnModel().getColumn(3).setPreferredWidth(150);//Pode Lançar Estoque
		return jTable;
	}
	public void fechar(){
		dispose();
		Inicio.limparTela(Inicio.cadUsuario);
		Inicio.cadUsuario=null;
		Inicio.setBotaoCadUsuario(true);
	}
	//
	public Usuario u(){
		if(u==null){
			u=new Usuario();
		}
		return u;
	}
	public void verCodigo(){
		String cod = textID.getText();
		int codigo = AN.stringPInt(cod);
		if(codigo>0){
			boolean existe = u().existeID(cod);
			if(existe==true){
				textID.setText(u().pxoID());
				AN.jOptionPaneError("Já Existe um Usuário com o ID Informado!");
			} 
		}			
	}
	public void incluir(){
		String id = textID.getText();
		String login = textLogin.getText();
		String senha = textSenha.getText();
		String podeMudarEra = radioEraSim.isSelected()?"S":"N";
		String podeMovEst   = radioEstSim.isSelected()?"S":"N";
		boolean temLogin = u().existeUsuario(login);
		boolean temID = u().existeID(id);
		if(temLogin==false){
			int codigo = AN.stringPInt(id);
			String pxoCod = u().pxoID();
			boolean usaCod = false;
			
			if(codigo<=0){
				int a = AN.jOptionPaneQuestion("Deseja Incluir com o ID: "+pxoCod+" ?");
				if(a==0){
					usaCod=true;
				}else{
					
				}	
			}
			//		
			///
			if(temID==false){
				id = usaCod==true?pxoCod:id;
				boolean senhaCorreta = AN.jOptionPanePassword("Confirme a Senha:", senha);
				if(senhaCorreta==true){
					MD5 gerar = new MD5();
					senha = gerar.geraMD5(textSenha.getText());
					boolean cad = u().incluirUsuario(AN.stringPInt(id), login, senha, podeMudarEra, podeMovEst);
					if(cad==true){
						AN.jOptionPaneInformation("Usuário Cadastrado com Sucesso!");
					}
				}else{
					AN.jOptionPaneError("Senha Incorreta!");
				}
				
			}else{
				AN.jOptionPaneError("Já Existe um Usuário com o ID Informado!");
			}
		}else{
			AN.jOptionPaneError("Já Existe um Usuário com o Nome Informado!");
		}
		
		
	}
	//
	public void alterar(){
		String id = "";
		try{id = jTable.getValueAt(linhaSel, 0).toString();}catch(Exception e){}
		String login = textLogin.getText();		
		String podeMudarEra = radioEraSim.isSelected()?"S":"N";
		String podeMovEst   = radioEstSim.isSelected()?"S":"N";
		boolean temLogin = login.equals(jTable.getValueAt(linhaSel, 1).toString())?false:u().existeUsuario(login);
		if(temLogin==false){
			///		
			boolean cad = u().alterar(AN.stringPInt(id), login, podeMudarEra, podeMovEst);
			if(cad==true){
				AN.jOptionPaneInformation("Usuário Alterado com Sucesso!");
				textID.setText("");
				textLogin.setText("");
				atualizaTable();
			}			
		}else{
			AN.jOptionPaneError("Já Existe um Usuário com o Nome Informado!");
		}
		
		
	}
	public void ver(){
		String id = "";
		try{id = jTable.getValueAt(linhaSel, 0).toString();}catch(Exception e){}
		if(!id.equals("")){
			String usuario = jTable.getValueAt(linhaSel, 1).toString();
			String podeMudarEra = jTable.getValueAt(linhaSel, 2).toString();
			String podeLancEst = jTable.getValueAt(linhaSel, 3).toString();
			textID.setText(id);
			textLogin.setText(usuario);
			radioEraSim.setSelected(podeMudarEra.equals("S")?true:false);
			radioEraNao.setSelected(podeMudarEra.equals("N")?true:false);
			radioEstSim.setSelected(podeLancEst.equals("S")?true:false);
			radioEstNao.setSelected(podeLancEst.equals("N")?true:false);
		}
		
		
		
	}
}
