
<?php

require 'connect.php';

$email = $_GET['email'];
$pw = $_GET['pw'];

$found = false;

$mysql_query = "SELECT `email`, `pw` FROM `users`";

if($query_data = mysql_query($mysql_query)){
			while($row = mysql_fetch_assoc($query_data)){
				if($email == $row['email'] && $pw == $row['pw']){
					$found = true;
				}
			}
			if($found == true){
				echo "Welcome ".$email." .... ";
			}
			else{
				echo "Login Failed...";
			}
		}

?>