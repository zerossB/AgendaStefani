package dbaccess;

/**
 * Classe responsável pela conexão e desconexão com o SGBD
 *
 * @author JSQLGen
 */
public class DBAccess {

    private java.sql.Connection connection;

    /**
     * Construtor
     *
     * @throws ClassNotFoundException throws when JDBC Driver is not found
     * @throws java.sql.SQLException throws when Driver Manager can't connect to
     * Database
     */
    public DBAccess() throws ClassNotFoundException, java.sql.SQLException {
        String iniFile = "./db.ini";
        if (new java.io.File(iniFile).exists()) {
            try {
                java.util.Properties propFile = new java.util.Properties();
                propFile.load(new java.io.FileInputStream(iniFile));
                connect(propFile);
            } catch (java.io.IOException e) {
                throw new java.sql.SQLException("File Ini doesn't exist.");
            }
        } else {
            /*String drv = "org.apache.derby.jdbc.EmbeddedDriver";
            String url = "jdbc:derby:./data;territory=pt_BR;create=TRUE";*/
            String drv = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://192.168.22.90/AgendaStefani";
            String user = "stefani";
            String password = "h39wa372";
            connect(drv, url, user, password);
            createTables();
        }
    }

    /**
     * Conecta no Banco de Dados
     *
     * @param drv
     * @param url
     * @param user
     * @param password
     * @throws ClassNotFoundException throws when JDBC Driver is not found
     * @throws java.sql.SQLException throws when Driver Manager can't connect to
     * Database
     */
    private void connect(String drv, String url, String user, String password) throws ClassNotFoundException, java.sql.SQLException {
        Class.forName(drv);
        connection = java.sql.DriverManager.getConnection(url, user, password);
    }

    /**
     *
     * @param fileIni
     * @throws ClassNotFoundException throws when JDBC Driver is not found
     * @throws java.sql.SQLException throws when Driver Manager can't connect to
     * Database
     */
    private void connect(java.util.Properties fileIni) throws ClassNotFoundException, java.sql.SQLException {
        connect(fileIni.getProperty("driver"), fileIni.getProperty("url"), fileIni.getProperty("user"), fileIni.getProperty("password"));
    }

    /**
     * Desconecta no Banco de Dados
     *
     * @throws java.sql.SQLException throws when Driver Manager can't disconnect
     * from Database
     */
    public void disconnect() throws java.sql.SQLException {
        connection.commit();
        connection.close();
    }

    /**
     * @return the connection
     */
    public java.sql.Connection getConnection() {
        return connection;
    }

    /**
     * Cria tabelas
     *
     * @throws java.sql.SQLException throws when Driver Manager can't disconnect
     * from Database
     */
    private void createTables() throws java.sql.SQLException {
        if (connection.getSchema() == "") {
            if (javax.swing.JOptionPane.showConfirmDialog(null, "O sistema criará o banco de dados.\nClique em <OK> para continuar.", "Aviso", javax.swing.JOptionPane.OK_CANCEL_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE) == javax.swing.JOptionPane.OK_OPTION) {
                pessoa.PessoaDAO.createTable(connection);
            } else {
                this.disconnect();
                System.exit(1);
            }
        }
    }
}
