/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codigoIntermedio;
import java.util.ArrayList;
/**
 *
 * @author Kuzuks
 */
public class Lista {
   
    public ArrayList<Integer>lista;
    public TablaCuadruplos tablacuad;
    
    public Lista(){
        this.lista = new ArrayList<Integer>();
        tablacuad=null;
    }
    public Lista(TablaCuadruplos tabla){
        lista = new ArrayList();
        tablacuad = tabla;
    }
    public Lista(int i, TablaCuadruplos tabla){
        lista = new ArrayList();
        lista.add(i);
        tablacuad = tabla;
    }
    public static Lista fusiona(Lista lista1,Lista lista2){
        Lista listaFusion = new Lista(lista1.tablacuad);
        listaFusion.lista.addAll(lista1.lista);
        listaFusion.lista.addAll(lista2.lista);
        return listaFusion;
    }
    public static void completa(Lista lista1,String etiq){
        if(lista1 != null){
            for(Integer index: lista1.lista){
                lista1.tablacuad.res(index.intValue(), etiq);
            }
        }
    }
}
