
<?php

require 'connect.php';

$type = $_GET['type'];

$mysql_query = "DELETE FROM `products` WHERE `type` = '".$type."'";

mysql_query($mysql_query);

?>
