
<?php

require 'connect.php';

$mysql_query = "SELECT * FROM `users`";

if($query_data = mysql_query($mysql_query)){
    while($row = mysql_fetch_assoc($query_data)){
        echo $row['email'].":".$row['pw'].":".$row['name'].":".$row['city'].":".$row['lat'].":".$row['lng'].":".$row['phone'].":".$row['rating'].":".$row['wishlist'].":".$row['isAdmin'];
        echo "<br>";
    }       
}


?>