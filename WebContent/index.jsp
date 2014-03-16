
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
            multiSorting: true,
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
                    edit:true
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
</script>
</head>

<!--  BODY ------------------------------------------------------------------------------------------------ -->
<body>

<!-- Include sidemnu.jsp -->
<jsp:include page="jsp/sidemenu.jsp"></jsp:include>

		<div id="main">
			<div class="header">
				<h1>The most awesome Cinema Database</h1>
				<h2>A project by: Marco, Ettore , Stefano and Julian</h2>
			</div>

			<!-- Table container for Movies -->
			<div
				style="width: 60%; margin-right: 20%; margin-left: 20%; text-align: center;">
				<div id="MovieContainer"></div>
			</div>

			<div class="content">
				
				<h2 class="content-subhead">Now Let's Speak Some Latin</h2>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed
					do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
					enim ad minim veniam, quis nostrud exercitation ullamco laboris
					nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
					reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
					pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
					culpa qui officia deserunt mollit anim id est laborum.</p>

				<h2 class="content-subhead">Try Resizing your Browser</h2>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed
					do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
					enim ad minim veniam, quis nostrud exercitation ullamco laboris
					nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
					reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
					pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
					culpa qui officia deserunt mollit anim id est laborum.</p>
			</div>
		</div>
		
		</div>

</body>

</html>