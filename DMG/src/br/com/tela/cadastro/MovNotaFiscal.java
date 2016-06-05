package br.com.tela.cadastro;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import br.com.util.JTextData;
import br.com.util.JTextData2;

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
import java.awt.event.ActionEvent;

public class MovNotaFiscal extends JInternalFrame {
	private JTextField textField;
	private JTable table;
	private JTextField textDataDe;
	private JTextField textDataA;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovNotaFiscal frame = new MovNotaFiscal();
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
	public MovNotaFiscal() {
		getContentPane().setBackground(Color.WHITE);
		setIconifiable(true);
		setBounds(100, 100, 727, 479);
		getContentPane().setLayout(null);
		
		JLabel lblCliente = new JLabel("Produtor:");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCliente.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCliente.setBounds(0, 11, 67, 21);
		getContentPane().add(lblCliente);
		
		textField = new JTextField();
		textField.setBounds(72, 11, 330, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 93, 687, 293);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNotasFiscais = new JLabel("Notas Fiscais (0)");
		lblNotasFiscais.setForeground(SystemColor.textHighlight);
		lblNotasFiscais.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotasFiscais.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNotasFiscais.setBounds(0, 61, 711, 35);
		getContentPane().add(lblNotasFiscais);
		
		JLabel lblPerodo = new JLabel("Per\u00EDodo de:");
		lblPerodo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPerodo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPerodo.setBounds(435, 11, 76, 21);
		getContentPane().add(lblPerodo);
		
		textDataDe = new JTextData2();
		textDataDe.setColumns(10);
		textDataDe.setBounds(513, 11, 67, 20);
		getContentPane().add(textDataDe);
		
		JLabel lblA = new JLabel("a");
		lblA.setHorizontalAlignment(SwingConstants.CENTER);
		lblA.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblA.setBounds(581, 11, 27, 21);
		getContentPane().add(lblA);
		
		textDataA = new JTextData2();
		textDataA.setColumns(10);
		textDataA.setBounds(607, 11, 67, 20);
		getContentPane().add(textDataA);
		
		JLabel lblf = new JLabel("(F9)");
		lblf.setForeground(SystemColor.controlShadow);
		lblf.setHorizontalAlignment(SwingConstants.CENTER);
		lblf.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblf.setBounds(402, 11, 27, 21);
		getContentPane().add(lblf);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 403, 701, 35);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnLanar = new JButton("Lan\u00E7ar");
		btnLanar.setBounds(10, 0, 135, 32);
		panel.add(btnLanar);
		btnLanar.setIcon(new ImageIcon(MovNotaFiscal.class.getResource("/image/icLanc.png")));
		
		JButton btnAlterar = new JButton("Alterar/Ver");
		btnAlterar.setBounds(148, 0, 135, 32);
		panel.add(btnAlterar);
		btnAlterar.setIcon(new ImageIcon(MovNotaFiscal.class.getResource("/image/icAlt.png")));
		
		JButton btnExcluir = new JButton("Excluir");
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Entrada", "Sa\u00EDda"}));
		comboBox.setBounds(72, 36, 115, 21);
		getContentPane().add(comboBox);

	}
	public void fechar(){
		dispose();
		Inicio.limparTela(Inicio.movNotaFiscal);
		Inicio.movNotaFiscal=null;
		Inicio.setBotaoMovNotaFiscal(true);
	}
}
