<?php
/* 	Name: Nathan Guenther
	Course: CNT 4714 – Fall 2015 
	Assignment title: A Three-Tier Distributed Web-Based Application Using PHP and Apache
	Due Date: November 29, 2015
*/

// Logic to determine type of query, and how to handle it. Accepts query input from user.
session_start(); 
if(isset($_POST['query']))
{
   if(preg_match('/[[:alnum:]]/',$_POST['sql']))
   {
       $_SESSION['query'] = $_POST['sql'];
   }
   
   else
   {
       $_SESSION['query'] = 'select * from shipments';
   }
	redirect('output.php');
}

else if(isset($_POST['update']))
{
    if(preg_match('/[[:alnum:]]/',$_POST['sql']))
	{
        $_SESSION['update'] = $_POST['sql'];
        redirect('output.php');
    }
}

else if(isset($_POST['logout']))
{
    session_destroy();
    header('Location:index.php');
}

function redirect($url)
{
    if (!headers_sent())
    {    
        header('Location: '.$url);
        exit;
        }
    else
        {  
        echo '<script type="text/javascript">';
        echo 'window.location.href="'.$url.'";';
        echo '</script>';
        echo '<noscript>';
        echo '<meta http-equiv="refresh" content="0;url='.$url.'" />';
        echo '</noscript>'; exit;
    }
}
?>

<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
        <script>
            $("#logout").click(function() 
			{
                  //destroy session and redirect
            });
			
            function showHide()
			{
                $("#sql").show();
                $("#results").hide();
            }
			
            window.onload =showHide;
        </script>
		
    </head>
    <body bgcolor="black">
        <header style="text-align:center; color:red">
            <h1>CNT 4714 - Project Five Database Client</h1>
		</header>
		
        <div id="middle" style="width: auto;height: 1000px; ">
        <div id="center" style="width: 1000px;margin-left: auto;margin-right: auto;">
        <hr />
            <div id="l-middle" style="float: left; text-align:left; font-size:20px; color:yellow;">
				<table>
				<tr>
					<td><b>Welcome back</b><br /></td>
				</tr><tr></tr>
                <tr>
					<td><?php echo $_SESSION["username"]; ?><br /></td>
				</tr><tr></tr>
				
                <tr><td><form method="post" action="query.php">
                    <input type="submit" value="Logout" id="logout" name="logout" style="width:75; height:25px; background-color:red; color:white; text-align:left; border:none;"/>
				</form></td></tr>
				</table>
            </div>
			
			<!--END OF L-MIDDLE-->
            <div id="r-middle" style="float: right; color:yellow;">
                <div id="sql">
                    <h2>Enter Query</h2>
                    <p> Please enter a valid query or update statement. You may also just press "Submit Query" to select all parts from the database.</p>
                    <form method="post" action="query.php">
                        <textarea rows="20" cols="70" id="sql" name="sql"></textarea>
                        <table>
							<tr>
								<td><br /></td>
							</tr>
                            <tr style="color:red;">
                                <td><input type="submit" value="Submit Query" id="query" name="query" style="width:75; height:25px; background-color:red; color:white; text-align:left; border:none;"/></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                <td><input type="submit" value="Submit Update" id="update"name="update" style="width:75; height:25px; background-color:red; color:white; text-align:left; border:none;"/></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                <td><input type="reset" value="Reset Window" style="width:75; height:25px; background-color:red; color:white; text-align:left; border:none;"/></td>
                            </tr>
							<tr>
								<td><br /><br /><br /></tr>
							</tr>
                        </table>
                    </form>
                </div>
                <div id="results"></div>
            </div> <!--END OF R-MIDDLE-->
            <div id='footer' style='clear:both; color:blue; text-align:center;'>
                <hr />
				<p>&copy; MJL &nbsp CNT 4717 PHP-Based Database Client</p>
            </div>
            </div> <!--END OF CENTER-->
        </div> <!--END OF MIDDLE-->


    </body>
</html>