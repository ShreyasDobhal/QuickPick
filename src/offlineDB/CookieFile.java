/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offlineDB;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author Shreyas
 */
public class CookieFile {
    
    private static final String fileName = "cookie.dat";
    
    public static void save(String str) {
        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter p = new PrintWriter(bw);
            p.println(str);
            p.flush();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Writing error");
        } 
    }
    public static String load() {
        
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String s="";
            String str="";
            while ((s=br.readLine())!=null) {
                str=str+s+"\n";
            }
            return str.trim();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Reading error");
            return null;
        }
        
    }
}
