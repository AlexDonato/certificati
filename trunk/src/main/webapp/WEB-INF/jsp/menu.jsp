<div class="container">
   <div class="navbar-header">
     <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
       <span class="icon-bar"></span>
       <span class="icon-bar"></span>
       <span class="icon-bar"></span>
     </button>
     <a class="navbar-brand" href="#">
     	<tiles:insertAttribute name="header" />
		</a>
   </div>
   <div class="navbar-collapse collapse">
     <ul class="nav navbar-nav">
			<li><a href="${pageContext.request.contextPath}/home.html"><span class="glyphicon glyphicon-home"></span></a></li>
				<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Certificati</a>
				<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
			    <li><a tabindex="-1" href="${pageContext.request.contextPath}/newCertificato.html">Nuovo</a></li>
				    <li><a tabindex="-1" href="#">Ricerca</a></li>
				    <li><a tabindex="-1" href="#">Something else here</a></li>
				    <li class="divider"></li>
				    <li><a tabindex="-1" href="#">Separated link</a></li>
				</ul>    
		  </li>
			<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Fornitori</a>
				<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
				    <li><a tabindex="-1" href="${pageContext.request.contextPath}/addFornitore.html">Nuovo</a></li>
				    <li><a tabindex="-1" href="#">Ricerca</a></li>
				    <li><a tabindex="-1" href="#">Something else here</a></li>
				    <li class="divider"></li>
				    <li><a tabindex="-1" href="#">Separated link</a></li>
				</ul>    
		  </li>		    
			<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Documenti</a>
				<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
			    <li><a tabindex="-1" href="${pageContext.request.contextPath}/addDocumento.html">Nuovo</a></li>
					<li><a tabindex="-1" href="${pageContext.request.contextPath}/findDocumento.html">Ricerca</a></li>
			    <li class="divider"></li>
			    <li><a tabindex="-1" href="#">Separated link</a></li>
				</ul>    
			</li>		    
		</ul>
    <form class="navbar-form navbar-right">
			<div class="form-group">
				<input type="text" placeholder="Email" class="form-control input-sm">
       </div>
       <div class="form-group">
         <input type="password" placeholder="Password" class="form-control input-sm">
       </div>
       <button type="submit" class="btn btn-success">Sign in</button>
     </form>
   </div><!--/.navbar-collapse -->
</div>
