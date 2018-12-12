<?php

require 'connect.php';
  $productId = $_GET['id'];
  $buyerName = $_GET['buyerName'];
  $buyerId = $_GET['buyer'];
  $DOS = $_GET['DOS'];
  $transactionId = $_GET['transactionId'];

    $mysql_query = "UPDATE `products` SET  `buyerName`='".$buyerName."', `buyer`='".$buyerId."',`DOS`='".
    $DOS."', `transactionId` = '".$transactionId."' WHERE `id`=".$productId;

    //echo $mysql_query;
  if(mysql_query($mysql_query))
  {
    echo "SUCCESS";
  }
  else
  {
    echo "FAILED";
  }

?>