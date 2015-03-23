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
else if (isset($_POST['B_Email']) && isset($_POST['B_Amount']) && isset($_POST['B_Date']) && isset($_POST['Email']) && isset($_POST['Mobile_No']) && isset($_POST['Password']))
 {
 
    $Email = $_POST['Email'];
    $Mobile_No = $_POST['Mobile_No'];
    $Password = $_POST['Password'];
    $B_Email = $_POST['B_Email'];
    $B_Amount = $_POST['B_Amount'];
    $B_Date = $_POST['B_Date'];
    require_once('db_connect.php');
    $db = new DB_CONNECT();
    $result = mysql_query("INSERT INTO Information(Email, Mobile_No, Password, B_Email, B_Amount, B_Date) VALUES('$Email', '$Mobile_No', '$Password', '$B_Email', '$B_Amount', '$B_Date')");
    if ($result) {
        $response["success"] = 1;
        $response["message"] = "Entry successfully created.";
        echo json_encode($response);
    } else {
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
        echo json_encode($response);
    }
}
else if (isset($_POST['L_Email']) && isset($_POST['L_Amount']) && isset($_POST['L_Date']) && isset($_POST['Email']) && isset($_POST['Mobile_No']) && isset($_POST['Password'])) {
    $Email = $_POST['Email'];
    $Mobile_No = $_POST['Mobile_No'];
    $Password = $_POST['Password'];$L_Email = $_POST['L_Email'];
    $L_Amount = $_POST['L_Amount'];
    $L_Date = $_POST['L_Date'];
    require_once ('db_connect.php');
    $db = new DB_CONNECT();
    $result = mysql_query("INSERT INTO Information(Email, Mobile_No, Password,L_Email, L_Amount, L_Date) VALUES('$Email', '$Mobile_No', '$Password', '$L_Email', '$L_Amount', '$L_Date')");
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