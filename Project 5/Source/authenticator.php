<?php
/* 	Name: Nathan Guenther
	Course: CNT 4714 – Fall 2015 
	Assignment title: A Three-Tier Distributed Web-Based Application Using PHP and Apache
	Due Date: November 29, 2015
*/

// Authenticates imputed credentials against the SQL database
include('dbConnect.php');

session_start();

$username = $_POST['username'];
$password = $_POST['password'];

echo $username."\n<br />";
echo $password."<br />";

$URL = 'localhost';
$DATABASE = 'project5';

$_SESSION['username'] = $username;
$_SESSION['url'] = $URL;
$_SESSION['password'] = $password;
$_SESSION['database'] = $DATABASE;

header('Location: query.php');
?>