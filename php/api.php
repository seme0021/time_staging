<?php
   //connect to db
   $link = mysql_connect("localhost","root","hophiper1!") or die(mysql_error());
   mysql_select_db("time_raw") or die(mysql_error());

   //methods
   //$arr = array();
   //$rs = mysql_query("select distinct x,y from tmp_chart");

   //while($obj = mysql_fetch_object($rs)) {
     //      $arr[] = $obj;
      // }
   //$myJSON = json_encode($arr);
   //echo $myJSON;
   // echo '{"sample":'.json_encode($arr).'}';


   //method 2
   $sql = mysql_query("select distinct y,time from tmp_chart");
   $results = array();

   while($row = mysql_fetch_array($sql))
     {
       $res = array(
           'x' => strtotime($row['time']) * 1000,
           'y' => (int)$row['y']
        );
       array_push($results, $res);
     }
   echo json_encode($results);
?>
