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
public class tablaSimbolos {
    private ArrayList<lineaTabla> lineasTabla;
    
    public tablaSimbolos(ArrayList<lineaTabla> lineasTabla){
        this.lineasTabla.addAll(lineasTabla);
    }
    public tablaSimbolos(){
        this.lineasTabla = new ArrayList<lineaTabla>();
    }
    public void agregar(lineaTabla linea){
        lineasTabla.add(linea);
    }
    public void agregar(String id,type tipo,String ambito,String valor){
        lineasTabla.add(new lineaTabla(id,tipo,ambito,valor,tipo.getSize()));
    }
    public ArrayList<lineaTabla> getLineasTabla(){
        return lineasTabla;  
    }
    public type getTipo (String id , String ambito ){
    for(int i =lineasTabla.size()-1; i>=0  ; i-- )
    {
    if (lineasTabla.get(i).getId().equals(id) && ambito.startsWith(lineasTabla.get(i).getAmbito()) )
        
        return lineasTabla.get(i).getTipo();
        
    }
        return null;
        
    }
    public lineaTabla getlinea(String id,String ambito){
        for(lineaTabla i : lineasTabla){
            if(i.getId().equals(id) && i.getAmbito().equals(ambito))
                return i;
        }
        System.out.println("No se encuentro la linea");
        return null;
        
    }
    public void removeFromTable(String id, String ambito){
        for(lineaTabla i : lineasTabla){
            if(i.getId().equals(id) && i.getAmbito().equals(ambito)){
                lineasTabla.remove(id);
            }
        }
    }
    public boolean existeEnTabla(String id,String ambito){
        
        for(lineaTabla i : lineasTabla){
            if(i.getId().equals(id) && i.getAmbito().equals(ambito)){
              //  System.out.println("\nSi se encuentra en la tabla el simbolo "+id);
                return true;
            }
        }
       // System.out.println("\nNo existe en la tabla el simbolo "+id);
        return false;
    }
    
}
