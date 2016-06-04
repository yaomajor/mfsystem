package br.com.tela.cadastro;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;

public class MudarDeEra extends JInternalFrame {
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MudarDeEra frame = new MudarDeEra();
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
	public MudarDeEra() {
		setTitle("Mudan\u00E7a de ERA");
		setIconifiable(true);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 481, 282);
		getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"0-3 meses", "3-8 meses", "8-12 meses", "12-24 meses", "24-36 meses", "Acima de 36 meses"}));
		comboBox.setBounds(85, 71, 302, 20);
		getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(85, 40, 302, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCliente.setBounds(10, 40, 74, 20);
		getContentPane().add(lblCliente);
		
		JLabel lblDe = new JLabel("De:");
		lblDe.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDe.setBounds(10, 71, 74, 20);
		getContentPane().add(lblDe);
		
		JLabel lblPara = new JLabel("Para:");
		lblPara.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPara.setBounds(10, 102, 74, 20);
		getContentPane().add(lblPara);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"0-3 meses", "3-8 meses", "8-12 meses", "12-24 meses", "24-36 meses", "Acima de 36 meses"}));
		comboBox_1.setBounds(85, 102, 302, 20);
		getContentPane().add(comboBox_1);
		
		JButton btnGravar = new JButton("Gravar");
		btnGravar.setBounds(114, 190, 115, 29);
		getContentPane().add(btnGravar);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnFechar.setBounds(233, 190, 115, 29);
		getContentPane().add(btnFechar);
		
		JLabel lblf = new JLabel("(F9)");
		lblf.setForeground(Color.GRAY);
		lblf.setHorizontalAlignment(SwingConstants.CENTER);
		lblf.setBounds(387, 40, 34, 20);
		getContentPane().add(lblf);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setHorizontalAlignment(SwingConstants.TRAILING);
		lblQuantidade.setBounds(10, 133, 74, 20);
		getContentPane().add(lblQuantidade);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(85, 133, 46, 20);
		getContentPane().add(spinner);

	}
}
