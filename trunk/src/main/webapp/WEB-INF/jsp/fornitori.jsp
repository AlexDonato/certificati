<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
			
<!-- Form modal -->
<div id="dialog-form" title="Inserimento nuovo fornitore">

	<form:form method="post" class="form-horizontal" role="form" commandName="fornitore">
	
	<h5>Compilare i dati relativi al fornitore da registrare</h5>
	
		<div class="panel panel-default">
	  	<div class="panel-heading">
	 			<h3 class="panel-title">Fornitore</h3>
	  	</div>
		  <div class="panel-body">
		  	<fieldset>
		  	
			    <div class="form-group">
		    		<div class="col-lg-12">
		    			<form:input path="ragioneSociale" class="form-control input-sm"	placeholder="Ragione sociale"/>
		    		</div>	    		
			    </div>
			    <div class="form-group">
		    		<div class="col-lg-9">
		    			<form:input path="indirizzo" class="form-control input-sm"	placeholder="Indirizzo"/>
		    		</div>	    		
		    		<div class="col-lg-3">
		    			<form:input path="cap" class="form-control input-sm"	placeholder="Cap"/>
		    		</div>
			    </div>
		
			    <div class="form-group">
		    		<div class="col-lg-9">
		    			<form:input path="citta" class="form-control input-sm"	placeholder="Città"/>
		    		</div>	    		
		    		<div class="col-lg-3">
		    			<form:select path="country" class="form-control input-sm"	placeholder="Paese">
		    				<form:option value="IT">Italia</form:option>
		    			</form:select>
		    		</div>
			    </div>
				
			    <div class="form-group">
		    		<div class="col-lg-6">
		    			<form:input path="telefono" class="form-control input-sm"	placeholder="Telefono"/>
		    		</div>	    		
		    		<div class="col-lg-6">
		    			<form:input path="fax" class="form-control input-sm"	placeholder="Fax"/>
		    		</div>
			    </div>
			    
			    <div class="form-group">
		    		<div class="col-lg-6">
		    			<form:input path="email" class="form-control input-sm"	placeholder="Email"/>
		    		</div>	    		
			    </div>
			    
			    <div class="form-group">
		    		<div class="col-lg-6">
		    			<form:input path="piva" class="form-control input-sm"	placeholder="Partita IVA"/>
		    		</div>
			    </div>
			
				</fieldset>						
		  </div>
		  
		</div>

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

	</form:form>

</div>

<script type="text/javascript">
	var isFormValid = 1;

	$(document).ready (function() {
		
		$("#salva").click (function () {

			var formInputs = $("form#fornitore input,form#fornitore select");
			var inputArray = $.makeArray(formInputs);

			inputArray.forEach (stampaLista);

			if (isFormValid == 0) {
				$("#statusMessage").removeClass("label-success");
				$("#statusMessage").addClass("label-danger");
				$("#statusMessage").html("Compilare i campi obbligatori.");
				isFormValid = 1;
				return;
			} else {
				$("#statusMessage").removeClass("label-danger");
				$("#statusMessage").html("");			
			}		
			
			var posting = $.post ("${pageContext.request.contextPath}/addFornitore.html", $("#fornitore").serialize() );
			
			posting.done (function(msg) {
				var messaggio; 
				if (msg == 0) {
					messaggio = "Dati salvati correttamente";

					$("#salva").attr ("disabled", "disabled");
					
					$("#statusMessage").removeClass("label-danger");
					$("#statusMessage").addClass("label-success");
					formSaved = 1;
					
				} else if (msg == 1) {
					messaggio = "Fornitore gia' presente (cod.err. 0xA1)";
					$("#statusMessage").removeClass("label-success");
					$("#statusMessage").addClass("label-danger");
					
				} else if (msg == 2) {
					messaggio = "Si e' verificato un errore (cod.err. 0xA2)";
					$("#statusMessage").removeClass("label-success");
					$("#statusMessage").addClass("label-danger");
					
				} else {
					messaggio = "Si e' verificato un errore (cod.err. 0xA3)";
					$("#statusMessage").removeClass("label-success");
					$("#statusMessage").addClass("label-danger");
					
				}
				$("#statusMessage").html(messaggio);
				 
			});

		});
			
	});

	function stampaLista (elem, index) {
		console.log( "Index " + index + ": " + elem.id + ": " + elem.value);
		var setError = 1;
		
		if (elem.id == "ragioneSociale" && $("#" + elem.id).val() == "") {
			setError = 0;
		} 

		if (setError == 0) { 
			$("#" + elem.id).addClass("is-error-element");
		} else {
			$("#" + elem.id).removeClass("is-error-element");
		}

		isFormValid &= setError;

		return setError;
	}	
//-->
</script>
