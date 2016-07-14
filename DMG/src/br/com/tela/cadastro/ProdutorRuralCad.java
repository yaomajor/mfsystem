package br.com.tela.cadastro;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.commons.lang.StringUtils;

import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;

import br.com.entity.Cidades;
import br.com.entity.Endereco;
import br.com.entity.Pessoa;
import br.com.entity.PessoaJuridica;
import br.com.entity.ProdutorRural;
import br.com.entity.Telefone;
import br.com.entity.Uf;
import br.com.exception.Excecoes;
import br.com.persistencia.CidadesDao;
import br.com.persistencia.EnderecoDao;
import br.com.persistencia.PessoaDao;
import br.com.persistencia.PessoaJuridicaDao;
import br.com.persistencia.ProdutorRuralDao;
import br.com.persistencia.TelefoneDao;
import br.com.persistencia.UfDao;
import br.com.tablemodel.TMlistaTelefone;
import br.com.util.JTextCNPJ;
import br.com.util.JTextCep;
import br.com.util.JTextFieldFocu;
import br.com.util.JTextTel;
import br.com.util.Mensagem;
import br.com.util.TextFieldLimit;
import br.com.util.Utils;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ProdutorRuralCad extends JInternalFrame {
	
	private JTextField txtNomePropriedade;
	private JTextField txtCodigo;
	private JTextField txtCnpj;
	private JTextField txtInscricaoEstadual;
	private JTextField txtCodigoPropriedade;
	private JTextField txtTelefone;
	private JTextField txtDataCadastro;
	private JTextField txtRazaoSocial;
	private JTextField txtNomeFantasia;
	private JTextField txtCep;
	private JTextField txtLogradouro;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField txtNumero;
	private JTextField txtContatoTel;
	
	private JButton btnAlterarTelefone;
	private JButton btnCancelarTelefone;
	private JButton btnIncluirTelefone;
	private JButton btnExcluirTelefone;
	
	private JComboBox cbClienteContabilidade;
	private JComboBox cbDddTelefone;
	private JComboBox cbUf;
	private JComboBox cbCidade;
	
	private ProdutorRural produtorRural;
	private Telefone telefone;
	private Endereco endereco;
	private Pessoa pessoa;
	private PessoaJuridica pessoaJuridica;
	
	private PessoaDao pessoaDao;
	private EnderecoDao enderecoDao;
	private TelefoneDao telefoneDao;
	private ProdutorRuralDao produtorRuralDao;
	private UfDao ufDao;
	private CidadesDao cidadesDao;
	private PessoaJuridicaDao pessoaJuridicaDao;
	
	boolean isAlterando = false;
	
	private List<Telefone> listaTelefone;
	private List<Uf> listaUf;
	private List<Cidades> listaCidades;
	
	private JTable tbTelefone;
	
	private int telefoneSelecionado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProdutorRuralCad frame = new ProdutorRuralCad();
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
	public ProdutorRuralCad() {
		setTitle("Cadastro Produtor Rural");
		iniacializaComponentes();
		eventos();
		setLayout();
	}
	
	public ProdutorRuralCad(ProdutorRural produtorRural) {
		setProdutorRural(produtorRural);
		iniacializaComponentes();
		eventos();
		setLayout();
		if(getProdutorRural().getId() != null){
			populaDados();
		}
	}
	
	private void populaDados() {
		((TMlistaTelefone) tbTelefone.getModel()).limpar();
		try {
			if(getProdutorRural() != null && getProdutorRural().getId() != null){
				setEndereco(getEnderecoDao().getEnderecoPorPessoa(getProdutorRural().getPessoaJuridica().getPessoa()));
			}else{
				setEndereco(getEnderecoDao().getEnderecoPorPessoa(getPessoaJuridica().getPessoa()));
			}
		} catch (Excecoes e) {
			Mensagem.erro("Erro ao buscar endereço!");
		}
		
		if(getProdutorRural() != null && getProdutorRural().getId() != null){
			setPessoaJuridica(getProdutorRural().getPessoaJuridica());
		}
		
		setPessoa(getEndereco().getPessoa());
		
		if(getProdutorRural() != null && getProdutorRural().getId() != null && getProdutorRural().getPessoaJuridica().getPessoa().getDataCadastro() != null){
			SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String data = sdf.format(getProdutorRural().getPessoaJuridica().getPessoa().getDataCadastro().getTime());
			txtDataCadastro.setText(data);
		}
		
		if(getProdutorRural() != null && getProdutorRural().getId() != null && getProdutorRural().getClienteContalidade() != null){
			if(getProdutorRural().getClienteContalidade().equals("S")){
				cbClienteContabilidade.setSelectedIndex(0);
			}else{
				cbClienteContabilidade.setSelectedIndex(1);
			}
		}
		
		if(getProdutorRural() != null && getProdutorRural().getId() != null){
			if(getProdutorRural().getCodigo() != null){
				txtCodigo.setText(getProdutorRural().getCodigo());
			}
			
			if(getProdutorRural().getCodigoPropriedade() != null){
				txtCodigoPropriedade.setText(getProdutorRural().getCodigoPropriedade());
			}
			
			if(getProdutorRural().getNomePropriedade() != null){
				txtNomePropriedade.setText(getProdutorRural().getNomePropriedade());
			}
		}
		
		if(getPessoaJuridica().getCnpj() != null){
			txtCnpj.setText(getPessoaJuridica().getCnpj());
		}
		
		if(getPessoaJuridica().getInscricaoEstadual() != null){
			txtInscricaoEstadual.setText(getPessoaJuridica().getInscricaoEstadual());
		}
		
		if(getPessoaJuridica().getRazaoSocial() != null){
			txtRazaoSocial.setText(getPessoaJuridica().getRazaoSocial());
		}
		
		if(getPessoaJuridica().getNomeFantasia() != null){
			txtNomeFantasia.setText(getPessoaJuridica().getNomeFantasia());
		}
		
		
		// telefone
		try {
			setListaTelefone(getTelefoneDao().getTelefonePorPessoa(getPessoa()));
			
		} catch (Excecoes e) {
			Mensagem.erro("Erro ao buscar telefone!");
		}
		
		((TMlistaTelefone) tbTelefone.getModel()).adicionarLista(getListaTelefone());
		
		if(getEndereco().getCep() != null){
			txtCep.setText(getEndereco().getCep());
		}
		
		if(getEndereco().getUf() != null){
			for (int i = 0; i < cbUf.getItemCount(); i++) {
				if (cbUf.getItemAt(i).equals(getEndereco().getUf())) {
					cbUf.setSelectedIndex(i);
				}
			}
		}
		
		carregaComboCidade(getEndereco().getUf());
		
		if(getEndereco().getCidade() != null){
			for (int i = 0; i < cbCidade.getItemCount(); i++) {
				if (cbCidade.getItemAt(i).equals(getEndereco().getCidade())) {
					cbCidade.setSelectedIndex(i);
				}
			}
		}
		
		if(getEndereco().getLogradouro() != null){
			txtLogradouro.setText(getEndereco().getLogradouro());
		}
		
		if(getEndereco().getComplemento() != null){
			txtComplemento.setText(getEndereco().getComplemento());
		}
		
		if(getEndereco().getBairro() != null){
			txtBairro.setText(getEndereco().getBairro());
		}
		
		if(getEndereco().getNumero() != null){
			txtNumero.setText(getEndereco().getNumero());
		}		
	}

	private void setLayout() {
		tbTelefone.setModel(new TMlistaTelefone());
		tbTelefone.setBackground(Color.WHITE);
        Utils.configuracaoJTable(tbTelefone);
        //"Forma Pagamento", "Valor"
        Utils.tamanhoJTable(tbTelefone, 185, 50);
        tbTelefone.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 12));
        tbTelefone.setFont(new Font("Dialog", Font.BOLD, 12));
        tbTelefone.setGridColor(new Color(153,153,153));
        tbTelefone.setShowGrid(true);
        tbTelefone.setShowHorizontalLines(true);
        tbTelefone.setShowVerticalLines(true);
        
        btnCancelarTelefone.setEnabled(false);
        btnAlterarTelefone.setEnabled(false);
        
        carregaComboUf();
	}

	private void eventos() {
		txtCnpj.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
				if(txtCnpj.getText() != null){
					getPessoaJuridica().setCnpj(Utils.formataCpfCnpj(txtCnpj.getText().replaceAll("\\D", "")));
					try {
						setProdutorRural(getProdutorRuralDao().getProdutoRural(getPessoaJuridica()));
					} catch (Excecoes e1) {
						Mensagem.erro("Erro ao buscar produtor rural!");
					}
					
					if(getProdutorRural().getId() == null){
						try {
							setPessoaJuridica(getPessoaJuridicaDao().getPessoaJuridica(getPessoaJuridica()));
						} catch (Excecoes e1) {
							Mensagem.erro("Erro ao buscar Pessoa Juridica!");
						}
					}
					
					if((getProdutorRural() != null && getProdutorRural().getId() != null) || 
							(getPessoaJuridica() != null && getPessoaJuridica().getId() != null) ){
						populaDados();
					}else{
						if(!Utils.isCNPJ(txtCnpj.getText().replaceAll("\\D", ""))){
							Mensagem.aviso("CNPJ não é valido!");
						}
					}
				}
            }
        });
		
		txtCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
            	try {
        			if(getProdutorRural().getId() == null && txtCodigo.getText() != null){
						setProdutorRural(getProdutorRuralDao().getProdutoRuralPorCodigo(txtCodigo.getText()));
						if(getProdutorRural() != null && getProdutorRural().getId() != null){
							Mensagem.informacao("Codigo : " + getProdutorRural().getCodigo()  
									+ " esta sendo utilizando pela " + getProdutorRural().getPessoaJuridica().getRazaoSocial());
						}
					}
				} catch (Excecoes e1) {
					Mensagem.erro("Erro ao buscar produtor rural!");
				}
            }
        });
		
		txtCnpj.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					txtInscricaoEstadual.requestFocus();
				}
			}
		});
		
		tbTelefone.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
            	telefoneSelecionado = tbTelefone.getSelectedRow();
            	if (telefoneSelecionado >= 0){ 
            		setTelefone(((TMlistaTelefone) tbTelefone.getModel()).getObj(telefoneSelecionado));
            		btnAlterarTelefone.setEnabled(false);
                    btnCancelarTelefone.setEnabled(true);
                    btnIncluirTelefone.setEnabled(true);
                    if(telefone.getDdd() != null){
            			for (int i = 0; i < cbDddTelefone.getItemCount(); i++) {
            				if (cbDddTelefone.getItemAt(i).equals(telefone.getDdd())) {
            					cbDddTelefone.setSelectedIndex(i);
            				}
            			}
            		}
        			txtTelefone.setText(telefone.getNumero());
        			txtContatoTel.setText(telefone.getContato());
            	}
            }
        });
		
		txtCep.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
            	ViaCEPClient client = new ViaCEPClient();
        		try {
					ViaCEPEndereco endereco = client.getEndereco(txtCep.getText());
					if(endereco != null && endereco.getCep() != null){
						
						if(endereco.getUf() != null){
							for (int i = 0; i < cbUf.getItemCount(); i++) {
								if (cbUf.getItemAt(i).equals(endereco.getUf())) {
									cbUf.setSelectedIndex(i);
								}
							}
						}
						
						carregaComboCidade(endereco.getUf());
						
						if(endereco.getLocalidade() != null){
							for (int i = 0; i < cbCidade.getItemCount(); i++) {
								if (cbCidade.getItemAt(i).equals(endereco.getLocalidade())) {
									cbCidade.setSelectedIndex(i);
								}
							}
						}
						
						txtLogradouro.setText(endereco.getLogradouro());
						if(StringUtils.isNotBlank(endereco.getComplemento())){
							txtComplemento.setText(endereco.getComplemento());
						}						
						txtBairro.setText(endereco.getBairro());
					}
				} catch (IOException e) {
					Mensagem.erro("Não foi possível obter o endereco");
				}
            }
		});
		
		cbUf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
            	carregaComboCidade(cbUf.getSelectedItem().toString());
            }
        });
	}

	private void iniacializaComponentes() {
		setBounds(100, 100, 630, 598);
		getContentPane().setLayout(null);
		setIconifiable(true);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 156, 590, 188);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblTelefone = new JLabel("Telefone :");
		lblTelefone.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTelefone.setBounds(10, 11, 64, 14);
		panel.add(lblTelefone);
		
		cbDddTelefone = new JComboBox();
		cbDddTelefone.setModel(new DefaultComboBoxModel(Utils.retDDDs()));
		cbDddTelefone.setBounds(74, 8, 45, 20);
		panel.add(cbDddTelefone);
		
		txtTelefone = new JTextTel();
		txtTelefone.setDocument(new TextFieldLimit(20, false));
		txtTelefone.setBounds(125, 8, 110, 20);
		panel.add(txtTelefone);
		txtTelefone.setColumns(10);
		
		JLabel lblContato = new JLabel("Contato :");
		lblContato.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblContato.setBounds(245, 11, 62, 14);
		panel.add(lblContato);
		
		txtContatoTel = new JTextFieldFocu();
		txtContatoTel.setDocument(new TextFieldLimit(50, false));
		txtContatoTel.setColumns(10);
		txtContatoTel.setBounds(307, 8, 273, 20);
		panel.add(txtContatoTel);
		
		JScrollPane spTelefone = new JScrollPane();
		spTelefone.setBounds(10, 75, 570, 107);
		panel.add(spTelefone);
		
		tbTelefone = new JTable();
		spTelefone.setColumnHeaderView(tbTelefone);
		
		btnIncluirTelefone = new JButton("Incluir Telefone");
		btnIncluirTelefone.setIcon(new ImageIcon(CadastroUsuario.class.getResource("/image/icLanc.png")));
		btnIncluirTelefone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirTelefone();
			}
		});
		btnIncluirTelefone.setBounds(10, 36, 135, 32);
		panel.add(btnIncluirTelefone);
		
		btnAlterarTelefone = new JButton("Alterar Telefone");
		btnAlterarTelefone.setIcon(new ImageIcon(CadastroUsuario.class.getResource("/image/icAlt.png")));
		btnAlterarTelefone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarTelefone();
			}
		});
		btnAlterarTelefone.setBounds(153, 36, 135, 32);
		panel.add(btnAlterarTelefone);
		
		JButton btnExcluirTelefone = new JButton("Excluir");
		btnExcluirTelefone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirTelefone();
			}
		});
		btnExcluirTelefone.setIcon(new ImageIcon(CadastroUsuario.class.getResource("/image/icExcluir.png")));
		btnExcluirTelefone.setBounds(443, 36, 135, 32);
		panel.add(btnExcluirTelefone);
		
		btnCancelarTelefone = new JButton("Cancelar");
		btnCancelarTelefone.setIcon(new ImageIcon(CadastroUsuario.class.getResource("/image/icCancelar.png")));
		btnCancelarTelefone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelarDadosTelefone();
			}
		});
		btnCancelarTelefone.setBounds(298, 36, 135, 32);
		panel.add(btnCancelarTelefone);
		
		txtNomePropriedade = new JTextFieldFocu();
		txtNomePropriedade.setDocument(new TextFieldLimit(100, false));
		txtNomePropriedade.setColumns(10);
		txtNomePropriedade.setBounds(276, 97, 324, 20);
		getContentPane().add(txtNomePropriedade);
		
		JLabel label = new JLabel("Nome Propriedade :");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(161, 100, 126, 14);
		getContentPane().add(label);
		
		txtCodigo = new JTextFieldFocu();
		txtCodigo.setDocument(new TextFieldLimit(20, true));
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(59, 97, 92, 20);
		getContentPane().add(txtCodigo);
		
		JLabel label_1 = new JLabel("C\u00F3digo :");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(10, 100, 52, 14);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("CNPJ :");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setBounds(10, 14, 40, 14);
		getContentPane().add(label_2);
		
		txtCnpj = new JTextCNPJ();
		txtCnpj.setDocument(new TextFieldLimit(14, false));
		txtCnpj = new JTextCNPJ();
		txtCnpj.setColumns(10);
		txtCnpj.setBounds(60, 11, 140, 20);
		getContentPane().add(txtCnpj);
		
		JLabel label_3 = new JLabel("IE :");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(210, 14, 26, 14);
		getContentPane().add(label_3);
		
		txtInscricaoEstadual = new JTextFieldFocu();
		txtInscricaoEstadual.setDocument(new TextFieldLimit(20, false));
		txtInscricaoEstadual.setColumns(10);
		txtInscricaoEstadual.setBounds(241, 11, 126, 20);
		getContentPane().add(txtInscricaoEstadual);
		
		JLabel label_4 = new JLabel("C\u00F3digo Propriedade :");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_4.setBounds(377, 14, 116, 14);
		getContentPane().add(label_4);
		
		txtCodigoPropriedade = new JTextFieldFocu();
		txtCodigoPropriedade.setDocument(new TextFieldLimit(20, true));
		txtCodigoPropriedade.setColumns(10);
		txtCodigoPropriedade.setBounds(499, 11, 101, 20);
		getContentPane().add(txtCodigoPropriedade);
		
		JLabel lblDataCadastro1 = new JLabel("Data Cadastro :");
		lblDataCadastro1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDataCadastro1.setBounds(10, 131, 92, 14);
		getContentPane().add(lblDataCadastro1);
		
		txtDataCadastro = new JTextField();
		txtDataCadastro.setEditable(false);
		txtDataCadastro.setColumns(10);
		txtDataCadastro.setBounds(102, 127, 126, 20);
		getContentPane().add(txtDataCadastro);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 518, 590, 38);
		getContentPane().add(panel_1);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(LancNota.class.getResource("/image/icSalvar2.png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});
		btnSalvar.setBounds(10, 3, 135, 32);
		panel_1.add(btnSalvar);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setIcon(new ImageIcon(CadastroUsuario.class.getResource("/image/icSair.png")));
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		btnFechar.setBounds(155, 3, 135, 32);
		panel_1.add(btnFechar);
		
		JLabel label_5 = new JLabel("Raz\u00E3o Social :");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_5.setBounds(10, 40, 92, 14);
		getContentPane().add(label_5);
		
		txtRazaoSocial = new JTextFieldFocu();
		txtRazaoSocial.setDocument(new TextFieldLimit(100, false));
		txtRazaoSocial.setColumns(10);
		txtRazaoSocial.setBounds(102, 38, 498, 20);
		getContentPane().add(txtRazaoSocial);
		
		JLabel label_6 = new JLabel("Nome Fantasia :");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_6.setBounds(10, 69, 92, 14);
		getContentPane().add(label_6);
		
		txtNomeFantasia = new JTextFieldFocu();
		txtNomePropriedade.setDocument(new TextFieldLimit(100, false));
		txtNomeFantasia.setColumns(10);
		txtNomeFantasia.setBounds(102, 66, 498, 20);
		getContentPane().add(txtNomeFantasia);
		
		JLabel lblClienteContabilidade = new JLabel("Cliente Contabilidade :");
		lblClienteContabilidade.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblClienteContabilidade.setBounds(242, 131, 134, 14);
		getContentPane().add(lblClienteContabilidade);
		
		cbClienteContabilidade = new JComboBox();
		cbClienteContabilidade.setModel(new DefaultComboBoxModel(new String[] {"Sim", "N\u00E3o"}));
		cbClienteContabilidade.setBounds(378, 127, 52, 20);
		getContentPane().add(cbClienteContabilidade);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(10, 355, 590, 154);
		getContentPane().add(panel_2);
		
		JPanel panelEndereco = new JPanel();
		panelEndereco.setBounds(5, 30, 580, 119);
		panel_2.add(panelEndereco);
		panelEndereco.setLayout(null);
		
		JLabel label_10 = new JLabel("UF :");
		label_10.setBounds(5, 14, 26, 14);
		panelEndereco.add(label_10);
		label_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		cbUf = new JComboBox();
		cbUf.setBounds(38, 11, 42, 20);
		panelEndereco.add(cbUf);
		
		JLabel label_11 = new JLabel("Cidade :");
		label_11.setBounds(101, 14, 52, 14);
		panelEndereco.add(label_11);
		label_11.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		cbCidade = new JComboBox();
		cbCidade.setBounds(151, 11, 424, 20);
		panelEndereco.add(cbCidade);
		
		JLabel label_12 = new JLabel("Logradouro :");
		label_12.setBounds(5, 44, 75, 14);
		panelEndereco.add(label_12);
		label_12.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		txtLogradouro = new JTextFieldFocu();
		txtLogradouro.setDocument(new TextFieldLimit(100, false));
		txtLogradouro.setBounds(93, 41, 482, 20);
		panelEndereco.add(txtLogradouro);
		txtLogradouro.setColumns(10);
		
		JLabel label_13 = new JLabel("Complemento :");
		label_13.setBounds(5, 72, 85, 14);
		panelEndereco.add(label_13);
		label_13.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		txtComplemento = new JTextFieldFocu();
		txtComplemento.setDocument(new TextFieldLimit(50, false));
		txtComplemento.setBounds(95, 69, 482, 20);
		panelEndereco.add(txtComplemento);
		txtComplemento.setColumns(10);
		
		JLabel label_14 = new JLabel("Bairro :");
		label_14.setBounds(5, 98, 82, 14);
		panelEndereco.add(label_14);
		label_14.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		txtBairro = new JTextFieldFocu();
		txtBairro.setDocument(new TextFieldLimit(100, false));
		txtBairro.setBounds(95, 95, 324, 20);
		panelEndereco.add(txtBairro);
		txtBairro.setColumns(10);
		
		txtNumero = new JTextFieldFocu();
		txtNumero.setDocument(new TextFieldLimit(20, true));
		txtNumero.setBounds(483, 95, 92, 20);
		panelEndereco.add(txtNumero);
		txtNumero.setColumns(10);
		
		JLabel label_15 = new JLabel("Numero :");
		label_15.setBounds(428, 98, 52, 14);
		panelEndereco.add(label_15);
		label_15.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel label_9 = new JLabel("CEP :");
		label_9.setBounds(10, 10, 39, 14);
		panel_2.add(label_9);
		label_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		txtCep = new JTextCep();
		txtCep.setDocument(new TextFieldLimit(9, false));
		txtCep.setBounds(41, 5, 107, 20);
		panel_2.add(txtCep);
		txtCep.setColumns(10);
		
		/**
		 * Fixo
		 
		//getProdutorRural
		cbClienteContabilidade.setSelectedIndex(0);
		txtCodigo.setText("1");
		txtCodigoPropriedade.setText("123");
		txtNomePropriedade.setText("Fazenda Alvorada");
		
		//getPessoaJuridica
		txtCnpj.setText("92.502.421/0001-39");
		txtInscricaoEstadual.setText("ISENTO");
		txtRazaoSocial.setText("TESTE LTDA");
		txtNomeFantasia.setText("TESTE");
		
		//setListaTelefone
		txtTelefone.setText("9782-9266");
		txtContatoTel.setText("Fabricio");
		
		//getEndereco
		txtCep.setText("15015-000");
		*/
	}
	
	@SuppressWarnings("unchecked")
	private void carregaComboUf() {
		try {
			setListaUf(getUfDao().getTodos("sigla"));
			for(Uf uf : getListaUf()){
				cbUf.addItem(uf.getSigla());
			}
		} catch (Excecoes e1) {
			Mensagem.erro("Não foi possível obter as Ufs!");
		}
	}
	
	@SuppressWarnings("unchecked")
	private void carregaComboCidade(String uf) {
		try {
			cbCidade.removeAllItems();
			setListaCidades(getCidadesDao().getListaCidadePorUf(uf));
			for(Cidades cidade : getListaCidades()){
				cbCidade.addItem(cidade.getNome());
			}
		} catch (Excecoes e1) {
			Mensagem.erro("Não foi possível obter as Cidades!");
		}
	}

	private void fechar() {
		this.dispose();
		Inicio.limparTela(ProdutorRuralConsulta.produtorRuralCad);
		ProdutorRuralConsulta.produtorRuralCad=null;
		setProdutorRural(new ProdutorRural());
		setPessoaJuridica(new PessoaJuridica());
		setPessoa(new Pessoa());
		try {
			ProdutorRuralConsulta.setPanelBotoes(true);
			Inicio.produtorRuralConsulta.setSelected(true);
		} catch (Exception e) {}
	}
	
	private void salvar() {
		if(getPessoa().getDataCadastro() == null){
			getPessoa().setDataCadastro(Calendar.getInstance());
		}
		
		if(cbClienteContabilidade.getSelectedItem().toString().equals("Sim")){
			getProdutorRural().setClienteContalidade("S");
		}else{
			getProdutorRural().setClienteContalidade("N");
		}
		
		
	    getProdutorRural().setCodigo(txtCodigo.getText());
		
		getProdutorRural().setCodigoPropriedade(txtCodigoPropriedade.getText().replaceAll("\\D", ""));
		getProdutorRural().setNomePropriedade(txtNomePropriedade.getText());
		
		getPessoaJuridica().setCnpj(Utils.formataCpfCnpj(txtCnpj.getText().replaceAll("\\D", "")));
		getPessoaJuridica().setInscricaoEstadual(txtInscricaoEstadual.getText());
		getPessoaJuridica().setRazaoSocial(txtRazaoSocial.getText());
		getPessoaJuridica().setNomeFantasia(txtNomeFantasia.getText());
		
		setListaTelefone(((TMlistaTelefone) tbTelefone.getModel()).getList());
		
		getEndereco().setCep(txtCep.getText());
		getEndereco().setUf(cbUf.getSelectedItem().toString());
		getEndereco().setCidade(cbCidade.getSelectedItem().toString());
		getEndereco().setLogradouro(txtLogradouro.getText());
		getEndereco().setComplemento(txtComplemento.getText());
		getEndereco().setBairro(txtBairro.getText());
		getEndereco().setNumero(txtNumero.getText());
		if(validaTela()){
			try {
				if(getPessoaDao().salvaProdutorRural(getPessoa(), getProdutorRural(), getPessoaJuridica(), getListaTelefone(), getEndereco())){
					Mensagem.informacao("Produtor rural salvo com sucesso!");
				}
					
			} catch (Exception e) {
				Mensagem.erro("Erro ao salvar o Produtor Rural");
			}
			fechar();
		}
	}
	
	private boolean validarCnpj() {
        String texto = txtCnpj.getText().replaceAll("\\D", "");
        if (StringUtils.isNotBlank(texto)) {
            if (texto.length() == 14) {
                return Utils.isCNPJ(texto);
            }
        }
        return false;
    }

	private boolean validaTela() {
		boolean retorno = true;
	    final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	 
	    final Set<ConstraintViolation<ProdutorRural>> violationsProdutorRural = validator.validate(getProdutorRural());
	    final Set<ConstraintViolation<PessoaJuridica>> violationsPessoaJuridica = validator.validate(getPessoaJuridica());
	    
	    StringBuffer mensagem = new StringBuffer();
	    for (ConstraintViolation violation : violationsProdutorRural) {
	    	mensagem.append("\n").append(violation.getMessage());
        }
	    
	    for (ConstraintViolation violation : violationsPessoaJuridica) {
	    	mensagem.append("\n").append(violation.getMessage());
        }

	    if (!mensagem.toString().isEmpty()) {
	    	Mensagem.informacao(mensagem.toString());
	    	retorno = false;
	    }
	    return retorno;
	}

	/*
	private boolean validaTela() {
		//validarCnpj()
		return true;
	}
	*/
	private void incluirTelefone() {
		if(validaTelefone()){
			if(isAlterando){
				if(telefoneSelecionado >= 0){
					((TMlistaTelefone) tbTelefone.getModel()).excluir(telefoneSelecionado);
				}
				isAlterando = false;
			}
			getTelefone().setDdd(cbDddTelefone.getSelectedItem().toString());
			getTelefone().setNumero(txtTelefone.getText());
			getTelefone().setContato(txtContatoTel.getText());
			((TMlistaTelefone) tbTelefone.getModel()).adicionar(getTelefone());
			limparDadosTelefone();
		}
	}
	
	private void limparDadosTelefone(){
		//cbDddTelefone.getSelectedItem().toString()
		txtTelefone.setText("");
		txtContatoTel.setText("");
		
	}
	
	private boolean validaTelefone() {
		boolean retorno = true;
		if(StringUtils.isBlank(cbDddTelefone.getSelectedItem().toString())){
			retorno = false;
			Mensagem.aviso("Prencher DDD!!");
		}else if(StringUtils.isBlank(txtTelefone.getText())){
			retorno = false;
			Mensagem.aviso("Prencher numero telefone!!");
		}else if(StringUtils.isBlank(txtContatoTel.getText())){
			retorno = false;
			Mensagem.aviso("Prencher numero telefone!!");
		}
		return retorno;
	}
	
	private void cancelarDadosTelefone() {
		btnAlterarTelefone.setEnabled(true);
        btnCancelarTelefone.setEnabled(false);
        btnIncluirTelefone.setEnabled(true);
		limparDadosTelefone();
	}
	
	private void alterarTelefone() {
		if(telefoneSelecionado >= 0){
			if(telefone.getDdd() != null){
    			for (int i = 0; i < cbDddTelefone.getItemCount(); i++) {
    				if (cbDddTelefone.getItemAt(i).equals(telefone.getDdd())) {
    					cbDddTelefone.setSelectedIndex(i);
    				}
    			}
    		}
			txtTelefone.setText(telefone.getNumero());
			txtContatoTel.setText(telefone.getContato());
			btnAlterarTelefone.setEnabled(false);
			btnCancelarTelefone.setEnabled(true);
			btnIncluirTelefone.setEnabled(true);
			isAlterando = true;
		}else{
			Mensagem.informacao("Nenhum telefone foi selecionado");
		}
	}
	
	private void excluirTelefone() {
		if(telefoneSelecionado >= 0){
			boolean isExcluso = true; 
			int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir?", "Telefone", JOptionPane.YES_NO_OPTION);
			if (resposta == JOptionPane.YES_OPTION) {
				setTelefone(((TMlistaTelefone) tbTelefone.getModel()).getObj(telefoneSelecionado));
				try {
					getTelefoneDao().excluir(getTelefone().getId());
				} catch (Excecoes e) {
					isExcluso = false;
					Mensagem.erro("Não foi possível exclir o telefone!");
				}
				if(isExcluso)
					((TMlistaTelefone) tbTelefone.getModel()).excluir(telefoneSelecionado);
			}
		}
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
	
	public List<Telefone> getListaTelefone(){
		if(listaTelefone == null){
			listaTelefone = new ArrayList<Telefone>();
		}
		return listaTelefone;
	}
	
	public List<Telefone> setListaTelefone(List<Telefone> listaTelefone){
		return this.listaTelefone = listaTelefone;
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
	
	public PessoaDao getPessoaDao() {
		if(pessoaDao == null){
			pessoaDao = new PessoaDao();
			setPessoaDao(new PessoaDao());
		}
		return pessoaDao;
	}
	
	public void setPessoaDao(PessoaDao pessoaDao) {
		this.pessoaDao = pessoaDao;
	}
	
	public Pessoa getPessoa() {
		if(pessoa == null){
			pessoa = new Pessoa();
			setPessoa(new Pessoa());
		}
		return pessoa;
	}
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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
	
	public UfDao getUfDao() {
		if(ufDao == null){
			ufDao = new UfDao();
		}
		return ufDao;
	}
	
	public void setUfDao(UfDao ufDao) {
		this.ufDao = ufDao;
	}
	
	public List<Uf> getListaUf(){
		if(listaUf == null){
			listaUf = new ArrayList<Uf>();
		}
		return listaUf;
	}
	
	public List<Uf> setListaUf(List<Uf> listaUf){
		return this.listaUf = listaUf;
	}
	
	
	public CidadesDao getCidadesDao() {
		if(cidadesDao == null){
			cidadesDao = new CidadesDao();
		}
		return cidadesDao;
	}
	
	public void setCidadesDao(CidadesDao cidadesDao) {
		this.cidadesDao = cidadesDao;
	}
	
	public List<Cidades> getListaCidades(){
		if(listaCidades == null){
			listaCidades = new ArrayList<Cidades>();
		}
		return listaCidades;
	}
	
	public List<Cidades> setListaCidades(List<Cidades> listaCidades){
		return this.listaCidades = listaCidades;
	}
	
	public PessoaJuridicaDao getPessoaJuridicaDao() {
		if(pessoaJuridicaDao == null){
			pessoaJuridicaDao = new PessoaJuridicaDao();
		}
		return pessoaJuridicaDao;
	}
	
	public void setPessoaJuridicaDao(PessoaJuridicaDao pessoaJuridicaDao) {
		this.pessoaJuridicaDao = pessoaJuridicaDao;
	}
}
