/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.awt.Frame;
import javax.swing.JFrame;
import quickpick.AppState;

/**
 *
 * @author Shreyas
 */
public class Application {
    public static void goToFrame(JFrame frame) {
        AppState states=AppState.getInstance();
        states.frame.setVisible(false);
        states.frame.dispose();
        states.frame=frame;
        states.frame.setVisible(true);
        //states.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    /**
     * Exits the app after cleanup code
     */
    public static void closeApp() {
        
        AppState.getInstance().isRunning=false;
        AppState.getInstance().checkInfo=false;
        
        int cnt=0;
        
        cnt=0;
        for (Frame f:Frame.getFrames()) {
            f.dispose();
            System.out.println("frame ended "+cnt);
            cnt++;
        }
        System.out.println(cnt);
        cnt=0;
        System.out.println("starting to end threads");
        for (Thread t:Thread.getAllStackTraces().keySet()) {
            if (t.getState()==Thread.State.RUNNABLE) {
                System.out.println("ending thread "+cnt);
                cnt++;
                t.stop();
            }
        }
        System.out.println(cnt);
        
        
        System.exit(0);
    }
}
