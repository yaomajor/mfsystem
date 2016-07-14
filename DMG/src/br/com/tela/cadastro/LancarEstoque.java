package br.com.tela.cadastro;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import br.com.classes.Animais;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LancarEstoque extends JInternalFrame {
	private JTextField textQtd;
	private JTextField textData;
	JComboBox comboProd;
	JComboBox comboTipo;
	JComboBox comboSexo;
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	int codCli =0;
	public LancarEstoque(int codCli) {
		this.codCli=codCli;
		setFrameIcon(new ImageIcon(LancarEstoque.class.getResource("/image/icEstoq.png")));
		setTitle("Lan\u00E7amento Manual de Estoque");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProduto.setHorizontalAlignment(SwingConstants.TRAILING);
		lblProduto.setBounds(0, 25, 96, 21);
		getContentPane().add(lblProduto);
		
		comboProd = new JComboBox();
		comboProd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					comboSexo.requestFocus();
				}
			}
		});
		comboProd.setModel(new DefaultComboBoxModel(new String[] {"0-3 meses", "3-8 meses", "8-12 meses", "12-24 meses", "24-36 meses", "Acima de 36 meses"}));
		comboProd.setBounds(100, 25, 296, 21);
		getContentPane().add(comboProd);
		
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
		
		textQtd = new JTextFieldFocu();
		textQtd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					comboTipo.requestFocus();
				}
			}
		});
		textQtd.setDocument(new SomenteNumeros());
		textQtd.setForeground(Color.BLUE);
		textQtd.setHorizontalAlignment(SwingConstants.TRAILING);
		textQtd.setFont(new Font("Tahoma", Font.BOLD, 13));
		textQtd.setText("0");
		textQtd.setBounds(100, 51, 86, 20);
		getContentPane().add(textQtd);
		textQtd.setColumns(10);
		
		comboTipo = new JComboBox();
		comboTipo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					textData.requestFocus();
				}
			}
		});
		comboTipo.setModel(new DefaultComboBoxModel(new String[] {"Entrada", "Sa\u00EDda"}));
		comboTipo.setBounds(100, 76, 86, 21);
		getContentPane().add(comboTipo);
		
		JButton btnGravar = new JButton("Gravar");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gravar();
			}
		});
		btnGravar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					gravar();
				}
			}
		});
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
		textData.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					btnGravar.requestFocus();
				}
			}
		});
		textData.setText(AN.dataAtualText());
		textData.setHorizontalAlignment(SwingConstants.CENTER);
		textData.setForeground(Color.DARK_GRAY);
		textData.setFont(new Font("Tahoma", Font.BOLD, 12));
		textData.setColumns(10);
		textData.setBounds(100, 102, 86, 20);
		getContentPane().add(textData);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSexo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSexo.setBounds(396, 25, 58, 21);
		getContentPane().add(lblSexo);
		
		comboSexo = new JComboBox();
		comboSexo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					textQtd.requestFocus();
				}
			}
		});
		comboSexo.setModel(new DefaultComboBoxModel(new String[] {"M", "F"}));
		comboSexo.setBounds(460, 25, 51, 21);
		getContentPane().add(comboSexo);
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
	Animais animais=null;
	public  Animais a(){
		if(animais==null){
			animais = new Animais();
		}
		return animais;
	}
	public void gravar(){
		String prod = comboProd.getSelectedItem().toString();
		String sexo = comboSexo.getSelectedItem().toString();
		int qtd = AN.stringPInt(textQtd.getText());
		String tipo = comboTipo.getSelectedItem().toString();
		if(qtd>0){
			if(!tipo.equals("Entrada")){
				qtd = qtd*-1;
			}
			String dataMov = AN.dataPMySQL(textData.getText());
			if(AN.jOptionPaneQuestion("Deseja Realmente Incluir no Estoque?")==0){
				boolean a = a().incluirEstoque(prod, qtd, codCli, dataMov, sexo);
				if(a==true){
					Estoque.atualizaTable();
					AN.jOptionPaneInformation("Produto Incluso no Estoque com Sucesso!");
				}
			}
		}else{
			AN.jOptionPaneInformation("Informe uma Quantidade!");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
