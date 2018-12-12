/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Shreyas
 */
public class Server {
    
    private ServerSocket ss;
    private Socket s;
    public DataOutputStream dos;
    public DataInputStream dis;
    public int portNnumber;
    public boolean isStarted;
    
    private static Server server;
    
    private Server() {
        
    }
    
    public static Server getInstance() {
        if (server==null)
            server=new Server();
        return server;
    }
    
    public void startServer() {
        Thread waiting = new Thread(()->{
            try {
                server.ss=new ServerSocket(server.portNnumber);
                server.s=server.ss.accept();
                
                server.dis=new DataInputStream(server.s.getInputStream());
                server.dos=new DataOutputStream(server.s.getOutputStream());
                server.isStarted=true;
            } catch (Exception e) {
                
            }
        });
        waiting.start();
    }
    
    
}
