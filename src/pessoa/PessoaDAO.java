package pessoa;

/**
 * Class PessoaDAO responsável pela 
 * leitura/escrita de objetos Pessoa no BD
 *
 * @author JSQLGen
 */
public final class PessoaDAO extends dbaccess.DAO {

    //*****************************************
    //CREATE TABLE
    //*****************************************

    /** createTable - Cria a tabela Pessoa no BD
     * @param connection Conexão com BD
     * @throws java.sql.SQLException
     */
    public static void createTable(java.sql.Connection connection) throws java.sql.SQLException {
        String sql = "CREATE TABLE Pessoa ("
                   + "id INT NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"
                   + "nome VARCHAR(255) NOT NULL,"
                   + "CONSTRAINT PK_Pessoa PRIMARY KEY (id)"
                   + ")";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
        statement.close();

        pessoa.telefones.TelefonesDAO.createTable(connection);
    }

    //*****************************************
    //UPDATE
    //*****************************************

    /** obj2stmt - Transfere o Objeto para o PreparedStatement.
     * @param connection Conexão com BD
     * @param pessoaSave Pessoa a ser armazenado
     * @param statement PreparedStatement contendo SQL
     * @throws java.sql.SQLException
     */
    private static void obj2stmt(Pessoa pessoaSave, java.sql.PreparedStatement statement) throws java.sql.SQLException {
        statement.setString(1, pessoaSave.getNome());
    }

