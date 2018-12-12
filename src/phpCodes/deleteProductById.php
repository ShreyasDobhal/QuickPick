


<?php

require 'connect.php';

$id = $_GET['id'];

$mysql_query = "DELETE FROM `products` WHERE `id` = '".$id."'";

mysql_query($mysql_query);

?>