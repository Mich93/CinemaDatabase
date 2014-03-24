<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport"></meta>
<!--  Include Pure Css -->
<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.4.2/pure-min.css">
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
        $('#MovieContainer').jtable({
            title: 'Movies',
            paging: true, //Enable paging
            sorting: true,
            defaultSorting: 'Name ASC',
            pageSize: 10, //Set page size (default: 10)           
            actions: {
                listAction: 'MovieController?action=list',
                createAction:'MovieController?action=create',
                updateAction: 'MovieController?action=update',
                deleteAction: 'MovieController?action=delete'
            },
            fields: {
                title: {
                	title:'Title',
                    key: true,
                    list: true,
                    width: '50%',
                    create:true
                },
                category: {
                    title: 'Category',
                    width: '15%',
                    edit:true,
                    options:["Action", "Adventure", "Comedy", "Crime", "Erotica", "Faction", "Fantasy", "Historical", "Horror", "Mystery", "Paranoid", "Philosophical", "Political", "Romance", "Saga", "Satire", "Science fiction", "Slice of Life", "Speculative", "Thriller", "Urban"]
                },
                directorname: {
                    title: 'Director',
                    width: '20%',
                    edit:true
                }             
            }
        });
        $('#MovieContainer').jtable('load');
    });
    
    $(document).ready(function () {
        $('#ActorContainer').jtable({
            title: 'Actors',
            paging: true, //Enable paging
            sorting: true,
            pageSize: 10, //Set page size (default: 10)           
            actions: {
                listAction: 'ActorController?action=list',
                createAction:'ActorController?action=create',
                updateAction: 'ActorController?action=update',
                deleteAction: 'ActorController?action=delete'
            },
            fields: {
                actorid: {
                	title:'Id',
                    key: true,
                    list: true,
                    width: '10%',
                    create:true
                },
                name: {
                    title: 'Name',
                    width: '45%',
                    edit:true
                },
                surname: {
                    title: 'Surname',
                    width: '45%',
                    edit:true
                }             
            }
        });
        $('#ActorContainer').jtable('load');
    });
</script>
</head>

<!--  BODY ------------------------------------------------------------------------------------------------ -->
<body>

<!-- Include sidemnu.jsp -->
<jsp:include page="sidemenu.jsp"></jsp:include>


		<div id="main">
			<div class="header">
				<h1>Cinema Database</h1>
				
			</div>
			
		<div class="content">
			<h2 class="content-subhead">Movies</h2>
			<!-- Table container for Movies -->
			<div
				style="width: 80%; margin-right: 10%; margin-left: 10%; text-align: center;">
				<div id="MovieContainer"></div>
			</div>
			
			<h2 class="content-subhead">Actors</h2>
			<!-- Table container for Actors -->
			<div
				style="width: 80%; margin-right: 10%; margin-left: 10%; text-align: center;">
				<div id="ActorContainer"></div>
			</div>

											
		</div>
		
		</div>

</div>
</body>
</html>