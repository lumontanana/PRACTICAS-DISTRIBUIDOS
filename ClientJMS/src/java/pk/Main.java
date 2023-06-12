/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pk;

import java.util.Scanner;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
/**
 *
 * @author usuario
 */
public class Main {
    
    


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NamingException, JMSException {
        //DECLARACIÓN DE VARIABLES
        Context c;
        QueueConnectionFactory f;
        QueueSession qs;
        Queue q;
        QueueSender s;
        Mensaje m;
        ObjectMessage om;
        QueueConnection cc;
        int op, valor;
        Scanner leer;
        
        //CÓDIGO ESTANDAR EN JMS QUE PERMITE INICIALIZAR EL CONTEXTO JNDI Y LO CALIZAR LA FACTORIA DE CONEXIONES DE COLAS Y LA COLA DE MENSAJES
        c = new InitialContext();
        f = (QueueConnectionFactory)c.lookup("factoriaConexiones"); //BUSCA LA COLA DE CONEXIONES 
        q = (Queue)c.lookup("cola"); // BUSCA EN EL JNDI LA COLA 
        cc = f.createQueueConnection();  //CREA LA COLA DE CONEXIONES
        qs= cc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE); //CREA UNA SESIÓN 
        cc.start(); 
        
        //CREAMOS EL EMISOR
        s=qs.createSender(q);
        
        //BUCLE
        leer = new Scanner(System.in);// Leemos por pantalla el codigo de operación
       
        do{
            System.out.println("Introduzca el código de operación: 1. Suma, 2. Resta, 0. Exit");
            op = leer.nextInt();
            System.out.println("Introduzca el valor");
            valor = leer.nextInt();
            m = new Mensaje(op, valor);
            om=qs.createObjectMessage(m);
            s.send(om);
        }while(op != 0);

        
        
        
        
        
    }
    
}
