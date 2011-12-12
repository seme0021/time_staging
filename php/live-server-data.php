<?php
// Set the JSON header
header("Content-type: text/json");

// The x value is the current JavaScript time, which is the Unix time multiplied by 1000.
//$x = time() * 1000;
// The y value is a random number
//$y = 22;

// Create a PHP array and echo it as JSON
//$ret = array($x, $y);
$ret = array(
   "x" => 1000,
   "y" => 10
);

//echo $myObj
echo json_encode($ret);
?>
