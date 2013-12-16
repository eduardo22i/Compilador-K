/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador_k;

import java.io.File;

public class MLexer {
    
    public static void main(String[]args){
    JFlex.Main.generate(new File("src\\compilador_k\\Lexer.flex"));
    
    
    }
}
