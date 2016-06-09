package br.com.tablemodel;

import br.com.entity.ProdutorRural;

public class TMlistaProdutorRural extends DMGAbstractTableModel<ProdutorRural>{
	private static final long serialVersionUID = 6261729368340775399L;

	// indices das colunas
    // Constantes representando o índice das colunas
    private static final int CODIGO = 0;
    private static final int CODIGO_PROPRIEDADE =1;
    private static final int CNPJ =2;
    private static final int RAZAO_SOCIAL =3;
    private static final int NOME_PROPRIEDADE =4;
    
    public TMlistaProdutorRural() {
		super(new String[] {"Código", "Cód. Propriedade", "Cnpj", "Razão Social", "Nome Propriedade"});
	}
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case CODIGO:
                return String.class;
            case CODIGO_PROPRIEDADE:
                return String.class;
            case CNPJ:
                return String.class;
            case RAZAO_SOCIAL:
                return String.class;
            case NOME_PROPRIEDADE:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("Coluna fora do indice");
        }
    }
	
	@Override
    public Object getValueAt(int rowIndex, int columnIndex) {
		ProdutorRural obj = linhas.get(rowIndex);
        switch (columnIndex) {
            case CODIGO:
                return obj.getCodigo();
            case CODIGO_PROPRIEDADE:
                return obj.getCodigoPropriedade();
            case CNPJ:
                return obj.getPessoaJuridica().getCnpj();
            case RAZAO_SOCIAL:
                return obj.getPessoaJuridica().getRazaoSocial();
            case NOME_PROPRIEDADE:
                return obj.getNomePropriedade();
            default:
                throw new IndexOutOfBoundsException("Coluna fora do indice");
        }
    }
}
