/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pk;

import java.io.Serializable;

/**
 *
 * @author usuario
 */
public class Mensaje implements Serializable{
    int op,valor;
    
    public Mensaje(int o, int v){
        this.op = o;
        this.valor = v;
    }
    
}
