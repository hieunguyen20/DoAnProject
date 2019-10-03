<?php 
	define("DBSERVER", "localhost");
	define("DBUSERNAME","root");
	define("DBPASSWORD", "");
	define("DBNAME","webservice");

	$conn = mysqli_connect(DBSERVER,DBUSERNAME,DBPASSWORD,DBNAME);
	$conn->set_charset("utf8");
	if (!$conn) {
		die('Connect Error: ' .mysqli_connect_errno());
	};

	
?>