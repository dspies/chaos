<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Chaos - Homepage</title>
        <r:require module="application" />
	</head>
	<body>
        <content tag="banner"><g:message code="welcome.banner" default="Welcome" /></content>
        <content tag="banner-description"><g:message code="welcome.banner.description" default="" /></content>
        <content tag="message">
            <div class="message" role="status">${flash.message}</div>
        </content>
        <content tag="detail">
			<p><g:message code="welcome.message" default="Welcome to the Chaos Research System" /></p>
		</div>
        </content>
	</body>
</html>
