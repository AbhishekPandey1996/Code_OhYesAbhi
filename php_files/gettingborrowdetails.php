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
    $result_borrowed1 = mysql_query("SELECT B_Email,B_Amount,B_Date FROM Information WHERE Email = $Email");
    $result_borrowed2 = mysql_query("SELECT Email,L_Amount,L_Date FROM Information WHERE L_Email = $Email");
    
 
    if (!empty($result_borrowed1 || !empty($result_borrowed2)) {
        // check for empty result
        if (mysql_num_rows($result_borrowed1) > 0 || mysql_num_rows($result_borrowed1) > 0) {
 
            $result_borrowed1 = mysql_fetch_array($result_borrowed1);
            $result_borrowed2 = mysql_fetch_array($result_borrowed2);
            $result_1 = array();
            $result_2 = array();
            $result_1["B_Email"] = $result_borrowed1["B_Email"];
            $result_1["B_Amount"] = $result_borrowed1["B_Amount"];
            $result_1["B_Date"] = $result_borrowed1["B_Date"];
            $result_2["B_Email"] = $result_borrowed2["Email"];
            $result_2["B_Amount"] = $result_borrowed2["L_Amount"];
            $result_2["B_Date"] = $result_borrowed2["L_Date"];
            // success
            $response["success"] = 1;
            $final_result=array();
            $final_result=array_merge($result_1,$result_2);
            // user node
            $response["product"] = array();
 
            array_push($response["product"], $final_result);
 
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