package pessoa.telefones;

/**
 * Class TelefonesDAO responsável pela 
 * leitura/escrita de objetos Telefones no BD
 *
 * @author JSQLGen
 */
public final class TelefonesDAO extends dbaccess.DAO {

    //*****************************************
    //CREATE TABLE
    //*****************************************

    /** createTable - Cria a tabela Telefones no BD
     * @param connection Conexão com BD
     * @throws java.sql.SQLException
     */
    public static void createTable(java.sql.Connection connection) throws java.sql.SQLException {
        String sql = "CREATE TABLE Telefones ("
                   + "pessoaOwner INT NOT NULL,"
                   + "id INT NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"
                   + "tipo VARCHAR(8) NOT NULL,"
                   + "celular VARCHAR(20) NOT NULL,"
                   + "fixo VARCHAR(20) NOT NULL,"
                   + "CONSTRAINT PK_Telefones PRIMARY KEY (id),"
                   + "CONSTRAINT FKC_Telefones_PessoaOwner FOREIGN KEY (pessoaOwner) REFERENCES Pessoa ON DELETE CASCADE"
                   + ")";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
        statement.close();
    }

    //*****************************************
    //UPDATE
    //*****************************************

    /** obj2stmt - Transfere o Objeto para o PreparedStatement.
     * @param connection Conexão com BD
     * @param telefonesSave Telefones a ser armazenado
     * @param statement PreparedStatement contendo SQL
     * @throws java.sql.SQLException
     * @param pessoaOwner Pessoa owner
     */
    private static void obj2stmt(Telefones telefonesSave, pessoa.Pessoa pessoaOwner, java.sql.PreparedStatement statement) throws java.sql.SQLException {
        statement.setInt(1, pessoaOwner.getId());
        statement.setString(2, telefonesSave.getTipo());
        statement.setString(3, telefonesSave.getCelular());
        statement.setString(4, telefonesSave.getFixo());
    }

    /** insert - Este método insere no BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param telefonesInsert Telefones a ser inserido
     * @param pessoaOwner Pessoa owner
     * @throws java.sql.SQLException
     */
    public static void insert(java.sql.Connection connection, Telefones telefonesInsert, pessoa.Pessoa pessoaOwner) throws java.sql.SQLException {
        String sql = "INSERT INTO Telefones (pessoaOwner,tipo,celular,fixo) "
                   + "VALUES (?,?,?,?)";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        obj2stmt(telefonesInsert, pessoaOwner, statement);
        statement.executeUpdate();
        statement.close();
    }

    /** update - Este método atualiza no BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param telefonesUpdate Telefones a ser atualizado
     * @param pessoaOwner Pessoa owner
     * @throws java.sql.SQLException
     */
    public static void update(java.sql.Connection connection, Telefones telefonesUpdate, pessoa.Pessoa pessoaOwner) throws java.sql.SQLException {
        String sql = "UPDATE Telefones SET "
                   + "pessoaOwner = ?,"
                   + "tipo = ?,"
                   + "celular = ?,"
                   + "fixo = ? "
                   + "WHERE id = ?";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        obj2stmt(telefonesUpdate, pessoaOwner, statement);
        statement.setInt(5, telefonesUpdate.getId());
        statement.executeUpdate();
        statement.close();
    }

    /** delete - Este método apaga do BD o objeto passado como parâmetro.
     * @param connection Conexão com BD
     * @param telefonesDelete Telefones a ser apagado
     * @throws java.sql.SQLException
     */
    public static void delete(java.sql.Connection connection, Telefones telefonesDelete) throws java.sql.SQLException {
        String sql = "DELETE FROM Telefones WHERE id = ?";
        java.sql.PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, telefonesDelete.getId());
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
    private static Telefones rs2obj(java.sql.Connection connection, java.sql.ResultSet resultSet) throws java.sql.SQLException {
        Telefones telefonesLoad = new Telefones();
        telefonesLoad.setId(resultSet.getInt("id"));
        telefonesLoad.setTipo(resultSet.getString("tipo"));
        telefonesLoad.setCelular(resultSet.getString("celular"));
        telefonesLoad.setFixo(resultSet.getString("fixo"));
        return telefonesLoad;
    }

