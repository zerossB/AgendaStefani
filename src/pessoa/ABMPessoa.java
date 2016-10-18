/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pessoa;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author guilh
 */
public class ABMPessoa extends AbstractTableModel {

    private List<pessoa.Pessoa> list;
    private final String[] colunas = {"Id", "Nome"};

    public ABMPessoa(List<Pessoa> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return this.colunas[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return list.get(rowIndex).getId();
            case 1:
                return list.get(rowIndex).getNome();
            default:
                return null;
        }
    }
}
