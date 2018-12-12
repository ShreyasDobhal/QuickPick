/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
//import java.text.ParseException;
//import jdk.nashorn.internal.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;





/**
 *
 * @author Shreyas
 */
public class JSON {
    
    private static final String peopleInSpace = "http://api.open-notify.org/astros.json";               // Tells the number of people in space
    private static final String forex = "https://api.fixer.io/latest";                                  // Tells the current exchange rates of currencies
    private static final String weather = "http://api.openweathermap.org/data/2.5/weather?q=London,uk"; // Tells the current weather condition
    private static final String iss = "http://api.open-notify.org/iss-now.json";                        // Tells the current location of International Space Station
    private static final String wordnik = "http://api.wordnik.com/v4/word.json/";                       // Tells the definition of the given word
    private static String wordnikWord = "word";
    private static final String wordnikAppid = "/definitions?limit=2&includeRelated=true&useCanonical=false&includeTags=false&api_key=a2a73e7b926c924fad7001ca3111acd55af2ffabf50eb4ae5";
    private static String wordnikURL = wordnik+wordnikWord+wordnikAppid;
    
    public static String strUrl = "{\"base\":\"EUR\",\"date\":\"2018-01-11\",\"rates\":{\"AUD\":1.5281,\"BGN\":1.9558,\"BRL\":3.8858,\"CAD\":1.5093,\"CHF\":1.1736,\"CNY\":7.8127,\"CZK\":25.547,\"DKK\":7.4474,\"GBP\":0.89075,\"HKD\":9.4002,\"HRK\":7.4538,\"HUF\":309.43,\"IDR\":16102.0,\"ILS\":4.1134,\"INR\":76.534,\"JPY\":134.19,\"KRW\":1282.3,\"MXN\":23.305,\"MYR\":4.7905,\"NOK\":9.6735,\"NZD\":1.6653,\"PHP\":60.589,\"PLN\":4.1785,\"RON\":4.6413,\"RUB\":68.465,\"SEK\":9.8203,\"SGD\":1.6006,\"THB\":38.46,\"TRY\":4.5648,\"USD\":1.2017,\"ZAR\":14.974}}";
    
    public static void urlJSON() {
        try {
            String url = iss;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            //con.connect();
           // con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
               response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());
            
            
            JSONParser parser = new JSONParser();
            JSONObject myResponse = (JSONObject)parser.parse(response.toString());
            for (Object o:myResponse.entrySet()) {
                System.out.println(o);
            }
        }
        catch (Exception e) {
            System.out.println("Error loading ");
            e.printStackTrace();
        }
    }
    public static void showJson(String data) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject myResponse = (JSONObject)parser.parse(data);
            //System.out.println(myResponse);
            String dat = (String)myResponse.get("date");
            System.out.println("Date : "+dat);
            double val = (Double)((JSONObject)myResponse.get("rates")).get("INR");
            System.out.println("Rate : "+val);
            System.out.println(myResponse.entrySet());
            for (Object o:((JSONObject)myResponse.get("rates")).entrySet()) {
                System.out.println(o);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public static void writeJSON() {
        JSONObject obj = new JSONObject();
        obj.put("name","Shreyas Dobhal");
        obj.put("college","MNNIT");
        obj.put("from","Dehradun");
        obj.put("reg","20165077");
        
        JSONArray list = new JSONArray();
        list.add("Java");
        list.add("JavaScript");
        list.add("C/C++");
        list.add("Python");
        
        obj.put("lang",list);
        
        try {
            FileWriter file = new FileWriter("myJSON.json");
            file.write(obj.toString());
            file.flush();
            System.out.println(obj);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public static void readJSON() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("myJSON.json"));
            JSONObject jObj = (JSONObject)obj;
            String data = (String)jObj.get("name");
            System.out.println("Name : "+data);
            data = (String)jObj.get("college");
            System.out.println("College : "+data);
            data = (String)jObj.get("reg");
            System.out.println("Reg. No : "+data);
            data = (String)jObj.get("from");
            System.out.println("From : "+data);
            
            JSONArray lang = (JSONArray)jObj.get("lang");
            System.out.print("Lang : ");
            Iterator<String> iterator = lang.iterator();
            
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Error");
        }
        catch (IOException e) {
            System.out.println("Error2");
        }
        catch (ParseException e) {
            System.out.println("Error3");
        }
        catch (Exception e) {
            System.out.println("Error4");
        }
    }
    
    public static JSONObject loadJSON(String url) {
        try {
            //String url = iss;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
               response.append(inputLine);
            }
            in.close();
            JSONParser parser = new JSONParser();
            JSONObject myResponse = (JSONObject)parser.parse(response.toString());
            //System.out.println(myResponse);
            return myResponse;
        }
        catch (Exception e) {
            return null;
        }
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
//    URL url = new URL("http://www.google.com");
//    HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
//
//
//    System.out.println("Response code is " + httpCon.getResponseCode());
        //urlJSON();
        //System.out.println(loadJSON("http://api.open-notify.org/iss-now.json"));
        //readHtml();
 }
    public static void readHtml() throws IOException {
        URL u1 = new URL("file:\\Users\\Shreyas\\Documents\\NetBeansProjects\\JSON\\src\\json\\data.html");//("https://api.fixer.io/latest");
        URLConnection u2 = u1.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(u2.getInputStream()));
        String str=br.readLine();
        while (str!=null) {
            System.out.println(str);
            str=br.readLine();
        }
    }
}
