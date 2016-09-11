<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Manage Goals</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="mobile-web-app-capable" content="yes" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link href="<c:url value="/static/resources/css/bootstrap.min.css"/>"
	rel="stylesheet">
<link
	href="<c:url value="/static/resources/css/bootcards-desktop.min.css"/>"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/static/resources/css/font-awesome.min.css"/>"
	rel="stylesheet" />
<link href="<c:url value="/static/resources/css/style.css"/>"
	rel="stylesheet" />

<script src="<c:url value="/static/resources/js/jquery.min.js"/>"></script>
<script>
	var isDesktop = true;
</script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
      <![endif]-->
</head>

<body>
  <!-- fixed top navbar -->
  <div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
      </div>

      <button type="button" class="btn btn-default btn-back navbar-left pull-left hidden" onclick="history.back()">
        <i class="fa fa-lg fa-chevron-left"></i><span>Back</span>
      </button>
      <!-- menu button to show/ hide the off canvas slider -->
      <button type="button" class="btn btn-default btn-menu navbar-left pull-left offCanvasToggle" data-toggle="offcanvas">
        <i class="fa fa-lg fa-bars"></i><span>Menu</span>
      </button>  

      <a class="navbar-brand no-break-out" title="Customers" href="/">ACME HealthAppraisal V1.0.0</a>  
      
      <div class="navbar-collapse collapse">
        <ul class="nav navbar-nav">
          <li>
            <a href="/dashboard" data-pjax="#main" data-title="Customers">
              <i class="fa fa-dashboard"></i>
              Dashboard
            </a>          
          </li>
		  <li>		
			<c:if test="${uinfo.loginstatus == true }"> <a href="/logout">Logout</a> </c:if> 
			<c:if test="${uinfo.loginstatus != true }"> <a href="/login">Login</a> </c:if>
		  </li>

        </ul>
      </div>          
    </div>
  </div>   

  <!-- slide in menu (mobile only) -->
  <nav id="offCanvasMenu" class="navmenu offcanvas offcanvas-left">
    <ul class="nav">
      <li>
        <a href="/dashboard" data-pjax="#main" data-title="Customers">
          <i class="fa fa-lg fa-dashboard"></i>
          Dashboard
        </a>          
      </li>
      <li>
			<c:if test="${uinfo.loginstatus == true }"> <a href="/logout">Logout</a> </c:if> 
			<c:if test="${uinfo.loginstatus != true }"> <a href="/login">Login</a> </c:if>        
      </li>
    </ul>

    <div style="margin-top:20px; padding-left: 20px; font-size: 12px; color: #777">v1.0.1</div>
  </nav>

	<div class="container bootcards-container" id="main">

			<div class="row">
			<form:form class="form-horizontal" modelAttribute="goalform" method="POST" action="/creategoal">
				<div class="panel panel-default">
					  <div class="panel-heading clearfix">
						<h3 class="panel-title pull-left">Create New Goal</h3>
						<div class="btn-group pull-right">
						  <button class="btn btn-danger" type="reset">
							<i class="fa fa-times"></i>
							Reset
						  </button>
						  <button class="btn btn-success" type="submit">
							<i class="fa fa-check"></i>
							Save
						  </button>
						</div>
					  </div>
					  
					  <div class="modal-body">
						<form class="form-horizontal">
						  <div class="form-group">
							<label class="col-xs-3 control-label">Goal Title</label>
							<div class="col-xs-9">
								<form:input type="text" path="goal_title" class="form-control" placeholder="e.g. Loose weight 3 lbs in 3 weeks" />
							</div>
						  </div>
						  
						  <div class="form-group">
							<label class="col-xs-3 control-label">Description</label>
							<div class="col-xs-9">
							  <form:textarea class="form-control" path="goal_desc" rows="6"></form:textarea>
							</div>
						  </div>
						  
						  <div class="form-group">
							<label class="col-xs-3 control-label">Goal Category</label>
							<div class="col-xs-9">
								<form:select path="goal_category" class="form-control">
									<form:option selected="selected" value="Eating">Eating</form:option>
									<form:option value="Fitness">Fitness</form:option>
									<form:option value="LifeStyle">LifeStyle</form:option>
								</form:select>							  
							</div>
						  </div>
						  <div class="form-group">
							<label class="col-xs-3 control-label">Checkpoint 1 ?</label>
							<div class="col-xs-9">
								<form:input type="text" path="checklist1" class="form-control" placeholder="e.g What is your weight before this goal ?" />
							</div>
						  </div>
						  
						  <div class="form-group">
							<label class="col-xs-3 control-label">Checkpoint 2 ?</label>
							<div class="col-xs-9">
								<form:input type="text" path="checklist2" class="form-control" placeholder="e.g What is your weight after this goal ?" />
							</div>
						  </div>
						  
						  <div class="form-group">
							<label class="col-xs-3 control-label">Checkpoint 3 ?</label>
							<div class="col-xs-9">
								<form:input type="text" path="checklist3" class="form-control" placeholder="e.g. What did you do to loose 3lbs ?" />
							</div>
						  </div>
						  
						</form>
					  </div>
					  <div class="panel-footer">
						<small>Create a Goal for the Employee</small>
					  </div>
				</div>
				</form:form>
			</div> <!-- //row -->

	</div>
	<!-- Load the required JavaScript libraries -->
	<script src="<c:url value="/static/resources/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/static/resources/js/jquery-ui.min.js"/>"></script>
	<script src="<c:url value="/static/resources/js/bootcards.min.js"/>"></script>

	<!--modals-->
	<div class="modal fade" id="editModal" tabindex="-1" role="dialog"
		aria-labelledby="editModal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
	</div>
	<div class="modal fade" id="docsModal" tabindex="-1" role="dialog"
		aria-labelledby="docsModal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
	</div>

	<script type="text/javascript">
		bootcards.init({
			offCanvasBackdrop : true,
			offCanvasHideOnMainClick : true,
			enableTabletPortraitMode : true,
			disableRubberBanding : true,
			disableBreakoutSelector : 'a.no-break-out'
		});
	</script>
</body>
</html>
