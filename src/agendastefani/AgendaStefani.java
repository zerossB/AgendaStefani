/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendastefani;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import pessoa.*;

/**
 *
 * @author guilh
 */
public final class AgendaStefani extends javax.swing.JFrame {

    private dbaccess.DBAccess connection;
    private java.util.List<Pessoa> list;
    private pessoa.ABMPessoa model;
    private AgendaStefani agenda;

    /**
     * Creates new form AgendaStefani
     */
    public AgendaStefani() {
        initComponents();
        stayTrayIcon();
        try {
            agenda = this;
            connection = new dbaccess.DBAccess();
            list = pessoa.PessoaDAO.loadListByName(connection.getConnection());
            refresh();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AgendaStefani.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AgendaStefani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Atualiza tabela
     */
    private void refresh() {
        list = pessoa.PessoaDAO.loadListByName(connection.getConnection());
        model = new pessoa.ABMPessoa(list);
        tTable.setModel(model);
        tTable.getColumnModel().getColumn(0).setMinWidth(0);
        tTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        tTable.getColumnModel().getColumn(0).setMaxWidth(0);

        //tTable.setDefaultRenderer(String.class, new MyRenderer());
        tTable.repaint();
    }

    /**
     * Atualiza tabela
     */
    private void refresha(java.util.List<Pessoa> lista) {
        list = lista;
        model = new pessoa.ABMPessoa(list);
        tTable.setModel(model);
        tTable.getColumnModel().getColumn(0).setMinWidth(0);
        tTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        tTable.getColumnModel().getColumn(0).setMaxWidth(0);

        //tTable.setDefaultRenderer(String.class, new MyRenderer());
        tTable.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        bAdd = new javax.swing.JButton();
        bRem = new javax.swing.JButton();
        bEdit = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        tPesquisa = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agenda Stéfani");
        setIconImage(new ImageIcon(getClass().getResource("/toolbox/images/AgendaStefani.png")).getImage());
        setResizable(false);

        tTable.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id", "Nome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tTable.setRowHeight(25);
        tTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tTable);
        if (tTable.getColumnModel().getColumnCount() > 0) {
            tTable.getColumnModel().getColumn(0).setMinWidth(0);
            tTable.getColumnModel().getColumn(0).setPreferredWidth(0);
            tTable.getColumnModel().getColumn(0).setMaxWidth(0);
            tTable.getColumnModel().getColumn(1).setResizable(false);
        }

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ações"));

        bAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/stock_new-16.png"))); // NOI18N
        bAdd.setMnemonic('N');
        bAdd.setText("Adicionar");
        bAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddActionPerformed(evt);
            }
        });

        bRem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/stock_delete-16.png"))); // NOI18N
        bRem.setMnemonic('R');
        bRem.setText("Remover");
        bRem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRemActionPerformed(evt);
            }
        });

        bEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/stock_open-16.png"))); // NOI18N
        bEdit.setMnemonic('E');
        bEdit.setText("Editar");
        bEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 326, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(bAdd)
                    .addGap(5, 5, 5)
                    .addComponent(bRem)
                    .addGap(5, 5, 5)
                    .addComponent(bEdit)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bAdd)
                        .addComponent(bRem)
                        .addComponent(bEdit))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisa por nome"));

        tPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tPesquisaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tPesquisa)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddActionPerformed
        Pessoa pessoa = new Pessoa();
        pessoa = PessoaForm.showInputDialog(this, connection.getConnection(), pessoa);
        if (pessoa != null) {
            try {
                PessoaDAO.insert(connection.getConnection(), pessoa);
                refresh();
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this, "Falha na gravação do banco de dados!\n" + e.getMessage(), "Aviso", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_bAddActionPerformed

    private void bRemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRemActionPerformed
        if (tTable.getSelectedRow() >= 0) {
            try {
                Pessoa pessoa = PessoaDAO.loadById(connection.getConnection(), (Integer) tTable.getValueAt(tTable.getSelectedRow(), 0));
                if (javax.swing.JOptionPane.showConfirmDialog(this, "Deseja excluir " + pessoa.getNome() + "?", "Questão", javax.swing.JOptionPane.OK_CANCEL_OPTION) == javax.swing.JOptionPane.OK_OPTION) {
                    PessoaDAO.delete(connection.getConnection(), pessoa);
                    list.remove(tTable.getSelectedRow());
                    refresh();
                }
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this, "Falha na exclusão do banco de dados!\n" + e.getMessage(), "Aviso", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_bRemActionPerformed

    private void bEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditActionPerformed
        if (tTable.getSelectedRow() >= 0) {
            try {
                Pessoa pessoa = PessoaDAO.loadById(connection.getConnection(), (Integer) tTable.getValueAt(tTable.getSelectedRow(), 0));
                pessoa = PessoaForm.showInputDialog(this, connection.getConnection(), pessoa);
                if (pessoa != null) {
                    PessoaDAO.update(connection.getConnection(), pessoa);
                }
                refresh();
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this, "Falha na gravação do banco de dados!\n" + e.getMessage(), "Aviso", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_bEditActionPerformed

    private void tTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tTableMouseClicked
        if (evt.getClickCount() >= 2) {
            try {
                Pessoa pes = PessoaDAO.loadById(connection.getConnection(),
                        (Integer) tTable.getValueAt(tTable.getSelectedRow(), 0));

                new pessoa.FormView(this, pes).setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(AgendaStefani.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tTableMouseClicked

    private void tPesquisaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tPesquisaKeyTyped
        if (tPesquisa.getText().length() >= 1) {
            java.util.List<Pessoa> lista = new java.util.ArrayList<Pessoa>();
            for (pessoa.Pessoa p : this.list) {
                if(p.getNome().contains(tPesquisa.getText()))
                    lista.add(p);
            }
            refresha(lista);
        }else{
            refresh();
        }
    }//GEN-LAST:event_tPesquisaKeyTyped

    public void stayTrayIcon() {
        // Instancia um novo SystemTray
        SystemTray tray = SystemTray.getSystemTray();

        /**
         * Pega uma imagem para definir como ícone.
         *
         * main.getClass().getClassLoader().getResource("icone.jpg") pega a
         * imagem do pacote onde a Classe se encontra. Será bem útil na hora de
         * exportar a aplicação.
         */
        Image imageIcon = new ImageIcon(
                getClass()
                .getResource("/toolbox/images/AgendaStefani.png"))
                .getImage();

        // Instancia e Define o icone do TrayIcon
        TrayIcon trayIcon = new TrayIcon(imageIcon);

        // Define o auto-ajuste da imagem
        trayIcon.setImageAutoSize(true);

        /**
         * Instancia um mouse listener para ser usado no TrayIcon
         */
        MouseListener mlOpcoes = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                /**
                 * Se o mouse for clicado com a roda do mouse ou com o botão
                 * direito fechará a aplicação.
                 */
                if (e.getButton() == 1) {
                    agenda.setVisible(true);
                }
                if (e.getButton() == 2 || e.getButton() == 3) {
                    System.exit(0);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        // adiciona o mouseListener no TrayIcon
        trayIcon.addMouseListener(mlOpcoes);

        try {
            // Adiciona o Ícone no SystemTray
            tray.add(trayIcon);
        } catch (AWTException e) {
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AgendaStefani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgendaStefani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgendaStefani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgendaStefani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgendaStefani().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAdd;
    private javax.swing.JButton bEdit;
    private javax.swing.JButton bRem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tPesquisa;
    private javax.swing.JTable tTable;
    // End of variables declaration//GEN-END:variables
}
