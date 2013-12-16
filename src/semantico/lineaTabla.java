/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semantico;

/**
 *
 * @author Kuzuks
 */
public class lineaTabla {
    private String id;
    private type tipo;
    private String ambito;
    private String valor;
    private int offset;
    
    public lineaTabla (String id, type tipo,String ambito,String valor,int offset){
        this.id = id;
        this.tipo = tipo;
        this.ambito = ambito;
        this.valor = valor;
        this.offset = offset;
    }

    public String getId() {
        return id;
    }

    public type getTipo() {
        return tipo;
    }

    public String getAmbito() {
        return ambito;
    }

    public String getValor() {
        return valor;
    }

    public int getOffset() {
        return offset;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTipo(type tipo) {
        this.tipo = tipo;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
    public String toString(){
        return "id: " + id + " tipo: "+tipo+" ambito: "+ ambito+" valor: "+valor+ " offset: "+offset +"\n";
    }
    
}