    /** load - Este método carrega o objeto com o seu identificador
     * @param connection Conexão com BD
     * @param condition Condição WHERE
     * @return objeto Telefones || null caso não encontrar
     * @throws java.sql.SQLException
     */
    private static Telefones load(java.sql.Connection connection, String condition) throws java.sql.SQLException {
        if(!condition.isEmpty()){
            String sql = "SELECT id,tipo,celular,fixo "
                       + "FROM Telefones "
                       + "WHERE "+condition;
            java.sql.PreparedStatement statement = connection.prepareStatement(sql);
            java.sql.ResultSet resultSet = statement.executeQuery();
            Telefones telefonesLoad;
            if (resultSet.next()) {
                telefonesLoad = rs2obj(connection, resultSet);
            } else {
                telefonesLoad = null;
            }
            statement.close();
            return telefonesLoad;
        } else {
            return null;
        }
    }

    /** loadList - Carrega lista de objetos Telefones
     * @param connection Conexão com BD
     * @param condition Condição WHERE
     * @return List contendo a tabela
     */
    private static java.util.List<Telefones> loadList(java.sql.Connection connection, String condition) {
        java.util.List<Telefones> list = new java.util.ArrayList<Telefones>();
        try {
            String sql = "SELECT id,tipo,celular,fixo "
                       + "FROM Telefones "
                       + (condition.isEmpty()?"":"WHERE "+condition);
            java.sql.PreparedStatement statement = connection.prepareStatement(sql);
            java.sql.ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Telefones telefonesLoad = rs2obj(connection, resultSet);
                list.add(telefonesLoad);
            }
            statement.close();
        } catch (java.sql.SQLException sqlex) {
            System.out.println("Falha na leitura do banco de dados !\n"+sqlex.getMessage());
        }
        return list;
    }

    /** loadView - Carrega visão de atributos de objetos Telefones
     * @param connection Conexão com BD
     * @param attributesList Atributos listados
     * @param condition condição WHERE
     * @param order Ordem da lista
     * @return lista de atributo
     */
    private static java.util.List loadView(java.sql.Connection connection, String attributesList, String condition, String order) {
        String sql = "SELECT " + attributesList + " "
                   + "FROM Telefones "
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
     * @param id campo identificador de Telefones
     * @return objeto Telefones || null caso não encontrar
     * @throws java.sql.SQLException
     */
    public static Telefones loadById(java.sql.Connection connection, Integer id) throws java.sql.SQLException {
        return load(connection, "id = "+id);
    }

    //*****************************************
    //EXISTS Object BY
    //*****************************************

    /** existsById - Este método verifica a existência de um objeto pelo o seu identificador
     * @param connection Conexão com BD
     * @param id campo identificador de Telefones
     * @return true caso exista || false caso não exista
     */
    public static Boolean existsById(java.sql.Connection connection, Integer id) {
        java.util.List<Telefones> l = loadList(connection, "id="+id);
        return !l.isEmpty();
    }

    //*****************************************
    //LOAD Attribute List
    //*****************************************

    /** loadIdList - Carrega lista de id de objetos Telefones
     * @param connection Conexão com BD
     * @return List contendo Id
     */
    public static java.util.List<String> loadIdList(java.sql.Connection connection) {
        return loadView(connection, "id", "", "id");
    }

    //*****************************************
    //LOAD Object List
    //*****************************************

    /** loadList - Retorna Lista de objetos Telefones por Pessoa
     * @param connection Conexão com BD
     * @param pessoaOwner Pessoa
     * @return List contendo os objetos
     */
    public static java.util.List<Telefones> loadList(java.sql.Connection connection, pessoa.Pessoa pessoaOwner) {
        return loadList(connection, "pessoaOwner = " + pessoaOwner.getId());
    }

    //*****************************************
    //LOAD Object View
    //*****************************************

}
