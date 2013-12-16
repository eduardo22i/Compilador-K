/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codigoIntermedio;
import semantico.*;
/**
 *
 * @author Kuzuks
 */
public class result {
    public type tipo;
    public Object valor;
    public String lugar;
    public BackPatch backpatch;
    
    public result(){
        this.tipo = null;
        this.valor = null;
        this.lugar = null;
        this.backpatch = null;
    }
    public result(Object ob, BackPatch back){
        this.valor=ob;
        this.backpatch = back;
    }
    public result( BackPatch back){
        this.valor=null;
        this.backpatch = back;
    }
    public result(BackPatch back,type tipo){
        this.tipo= tipo;
        this.valor =null;
        this.backpatch = back;
    }
    public result(Object ob, type tipo){
        this.tipo=tipo;
        this.valor = ob;
        this.lugar = null;
    }
    
    public result(Object ob){
        this.valor =ob;
        this.lugar =null;
    }
    public result(Object ob, int i){
        this.valor = ob;
        this.lugar = ""+i;
    }
    public result(String st){
        this.valor = (Object)st;
        this.lugar = st;
    }
    public result (String st,type tipo){
        this.valor = (Object)st;
        this.lugar = st;
        this.tipo =tipo; 
                
    }
    public result(Object ob, String st){
        this.valor = ob;
        this.lugar = st;
    }
    public result(Object ob,String st,type tipo){
        this.lugar =st;
        this.tipo=tipo;
        this.valor =ob;
    }
}
