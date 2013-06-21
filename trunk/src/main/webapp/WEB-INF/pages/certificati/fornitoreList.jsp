<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="userList.title"/></title>
    <meta name="menu" content="CertificatiMenu"/>
</head>

<c:if test="${not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="${searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="fornitoriList.heading"/></h2>

    <form method="get" action="${ctx}/certificati/fornitore" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <div id="actions" class="form-actions">
        <a class="btn btn-primary" href="<c:url value='/fornitoreform?method=Add&from=list'/>">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>

        <a class="btn" href="<c:url value='/mainMenu'/>">
            <i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

    <display:table name="fornitoreList" cellspacing="0" cellpadding="0" requestURI=""
                   defaultsort="1" id="fornitori" pagesize="25" class="table table-condensed table-striped table-hover" export="true">
        
        <display:column property="ragioneSociale" escapeXml="true" sortable="true" titleKey="fornitore.ragioneSociale" style="width: 30%"
                        url="/fornitoreform?from=list" paramId="id" paramProperty="id"/>
				
				<display:column property="indirizzo" escapeXml="true" titleKey="fornitore.indirizzo" style="width: 30%"
                        url="/fornitoreform?from=list" paramId="id" paramProperty="id"/>         

				<display:column property="cap" escapeXml="true" titleKey="fornitore.cap" style="width: 5%"
                        url="/fornitoreform?from=list" paramId="id" paramProperty="id"/>         

				<display:column property="citta" escapeXml="true" sortable="true" titleKey="fornitore.citta" style="width: 25%"
                        url="/fornitoreform?from=list" paramId="id" paramProperty="id"/>         

        <display:column property="telefono" escapeXml="true" sortable="true" titleKey="fornitore.telefono" style="width: 10%"
                        url="/fornitoreform?from=list" paramId="id" paramProperty="id"/>
                     
        <display:setProperty name="export.excel.filename" value="User List.xls"/>
        <display:setProperty name="export.csv.filename" value="User List.csv"/>
        <display:setProperty name="export.pdf.filename" value="User List.pdf"/>
    </display:table>
</div>
