<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="dialog-form" title="Inserimento nuovo certificato">

	<form:form id="certificatoForm" method="post" enctype="multipart/form-data" class="form-horizontal" action="salvaCertificato.html" role="form" commandName="certificato">

		<h5>Compilare i dati relativi al certificato da registrare</h5>
	
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
						Intestazione certificato
				</h3>
			</div>

			<div class="panel-body">
				<div class="form-group">
					<div class="col-lg-3">
						<label class="control-label">Codice</label>
						<form:input path="codice" id="formCertificato-codice" class="form-control input-sm"	placeholder="Codice" disabled="true"/>
						<form:hidden path="codice" id="formCertificato-codice" class="form-control input-sm"	placeholder="Codice"/>
					</div>
									
					<div class="col-lg-3">
						<label class="control-label">Data</label>
						<form:input path="data" id="formCertificato-data" class="form-control input-sm"	placeholder=""/>
					</div>
	
					<div class="col-lg-6">
						<label class="control-label">Fornitore</label>
						<form:select path="fornitore" id="formCertificato-fornitore" class="form-control input-sm"	placeholder="Fornitore">
								<form:option value="NONE" label="--- Seleziona ---"/>
								<form:options items="${fornitoreCombo}"  />				    			
						</form:select>
					</div>
					
				</div>
						
			</div>
		</div> <!-- fine panel intestazione certificato -->
				
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
						Elenco materiali
				</h3>
			</div>
			<div class="panel-body">
				<table id="elencoMaterialiTable" class="table table-stripped">
					<th>Colata</th>
					<th>Tipo</th>
					<th>Unità misura</th>
					<th>Dimensione</th>
					<th>Specifica</th>
					<th>&nbsp;</th>
					<tr id="tableRowMateriale-1">
						<td>
							<input id="formCertificato-rowMateriale1-colata" name="formCertificato-rowMateriale1-colata" type="text" class="form-control input-sm"/>
						</td>
						<td>
							<select id="formCertificato-rowMateriale1-tipoMateriale" name="formCertificato-rowMateriale1-tipoMateriale" class="form-control input-sm">
								<option value="NONE" label="Seleziona ---"/>
								<option value="1" label="Curva">
								<option value="2" label="Flangia">
								<option value="3" label="Fondelli">
								<option value="4" label="Mezzi manicotti">
								<option value="5" label="Riduzione">
								<option value="6" label="Tubo">
							</select>
						</td>
						<td>
							<select id="formCertificato-rowMateriale1-unitaMisura" name="formCertificato-rowMateriale1-unitaMisura" class="form-control input-sm">
								<option value="NONE" label="Seleziona ---"/>
								<option value="1" label="Numero">
								<option value="2" label="Metri">
								<option value="3" label="Kilogrammi">
								<option value="4" label="Diametro">				    			
							</select>
						</td>
						<td>
							<input id="formCertificato-rowMateriale1-dimensione" name="formCertificato-rowMateriale1-dimensione" type="text" class="form-control input-sm" />
						</td>
						<td>
							<input id="formCertificato-rowMateriale1-specifica" name="formCertificato-rowMateriale1-specifica" type="text" class="form-control input-sm"/>
						</td>
						<td>
							<button id="remove-1" type="button" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-ok"></span></button>
						</td>
						
					</tr>
				</table>
				<div class="row">				
					<div class="col-lg-9"></div>
					<div class="col-lg-3">
						<button id="aggiungi_materiale" type="button" class="btn btn-primary btn-block btn-xs">Aggiungi</button>
					</div>
				</div>
			</div>
		</div>
		<!-- fine panel materiali -->

	</form:form>

	<form id="fileCertificatoForm" method="post" enctype="multipart/form-data" class="form-horizontal" action="salvaFileCertificato.html" role="form">
		
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
						File del certificato
				</h3>
			</div>

			<div class="panel-body">
	
				<div class="form-group">
					<div class="col-lg-4">
						<label for="certificatoInputFile">File certificato</label>
						<input id="fileupload" type="file" name="files[]" data-url="salvaFileCertificato.html" multiple >
					</div>
				</div>
					
			</div>
		</div> <!-- fine panel file certificato -->

		<div class="panel panel-default">
		  <div class="panel-body">
		  
		    <div class="row">
	    		<div class="col-lg-6">
						<span id="statusMessage" class="label label-success"></span>
	    		</div>
	    		<div class="col-lg-3">
						<button id="salva" type="button" class="btn btn-primary btn-block btn-xs">Salva</button>
	    		</div>
	    		<div class="col-lg-3">
						<button id="cancella" type="reset" class="btn btn-block btn-xs">Cancella</button>
	    		</div>
		    </div>
		  
		  </div>
		</div>		

		<div class="row">
			<div class="col-lg-12">
				&nbsp;
			</div>
		</div>
		
	</form>
		
</div> <!-- div principale -->


