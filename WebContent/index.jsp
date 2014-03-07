<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cinema Database</title>
<!-- Include one of jTable styles. -->
<link href="css/metro/red/jtable.css" rel="stylesheet" type="text/css" />
<link href="css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
<!-- Include jTable script file. -->
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="js/jquery.jtable.js" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#MovieContainer').jtable({
            title: 'Table of Movies',
            paging: true, //Enable paging
            pageSize: 10, //Set page size (default: 10)           
            actions: {
                listAction: 'MovieController?action=list',
                //createAction:'CRUDController?action=create',
                //updateAction: 'CRUDController?action=update',
                //deleteAction: 'CRUDController?action=delete'
            },
            fields: {
                title: {
                	title:'Title',
                    key: true,
                    list: true,
                    create:false
                },
                category: {
                    title: 'Category',
                    width: '30%',
                    edit:false
                },
                directorname: {
                    title: 'Director',
                    width: '30%',
                    edit:true
                }             
            }
        });
        $('#MovieContainer').jtable('load');
    });
</script>
</head>

<body>
<div style="width:60%;margin-right:20%;margin-left:20%;text-align:center;">
<h1>AJAX based CRUD operations in Java Web Application using jquery jTable plugin</h1>
<h4>Demo by Priya Darshini, Tutorial at <a href="http://www.programming-free.com/2013/08/ajax-based-crud-operations-in-java-web.html">www.programming-free.com</a></h4>
<div id="MovieContainer"></div>
</div>
</body>
</html>