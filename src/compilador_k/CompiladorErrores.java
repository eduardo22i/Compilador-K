/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador_k;
/**
 *
 * @author Kuzuks
 */

 class CompiladorErrores implements java.lang.Comparable{
    int linea, columna;
    String error;
    CompiladorErrores(int l, int c, String e){
        linea=l;
        columna=c;
        error=e;
    
    }
    
    @Override
    public int compareTo(Object o) {
        CompiladorErrores compare = (CompiladorErrores) o;
        if(linea ==-1){
            return 1;
        }
        else{
            if(compare.linea == -1){
                return -1;
            }
        }
        
        int resultado =0;
        if(linea <compare.linea){
            resultado =-1;
        }
        else{
            if(linea == compare.linea){
                if(columna< compare.columna){
                    resultado =-1;
                }
                else{
                    if (columna== compare.columna){
                        resultado =0;
                    }
                    else
                        resultado =1;
                }
                    
                
            }
            else{
                resultado =1;
            }
            
        }
        
        return resultado;
    }
    
}
