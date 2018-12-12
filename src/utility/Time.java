/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Shreyas
 */
public class Time {
    
    /**
     * Date in HH:mm dd-MM-yyyy format;
     * @return 
     */
    public static String getTime() {
        String DATE_FORMAT_NOW="HH:mm dd-MM-yyyy";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }
    
    /**
     * Today's date in dd/MM/yyyy format
     * @return 
     */
    public static String getCurrentDate() {
        String DATE_FORMAT_NOW="dd/MM/yyyy";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }
    
    
    /**
     * Time stamp for naming files
     * in yyyy_MM_dd HH_mm format
     * @return 
     */
    public static String getTimeForName() {
        String DATE_FORMAT_NOW="yyyy_MM_dd HH_mm";//HH:mm dd-MM-yyyy";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }
    
    /**
     * Converts dd/MM/yyyy format to dd MMM, yyyy format
     * @param date
     * @return 
     */
    public static String convertDate(String date) {
        int i=date.indexOf('/');
        int j=date.lastIndexOf('/');
        int mon=Integer.valueOf(date.substring(i+1,j));
        String month[]={"","January","February","March","April","Map","June","July","August","September","October","November","December"};
        date = date.substring(0,i)+" "+month[mon]+", "+date.substring(j+1);
        return date;
    }
}
