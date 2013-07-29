<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form method="post" action="insertCertificato.html">

	<table>
		<tr>
			<td><form:label path="numero" class="ui-widget input">Numero certificato fornitore</form:label></td>
			<td><form:input path="numero" class="ui-widget input"/></td>
			<td>&nbsp;</td>
			<td><form:label path="data" class="ui-widget input">Data certificato fornitore</form:label></td>
			<td><form:input path="data" id="dataCertificato" class="ui-widget input"/></td>
		</tr>
		<tr>
			<td><form:label path="fornitore" class="ui-widget input">Fornitore</form:label></td>
			<td><form:input path="fornitore" class="ui-widget input"/></td>
			<td>&nbsp;</td>
			<td><form:label path="numero" class="ui-widget input">Numero certificato fornitore</form:label></td>
			<td>file</td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="Salva" /></td>
		</tr>
	</table>

</form:form>



<table id="certificatiFlex" style="display: visible"></table>

<script>
$(function() {
	$( "#dataCertificato" ).datepicker({ dateFormat: "dd MM yy" });
});

</script>

<script type="text/javascript">
	$("#certificatiFlex").flexigrid({
		url : '${pageContext.request.contextPath}/test.json',
		dataType : 'json',
		colModel : [ {
			display : 'Codice',
			name : 'codice',
			width : 40,
			sortable : true,
			align : 'center'
		}, {
			display : 'Nome',
			name : 'nome',
			width : 180,
			sortable : true,
			align : 'left'
		}],
		buttons : [ {
			name : 'Aggiungi materiale',
			bclass : 'add',
			onpress : test
		}, {
			name : 'Cancella materiale',
			bclass : 'delete',
			onpress : test
		}, {
			separator : true
		} ],
		searchitems : [ {
			display : 'Codice',
			name : 'codice'
		}, {
			display : 'Nome',
			name : 'nome',
			isdefault : true
		} ],
		sortname : "codice",
		sortorder : "asc",
		usepager : true,
		title : 'Elenco materiali',
		useRp : true,
		rp : 15,
		showTableToggleBtn : true,
		width : 500,
		height : 250
	});
	function test(com, grid) {
		if (com == 'Delete') {
			confirm('Delete ' + $('.trSelected', grid).length + ' items?')
		} else if (com == 'Aggiungi materiale') {
			alert('Add New Item');
		}
	}
</script>
