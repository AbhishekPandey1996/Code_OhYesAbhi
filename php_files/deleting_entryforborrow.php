<?php
 
/*
 * Following code will delete a product from table
 * A product is identified by product id (pid)
 */
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['B_Email']) && isset($_POST['B_Date'] && isset($_POST['B_Amount']) {
    $B_Email = $_POST['B_Email'];
    $B_Date = $_POST['B_Date'];
    $B_Amount = $_POST['B_Amount'];
 
    // include db connect class
    require_once ('db_connect.php');
 
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql update row with matched pid
    $result1 = mysql_query("DELETE FROM Information WHERE B_Email = $B_Email AND B_Amount = $B_Amount AND B_Date = $B_Date");
    $result1 = mysql_query("DELETE FROM Information WHERE Email = $B_Email AND L_Amount = $B_Amount AND L_Date = $B_Date");
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