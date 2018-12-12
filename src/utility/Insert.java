/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

// NOTE: delete this file

import database.DBQuery;

/**
 *
 * @author Shreyas
 */
public class Insert {
    public static void main(String args[]) {
        DBQuery db=new DBQuery();
        db.createTableWithUser("shreyasdobhal@gmail.com");
        db.createTableWithUser("prakharsrivastava@gmail.com");
        db.createTableWithUser("kalyanprusty@gmail.com");
    }
}
