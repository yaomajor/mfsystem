package br.com.tela.cadastro;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class ProdutorRuralConsulta extends JInternalFrame {
	private JTable table;
	public static ProdutorRuralCad produtorRuralCad;
	private static JPanel panelBotoes;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProdutorRuralConsulta frame = new ProdutorRuralConsulta();
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
	public ProdutorRuralConsulta() {
		setBounds(100, 100, 626, 437);
		getContentPane().setLayout(null);
		
		table = new JTable();
		table.setBounds(22, 133, 564, 183);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 162, 590, 183);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(10, 128, 116, 23);
		getContentPane().add(btnPesquisar);
		
		panelBotoes = new JPanel();
		panelBotoes.setBounds(10, 356, 590, 40);
		getContentPane().add(panelBotoes);
		panelBotoes.setLayout(null);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.setBounds(10, 11, 89, 23);
		panelBotoes.add(btnIncluir);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(112, 11, 89, 23);
		panelBotoes.add(btnAlterar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(211, 11, 89, 23);
		panelBotoes.add(btnExcluir);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		btnFechar.setBounds(310, 11, 89, 23);
		panelBotoes.add(btnFechar);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(59, 97, 92, 20);
		getContentPane().add(textField);
		
		JLabel label = new JLabel("Nome Propriedade :");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(161, 100, 126, 14);
		getContentPane().add(label);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(276, 97, 324, 20);
		getContentPane().add(textField_1);
		
		JLabel label_1 = new JLabel("C\u00F3digo :");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(10, 100, 52, 14);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Nome Fantasia :");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setBounds(10, 69, 92, 14);
		getContentPane().add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(102, 66, 498, 20);
		getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(102, 38, 498, 20);
		getContentPane().add(textField_3);
		
		JLabel label_3 = new JLabel("Raz\u00E3o Social :");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(10, 40, 92, 14);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("CNPJ :");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_4.setBounds(10, 14, 40, 14);
		getContentPane().add(label_4);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(60, 11, 140, 20);
		getContentPane().add(textField_4);
		
		JLabel label_5 = new JLabel("IE :");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_5.setBounds(210, 14, 26, 14);
		getContentPane().add(label_5);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(241, 11, 126, 20);
		getContentPane().add(textField_5);
		
		JLabel label_6 = new JLabel("C\u00F3digo Propriedade :");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_6.setBounds(377, 14, 116, 14);
		getContentPane().add(label_6);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(499, 11, 101, 20);
		getContentPane().add(textField_6);
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluir();
			}
		});

	}
	
	private void incluir() {
		produtorRuralCad = new ProdutorRuralCad();
		Inicio.addTela(produtorRuralCad);
		produtorRuralCad.setVisible(true);
		try{
			produtorRuralCad.setSelected(true);
		}catch(Exception e){}		
		setPanelBotoes(false);
	}
	
	
	public static void setPanelBotoes(boolean a){
		panelBotoes.setVisible(a);	
	}
	
	private void fechar() {
		this.dispose();
		Inicio.limparTela(Inicio.produtorRuralConsulta);
		Inicio.produtorRuralConsulta=null;
		Inicio.setBotaoProdutorRural(true);
	}
}
