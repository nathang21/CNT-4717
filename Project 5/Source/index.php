<?php
/* 	Name: Nathan Guenther
	Course: CNT 4714 – Fall 2015 
	Assignment title: A Three-Tier Distributed Web-Based Application Using PHP and Apache
	Due Date: November 29, 2015
*/

// Home page what the user sees before interacting with the web app.
session_start();

?>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src"https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    </head>
	
    <body bgcolor="black">
        <header style="text-align:center; color:red;">
            <h1>CNT 4714 - Project Five Database Client</h1>
        </header>

        <div id="middle" style="width: auto;height: 1000px; ">
        <div id="center" style="width: 1000px;margin-left: auto;margin-right: auto;">
        <hr />
            <div id="l-middle" style="float: left;">
                <form method="post" action="authenticator.php">
                    <table style="font-size:18px; font-weight:bold; color:yellow;">
                        <tr>
                            <td><label for="username">Username </label></td>
						</tr>
						<tr>
                            <td><input type="textbox" id="username" name="username" style="width: 150px;"/></td>
                        </tr>
                        <tr>
                            <td><label for="password">Password </label></td>
						</tr>
						<tr>
                            <td><input type="password" id="password" name="password" style="width: 150px;"/></td>
						<tr></tr><tr></tr>
						<tr>
							<td><input type="submit" id="submit;" name="submit" value="Login" style="width:75; height:25px; background-color:red; color:white; text-align:left; border:none"/></td>
						</tr>
					</table>
					

                </form>
            </div>

			<!--END OF L-MIDDLE-->
			
            <div id="r-middle" style="float: right; color:yellow;">
			
                <h2>Welcome to the Database Client</h2>
                <p>This will allow you to run SQL queries and update statements on the Project 5 Database.</p>
                <h2>Database</h2>
                <p>Connecting to MySQL Database</p>
                <h2>Features</h2>
					<ul>
                    <li>User Authentication</li>
                    <li>Select Query</li>
                    <li>Update Query</li>
                    <li>Business Logic Implementation</li>
                    <li>Results Page</li>
                    <li>Error Checking</li>
                </ul>
                <h2>User Login</h2>
                <p>Use the following on the left</p>
                <ul>
                    <li><b>Username:</b> "client1", "client2", or "root"</li>
                    <li><b>Password: </b> "client1", "client2", or "root"</li>


                </ul>
            </div>
			
            <div id='footer' style='clear:both; color:blue; text-align:center;'>
                <hr />
				<p>&copy; MJL &nbsp CNT 4717 PHP-Based Database Client</p>
            </div>
            </div> 
        </div>
    </body>
</html>