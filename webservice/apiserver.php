<?php 
	include_once("config.php");

	if(isset($_POST["ham"]))
	$ham=$_POST["ham"];

	// if(isset($_GET["ham"]))
	// $ham=$_GET["ham"];

	switch ($ham) {
		case 'LayDanhSachMenu':
			$ham();
			break;

		case 'DangKyThanhVien':
			$ham();
			break;

		case 'KiemTraDangNhap':
			$ham();
			break;

		case 'LayDanhSachCacThuongHieuLon':
			$ham();
			break;

		case 'LayDanhSachTopDienThoaiMayTinhBang':
			$ham();
			break;

		case 'LayDanhSachTopPhuKien':
			$ham();
			break;

		case 'LayDanhSachPhuKien':
			$ham();
			break;

		case 'LayDanhSachTienIch':
			$ham();
			break;

		case 'LayTopTienIch':
			$ham();
			break;

		case 'LayDanhSachSanPhamTheoMaLoaiDanhMuc':
			$ham();
			break;

		case 'LayDanhSachSanPhamTheoMaThuongHieu':
			$ham();
			break;
	}

	function LayDanhSachSanPhamTheoMaThuongHieu(){
		global $conn;
		$chuoijson = array();
		if(isset($_POST["maloaisp"]) || isset($_POST["limit"])){
			$maloai = $_POST["maloaisp"];
			$limit = $_POST["limit"];
		}
		
		echo "{";
		echo "\"DANHSACHSANPHAM\":";

		$chuoijson = LayDanhSachSanPhamTheoMaLoaiThuongHieu($conn,$maloai,$chuoijson,$limit);

		echo json_encode($chuoijson, JSON_UNESCAPED_UNICODE);
		echo "}";

	}

	function LayDanhSachSanPhamTheoMaLoaiDanhMuc(){
		global $conn;
		$chuoijson = array();

		if(isset($_POST["maloaisp"]) || isset($_POST["limit"])){
			$maloai = $_POST["maloaisp"];
			$limit = $_POST["limit"];
		}

		$chuoijson = LayDanhSachSanPhamDanhMucTheoMaLoai($conn,$maloai,$chuoijson,$limit);

		echo "{";
		echo "\"DANHSACHSANPHAM\":";

		echo json_encode($chuoijson, JSON_UNESCAPED_UNICODE);
		echo "}";
	}

	function LayTopTienIch(){
		global $conn;

		$ketqua = LayDanhSachLOAISANPHAMTheoMaLoai($conn, 81);
		$chuoijson=array();

		echo "{";
		echo "\"TOPTIENICH\":";
		if ($ketqua) {
			while ($dong = mysqli_fetch_array($ketqua)) {
				$ketquacon = LayDanhSachLOAISANPHAMTheoMaLoai($conn,$dong["MALOAISP"]);

				if ($ketquacon) {
					while ($dongcon=mysqli_fetch_array($ketquacon)) {
						$chuoijson = LayDanhSachSanPhamTheoMaLoai($conn,$dongcon["MALOAISP"], $chuoijson,10);
					}
				}
				
			}
			
		}
		
		echo json_encode($chuoijson, JSON_UNESCAPED_UNICODE);
		echo "}";
	}

	function LayDanhSachTienIch(){
		global $conn;

		$ketqua = LayDanhSachLOAISANPHAMTheoMaLoai($conn, 81);
		$chuoijson=array();

		echo "{";
		echo "\"DANHSACHTIENICH\":";
		if ($ketqua) {
			while ($dong = mysqli_fetch_array($ketqua)) {
				$ketquacon = LayDanhSachLOAISANPHAMTheoMaLoai($conn,$dong["MALOAISP"]);

				if ($ketquacon) {
					while ($dongcon=mysqli_fetch_array($ketquacon)) {
						$chuoijson = LayDanhSachSanPhamTheoMaLoai($conn,$dongcon["MALOAISP"], $chuoijson,1);
					}
				}
				
			}
			
		}
		
		echo json_encode($chuoijson, JSON_UNESCAPED_UNICODE);
		echo "}";
	}

	function LayDanhSachLOAISANPHAMTheoMaLoai($conn,$maloaisp){
		$truyvancha="SELECT * FROM loaisanpham lsp WHERE lsp.MALOAI_CHA =" .$maloaisp;
		$ketqua=mysqli_query($conn,$truyvancha);
	
		return $ketqua;
	}

		function LayDanhSachSanPhamDanhMucTheoMaLoai($conn,$maloaisp,$chuoijson,$limit){
			$truyvantienich="SELECT * FROM loaisanpham lsp, sanpham sp WHERE lsp.MALOAISP=".$maloaisp." AND lsp.MALOAISP = sp.MALOAISP ORDER BY sp.LUOTMUA DESC LIMIT ".$limit;
				$ketquacon=mysqli_query($conn,$truyvantienich);

				if($ketquacon){
					while ($dongtienich=mysqli_fetch_array($ketquacon)) {
						array_push($chuoijson, array("MASP"=>$dongtienich["MASP"], "TENSP"=>$dongtienich["TENSP"], "GIATIEN"=>$dongtienich["GIA"],"HINHSANPHAM"=>"http://".$_SERVER['SERVER_NAME']."/webservice".$dongtienich["HINHLON"],"HINHSANPHAMNHO"=>"http://".$_SERVER['SERVER_NAME']."/webservice".$dongtienich["HINHNHO"])); 
					}
				}

				return $chuoijson;
	}


	function LayDanhSachSanPhamTheoMaLoaiThuongHieu($conn,$maloaith,$chuoijson,$limit){
			$truyvantienich="SELECT * FROM thuonghieu th, sanpham sp WHERE th.MATHUONGHIEU=".$maloaith." AND th.MATHUONGHIEU = sp.MATHUONGHIEU ORDER BY sp.LUOTMUA DESC LIMIT ".$limit;
				$ketquacon=mysqli_query($conn,$truyvantienich);

				if($ketquacon){
					while ($dongtienich=mysqli_fetch_array($ketquacon)) {
						array_push($chuoijson, array("MASP"=>$dongtienich["MASP"], "TENSP"=>$dongtienich["TENSP"], "GIATIEN"=>$dongtienich["GIA"],"HINHSANPHAM"=>"http://".$_SERVER['SERVER_NAME']."/webservice".$dongtienich["HINHLON"],"HINHSANPHAMNHO"=>"http://".$_SERVER['SERVER_NAME']."/webservice".$dongtienich["HINHNHO"])); 
					}
				}

				return $chuoijson;
	}



	function LayDanhSachSanPhamTheoMaLoai($conn,$maloaisp,$chuoijson,$limit){
			$truyvantienich="SELECT * FROM loaisanpham lsp, sanpham sp WHERE lsp.MALOAISP=".$maloaisp." AND lsp.MALOAISP = sp.MALOAISP ORDER BY sp.LUOTMUA DESC LIMIT ".$limit;
				$ketquacon=mysqli_query($conn,$truyvantienich);

				if($ketquacon){
					while ($dongtienich=mysqli_fetch_array($ketquacon)) {
						array_push($chuoijson, array("MASP"=>$dongtienich["MASP"], "TENSP"=>$dongtienich["TENLOAISP"], "GIATIEN"=>$dongtienich["GIA"],"HINHSANPHAM"=>"http://".$_SERVER['SERVER_NAME']."/webservice".$dongtienich["HINHLON"],"HINHSANPHAMNHO"=>"http://".$_SERVER['SERVER_NAME']."/webservice".$dongtienich["HINHNHO"])); 
					}
				}

				return $chuoijson;
	}

	

	function LayDanhSachPhuKien(){
				global $conn;

		$truyvancha="SELECT * FROM loaisanpham lsp WHERE lsp.TENLOAISP LIKE 'phụ kiện điện thoại%' ";
		$ketqua=mysqli_query($conn,$truyvancha);
		$chuoijson=array();

		echo "{";
		echo "\"DANHSACHPHUKIEN\":";
		if ($ketqua) {
			while ($dong=mysqli_fetch_array($ketqua)) {
				$truyvanphukiencon="SELECT * FROM loaisanpham lsp, sanpham sp WHERE lsp.MALOAI_CHA=".$dong["MALOAISP"]." AND lsp.MALOAISP = sp.MALOAISP ORDER BY sp.LUOTMUA DESC LIMIT 10";
				$ketquacon=mysqli_query($conn,$truyvanphukiencon);

				if($ketquacon){
					while ($dongphukiencon=mysqli_fetch_array($ketquacon)) {
						array_push($chuoijson, array("MASP"=>$dongphukiencon["MALOAISP"], "TENSP"=>$dongphukiencon["TENLOAISP"], "GIATIEN"=>$dongphukiencon["GIA"],"HINHSANPHAM"=>"http://".$_SERVER['SERVER_NAME']."/webservice".$dongphukiencon["HINHLON"])); 
					}
				}

				
			}
			
		}
		
		echo json_encode($chuoijson, JSON_UNESCAPED_UNICODE);
		echo "}";
	}


	function LayDanhSachTopPhuKien(){
		global $conn;

		$truyvancha="SELECT * FROM loaisanpham lsp WHERE lsp.TENLOAISP LIKE 'phụ kiện điện thoại%' ";
		$ketqua=mysqli_query($conn,$truyvancha);
		$chuoijson=array();

		echo "{";
		echo "\"TOPPHUKIEN\":";
		if ($ketqua) {
			while ($dong=mysqli_fetch_array($ketqua)) {
				$truyvanphukiencon="SELECT * FROM loaisanpham lsp, sanpham sp WHERE lsp.MALOAI_CHA=".$dong["MALOAISP"]." AND lsp.MALOAISP = sp.MALOAISP ORDER BY sp.LUOTMUA DESC LIMIT 10 ";
				$ketquacon=mysqli_query($conn,$truyvanphukiencon);

				if($ketquacon){
					while ($dongphukiencon=mysqli_fetch_array($ketquacon)) {
						array_push($chuoijson, array("MASP"=>$dongphukiencon["MASP"], "TENSP"=>$dongphukiencon["TENSP"], "GIATIEN"=>$dongphukiencon["GIA"],"HINHSANPHAM"=>"http://".$_SERVER['SERVER_NAME']."/webservice".$dongphukiencon["HINHLON"])); 
					}
				}

				
			}
			
		}
		
		echo json_encode($chuoijson, JSON_UNESCAPED_UNICODE);
		echo "}";
	}

	function LayDanhSachTopDienThoaiMayTinhBang(){
		global $conn;

		$truyvan="SELECT * FROM loaisanpham lsp, sanpham sp WHERE lsp.TENLOAISP LIKE 'điện thoại%' AND lsp.MALOAISP = sp.MALOAISP ORDER BY sp.LUOTMUA DESC LIMIT 10";
		$ketqua=mysqli_query($conn,$truyvan);
		$chuoijson=array();

		echo "{";
		echo "\"TOPDIENTHOAIVAMAYTINHBANG\":";
		if ($ketqua) {
			while ($dong=mysqli_fetch_array($ketqua)) {
				array_push($chuoijson, array("MASP"=>$dong["MASP"], "TENSP"=>$dong["TENSP"], "GIATIEN"=>$dong["GIA"],"HINHSANPHAM"=>"http://".$_SERVER['SERVER_NAME']."/webservice".$dong["HINHLON"])); 
			}
			
		}


		$truyvan="SELECT * FROM loaisanpham lsp, sanpham sp WHERE lsp.TENLOAISP LIKE 'máy tính bảng%' AND lsp.MALOAISP = sp.MALOAISP ORDER BY sp.LUOTMUA DESC LIMIT 10";
		$ketquamtb=mysqli_query($conn,$truyvan);
		if ($ketquamtb) {
			while ($dongmtb=mysqli_fetch_array($ketquamtb)) {
				array_push($chuoijson, array("MASP"=>$dongmtb["MASP"], "TENSP"=>$dongmtb["TENSP"], "GIATIEN"=>$dongmtb["GIA"],"HINHSANPHAM"=>"http://".$_SERVER['SERVER_NAME']."/webservice".$dongmtb["HINHLON"])); 
			}
			
		}
		
		echo json_encode($chuoijson, JSON_UNESCAPED_UNICODE);
		echo "}";
	}

	function LayDanhSachCacThuongHieuLon(){
		global $conn;

		$truyvan="SELECT * FROM thuonghieu th, chitietthuonghieu cth WHERE th.MATHUONGHIEU = cth.MATHUONGHIEU";
		$ketqua=mysqli_query($conn,$truyvan);
		$chuoijson=array();

		echo "{";
		echo "\"DANHSACHTHUONGHIEU\":";
		if ($ketqua) {
			while ($dong=mysqli_fetch_array($ketqua)) {
				array_push($chuoijson, array("MASP"=>$dong["MATHUONGHIEU"], "TENSP"=>$dong["TENTHUONGHIEU"], "HINHSANPHAM"=>"http://".$_SERVER['SERVER_NAME']."/webservice".$dong["HINHTHUONGHIEU"])); 
			}
			echo json_encode($chuoijson, JSON_UNESCAPED_UNICODE);
		}
		echo "}";

	}

	function KiemTraDangNhap(){
		global $conn;
		if(isset($_POST["tendangnhap"]) || isset($_POST["tendangnhap"])){
			$tendangnhap = $_POST["tendangnhap"];
			$matkhau = $_POST["matkhau"];
		}

		$truyvan = "SELECT * FROM nguoidung WHERE TENDANGNHAP='".$tendangnhap."' AND MATKHAU='".$matkhau."'";
		$ketqua = mysqli_query($conn, $truyvan); 
		$demdong = mysqli_num_rows($ketqua);
		if($demdong >=1){
			$tennguoidung = "";
			while ($dong = mysqli_fetch_array($ketqua)) {
				$tennguoidung = $dong["TENNGUOIDUNG"];
			}
			echo "{ketqua: true, tennguoidung :\"".$tennguoidung."\" }";
		} else{
			echo "{ketqua: false}";
		}
	}

	function DangKyThanhVien(){
		global $conn;
		if(isset($_POST["tennguoidung"]) || isset($_POST["tendangnhap"]) || isset($_POST["matkhau"]) ||isset($_POST["maloainguoidung"]) || isset($_POST["emaildocquyen"])){

		$tennguoidung = $_POST["tennguoidung"];
		$tendangnhap = $_POST["tendangnhap"];
		$matkhau = $_POST["matkhau"];
		$maloainguoidung = $_POST["maloainguoidung"];
		$emaildocquyen = $_POST["emaildocquyen"];
		
		}
	

		$truyvan= "INSERT INTO nguoidung (TENNGUOIDUNG, TENDANGNHAP, MATKHAU, MALOAINGUOIDUNG,EMAILDOCQUYEN) 
		VALUES ('".$tennguoidung."', '".$tendangnhap."', '".$matkhau."', '".$maloainguoidung."', '".$emaildocquyen."')";

		if (mysqli_query($conn, $truyvan)) {
			echo "{ketqua: true}";
		} else {
			echo "{ketqua: false}".mysqli_error($conn);
		}

		mysqli_close($conn);

	}

	function LayDanhSachMenu(){
		global $conn;

		if(isset($_POST["maloaicha"]))
		$maloaicha=$_POST["maloaicha"];

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

		mysqli_close($conn);


	}

?>