package database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import quickpick.Callback;
import java.util.ArrayList;
import database.Product;
/**
 *
 * @author
 */
import static database.JSON.loadJSON;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Base64;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import quickpick.AppState;

// php codes are placed in C:\xampp\htdocs\QuickPickphp

public class DBQuery{

    private static String responce  = null;
    private static String ip=AppState.getInstance().ip;
    private static String DIRECTORY_PATH = "http://"+ip+"/QuickPickphp/"; 
 
    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }  
    
    private static String encodeFileToBase64Binary(File file) throws FileNotFoundException, IOException{
        String encodedfile = null;
        FileInputStream fileInputStreamReader = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        fileInputStreamReader.read(bytes);
        encodedfile = new String(Base64.getEncoder().encode(bytes), "UTF-8");
        return encodedfile;
    }
    
    private void uploadImage(String path,String imageName) throws MalformedURLException, ProtocolException, IOException{
       
        File file =  new File(path);
        String encodstring = encodeFileToBase64Binary(file);
        
        String connectionUrl = DIRECTORY_PATH + "UploadImage.php";
        URL url = new URL(connectionUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        
        OutputStream out = httpURLConnection.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));

        String data = URLEncoder.encode("pic", "UTF-8") + "=" + URLEncoder.encode(encodstring, "UTF-8") + "&" +
                      URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(imageName, "UTF-8");

        bufferedWriter.write(data);
        bufferedWriter.flush();
        bufferedWriter.close();
        
        InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
        String res = readFromStream(inputStream);
        System.out.println(res);
        httpURLConnection.disconnect();
    }
    
    private String getResponce(String str_url){
            URL url = null;
            String responce = "";
            try {
                url = new URL(str_url);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                InputStream inputStream = connection.getInputStream();
                responce = readFromStream(inputStream);
                connection.disconnect(); 
            }
            catch(Exception e){
                System.out.println("error has occured");
            }
         return responce;
    }
    
    public void updateUser(String email,String name,String pass,String city,String phone){
        String url_string = "http://www.mapquestapi.com/geocoding/v1/address?key=cjC8umEANz2W4kFrAdBZf6VBWrjr1cPY&location=" +city;
        String strlat,strlng;
//        System.out.println(url_string);       
//        JSONObject jobj= (JSONObject)loadJSON(url_string);
//        if (jobj!=null) {
//            JSONArray resultArray = (JSONArray)(jobj.get("results"));
//            JSONObject resultObj = (JSONObject)(resultArray.get(0));
//            JSONArray locationArray = (JSONArray)(resultObj.get("locations"));
//            JSONObject locationObj = (JSONObject) locationArray.get(0);
//            JSONObject latlngObj = (JSONObject) locationObj.get("latLng");
//
//            double lat = (double)(latlngObj.get("lat"));
//            double lng = (double)(latlngObj.get("lng"));
//
//            strlat = String.valueOf(lat);
//            strlng = String.valueOf(lng);
//        }
//        else
        {
            strlat = "0";
            strlng = "0";
        }

        System.out.println(strlat+" "+strlng);

        String newemail = email.substring(0,email.lastIndexOf('@'))+"_" + email.substring(email.lastIndexOf('@')+1,email.lastIndexOf('.'));
        String reg_url = DIRECTORY_PATH+"updateUser.php?email="+newemail+"&pw="+pass+"&name="+name+"&city="+city+
           "&lat="+strlat+"&lng="+strlng+"&phone="+phone;
        System.out.println(reg_url);
        String responce = getResponce(reg_url);
    }
    
    public int countProductByType(String type){
        String reg_url = DIRECTORY_PATH+"countProductByType.php?type="+type;
        System.out.println(reg_url);
        String responce = getResponce(reg_url);
        return Integer.valueOf(responce);
    }
    
    public ArrayList<Product> getAllProductById(int id){
        ArrayList<Product> returnList = new ArrayList<Product>();
            String reg_url = DIRECTORY_PATH+"getProductById.php?id="+id;
            System.out.println(reg_url);
            String responce = getResponce(reg_url);
            System.out.println(responce);
            
            if(responce.length() > 0){
            String[] singleProductList = responce.split("<br>");
                for(int i=0;i < singleProductList.length - 1;i++){
                     System.out.println(singleProductList[i]);
                    String[] singleProduct = singleProductList[i].split(":");
                    returnList.add(new Product(singleProduct[0],singleProduct[1],singleProduct[2],Integer.valueOf(singleProduct[3]),singleProduct[4],singleProduct[5],singleProduct[6],singleProduct[8],
                    singleProduct[9],singleProduct[10],singleProduct[12],singleProduct[14],singleProduct[11],singleProduct[13],singleProduct[15],singleProduct[14]));
                }
            }
        System.out.println(returnList);
        return returnList;
    }
    
    public ArrayList<User> getAllUsers(){
        ArrayList<User> returnList = new ArrayList<User>();
            String reg_url = DIRECTORY_PATH+"getAllUsers.php?";
            System.out.println(reg_url);
            String responce = getResponce(reg_url);
            System.out.println(responce);
            if(responce.length() > 0){
                String[] singleUserList = responce.split("<br>");
                for(int i=0;i < singleUserList.length;i++){
                    System.out.println(singleUserList[i]);
                    String[] singleUser = singleUserList[i].split(":");
                    // (String email,String pass,String name,String location,String phoneNumber,int rating,String wishList,boolean isAdmin) {
                    returnList.add(new User(singleUser[0],singleUser[1],singleUser[2],singleUser[3],singleUser[6],Integer.valueOf(singleUser[7]),singleUser[8],Boolean.valueOf(singleUser[9])));
                }
            }
        return returnList;
    }
    
    /**
     * This function requires email in normal format i.e for e.g. kalyan@gmail.com
     * @param email
     * @param wishlist 
     */
    public void updateUserWishlist(String email,String wishlist){
        if((email.contains("@") || email.contains(".com")))
            email = email.substring(0,email.lastIndexOf('@'))+"_" + email.substring(email.lastIndexOf('@')+1,email.lastIndexOf('.'));
        String reg_url = DIRECTORY_PATH+"updateUserWishlist.php?email="+email+"&wishlist=" + wishlist;
        System.out.println(reg_url);
        String responce = getResponce(reg_url);
        System.out.println(responce);
    }
    
    public void deleteProductByType(String type){
        String reg_url = DIRECTORY_PATH+"deleteProductByType.php?type="+type;
        System.out.println(reg_url);
        String responce = getResponce(reg_url);
    }
    
    public void deleteProductById(String id){
        String reg_url = DIRECTORY_PATH+"deleteProductById.php?id="+id;
        System.out.println(reg_url);
        String responce = getResponce(reg_url);
    }
    
    // TODO: return ArrayList of the products from here
    public ArrayList<Product> getAllProduct(){
        ArrayList<Product> returnList = new ArrayList<Product>();
            String reg_url = DIRECTORY_PATH+"getAllProduct.php?";
            System.out.println(reg_url);
            String responce = getResponce(reg_url);
            System.out.println(responce);
            if(responce.length() > 0){
                String[] singleProductList = responce.split("<br>");
                for(int i=0;i < singleProductList.length;i++){
                    System.out.println(singleProductList[i]);
                    String[] singleProduct = singleProductList[i].split(":");
                    returnList.add(new Product(singleProduct[0],singleProduct[1],singleProduct[2],Integer.valueOf(singleProduct[3]),singleProduct[4],singleProduct[5],singleProduct[6],singleProduct[8],
                    singleProduct[9],singleProduct[10],singleProduct[12],singleProduct[15],singleProduct[11],singleProduct[13],singleProduct[16],singleProduct[14]));
                }
            }
        return returnList;
    }
    
    public ArrayList<Product> getAllProductByDiscount(){
        ArrayList<Product> returnList = new ArrayList<Product>();
            String reg_url = DIRECTORY_PATH+"getAllProductByDiscount.php";
            System.out.println(reg_url);
            String responce = getResponce(reg_url);
            System.out.println(responce);
            if(responce.length() > 0){
                String[] singleProductList = responce.split("<br>");
                for(int i=0;i < singleProductList.length;i++){
                    System.out.println(singleProductList[i]);
                    String[] singleProduct = singleProductList[i].split(":");
                    returnList.add(new Product(singleProduct[0],singleProduct[1],singleProduct[2],Integer.valueOf(singleProduct[3]),singleProduct[4],singleProduct[5],singleProduct[6],singleProduct[8],
                    singleProduct[9],singleProduct[10],singleProduct[12],singleProduct[15],singleProduct[11],singleProduct[13],singleProduct[16],singleProduct[14]));
                }
            }
        return returnList;
    }
    
    public ArrayList<Product> getAllProductByTag(String tags){
        ArrayList<Product> returnList = new ArrayList<Product>();
            String reg_url = DIRECTORY_PATH+"getAllProductByTag.php?tags="+tags;
            System.out.println(reg_url);
            String responce = getResponce(reg_url);
            System.out.println(responce);
            if(responce.length() > 0){
                String[] singleProductList = responce.split("<br>");
                for(int i=0;i < singleProductList.length;i++){
                    System.out.println(singleProductList[i]);
                    String[] singleProduct = singleProductList[i].split(":");
                    returnList.add(new Product(singleProduct[0],singleProduct[1],singleProduct[2],Integer.valueOf(singleProduct[3]),singleProduct[4],singleProduct[5],singleProduct[6],singleProduct[8],
                    singleProduct[9],singleProduct[10],singleProduct[12],singleProduct[15],singleProduct[11],singleProduct[13],singleProduct[16],singleProduct[14]));
                }
            }
        return returnList;
    }
    
    /**
     * month and year are in integer format
     * email must contain @ and .com
     * @param month
     * @param year
     * @param email
     * @return 
     */
   
    public ArrayList<Product> getAllProductsUploadByUserInAMonthOfAYear(int month,int year,String email){
        ArrayList<Product> returnList = new ArrayList<Product>();
        String strMon = String.valueOf(month);;
        if(month < 10)
            strMon = "0" + strMon;
        String finalDOU = "/"+ strMon + "/"+ String.valueOf(year);
        String newemail = email.substring(0,email.lastIndexOf('@'))+"_" + email.substring(email.lastIndexOf('@')+1,email.lastIndexOf('.'));
        String reg_url = DIRECTORY_PATH + "getAllProductsUploadByUserInAMonthOfAYear.php?seller="+newemail+"&DOU="+finalDOU;
        System.out.println(reg_url);
        String responce = getResponce(reg_url);
        System.out.println(responce);
        
        if(responce.length() > 0){
            String[] singleProductList = responce.split("<br>");
            for(int i=0;i < singleProductList.length - 1;i++){
                 System.out.println(singleProductList[i]);
                String[] singleProduct = singleProductList[i].split(":");
                returnList.add(new Product(singleProduct[0],singleProduct[1],singleProduct[2],Integer.valueOf(singleProduct[3]),singleProduct[4],singleProduct[5],singleProduct[6],singleProduct[8],
                singleProduct[9],singleProduct[10],singleProduct[12],singleProduct[15],singleProduct[11],singleProduct[13],singleProduct[16],singleProduct[14]));
            }
        }
        return returnList;
    }
    
    public ArrayList<Product> getAllProductWithSellerId(String sellerId){
        ArrayList<Product> returnList = new ArrayList<Product>();
            String reg_url = DIRECTORY_PATH+"getAllProductWithSellerId.php?seller="+sellerId;
            System.out.println(reg_url);
            String responce = getResponce(reg_url);
            System.out.println(responce);
            
            if(responce.length() > 0){
            String[] singleProductList = responce.split("<br>");
                for(int i=0;i < singleProductList.length - 1;i++){
                     System.out.println(singleProductList[i]);
                    String[] singleProduct = singleProductList[i].split(":");
                    returnList.add(new Product(singleProduct[0],singleProduct[1],singleProduct[2],Integer.valueOf(singleProduct[3]),singleProduct[4],singleProduct[5],singleProduct[6],singleProduct[8],
                    singleProduct[9],singleProduct[10],singleProduct[12],singleProduct[14],singleProduct[11],singleProduct[13],singleProduct[15],singleProduct[14]));
                }
            }
        return returnList;
    }
    
    public void insertProduct(Product product,String fetchpath,String imageName){
        
        String name = product.productName.replace(" ","_");
        String description = product.description.replace(" ","_");
        String price = Integer.toString(product.cost);
        String tmpseller = product.sellerId.replace(" ","_");
        String seller;//="";
        if (tmpseller.indexOf('@')!=-1) {
            seller = product.sellerId.substring(0,product.sellerId.lastIndexOf('@'))+"_" + product.sellerId.substring(product.sellerId.lastIndexOf('@')+1,product.sellerId.lastIndexOf('.'));
        } else {
            seller="";
        }
        
        String sellerName = product.sellerName.replace(" ","_");
        String DOU = product.dateOfUpload.replace(" ","_");
        String tags = product.tags.replace(" ","_");
        String type = product.type.replace(" ","_");
        
        String duration = product.duration.replace(" ","_");
        String discount = product.discount.replace(" ","_");
        
        Thread thread = new Thread(()->{
            String reg_url = DIRECTORY_PATH+"insertProduct.php?name="+name+"&description="+description+"&price="+price+"&seller="+seller+
                    "&sellerName="+sellerName+"&DOU="+DOU+"&tags="+tags+"&type="+type+"&duration="+duration+"&discount="+discount
                    +"&imagePath=upload/" +imageName+"&expiry="+product.expiry;
            try {
                uploadImage(fetchpath,imageName);
            } catch (Exception ex) {
                System.out.println("Exception during upload");
            }
            System.out.println(reg_url);
            String responce = getResponce(reg_url);
             System.out.println(responce);
        });
        thread.start();
    }
    
    /**
      * Make sure that the email must contains @ and .com 
      * And date should be in the format of dd/mm/yyyy
      * productId and transactionId are in String format
      * @param productId
      * @param buyerName
      * @param buyerEmail
      * @param DOS
      * @param transactionId 
      */
    public void updateProductAfterSelling(String productId,String buyerName,String buyerEmail,String DOS,String transactionId){
        String newemail = buyerEmail.substring(0,buyerEmail.lastIndexOf('@'))+"_" + buyerEmail.substring(buyerEmail.lastIndexOf('@')+1,buyerEmail.lastIndexOf('.'));
        String reg_url = DIRECTORY_PATH + "updateProductAfterSelling.php?buyerName="+buyerName+"&buyer="+newemail+"&DOS="+DOS+
                            "&id="+productId+"&transactionId="+transactionId;
        System.out.println(reg_url);
        String responce = getResponce(reg_url);
        System.out.println(responce);
    }
    
    public static void main(String args[]) {
        DBQuery obj = new DBQuery();
        //insertUser(String email,String pw,String name,String city,String phone)
        obj.insertUser("shreyasdobhal@gmail.com", "1234", "name", "cty", "pjone");
    }
    
    public boolean insertUser(String email,String pw,String name,String city,String phone){
        String url_string = "http://www.mapquestapi.com/geocoding/v1/address?key=cjC8umEANz2W4kFrAdBZf6VBWrjr1cPY&location=" +city;
        String strlat,strlng;
        System.out.println(url_string);       
        JSONObject jobj= (JSONObject)loadJSON(url_string);
        if (jobj!=null) {
            JSONArray resultArray = (JSONArray)(jobj.get("results"));
            JSONObject resultObj = (JSONObject)(resultArray.get(0));
            JSONArray locationArray = (JSONArray)(resultObj.get("locations"));
            JSONObject locationObj = (JSONObject) locationArray.get(0);
            JSONObject latlngObj = (JSONObject) locationObj.get("latLng");

            double lat = (double)(latlngObj.get("lat"));
            double lng = (double)(latlngObj.get("lng"));

            strlat = String.valueOf(lat);
            strlng = String.valueOf(lng);
        }
        else{
            strlat = "0";
            strlng = "0";
        }

        System.out.println(strlat+" "+strlng);

        String newemail = email.substring(0,email.lastIndexOf('@'))+"_" + email.substring(email.lastIndexOf('@')+1,email.lastIndexOf('.'));
        String reg_url = DIRECTORY_PATH+"insertUser.php?email="+newemail+"&pw="+pw+"&name="+name+"&city="+city+
           "&lat="+strlat+"&lng="+strlng+"&phone="+phone;
        System.out.println(reg_url);
        String responce = getResponce(reg_url);
        if(responce.contains("not ok"))
            return false;
        else 
            return true;
    }
    
    /**
     * if the responce is "yes" then he is a valid user else if responce is "no" then not
     * @param email
     * @param obj 
     */
    public void isEmailInDB(String email,Callback obj){
        Thread thread = new Thread(()->{
            String newemail = email.substring(0,email.lastIndexOf('@'))+"_" + email.substring(email.lastIndexOf('@')+1,email.lastIndexOf('.'));
            System.out.println(newemail);
            String reg_url = DIRECTORY_PATH+"isEmailInDB.php?email="+newemail;
            String responce = getResponce(reg_url);
            System.out.println(reg_url);
            System.out.println(responce);
            obj.exec(responce);
        });
        thread.start();
    }
    
    
    /**
     * if the responce contains  "Welcome" then he is a valid user else if responce contains "Failed" then not
     * @param email
     * @param pass
     * @param obj 
     */
    public void isValidUser(String email,String pass,Callback obj){
        Thread thread = new Thread(()->{
            String newemail = email.substring(0,email.lastIndexOf('@'))+"_" + email.substring(email.lastIndexOf('@')+1,email.lastIndexOf('.'));
            String reg_url = DIRECTORY_PATH+"Login.php?email="+newemail+"&pw="+pass;
            String responce = getResponce(reg_url);
            System.out.println(reg_url);
            obj.exec(responce);
        });
        thread.start();
    }
    
    public User getUserWithEmail(String email){
        String newemail = email.substring(0,email.lastIndexOf('@'))+"_" + email.substring(email.lastIndexOf('@')+1,email.lastIndexOf('.'));
        String reg_url = DIRECTORY_PATH+"getUserWithEmail.php?email="+newemail;
        System.out.println(reg_url);
        String responce = getResponce(reg_url);
        String[] userLine = responce.split(" ");
        System.out.println(Arrays.toString(userLine)+" "+userLine.length);
        
        User retUser = new User(userLine[0],userLine[1],userLine[2],userLine[3],userLine[4],Integer.valueOf(userLine[5]),userLine[6],Boolean.valueOf(userLine[7]));
        return retUser;
    }
    
    public void createTableWithUser(String email){
        Thread thread = new Thread(()->{
            String newemail = email.substring(0,email.lastIndexOf('@'))+"_" + email.substring(email.lastIndexOf('@')+1,email.lastIndexOf('.'));
            String reg_url = DIRECTORY_PATH+"CreateTable.php?email="+newemail;
            String responce = getResponce(reg_url);
            System.out.println(responce);
        });
        thread.start();
    }
 }