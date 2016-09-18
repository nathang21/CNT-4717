<?php
/* 	Name: Nathan Guenther
	Course: CNT 4714 – Fall 2015 
	Assignment title: A Three-Tier Distributed Web-Based Application Using PHP and Apache
	Due Date: November 29, 2015
*/

// Performs query, and displays results on page for user.

session_start();
include('dbConnect.php');

// Initialize Variables
$database = Database_Factory::create(1);
$database->connect($_SESSION['url'],$_SESSION['username'],$_SESSION['password'],$_SESSION['database']);
$results_string;
$error_sql;
settype($error_sql, 'bool');
$query;
settype($query, 'bool');
$error_message;
$error_message2;
$error_message3;
$alert = "";
settype($alert, 'bool');
$alert_message;
$insert_results_string;
$temp;
$updateSuppliers = ""; 

// If Query
if(isset($_SESSION['query']))
{	
	$query = true;
	
	// Execute Query
    $_SESSION['results'] = $database->run_query($_SESSION['query']);
	
	// Handle Error
    if(is_string($_SESSION['results']))
	{
		$error_message = "<p><h2 style='color:yellow;'>Major Error:</h2></p>";
		$results_string .= "<span>".$_SESSION['results']."</span>";
		$error_sql = true;
    }
	
	// Display Query Results
	else
	{
		$error_message = "<p><h2 style='color:yellow;'>Query Results</h2></p>";
		$error_sql = "";
        $results = $_SESSION['results'];
        $field_count = $results->field_count;
        $fields = $results->fetch_fields();

        $results_string .= "<table border=1 width=300px>  
							<tr>";
							
        foreach($fields as $field){
            $results_string .= "<th class='first'>".$field->name."</th>";
        }	
        $results_string .= '</tr>';
        while($row = $results->fetch_row()){
            $results_string .= "<tr class='rest'>";
            for($i = 0; $i < $field_count; $i++){
                $results_string .= '<td align="left">'.$row[$i]."</td>";
            }
            $results_string .= '</tr>';
        }
        $results_string .= '</table>';
    }
	
    unset($_SESSION['query']);	
}

// If Update
else if(isset($_SESSION['update']))
{
	$error_sql = "";
	$query = "";
    if(preg_match('/^insert into shipments/',strtolower($_SESSION['update'])) || preg_match('/^update shipments/',strtolower($_SESSION['update'])))
	{ 
        $supplier_snum;
        $updateSuppliers = false;    

		// Break down statement
        $first = strpos($_SESSION['update'],"(")+1;
        $last = strpos($_SESSION['update'],")");
        $temp = substr($_SESSION['update'],$first,$last);
        $temp = preg_replace('/\)/', '',$temp);
        $temp = preg_replace('/\;/','',$temp);
        $temp = preg_replace('/\'/','',$temp);
        $temp = preg_replace("/\s+/","",$temp);
        $values = explode(",",$temp);
		// Determine if business logic is needed
        foreach($values as $val)
		{
            if($val >= 100)
			{
                $updateSuppliers = true;
            }
			else if(preg_match('/^S/',$val))
			{
                $supplier_snum = $val;
            }
        }
    }
	
	// If Business Logic
	if($updateSuppliers){
			
	
	// Prep tables for Business Logic
	$noReturn = 0;
	$prep = 0;
	$noReturn = $database->run_update("create table beforeShipments2 like shipments");
	$noReturn = 0;
	$noReturn = $database->run_update("insert into beforeShipments2 select * from shipments");
	$noReturn = 0;
	}
	
	// Perform user command
	$_SESSION['results'] = $database->run_update($_SESSION['update']);
	
	// Insert Command
    if(is_numeric($_SESSION['results']))
	{
		$error_message = "<p><h2 style='color:yellow;'>Insert Query Results</h2></p>";
		
		// Check if business logic needed
        if($updateSuppliers){
			
			// Alert that busines logic has triggered
			$alert = true;
			
			//  Re-join table after business logic
			$prep = $database->run_update("update suppliers set status = status + 5 where snum in (select distinct snum from shipments left join beforeShipments2 using (snum, pnum, jnum, quantity) where beforeShipments2.snum is null);");
			}
			
			// Cleanup
			$noReturn = $database->run_update("DROP TABLE beforeShipments2");
			
			// Output updated TABLE
			$_SESSION['updated'] = $database->run_query("SELECT * FROM SUPPLIERS");
			$updated = $_SESSION['updated'];
			$field_count = $updated->field_count;
			$fields = $updated->fetch_fields();
			
			
			$insert_results_string .= "<table border=1 width=300px>  
							<tr>";
							
			foreach($fields as $field){
				$insert_results_string .= "<th class='first'>".$field->name."</th>";
			}	
			$insert_results_string .= '</tr>';
			while($row = $updated->fetch_row()){
				$insert_results_string .= "<tr class='rest'>";
				for($i = 0; $i < $field_count; $i++){
					$insert_results_string .= '<td align="left">'.$row[$i]."</td>";
				}
				$insert_results_string .= '</tr>';
			}
			$insert_results_string .= '</table>';
        
    }
	
	// Update Command
	else
	{
		$error_message = "<p><h2 style='color:yellow;'>Major Error:</h2></p>";
		$results_string .= "<span>".$_SESSION['results']."</span>";
		$error_sql = true;
    }
	
    unset($_SESSION['update']);
}
	// Logout Option
	else if(isset($_POST['logout']))
{
    session_destroy();
    header('Location:index.php');
}
?>

