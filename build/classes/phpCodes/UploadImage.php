<?php

require 'connect.php';


if($_SERVER["REQUEST_METHOD"]=="POST"){

    $pic=$_POST['pic'];
    $imgname = $_POST['name'];
    $path = "upload/".$imgname;

   
    file_put_contents($path,base64_decode($pic));
    echo "SUCCESS";
}

else
    echo "FAILED";  

?>