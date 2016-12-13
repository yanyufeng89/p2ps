<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>后台登录</title>
    <meta name="description" content="User login page"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!-- basic styles -->
    <link href="/manage/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="/manage/css/font-awesome.min.css"/>

    <!--[if IE 7]>
    <link rel="stylesheet" href="/manage/css/font-awesome-ie7.min.css"/>
    <![endif]-->

    <link rel="stylesheet" href="/manage/css/ace.min.css"/>
    <link rel="stylesheet" href="/manage/css/ace-rtl.min.css"/>

    <!--[if lte IE 8]>
    <link rel="stylesheet" href="/manage/css/ace-ie.min.css"/>
    <![endif]-->
    <!-- inline styles related to this page -->
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="/manage/js/html5shiv.js"></script>
    <script src="/manage/js/respond.min.js"></script>
    <![endif]-->
</head>
<body class="login-layout" style="background-color: #e4e6e9;">
<div class="main-container" style="margin-top: 100px;">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container">
                    <div class="position-relative">
                        <div id="login-box" class="login-box visible widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header blue lighter bigger">
                                        <i class="icon-coffee green"></i>
                                        欢迎使用知识库系统后台
                                    </h4>

                                    <div class="space-6"></div>

                                    <form method="post" action="/m/login">
                                        <fieldset>
                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control"
                                                                   placeholder="Username" id="username"
                                                                   name="username"/>
															<i class="icon-user"></i>
														</span>
                                            </label>

                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control"
                                                                   placeholder="Password" id="passwd" name="passwd"/>
															<i class="icon-lock"></i>
														</span>
                                            </label>

                                            <div class="space"></div>

                                            <div class="clearfix">
                                                <button type="submit"
                                                        class="width-35 pull-right btn btn-sm btn-primary">
                                                    <i class="icon-key"></i>
                                                    Login
                                                </button>
                                            </div>

                                            <div class="space-4"></div>
                                        </fieldset>
                                    </form>
                                    <#if message??>
                                        <div class="alert alert-danger">
                                            <i class="icon-remove"></i> ${message}
                                        </div>
                                    </#if>
                                </div><!-- /widget-main -->

                            </div><!-- /widget-body -->
                        </div><!-- /login-box -->
                    </div><!-- /position-relative -->
                </div>
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div>
</div><!-- /.main-container -->
<script src="/manage/js/jquery-1.10.2.min.js"></script>
<script src="/manage/js/jquery.validate.min.js"></script>
<script type="text/javascript">
    $(function(){
         $("form").validate({
            submitHandler: function(form){
                form.submit();
            },
            rules:{
                username:{
                    required:true
                },
                passwd:{
                    required:true
                }
            },
            messages:{
                username:{
                    required:"必填"
                },
                passwd:{
                    required: "必填"
                }
            }
        });
    });
</script>
</body>
</html>
