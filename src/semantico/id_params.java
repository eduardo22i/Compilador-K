/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semantico;
import java.util.ArrayList;
/**
 *
 * @author Kuzuks
 */
public class id_params {
    private String id;
    private ArrayList<type> tipos;
    
    public id_params(String id, ArrayList<type> tipos){
        this.id= id;
        this.tipos = new ArrayList<type>();
        this.tipos.addAll(tipos);
    }
    
    public id_params(String id){
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public ArrayList<type> getTipos() {
        return tipos;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTipos(ArrayList<type> tipos) {
        this.tipos = tipos;
    }
    public String toString(){
        return "id "+ id +" parametros: "+ tipos;
    }
}
