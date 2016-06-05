package br.com.tela.cadastro;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import br.com.util.JTextFieldFocu;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroUsuario extends JInternalFrame {
	private JTextField textID;
	private JTextField textLogin;
	private JPasswordField textSenha;
	private JTable table;

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
		textID.setBounds(103, 11, 86, 20);
		getContentPane().add(textID);
		textID.setColumns(10);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUsurio.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsurio.setBounds(10, 34, 87, 21);
		getContentPane().add(lblUsurio);
		
		textLogin = new JTextFieldFocu();
		textLogin.setColumns(10);
		textLogin.setBounds(103, 34, 223, 20);
		getContentPane().add(textLogin);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSenha.setBounds(10, 57, 87, 21);
		getContentPane().add(lblSenha);
		
		textSenha = new JPasswordField();
		textSenha.setBounds(103, 57, 87, 21);
		getContentPane().add(textSenha);
		
		JLabel lblPermiteMudarEra = new JLabel("Permite Mudar Era:");
		lblPermiteMudarEra.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPermiteMudarEra.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPermiteMudarEra.setBounds(191, 56, 134, 21);
		getContentPane().add(lblPermiteMudarEra);
		
		JRadioButton rdbtnSim = new JRadioButton("Sim");
		rdbtnSim.setBackground(Color.WHITE);
		rdbtnSim.setBounds(329, 55, 56, 23);
		getContentPane().add(rdbtnSim);
		
		JRadioButton rdbtnNo = new JRadioButton("N\u00E3o");
		rdbtnNo.setSelected(true);
		rdbtnNo.setBackground(Color.WHITE);
		rdbtnNo.setBounds(387, 55, 77, 23);
		getContentPane().add(rdbtnNo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 112, 770, 234);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblUsurios = new JLabel("Usu\u00E1rios (0)");
		lblUsurios.setForeground(new Color(139, 0, 139));
		lblUsurios.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsurios.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsurios.setBounds(0, 89, 790, 23);
		getContentPane().add(lblUsurios);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 363, 701, 35);
		getContentPane().add(panel);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.setIcon(new ImageIcon(CadastroUsuario.class.getResource("/image/icLanc.png")));
		btnIncluir.setBounds(10, 0, 135, 32);
		panel.add(btnIncluir);
		
		JButton button_1 = new JButton("Alterar");
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
		setIconifiable(true);
		setBounds(100, 100, 806, 454);

	}
	public void fechar(){
		dispose();
		Inicio.limparTela(Inicio.cadUsuario);
		Inicio.cadUsuario=null;
		Inicio.setBotaoCadUsuario(true);
	}
}
