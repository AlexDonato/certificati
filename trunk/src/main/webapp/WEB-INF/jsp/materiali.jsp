<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- Form modal -->
<div id="dialog-form" title="Inserimento nuovo fornitore">
	<form:form method="post" action="${pageContext.request.contextPath}/addFornitore.html">
		<table>
	    <tr>
	        <td colspan="2">Inserire i dati relativi al fornitore.</td>
	    </tr>
	    <tr>
	        <td><form:label path="nome" class="ui-widget input">Ragione sociale</form:label></td>
	        <td><form:input path="nome" class="ui-widget input"/></td>
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
	    </tr>
	    
		</table>
	</form:form>
	
</div>

<table id="fornitoriFlex" style="display: none"></table>


<script type="text/javascript">
$(function() {

	$( "#dialog-form" ).dialog({
		autoOpen: false,
		height: 200,
		width: 350,
		modal: true,
		buttons: {
			"Inserisci nuovo fornitore": function() {
				var bValid = true;
				//allFields.removeClass( "ui-state-error" );
				
				if ( bValid ) {
					saveFornitore();
				}
			},
			Cancel: function() {
				$( this ).dialog( "close" );
			}
		},
		close: function() {
		}
	});

});


$("#fornitoriFlex").flexigrid({
	url : '${pageContext.request.contextPath}/json/listaFornitori.html',
	dataType : 'json',
	method   : 'GET',
	colModel : [ {
		display : 'ID',
		name : 'id',
		width : 40,
		sortable : false,
		align : 'center'
	}, {
		display : 'Ragione sociale',
		name : 'nome',
		width : 380,
		sortable : true,
		align : 'left'
	}, {
		display : 'Telefono',
		name : 'telefono',
		width : 150,
		sortable : false,
		align : 'right'
	}, {
		display : 'P.IVA',
		name : 'piva',
		width : 200,
		sortable : false,
		align : 'right'
	}],
	buttons : [{
		name : 'Aggiungi fornitore',
		bclass : 'add',
		onpress : test
	}, {
		name : 'Cancella fornitore',
		bclass : 'delete',
		onpress : test
	}, {
		separator : true
	} ],
	searchitems : [ {
		display : 'ID',
		name : 'id'
	}, {
		display : 'Nome',
		name : 'nome',
		isdefault : true
	} ],
	sortname : "nome",
	sortorder : "asc",
	usepager : true,
	title : 'Elenco fornitori',
	useRp : true,
	rp : 10,
	showTableToggleBtn : true,
	width : 500,
	height : 250
});

function test(com, grid) {
	if (com == 'Cancella fornitore') {
		confirm('Eliminare il fornitore ' + $('.trSelected', grid).length + ' ?')
	} else if (com == 'Aggiungi fornitore') {
		$( "#dialog-form" ).dialog( "open" );
	}
}

$(document).ready (function (){
	// da usare se vuoi ...
});

function saveFornitore() {
 $(function() {
  var jqxhr = $.post("${pageContext.request.contextPath}/addFornitore.html",
     			{	nome:  $("#nome").val(),
        		telefono:  $("#telefono").val(),
        		piva: $("#piva").val()	},
      			function (data){
			       	// data contains the result
			       	// Assign result to the sum id
			       	$( "#dialog-message" ).text (data);
			       	$( "#dialog-message" ).show();
			    });
  jqxhr.fail ('Errore');
 });
}

</script>