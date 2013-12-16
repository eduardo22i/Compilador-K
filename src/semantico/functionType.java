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
public class functionType extends type {
  //  private types dominio;
    private type rango;
    private ArrayList<type> dominio;
        
    public functionType (ArrayList<type> dominio, type rango){
        super();
        this.setSize(0);
        this.dominio = new ArrayList<type>();
        if(dominio != null)
        this.dominio.addAll(dominio);
        this.rango = rango;
        calcularSize();
      //  this.dominio= dominio;
       // this.rango = rango;
    }
    public functionType(type rango){
        super();
        this.setSize(0);
        this.rango = rango;
        this.dominio = null;
        //calcularSize();
                
    }
    
    //para encontrar el tamano de la funcion hay que incluir el rango?
      public void calcularSize(){
          for(type i : this.dominio)
          this.setSize(this.getSize()+ i.getSize());
      }
    public String toString(){
        String domain = "";
        int contador = 0;
        
        for(type i: this.dominio){
            contador ++;
            if(contador == this.dominio.size()){
                domain =domain + i.toString();
            }
            else
                domain = domain + i.toString() + " x ";
        }
            return rango.toString();
    }
    
    public type getRango(){
        return rango;
    }
    public void setRango(type rango){
        this.rango = rango;
    }
    public void setDominio(ArrayList<type> dominio){
        this.dominio = dominio;
    }

 
}
