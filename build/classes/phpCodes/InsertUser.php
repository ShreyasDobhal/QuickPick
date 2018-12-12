
<?php

require 'connect.php';

$email = $_GET['email'];
$pw = $_GET['pw'];
$name = $_GET['name'];
$city = $_GET['city'];
$lat = $_GET['lat'];
$lng = $_GET['lng'];
$phone = $_GET['phone'];
$isAdmin = 'false';
$wishlist = 'null';

$mysql_query = "INSERT INTO `users`(`email`, `pw`, `name`, `city`, `lat`, `lng`, `phone`,`isAdmin`,
            `wishlist`) VALUES (\"".$email.
    "\",\"".$pw."\",\"".$name."\",\"".$city."\",\"".$lat."\",\"".$lng."\",\"".$phone."\",\"".$isAdmin."\",\"".$wishlist."\")";

echo $mysql_query;

if(mysql_query($mysql_query)){
    echo "ok";
}
else {
    echo "not ok";
}

?>