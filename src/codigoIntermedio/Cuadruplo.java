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
public class Cuadruplo {
    public String op;
    public String arg1;
    public String arg2;
    public String res;
    public String ambito = null;
    
    
    public Cuadruplo(String operador,String argumento1, String argumento2, String resultado){
        this.op = operador;
        this.arg1 = argumento1;
        this.arg2 = argumento2;
        this.res = resultado;
    }
    public Cuadruplo(String operador,String argumento1, String argumento2, String resultado,String ambito){
        this.op = operador;
        this.arg1 = argumento1;
        this.arg2 = argumento2;
        this.res = resultado;
        this.ambito = ambito;
    }
    
    public Cuadruplo(String operador,String argumento1,String resultado){
        this.op = operador;
        this.arg1 = argumento1;
        this.res = resultado;
    }
    public Cuadruplo(String operador,String resultado){
        this.op = operador;
        this.res = resultado;
    }
    public String toString(){
        return "Op: "+ op + " Arg1: "+ arg1+" Arg2: "+arg2+ " Res: "+res;
    }

    public String getOp() {
        return op;
    }

    public String getArg1() {
        return arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public String getRes() {
        return res;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    public void setRes(String res) {
        this.res = res;
    }
    
}
