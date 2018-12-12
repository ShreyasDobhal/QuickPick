/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import utility.Time;

/**

 * @author KALYAN
 */
public class Product {
    public String productId;
    public String productName;
    public String sellerName;
    public String buyerName;
    public String sellerId;
    public String buyerId;
    public String dateOfUpload;
    public String dateOfSelling = "null";
    public String description;
    public String discount = "null";
    public String type;
    public String duration = "null";
    public String expiry = "null";
    public String tags;
    //TODO : to shreyas
    public String transactionId;
    public String imagePath;
    public Set<String> TAGS;
   
    public int cost;
    public boolean sold = false;
    

    public Product(String productId,String productName,String description,int cost,String sellerId,String sellerName,String dateOfUpload,
            String buyerId,String buyerName,String tags,String type,String transactionId,String discount,String duration,String expiry,String imagePath) {
        this.productId=productId;
        this.productName=productName;
        this.description=description;
        this.sellerName=sellerName;
        this.cost=cost;
        this.tags=tags;
        this.sellerId=sellerId;
        this.buyerId=buyerId;
        this.buyerName=buyerName;
        this.dateOfUpload=dateOfUpload;
        this.type=type;
        this.transactionId = transactionId;
        this.discount=discount;
        this.duration=duration;
        this.expiry=expiry;
        this.imagePath=imagePath;
        TAGS=new HashSet<>();
        String tg[]=tags.split(",");
        for (String t:tg) {
            TAGS.add(t);
        }
    }
    
    public Product(String productId,String productName,String description,int cost,String sellerId,String sellerName,String dateOfUpload,
            String buyerId,String buyerName,String tags,String type) {
        this.productId=productId;
        this.productName=productName;
        this.description=description;
        this.sellerName=sellerName;
        this.cost=cost;
        this.tags=tags;
        this.sellerId=sellerId;
        this.buyerId=buyerId;
        this.buyerName=buyerName;
        this.dateOfUpload=dateOfUpload;
        this.type=type;
    }
    
    public Product(String productName,String description,String sellerName,int cost) {
        this.productName=productName;
        this.description=description;
        this.cost=cost;
        this.sellerName=sellerName;
    }
    
    @Override
    public String toString()
    {
        return cost+"";
    }
}