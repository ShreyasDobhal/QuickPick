/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import utility.Time;

/**
 *
 * @author Shreyas
 */
public class Transaction {
    
    public String transactionId;
    public String productId;
    public String sellerId;
    public String buyerId;
    public String productName;
    public String date;
    public int cost;
    
    
    public Transaction(String transactionId,String produvtId,String sellerId,String buyerId,String productName,String date,int cost) {
        this.transactionId=transactionId;
        this.productId=produvtId;
        this.sellerId=sellerId;
        this.buyerId=buyerId;
        this.productName=productName;
        this.date=date;
        this.cost=cost;
    }
    
    public Transaction() {
        transactionId="@34"+((int)(Math.random()*100));
        productId="@34"+((int)(Math.random()*100));
        String users[]={"shreyasdobhal@gmail.com","kalyanprusty@gmail.com"};
        String product[]={"laptop","bagpack","cycle","table"};
        int i=(int)(Math.random()*2);
        int j=1-i;
        sellerId=users[i];
        buyerId=users[j];
        productName=product[(int)(Math.random()*product.length)];
        date=Time.getCurrentDate();
        cost=(int)(Math.random()*1000);
    }
    
    public boolean isBuyer(User user) {
        if (user==null)
            return false;
        if (user.emailId.equals(buyerId))
            return true;
        return false;
    }
    
    public boolean isSeller(User user) {
        if (user==null)
            return false;
        if (user.emailId.equals(sellerId))
            return true;
        return false;
    }
    
}
