/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codigoIntermedio;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import semantico.*;
import java.util.ArrayList;
/**
 *
 * @author Kuzuks
 */
public class CodigoFinal {
    ArrayList<Cuadruplo> cuadruplos;
    tablaSimbolos simbolos;
    public ArrayList<String> codigo;
    ArrayList<String> mensajes;
     ArrayList<String> vars= new ArrayList<String>();
    public String temporales[][];
   public int contador=0;
    
   public CodigoFinal(ArrayList<Cuadruplo> cuadruplos,tablaSimbolos simbolos,ArrayList<String> mensajes){
     this.cuadruplos = cuadruplos;
     this.simbolos = simbolos;
     this.codigo = new ArrayList();
     this.mensajes = mensajes;  
     this.temporales = new String[8][2];
      for (int i = 0; i < 8; i++) {
            temporales[i][0] = "0";
            temporales[i][1] = "-1";
        }
   }
    public int temp_nuevo() {
        for (int i = 0; i < 8; i++) {
            if (temporales[i][0].equals("0")) {
                temporales[i][0] = "1";
                return i;
            }
        }
        return -1;
    }
    public int getTemp(String s) {
        for (int i = 0; i < 8; i++) {
            if (temporales[i][1].equals(s)) {
                return i;
            }
        }
        return -1;
    }
    
