
<?php

require 'connect.php';

$email = $_GET['email'];

$found = false;

$mysql_query = "SELECT `email` FROM `users`";

if($query_data = mysql_query($mysql_query)){
			while($row = mysql_fetch_assoc($query_data)){
				if($email == $row['email']){
					$found = true;
				}
			}
			if($found == true){
				echo "yes";
			}
			else{
				echo "no";
			}
		}

?>