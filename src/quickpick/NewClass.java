/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quickpick;

// NOTE: delete this 

import python.pyExecuter;

/**
 *
 * @author Shreyas
 */
public class NewClass {
    public static void main(String args[]) {
        pyExecuter pyexe=new pyExecuter();
        pyexe.runPython("plot.py","", (out)->{
            System.out.println(out);
        });
    }
}
