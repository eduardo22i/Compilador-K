/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package compilador_k;
/**
 *
 * @author Kuzuks
 */
public class MCup {
    
    public static void main(String[]args){
        
        String opciones[] = new String[5];
        
        opciones[0] = "-destdir";
        opciones[1] = "src\\compilador_k";
        opciones[2] = "-parser";
        opciones[3] = "Analizador";
        opciones[4] = "src/compilador_k/Codigo.cup";
                 
        try{
            java_cup.Main.main(opciones);
            
        }catch(Exception e){
            System.out.print(e);
        }
    
    }
}
    

