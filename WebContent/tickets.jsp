<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport"></meta>
<!--  Include Pure Css -->
<link rel="stylesheet"
	href="http://yui.yahooapis.com/pure/0.4.2/pure-min.css">
<link href="css/side-menu.css" rel="stylesheet"></link>


<!-- Include jTable script file. -->
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="js/jquery.jtable.js" type="text/javascript"></script>
<script type="text/javascript">

    $(document).ready(function () {
        $('#TicketContainer').jtable({
            title: 'Movies',
            paging: true, //Enable paging
            sorting: true,
            multiSorting: true,
            pageSize: 10, //Set page size (default: 10)           
            actions: {
                listAction: 'TicketController?action=list&id='+,
                createAction:'TicketController?action=create',
                updateAction: 'TicketController?action=update',
                deleteAction: 'TicketController?action=delete'
            },
            fields: {
                seatnr: {
                	title:'Title',
                    key: true,
                    list: true,
                    width: '50%',
                    create:true
                },
                money: {
                    title: 'Price',
                    width: '15%',
                    edit:true
                },
                projectionid: {
                    title: 'Director',
                    width: '20%',
                    edit:true
                }             
            }
        });
        $('#TicketContainer').jtable('load');
    });
</script>
</head>

<!--  BODY ------------------------------------------------------------------------------------------------ -->
<body>

	<!-- Include sidemnu.jsp -->
	<jsp:include page="sidemenu.jsp"></jsp:include>


	<div id="main">
		<div class="header">
			<h1>My Tickets</h1>

		</div>

		<div class="content">
			<h2 class="content-subhead">Login</h2>
			<!-- Login form  -->
			<div>
				<form action="TicketController" method="GET" class="pure-form">
					<fieldset>
						<legend>Enter your e-mail and password </legend>

						<input type="email" placeholder="Email"> <input
							type="password" placeholder="Password">

						<button type="submit" class="pure-button pure-button-primary">Sign
							in</button>
					</fieldset>
				</form>
			</div>

			<h2 class="content-subhead">Your tickets</h2>
			<!-- Table container for Movies -->
			<div></div>


		</div>

	</div>

	</div>
</body>
</html>