
<?php
require 'connect.php';

$sellerId = $_GET['seller'];

$mysql_query = "SELECT * FROM `products` WHERE seller = '".$sellerId."' ";

if($query_data = mysql_query($mysql_query)){
    while($row = mysql_fetch_assoc($query_data)){
        echo $row['id'].":".$row['name'].":".$row['description'].":".$row['price'].":".$row['seller'].":".$row['sellerName'].":".$row['DOU'].":".$row['DOS'].":".$row['buyer'].":".$row['buyerName'].":".$row['tags'].":".$row['discount'].":".$row['type'].":".$row['duration'].":".$row['imagePath'].":".$row['transactionId'].":".$row['expiry'];
        echo "<br>";
    }       
}


?>