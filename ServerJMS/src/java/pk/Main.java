/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pk;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
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
        // INICIALIZACIÓN DE VARIABLES
        Context c;
        QueueConnectionFactory f;
        QueueSession qs;
        Queue q;
        QueueReceiver r;
        Mensaje m;
        ObjectMessage om;
        QueueConnection cc;
        int counter = 0;
        
        //SERVIDOR 
        c=new InitialContext();
        f=(QueueConnectionFactory)c.lookup("factoriaConexiones");
        q=(Queue)c.lookup("cola");
        cc = f.createQueueConnection();
        qs=cc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        cc.start();
        
        
        r = qs.createReceiver(q);

        
        while(counter<=100){
            om=(ObjectMessage)r.receive(0);
            m=(Mensaje)om.getObject();
            if(m.op == 1){
                counter += m.valor;
            }else if(m.op == 2){
                counter -= m.valor;
            }else if(m.op == 0){
                break;
            }
             
        }
            
            

        
        

    }
    
}
