package br.com.tela.cadastro;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import br.com.util.JTextData2;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Estoque extends JInternalFrame {
	private JTextField textDataDe;
	private JTextField textDataA;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Estoque frame = new Estoque();
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
	public Estoque() {
		getContentPane().setBackground(Color.WHITE);
		setTitle("Movimenta\u00E7\u00E3o de Estoque");
		setFrameIcon(new ImageIcon(Estoque.class.getResource("/image/icEstoq.png")));
		setIconifiable(true);
		setBounds(100, 100, 950, 459);
		getContentPane().setLayout(null);
		
		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProduto.setHorizontalAlignment(SwingConstants.TRAILING);
		lblProduto.setBounds(0, 11, 72, 21);
		getContentPane().add(lblProduto);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(75, 11, 475, 21);
		getContentPane().add(comboBox);
		
		JLabel lblPerodoDe = new JLabel("Per\u00EDodo de:");
		lblPerodoDe.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPerodoDe.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPerodoDe.setBounds(551, 11, 107, 21);
		getContentPane().add(lblPerodoDe);
		
		textDataDe = new JTextData2();
		textDataDe.setBounds(660, 11, 78, 20);
		getContentPane().add(textDataDe);
		textDataDe.setColumns(10);
		
		JLabel lblA = new JLabel("a");
		lblA.setHorizontalAlignment(SwingConstants.CENTER);
		lblA.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblA.setBounds(737, 11, 32, 21);
		getContentPane().add(lblA);
		
		textDataA = new JTextData2();
		textDataA.setColumns(10);
		textDataA.setBounds(768, 11, 78, 20);
		getContentPane().add(textDataA);
		
		JLabel lblMovimentaoDeEstoque = new JLabel("Movimenta\u00E7\u00E3o de Estoque (0)");
		lblMovimentaoDeEstoque.setForeground(new Color(102, 0, 102));
		lblMovimentaoDeEstoque.setHorizontalAlignment(SwingConstants.CENTER);
		lblMovimentaoDeEstoque.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMovimentaoDeEstoque.setBounds(10, 57, 914, 21);
		getContentPane().add(lblMovimentaoDeEstoque);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 78, 914, 267);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 369, 618, 35);
		getContentPane().add(panel);
		
		JButton btnMudarEra = new JButton("Mudar \u00C9ra");
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
		btnIncluir.setIcon(new ImageIcon(Estoque.class.getResource("/image/icLanc.png")));
		btnIncluir.setBounds(10, 0, 135, 32);
		panel.add(btnIncluir);

	}
	public void fechar(){
		dispose();
		Inicio.limparTela(Inicio.estoque);
		Inicio.estoque=null;
		Inicio.setBotaoEstoque(true);
	}

}
