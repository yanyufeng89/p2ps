<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>首页</title>

    <meta name="description" content="overview &amp; stats"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <!-- basic styles -->

    <link href="/manage/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="/manage/css/font-awesome.min.css"/>

    <!--[if IE 7]>
    <link rel="stylesheet" href="/manage/css/font-awesome-ie7.min.css"/>
    <![endif]-->

    <!-- page specific plugin styles -->

    <!-- fonts -->

    <!-- ace styles -->

    <link rel="stylesheet" href="/manage/css/ace.min.css"/>
    <link rel="stylesheet" href="/manage/css/ace-rtl.min.css"/>
    <link rel="stylesheet" href="/manage/css/ace-skins.min.css"/>

    <!--[if lte IE 8]>
    <link rel="stylesheet" href="/manage/css/ace-ie.min.css"/>
    <![endif]-->

    <link rel="stylesheet" href="/manage/js/layer/skin/layer.css"/>
    <!-- inline styles related to this page -->

    <!-- ace settings handler -->

    <script src="/manage/js/ace-extra.min.js"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

    <!--[if lt IE 9]>
    <script src="/manage/js/html5shiv.js"></script>
    <script src="/manage/js/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="navbar navbar-default" id="navbar">
    <script type="text/javascript">
        try {
            ace.settings.check('navbar', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="navbar-container" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="#" class="navbar-brand">
                <small>
                    <i class="icon-leaf"></i>
                    JobPlus
                </small>
            </a><!-- /.brand -->
        </div><!-- /.navbar-header -->

        <div class="navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li class="light-blue">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img class="nav-user-photo" src="/manage/avatars/avatar.png"/>
                        <span class="user-info">
									<small>Welcome,</small>
                        ${Session.user.username}
								</span>

                        <i class="icon-caret-down"></i>
                    </a>

                    <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="javascript:logout();">
                                <i class="icon-off"></i>
                                Logout
                            </a>
                        </li>
                    </ul>
                </li>
            </ul><!-- /.ace-nav -->
        </div><!-- /.navbar-header -->
    </div><!-- /.container -->
</div>

<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#">
            <span class="menu-text"></span>
        </a>

        <div class="sidebar" id="sidebar">
            <script type="text/javascript">
                try {
                    ace.settings.check('sidebar', 'fixed')
                } catch (e) {
                }
            </script>
            <ul class="nav nav-list">
                <li>
                    <a href="javascript:void(0);" onclick="reloadFrame('/backstage/resource',this)">
                        <i class="icon-edit"></i>
                        <span class="menu-text"> 资源管理 </span>
                    </a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="reloadFrame('/backstage/user',this)">
                        <i class="icon-group"></i>
                        <span class="menu-text"> 用户管理 </span>
                    </a>
                </li>
                <li>
                    <a href="javascript:void(0);" class="dropdown-toggle">
                        <i class="icon-list"></i>
                        <span class="menu-text"> 投诉建议 </span>

                        <b class="arrow icon-angle-down"></b>
                    </a>

                    <ul class="submenu" style="display: block;">
                        <li>
                            <a href="javascript:void(0);" onclick="reloadFrame('/backstage/complaints',this)">
                                <i class="icon-double-angle-right"></i>
                                用户投诉
                            </a>
                        </li>

                        <li>
                            <a href="javascript:void(0);" onclick="reloadFrame('/backstage/suggest',this)">
                                <i class="icon-double-angle-right"></i>
                                用户建议
                            </a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="reloadFrame('/backstage/sensitive',this)">
                        <i class="icon-tag"></i>
                        <span class="menu-text"> 非法数据 </span>
                    </a>
                </li>
            </ul><!-- /.nav-list -->

            <div class="sidebar-collapse" id="sidebar-collapse">
                <i class="icon-double-angle-left" data-icon1="icon-double-angle-left"
                   data-icon2="icon-double-angle-right"></i>
            </div>

            <script type="text/javascript">
                try {
                    ace.settings.check('sidebar', 'collapsed')
                } catch (e) {
                }
            </script>
        </div>

        <div class="main-content">
            <iframe src="/manage/backstage/index" id="iframepage" onload="changeFrameHeight()" scrolling="no" frameborder="0" style="width: 100%;"></iframe>
        </div><!-- /.main-content -->

        <div class="ace-settings-container" id="ace-settings-container">
            <div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
                <i class="icon-cog bigger-150"></i>
            </div>

            <div class="ace-settings-box" id="ace-settings-box">
                <div>
                    <div class="pull-left">
                        <select id="skin-colorpicker" class="hide">
                            <option data-skin="default" value="#438EB9">#438EB9</option>
                            <option data-skin="skin-1" value="#222A2D">#222A2D</option>
                            <option data-skin="skin-2" value="#C6487E">#C6487E</option>
                            <option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
                        </select>
                    </div>
                    <span>&nbsp; Choose Skin</span>
                </div>

                <div>
                    <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar"/>
                    <label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
                </div>

                <div>
                    <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar"/>
                    <label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
                </div>

                <div>
                    <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs"/>
                    <label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
                </div>

                <div>
                    <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl"/>
                    <label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
                </div>

                <div>
                    <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container"/>
                    <label class="lbl" for="ace-settings-add-container">
                        Inside
                        <b>.container</b>
                    </label>
                </div>
            </div>
        </div><!-- /#ace-settings-container -->
    </div><!-- /.main-container-inner -->

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div><!-- /.main-container -->

<!-- basic scripts -->
<script src="/manage/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
    if ("ontouchend" in document) document.write("<script src='/manage/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
</script>
<script src="/manage/js/bootstrap.min.js"></script>
<script src="/manage/js/typeahead-bs2.min.js"></script>

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
<script src="/manage/js/excanvas.min.js"></script>
<![endif]-->

<script src="/manage/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="/manage/js/jquery.ui.touch-punch.min.js"></script>
<script src="/manage/js/jquery.slimscroll.min.js"></script>
<script src="/manage/js/jquery.easy-pie-chart.min.js"></script>
<script src="/manage/js/jquery.sparkline.min.js"></script>
<script src="/manage/js/flot/jquery.flot.min.js"></script>
<script src="/manage/js/flot/jquery.flot.pie.min.js"></script>
<script src="/manage/js/flot/jquery.flot.resize.min.js"></script>

<!-- ace scripts -->

<script src="/manage/js/ace-elements.min.js"></script>
<script src="/manage/js/ace.min.js"></script>

<!-- inline scripts related to this page -->
<script src="/manage/js/layer/layer.js"></script>
<script>
    function reloadFrame(url,ts) {
        $(".nav-list li").removeClass("active");
        $(ts).parent().addClass("active");
        $("iframe").attr("src", "/manage" + url);
    }

    function changeFrameHeight(){
        var ifm= document.getElementById("iframepage");
        ifm.height=document.documentElement.clientHeight-55;
    }
    window.onresize=function(){
        changeFrameHeight();
    }

    function logout() {
        window.location.href = "/manage/backstage/logout";
    }
</script>
</body>
</html>
