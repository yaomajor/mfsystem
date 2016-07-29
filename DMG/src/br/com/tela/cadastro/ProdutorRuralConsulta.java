package br.com.tela.cadastro;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import org.apache.commons.lang.StringUtils;

import br.com.entity.Endereco;
import br.com.entity.PessoaJuridica;
import br.com.entity.ProdutorRural;
import br.com.entity.Telefone;
import br.com.exception.Excecoes;
import br.com.persistencia.EnderecoDao;
import br.com.persistencia.PessoaJuridicaDao;
import br.com.persistencia.ProdutorRuralDao;
import br.com.persistencia.TelefoneDao;
import br.com.tablemodel.TMlistaProdutorRural;
import br.com.util.Constantes;
import br.com.util.Mensagem;
import br.com.util.Utils;
import br.com.vo.ProdutorRuralVO;
import br.com.vo.TelefoneVO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.BevelBorder;

public class ProdutorRuralConsulta extends JInternalFrame {
	private static JTable tbProdutor;
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
	private Endereco endereco;
	private Telefone telefone;
	
	private PessoaJuridicaDao pessoaJuridicaDao;
	private ProdutorRuralDao produtorRuralDao;
	private TelefoneDao telefoneDao;
	private EnderecoDao enderecoDao;
	
	private List<ProdutorRural> listaProdutorRural;
	private List<Telefone> listaTelefone;
	
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
		setTitle("Consulta Produtor Rural");
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
        Utils.tamanhoJTable(tbProdutor, 30, 40, 80, 180, 180);
        tbProdutor.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 12));
        tbProdutor.setFont(new Font("Dialog", Font.BOLD, 12));
        tbProdutor.setGridColor(new Color(153,153,153));
        tbProdutor.setShowGrid(true);
        tbProdutor.setShowHorizontalLines(true);
        tbProdutor.setShowVerticalLines(true);
	}

	private void iniacializaComponentes() {
		
		setBounds(100, 100, 730, 500);
		getContentPane().setLayout(null);
		setIconifiable(true);
		
		tbProdutor = new JTable();
		tbProdutor.setBounds(22, 133, 564, 183);
		
		JScrollPane spProdutor = new JScrollPane();
		spProdutor.setBounds(10, 183, 694, 236);
		getContentPane().add(spProdutor);
		spProdutor.setViewportView(tbProdutor);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		btnPesquisar.setIcon(new ImageIcon(CadastroUsuario.class.getResource("/image/lupaNo02.png")));
		btnPesquisar.setBounds(10, 142, 135, 32);
		getContentPane().add(btnPesquisar);
		
		panelBotoes = new JPanel();
		panelBotoes.setBounds(10, 423, 590, 40);
		getContentPane().add(panelBotoes);
		panelBotoes.setLayout(null);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.setIcon(new ImageIcon(CadastroUsuario.class.getResource("/image/icLanc.png")));
		btnIncluir.setBounds(10, 5, 135, 32);
		panelBotoes.add(btnIncluir);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterar();
			}
		});
		btnAlterar.setBounds(155, 5, 135, 32);
		btnAlterar.setIcon(new ImageIcon(CadastroUsuario.class.getResource("/image/icAlt.png")));
		panelBotoes.add(btnAlterar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();
			}
		});
		btnExcluir.setBounds(300, 5, 135, 32);
		btnExcluir.setIcon(new ImageIcon(CadastroUsuario.class.getResource("/image/icExcluir.png")));
		panelBotoes.add(btnExcluir);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		btnFechar.setBounds(445, 5, 135, 32);
		btnFechar.setIcon(new ImageIcon(CadastroUsuario.class.getResource("/image/icSair.png")));
		panelBotoes.add(btnFechar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 6, 694, 127);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(99, 97, 92, 20);
		panel.add(txtCodigo);
		
		JLabel label = new JLabel("Nome Propriedade :");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(201, 100, 126, 14);
		panel.add(label);
		
		txtNomePropriedade = new JTextField();
		txtNomePropriedade.setColumns(10);
		txtNomePropriedade.setBounds(316, 97, 324, 20);
		panel.add(txtNomePropriedade);
		
		JLabel label_1 = new JLabel("C\u00F3digo :");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(50, 100, 52, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Nome Fantasia :");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setBounds(50, 69, 92, 14);
		panel.add(label_2);
		
		txtNomeFantasia = new JTextField();
		txtNomeFantasia.setColumns(10);
		txtNomeFantasia.setBounds(142, 66, 498, 20);
		panel.add(txtNomeFantasia);
		
		txtRazaoSocial = new JTextField();
		txtRazaoSocial.setColumns(10);
		txtRazaoSocial.setBounds(142, 38, 498, 20);
		panel.add(txtRazaoSocial);
		
		JLabel label_3 = new JLabel("Raz\u00E3o Social :");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(50, 40, 92, 14);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("CNPJ :");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_4.setBounds(50, 14, 40, 14);
		panel.add(label_4);
		
		txtCnpj = new JTextField();
		txtCnpj.setColumns(10);
		txtCnpj.setBounds(100, 11, 140, 20);
		panel.add(txtCnpj);
		
		JLabel label_5 = new JLabel("IE :");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_5.setBounds(250, 14, 26, 14);
		panel.add(label_5);
		
		txtInscricaoEstadual = new JTextField();
		txtInscricaoEstadual.setColumns(10);
		txtInscricaoEstadual.setBounds(281, 11, 126, 20);
		panel.add(txtInscricaoEstadual);
		
		JLabel label_6 = new JLabel("C\u00F3digo Propriedade :");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_6.setBounds(417, 14, 116, 14);
		panel.add(label_6);
		
		txtCodigoPropriedade = new JTextField();
		txtCodigoPropriedade.setColumns(10);
		txtCodigoPropriedade.setBounds(539, 11, 101, 20);
		panel.add(txtCodigoPropriedade);
		
		JButton btnRelatorioResumido = new JButton("Relat\u00F3rio Resumido");
		btnRelatorioResumido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gerarRelatorio("resumido");
			}
		});
		btnRelatorioResumido.setBounds(161, 140, 156, 32);
		getContentPane().add(btnRelatorioResumido);
		
		JButton btnRelatorioCompleto = new JButton("Relat\u00F3rio Completo");
		btnRelatorioCompleto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gerarRelatorio("completo");
			}
		});
		btnRelatorioCompleto.setBounds(327, 140, 158, 32);
		getContentPane().add(btnRelatorioCompleto);
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
		if(a){
			((TMlistaProdutorRural) tbProdutor.getModel()).limpar();
		}
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
	
	private void excluir() {
		if(produtorSelecionado >= 0){
			boolean isExcluso = true;
			int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir?", "Produtor Rural", JOptionPane.YES_NO_OPTION);
			if (resposta == JOptionPane.YES_OPTION) {
				try {
					getProdutorRuralDao().excluir(getProdutorRural().getId());
				} catch (Excecoes e) {
					isExcluso = false;
					Mensagem.erro("Não foi possível exclir o Produtor Rural!");
				}
				if(isExcluso)
					((TMlistaProdutorRural) tbProdutor.getModel()).excluir(produtorSelecionado);
			}
		}
		
	}
	private void gerarRelatorio(String tipoRelatorio) {
		if(Constantes.pessoaJuridica.getId() != null){
			setListaProdutorRural(((TMlistaProdutorRural) tbProdutor.getModel()).getLista());
			if(getListaProdutorRural().isEmpty()){
				Mensagem.aviso("Faça uma pesquisa antes de gerar o relatório!");
			}else{
				List<ProdutorRuralVO> listaVO = new ArrayList<ProdutorRuralVO>();
				for(ProdutorRural produtorRural : getListaProdutorRural()){
					ProdutorRuralVO produtorRuralVO = new ProdutorRuralVO();
					if(StringUtils.isNotBlank(produtorRural.getCodigo())){
						produtorRuralVO.setCodigo(produtorRural.getCodigo());
					}
					produtorRuralVO.setRazaoSocial(produtorRural.getPessoaJuridica().getRazaoSocial());
					produtorRuralVO.setCnpj(produtorRural.getPessoaJuridica().getCnpj().replaceAll("\\D", ""));
					produtorRuralVO.setIe(produtorRural.getPessoaJuridica().getInscricaoEstadual().replaceAll("\\D", ""));
					produtorRuralVO.setCodigoPropriedade(produtorRural.getCodigoPropriedade());
					produtorRuralVO.setNomePropriedade(produtorRural.getNomePropriedade());
					produtorRuralVO.setDataCadastro(Utils.getDataHora(produtorRural.getPessoaJuridica().getPessoa().getDataCadastro().getTime()));
					produtorRuralVO.setNomeFantasia(produtorRural.getPessoaJuridica().getNomeFantasia());
					
					try {
						setListaTelefone(getTelefoneDao().getTelefonePorPessoa(produtorRural.getPessoaJuridica().getPessoa()));
					} catch (Excecoes e) {
						Mensagem.erro("Não foi possível obter Telefone!!");
					}
					
					List<TelefoneVO> lista = new ArrayList<TelefoneVO>();
					for(Telefone tel : getListaTelefone()){
						TelefoneVO telefoneVO = new TelefoneVO();
						telefoneVO.setContato(tel.getContato());
						telefoneVO.setDdd(tel.getDdd());
						telefoneVO.setNumero(tel.getNumero());
						lista.add(telefoneVO);
					}
					produtorRuralVO.setListaTelefone(lista);
					
					try {
						setEndereco(getEnderecoDao().getEnderecoPorPessoa(produtorRural.getPessoaJuridica().getPessoa()));
					} catch (Excecoes e) {
						Mensagem.erro("Não foi possível obter Endereço!!");
					}
					
					if(StringUtils.isNotBlank(getEndereco().getCep())){
						produtorRuralVO.setCep(getEndereco().getCep());
					}
					
					if(StringUtils.isNotBlank(getEndereco().getUf())){
						produtorRuralVO.setUf(getEndereco().getUf());
					}
					
					if(StringUtils.isNotBlank(getEndereco().getCidade())){
						produtorRuralVO.setCidade(getEndereco().getCidade());
					}
					
					if(StringUtils.isNotBlank(getEndereco().getLogradouro())){
						produtorRuralVO.setLogradouro(getEndereco().getLogradouro());
					}
					
					if(StringUtils.isNotBlank(getEndereco().getComplemento())){
						produtorRuralVO.setComplemento(getEndereco().getComplemento());
					}
					
					if(StringUtils.isNotBlank(getEndereco().getBairro())){
						produtorRuralVO.setBairro(getEndereco().getBairro());
					}
					
					if(StringUtils.isNotBlank(getEndereco().getNumero())){
						produtorRuralVO.setNumero(getEndereco().getNumero());
					}
					
					listaVO.add(produtorRuralVO);
				}
				
				String jasper = "";
				// local do relatorio jasper
				if(tipoRelatorio.equals("resumido")){
					jasper = "ireport/relProdutorRuralResumido.jasper";
				}else{
					jasper = "ireport/relProdutorRuralCompleto.jasper";
				}
	            
	            // parametros
	            Map<String, Object> param = new HashMap<>();
	            param.put("empresa", Constantes.pessoaJuridica.getRazaoSocial());
	            param.put("cidade", Constantes.endereco.getCidade());
	            param.put("REPORT_LOCALE", Locale.getDefault());
	            try {
	            	if(tipoRelatorio.equals("resumido")){
	            		File caminhoSubreport = new File("ireport/relProdutorRuralResumidoSubReport.jasper");
						param.put("SUBREPORT_DIR", caminhoSubreport.getCanonicalPath());
	            	}else{
	            		File caminhoSubreport = new File("ireport/relProdutorRuraCompletoSubReport.jasper");
						param.put("SUBREPORT_DIR", caminhoSubreport.getCanonicalPath());
	            	}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            
	            // gerando o relatorio
				try {
					JasperPrint print = JasperFillManager.fillReport(jasper, param, new JRBeanCollectionDataSource(listaVO));
					JasperViewer jv = new JasperViewer(print, false);  
		            jv.setTitle("Relatório");    
		            jv.setVisible(true); 
				} catch (JRException e) {
					e.printStackTrace();
					Mensagem.erro("Não foi possível gerar o relatório!!");
				}
			}
		}else{
			Mensagem.aviso("Carregue a empresa na tela principal!");
		}
	}
	
	//gets e sets
	
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
	
	public TelefoneDao getTelefoneDao() {
		if(telefoneDao == null){
			telefoneDao = new TelefoneDao();
			setTelefoneDao(new TelefoneDao());
		}
		return telefoneDao;
	}
	
	public void setTelefoneDao(TelefoneDao telefoneDao) {
		this.telefoneDao = telefoneDao;
	}
	
	public List<Telefone> getListaTelefone(){
		if(listaTelefone == null){
			listaTelefone = new ArrayList<Telefone>();
		}
		return listaTelefone;
	}
	
	public List<Telefone> setListaTelefone(List<Telefone> listaTelefone){
		return this.listaTelefone = listaTelefone;
	}
	
	public EnderecoDao getEnderecoDao() {
		if(enderecoDao == null){
			enderecoDao = new EnderecoDao();
			setEnderecoDao(new EnderecoDao());
		}
		return enderecoDao;
	}
	
	public void setEnderecoDao(EnderecoDao enderecoDao) {
		this.enderecoDao = enderecoDao;
	}
	
	public Endereco getEndereco() {
		if(endereco == null){
			endereco = new Endereco();
			setEndereco(new Endereco());
		}
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Telefone getTelefone() {
		if(telefone == null){
			telefone = new Telefone();
			setTelefone(new Telefone());
		}
		return telefone;
	}
	
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
}
