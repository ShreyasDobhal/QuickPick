/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package python;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import quickpick.*;
/**
 *
 * @author Shreyas
 */
public class pyExecuter {
    
    public static final String pythonFile = "test.py";
    public static final String fileDir = AppState.getInstance().pythonFileDir;   //"C:\\Users\\Shreyas\\Documents\\NetBeansProjects\\QuickPick\\src\\python";
    public static final String path = "C:\\Python27";
    
    ProcessBuilder pb;
    Process pro;
    BufferedReader br;
    

    public pyExecuter() {
        
    }
    /**
     * Executes specified python code in a separate thread
     * and prints the output when it terminates
     * @param pythonFile 
     */
    public void runPython(String pythonFile) {
        ProcessBuilder pb;
        Process pro;
        BufferedReader br;
        
        try {
            pb = new ProcessBuilder ("cmd","/C","python "+fileDir+"\\"+pythonFile);
            pb.directory(new File(path));
            pro = pb.start();
            br = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            
            Thread output = new Thread(()->{
                try {
                    String out="";
                    while ((out=br.readLine())!=null) {
                        System.out.println(pythonFile+" : "+out);
                    }
                    pro.waitFor();
                    int x = pro.exitValue();
                    if (x == 0) {
                        System.out.println(pythonFile+" : done successful");
                    }
                    else {
                        System.out.println(pythonFile+" : done with error");

                        BufferedReader r = new BufferedReader(new InputStreamReader(pro.getErrorStream()));
                        String errMsg;
                        while ((errMsg = r.readLine()) != null)
                        {
                            String msg=errMsg + System.getProperty("line.separator");
                            System.err.println(msg);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            output.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Executes specified python code with given input in a separate thread
     * and prints the output when it terminates
     * @param pythonFile 
     * @param input 
     */
    public void runPython(String pythonFile,String input) {
        ProcessBuilder pb;
        Process pro;
        BufferedReader br;
        try {
            pb = new ProcessBuilder ("cmd","/C","python "+fileDir+"\\"+pythonFile);
            pb.directory(new File(path));
            pro = pb.start();
            br = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            
            String inp=input+"\n";
            byte buffer[] = inp.getBytes();
            OutputStream os =(pro.getOutputStream());
            os.write(buffer,0,buffer.length);
            os.flush();
            
            
            Thread output = new Thread(()->{
                try {
                    ProcessBuilder pblocal;
                    pblocal = pb;//new ProcessBuilder ("cmd","/C","python "+fileDir+"\\"+pythonFile);
                    //pblocal.directory(new File(path));
                    Process plocal = pro;//pblocal.start();
                    BufferedReader brlocal = br;//new BufferedReader(new InputStreamReader(plocal.getInputStream()));
                    String out="";
                    while ((out=brlocal.readLine())!=null) {
                        System.out.println(pythonFile+" "+out);
                    }
                    plocal.waitFor();
                    
                    int x = pro.exitValue();
                    if (x!=0) {
                        System.out.println(pythonFile+" : done with error");

                        BufferedReader r = new BufferedReader(new InputStreamReader(pro.getErrorStream()));
                        String errMsg;
                        while ((errMsg = r.readLine()) != null)
                        {
                            String msg=errMsg + System.getProperty("line.separator");
                            System.err.println(msg);
                        }
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            output.start();
        } catch (Exception e) {
            System.out.println("Error in running");
            e.printStackTrace();
        }
    }
    
    /**
     * Executes specified python code with given input in a separate thread and with a callback
     * and prints the output when it terminates
     * @param pythonFile 
     * @param input 
     */
    public void runPython(String pythonFile,String input,Callback object) {
        ProcessBuilder pb;
        Process pro;
        BufferedReader br;
        try {
            pb = new ProcessBuilder ("cmd","/C","python "+fileDir+"\\"+pythonFile);
            pb.directory(new File(path));
            pro = pb.start();
            br = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            
            String inp=input+"\n";
            byte buffer[] = inp.getBytes();
            OutputStream os =(pro.getOutputStream());
            os.write(buffer,0,buffer.length);
            os.flush();
            
            
            Thread output = new Thread(()->{
                String response="";
                try {
                    ProcessBuilder pblocal;
                    pblocal = pb;//new ProcessBuilder ("cmd","/C","python "+fileDir+"\\"+pythonFile);
                    //pblocal.directory(new File(path));
                    Process plocal = pro;//pblocal.start();
                    BufferedReader brlocal = br;//new BufferedReader(new InputStreamReader(plocal.getInputStream()));
                    String out="";
                    while ((out=brlocal.readLine())!=null) {
                        //System.out.println(pythonFile+" "+out);
                        response=response+out+"\n";
                        //System.out.println("~ " + response);
                    }
                    plocal.waitFor();
                    
                    int x = pro.exitValue();
                    if (x!=0) {
                        System.out.println(pythonFile+" : done with error");

                        BufferedReader r = new BufferedReader(new InputStreamReader(pro.getErrorStream()));
                        String errMsg;
                        while ((errMsg = r.readLine()) != null)
                        {
                            String msg=errMsg + System.getProperty("line.separator");
                            System.err.println(msg);
                        }
                    }
                    
                    
                    
                    object.exec(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            output.start();
        } catch (Exception e) {
            System.out.println("Error in running");
            e.printStackTrace();
        }
    }
}