<?php
 
/*
 * Following code will delete a product from table
 * A product is identified by product id (pid)
 */
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['L_Email']) && isset($_POST['L_Date'] && isset($_POST['L_Amount']) {
    $L_Email = $_POST['L_Email'];
    $L_Date = $_POST['L_Date'];
    $L_Amount = $_POST['L_Amount'];
 
    // include db connect class
    require_once ('db_connect.php');
 
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql update row with matched pid
    $result1 = mysql_query("DELETE FROM Information WHERE L_Email = $L_Email AND L_Amount = $L_Amount AND L_Date = $L_Date");
    $result2 = mysql_query("DELETE FROM Information WHERE Email = $L_Email AND B_Amount = $L_Amount AND B_Date = $L_Date");
    // check if row deleted or not
    if (mysql_affected_rows() > 0) {
        // successfully updated
        $response["success"] = 1;
        $response["message"] = "Product successfully deleted";
 
        // echoing JSON response
        echo json_encode($response);
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "No product found";
 
        // echo no users JSON
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>