<!DOCTYPE html>
<html>
	<head>
		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>

		<script>
		function goBack()
		{
			window.history.back()
		}
		</script>
		<style>
			.first{
				background:#696969;
				color:white;
				font-weight:bold;
				text-align:left
			}
			.rest{
				background:yellow;
				color:black;
				text-align:left
				max-width:100%;
				white-space:nowrap;
			}
		</style>
	</head>

    <body bgcolor="black">
        <header style="text-align:center; color:red">
			<h1>CNT 4714 - Project Five Database Client</h1>
		</header>
	<div id='middle' style='width: auto;height: 1000px; '>
    <div id='center' style='width: 1000px; margin-left: auto;margin-right: auto;'>
        <hr />
        <div id='l-middle' style='float: left; text-align:left; color:yellow;'>
			<table>
				<tr>
					<td><b><span style="color: yellow;">Welcome back</b></span><br /></td>
				</tr><tr></tr>
				<tr>
					<td><span style="color: yellow;"><?php echo $_SESSION['username']; ?></span><br /></td>
				</tr><tr></tr>
				<tr>
					<td><form action="output.php" method="post">
					<input type="submit" value="Logout" id="logout" name="logout" style="width:75; height:25px; background-color:red; color:white; text-align:left; border:none;"/>
					</form></td>
				</tr>
			</table>
            
        </div>
		
        <div id='inner_center' style='width:500px;margin-left: auto; margin-right:auto;'>
			<div id='sql_results'>
			<?php 
				echo $error_message;
					
				// Query Output
				if($query == "true" && $error_sql != "true") {
					echo $results_string;
				}
		
				// Error Output
				if ($error_sql == "true") 
				{
					$error_message2 = "<span style='color:yellow';>An SQL Exception occurred while interacting with the Project 5database.</p><p style='color:yellow';>The error message was:</span><br /><b>";
					echo $error_message2;
					echo $results_string;
					?>
					</b>
					<?php
				}

			?>	
			<?php
				// Follow-up Error Output
				if ($error_sql == "true") { 	 	
				
					$error_message3 = "<p style='color:yellow';>Please try again later</p>";
					echo $error_message3;
				}
			
				// If Insert --> Show Alert & Updated Table
				if ($query != "true" && $alert == "true" && $error_sql != "true") {
					echo '<script language="javascript">';
					echo 'alert("ALERT: SUPPLIER STATUS HAS CHANGED DUE TO BUSINESS LOGIC.\nDISPLAYING UPDATED SUPPLIER TABLE!")';
					echo '</script>';	
					echo $insert_results_string;
				}
				
				// If Update -> Show Business Logic Changes
			  else if ($query != "true" && $alert != "true" && $error_sql != "true") {
				echo "<span style='color:green';'>".$_SESSION['results']." row(s) have been successfully updated!</span><br /><br />";
			  }			
			?>
				
				<br /><br /></div>
                <a href='query.php' style="font-weight:bold; color:#00FF00;"> Return to Main Page </a>
        
		
        <div id='r-middle' style='float: right;'>
        </div>
		
        <div id='footer' style='clear:both; color:blue; text-align:center;'>
			<hr />
			<p>&copy; MJL &nbsp CNT 4717 PHP-Based Database Client</p>
		</div>
    </div> 
</div> 
</body>
</html>