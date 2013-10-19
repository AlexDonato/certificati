<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="dialog-form" title="Inserimento nuovo fornitore">

	<form action="" method="post">

		<div class="panel panel-default">
 			<div class="panel-heading">
					<h3 class="panel-title">Nuovo documento</h3>
 			</div>

		  <div class="panel-body">
	  	
				<div class="col-lg-3">
					<div class="form-group">
						<label for="formRicercaCertificato-codice" class="col-lg-3 control-label">Codice</label>
						<input id="formRicercaCertificato-codice" class="form-control input-sm" placeholder=""/>
					</div>
				</div>
										
				<div class="col-lg-3">
					<div class="form-group">
						<label for="formRicercaCertificato-data" class="col-lg-3 control-label">Data</label>
						<input id="formRicercaCertificato-data" class="form-control input-sm"	placeholder=""/>
					</div>
				</div>
		
				<div class="col-lg-6">
					<div class="form-group">
						<label for="formRicercaCertificato-fornitore" class="col-lg-3 control-label">Fornitore</label>
						<select path="fornitore" id="formRicercaCertificato-fornitore" class="form-control input-sm"	placeholder="Fornitore">
							<option value="NONE" label="Seleziona Fornitore"/>
							<c:forEach items="${fornitoreCombo}" var="fornitore">
								<option value="${fornitore}"/>
							</c:forEach>
						</select>
					</div>
		    </div>

				<!-- Inizio riga 2 -->
<!-- 	    	
				<div class="col-lg-3">
	    		<div class="form-group">
						<label for="formRicercaCertificato-colata" class="col-lg-3 control-label">Colata</label>
						<input id="formRicercaCertificato-colata" class="form-control input-sm"	placeholder=""/>
	    		</div>
	    	</div>
		    <div class="col-lg-9">
	    		<div class="form-group">
						&nbsp;
	    		</div>
	    	</div>
 -->				
 				<!-- Fine riga 2 -->

				<!-- Inizio riga 3 -->		    
		    <div class="col-lg-9">
	    		<div class="form-group">
						<span id="statusMessage" class="label label-success"></span>
	    		</div>
	    	</div>
	    	<div class="col-lg-3">
	    		<div class="form-group">
						<button id="cerca" type="button" class="btn btn-primary btn-block btn-xs">Cerca</button>
	    		</div>
	    	</div>
	    	<!-- fine riga 3 -->
			</div>
		</div>
	</form>
	
	<!-- Risultati -->	
	<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Ricerca certificati</h3>
			</div>

	  <div class="panel-body">
		</div>	
	</div>
	<!-- Fine risultati -->
				
</div>

	