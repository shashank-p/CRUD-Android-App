<?php
include 'connection.php';

$uid = $_POST['uid'];
$name = $_POST['name'];
$phone = $_POST['phone'];
$address = $_POST['address'];
$response = array();
$query = mysqli_query($con, "INSERT INTO data (uid, name, phone, address) VALUES ('$uid','$name','$phone','$address')");

if($query){
  $response['success'] = 'true';
  $response['message'] = 'Data Inserted Successfully';
}else{
  $response['success'] = 'false';
  $response['message'] = 'Data Insertion Failed';
}

echo json_encode($response);
?>
