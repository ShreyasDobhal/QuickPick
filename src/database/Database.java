/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.LinkedList;
import java.util.List;
import quickpick.Trie;

/**
 *
 * @author Shreyas
 */
public class Database {
    public List<String> tags;
    private static Database database;
    public DBQuery dbQuery;
    public int portNumber=5077;
    // TODO: fill tags from database
    public static String tag[];//={"laptop","keyboard","earphone","mobile","powerbank"};
    //public static List<String> //{"cooler","cycle","bicycle","bed","blanket","notes","laptop","sickle","bluetooth","mini drafter","earphones","power bank"};
    
   
    public static String productTypes[][]={{"TV","Headphone","Speaker","Camera","Printer","Refrigerator","Washing Machine","AC","Other"}, /* Electronics*/
                                           {"Mobile","Mobile Cover","Powerbank","Tablet","Gear","Laptop","Earphones","Keyboard","Storage Device","Printer","Monitor","Other"}, /*Mobile/Computers*/
                                           {"Clothes","Watch","Bag","Handbag","Sunglasses","Wallet","Shoes","Other"}, /*Fashion*/
                                           {"Furniture","Kitchen Appliances","Home Appliances","Food","Vegetables","Medicines","Table","Lamp","Lock","Other"}, /*Home/Kitchen*/
                                           {"Cricket","Badminton","Cycle","Football","Yoga","Basketball","Skates","Helmet","Other"}, /*Sports*/
                                           {"Books","Notes","Pens","Notebooks","Box","Other"} /*Stationary*/ };

   
    public Trie trie;
    
    
    private Database() {
        
    }
    
    public static Database getInstance() {
        if (database==null) {
            database=new Database();
            database.tags=new LinkedList<>();
            database.dbQuery=new DBQuery();
            database.trie=new Trie();
            //fillTags();
        }
        return database;
    }
    
    // TODO: Remove fillTags and add tags from DB
    private static void fillTags() {
        
        for (String str:tag) {
            database.tags.add(str);
        }
    }
}
