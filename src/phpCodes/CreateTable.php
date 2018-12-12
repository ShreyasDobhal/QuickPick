<?php

require 'connect.php';

$email = $_GET['email'];

$mysql_query =  "CREATE TABLE ".$email."(id INT NOT NULL AUTO_INCREMENT, other_user varchar(30), msg TEXT, PRIMARY KEY(id))";

if(mysql_query($mysql_query)){
	echo "ok done";
}
else{
	echo "not ok bye \n";
}

?>