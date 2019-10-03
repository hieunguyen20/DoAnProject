<?php 
	include_once("config.php");

	if(isset($_GET["maloaicha"]))
	$maloaicha=$_GET["maloaicha"];

	$truyvan="SELECT * FROM loaisanpham WHERE MALOAI_CHA=".$maloaicha;
	$ketqua=mysqli_query($conn,$truyvan);
	$chuoijson=array();
	echo "{";
	echo "\"LOAISANPHAM\":";
	if ($ketqua) {
		while ($dong=mysqli_fetch_array($ketqua)) {
			array_push($chuoijson, array("TENLOAISP"=>$dong["TENLOAISP"], "MALOAISP"=>$dong["MALOAISP"], "MALOAI_CHA"=>$dong["MALOAI_CHA"])); 
		}
		echo json_encode($chuoijson, JSON_UNESCAPED_UNICODE);
	}
	echo "}";

?>