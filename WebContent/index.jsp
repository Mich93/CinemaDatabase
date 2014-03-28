<% Class.forName("org.postgresql.Driver"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport"></meta>
<!--  Include Pure Css -->
<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.4.2/pure-min.css">
<link href="css/side-menu.css" rel="stylesheet"></link>
</head>

<!--  BODY ------------------------------------------------------------------------------------------------ -->
<body>

<!-- Include sidemnu.jsp -->
<jsp:include page="sidemenu.jsp"></jsp:include>

		<div id="main">
			<div class="header">
				<h1>Cinema Booking System</h1>
				<h2>A project by: Marco, Ettore , Stefano and Julian</h2>
			</div>
			
			<div class="content">
				
				<h2 class="content-subhead">Our Project</h2>
				<p>This site displays and manipulates some data of our database.</p>

				<h2 class="content-subhead">Functionalities</h2>
				<p>The 'Program' tab displays the projection that are available on the database. 
				    Projections can be updated, created or deleted.
				    A signed user can book a projection.
				    Try it by entering <br><b>Username:</b> <i>Ettore.Ciprian@stud-inf.unibz.it</i> <br><b>password:</b>  <i>sottone93</i>
				    <br>Or also  <br><b>Username:</b> <i>Stefano.Mich@stud-inf.unibz.it</i> <br><b>password:</b>  <i>latemar93</i></p>
				<p> The 'My Tickets' tab allows the user to view his/her tickets by querying the 
				   account table and showing the corresponding tuples.  </p>
				<p> The 'Catalogue' tab allows the user to manipulate the movies and actors stored in the database.
				  </p>
				  <p> The 'About' tab shows the team and its members.
				  </p>
				  
				<h2 class="content-subhead">Implementation</h2> 
				<p>To inspect the <code>code</code> you can also clone <a href="https://github.com/Mich93/CinemaDatabase" target="_blanc">our Repository</a> </p>
				<p> This site has been implemented using Java servlets, Javascript and Html. </p>
				<p>  The tables are manipulated through Json arrays which are sent to a JQuery plugin, <a href="http://www.jtable.org/" target="_blanc">Jtable</a>.</p>
				<p> The graphic implementation, which is also mobile responsive, has been created using <a href="http://purecss.io" target="_blanc"/>Pure Css</a>  </p>
				  
			</div>
		</div>
		
		</div>

</body>

</html>