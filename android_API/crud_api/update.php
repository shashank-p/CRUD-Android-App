<?php
include 'connection.php';

$id = $_POST['id'];
$uid = $_POST['uid'];
$name = $_POST['name'];
$phone = $_POST['phone'];
$address = $_POST['address'];

$query = mysqli_query($con, "UPDATE data SET uid = '$uid', name = '$name', phone = '$phone', address = '$address' WHERE id = '$id' ");

if($query){
  $response['success'] = 'true';
  $response['message'] = 'Data Updated Successfully';
}else{
  $response['success'] = 'false';
  $response['message'] = 'Data Updation Failed';
}

echo json_encode($response);
?>
