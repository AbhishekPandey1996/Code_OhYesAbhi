<?php
$response = array();
if (isset($_GET['Email']) && isset($_GET['Password'])) {
$Email = $_GET['Email'];
$Password = $_GET['Password'];
require_once ('db_connect.php');
$db = new DB_CONNECT();
$result_1 = mysql_query("SELECT Email FROM Information WHERE Email = $Email");
if ($result_1) {
$result_2 = mysql_query("SELECT Email FROM Information WHERE Email = $Email AND Password = $Password");
if ($result_2) {
$response["success"] = 3;
$response["message"] = "password match";
echo json_encode($response);
} else {
$response["success"] = 2;
$response["message"] = "password do not match";
echo json_encode($response);
}
}else {
$response["success"] = 1;
$response["message"] = "username not found";
echo json_encode($response);
}
} else {
$response["success"] = 0;
$response["message"] = "Required field(s) is missing";
echo json_encode($response);
}
?>        