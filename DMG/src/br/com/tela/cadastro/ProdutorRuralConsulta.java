package br.com.tela.cadastro;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import org.apache.commons.lang.StringUtils;

import br.com.entity.PessoaJuridica;
import br.com.entity.ProdutorRural;
import br.com.exception.Excecoes;
import br.com.persistencia.PessoaJuridicaDao;
import br.com.persistencia.ProdutorRuralDao;
import br.com.tablemodel.TMlistaProdutorRural;
import br.com.util.Mensagem;
import br.com.util.Utils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ProdutorRuralConsulta extends JInternalFrame {
	private JTable tbProdutor;
	public static ProdutorRuralCad produtorRuralCad;
	private static JPanel panelBotoes;
	private JTextField txtCodigo;
	private JTextField txtNomePropriedade;
	private JTextField txtNomeFantasia;
	private JTextField txtRazaoSocial;
	private JTextField txtCnpj;
	private JTextField txtInscricaoEstadual;
	private JTextField txtCodigoPropriedade;
	
	private PessoaJuridica pessoaJuridica;
	private ProdutorRural produtorRural;
	
	private PessoaJuridicaDao pessoaJuridicaDao;
	private ProdutorRuralDao produtorRuralDao;
	
	private List<ProdutorRural> listaProdutorRural;
	
	private int produtorSelecionado;

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
		iniacializaComponentes();
		setLayout();
		eventos();
	}
	
	private void eventos() {
		tbProdutor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
            	produtorSelecionado = tbProdutor.getSelectedRow();
            	if (produtorSelecionado >= 0){
            		setProdutorRural(new ProdutorRural());
            		setProdutorRural(((TMlistaProdutorRural) tbProdutor.getModel()).getObj(produtorSelecionado));
            	}
            }
        });
		
	}

	private void setLayout() {
		tbProdutor.setModel(new TMlistaProdutorRural());
		tbProdutor.setBackground(Color.WHITE);
        Utils.configuracaoJTable(tbProdutor);
        //"Código", "Cód. Propriedade", "Cnpj", "Razão Social", "Nome Propriedade"
        Utils.tamanhoJTable(tbProdutor, 20, 20, 50, 200, 200);
        tbProdutor.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 12));
        tbProdutor.setFont(new Font("Dialog", Font.BOLD, 12));
        tbProdutor.setGridColor(new Color(153,153,153));
        tbProdutor.setShowGrid(true);
        tbProdutor.setShowHorizontalLines(true);
        tbProdutor.setShowVerticalLines(true);
	}

	private void iniacializaComponentes() {
		setBounds(100, 100, 626, 437);
		getContentPane().setLayout(null);
		
		tbProdutor = new JTable();
		tbProdutor.setBounds(22, 133, 564, 183);
		
		JScrollPane spProdutor = new JScrollPane();
		spProdutor.setBounds(10, 162, 590, 183);
		getContentPane().add(spProdutor);
		spProdutor.setViewportView(tbProdutor);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
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
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterar();
			}
		});
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
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(59, 97, 92, 20);
		getContentPane().add(txtCodigo);
		
		JLabel label = new JLabel("Nome Propriedade :");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(161, 100, 126, 14);
		getContentPane().add(label);
		
		txtNomePropriedade = new JTextField();
		txtNomePropriedade.setColumns(10);
		txtNomePropriedade.setBounds(276, 97, 324, 20);
		getContentPane().add(txtNomePropriedade);
		
		JLabel label_1 = new JLabel("C\u00F3digo :");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(10, 100, 52, 14);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Nome Fantasia :");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setBounds(10, 69, 92, 14);
		getContentPane().add(label_2);
		
		txtNomeFantasia = new JTextField();
		txtNomeFantasia.setColumns(10);
		txtNomeFantasia.setBounds(102, 66, 498, 20);
		getContentPane().add(txtNomeFantasia);
		
		txtRazaoSocial = new JTextField();
		txtRazaoSocial.setColumns(10);
		txtRazaoSocial.setBounds(102, 38, 498, 20);
		getContentPane().add(txtRazaoSocial);
		
		JLabel label_3 = new JLabel("Raz\u00E3o Social :");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(10, 40, 92, 14);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("CNPJ :");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_4.setBounds(10, 14, 40, 14);
		getContentPane().add(label_4);
		
		txtCnpj = new JTextField();
		txtCnpj.setColumns(10);
		txtCnpj.setBounds(60, 11, 140, 20);
		getContentPane().add(txtCnpj);
		
		JLabel label_5 = new JLabel("IE :");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_5.setBounds(210, 14, 26, 14);
		getContentPane().add(label_5);
		
		txtInscricaoEstadual = new JTextField();
		txtInscricaoEstadual.setColumns(10);
		txtInscricaoEstadual.setBounds(241, 11, 126, 20);
		getContentPane().add(txtInscricaoEstadual);
		
		JLabel label_6 = new JLabel("C\u00F3digo Propriedade :");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_6.setBounds(377, 14, 116, 14);
		getContentPane().add(label_6);
		
		txtCodigoPropriedade = new JTextField();
		txtCodigoPropriedade.setColumns(10);
		txtCodigoPropriedade.setBounds(499, 11, 101, 20);
		getContentPane().add(txtCodigoPropriedade);
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluir();
			}
		});
	}
	
	private void alterar() {
		if(getProdutorRural().getId() != null && produtorSelecionado >= 0){
			produtorRuralCad = new ProdutorRuralCad(getProdutorRural());
			Inicio.addTela(produtorRuralCad);
			produtorRuralCad.setVisible(true);
			try{
				produtorRuralCad.setSelected(true);
			}catch(Exception ex){}		
			setPanelBotoes(false);
		}else{
			Mensagem.informacao("Nenhum produtor rural foi selecionado");
		}
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
	
	private void pesquisar() {
		((TMlistaProdutorRural) tbProdutor.getModel()).limpar();
		
		setPessoaJuridica(new PessoaJuridica());
		setProdutorRural(new ProdutorRural());
		
		if(StringUtils.isNotBlank(txtCnpj.getText())){
			getPessoaJuridica().setCnpj(Utils.formataCpfCnpj(txtCnpj.getText().replaceAll("\\D", "")));
		}
		if(StringUtils.isNotBlank(txtInscricaoEstadual.getText())){
			getPessoaJuridica().setInscricaoEstadual(txtInscricaoEstadual.getText().replaceAll("\\D", ""));
		}
		if(StringUtils.isNotBlank(txtCodigoPropriedade.getText())){
			getProdutorRural().setCodigoPropriedade(txtCodigoPropriedade.getText().replaceAll("\\D", ""));
		}
		if(StringUtils.isNotBlank(txtRazaoSocial.getText())){
			getPessoaJuridica().setRazaoSocial(txtRazaoSocial.getText());
		}
		if(StringUtils.isNotBlank(txtNomeFantasia.getText())){
			getPessoaJuridica().setNomeFantasia(txtNomeFantasia.getText());
		}
		if(StringUtils.isNotBlank(txtCodigo.getText())){
			getProdutorRural().setCodigo(txtCodigo.getText());
		}
		if(StringUtils.isNotBlank(txtNomePropriedade.getText())){
			getProdutorRural().setNomePropriedade(txtNomePropriedade.getText());
		}
		try {
			setListaProdutorRural(getProdutorRuralDao().getListaProdutoRural(getProdutorRural(), getPessoaJuridica()));
			if(getListaProdutorRural().isEmpty()){
				Mensagem.informacao("Nenhum produtor rural foi encontrado!");
			}else{
				((TMlistaProdutorRural) tbProdutor.getModel()).adicionarLista(getListaProdutorRural());
			}
		} catch (Excecoes e) {
			Mensagem.erro(e.getMessage());
		}
	}
	
	public PessoaJuridica getPessoaJuridica() {
		if(pessoaJuridica == null){
			pessoaJuridica = new PessoaJuridica();
			setPessoaJuridica(new PessoaJuridica());
		}
		return pessoaJuridica;
	}
	
	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}
	
	public ProdutorRural getProdutorRural() {
		if(produtorRural == null){
			produtorRural = new ProdutorRural();
		}
		return produtorRural;
	}
	
	public void setProdutorRural(ProdutorRural produtorRural) {
		this.produtorRural = produtorRural;
	}
	
	public PessoaJuridicaDao getPessoaJuridicaDao() {
		if(pessoaJuridicaDao == null){
			pessoaJuridicaDao = new PessoaJuridicaDao();
			setPessoaJuridicaDao(new PessoaJuridicaDao());
		}
		return pessoaJuridicaDao;
	}
	
	public void setPessoaJuridicaDao(PessoaJuridicaDao pessoaJuridicaDao) {
		this.pessoaJuridicaDao = pessoaJuridicaDao;
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
	
	public List<ProdutorRural> getListaProdutorRural(){
		if(listaProdutorRural == null){
			listaProdutorRural = new ArrayList<ProdutorRural>();
		}
		return listaProdutorRural;
	}
	
	public List<ProdutorRural> setListaProdutorRural(List<ProdutorRural> listaProdutorRural){
		return this.listaProdutorRural = listaProdutorRural;
	}
}
