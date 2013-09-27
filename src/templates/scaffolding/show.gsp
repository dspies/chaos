<% import grails.persistence.Event %>
<%=packageName%>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
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
    <g:message code="default.show.label" args="[entityName]" />
</content>
<content tag="message">
    <div class="message" role="status">\${flash.message}</div>
</content>
<content tag="detail">

    <g:form class="form-horizontal property-list ${domainClass.propertyName}" role="form">
        <%  excludedProps = Event.allEvents.toList() << 'id' << 'version'
        allowedNames = domainClass.persistentProperties*.name << 'dateCreated' << 'lastUpdated'
        props = domainClass.properties.findAll { allowedNames.contains(it.name) && !excludedProps.contains(it.name) }
        Collections.sort(props, comparator.constructors[0].newInstance([domainClass] as Object[]))
        props.each { p -> %>
        <g:if test="\${${propertyName}?.${p.name}}">
            <div class="form-group">
                <label class="col-lg-2 control-label"><g:message code="${domainClass.propertyName}.${p.name}.label" default="${p.naturalName}" /></label>
                <div class="col-lg-10">
                    <p class="form-control-static">
                        <%  if (p.isEnum()) { %>
                        <g:fieldValue bean="\${${propertyName}}" field="${p.name}"/>
                        <%  } else if (p.oneToMany || p.manyToMany) { %>
                        <g:each in="\${${propertyName}.${p.name}}" var="${p.name[0]}">
                            <g:link controller="${p.referencedDomainClass?.propertyName}" action="show" id="\${${p.name[0]}.id}">\${${p.name[0]}?.encodeAsHTML()}</g:link>
                        </g:each>
                        <%  } else if (p.manyToOne || p.oneToOne) { %>
                        <g:link controller="${p.referencedDomainClass?.propertyName}" action="show" id="\${${propertyName}?.${p.name}?.id}">\${${propertyName}?.${p.name}?.encodeAsHTML()}</g:link>
                        <%  } else if (p.type == Boolean || p.type == boolean) { %>
                        <g:formatBoolean boolean="\${${propertyName}?.${p.name}}" />
                        <%  } else if (p.type == Date || p.type == java.sql.Date || p.type == java.sql.Time || p.type == Calendar) { %>
                        <g:formatDate date="\${${propertyName}?.${p.name}}" />
                        <%  } else if (!p.type.isArray()) { %>
                        <g:fieldValue bean="\${${propertyName}}" field="${p.name}"/>
                        <%  } %>
                    </p>
                </div>
            </div>
        </g:if>
        <%  } %>
        <div class="form-group">
            <div class="col-lg-push-2 col-md-push-2 col-lg-2 col-md-2">
                <g:hiddenField name="id" value="\${${propertyName}?.id}" />
                <g:link class="btn btn-primary btn-sm" action="edit" id="\${${propertyName}?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                <g:actionSubmit class="btn btn-danger btn-sm" action="delete" value="\${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('\${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                <g:link class="btn btn-default btn-sm" action="list"><g:message code="default.button.cancel.label" default="Cancel" /></g:link>
            </div>
        </div>
    </g:form>
</content>
</body>
</html>