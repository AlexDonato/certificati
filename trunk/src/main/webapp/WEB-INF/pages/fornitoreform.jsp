<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="userProfile.title"/></title>
    <meta name="menu" content="CertificatiMenu"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="fornitoreList.fornitore"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="userProfile.heading"/></h2>
    <c:choose>
        <c:when test="${param.from == 'list'}">
            <p><fmt:message key="userProfile.admin.message"/></p>
        </c:when>
        <c:otherwise>
            <p><fmt:message key="userProfile.message"/></p>
        </c:otherwise>
    </c:choose>
</div>
<div class="span7">
    <spring:bind path="fornitore.*">
        <c:if test="${not empty status.errorMessages}">
            <div class="alert alert-error fade in">
                <a href="#" data-dismiss="alert" class="close">&times;</a>
                <c:forEach var="error" items="${status.errorMessages}">
                    <c:out value="${error}" escapeXml="false"/><br/>
                </c:forEach>
            </div>
        </c:if>
    </spring:bind>

    <form:form commandName="fornitore" method="post" action="fornitoreform" id="fornitoreForm" autocomplete="off"
               cssClass="well form-horizontal" onsubmit="return true;">
               
        <form:hidden path="id"/>
        <input type="hidden" name="from" value="<c:out value="${param.from}"/>"/>


        <!-- DA SISTEMARE IL DETECT DEGLI ERRORI  -->
        <spring:bind path="fornitore.ragioneSociale">
        	<fieldset class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
        </spring:bind>
            <appfuse:label styleClass="control-label" key="fornitore.ragioneSociale"/>
            <div class="controls">
                <form:input path="ragioneSociale" id="ragioneSociale"/>
                <form:errors path="ragioneSociale" cssClass="help-inline"/>
            </div>
        </fieldset>    
           
        <!-- DA SISTEMARE IL DETECT DEGLI ERRORI  -->
        <spring:bind path="fornitore.indirizzo">
        	<fieldset class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
        </spring:bind>
            <appfuse:label styleClass="control-label" key="fornitore.indirizzo"/>
            <div class="controls">
                <form:input path="indirizzo" id="indirizzo"/>
                <form:errors path="indirizzo" cssClass="help-inline"/>
            </div>
        </fieldset>            

				<fieldset class="control-group">
					<appfuse:label styleClass="control-label" key="fornitore.cap" />
					<div class="controls">
						<form:input path="cap" id="cap" />
					</div>
				</fieldset>        
	
	        <!-- DA SISTEMARE IL DETECT DEGLI ERRORI  -->
        <spring:bind path="fornitore.citta">
        	<fieldset class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
        </spring:bind>
            <appfuse:label styleClass="control-label" key="fornitore.citta"/>
            <div class="controls">
                <form:input path="citta" id="citta"/>
                <form:errors path="citta" cssClass="help-inline"/>
            </div>
        </fieldset>			
  
				<!-- DA SISTEMARE IL DETECT DEGLI ERRORI  -->
        <spring:bind path="fornitore.provincia">
        	<fieldset class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
        </spring:bind>
            <appfuse:label styleClass="control-label" key="fornitore.provincia"/>
            <div class="controls">
                <form:input path="provincia" id="provincia"/>
                <form:errors path="provincia" cssClass="help-inline"/>
            </div>
        </fieldset>	
        
        <form:hidden path="paese" id="paese" value="IT"/>
        
        <fieldset class="control-group">
					<appfuse:label styleClass="control-label" key="fornitore.telefono" />
					<div class="controls">
						<form:input path="telefono" id="telefono" />
					</div>
				</fieldset>
				
        <fieldset class="control-group">
					<appfuse:label styleClass="control-label" key="fornitore.piva" />
					<div class="controls">
						<form:input path="piva" id="piva" />
					</div>
				</fieldset>				      

        <fieldset class="form-actions">
            <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </button>

            <c:if test="${param.from == 'list' and param.method != 'Add'}">
              <button type="submit" class="btn" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                  <i class="icon-trash"></i> <fmt:message key="button.delete"/>
              </button>
            </c:if>

            <button type="submit" class="btn" name="cancel" onclick="bCancel=true">
                <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
            </button>
        </fieldset>
    </form:form>
</div>


