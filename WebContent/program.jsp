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
        $('#ProjectionContainer').jtable({
            title: '',
            paging: true, //Enable paging
            sorting: true,
            pageSize: 10, //Set page size (default: 10)           
            actions: {
                listAction: 'ProjectionController?action=list',
                createAction: 'ProjectionController?action=create',
                updateAction: 'ProjectionController?action=update',            		
                deleteAction: 'ProjectionController?action=delete'
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
				<h1>What is on this week ?</h1>
				
    <h2 class="content-subhead">Program</h2>
			<!-- Table container for Projections -->
			<div
				style="width: 80%; margin-right: 10%; margin-left: 10%; text-align: center;">
				<div id="ProjectionContainer"></div>
			</div>

	
      </div>
      </div>
	
</body>
</html>