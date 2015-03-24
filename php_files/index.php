<?php
$response = array();
if (isset($_GET['Email']) && isset($_GET['Mobile_No']) && isset($_GET['Password']) && !isset($_GET['B_Email']) && !isset($_GET['B_Amount']) && !isset($_GET['B_Date']) && !isset($_GET['L_Email']) && !isset($_GET['L_Amount']) && !isset($_GET['L_Date'])) {
    $Email = $_GET['Email'];
    $Mobile_No = $_GET['Mobile_No'];
    $Password = $_GET['Password'];
    require_once ('db_connect.php');
    $db = new DB_CONNECT();
    $result = mysql_query("INSERT INTO Information(Email, Mobile_No, Password) VALUES('$Email', '$Mobile_No', '$Password')");
    if ($result) {
        $response["success"] = 1;
        $response["message"] = "Entry successfully created.";
        echo json_encode($response);
    } 
    else {
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
        echo json_encode($response);
    }
}
 else {
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
    echo json_encode($response);
    }
?>		
