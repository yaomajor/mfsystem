package br.com.tela.cadastro;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import br.com.util.AN;
import br.com.util.JTextData;
import br.com.util.JTextFieldFocu;
import br.com.util.SomenteNumeros;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LancarEstoque extends JInternalFrame {
	private JTextField textField;
	private JTextField textData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LancarEstoque frame = new LancarEstoque();
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
	public LancarEstoque() {
		setFrameIcon(new ImageIcon(LancarEstoque.class.getResource("/image/icEstoq.png")));
		setTitle("Lan\u00E7amento Manual de Estoque");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProduto.setHorizontalAlignment(SwingConstants.TRAILING);
		lblProduto.setBounds(0, 25, 96, 21);
		getContentPane().add(lblProduto);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"0-3 meses", "3-8 meses", "8-12 meses", "12-24 meses", "24-36 meses", "Acima de 36 meses"}));
		comboBox.setBounds(100, 25, 351, 21);
		getContentPane().add(comboBox);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setHorizontalAlignment(SwingConstants.TRAILING);
		lblQuantidade.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQuantidade.setBounds(0, 51, 96, 21);
		getContentPane().add(lblQuantidade);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTipo.setBounds(0, 76, 96, 21);
		getContentPane().add(lblTipo);
		
		textField = new JTextFieldFocu();
		textField.setDocument(new SomenteNumeros());
		textField.setForeground(Color.BLUE);
		textField.setHorizontalAlignment(SwingConstants.TRAILING);
		textField.setFont(new Font("Tahoma", Font.BOLD, 13));
		textField.setText("0");
		textField.setBounds(100, 51, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JComboBox comboTipo = new JComboBox();
		comboTipo.setModel(new DefaultComboBoxModel(new String[] {"Entrada", "Sa\u00EDda"}));
		comboTipo.setBounds(100, 76, 86, 21);
		getContentPane().add(comboTipo);
		
		JButton btnGravar = new JButton("Gravar");
		btnGravar.setIcon(new ImageIcon(LancarEstoque.class.getResource("/image/icSalvar2.png")));
		btnGravar.setBounds(143, 146, 115, 29);
		getContentPane().add(btnGravar);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		btnFechar.setIcon(new ImageIcon(LancarEstoque.class.getResource("/image/icSair.png")));
		btnFechar.setBounds(262, 146, 115, 29);
		getContentPane().add(btnFechar);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setHorizontalAlignment(SwingConstants.TRAILING);
		lblData.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblData.setBounds(0, 102, 96, 21);
		getContentPane().add(lblData);
		
		textData = new JTextData();
		textData.setText(AN.dataAtualText());
		textData.setHorizontalAlignment(SwingConstants.CENTER);
		textData.setForeground(Color.DARK_GRAY);
		textData.setFont(new Font("Tahoma", Font.BOLD, 12));
		textData.setColumns(10);
		textData.setBounds(100, 102, 86, 20);
		getContentPane().add(textData);
		setIconifiable(true);
		setBounds(100, 100, 537, 228);

	}
	public void fechar(){
		dispose();
		try{
			Inicio.limparTela(Estoque.lancarEstoque);
			Estoque.lancarEstoque=null;
			Inicio.estoque.setSelected(true);
			Estoque.setBotoes(true);
		}catch(Exception e){}
	}
}
