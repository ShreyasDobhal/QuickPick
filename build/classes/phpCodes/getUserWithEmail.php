<?php

require 'connect.php';

$email = $_GET['email'];

$mysql_query = "SELECT * FROM `users` WHERE email= '".$email."'";

//SELECT * FROM `users` WHERE email='kalyan'

if($query_data = mysql_query($mysql_query)){
	while($row = mysql_fetch_assoc($query_data)){
		echo $row['email']." ".$row['pw']." ".$row['name']." ".$row['city']." ".$row['phone']." ".$row['rating']." ".$row['wishlist']." ".$row['isAdmin'];
	}		
}
else{
	echo "error.....";
}

?>