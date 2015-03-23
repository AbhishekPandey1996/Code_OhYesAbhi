<?php
 
/*
 * Following code will get single product details
 * A product is identified by product id (Email)
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once ('db_connect.php');
 
// connecting to db
$db = new DB_CONNECT();
 
// check for post data
if (isset($_GET["Email"])) {
    $Email = $_GET['Email'];
 
    // get a product from products table
    $result_mobile= mysql_query("SELECT Mobile_No FROM Information WHERE Email = $Email");
    
 
    if (!empty($result_mobile) {
        // check for empty result
        if (mysql_num_rows($result_mobile) > 0) {
 
            $result_mobile = mysql_fetch_array($result_mobile);
            
            $result_final = array();
            
            $result_final["Mobile_No"] = $result_mobile["Mobile_No"];
            // success
            $response["success"] = 1;
            
            
            // user node
            $response["product"] = array();
 
            array_push($response["product"], $result_final);
 
            // echoing JSON response
            echo json_encode($response);
        } else {
            // no product found
            $response["success"] = 2;
            $response["message"] = "No mobile found";
 
            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no product found
        $response["success"] = 2;
        $response["message"] = "No mobile found";
 
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