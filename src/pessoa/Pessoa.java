package pessoa;

/**
 *
 * @author JSQLGen
 */
public final class Pessoa {

    /**
     * Atributos
     */
    private Integer id;
    private String nome;
    private java.util.List<pessoa.telefones.Telefones> telefone;

    /**
     * Construtor
     */
    public Pessoa() {
        id = null;
        nome = "";
        telefone = new java.util.ArrayList<pessoa.telefones.Telefones>();
    }

    /**
     * Metodos
     */

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id Id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param id - String id to set
     */
    public void setId(String id) {
        this.id = (id.equals("null") || id.isEmpty()) ? null : Integer.parseInt(id);
    }

    /**
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome Nome to set
     */
    public void setNome(String nome) {
        this.nome = (nome.length() > 255 ? nome.substring(0, 255) : nome).replace('\'', '`');
    }

    /**
     * @return the telefone
     */
    public java.util.List<pessoa.telefones.Telefones> getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(java.util.List<pessoa.telefones.Telefones> telefone) {
        this.telefone = telefone;
    }
}
