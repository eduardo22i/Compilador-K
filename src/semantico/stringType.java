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
public class stringType extends type{
    private ArrayList<charType> cadena;
    
    public stringType(){
        setSize(0);
    }
    public String toString(){
        return  "string";
    }    
    public void setCadena(ArrayList<charType> cadena){
        this.cadena = new ArrayList<charType>();       
        this.cadena.addAll(cadena);    
        Size();
    }
    public void Size(){
        for(type i : this.cadena){
            this.setSize(this.getSize()+i.getSize());
        }
            
    }
}
