<?php 
	$sanpham = array('empid' => "SJ011MS","personal" => array('name' => "SmithJson" ));
	echo "{sanpham:";
	echo json_encode($sanpham); // xử lý dữ liệu bằng mảng
	echo "}";
?>