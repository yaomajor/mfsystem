package br.com.tela.cadastro;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.entity.ProdutorRural;
import br.com.exception.Excecoes;
import br.com.persistencia.ProdutorRuralDao;
import br.com.util.Mensagem;

import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.ComponentOrientation;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class Inicio extends JFrame {

	public static ProdutorRuralConsulta produtorRuralConsulta;
	
	private static JButton btnProdutor;
	
	private ProdutorRural produtorRural;
	
	private ProdutorRuralDao produtorRuralDao;
	
	private List<ProdutorRural> listaProdutorRural;
	
	public static void addTela(Component a) {
		desktopPane.add(a);
	}

	public static void limparTela(Component a) {
		desktopPane.remove(a);
		desktopPane.repaint();
	}
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	static JButton btnNotaFiscal;
	static JButton btnUsurio;
	static JButton btnEstoque;
	
	/**
	 * Create the frame.
	 */
	static JDesktopPane desktopPane;
	public Inicio() {
		
		setTitle("DMG");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);// Inicializa a Tela
		// Maximizada
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1052, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		//		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		this.setContentPane(desktopPane);
		desktopPane.setLayout(null);
		
		btnProdutor = new JButton("Produtor");
		btnProdutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				produtorRural();
			}
		});
		btnProdutor.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnProdutor.setHorizontalTextPosition(SwingConstants.CENTER);
		btnProdutor.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnProdutor.setIcon(new ImageIcon(Inicio.class.getResource("/image/icCliente.png")));
		btnProdutor.setBounds(15, 179, 127, 104);
		desktopPane.add(btnProdutor);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCliente.setForeground(new Color(0, 0, 0));
		lblCliente.setBounds(370, 20, 71, 21);
		desktopPane.add(lblCliente);
		
		JLabel label = new JLabel("");
		label.setOpaque(true);
		label.setBackground(new Color(144, 238, 144));
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(445, 20, 481, 21);
		desktopPane.add(label);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setIcon(new ImageIcon(Inicio.class.getResource("/image/icSetar.png")));
		btnNewButton.setBounds(928, 19, 26, 24);
		desktopPane.add(btnNewButton);
		
		JLabel label_1 = new JLabel();
		label_1.setIcon(new ImageIcon(Inicio.class.getResource("/image/usuarioPQ.png")));
		label_1.setText("");
		label_1.setBounds(new Rectangle(1, 1, 28, 29));
		label_1.setBounds(10, 11, 35, 40);
		desktopPane.add(label_1);
		
		JLabel label_2 = new JLabel();
		label_2.setText(" USU\u00C1RIO LOGADO:");
		label_2.setSize(new Dimension(143, 23));
		label_2.setLocation(new Point(29, 4));
		label_2.setFont(new Font("Dialog", Font.BOLD, 14));
		label_2.setBounds(43, 20, 143, 23);
		desktopPane.add(label_2);
		
		JLabel label_3 = new JLabel();
		label_3.setText("");
		label_3.setSize(new Dimension(187, 23));
		label_3.setLocation(new Point(172, 4));
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Dialog", Font.BOLD, 17));
		label_3.setBounds(186, 20, 187, 23);
		desktopPane.add(label_3);
		
		JLabel label_4 = new JLabel();
		label_4.setVerticalTextPosition(SwingConstants.TOP);
		label_4.setVerticalAlignment(SwingConstants.CENTER);
		label_4.setText("");
		label_4.setPreferredSize(new Dimension(358, 19));
		label_4.setHorizontalTextPosition(SwingConstants.RIGHT);
		label_4.setHorizontalAlignment(SwingConstants.LEADING);
		label_4.setForeground(new Color(0, 153, 0));
		label_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_4.setComponentOrientation(ComponentOrientation.UNKNOWN);
		label_4.setBounds(new Rectangle(1, 30, 358, 19));
		label_4.setBounds(15, 46, 602, 23);
		desktopPane.add(label_4);
		
		JLabel label_5 = new JLabel();
		label_5.setText("IE.:");
		label_5.setPreferredSize(new Dimension(202, 19));
		label_5.setForeground(new Color(0, 153, 0));
		label_5.setBounds(new Rectangle(180, 49, 21, 19));
		label_5.setBounds(194, 69, 21, 19);
		desktopPane.add(label_5);
		
		JLabel label_6 = new JLabel();
		label_6.setText("");
		label_6.setSize(new Dimension(129, 19));
		label_6.setPreferredSize(new Dimension(156, 19));
		label_6.setLocation(new Point(42, 49));
		label_6.setForeground(new Color(0, 153, 0));
		label_6.setBounds(56, 69, 129, 19);
		desktopPane.add(label_6);
		
		JLabel label_7 = new JLabel();
		label_7.setText("CNPJ.:");
		label_7.setPreferredSize(new Dimension(156, 19));
		label_7.setForeground(new Color(0, 153, 0));
		label_7.setBounds(new Rectangle(1, 49, 42, 19));
		label_7.setBounds(15, 69, 42, 19);
		desktopPane.add(label_7);
		
		JLabel label_8 = new JLabel();
		label_8.setVerticalTextPosition(SwingConstants.TOP);
		label_8.setVerticalAlignment(SwingConstants.CENTER);
		label_8.setText(", N\u00BA - ");
		label_8.setPreferredSize(new Dimension(358, 19));
		label_8.setHorizontalTextPosition(SwingConstants.RIGHT);
		label_8.setHorizontalAlignment(SwingConstants.LEADING);
		label_8.setForeground(new Color(0, 153, 0));
		label_8.setComponentOrientation(ComponentOrientation.UNKNOWN);
		label_8.setBounds(new Rectangle(1, 68, 443, 19));
		label_8.setBounds(15, 88, 443, 19);
		desktopPane.add(label_8);
		
		JLabel label_9 = new JLabel();
		label_9.setText("");
		label_9.setSize(new Dimension(158, 19));
		label_9.setPreferredSize(new Dimension(202, 19));
		label_9.setLocation(new Point(201, 49));
		label_9.setForeground(new Color(0, 153, 0));
		label_9.setBounds(215, 69, 225, 19);
		desktopPane.add(label_9);
		
		JLabel label_10 = new JLabel();
		label_10.setText("/ - CEP: ");
		label_10.setSize(new Dimension(444, 20));
		label_10.setLocation(new Point(1, 87));
		label_10.setForeground(new Color(0, 153, 0));
		label_10.setBounds(15, 107, 444, 20);
		desktopPane.add(label_10);
		
		btnNotaFiscal = new JButton("Nota Fiscal");
		btnNotaFiscal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				movNotaFiscal();
			}
		});
		btnNotaFiscal.setIcon(new ImageIcon(Inicio.class.getResource("/image/icNf2.png")));
		btnNotaFiscal.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNotaFiscal.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNotaFiscal.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNotaFiscal.setBounds(289, 179, 127, 104);
		desktopPane.add(btnNotaFiscal);
		
		btnUsurio = new JButton("Usu\u00E1rio");
		btnUsurio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				usuario();
			}
		});
		btnUsurio.setIcon(new ImageIcon(Inicio.class.getResource("/image/icUsuario2.png")));
		btnUsurio.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnUsurio.setHorizontalTextPosition(SwingConstants.CENTER);
		btnUsurio.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnUsurio.setBounds(152, 179, 127, 104);
		desktopPane.add(btnUsurio);
		
		btnEstoque = new JButton("Estoque");
		btnEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				estoque();
			}
		});
		btnEstoque.setIcon(new ImageIcon(Inicio.class.getResource("/image/icBoi.png")));
		btnEstoque.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEstoque.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEstoque.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEstoque.setBounds(426, 179, 127, 104);
		desktopPane.add(btnEstoque);
		
		JLabel label_11 = new JLabel("");
		label_11.setIcon(new ImageIcon(Inicio.class.getResource("/image/dmg.png")));
		label_11.setBounds(617, 170, 269, 113);
		desktopPane.add(label_11);
		
		try {
			setListaProdutorRural(getProdutorRuralDao().getListaProdutoRuralContabilidade());
		} catch (Excecoes e1) {
			Mensagem.erro("Erro ao buscar produtor rural cliente contabil!");
		}
	}
	//
	//
	//
	static MovNotaFiscal movNotaFiscal=null;
	public void movNotaFiscal(){
		movNotaFiscal = new MovNotaFiscal();
		addTela(movNotaFiscal);
		movNotaFiscal.setVisible(true);
		try{
			movNotaFiscal.setSelected(true);
		}catch(Exception e){}		
		setBotaoMovNotaFiscal(false);
	}
	public static void setBotaoMovNotaFiscal(boolean a){
		btnNotaFiscal.setEnabled(a);	
	}
	//
	static CadastroUsuario cadUsuario=null;
	//
	public void usuario(){
		cadUsuario = new CadastroUsuario();
		addTela(cadUsuario);
		cadUsuario.setVisible(true);
		try{
			cadUsuario.setSelected(true);
		}catch(Exception e){}		
		setBotaoCadUsuario(false);
	}
	public static void setBotaoCadUsuario(boolean a){
		btnUsurio.setEnabled(a);	
	}
	//
	static Estoque estoque;
	public static void estoque(){
		estoque = new Estoque();
		addTela(estoque);
		estoque.setVisible(true);
		try{
			estoque.setSelected(true);
		}catch(Exception e){}
		setBotaoEstoque(false);
	}
	public static void setBotaoEstoque(boolean a){
		btnEstoque.setEnabled(a);	
	}
	
	private void produtorRural() {
		produtorRuralConsulta = new ProdutorRuralConsulta();
		addTela(produtorRuralConsulta);
		produtorRuralConsulta.setVisible(true);
		try{
			produtorRuralConsulta.setSelected(true);
		}catch(Exception e){}
		setBotaoProdutorRural(false);
	}
	
	public static void setBotaoProdutorRural(boolean a){
		btnProdutor.setEnabled(a);	
	}
	
	// gets e sets
	
	public ProdutorRural getProdutorRural() {
		if(produtorRural == null){
			produtorRural = new ProdutorRural();
		}
		return produtorRural;
	}
	
	public void setProdutorRural(ProdutorRural produtorRural) {
		this.produtorRural = produtorRural;
	}
	
	public List<ProdutorRural> getListaProdutorRural(){
		if(listaProdutorRural == null){
			listaProdutorRural = new ArrayList<ProdutorRural>();
		}
		return listaProdutorRural;
	}
	
	public List<ProdutorRural> setListaProdutorRural(List<ProdutorRural> listaProdutorRural){
		return this.listaProdutorRural = listaProdutorRural;
	}
	
	public ProdutorRuralDao getProdutorRuralDao() {
		if(produtorRuralDao == null){
			produtorRuralDao = new ProdutorRuralDao();
			setProdutorRuralDao(new ProdutorRuralDao());
		}
		return produtorRuralDao;
	}
	
	public void setProdutorRuralDao(ProdutorRuralDao produtorRuralDao) {
		this.produtorRuralDao = produtorRuralDao;
	}
}
