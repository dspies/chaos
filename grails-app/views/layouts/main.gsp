<g:applyLayout name="site">
    <html>
    <head>
        <g:layoutHead/>
        <r:layoutResources />
    </head>
    <body>
    <div class="navbar navbar-fixed-top navbar-default" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Project name</a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/LINK_HOME_PAGE">{{ ACTIVE LINK }}</a></li>
                    <li><a href="/LINK_ALTERNATE">{{ LINK }}</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">{{ DROPDOWN }} <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="/LINK_DROP_DOWN_ALTERNATE">{{ LINK }}</a></li>
                            <li><a href="/LINK_DROP_DOWN_ALTERNATE">{{ LINK }}</a></li>
                            <li><a href="/LINK_DROP_DOWN_ALTERNATE">{{ LINK }}</a></li>
                            <li class="divider"></li>
                            <li class="dropdown-header">{{ DROP DOWN HEADER }}</li>
                            <li><a href="/LINK_DROP_DOWN_ALTERNATE">{{ LINK }}</a></li>
                            <li><a href="/LINK_DROP_DOWN_ALTERNATE">{{ LINK }}</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="/LINK_ALTERNATE">{{ LINK }}</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">{{ USER }} <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="/LINK_DROP_DOWN_ALTERNATE">{{ LINK }}</a></li>
                            <li><a href="/LINK_DROP_DOWN_ALTERNATE">{{ LINK }}</a></li>
                            <li class="divider"></li>
                            <li class="dropdown-header">{{ DROP DOWN HEADER }}</li>
                            <li><a href="/LINK_DROP_DOWN_ALTERNATE">{{ LINK }}</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <!-- /.nav-collapse -->

        </div>
        <!-- /.container -->
    </div>
    <!-- /.navbar -->

    <div class="container-fluid">
        <div class="row row-offcanvas row-offcanvas-left">
            <!-- NAVIGATION -->
            <div class="col-xs-6 col-sm-3 col-md-2 col-lg-2 sidebar-offcanvas" id="sidebar" role="navigation">
                <div class="well well-xs">
                    <ul class="nav">
                        <li class="header">Sidebar</li>
                        <li class="active"><a href="#">Link</a></li>
                        <li><a href="#">Link</a></li>
                        <li><a href="#">Link</a></li>
                        <li class="header">Sidebar</li>
                        <li><a href="#">Link</a></li>
                        <li><a href="#">Link</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">{{ DROPDOWN }} <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="/LINK_DROP_DOWN_ALTERNATE">{{ LINK }}</a></li>
                                <li><a href="/LINK_DROP_DOWN_ALTERNATE">{{ LINK }}</a></li>
                                <li><a href="/LINK_DROP_DOWN_ALTERNATE">{{ LINK }}</a></li>
                                <li class="divider"></li>
                                <li class="dropdown-header">{{ DROP DOWN HEADER }}</li>
                                <li><a href="/LINK_DROP_DOWN_ALTERNATE">{{ LINK }}</a></li>
                                <li><a href="/LINK_DROP_DOWN_ALTERNATE">{{ LINK }}</a></li>
                            </ul>
                        </li>
                        <li>Sidebar</li>
                        <li><a href="#">Link</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">{{ DROPDOWN }} <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="/LINK_DROP_DOWN_ALTERNATE">{{ LINK }}</a></li>
                                <li><a href="/LINK_DROP_DOWN_ALTERNATE">{{ LINK }}</a></li>
                                <li><a href="/LINK_DROP_DOWN_ALTERNATE">{{ LINK }}</a></li>
                                <li class="divider"></li>
                                <li class="dropdown-header">{{ DROP DOWN HEADER }}</li>
                                <li><a href="/LINK_DROP_DOWN_ALTERNATE">{{ LINK }}</a></li>
                                <li><a href="/LINK_DROP_DOWN_ALTERNATE">{{ LINK }}</a></li>
                            </ul>
                        </li>

                    </ul>
                </div>
                <!--/.well -->
            </div>
            <!-- END NAVIGATION -->

            <!-- MAIN CONTENT -->
            <div class="col-xs-12 col-sm-9">
                <p class="pull-left visible-xs">
                    <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
                </p>

                <!-- START ACTUAL DATA -->
                <div id="main-content">
                    <div id="breadcrumbs" class="hidden-xs">
                        <g:pageProperty name="page.breadcrumbs"/>
                        <!-- Breadcrumb
                                <ul class="breadcrumb">
                                    <li><span class="glyphicon glyphicon-home"></span><a href="/LINK_HOME">{{ LINK HOME }}</a></li>
                                    <li><span class="glyphicon glyphicon-list"></span><a href="/LINK_LIST">{{ LIST }}</a></li>
                                    <li><span class="glyphicon glyphicon-list active"></span><a href="/LINK_LIST">{{ LIST }}</a></li>
                                </ul>
                                -->
                    </div>

                    <div id="page-content" class="clearfix">
                        <div class="page-header">
                            <h3>
                                <!-- page.banner -->
                                <g:pageProperty name="page.banner"/>
                                <small>
                                    <span class="glyphicon glyphicon-chevron-right"></span>
                                    <!-- page.banner-description -->
                                    <g:pageProperty name="page.banner-description"/>
                                    <!-- end page.banner-description -->
                                </small>
                            </h3>
                        </div>
                        <!--/.page-header-->

                        <div class="row-fluid">

                            <g:if test="${flash.message}">
                                <div class="alert alert-info">
                                    <a href="#" class="close" data-dismiss="alert">&times;</a>
                                    <g:pageProperty name="page.message"/>
                                </div>
                            </g:if>
                            <g:pageProperty name="page.errors"/>
                            <g:pageProperty name="page.detail"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <a href="#" id="scroll-top" class="btn-sm pull-right">
            top <span class="glyphicon glyphicon-arrow-up"></span>
        </a>

        <div id="footer">
            <span class="left">
                <g:pageProperty name="page.footer-left"/>
                <g:message code="application.version.label" default="Version" />: <g:meta name="app.version"/>
            </span>
            <span class="right">
                <g:pageProperty name="page.footer-right"/>
            &copy; <g:message code="copyright.year" default="" /> <g:message code="company.name" default=""/>
            </span>
        </div>
    </div>
    <r:layoutResources />
    </body>
    </html>
</g:applyLayout>