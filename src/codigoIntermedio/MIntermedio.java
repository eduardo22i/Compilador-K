/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codigoIntermedio;

/**
 *
 * @author Kuzuks
 */
public class MIntermedio {
    public static void main(String[]args){
        
        String opciones[] = new String[5];
        
        opciones[0] = "-destdir";
        opciones[1] = "src\\codigoIntermedio";
        opciones[2] = "-parser";
        opciones[3] = "intermedio";
        opciones[4] = "src/codigoIntermedio/Intermedio.cup";
                 
        try{
            java_cup.Main.main(opciones);
            
        }catch(Exception e){
            System.out.print(e);
        }
    
    }
}
