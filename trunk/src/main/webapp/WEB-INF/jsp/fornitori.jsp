<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
	$(function() {
		
		

		
		$( "#button" ).button();
	});
	</script>
			
<!-- Form modal -->
<div id="dialog-form" title="Inserimento nuovo fornitore">
	<form:form method="post" action="${pageContext.request.contextPath}/addFornitore.html">
		<table>
	    <tr>
	        <td colspan="2">Inserire i dati relativi al fornitore.</td>
	    </tr>
	    <tr>
	        <td><form:label path="ragioneSociale" class="ui-widget input">Ragione sociale</form:label></td>
	        <td><form:input path="ragioneSociale" class="ui-widget input"/></td>
	    </tr>
	    <tr>
	        <td><form:label path="telefono" class="ui-widget input">Telefono</form:label></td>
	        <td><form:input path="telefono" class="ui-widget input"/></td>
	    </tr>
	    <tr>
	        <td><form:label path="piva" class="ui-widget input">P.IVA</form:label></td>
	        <td><form:input path="piva" class="ui-widget input"/></td>
	    </tr>
	    <tr>
	        <td id="dialog-message" colspan="2">test</td>
	        <td><button id="button" onclick="">Salva</button></td>
	    </tr>
	    
		</table>
	</form:form>
	
</div>