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
<!-- Include one of jTable styles. -->
<link href="js/themes/metro/blue/jtable.min.css" rel="stylesheet" type="text/css" />
<link href="js/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
<!-- Include jTable script file. -->
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="js/jquery.jtable.js" type="text/javascript"></script>


<script type="text/javascript">

    $(document).ready(function () {
        $('#TicketContainer').jtable({
            title: 'Tickets you booked',
            paging: true, //Enable paging
            sorting: true,
            multiSorting: true,
            pageSize: 10, //Set page size (default: 10)           
            actions: {
                listAction: 'TicketController?action=list',
                deleteAction: 'TicketController?action=delete'
            },
            fields: {
                seatnr: {
                	title:'Seat Number',
                    key: true,
                    list: true,
                    width: '50%',
                    create:true
                },
                money: {
                    title: 'Price',
                    width: '15%',
                    edit:false
                },
                projectionid: {
                    title: 'Id',
                    width: '20%',
                    edit:false
                }             
            }
        });
        $('#TicketContainer').jtable('load');
    });
</script>
<script type="text/javascript">

    $(document).ready(function () {
        $('#ProjectionContainer').jtable({
            title: '',
            paging: true, //Enable paging
            sorting: true,
            selecting: true,
            multiselect: true,
            pageSize: 10, //Set page size (default: 10)           
            actions: {
                listAction: 'TicketController?action=list',
                createAction: 'TicketController?action=create',
                updateAction: 'TicketController?action=update',            		
                deleteAction: 'TicketController?action=delete'
            },
            fields: {
            	id: {
                	title:'Id',
                    key: true,
                    list: true,
                    width: '10%',
                    create:true
                },
                language: {
                    title: 'Language',
                    width: '15%',
                    edit:true
                },
                date: {
                    title: 'Date',
                    width: '20%',
                    edit:true
                },  
                start: {
                    title: 'Start',
                    width: '10%',
                    edit:true
                },
                end: {
                    title: 'End',
                    width: '10%',
                    edit:true
                },
                theaterNr: {
                    title: 'Theater',
                    width: '10%',
                    edit:true
                },
                movie: {
                    title: 'Movie',
                    width: '25%',
                    edit:true
                }           
            }
        });
        $('#ProjectionContainer').jtable('load');
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

						<input type="email" name="email" placeholder="Email"> <input
							type="password" name="password" placeholder="Password">

						<button type="submit" class="pure-button pure-button-primary">Sign
							in</button>
					</fieldset>
				</form>
			</div>

			<h2 class="content-subhead">Your tickets</h2>
			<!-- Table container for Movies -->
			<div
				style="width: 80%; margin-right: 10%; margin-left: 10%; text-align: center;">
				<div id="ProjectionContainer"></div>
			</div>


		</div>

	</div>

	</div>
</body>
</html>