<script type="text/javascript">
<!--

	var formOkToBePosted = 1;
	var formSaved = 0;

	$(window).bind('beforeunload', function(){
		  return 'I dati non sono ancora stati salvati.';
	});
	
	$(document).ready (function() {
/* 		// Preparazione della form per upload del file
		var options = {
				beforeSend: function () {alert ("Sta per partire l'upload");},
				uploadProgress: function () {},
				success: function () {alert ("Tutto ok!");},
				error: function () {alert ("Si è verificato un errore"); },
				complete: function (response) {alert ("response: " + response);}
		};

		$("#fileCertificatoForm").ajaxForm(options);
 */
 		
		$("#aggiungi_materiale").click (
				function () {
					var matchPattern = "#elencoMaterialiTable *[id^='tableRowMateriale']";
					var numeroRighe = $(matchPattern).length;
					//alert ('righe ' + numeroRighe);
					var nextId = numeroRighe + 1;
					var newRow = $("<tr id=\"tableRowMateriale-" + nextId + "\">");
					var cols = "";
					cols = "<td><input id=\"formCertificato-rowMateriale" + nextId + "-colata\" name=\"formCertificato-rowMateriale" + nextId + "-colata\"type=\"text\" class=\"form-control input-sm\"/></td>";

					cols += "<td><select id=\"formCertificato-rowMateriale1" + nextId + "-tipoMateriale\" name=\"formCertificato-rowMateriale" + nextId + "-tipoMateriale\" class=\"form-control input-sm\">";
					cols += "<option value=\"NONE\" label=\"--- Seleziona ---\"/>";
					cols += "<option value=\"1\" label=\"Curva\">";
					cols += "<option value=\"2\" label=\"Flangia\">";
					cols += "<option value=\"3\" label=\"Fondelli\">";
					cols += "<option value=\"4\" label=\"Mezzi manicotti\">";
					cols += "<option value=\"5\" label=\"Riduzione\">";
					cols += "<option value=\"6\" label=\"Tubo\">";
					cols += "</select></td>";
					
					cols += "<td><select id=\"formCertificato-rowMateriale" + nextId + "-unitaMisura\" name=\"formCertificato-rowMateriale" + nextId + "-unitaMisura\" class=\"form-control input-sm\">";
					cols += "<option value=\"NONE\" label=\"--- Seleziona ---\"/>";
					cols += "<option value=\"1\" label=\"Numero\">";
					cols += "<option value=\"2\" label=\"Metri\">";
					cols += "<option value=\"3\" label=\"Kilogrammi\">";				    			
					cols += "</select></td>";
					cols += "<td><input id=\"formCertificato-rowMateriale" + nextId + "-dimensione\" name=\"formCertificato-rowMateriale" + nextId + "-dimensione\" type=\"text\" class=\"form-control input-sm\"/></td>";
					cols += "<td><input id=\"formCertificato-rowMateriale" + nextId + "-specifica\" name=\"formCertificato-rowMateriale" + nextId + "-specifica\" type=\"text\" class=\"form-control input-sm\"/></td>";
					cols += "<td><button id=\"remove-" + nextId + "\" type=\"button\" class=\"btn btn-default btn-sm\"><span class=\"glyphicon glyphicon-remove\"></span></button></td>";
					cols += "</tr>"; 
						
					newRow.append(cols);
					$("#elencoMaterialiTable").append(newRow);

					//alert ($(matchPattern).length);										

					$("#remove-" + nextId).click (
							function (netxId) {
								var okDelete = confirm ("Rimuovere la riga " + nextId + "?");
								if (okDelete == true)
									$("#tableRowMateriale-" + nextId).remove();
							});
					});
					
				});
		$("#salva").click (
			function () {

				if (formOkToBePosted == 0) {
					alert ('ciccia');
					return;
				}				
				var formInputs = $("*[id^='formCertificato-']");
				var inputArray = $.makeArray(formInputs);
				var lista = "";

				inputArray.forEach (stampaLista);		
				//alert ("salva " + inputArray);
				
				var posting = $.post ("${pageContext.request.contextPath}/salvaCertificato.html", $("#certificatoForm").serialize() );
				
				posting.done (function(msg) {
					var messaggio; 
					if (msg == 0) {

						alert ('dati base salvati');
						messaggio = "Dati salvati correttamente";

						// upload del file
						$("#fileupload").fileupload ({
									dataType: 'json',
									done: function (e, data) {
										alert ('finito upload');
									}
						});

						$("#salva").attr ("disabled", "disabled");
						
						$("#statusMessage").removeClass("label-danger");
						$("#statusMessage").addClass("label-success");
						formSaved = 1;
						
					} else if (msg == 1) {
						messaggio = "Certificato gia' presente (cod.err. 0x01)";
						$("#statusMessage").removeClass("label-success");
						$("#statusMessage").addClass("label-danger");
						
					} else if (msg == 2) {
						messaggio = "Si e' verificato un errore (cod.err. 0x02)";
						$("#statusMessage").removeClass("label-success");
						$("#statusMessage").addClass("label-danger");
						
					} else {
						messaggio = "Si e' verificato un errore (cod.err. 0x03)";
						$("#statusMessage").removeClass("label-success");
						$("#statusMessage").addClass("label-danger");
						
					}
					$("#statusMessage").html(messaggio);
					 
				});
				
		});
		function stampaLista (elem, index) {
			console.log( "Index " + index + ": " + elem.id + ": " + elem.value);
		}	
//-->
</script>