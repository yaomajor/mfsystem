package br.com.tela.cadastro;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import br.com.classes.Animais;
import br.com.util.AN;
import br.com.util.JSpinnerMHM;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.ImageIcon;
import javax.swing.SpinnerNumberModel;

public class MudarDeEra extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	
	JSpinner spinner;
	JComboBox comboSexo;
	JComboBox comboDe;
	JComboBox comboPara;
	/**
	 * Create the frame.
	 */
	String dataAtual = "";
	int codCli=0;
	public MudarDeEra(int codCli) {
		this.codCli=codCli;
		dataAtual = AN.dataAtualSql();
		setFrameIcon(new ImageIcon(MudarDeEra.class.getResource("/image/icEstoq.png")));
		setTitle("Mudan\u00E7a de ERA");
		setIconifiable(true);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 481, 282);
		getContentPane().setLayout(null);
		
		comboDe = new JComboBox();
		comboDe.setModel(new DefaultComboBoxModel(new String[] {"0-3 meses", "3-8 meses", "8-12 meses", "12-24 meses", "24-36 meses", "Acima de 36 meses"}));
		comboDe.setBounds(85, 79, 253, 20);
		getContentPane().add(comboDe);
		
		JLabel lblDe = new JLabel("De:");
		lblDe.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDe.setBounds(10, 79, 74, 20);
		getContentPane().add(lblDe);
		
		JLabel lblPara = new JLabel("Para:");
		lblPara.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPara.setBounds(10, 110, 74, 20);
		getContentPane().add(lblPara);
		
		comboPara = new JComboBox();
		comboPara.setModel(new DefaultComboBoxModel(new String[] {"0-3 meses", "3-8 meses", "8-12 meses", "12-24 meses", "24-36 meses", "Acima de 36 meses"}));
		comboPara.setBounds(85, 110, 253, 20);
		getContentPane().add(comboPara);
		
		JButton btnGravar = new JButton("Gravar");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gravar();
			}
		});
		btnGravar.setIcon(new ImageIcon(MudarDeEra.class.getResource("/image/icSalvar2.png")));
		btnGravar.setBounds(114, 198, 115, 29);
		getContentPane().add(btnGravar);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setIcon(new ImageIcon(MudarDeEra.class.getResource("/image/icSair.png")));
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fechar();
			}
		});
		btnFechar.setBounds(233, 198, 115, 29);
		getContentPane().add(btnFechar);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setHorizontalAlignment(SwingConstants.TRAILING);
		lblQuantidade.setBounds(10, 141, 74, 20);
		getContentPane().add(lblQuantidade);
		
		spinner = new JSpinnerMHM();
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner.setBounds(85, 141, 46, 20);
		getContentPane().add(spinner);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MudarDeEra.class.getResource("/image/icMudaEra2.png")));
		label.setBounds(138, 0, 317, 85);
		getContentPane().add(label);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSexo.setBounds(343, 79, 39, 20);
		getContentPane().add(lblSexo);
		
		comboSexo = new JComboBox();
		comboSexo.setModel(new DefaultComboBoxModel(new String[] {"M", "F"}));
		comboSexo.setBounds(384, 79, 46, 20);
		getContentPane().add(comboSexo);

	}
	public void fechar(){
		dispose();
		try{
			Inicio.limparTela(Estoque.mudaDeEra);
			Estoque.mudaDeEra=null;
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
		if(AN.jOptionPaneQuestion("Deseja Realmente Gravar a Mudança de Era?")==0){
			String de = comboDe.getSelectedItem().toString();
			String para = comboPara.getSelectedItem().toString();
			if(!de.equals(para)){
				String sexo = comboSexo.getSelectedItem().toString();
				boolean a =a().incluirEra(de, para, AN.stringPInt(((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().getText()), codCli, dataAtual, sexo);
				if(a==true){
					Estoque.atualizaTable();
					AN.jOptionPaneInformation("Mudança de Era efetuado com Sucesso!");
				}
			}else{
				AN.jOptionPaneError("Verifique os Produtos!");
			}
			
		}
		
		
		
		
		
		
		
		
		
		
	}
}
