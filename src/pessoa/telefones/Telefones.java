package pessoa.telefones;
/**
 *
 * @author JSQLGen
 */
public final class Telefones {

    /** Atributos estaticos */
    public static String[] TIPO = {
        "Telefone",
        "Celular"
    };

    /** Atributos */
    private Integer id;
    private String tipo;
    private String celular;
    private String fixo;

    /** Construtor */
    public Telefones() {
        id = null;
        tipo = "";
        celular = "";
        fixo = "";
    }
    /** Metodos */

    /**
     * @return id
     */
    public Integer getId() { return id; }
    /**
     * @param id Id to set
     */
    public void setId(Integer id) { this.id = id; }
    /**
     * @param id - String id to set
     */
    public void setId(String id) { this.id = (id.equals("null") || id.isEmpty())?null:Integer.parseInt(id); }

    /**
     * @return the tipo
     */
    public String getTipo() { return tipo; }
    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) { this.tipo = tipo; }

    /**
     * @return celular
     */
    public String getCelular() { return celular; }
    /**
     * @param celular Celular to set
     */
    public void setCelular(String celular) { this.celular = (celular.length()>20?celular.substring(0,20):celular).replace('\'','`'); }

    /**
     * @return fixo
     */
    public String getFixo() { return fixo; }
    /**
     * @param fixo Fixo to set
     */
    public void setFixo(String fixo) { this.fixo = (fixo.length()>20?fixo.substring(0,20):fixo).replace('\'','`'); }
}
