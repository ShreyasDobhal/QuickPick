/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

/**
 *
 * @author Shreyas
 */
public class Patterns {
    /**
     * Checks if input string is a valid email or not
     * @param email
     * @return 
     */
    public static boolean isValidEmail(String email) {
        String reg="^[a-zA-Z0-9]{1,20}@[a-zA-Z0-9]{1,20}.[a-zA-Z]{2,3}$";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(reg);
        
        if (!pattern.matcher(email).matches())
            return false;
        return true;
    }
    
    /**
     * Checks if input string matches the reg pattern
     * @param str
     * @param reg
     * @return 
     */
    public static boolean isMatch(String str,String reg) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(reg);
        
        if (!pattern.matcher(str).matches())
            return false;
        return true;
    }
    
    /**
     * Checks if valid name or not
     * @param name
     * @return 
     */
    public static boolean isValidName(String name) {
        return isMatch(name, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$");
    }
    
    /**
     * Checks if valid contact number or not
     * @param name
     * @return 
     */
    public static boolean isValidContact(String name) {
        return isMatch(name, "^(\\+?\\d{1,4}[\\s-])?(?!0+\\s+,?$)\\d{10}\\s*,?$");
    }
    
    /**
     * Checks if valid number or not
     * @param name
     * @return 
     */
    public static boolean isValidNumber(String name) {
        return isMatch(name, "\\+?\\d+");
    }
    
}
