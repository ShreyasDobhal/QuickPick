/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quickpick;

import database.User;
import javax.swing.JFrame;

/**
 *
 * @author Shreyas
 */

public class AppState {
    
    private static AppState obj;
    public User user;
    public volatile boolean isRunning=false; // Turn this false to stop all daemon threads
    public boolean checkInfo=false; // Turn this false to stop auto email id check and password match
    public JFrame frame; // Currently visible frame
    public boolean dataLoaded; // Flag for taking all products
    public String ip="localhost";
    public String pythonFileDir="C:\\Users\\Shreyas\\Documents\\NetBeansProjects\\QuickPick\\src\\python";
    
    private AppState() {
        
    }
    
    public static AppState getInstance() {
        if (obj==null) {
            obj=new AppState();
            obj.isRunning=true;
        }
        return obj;
    }
    
}
