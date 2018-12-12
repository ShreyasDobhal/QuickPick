<?php

require 'connect.php';

$id = $_GET['id'];

$mysql_query = "SELECT * FROM `products` WHERE `id` = ".$id;

if($query_data = mysql_query($mysql_query)){
	while($row = mysql_fetch_assoc($query_data)){
		echo $row['id'].":".$row['name'].":".$row['description'].":".$row['price'].":".$row['seller'].":".$row['sellerName'].":".$row['DOU'].":".$row['DOS'].":".$row['buyer'].":".$row['buyerName'].":".$row['tags'].":".$row['discount'].":".$row['type'].":".$row['duration'].":".$row['transactionId'].":".$row['expiry'];
		echo "<br>";
	}		
}


?>