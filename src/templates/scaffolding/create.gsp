<%=packageName%>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
    <title><g:message code="default.create.label" args="[entityName]" /></title>
    <r:require module="application" />
</head>
<body>
<content tag="breadcrumbs">
    <ul class="breadcrumb">
        <li><a class="home" href="\${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
    </ul>
</content>
<content tag="banner">
    <g:message code="default.create.label" args="[entityName]" />
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
    <g:form class="form-horizontal" role="form" action="save" <%= multiPart ? ' enctype="multipart/form-data"' : '' %>>
    <g:render template="form"/>
    <div class="form-group">
        <div class="col-lg-push-2 col-md-push-2 col-lg-2 col-md-2">
            <g:submitButton name="create" class="btn btn-primary btn-sm" value="\${message(code: 'default.button.create.label', default: 'Create')}" />
            <g:link class="btn btn-default btn-sm" action="list"><g:message code="default.button.cancel.label" default="Cancel" /></g:link>
        </div>
    </div>
    </g:form>
</content>
</div>
</body>
</html>