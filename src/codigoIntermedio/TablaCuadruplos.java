/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codigoIntermedio;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 *
 * @author Kuzuks
 */
public class TablaCuadruplos {
    public ArrayList<Cuadruplo> tabla_cuad;
    
    public TablaCuadruplos(){
        this.tabla_cuad= new ArrayList<Cuadruplo>();
    }
    public void Gen(String op,String arg1,String arg2,String res){
        tabla_cuad.add(new Cuadruplo(op,arg1,arg2,res));
    }
    public void Gen(String op,String arg1,String arg2){
        tabla_cuad.add(new Cuadruplo(op,arg1,arg2));
    }            
    public void AgregarCuad(Cuadruplo cuad){
        tabla_cuad.add(cuad);
    }
    public ArrayList<Cuadruplo> getTablaCuad(){
        return tabla_cuad;
    }
    public void res(int i,String etiq){
        this.tabla_cuad.get(i).res =etiq;
    }
    public void PrintTablaCuad(){
        for(Cuadruplo i: tabla_cuad){
            System.out.print(i.op+" |");
            if(i.arg1 != null)
                System.out.print(i.arg1 + " |");
            if(i.arg2 != null)
                System.out.print(i.arg2+" |");
            System.out.print(i.res+" |");
            System.out.println("\n");
        }
    }
    public void PrintTabla() throws IOException{
        String s1,s2,s3,s4;
         s2="";
         s3="";
        File file2 = new File("src/outputs/TablaCuadruplos.txt");
                 if (!file2.exists()) {
                    file2.createNewFile();
                  } 
                 PrintWriter pw2 = new PrintWriter(new FileOutputStream(file2));
        for(Cuadruplo i: tabla_cuad){
            pw2.print("op "+i.op+" |");
            if(i.arg1 != null)
               pw2.print("arg1 "+i.arg1 + " |");
            if(i.arg2 != null)
                pw2.print("arg2 "+i.arg2+" |");
            pw2.print("res "+i.res+" |");
           pw2.println();
        }
            pw2.close();
    }
}
