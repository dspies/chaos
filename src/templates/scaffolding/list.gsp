<% import grails.persistence.Event %>
<%=packageName%>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
    <r:require module="application" />
</head>
<body>
<content tag="breadcrumbs">
    <ul class="breadcrumb">
        <li><a class="home" href="\${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
</content>
<content tag="banner"><g:message code="default.list.label" args="[entityName]" /></content>
<content tag="banner-description"></content>
<content tag="message"><div class="message" role="status">\${flash.message}</div></content>
<content tag="detail">
    <div class="table-responsive">
        <table class="table table-bordered table-condensed table-striped table-hover" role="main">
            <thead>
            <tr>
                <% excludedProps = Event.allEvents.toList() << 'id' << 'version'
                allowedNames = domainClass.persistentProperties*.name << 'dateCreated' << 'lastUpdated'
                props = domainClass.properties.findAll { allowedNames.contains(it.name) && !excludedProps.contains(it.name) && it.type != null && !Collection.isAssignableFrom(it.type) }
                Collections.sort(props, comparator.constructors[0].newInstance([domainClass] as Object[]))
                props.eachWithIndex { p, i ->
                    if (i < 6) {
                        if (p.isAssociation()) { %>
                <th><g:message code="${domainClass.propertyName}.${p.name}.label" default="${p.naturalName}"/></th>
                <% } else { %>
                <g:sortableColumn property="${p.name}"
                                  title="\${message(code: '${domainClass.propertyName}.${p.name}.label', default: '${p.naturalName}')}"/>
                <% }
                }
                } %>
                <th><g:message code="default.actions.label" default="Actions" /></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="\${${propertyName}List}" status="i" var="${propertyName}">
                <tr class="\${(i % 2) == 0 ? 'even' : 'odd'}">
                    <%  props.eachWithIndex { p, i ->
                        if (i == 0) { %>
                    <td><g:link action="show" id="\${${propertyName}.id}">\${fieldValue(bean: ${propertyName}, field: "${p.name}")}</g:link></td>
                    <%      } else if (i < 6) {
                        if (p.type == Boolean || p.type == boolean) { %>
                    <td><g:formatBoolean boolean="\${${propertyName}.${p.name}}" /></td>
                    <%          } else if (p.type == Date || p.type == java.sql.Date || p.type == java.sql.Time || p.type == Calendar) { %>
                    <td><g:formatDate date="\${${propertyName}.${p.name}}" /></td>
                    <%          } else { %>
                    <td>\${fieldValue(bean: ${propertyName}, field: "${p.name}")}</td>
                    <%  }   }   } %>
                    <td>
                        <g:link action="show" id="\${${propertyName}.id}"><g:message code="default.actions.view.label" default="View" /></g:link> |
                        <g:link action="edit" id="\${${propertyName}.id}"><g:message code="default.actions.edit.label" default="Edit" /></g:link> |
                        <g:link action="delete" id="\${${propertyName}.id}" onclick="return confirm('\${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"><g:message code="default.actions.delete.label" default="Delete" /></g:link>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
        <div class="pagination">
            <g:paginate class="pagination" total="\${${propertyName}Total}" />
        </div>
    </div>
</content>
</div>
</body>
</html>