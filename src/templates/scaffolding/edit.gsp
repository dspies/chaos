<%=packageName%>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
    <title><g:message code="default.edit.label" args="[entityName]" /></title>
    <r:require module="application" />
</head>
<body>
<content tag="breadcrumbs">
    <ul class="breadcrumb">
        <li><a class="home" href="\${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
</content>
<content tag="banner">
    <g:message code="default.edit.label" args="[entityName]" />
</content>
<content tag="message">
    <div class="message" role="status">\${flash.message}</div>
</content>
<content tag="errors">
    <g:hasErrors bean="\${${propertyName}}">
        <ul class="errors" role="alert">
            <g:eachError bean="\${${propertyName}}" var="error">
                <li <g:if test="\${error in org.springframework.validation.FieldError}">data-field-id="\${error.field}"</g:if>><g:message error="\${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
</content>
<content tag="detail">
    <g:form class="form-horizontal" role="form" method="post" <%= multiPart ? ' enctype="multipart/form-data"' : '' %>>
    <g:hiddenField name="id" value="\${${propertyName}?.id}" />
    <g:hiddenField name="version" value="\${${propertyName}?.version}" />
    <g:render template="form"/>
    <div class="form-group">
        <div class="col-lg-push-2 col-md-push-2 col-lg-2 col-md-2">
            <g:actionSubmit class="btn btn-primary btn-sm" action="update" value="\${message(code: 'default.button.update.label', default: 'Update')}" />
            <g:actionSubmit class="btn btn-danger btn-sm" action="delete" value="\${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('\${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
            <g:link class="btn btn-default btn-sm" action="list"><g:message code="default.button.cancel.label" default="Cancel" /></g:link>
        </div>
    </div>
    </g:form>
</content>
</body>
</html>