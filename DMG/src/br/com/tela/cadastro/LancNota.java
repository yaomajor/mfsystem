package br.com.tela.cadastro;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import br.com.util.AN;
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

public class LancNota extends JInternalFrame {
	private JTextField textVend;
	private JTextField textComp;
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
	
	JComboBox comboProd;
	JComboBox comboSexo;
	/**
	 * Create the frame.
	 */
	
	String op = "";
	public LancNota() {
		getContentPane().setBackground(Color.WHITE);
		setIconifiable(true);
		setBounds(100, 100, 603, 359);
		getContentPane().setLayout(null);
		
		JLabel lblVendedor = new JLabel("Vendedor:");
		lblVendedor.setHorizontalAlignment(SwingConstants.TRAILING);
		lblVendedor.setBounds(10, 69, 74, 20);
		getContentPane().add(lblVendedor);
		
		textVend = new JTextFieldFocu();
		textVend.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					textComp.requestFocus();
				}
			}
		});
		textVend.setColumns(10);
		textVend.setBounds(85, 69, 415, 20);
		getContentPane().add(textVend);
		
		JLabel label_1 = new JLabel("(F9)");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.GRAY);
		label_1.setBounds(500, 69, 34, 20);
		getContentPane().add(label_1);
		
		JLabel lblComprador = new JLabel("Comprador:");
		lblComprador.setHorizontalAlignment(SwingConstants.TRAILING);
		lblComprador.setBounds(10, 95, 74, 20);
		getContentPane().add(lblComprador);
		
		textComp = new JTextFieldFocu();
		textComp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					comboProd.requestFocus();
				}
			}
		});
		textComp.setColumns(10);
		textComp.setBounds(85, 95, 415, 20);
		getContentPane().add(textComp);
		
		JLabel label_2 = new JLabel("(F9)");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.GRAY);
		label_2.setBounds(500, 95, 34, 20);
		getContentPane().add(label_2);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Produto da Nota", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 125, 522, 91);
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
		lblUn.setBounds(387, 25, 31, 20);
		panel.add(lblUn);
		
		textUN = new JTextField();
		textUN.setEditable(false);
		textUN.setText("cb\u00E7");
		textUN.setColumns(10);
		textUN.setBounds(422, 25, 42, 20);
		panel.add(textUN);
		
		JLabel lblVlrUnitrio = new JLabel("Vlr.: Unit\u00E1rio:");
		lblVlrUnitrio.setHorizontalAlignment(SwingConstants.TRAILING);
		lblVlrUnitrio.setBounds(133, 50, 74, 20);
		panel.add(lblVlrUnitrio);
		
		textVlrUnit = new JMoneyField();
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
		textVlrUnit.setBounds(208, 50, 67, 20);
		panel.add(textVlrUnit);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setHorizontalAlignment(SwingConstants.TRAILING);
		lblQuantidade.setBounds(10, 50, 74, 20);
		panel.add(lblQuantidade);
		
		textQtd = new JTextFieldFocu();
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
		lblTotal.setBounds(364, 50, 54, 20);
		panel.add(lblTotal);
		
		textTotal = new JTextFieldFocu();
		textTotal.setBackground(Color.BLUE);
		textTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		textTotal.setForeground(Color.YELLOW);
		textTotal.setText("0,00");
		textTotal.setHorizontalAlignment(SwingConstants.TRAILING);
		textTotal.setEditable(false);
		textTotal.setColumns(10);
		textTotal.setBounds(422, 50, 68, 20);
		panel.add(textTotal);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSexo.setBounds(278, 50, 42, 20);
		panel.add(lblSexo);
		
		comboSexo = new JComboBox();
		comboSexo.setModel(new DefaultComboBoxModel(new String[] {"M", "F"}));
		comboSexo.setBounds(322, 50, 42, 20);
		panel.add(comboSexo);
		
		JLabel lblNaturezaDeOp = new JLabel("N\u00BA Nota Fiscal:");
		lblNaturezaDeOp.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNaturezaDeOp.setBounds(0, 18, 84, 20);
		getContentPane().add(lblNaturezaDeOp);
		
		textNF = new JTextFieldFocu();
		textNF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					textNatOp.requestFocus();
				}
			}
		});
		textNF.setColumns(10);
		textNF.setBounds(85, 18, 67, 20);
		getContentPane().add(textNF);
		
		JLabel lblNaturezaDeOperao = new JLabel("Natureza de Opera\u00E7\u00E3o:");
		lblNaturezaDeOperao.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNaturezaDeOperao.setBounds(152, 18, 139, 20);
		getContentPane().add(lblNaturezaDeOperao);
		
		textNatOp = new JTextFieldFocu();
		textNatOp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					textEm.requestFocus();
				}
			}
		});
		textNatOp.setColumns(10);
		textNatOp.setBounds(293, 18, 207, 20);
		getContentPane().add(textNatOp);
		
		JLabel lblEmisso = new JLabel("Emiss\u00E3o:");
		lblEmisso.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEmisso.setBounds(0, 44, 84, 20);
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
		textEm.setBounds(85, 44, 67, 20);
		getContentPane().add(textEm);
		
		JLabel lblEntrega = new JLabel("Entrega:");
		lblEntrega.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEntrega.setBounds(152, 44, 74, 20);
		getContentPane().add(lblEntrega);
		
		textEnt = new JTextData();
		textEnt.setText(dataAtual);
		textEnt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					textVend.requestFocus();
				}
			}
		});
		textEnt.setColumns(10);
		textEnt.setBounds(227, 44, 67, 20);
		getContentPane().add(textEnt);
		
		JButton btnIncluir = new JButton("Gravar");
		btnIncluir.setIcon(new ImageIcon(LancNota.class.getResource("/image/icSalvar2.png")));
		btnIncluir.setBounds(175, 267, 115, 29);
		getContentPane().add(btnIncluir);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		btnFechar.setIcon(new ImageIcon(LancNota.class.getResource("/image/icSair.png")));
		btnFechar.setBounds(294, 267, 115, 29);
		getContentPane().add(btnFechar);

	}
	public void fechar(){
		dispose();
		try{
			Inicio.limparTela(MovNotaFiscal.lancNota);
			MovNotaFiscal.lancNota=null;
			Inicio.movNotaFiscal.setSelected(true);
			MovNotaFiscal.setBotoes(true);
		}catch(Exception e){}
		
	}
	
}
