<?php

    /*  
    	->if u are using @ before any statement then the error message of that will not be shown
		//to the user...u can see it at the beg of mysql_connect statement
		->allways write " || die(error msg)" becoz in some cases the database does not give any error but
		//disconnects automatically
	*/
	$error = "Could not connect !!!";
	$mysql_host = "localhost";
	$mysql_username = "root";
	$mysql_pass = "";

	$mysql_db = "QuickPick";

	
	$conn = mysql_connect($mysql_host,$mysql_username,$mysql_pass);
	
	if(($conn && @mysql_select_db($mysql_db) ) || die($error)){
		//echo "connected";
	}else{
		echo $error.mysql_error();
	}
?>