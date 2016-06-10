package br.com.tablemodel;

import br.com.entity.Telefone;

public class TMlistaTelefone extends DMGAbstractTableModel<Telefone>{
	private static final long serialVersionUID = -1403784618422587356L;
	
	// indices das colunas
    // Constantes representando o índice das colunas
    private static final int CONTATO = 0;
    private static final int NUMERO =1;

    public TMlistaTelefone() {
		super(new String[] {"Numero", "Contato"});
	}
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case CONTATO:
                return String.class;
            case NUMERO:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("Coluna fora do indice");
        }
    }
	
	@Override
    public Object getValueAt(int rowIndex, int columnIndex) {
		Telefone obj = linhas.get(rowIndex);
        switch (columnIndex) {
            case CONTATO:
                return obj.getContato();
            case NUMERO:
            	obj.setNumero(obj.getNumero().replaceAll("\\D", ""));
            	StringBuilder sb = new StringBuilder();
            	sb.append("(").append(obj.getDdd()).append(")");
            	StringBuilder tel = new StringBuilder(obj.getNumero());
            	tel.insert(obj.getNumero().length() - 4, '-');
            	sb.append(tel);
                return sb.toString();
            default:
                throw new IndexOutOfBoundsException("Coluna fora do indice");
        }
    }

}