    public void generarFinal(){
        codigo.add("    .data");
        System.out.println("size "+ simbolos.getLineasTabla().size());
        for(int i=0;i<simbolos.getLineasTabla().size();i++){
            
            if(simbolos.getLineasTabla().get(i).getTipo() instanceof charType){
                vars.add(simbolos.getLineasTabla().get(i).getAmbito().replace('.','_')+
                    "." + simbolos.getLineasTabla().get(i).getId());
                codigo.add( "_" + simbolos.getLineasTabla().get(i).getId() + ":  .byte ");
            }  
            
            if(simbolos.getLineasTabla().get(i).getTipo() instanceof integerType){ 
               vars.add(simbolos.getLineasTabla().get(i).getAmbito().replace('.','_')+
                    "." + simbolos.getLineasTabla().get(i).getId());
                
                codigo.add("_" + simbolos.getLineasTabla().get(i).getId() + ":   .word ");
            } 
            
            if(simbolos.getLineasTabla().get(i).getTipo() instanceof floatType){
                vars.add(simbolos.getLineasTabla().get(i).getAmbito().replace('.','_')+
                    "." + simbolos.getLineasTabla().get(i).getId());
                codigo.add("_" + simbolos.getLineasTabla().get(i).getId() + ":   .word ");
            } 
         }
        
        
        for(int i=0;i<mensajes.size();i++){
            codigo.add("_msg"+i+":    .asciiz "+mensajes.get(i) );
        }
        codigo.add("\n");
        codigo.add("    .text");
        codigo.add("    .globl _main");
        //codigo.add("main:");
        //codigo.add(" move $fp,$sp");
        int a=0;
        String val ="";
        String lineaActual ="";
      
        for(int i =0; i< cuadruplos.size();i++){
            String opcion = cuadruplos.get(i).op;
            
            if(opcion.equals("*")){
                val = validarCuad(i);
                lineaActual ="";
                if (val.charAt(0) == '1') {
                        a = temp_nuevo();
                        temporales[a][1] = cuadruplos.get(i).res;
                        lineaActual = lineaActual + "$t" + a;
                    } else {
                        lineaActual = lineaActual + cuadruplos.get(i).res;
                    }
                    if (val.charAt(1) == '1') {
                        a = getTemp(cuadruplos.get(i).arg1);
                        lineaActual = lineaActual + ", $t" + a;

                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    } else {
                        boolean temp_arg1 = simbolos.existeEnTabla(cuadruplos.get(i).arg1,cuadruplos.get(i).ambito);
                        if(temp_arg1){
                            String var = getVar(cuadruplos.get(i).arg1,cuadruplos.get(i).ambito.replace('.','_') + '_');
                            a = temp_nuevo();
                            temporales[a][1] = cuadruplos.get(i).arg1;
                            codigo.add("lw $t" + a + ", " +  var.substring(var.indexOf('.')).replace('.', '_'));
                            lineaActual = lineaActual + ", $t" + a;
                    }else{
                        codigo.add("li $t"+a +","+cuadruplos.get(i).arg1);
                    lineaActual = lineaActual +", "+"$t"+a;
                    }}
                    if (val.charAt(2) == '1') {
                        a = getTemp(cuadruplos.get(i).arg2);
                        lineaActual = lineaActual + ", $t" + a;
                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    } else {
                        boolean temp_arg2 = simbolos.existeEnTabla(cuadruplos.get(i).arg2,cuadruplos.get(i).ambito);
                        if(temp_arg2){
                            String var = getVar(cuadruplos.get(i).arg2,cuadruplos.get(i).ambito.replace('.','_') + '_');
                            a = temp_nuevo();
                            temporales[a][1] = cuadruplos.get(i).arg2;
                            codigo.add("lw $t" + a + ", " +  var.substring(var.indexOf('.')).replace('.', '_'));
                            lineaActual = lineaActual + ", $t" + a;
                    }else{
                        lineaActual = lineaActual + ", " + cuadruplos.get(i).arg2;
                    }
                    }
                codigo.add("mult "+lineaActual);
                
            }else if(opcion.equals("/")){
                val = validarCuad(i);
                lineaActual ="";
                if (val.charAt(0) == '1') {
                        a = temp_nuevo();
                        temporales[a][1] = cuadruplos.get(i).res;
                        lineaActual = lineaActual + "$t" + a;
                    } else {
                        lineaActual = lineaActual + cuadruplos.get(i).res;
                    }
                    if (val.charAt(1) == '1') {
                        a = getTemp(cuadruplos.get(i).arg1);
                        lineaActual = lineaActual + ", $t" + a;

                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    } else {
                        boolean temp_arg1 = simbolos.existeEnTabla(cuadruplos.get(i).arg1,cuadruplos.get(i).ambito);
                        if(temp_arg1){
                            String var = getVar(cuadruplos.get(i).arg1,cuadruplos.get(i).ambito.replace('.','_') + '_');
                            a = temp_nuevo();
                            temporales[a][1] = cuadruplos.get(i).arg1;
                            codigo.add("lw $t" + a + ", " +  var.substring(var.indexOf('.')).replace('.', '_'));
                            lineaActual = lineaActual + ", $t" + a;
                    }else{
                        codigo.add("li $t"+a +","+cuadruplos.get(i).arg1);
                    lineaActual = lineaActual +", "+"$t"+a;
                    }}
                    if (val.charAt(2) == '1') {
                        a = getTemp(cuadruplos.get(i).arg2);
                        lineaActual = lineaActual + ", $t" + a;
                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    } else {
                        boolean temp_arg2 = simbolos.existeEnTabla(cuadruplos.get(i).arg2,cuadruplos.get(i).ambito);
                        if(temp_arg2){
                            String var = getVar(cuadruplos.get(i).arg2,cuadruplos.get(i).ambito.replace('.','_') + '_');
                            a = temp_nuevo();
                            temporales[a][1] = cuadruplos.get(i).arg2;
                            codigo.add("lw $t" + a + ", " +  var.substring(var.indexOf('.')).replace('.', '_'));
                            lineaActual = lineaActual + ", $t" + a;
                    }else{
                        lineaActual = lineaActual + ", " + cuadruplos.get(i).arg2;
                    }
                    }

                    codigo.add("div " + lineaActual);
            }else if(opcion.equals("+")){
                val = validarCuad(i);
                lineaActual ="";
                if (val.charAt(0) == '1') {
                        a = temp_nuevo();
                        temporales[a][1] = cuadruplos.get(i).res;
                        lineaActual = lineaActual + "$t" + a;
                    } else {
                        lineaActual = lineaActual + cuadruplos.get(i).res;
                    }
                    if (val.charAt(1) == '1') {
                        a = getTemp(cuadruplos.get(i).arg1);
                        lineaActual = lineaActual + ", $t" + a;

                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    } else {
                        boolean temp_arg1 = simbolos.existeEnTabla(cuadruplos.get(i).arg1,cuadruplos.get(i).ambito);
                        if(temp_arg1){
                            String var = getVar(cuadruplos.get(i).arg1,cuadruplos.get(i).ambito.replace('.','_') + '_');
                            a = temp_nuevo();
                            temporales[a][1] = cuadruplos.get(i).arg1;
                            codigo.add("lw $t" + a + ", " +  var.substring(var.indexOf('.')).replace('.', '_'));
                            lineaActual = lineaActual + ", $t" + a;
                    }else{
                        codigo.add("li $t"+a +","+cuadruplos.get(i).arg1);
                    lineaActual = lineaActual +", "+"$t"+a;
                    }}
                    if (val.charAt(2) == '1') {
                        a = getTemp(cuadruplos.get(i).arg2);
                        lineaActual = lineaActual + ", $t" + a;
                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    } else {
                        boolean temp_arg2 = simbolos.existeEnTabla(cuadruplos.get(i).arg2,cuadruplos.get(i).ambito);
                        if(temp_arg2){
                            String var = getVar(cuadruplos.get(i).arg2,cuadruplos.get(i).ambito.replace('.','_') + '_');
                            a = temp_nuevo();
                            temporales[a][1] = cuadruplos.get(i).arg2;
                            codigo.add("lw $t" + a + ", " +  var.substring(var.indexOf('.')).replace('.', '_'));
                            lineaActual = lineaActual + ", $t" + a;
                    }else{
                        lineaActual = lineaActual + ", " + cuadruplos.get(i).arg2;
                    }
                    }
                     a = getTemp(cuadruplos.get(i).arg1);
                    if(a!=-1){
                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    }
                    a = getTemp(cuadruplos.get(i).arg2);
                    if(a!=-1){
                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    }
                    codigo.add("add " + lineaActual);
                    
            }else if(opcion.equals("-")){
                val = validarCuad(i);
                lineaActual ="";
                if (val.charAt(0) == '1') {
                        a = temp_nuevo();
                        temporales[a][1] = cuadruplos.get(i).res;
                        lineaActual = lineaActual + "$t" + a;
                    } else {
                        lineaActual = lineaActual + cuadruplos.get(i).res;
                    }
                    if (val.charAt(1) == '1') {
                        a = getTemp(cuadruplos.get(i).arg1);
                        lineaActual = lineaActual + ", $t" + a;

                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    } else {
                        boolean temp_arg1 = simbolos.existeEnTabla(cuadruplos.get(i).arg1,cuadruplos.get(i).ambito);
                        if(temp_arg1){
                            String var = getVar(cuadruplos.get(i).arg1,cuadruplos.get(i).ambito.replace('.','_') + '_');
                            a = temp_nuevo();
                            temporales[a][1] = cuadruplos.get(i).arg1;
                            codigo.add("lw $t" + a + ", _" +  var.substring(var.indexOf('.')).replace('.', '_'));
                            lineaActual = lineaActual + ", $t" + a;
                    }else{
                        codigo.add("li $t"+a +","+cuadruplos.get(i).arg1);
                    lineaActual = lineaActual +", "+"$t"+a;
                    }}
                    if (val.charAt(2) == '1') {
                        a = getTemp(cuadruplos.get(i).arg2);
                        lineaActual = lineaActual + ", $t" + a;
                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    } else {
                        boolean temp_arg2 = simbolos.existeEnTabla(cuadruplos.get(i).arg2,cuadruplos.get(i).ambito);
                        if(temp_arg2){
                            String var = getVar(cuadruplos.get(i).arg2,cuadruplos.get(i).ambito.replace('.','_') + '_');
                            a = temp_nuevo();
                            temporales[a][1] = cuadruplos.get(i).arg2;
                            codigo.add("lw $t" + a + ", _" +  var.substring(var.indexOf('.')).replace('.', '_'));
                            lineaActual = lineaActual + ", $t" + a;
                    }else{
                        lineaActual = lineaActual + ", " + cuadruplos.get(i).arg2;
                    }
                    }
                     a = getTemp(cuadruplos.get(i).arg1);
                    if(a!=-1){
                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    }
                    a = getTemp(cuadruplos.get(i).arg2);
                    if(a!=-1){
                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    }
                    codigo.add("sub " + lineaActual);
            }else if(opcion.equals("++")){
                val = validarCuad(i);
                lineaActual ="";
                if (val.charAt(0) == '1') {
                        a = temp_nuevo();
                        temporales[a][1] = cuadruplos.get(i).res;
                        lineaActual = lineaActual + "$t" + a;
                    } else {
                        lineaActual = lineaActual + cuadruplos.get(i).res;
                    }
                    if (val.charAt(1) == '1') {
                        a = getTemp(cuadruplos.get(i).arg1);
                        lineaActual = lineaActual + ", $t" + a;

                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    } else {
                        boolean temp_arg1 = simbolos.existeEnTabla(cuadruplos.get(i).arg1,cuadruplos.get(i).ambito);
                        if(temp_arg1){
                            String var = getVar(cuadruplos.get(i).arg1,cuadruplos.get(i).ambito.replace('.','_') + '_');
                            a = temp_nuevo();
                            temporales[a][1] = cuadruplos.get(i).arg1;
                            codigo.add("lw $t" + a + ", _" +  var.substring(var.indexOf('.')).replace('.', '_'));
                            lineaActual = lineaActual + ", $t" + a;
                    }else{
                        codigo.add("li $t"+a +","+cuadruplos.get(i).arg1);
                    lineaActual = lineaActual +", "+"$t"+a;
                    }}
                    if (val.charAt(2) == '1') {
                        a = getTemp(cuadruplos.get(i).arg1);
                        lineaActual = lineaActual + ", $t" + a;
                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    
                    }else{
                        lineaActual = lineaActual + ", " + cuadruplos.get(i).arg1;
                    
                    }
                codigo.add("add "+lineaActual);
            }else if(opcion.equals("--")){
                val = validarCuad(i);
                lineaActual ="";
                if (val.charAt(0) == '1') {
                        a = temp_nuevo();
                        temporales[a][1] = cuadruplos.get(i).res;
                        lineaActual = lineaActual + "$t" + a;
                    } else {
                        lineaActual = lineaActual + cuadruplos.get(i).res;
                    }
                    if (val.charAt(1) == '1') {
                        a = getTemp(cuadruplos.get(i).arg1);
                        lineaActual = lineaActual + ", $t" + a;

                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    } else {
                        boolean temp_arg1 = simbolos.existeEnTabla(cuadruplos.get(i).arg1,cuadruplos.get(i).ambito);
                        if(temp_arg1){
                            String var = getVar(cuadruplos.get(i).arg1,cuadruplos.get(i).ambito.replace('.','_') + '_');
                            a = temp_nuevo();
                            temporales[a][1] = cuadruplos.get(i).arg1;
                            codigo.add("lw $t" + a + ", _" +  var.substring(var.indexOf('.')).replace('.', '_'));
                            lineaActual = lineaActual + ", $t" + a;
                    }else{
                        codigo.add("li $t"+a +","+cuadruplos.get(i).arg1);
                    lineaActual = lineaActual +", "+"$t"+a;
                    }}
                    if (val.charAt(2) == '1') {
                        a = getTemp(cuadruplos.get(i).arg1);
                        lineaActual = lineaActual + ", $t" + a;
                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    
                    }else{
                        lineaActual = lineaActual + ", " + cuadruplos.get(i).arg1;
                    
                    }
                codigo.add("sub "+lineaActual);
            }/*else if(opcion.equals("=")){
                System.out.println("antesisismo");
                val = validarCuad(i);
               // val ="11";
                lineaActual ="";
                System.out.println("antes de bien");
                if(val.charAt(0)== '1'){
                    
                    a= temp_nuevo();
                    
                    temporales[a][1]= cuadruplos.get(i).res;
                    lineaActual = lineaActual + "$t"+a;
                    System.out.println("aca bien");
                  
                }else{
                     boolean temp_arg2 = simbolos.existeEnTabla(cuadruplos.get(i).res,cuadruplos.get(i).ambito);
                     System.out.println("antes");
                        if(temp_arg2 ){
                            String var = getVar(cuadruplos.get(i).res,cuadruplos.get(i).ambito.replace('.','_') + '_');
                            System.out.println("despuesss");
                            lineaActual = lineaActual + var.substring(var.indexOf('.')).replace('.', '_');
                        } else{
                            lineaActual = lineaActual + cuadruplos.get(i).res;
                        }
                }
                 if(val.charAt(i)=='1'){
                     System.out.println("acaaaaaaaaaa2");
                     a=getTemp(cuadruplos.get(i).arg1);
                     lineaActual = lineaActual + ", $t"+a;
                     //temporales[a][0] = "0";
                     //temporales[a][1] = "";
                 }else{
                      System.out.println("acaaaaaaaaaa3");
                     boolean temp_arg1 = simbolos.existeEnTabla(cuadruplos.get(i).arg1,cuadruplos.get(i).ambito);
                        if(temp_arg1){
                            System.out.println("acaaaaaaaaaa4");
                            String var = getVar(cuadruplos.get(i).arg1,cuadruplos.get(i).ambito.replace('.','_') + '_');
                            a = temp_nuevo();
                            temporales[a][1] = cuadruplos.get(i).arg1;
                            codigo.add("lw $t" + a + ", " + var.substring(var.indexOf('.')).replace('.', '_'));
                            lineaActual = lineaActual + ", " + a;
                        } else{
                            lineaActual = lineaActual + ", " + cuadruplos.get(i).arg1;
                            System.out.println("acaaaaaaaaaa5");
                        }
                 }
                 System.out.println("acaaaaaaaaaa6");
               a = getTemp(cuadruplos.get(i).arg1);
                    if(a!=-1){
                       // temporales[a][0] = "0";
                       // temporales[a][1] = "";
                        System.out.println("acaaaaaaaaaa7");
                    }
                   
                    codigo.add("sw "+lineaActual);
            }*/
        else if(opcion.equals("if>")){
            val = validarCuad(i);
                    lineaActual = "";
                   
                    if (val.charAt(1) == '1') {
                        a = getTemp(cuadruplos.get(i).arg1);
                        lineaActual = lineaActual + " $t" + a;

                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    } else {
                         boolean temp_arg1 = simbolos.existeEnTabla(cuadruplos.get(i).arg1,cuadruplos.get(i).ambito);
                         if(temp_arg1){
                             a = temp_nuevo();
                            temporales[a][1] = cuadruplos.get(i).arg1;
                            codigo.add("lw $t" + a + ", " + cuadruplos.get(i).arg1);
                            lineaActual = lineaActual + " $t" + a;
                            //lineaActual = lineaActual + " " + cuadruplos.get(i).arg1;
                         }else{
                             a = temp_nuevo();
                              codigo.add("lw $t" + a + ", " + cuadruplos.get(i).arg1);
                                lineaActual = lineaActual + " $t" + a;
                         }
                    }
                    if (val.charAt(2) == '1') {
                        a = getTemp(cuadruplos.get(i).arg2);
                        lineaActual = lineaActual + ", $t" + a;
                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    } else {
                       boolean temp_arg2 = simbolos.existeEnTabla(cuadruplos.get(i).arg2,cuadruplos.get(i).ambito);
                         if(temp_arg2){
                             a = temp_nuevo();
                            temporales[a][1] = cuadruplos.get(i).arg2;
                            codigo.add("li $t" + a + ", " + cuadruplos.get(i).arg2);

                            lineaActual = lineaActual + " $t" + a;
                            //lineaActual = lineaActual + " " + cuadruplos.get(i).arg1;
                         }else{
                             a = temp_nuevo();
                              codigo.add("li $t" + a + ", " + cuadruplos.get(i).arg2);
                                lineaActual = lineaActual + " $t" + a;
                         }
                    }
                    if (val.charAt(0) == '1') {
                        a = temp_nuevo();
                        temporales[a][1] = cuadruplos.get(i).res;
                        lineaActual = lineaActual + "$t" + a;
                    } else {
                        lineaActual = lineaActual + ", _etiq" + cuadruplos.get(i).res;
                    }
                    a = getTemp(cuadruplos.get(i).arg1);
                    if(a!=-1){
                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    }
                    a = getTemp(cuadruplos.get(i).arg2);
                    if(a!=-1){
                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    }
                    codigo.add("bgt " + lineaActual);
                    
        }else if(opcion.equals("if<")){
              val = validarCuad(i);
                    lineaActual = "";
                   
                    if (val.charAt(1) == '1') {
                        a = getTemp(cuadruplos.get(i).arg1);
                        lineaActual = lineaActual + " $t" + a;

                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    } else {
                         boolean temp_arg1 = simbolos.existeEnTabla(cuadruplos.get(i).arg1,cuadruplos.get(i).ambito);
                         if(temp_arg1){
                             a = temp_nuevo();
                            temporales[a][1] = cuadruplos.get(i).arg1;
                            codigo.add("lw $t" + a + ", " + cuadruplos.get(i).arg1);
                            lineaActual = lineaActual + " $t" + a;
                            //lineaActual = lineaActual + " " + cuadruplos.get(i).arg1;
                         }else{
                             a = temp_nuevo();
                              codigo.add("lw $t" + a + ", " + cuadruplos.get(i).arg1);
                                lineaActual = lineaActual + " $t" + a;
                         }
                    }
                    if (val.charAt(2) == '1') {
                        a = getTemp(cuadruplos.get(i).arg2);
                        lineaActual = lineaActual + ", $t" + a;
                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    } else {
                       boolean temp_arg2 = simbolos.existeEnTabla(cuadruplos.get(i).arg2,cuadruplos.get(i).ambito);
                         if(temp_arg2){
                             a = temp_nuevo();
                            temporales[a][1] = cuadruplos.get(i).arg2;
                            codigo.add("li $t" + a + ", " + cuadruplos.get(i).arg2);

                            lineaActual = lineaActual + " $t" + a;
                            //lineaActual = lineaActual + " " + cuadruplos.get(i).arg1;
                         }else{
                             a = temp_nuevo();
                              codigo.add("li $t" + a + ", " + cuadruplos.get(i).arg2);
                                lineaActual = lineaActual + " $t" + a;
                         }
                    }
                    if (val.charAt(0) == '1') {
                        a = temp_nuevo();
                        temporales[a][1] = cuadruplos.get(i).res;
                        lineaActual = lineaActual + "$t" + a;
                    } else {
                        lineaActual = lineaActual + ", _etiq" + cuadruplos.get(i).res;
                    }
                    a = getTemp(cuadruplos.get(i).arg1);
                    if(a!=-1){
                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    }
                    a = getTemp(cuadruplos.get(i).arg2);
                    if(a!=-1){
                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    }
                    codigo.add("blt " + lineaActual);
                    
        }else if(opcion.equals("if==")){
            val = validarCuad(i);
                    lineaActual = "";
                    

                    if (val.charAt(1) == '1') {
                        a = getTemp(cuadruplos.get(i).arg1);
                        lineaActual = lineaActual + " $t" + a;

                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    } else {
                         a = temp_nuevo();
                        temporales[a][1] = cuadruplos.get(i).arg1;
                        codigo.add("lw $t" + a + ", " + cuadruplos.get(i).arg1);

                        lineaActual = lineaActual + " $t" + a;
                        //lineaActual = lineaActual + " " + cuadruplos.get(i).arg1;
                    }
                    if (val.charAt(2) == '1') {
                        a = getTemp(cuadruplos.get(i).arg2);
                        lineaActual = lineaActual + ", $t" + a;
                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    } else {
                         a = temp_nuevo();
                        temporales[a][1] = cuadruplos.get(i).arg2;
                        codigo.add("lw $t" + a + ", " + cuadruplos.get(i).arg2);

                        lineaActual = lineaActual + " $t" + a;
                        //lineaActual = lineaActual + ", " + cuadruplos.get(i).arg2;
                    }
                    if (val.charAt(0) == '1') {
                        a = temp_nuevo();
                        temporales[a][1] = cuadruplos.get(i).res;
                        lineaActual = lineaActual + "$t" + a;
                    } else {
                        lineaActual = lineaActual + ", _etiq" + cuadruplos.get(i).res;
                    }
                    a = getTemp(cuadruplos.get(i).arg1);
                    if(a!=-1){
                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    }
                    a = getTemp(cuadruplos.get(i).arg2);
                    if(a!=-1){
                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    }
                    codigo.add("beq " + lineaActual);
                   
        }else if(opcion.equals("if!=")){
            val = validarCuad(i);
                    lineaActual = "";
                    
                    if (val.charAt(1) == '1') {
                        a = getTemp(cuadruplos.get(i).arg1);
                        lineaActual = lineaActual + " $t" + a;

                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    } else {
                         a = temp_nuevo();
                        temporales[a][1] = cuadruplos.get(i).arg1;
                        codigo.add("lw $t" + a + ", " + cuadruplos.get(i).arg1);

                        lineaActual = lineaActual + " $t" + a;
                        //lineaActual = lineaActual + " " + cuadruplos.get(i).arg1;
                    }
                    if (val.charAt(2) == '1') {
                        a = getTemp(cuadruplos.get(i).arg2);
                        lineaActual = lineaActual + ", $t" + a;
                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    } else {
                         a = temp_nuevo();
                        temporales[a][1] = cuadruplos.get(i).arg2;
                        codigo.add("lw $t" + a + ", " + cuadruplos.get(i).arg2);

                        lineaActual = lineaActual + " $t" + a;
                        //lineaActual = lineaActual + ", " + cuadruplos.get(i).arg2;
                    }
                    if (val.charAt(0) == '1') {
                        a = temp_nuevo();
                        temporales[a][1] = cuadruplos.get(i).res;
                        lineaActual = lineaActual + "$t" + a;
                    } else {
                        lineaActual = lineaActual + ", _etiq" + cuadruplos.get(i).res;
                    }
                    a = getTemp(cuadruplos.get(i).arg1);
                    if(a!=-1){
                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    }
                    a = getTemp(cuadruplos.get(i).arg2);
                    if(a!=-1){
                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    }
                    codigo.add("bne " + lineaActual);
                   
        }else if(opcion.equals("if>=")){
            val = validarCuad(i);
                    lineaActual = "";
                    
                    if (val.charAt(1) == '1') {
                        a = getTemp(cuadruplos.get(i).arg1);
                        lineaActual = lineaActual + " $t" + a;

                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    } else {
                         a = temp_nuevo();
                        temporales[a][1] = cuadruplos.get(i).arg1;
                        codigo.add("lw $t" + a + ", " + cuadruplos.get(i).arg1);

                        lineaActual = lineaActual + " $t" + a;
                        //lineaActual = lineaActual + " " + cuadruplos.get(i).arg1;
                    }
                    if (val.charAt(2) == '1') {
                        a = getTemp(cuadruplos.get(i).arg2);
                        lineaActual = lineaActual + ", $t" + a;
                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    } else {
                         a = temp_nuevo();
                        temporales[a][1] = cuadruplos.get(i).arg2;
                        codigo.add("lw $t" + a + ", " + cuadruplos.get(i).arg2);

                        lineaActual = lineaActual + " $t" + a;
                        //lineaActual = lineaActual + ", " + cuadruplos.get(i).arg2;
                    }
                    if (val.charAt(0) == '1') {
                        a = temp_nuevo();
                        temporales[a][1] = cuadruplos.get(i).res;
                        lineaActual = lineaActual + "$t" + a;
                    } else {
                        lineaActual = lineaActual + ", _etiq" + cuadruplos.get(i).res;
                    }
                    a = getTemp(cuadruplos.get(i).arg1);
                    if(a!=-1){
                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    }
                    a = getTemp(cuadruplos.get(i).arg2);
                    if(a!=-1){
                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    }
                    codigo.add("bge " + lineaActual);
                    
        }else if(opcion.equals("if<=")){
            val = validarCuad(i);
                    lineaActual = "";
                   
                    if (val.charAt(1) == '1') {
                        a = getTemp(cuadruplos.get(i).arg1);
                        lineaActual = lineaActual + " $t" + a;

                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    } else {
                         a = temp_nuevo();
                        temporales[a][1] = cuadruplos.get(i).arg1;
                        codigo.add("lw $t" + a + ", " + cuadruplos.get(i).arg1);

                        lineaActual = lineaActual + " $t" + a;
                        //lineaActual = lineaActual + " " + cuadruplos.get(i).arg1;
                    }
                    if (val.charAt(2) == '1') {
                        a = getTemp(cuadruplos.get(i).arg2);
                        lineaActual = lineaActual + ", $t" + a;
                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    } else {
                         a = temp_nuevo();
                        temporales[a][1] = cuadruplos.get(i).arg2;
                        codigo.add("lw $t" + a + ", " + cuadruplos.get(i).arg2);

                        lineaActual = lineaActual + " $t" + a;
                        //lineaActual = lineaActual + ", " + cuadruplos.get(i).arg2;
                    }
                    if (val.charAt(0) == '1') {
                        a = temp_nuevo();
                        temporales[a][1] = cuadruplos.get(i).res;
                        lineaActual = lineaActual + "$t" + a;
                    } else {
                        lineaActual = lineaActual + ", _etiq" + cuadruplos.get(i).res;
                    }
                    a = getTemp(cuadruplos.get(i).arg1);
                    if(a!=-1){
                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    }
                    a = getTemp(cuadruplos.get(i).arg2);
                    if(a!=-1){
                        temporales[a][0] = "0";
                        temporales[a][1] = "";
                    }
                    codigo.add("ble " + lineaActual);
                    
        }else if(opcion.equals("Etiq")){
            codigo.add("\n"+cuadruplos.get(i).res+": ");
        }else if(opcion.equals("ret")){
            if (!cuadruplos.get(i).res.equals("")) {
                        if (cuadruplos.get(i).res.codePointAt(0) == 't') {
                            a = getTemp(cuadruplos.get(i).res);
                            codigo.add("move $v0, $t" );

                            //temporales[a][0] = "0";
                           // temporales[a][1] = "";
                        }else if(!simbolos.existeEnTabla(cuadruplos.get(i).res, cuadruplos.get(i).ambito)) {
                            codigo.add("li $v0, " + cuadruplos.get(i).res);
                        }
                    }
                    codigo.add("move $sp,$fp");
                    codigo.add("lw $fp,-4($sp)");
                    codigo.add("lw $ra,-8($sp)");
                    codigo.add("jr $ra");
        }else if(opcion.equals("GOTO")){
            codigo.add("b _etiq"+ cuadruplos.get(i).res);
        }else if(opcion.equals("param")){
            
            String par = cuadruplos.get(i).res;
            //temporales[a][1] = cuadruplos.get(i).res;
            codigo.add("lw $a"+contador+", _"+par);
            contador++;
        }else if(opcion.equals("call")){
            codigo.add("jal "+ cuadruplos.get(i).res);
            
        }else if(opcion.equals("printf")){
            //for(int i=0,i<simbolos.getTipo (val, opcion))z
            if(simbolos.existeEnTabla(cuadruplos.get(i).res,cuadruplos.get(i).ambito)){
            type tipo = simbolos.getlinea(cuadruplos.get(i).res, cuadruplos.get(i).ambito).getTipo();
            if(tipo instanceof integerType){
                codigo.add("li $v0, 1");
                codigo.add("la $a"+contador+", _"+cuadruplos.get(i).res);
                codigo.add("syscall");
            }else if(tipo instanceof floatType){
                codigo.add("li $v0, 2");
                codigo.add("la $a"+contador+", _"+cuadruplos.get(i).res);
                codigo.add("syscall");
            }else if(tipo instanceof stringType){
                codigo.add("li $v0, 4");
                codigo.add("la $a"+contador+", _"+cuadruplos.get(i).res);
                codigo.add("syscall");
            }
            }else {
                int numero=mensajes.indexOf(cuadruplos.get(i).res);
                codigo.add("li $v0, 4");
                codigo.add("la $a"+contador+", _msg"+numero);
                codigo.add("syscall");
            }
        }else if(opcion.equals("scanf")){
            System.out.println("passssssooo");
             if(simbolos.existeEnTabla(cuadruplos.get(i).res,cuadruplos.get(i).ambito)){
                 
            type tipo = simbolos.getlinea(cuadruplos.get(i).res, cuadruplos.get(i).ambito).getTipo();
                if(tipo instanceof integerType){
                    codigo.add("li $v0, 5");
                    codigo.add("syscall");
                }else if(tipo instanceof floatType){
                     codigo.add("li $v0, 6");
                    codigo.add("syscall");
                }else if(tipo instanceof stringType){
                     codigo.add("li $v0, 8");
                    codigo.add("syscall");
                }
            }
        }
        }
        codigo.add("li $v0,10");
        codigo.add("syscall");
        
    }
    public String validarCuad(int numero){
        String s = "";
        if(!cuadruplos.get(numero).res.equals("")){
            if(cuadruplos.get(numero).res.codePointAt(0)=='t'){
                s=s + "1";
            }else{
                s=s+"0";
            }
        }else{
            s=s+"0";
        }
        if(!cuadruplos.get(numero).arg1.equals("")){
            if(cuadruplos.get(numero).arg1.codePointAt(0)=='t'){
                s=s+"1";
            }else{
                s=s+"0";
            }
        }else{
            s=s+"0";
        }
        if(!cuadruplos.get(numero).arg2.equals("")){
            if(cuadruplos.get(numero).arg2.codePointAt(0)=='t'){
                s=s+"1";
            }else{
                s=s+"0";
            }
        }else{
            s=s+"0";
        }
        return s;
                
    }
    public boolean jump(int numero){
        for(int i=0;i<cuadruplos.size();i++){
            if((cuadruplos.get(i).op.charAt(0)== 'i' && cuadruplos.get(i).op.charAt(1)=='f')|| cuadruplos.get(i).op.equals("GOTO")){
                int num = Integer.parseInt(cuadruplos.get(i).res);
                if(numero ==num){
                    return true;
                }
            }
        }
        return false;
    }
    public String getVar(String id,String ambito){
        for(String s: this.vars){
            if(ambito.startsWith(s.substring(0,s.indexOf('.')))&& s.substring(s.indexOf('.')+1).equals(id)){
                System.out.println("encontrado");
                return s;
                
            }
        }
        System.out.println("no encontrado");
        return "";
    }
         
    public void escribir() throws IOException{
        File file = new File("src/outputs/CodigoFinal.asm");
          if (!file.exists()) {
              file.createNewFile();
          } 
          PrintWriter out = new PrintWriter((new FileOutputStream(file)));
         for(String i: codigo){
             out.println(i);
             System.out.println(i.toString());
         }
            out.close();
            
    }
}
