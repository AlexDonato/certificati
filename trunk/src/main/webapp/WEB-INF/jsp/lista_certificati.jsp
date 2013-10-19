<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="dialog-form" title="Lista certificati">

	<form action="" method="post">

		<div class="panel panel-default">
 			<div class="panel-heading">
					<h3 class="panel-title">Ricerca certificati</h3>
 			</div>

		  <div class="panel-body">
				
				<!-- Inizio riga 1 -->
	  		<div class="row">
					<div class="col-lg-2">
						<div class="form-group">
							<label for="formRicercaCertificato-codice" class="col-lg-3 control-label">Codice</label>
							<input id="formRicercaCertificato-codice" class="form-control input-sm" placeholder=""/>
						</div>
					</div>
											
					<div class="col-lg-2">
						<div class="form-group">
							<label for="formRicercaCertificato-data-from" class="col-lg-3 control-label"><nobr>Da data</nobr></label>
							<input id="formRicercaCertificato-data-from" class="form-control input-sm"	placeholder="" readonly/>
						</div>
					</div>
					
					<div class="col-lg-2">
						<div class="form-group">
							<label for="formRicercaCertificato-data-to" class="col-lg-3 control-label"><nobr>A data</nobr></label>
							<input id="formRicercaCertificato-data-to" class="form-control input-sm"	placeholder="" readonly/>							
						</div>
					</div>
					
					<div class="col-lg-6">
						<div class="form-group">
							<label for="formRicercaCertificato-fornitore" class="col-lg-3 control-label">Fornitore</label>
							<form:select 
							path="fornitore" id="formRicercaCertificato-fornitore" class="form-control input-sm"	placeholder="Fornitore">
								<form:option value="-1" label="Seleziona Fornitore"/>
								<form:options items="${fornitoreCombo}" itemValue="id" itemLabel="ragioneSociale"/>
							</form:select>
						</div>
			    </div>
				</div>
				<!-- Fine riga 1 -->
				
				<!-- Inizio riga 2 -->
				<div class="row">
					<div class="col-lg-2">
		    		<div class="form-group">
							<label for="formRicercaCertificato-colata" class="col-lg-3 control-label">Colata</label>
							<input id="formRicercaCertificato-colata" class="form-control input-sm"	placeholder=""/>
		    		</div>
		    	</div>
			    <div class="col-lg-10">
		    		<div class="form-group">
							&nbsp;
		    		</div>
		    	</div>
		    </div>
	 			<!-- Fine riga 2 -->

				<!-- Inizio riga 3 -->
				<div class="row">		    
			    <div class="col-lg-6">
		    		<div class="form-group">
							<span id="statusMessage" class="label label-success"></span>
		    		</div>
		    	</div>
			    <div class="col-lg-3">
		    		<div class="form-group">
							<input type="reset" id="rest" type="button" class="btn btn-default btn-block btn-xs" value="Reset">
		    		</div>
		    	</div>
		    	<div class="col-lg-3">
		    		<div class="form-group">
							<button id="cerca" type="button" class="btn btn-primary btn-block btn-xs">Cerca</button>
		    		</div>
		    	</div>
		    </div>
		    <!-- Fine riga 3 -->
		    
			</div>
		</div>
	</form>
	
	<!-- Risultati -->	
	<div id="panelListaRisultati" class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">Lista risultati</h3>
			</div>
	  <div class="panel-body">
	  	<table class="table">
	  		<thead>
	  			<tr>
	  				<th>Codice</th>
	  				<th>Data</th>
	  				<th>Fornitore</th>
	  				<th>-</th>
	  			</tr>
	  		</thead>
	  		<tbody id="elencoCertificati">
	  		</tbody>
	  	</table>
		</div>	
	</div>
	<!-- Fine risultati -->
				
</div>

<script language="javascript">
	$(document).ready ( function () {

		$("#panelListaRisultati").hide();

		$("#formRicercaCertificato-data-from").datepicker({ format: "dd/mm/yyyy" });
		$("#formRicercaCertificato-data-to").datepicker({ format: "dd/mm/yyyy" });
		
		$("#cerca").click (function () {
			$.ajax ({
					type: "post",
					url: "list-certificates.html",
					cache: false,
					data: 'codice=' + $("#formRicercaCertificato-codice").val() + 
								'&colata=' + $("#formRicercaCertificato-colata").val() +
								'&fornitore=' + $("#formRicercaCertificato-fornitore").val() +
								'&dataFrom=' + $("#formRicercaCertificato-data-from").val() +
								'&dataTo=' + $("#formRicercaCertificato-data-to").val(),
					success: function (response) {
						var listaCertificati = JSON.parse (response);
						var idx = listaCertificati.length;
						console.log ("trovati n." + idx +  " certificati.");
						var objKeys;
						if (idx > 0) { 
							objKeys = Object.keys(listaCertificati[0]);
							console.log ("lista campi: " + objKeys);
						}
						var elencoCertificati = "";
						for (var i = 0 ; i< idx ; i++) {
							var certificato = listaCertificati[i];
							
/* 							for (var j = 0 ; j < objKeys.length ; j++) {
								var key = objKeys[j];
								alert ("property " + key);
								alert ("value " + certificato[key]);
							}
 */
 							var fornitore = certificato['fornitore'];
							var rigaCertificato = "<tr id=\""+ certificato['id'] +"\">";
							rigaCertificato += "<td>" + certificato['codice'] + "</td>";			  				
							rigaCertificato += "<td>" + certificato['data'] + "</td>";
							rigaCertificato += "<td>" + fornitore['ragioneSociale'] + "</td>";
							rigaCertificato += "<td><button class=\"edit-certificato\" id=\""+ certificato['id'] +"\">" + 
																	"<span class=\"glyphicon glyphicon-pencil\"></span></button></td>";
							
		  				rigaCertificato += "</tr>";
							console.log (rigaCertificato);
		  				elencoCertificati += rigaCertificato;
						}

						$("#elencoCertificati").html(elencoCertificati);
						$("#panelListaRisultati").show();
						$(".edit-certificato").click(function (id) {
							console.log ("edit certificato " + $(this).attr("id"));
							$(window.location).attr('href', '${pageContext.request.contextPath}/load-certificate.html?id=' + $(this).attr("id"));
						});					
					},
					error: function () {
						alert ("errore");
					}
				});
		});
	});
	
</script>

	