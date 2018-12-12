/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import database.Database;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import quickpick.AppState;

/**
 *
 * @author Shreyas
 */
public class Client {
    
    private Socket s;
    public DataOutputStream dos;
    public DataInputStream dis;
    public int portNnumber;
    public boolean isStarted;
    private static Client client;
    public static AppState state;
    public static Database database;
    
    private Client() {
        
    }
    
    public static Client getInstance() {
        if (client==null)
        {
            client=new Client();
            state=AppState.getInstance();
        }
        return client;
    }
    
    public void startClient() {
        Thread waiting = new Thread(()->{
            try {
                String ip=state.ip;
                client.s= new Socket(ip,database.portNumber);
                
                client.dis=new DataInputStream(client.s.getInputStream());
                client.dos=new DataOutputStream(client.s.getOutputStream());
                client.isStarted=true;
            } catch (Exception e) {
                
            }
        });
        waiting.start();
    }
    
    
}
