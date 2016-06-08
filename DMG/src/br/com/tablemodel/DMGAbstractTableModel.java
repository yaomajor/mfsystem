package br.com.tablemodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public abstract class DMGAbstractTableModel <T> extends AbstractTableModel{
	protected List<T> linhas;
    private String[] colunas;
    
     public DMGAbstractTableModel(String[] colunas) {
        linhas = new ArrayList<T>();
        this.colunas = colunas;
    }

    public DMGAbstractTableModel(List<T> linhas, String[] colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
    }

    public int getRowCount() {
        //Quantas linhas tem sua tabela? Uma para cada item da lista.
        return linhas.size();
    }

    public int getColumnCount() {
        //Quantas colunas tem a tabela
        return colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        //Qual é o nome das nossas colunas
        return colunas[columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //e as células não forem editáveis, nem precisamos implementar o método “setValueAt”
    }

    // manipuladores de dados 
    public T getObj(int indiceLinha) {
        return linhas.get(indiceLinha);
    }
    
    public List<T> getList(){
        return linhas;
    }

    public void adicionar(T obj) {
        linhas.add(obj);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void excluir(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public void adicionarLista(List<T> lista) {
        int indice = getRowCount();
        linhas.addAll(lista);
        fireTableRowsInserted(indice, indice + lista.size());
    }

    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }   
    
    public List<T> getLista() {
        return linhas;
    }
}
