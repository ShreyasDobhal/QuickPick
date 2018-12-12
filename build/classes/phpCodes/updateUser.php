<?php

require 'connect.php';

$email = $_GET['email'];
$pw = $_GET['pw'];
$name = $_GET['name'];
$city = $_GET['city'];
$lat = $_GET['lat'];
$lng = $_GET['lng'];
$phone = $_GET['phone'];

//SET `email`=[value-1],`pw`=[value-2],`name`=[value-3],`city`=[value-4],`lat`=[value-5],`lng`=[value-6],`phone`=[value-7],`rating`=[value-8],`wishlist`=[value-9],`isAdmin`=[value-10] WHERE 1
$mysql_query = "UPDATE `users` SET `pw` = '".$pw."',`name` = '".$name.
					"',`city`='".$city."',`lat`='".$lat."',`lng`='".$lng."',`phone`='".$phone.
					"' WHERE `email` ='".$email."'";

echo $mysql_query;

if(mysql_query($mysql_query)){
	echo "ok";
}
else {
	echo "not ok";
}

?>
