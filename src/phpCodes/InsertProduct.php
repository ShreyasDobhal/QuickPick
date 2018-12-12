<?php

require 'connect.php';

$name = str_replace("_", " ", $_GET['name']);
$description = str_replace("_", " ", $_GET['description']);
$price = str_replace("_", " ", $_GET['price']);
$seller = str_replace("_", " ", $_GET['seller']);
$sellerName = str_replace("_", " ", $_GET['sellerName']);
$DOU = str_replace("_", " ", $_GET['DOU']);
$DOS = 'null';
$tags = str_replace("_", " ", $_GET['tags']);
$buyer = 'null';
$buyerName = 'null';
$discount = str_replace("_", " ", $_GET['discount']);
$type = str_replace("_", " ", $_GET['type']);
$duration =  str_replace("_", " ", $_GET['duration']);
$imagePath = str_replace("_", " ", $_GET['imagePath']);
$expiry = str_replace("_", " ", $_GET['expiry']);
$transactionId = 'null';

$mysql_query = "INSERT INTO `products`(`name`, `description`, `price`, `seller`,`sellerName`, `DOU`,`DOS`,`buyer`,`buyerName`,`tags`,`discount`,`type`,`duration`,`imagePath`,`transactionId`,`expiry`) 
VALUES (\"".$name."\",\"".$description."\",\"".$price."\",\"".$seller."\",\"".$sellerName."\",\"".$DOU."\",\"".$DOS."\",\"".$buyer."\",\"".$buyerName."\",\"".$tags."\",\"".$discount."\",\"".$type."\",\"".$duration."\",\"".$imagePath."\",\"".$transactionId."\",\"".$expiry."\")";

echo $mysql_query;
if(mysql_query($mysql_query)){
    echo "ok";
}
else {
    echo "not ok";
}

?>