    /** insert - Este método insere no BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param pessoaInsert Pessoa a ser inserido
     * @throws java.sql.SQLException
     */
    public static void insert(java.sql.Connection connection, Pessoa pessoaInsert) throws java.sql.SQLException {
        String sql = "INSERT INTO Pessoa (nome) "
                   + "VALUES (?)";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        obj2stmt(pessoaInsert, statement);
        statement.executeUpdate();
        statement.close();
        sql = "SELECT ID FROM Pessoa ORDER BY ID DESC LIMIT 1";
        statement = connection.prepareStatement(sql);
        java.sql.ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            pessoaInsert.setId(resultSet.getInt(1));
        }
        statement.close();
        for (pessoa.telefones.Telefones telefonesInsert : pessoaInsert.getTelefone()) {
            pessoa.telefones.TelefonesDAO.insert(connection, telefonesInsert, pessoaInsert);
        }
    }

    /** update - Este método atualiza no BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param pessoaUpdate Pessoa a ser atualizado
     * @throws java.sql.SQLException
     */
    public static void update(java.sql.Connection connection, Pessoa pessoaUpdate) throws java.sql.SQLException {
        String sql = "UPDATE Pessoa SET "
                   + "nome = ? "
                   + "WHERE id = ?";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        obj2stmt(pessoaUpdate, statement);
        statement.setInt(2, pessoaUpdate.getId());
        statement.executeUpdate();
        statement.close();
    }

    /** delete - Este método apaga do BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param pessoaDelete Pessoa a ser apagado
     * @throws java.sql.SQLException
     */
    public static void delete(java.sql.Connection connection, Pessoa pessoaDelete) throws java.sql.SQLException {
        String sql = "DELETE FROM Pessoa WHERE id = ?";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, pessoaDelete.getId());
        statement.executeUpdate();
        statement.close();
    }

    //*****************************************
    //QUERY private
    //*****************************************

    /**
     * rs2obj - Transfere do ResultSet da Query SQL para um novo objeto
     * @param connection
     * @param resultSet to parse
     * @return novo objeto
     * @throws java.sql.SQLException 
     */
    private static Pessoa rs2obj(java.sql.Connection connection, java.sql.ResultSet resultSet) throws java.sql.SQLException {
        Pessoa pessoaLoad = new Pessoa();
        pessoaLoad.setId(resultSet.getInt("id"));
        pessoaLoad.setNome(resultSet.getString("nome"));
        pessoaLoad.setTelefone(pessoa.telefones.TelefonesDAO.loadList(connection, pessoaLoad));
        return pessoaLoad;
    }

    /** load - Este método carrega o objeto com o seu identificador
     * @param connection Conexão com BD
     * @param condition Condição WHERE
     * @return objeto Pessoa || null caso não encontrar
     * @throws java.sql.SQLException
     */
    private static Pessoa load(java.sql.Connection connection, String condition) throws java.sql.SQLException {
        if(!condition.isEmpty()){
            String sql = "SELECT id,nome "
                       + "FROM Pessoa "
                       + "WHERE "+condition;
            java.sql.PreparedStatement statement = connection.prepareStatement(sql);
            java.sql.ResultSet resultSet = statement.executeQuery();
            Pessoa pessoaLoad;
            if (resultSet.next()) {
                pessoaLoad = rs2obj(connection, resultSet);
            } else {
                pessoaLoad = null;
            }
            statement.close();
            return pessoaLoad;
        } else {
            return null;
        }
    }

    /** loadList - Carrega lista de objetos Pessoa
     * @param connection Conexão com BD
     * @param condition Condição WHERE
     * @return List contendo a tabela
     */
    private static java.util.List<Pessoa> loadList(java.sql.Connection connection, String condition) {
        java.util.List<Pessoa> list = new java.util.ArrayList<Pessoa>();
        try {
            String sql = "SELECT id,nome "
                       + "FROM Pessoa "
                       + (condition.isEmpty()?"":"WHERE "+condition);
            java.sql.PreparedStatement statement = connection.prepareStatement(sql);
            java.sql.ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Pessoa pessoaLoad = rs2obj(connection, resultSet);
                list.add(pessoaLoad);
            }
            statement.close();
        } catch (java.sql.SQLException sqlex) {
            System.out.println("Falha na leitura do banco de dados !\n"+sqlex.getMessage());
        }
        return list;
    }
    
    /** loadList - Carrega lista de objetos Pessoa
     * @param connection Conexão com BD
     * @param condition Condição WHERE
     * @return List contendo a tabela
     */
    private static java.util.List<Pessoa> loadListByCondition(java.sql.Connection connection, String condition) {
        java.util.List<Pessoa> list = new java.util.ArrayList<Pessoa>();
        try {
            String sql = "SELECT id,nome "
                       + "FROM Pessoa "
                       + (condition.isEmpty()?"":"ORDER BY "+condition);
            java.sql.PreparedStatement statement = connection.prepareStatement(sql);
            java.sql.ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Pessoa pessoaLoad = rs2obj(connection, resultSet);
                list.add(pessoaLoad);
            }
            statement.close();
        } catch (java.sql.SQLException sqlex) {
            System.out.println("Falha na leitura do banco de dados !\n"+sqlex.getMessage());
        }
        return list;
    }

    /** loadView - Carrega visão de atributos de objetos Pessoa
     * @param connection Conexão com BD
     * @param attributesList Atributos listados
     * @param condition condição WHERE
     * @param order Ordem da lista
     * @return lista de atributo
     */
    private static java.util.List loadView(java.sql.Connection connection, String attributesList, String condition, String order) {
        String sql = "SELECT " + attributesList + " "
                   + "FROM Pessoa "
                   + (condition.isEmpty() ? "" : "WHERE " + condition)
                   + (order.isEmpty() ? "" : "ORDER BY " + order);
        return execQueryF(connection, sql);
    }

    //*****************************************
    //QUERY public
    //*****************************************

    //*****************************************
    //LOAD Object BY
    //*****************************************

    /** loadById - Este método carrega o objeto com o seu identificador
     * @param connection Conexão com BD
     * @param id campo identificador de Pessoa
     * @return objeto Pessoa || null caso não encontrar
     * @throws java.sql.SQLException
     */
    public static Pessoa loadById(java.sql.Connection connection, Integer id) throws java.sql.SQLException {
        return load(connection, "id = "+id);
    }

    //*****************************************
    //EXISTS Object BY
    //*****************************************

    /** existsById - Este método verifica a existência de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param id campo identificador de Pessoa
     * @return true caso exista || false caso não exista
     */
    public static Boolean existsById(java.sql.Connection connection, Integer id) {
        java.util.List<Pessoa> l = loadList(connection, "id="+id);
        return !l.isEmpty();
    }

    //*****************************************
    //LOAD Attribute List
    //*****************************************

    /** loadIdList - Carrega lista de id de objetos Pessoa
     * @param connection Conexão com BD
     * @return List contendo Id
     */
    public static java.util.List<String> loadIdList(java.sql.Connection connection) {
        return loadView(connection, "id", "", "id");
    }

    //*****************************************
    //LOAD Object List
    //*****************************************

    /** loadList - Carrega lista de objetos Pessoa
     * @param connection Conexão com BD
     * @return List contendo os objetos
     */
    public static java.util.List<Pessoa> loadList(java.sql.Connection connection) {
        return loadList(connection, "");
    }
    
    /** loadList - Carrega lista de objetos Pessoa
     * @param connection Conexão com BD
     * @return List contendo os objetos
     */
    public static java.util.List<Pessoa> loadListByName(java.sql.Connection connection) {
        return loadListByCondition(connection, "nome asc");
    }

    //*****************************************
    //LOAD Object View
    //*****************************************

    /** loadView - Carrega visão de atributos de objetos Pessoa
     * @param connection Conexão com BD
     * @return lista com visão de atributos
     */
    public static java.util.List loadView(java.sql.Connection connection) {
        String sql = "SELECT "
                   + "Pessoa.id,"
                   + "Pessoa.nome "
                   + "FROM Pessoa ";
        return execQueryF(connection, sql);
    }

}
