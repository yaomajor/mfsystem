package br.com.tela.cadastro;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.classes.Usuario;
import br.com.exception.Excecoes;
import br.com.persistencia.UfDao;
import br.com.util.AN;
import br.com.util.Conexao;
import br.com.util.MD5;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField textSenha;

	JComboBox comboUsuario;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	Usuario u = null;
	public Usuario u(){
		if(u==null){
			u=new Usuario();
		}
		return u;
	}
	String[][] us=null;
	public Login() {
	
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/image/icUsuarioPeq.png")));
		setTitle("LOGIN");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 317);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUsurio.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUsurio.setBounds(41, 90, 107, 33);
		contentPane.add(lblUsurio);
		
		comboUsuario = new JComboBox();
		comboUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					textSenha.requestFocus();
				}
			}
		});
		
		us = u().buscarUsuarios();
		if(us!= null){
			for(int i=0; i<us.length; i++){
				comboUsuario.addItem(us[i][0]);
			}
		}else{
			comboUsuario.addItem("[NULL]");
		}
		comboUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboUsuario.setBounds(152, 90, 153, 34);
		contentPane.add(comboUsuario);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSenha.setBounds(41, 134, 107, 33);
		contentPane.add(lblSenha);
		
		textSenha = new JPasswordField();
		textSenha.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textSenha.selectAll();
			}
		});
		textSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					entrar();
				}
			}
		});
		textSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		textSenha.setBounds(152, 134, 153, 34);
		contentPane.add(textSenha);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				entrar();
			}
		});
		btnEntrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEntrar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEntrar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEntrar.setIcon(new ImageIcon(Login.class.getResource("/image/icChave.png")));
		btnEntrar.setBounds(123, 193, 93, 71);
		contentPane.add(btnEntrar);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setIcon(new ImageIcon(Login.class.getResource("/image/icEntra1.png")));
		btnFechar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnFechar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnFechar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnFechar.setBounds(226, 193, 93, 71);
		contentPane.add(btnFechar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Login.class.getResource("/image/usuGif.gif")));
		label.setBounds(190, 20, 115, 71);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setOpaque(true);
		label_1.setBackground(new Color(176, 224, 230));
		label_1.setBounds(0, 0, 444, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setOpaque(true);
		label_2.setBackground(new Color(176, 224, 230));
		label_2.setBounds(0, 24, 93, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setOpaque(true);
		label_3.setBackground(new Color(176, 224, 230));
		label_3.setBounds(0, 49, 58, 14);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.setOpaque(true);
		label_4.setBackground(new Color(176, 224, 230));
		label_4.setBounds(0, 74, 30, 14);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("");
		label_5.setOpaque(true);
		label_5.setBackground(new Color(176, 224, 230));
		label_5.setBounds(0, 100, 12, 14);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("");
		label_6.setOpaque(true);
		label_6.setBackground(new Color(176, 224, 230));
		label_6.setBounds(0, 127, 3, 161);
		contentPane.add(label_6);
		this.setLocationRelativeTo(null);//Centro da tela
		
		try {
			UfDao ufDao = new UfDao();
			ufDao.getTodos("nome");
		} catch (Excecoes e) {
		}
	}
	public void entrar(){
		if(us!=null){
			String usuario = comboUsuario.getSelectedItem().toString();
			String senha = "";
			boolean podeME = false;
			boolean podeLE = false;
			for(int i=0; i<us.length; i++){
				String usFor = us[i][0];
				if(usFor.equals(usuario)){
					senha = us[i][1];
					podeME = us[i][2].equals("S")?true:false;
					podeLE = us[i][3].equals("S")?true:false;					
					break;
				}
			}
			String senhaDig = textSenha.getText();
			MD5 gerar = new MD5();
			senhaDig = gerar.geraMD5(senhaDig);
			System.out.println("Dig "+senhaDig);
			if(senha.equals(senhaDig)){
				dispose();
				try{
					Inicio inicio = new Inicio(usuario, podeME, podeLE);
					inicio.setVisible(true);
					
				}catch(Exception e){AN.jOptionPaneError(e.getMessage());}
				//
				
				
			}else{
				AN.jOptionPaneError("Senha Incorreta!");
			}
		}
		
		
		
	}
}
