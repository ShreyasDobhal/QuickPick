/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quickpick;

/**
 *
 * @author Shreyas
 */

import components.Bill;
import database.Database;
import java.util.Arrays;
import offlineDB.CookieFile;
import python.*;
import utility.Time;

/**
 * To Run the project first start the xampp server 
 * Ensure you have the php codes places in C:\xampp\htdocs\QuickPickphp\
 * @author Shreyas
 */
public class QuickPick {

    //public static final String pythonFile = "input.py";
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainPage().setVisible(true);
            }
        });
        
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
                System.out.println("exiting app");
                
            }
        });
                
        
    }
}
