/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codigoIntermedio;

/**
 *
 * @author Kuzuks
 */
public class BackPatch {
    public Lista siguiente;
    public Lista verdadera;
    public Lista falsa;
    public String lugar;
    
    public BackPatch(){
        this.siguiente = new Lista();
        this.verdadera = new Lista();
        this.falsa = new Lista();
        this.lugar = null;
    }
    public BackPatch(Lista siguiente){
        this.siguiente = siguiente;
        this.verdadera = null;
        this.falsa = null;
        this.lugar = null;
        
    }
    public BackPatch(Lista verdadera,Lista falsa){
        this.siguiente =null;
        this.verdadera = verdadera;
        this.falsa = falsa;
        this.lugar = null;
    }
    public BackPatch(String lugar){
        this.siguiente = null;
        this.verdadera = null;
        this.falsa = null;
        this.lugar = lugar;
    }
    public BackPatch(Lista siguiente,Lista verdadera, Lista falsa){
        this.siguiente = siguiente;
        this.verdadera = verdadera;
        this.falsa = falsa;
    }
}
