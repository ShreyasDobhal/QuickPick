/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Shreyas
 */
public class User {
    
    public String name;
    public String emailId;
    public String password;
    public String phoneNumber;
    public String location;
    public int rating;
    public Set<String>  cartList;
    public String wishList;
    public boolean isAdmin;
    
    public User() {
        
    }
    
    public User(String email,String pass,String name,String location,String phoneNumber,int rating,String wishList,boolean isAdmin) {
        if (email.indexOf('@')==-1) {
            char arr[]=email.toCharArray();
            arr[email.indexOf('_')]='@';
            email=(new String(arr))+".com";
        }
        
        this.emailId = email;
        this.isAdmin = isAdmin;
        this.location = location;
        this.name = name;
        this.password = pass;
        this.phoneNumber = phoneNumber;
        this.rating = rating;
        wishList=wishList.trim();
        this.wishList = wishList;
        this.cartList = new HashSet<>();
        if (wishList.length()==0 || "".equals(wishList) || wishList.equals("null"))
            return;
        String items[]=wishList.split(",");
        for (String item:items) {
            cartList.add(item);
        }
        
    }
    
    public String generateWishList() {
        String str="";
        int size=cartList.size();
        if (size==0)
            return null;
        for (String s:cartList) {
            str=str+s+",";
        }
        str=str.substring(0,str.length()-1);
        wishList=str;
        return str;
    }
}
