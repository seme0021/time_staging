<?php

$myTweets = array(
    "text" => "Just wrote some stuff", 
    "created_at" => "Sun Dec 12 21:36:12 +0000 2009"
);

$myTwitterUser = array("name" => "mikhailsemeniuk");
$myTweets['user'] = $myTwitterUser;
$myJSONTweets = json_encode($myTweets);

echo $myJSONTweets;
?>
