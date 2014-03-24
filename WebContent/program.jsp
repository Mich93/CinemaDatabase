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
            title: 'Tickets you booked',
            paging: true, //Enable paging
            sorting: true,
            multiSorting: true,
            pageSize: 10, //Set page size (default: 10)           
            actions: {
                listAction: 'ProjectionController?action=list',
                createAction: 'ProjectionController?action=create',
                updateAction: 'ProjectionController?action=update',            		
                deleteAction: 'ProjectionController?action=delete'
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
        $('#ProjectionContainer').jtable('load');
    });
</script>
</head>

<!--  BODY ------------------------------------------------------------------------------------------------ -->
<body>

	<!-- Include sidemnu.jsp -->
	<jsp:include page="sidemenu.jsp"></jsp:include>


	

	</div>
</body>
</html>