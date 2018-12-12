<?php

require 'connect.php';

$_type = $_GET['type'];
$count = 0;

$mysql_query = "SELECT * FROM `products` WHERE `type` = '".$_type."'";

if($query_data = mysql_query($mysql_query)){
    while($row = mysql_fetch_assoc($query_data)){
        $count++;
    }       
}

echo $count;

?>