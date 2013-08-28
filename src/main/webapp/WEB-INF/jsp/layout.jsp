<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../assets/ico/favicon.png">

		<title>
			<tiles:insertAttribute name="title" ignore="true" />
		</title>

		<!-- Bootstrap core CSS -->
		<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
		<link href="${pageContext.request.contextPath}/css/elledia.css" rel="stylesheet">

		<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.js"></script>
		
		<!-- Form Validator http://jqueryvalidation.org-->
		<script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
		<script src="${pageContext.request.contextPath}/js/additional-methods.js"></script>

		<%-- <script src="${pageContext.request.contextPath}/js/vendor/jquery.ui.widget.js"></script> --%>
		<script src="${pageContext.request.contextPath}/js/jquery.iframe-transport.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.fileupload.js"></script>
		<script src="${pageContext.request.contextPath}/js/vendor/jquery.ui.widget.js"></script>


		<!-- Form plugin -->
		<script src="${pageContext.request.contextPath}/js/jquery.form.js"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="../../assets/js/html5shiv.js"></script>
      <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <!-- Wrap all page content here -->
    <div id="wrap">
	    <div class="navbar navbar-inverse navbar-fixed-top">
	    	<tiles:insertAttribute name="menu" />
	    </div>
	
	    <div class="container">
	      <!-- Example row of columns -->
	      <div class="row">
	        <div class="col-lg-2">
						Help tbd<!--  -->
					</div>
	        <div class="col-lg-8">
						<tiles:insertAttribute name="body" />
					</div>
	      </div>
	      
	    </div> <!-- /container -->

		</div>
		
		<div id="footer">
      <div class="container">
        <tiles:insertAttribute name="footer"/>
      </div>
    </div>
    
 		<!-- Bootstrap core JavaScript
		================================================== -->	
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

		<div id="mixpanel" style="visibility: hidden;"></div>
		
  </body>
</html>
