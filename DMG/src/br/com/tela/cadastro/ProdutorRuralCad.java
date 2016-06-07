package br.com.tela.cadastro;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.lang.StringUtils;

import br.com.entity.Endereco;
import br.com.entity.Pessoa;
import br.com.entity.PessoaJuridica;
import br.com.entity.ProdutorRural;
import br.com.entity.Telefone;
import br.com.persistencia.PessoaDao;
import br.com.tablemodel.TMlistaTelefone;
import br.com.util.Mensagem;
import br.com.util.Utils;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import javax.swing.DefaultComboBoxModel;
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
	
	private List<Telefone> listaTelefone;;
	
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
		iniacializaComponentes();
		eventos();
		setLayout();
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
		
	}

	private void eventos() {
		txtCnpj.addKeyListener(new KeyAdapter() {    
            public void keyPressed(KeyEvent e) {    
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    //buscaPessoaJuridica();
                }
            }
        });
		
		tbTelefone.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
            	telefoneSelecionado = tbTelefone.getSelectedRow();
            	if (telefoneSelecionado >= 0){ 
            		setTelefone(((TMlistaTelefone) tbTelefone.getModel()).getObj(telefoneSelecionado));
            	}
            }
        });
	}

	private void iniacializaComponentes() {
		setBounds(100, 100, 630, 598);
		getContentPane().setLayout(null);
		
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
		cbDddTelefone.setModel(new DefaultComboBoxModel(new String[] {"17"}));
		cbDddTelefone.setBounds(74, 8, 39, 20);
		panel.add(cbDddTelefone);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(123, 8, 110, 20);
		panel.add(txtTelefone);
		txtTelefone.setColumns(10);
		
		JLabel lblContato = new JLabel("Contato :");
		lblContato.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblContato.setBounds(243, 11, 64, 14);
		panel.add(lblContato);
		
		txtContatoTel = new JTextField();
		txtContatoTel.setColumns(10);
		txtContatoTel.setBounds(307, 8, 273, 20);
		panel.add(txtContatoTel);
		
		JScrollPane spTelefone = new JScrollPane();
		spTelefone.setBounds(10, 70, 570, 107);
		panel.add(spTelefone);
		
		tbTelefone = new JTable();
		spTelefone.setColumnHeaderView(tbTelefone);
		
		JButton btnIncluirTelefone = new JButton("Incluir Telefone");
		btnIncluirTelefone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirTelefone();
			}
		});
		btnIncluirTelefone.setBounds(10, 36, 110, 23);
		panel.add(btnIncluirTelefone);
		
		JButton btnAlterarTelefone = new JButton("Alterar Telefone");
		btnAlterarTelefone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarTelefone();
			}
		});
		btnAlterarTelefone.setBounds(133, 36, 111, 23);
		panel.add(btnAlterarTelefone);
		
		JButton btnCancelarTelefone = new JButton("Cancelar");
		btnCancelarTelefone.setBounds(253, 36, 89, 23);
		panel.add(btnCancelarTelefone);
		
		txtNomePropriedade = new JTextField();
		txtNomePropriedade.setColumns(10);
		txtNomePropriedade.setBounds(276, 97, 324, 20);
		getContentPane().add(txtNomePropriedade);
		
		JLabel label = new JLabel("Nome Propriedade :");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(161, 100, 126, 14);
		getContentPane().add(label);
		
		txtCodigo = new JTextField();
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
		
		txtCnpj = new JTextField();
		txtCnpj.setColumns(10);
		txtCnpj.setBounds(60, 11, 140, 20);
		getContentPane().add(txtCnpj);
		
		JLabel label_3 = new JLabel("IE :");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(210, 14, 26, 14);
		getContentPane().add(label_3);
		
		txtInscricaoEstadual = new JTextField();
		txtInscricaoEstadual.setColumns(10);
		txtInscricaoEstadual.setBounds(241, 11, 126, 20);
		getContentPane().add(txtInscricaoEstadual);
		
		JLabel label_4 = new JLabel("C\u00F3digo Propriedade :");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_4.setBounds(377, 14, 116, 14);
		getContentPane().add(label_4);
		
		txtCodigoPropriedade = new JTextField();
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
		panel_1.setBounds(10, 522, 590, 38);
		getContentPane().add(panel_1);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});
		btnSalvar.setBounds(10, 11, 89, 23);
		panel_1.add(btnSalvar);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		btnFechar.setBounds(119, 11, 89, 23);
		panel_1.add(btnFechar);
		
		JLabel label_5 = new JLabel("Raz\u00E3o Social :");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_5.setBounds(10, 40, 92, 14);
		getContentPane().add(label_5);
		
		txtRazaoSocial = new JTextField();
		txtRazaoSocial.setColumns(10);
		txtRazaoSocial.setBounds(102, 38, 498, 20);
		getContentPane().add(txtRazaoSocial);
		
		JLabel label_6 = new JLabel("Nome Fantasia :");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_6.setBounds(10, 69, 92, 14);
		getContentPane().add(label_6);
		
		txtNomeFantasia = new JTextField();
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
		cbUf.setModel(new DefaultComboBoxModel(new String[] {"SP"}));
		cbUf.setBounds(41, 11, 39, 20);
		panelEndereco.add(cbUf);
		
		JLabel label_11 = new JLabel("Cidade :");
		label_11.setBounds(90, 14, 61, 14);
		panelEndereco.add(label_11);
		label_11.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		cbCidade = new JComboBox();
		cbCidade.setModel(new DefaultComboBoxModel(new String[] {"Votuporanga"}));
		cbCidade.setBounds(140, 11, 435, 20);
		panelEndereco.add(cbCidade);
		
		JLabel label_12 = new JLabel("Logradouro :");
		label_12.setBounds(5, 44, 75, 14);
		panelEndereco.add(label_12);
		label_12.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		txtLogradouro = new JTextField();
		txtLogradouro.setBounds(93, 41, 482, 20);
		panelEndereco.add(txtLogradouro);
		txtLogradouro.setColumns(10);
		
		JLabel label_13 = new JLabel("Complemento :");
		label_13.setBounds(5, 72, 85, 14);
		panelEndereco.add(label_13);
		label_13.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		txtComplemento = new JTextField();
		txtComplemento.setBounds(95, 69, 482, 20);
		panelEndereco.add(txtComplemento);
		txtComplemento.setColumns(10);
		
		JLabel label_14 = new JLabel("Bairro :");
		label_14.setBounds(5, 98, 82, 14);
		panelEndereco.add(label_14);
		label_14.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		txtBairro = new JTextField();
		txtBairro.setBounds(95, 95, 324, 20);
		panelEndereco.add(txtBairro);
		txtBairro.setColumns(10);
		
		txtNumero = new JTextField();
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
		
		txtCep = new JTextField();
		txtCep.setBounds(41, 5, 107, 20);
		panel_2.add(txtCep);
		txtCep.setColumns(10);
	}

	private void fechar() {
		this.dispose();
		Inicio.limparTela(ProdutorRuralConsulta.produtorRuralCad);
		ProdutorRuralConsulta.produtorRuralCad=null;
		try {
			ProdutorRuralConsulta.setPanelBotoes(true);
			Inicio.produtorRuralConsulta.setSelected(true);
		} catch (Exception e) {}
	}
	
	private void salvar() {
		if(validaTela()){
			getPessoa().setDataCadastro(Calendar.getInstance());
			
			if(cbClienteContabilidade.getSelectedItem().toString().equals("Sim")){
				getProdutorRural().setClienteContalidade("S");
			}else{
				getProdutorRural().setClienteContalidade("S");
			}
			getProdutorRural().setCodigo(txtCodigo.getText());
			getProdutorRural().setCodigoPropriedade(txtCodigoPropriedade.getText());
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
			
			try {
				getPessoaDao().salvaProdutorRural(getPessoa(), getProdutorRural(), getPessoaJuridica(), getListaTelefone(), getEndereco());
			} catch (Exception e) {
				Mensagem.erro("Erro ao salvar o Produtor Rural");
			}
			
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
		//validarCnpj()
		return true;
	}
	
	private void incluirTelefone() {
		if(validaTelefone()){
			Telefone telefone = new Telefone();
			telefone.setDdd(cbDddTelefone.getSelectedItem().toString());
			telefone.setNumero(txtTelefone.getText());
			telefone.setContato(txtContatoTel.getText());
			((TMlistaTelefone) tbTelefone.getModel()).adicionar(telefone);
		}
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
	
	private void alterarTelefone() {
		if(telefone.getId() != null && telefoneSelecionado >= 0){
			//if(cbDddTelefone.getSelectedItem())
			txtTelefone.setText(telefone.getNumero());
			txtContatoTel.setText(telefone.getContato());
		}
	}

	public ProdutorRural getProdutorRural() {
		if(produtorRural == null){
			produtorRural = new ProdutorRural();
			getProdutorRural().setPessoaJuridica(new PessoaJuridica());
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
	
	
	
	
}
