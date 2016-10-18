package pessoa;
/**
 *
 * @author JSQLGen
 */
public class PessoaForm extends javax.swing.JDialog {
    private final java.sql.Connection connection;
    private Pessoa pessoa;

    /** Construtor do form PessoaForm
     * @param parent Janela mãe
     * @param connection Conexão com o BD
     * @param pessoa Objeto a ser editado
     */
    public PessoaForm(java.awt.Window parent, java.sql.Connection connection, Pessoa pessoa) {
        super(parent);
        this.connection = connection;
        this.pessoa = pessoa;
        initComponents();
        obj2form();
        this.setVisible(true);
    }

    /** Exibe caixa de diálogo para preenchimento dos campos
     * @param parent Janela mãe
     * @param connection Conexão com o BD
     * @param pessoa Objeto a ser editado
     * @return the pessoa
     */
    public static Pessoa showInputDialog(java.awt.Window parent, java.sql.Connection connection, Pessoa pessoa) {
        return new PessoaForm(parent, connection, pessoa).pessoa;
    }

    /** Transfere os dados do objeto para o formulário */
    private void obj2form() {
        tId.setText(pessoa.getId()==null?"":pessoa.getId().toString());
        tNome.setText(pessoa.getNome());
    }

    /** Transfere os dados do formulário para o objeto */
    private void form2obj() {
        pessoa.setId(tId.getText());
        pessoa.setNome(tNome.getText());
    }

    /** valida os dados do formulário */
    private String validateForm() {
        String fieldError ="";
        if(tNome.getText().length()<1) fieldError +="Nome\n";
        return fieldError;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tId = new javax.swing.JLabel();
        tbData = new javax.swing.JTabbedPane();
        pTelefone = new pessoa.telefones.TelefonesTab(this, connection, pessoa);
        pButtons = new javax.swing.JPanel();
        bOk = new javax.swing.JButton();
        bCancel = new javax.swing.JButton();
        tNome = new javax.swing.JFormattedTextField();

        tId.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        tId.setBorder(javax.swing.BorderFactory.createTitledBorder("Id"));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formulário de Pessoa");
        setModal(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tbData.addTab("Telefone", new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/stock_data-table-16.png")), pTelefone); // NOI18N

        pButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        bOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/stock_calc-accept-16.png"))); // NOI18N
        bOk.setMnemonic('O');
        bOk.setText("Ok");
        bOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bOkActionPerformed(evt);
            }
        });
        pButtons.add(bOk);

        bCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbox/images/stock_calc-cancel-16.png"))); // NOI18N
        bCancel.setMnemonic('C');
        bCancel.setText("Cancelar");
        bCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelActionPerformed(evt);
            }
        });
        pButtons.add(bCancel);

        tNome.setBorder(javax.swing.BorderFactory.createTitledBorder("Nome"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(pButtons, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tNome)
                            .addComponent(tbData))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tbData, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(494, 395));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bOkActionPerformed
        String fieldError =validateForm();
        if(fieldError.length()==0) {
            form2obj();
            this.setVisible(false);
            this.dispose();
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Verifique os campos seguintes e tente novamente!\n"+fieldError,"Aviso",javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bOkActionPerformed

    private void bCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelActionPerformed
        pessoa = null;
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_bCancelActionPerformed


    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        bCancelActionPerformed(null);
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancel;
    private javax.swing.JButton bOk;
    private javax.swing.JPanel pButtons;
    private javax.swing.JPanel pTelefone;
    private javax.swing.JLabel tId;
    private javax.swing.JFormattedTextField tNome;
    private javax.swing.JTabbedPane tbData;
    // End of variables declaration//GEN-END:variables
}