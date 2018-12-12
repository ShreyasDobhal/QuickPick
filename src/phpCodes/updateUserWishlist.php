
<?php

require 'connect.php';

$email = $_GET['email'];
$wishlist = $_GET['wishlist'];

//UPDATE `users` SET `wishlist`= 'laptop' WHERE `email` = 'kalyan_gmail'

$mysql_query = "UPDATE `users` SET `wishlist`= '".$wishlist."' WHERE `email` = '".$email."'";

echo $mysql_query;

if(mysql_query($mysql_query)){
    echo "ok";
}
else {
    echo "not ok";
}

?>