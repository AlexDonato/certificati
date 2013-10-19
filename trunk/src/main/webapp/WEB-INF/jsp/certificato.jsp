<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="dialog-form" title="Inserimento nuovo certificato">

	<form:form id="formCertificato" method="post" enctype="multipart/form-data" class="form-horizontal" 
						action="new-certificate.html" role="form" commandName="certificato">

		<h5>
		<c:choose>
			<c:when test="${not empty certificato}">
				Dati del certificato
			</c:when>
			<c:otherwise>
				Compilare i dati relativi al certificato da registrare
			</c:otherwise>
		</c:choose>
		
		</h5>
	
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
						<form:input path="data" id="formCertificato-data" class="form-control input-sm"	placeholder="" readonly="true"/>				
					</div>
	
					<div class="col-lg-6">
						<label class="control-label">Fornitore</label>
						<form:select path="fornitore" id="formCertificato-fornitore" class="form-control input-sm"	placeholder="Fornitore">
								<form:option value="NONE" label="--- Seleziona ---"/>
								<form:options items="${fornitoreCombo}" itemValue="id" itemLabel="ragioneSociale" />				    			
						</form:select>
					</div>
					
				</div>
						
			</div>
		</div> <!-- fine panel intestazione certificato -->
				
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
						Elenco materiali ${certificato.materiali}
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
								<option value="Curva" label="Curva">
								<option value="Flangia" label="Flangia">
								<option value="Fondello" label="Fondello">
								<option value="Mezzo manicotto" label="Mezzo manicotto">
								<option value="Riduzione" label="Riduzione">
								<option value="Tubo" label="Tubo">
							</select>
						</td>
						<td>
							<select id="formCertificato-rowMateriale1-unitaMisura" name="formCertificato-rowMateriale1-unitaMisura" class="form-control input-sm">
								<option value="NONE" label="Seleziona ---"/>
								<option value="Numero" label="Numero">
								<option value="Mt" label="Metri">
								<option value="Kg" label="Kilogrammi">
								<option value="Diam." label="Diametro">				    			
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
					<c:forEach items="${certificato.materiali}" var="materiale">
						<c:out value="${materiale.id}"></c:out>
					</c:forEach>
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
		
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
						File del certificato
				</h3>
			</div>

			<div class="panel-body">
	
				<div class="form-group">
					<div class="col-lg-4">
						<label for="formCertificato-fileCertificato">File certificato</label>
						<input id="formCertificato-fileCertificato" type="file" name="formCertificato-fileCertificato">
						<a href="${pageContext.request.contextPath}/download/${certificato.fileName}?id=${certificato.id}" target="_new">SCARICA</a>
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
		
	</form:form>
		
</div> <!-- div principale -->


<script type="text/javascript">
<!--

	var formSaved = "1";

	$(document).ready (function() {

		$("#formCertificato-data").datepicker({ dateFormat: "dd/mm/yyyy" });
		
		<c:set var="isFormSaved" value="${result}" />
		<c:choose>
			<c:when test="${isFormSaved == 0}">
				// se il result è 0, allora ha salvato con successo, quindi disabilito il bottone di save
				$("#salva").attr ("disabled", "disabled");
				$("#statusMessage").removeClass("label-danger");
				$("#statusMessage").html ("Il certificato è stato salvato.");
			</c:when>
			<c:when test="${isFormSaved == 0}">
				// se il result è -1, allora il certificato non è stato salvato !!!
				$("#salva").attr ("disabled", "disabled");
				$("#statusMessage").addClass("label-danger");
				$("#statusMessage").html ("Si è verificato un errorre: il certificato NON è stato salvato.");
			</c:when>
		</c:choose>
					
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

				// Per log				
				var formInputs = $("*[id^='formCertificato-']");
				var inputArray = $.makeArray(formInputs);
				inputArray.forEach (stampaLista);
				// Fine per log
				
				var formOkToBePosted = 1;

				//TODO: verifica dei campi obbligatori
				if ($("#formCertificato-data").val() == "") {
					setFieldAsInvalid ("formCertificato-data","statusMessage");
					formOkToBePosted = 0;
				}
				if ($("#formCertificato-fornitore").val() == "NONE") {
					setFieldAsInvalid ("formCertificato-fornitore","statusMessage");
					formOkToBePosted = 0;
				}

				// Almeno un materiale ...
				if ($("#formCertificato-rowMateriale1-colata").val() == "") {
					setFieldAsInvalid ("formCertificato-rowMateriale1-colata","statusMessage");
					formOkToBePosted = 0;
				}
				if ($("#formCertificato-rowMateriale1-tipoMateriale").val() == "NONE") {
					setFieldAsInvalid ("formCertificato-rowMateriale1-tipoMateriale","statusMessage");
					formOkToBePosted = 0;
				}
				if ($("#formCertificato-rowMateriale1-unitaMisura").val() == "") {
					setFieldAsInvalid ("formCertificato-rowMateriale1-unitaMisura","statusMessage");
					formOkToBePosted = 0;
				}
				if ($("#formCertificato-rowMateriale1-dimensione").val() == "") {
					setFieldAsInvalid ("formCertificato-rowMateriale1-dimensione","statusMessage");
					formOkToBePosted = 0;
				}
				if ($("#formCertificato-rowMateriale1-specifica").val() == "") {
					setFieldAsInvalid ("formCertificato-rowMateriale1-specifica","statusMessage");
					formOkToBePosted = 0;
				}

				// Il file ..
				if ($("#formCertificato-fileCertificato").val() == "") {
					setFieldAsInvalid ("formCertificato-fileCertificato","statusMessage");
					formOkToBePosted = 0;
				}

				// Se non è tutto ok ...
				if (formOkToBePosted == 0) {	
					$("#statusMessage").removeClass("label-success");
					$("#statusMessage").addClass("label-danger");
					$("#statusMessage").html ("Compilare i campi obbligatori");
				}	else {
					// Tutto ok ... salviamo
					$("#formCertificato").submit();
				}
						
		});
		function stampaLista (elem, index) {
			console.log( "Index " + index + ": " + elem.id + ": " + elem.value);
		}	
//-->
</script>