package pessoa.telefones;
/**
 *
 * @author JSQLGen
 */
public class TelefonesTab extends javax.swing.JPanel {
    private final pessoa.PessoaForm parent;
    private final java.sql.Connection connection;
    private final pessoa.Pessoa pessoaOwner;
    private final java.util.List<Telefones> list;

    /** Construtor do form TelefonesTab
     * @param parent
     * @param connection
     * @param pessoaOwner 
     */
    public TelefonesTab(pessoa.PessoaForm parent, java.sql.Connection connection, pessoa.Pessoa pessoaOwner) {
        this.parent = parent;
        this.connection = connection;
        this.pessoaOwner = pessoaOwner;
        this.list = pessoaOwner.getTelefone();
        initComponents();
        refresh();
    }

    /** Atualiza tabela */
    private void refresh(){
        javax.swing.table.DefaultTableModel mTable = (javax.swing.table.DefaultTableModel)tTable.getModel();
        int lineSel = tTable.getSelectedRow();
        while(mTable.getRowCount()>0) mTable.removeRow(0);
        for(Telefones telefones: list) {
            java.util.List<Object> line = new java.util.ArrayList<Object>();
            line.add(telefones.getId());
            line.add(telefones.getTipo());
            line.add(telefones.getCelular());
            line.add(telefones.getFixo());
            mTable.addRow(line.toArray());
        }
        if(lineSel>=0 && lineSel<list.size()) tTable.setRowSelectionInterval(lineSel, lineSel);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sTable = new javax.swing.JScrollPane();
        tTable = new javax.swing.JTable();
        pButtons = new javax.swing.JPanel();
        bAdd = new javax.swing.JButton();
        bRem = new javax.swing.JButton();
        bEdit = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        tTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Tipo", "Celular", "Fixo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tTableMouseClicked(evt);
            }
        });
        sTable.setViewportView(tTable);
        if (tTable.getColumnModel().getColumnCount() > 0) {
            tTable.getColumnModel().getColumn(0).setMinWidth(0);
            tTable.getColumnModel().getColumn(0).setPreferredWidth(0);
            tTable.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        add(sTable, java.awt.BorderLayout.CENTER);

        pButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        bAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/stock_new-16.png"))); // NOI18N
        bAdd.setMnemonic('N');
        bAdd.setText("Adicionar");
        bAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddActionPerformed(evt);
            }
        });
        pButtons.add(bAdd);

        bRem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/stock_delete-16.png"))); // NOI18N
        bRem.setMnemonic('R');
        bRem.setText("Remover");
        bRem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRemActionPerformed(evt);
            }
        });
        pButtons.add(bRem);

        bEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/stock_open-16.png"))); // NOI18N
        bEdit.setMnemonic('E');
        bEdit.setText("Editar");
        bEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditActionPerformed(evt);
            }
        });
        pButtons.add(bEdit);

        add(pButtons, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void bAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddActionPerformed
        Telefones telefones = new Telefones();
        telefones = TelefonesForm.showInputDialog(parent, connection, telefones);
        if(telefones!=null){
            try{
                if(pessoaOwner.getId()!=null) TelefonesDAO.insert(connection, telefones, pessoaOwner);
                list.add(telefones);
                refresh();
            } catch(java.sql.SQLException e){
                e.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this, "Falha na gravação do banco de dados!\n"+e.getMessage(),"Aviso",javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_bAddActionPerformed

    private void bRemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRemActionPerformed
        if(tTable.getSelectedRow()>=0){
            Telefones telefones = list.get(tTable.getSelectedRow());
            if(javax.swing.JOptionPane.showConfirmDialog(this, "Deseja excluir o Telefones "+telefones.getId()+"?","Questão",javax.swing.JOptionPane.OK_CANCEL_OPTION)==javax.swing.JOptionPane.OK_OPTION){
                try{
                    if(pessoaOwner.getId()!=null) TelefonesDAO.delete(connection, telefones);
                    list.remove(tTable.getSelectedRow());
                    refresh();
                } catch(java.sql.SQLException e){
                    e.printStackTrace();
                    javax.swing.JOptionPane.showMessageDialog(this, "Falha na exclusão do banco de dados!\n"+e.getMessage(),"Aviso",javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_bRemActionPerformed

    private void bEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditActionPerformed
        if(tTable.getSelectedRow()>=0){
            Telefones telefones = list.get(tTable.getSelectedRow());
            telefones = TelefonesForm.showInputDialog(parent, connection, telefones);
            if(telefones!=null){
                try{
                    if(pessoaOwner.getId()!=null) TelefonesDAO.update(connection, telefones, pessoaOwner);
                    refresh();
                } catch(java.sql.SQLException e){
                    e.printStackTrace();
                    javax.swing.JOptionPane.showMessageDialog(this, "Falha na gravação do banco de dados!\n"+e.getMessage(),"Aviso",javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_bEditActionPerformed

    private void tTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tTableMouseClicked
        if(evt.getClickCount()>=2){
            bEditActionPerformed(null);
        }
    }//GEN-LAST:event_tTableMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAdd;
    private javax.swing.JButton bEdit;
    private javax.swing.JButton bRem;
    private javax.swing.JPanel pButtons;
    private javax.swing.JScrollPane sTable;
    private javax.swing.JTable tTable;
    // End of variables declaration//GEN-END:variables
}
