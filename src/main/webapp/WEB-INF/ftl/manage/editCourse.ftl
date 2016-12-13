<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>后台登录</title>
    <meta name="description" content="User login page"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <meta name="description" content="overview &amp; stats"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<#include "/manage/header.ftl"/>
    <link rel="stylesheet" type="text/css" href="/uedit/themes/default/css/umeditor.css" charset="UTF-8">
    <script type="text/javascript" src="/uedit/umeditor.config.js"></script>
    <script type="text/javascript" src="/uedit/umeditor.js"></script>
</head>
<body>
<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <form class="form-horizontal" role="form" id="courseForm" method="POST" enctype="multipart/form-data">
                <input type="hidden" name="id" id="id" value="${course.id}"/>
                <div class="form-group">
                    <label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 课程标题： </label>

                    <div class="col-sm-10">
                        <input type="text" placeholder="课程标题" name="coursesname" id="coursesname"
                               class="col-xs-10 col-sm-10" value="${course.coursesname}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 课程URL： </label>

                    <div class="col-sm-10">
                        <input type="text" placeholder="课程URL" name="coursesurl" id="coursesurl"
                               value="${course.coursesurl}"
                               class="col-xs-10 col-sm-10"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 课程图片： </label>

                    <div class="col-sm-10">
                        <input name="coursesimgFile" id="coursesimgFile" class="file-3" type="file" accept="image/*"
                               class="col-xs-10 col-sm-10"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label no-padding-right" for="form-field-1"></label>

                    <div class="col-sm-10">
                        <img src="${course.coursesimg}" height="100px">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 课程简介： </label>

                    <div class="col-sm-10">
                        <textarea placeholder="课程简介" name="intro" id="intro" rows="6"
                                  class="col-xs-10 col-sm-10">${course.intro}</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 课程描述： </label>

                    <div class="col-sm-10">
                        <input type='hidden' name='contents' id="contents" value=''>
                        <textarea title="在这里输入描述" id='contentEditor' class='arsummernote'
                                  placeholder='在这里输入描述'>${course.contents}</textarea>
                    </div>
                </div>
                <div class="clearfix" align="center">
                    <div class="col-md-offset-1 col-md-10">
                        <button class="btn btn-info" type="button" onclick="saveEdit();">
                            <i class="icon-ok bigger-110"></i>
                            保存
                        </button>

                        &nbsp; &nbsp; &nbsp;
                        <button class="btn" type="button" onclick="cancelEdit()">
                            <i class="icon-remove bigger-110"></i>
                            取消
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    var editor;
    $(function () {
        //实例化编辑器
        editor = UM.getEditor('contentEditor', {
            initialFrameWidth: 660,//设置编辑器宽度
            initialFrameHeight: 300,//设置编辑器高度
            scaleEnabled: true
        });

        /*var contents = $("#contents").val();
        if (contents != '')
            editor.setContent(contents);*/
    });


    function cancelEdit() {
        closeDia();
    }

    function saveEdit() {
        var coursesname = $.trim($("#coursesname").val());
        var coursesurl = $.trim($("#coursesurl").val());
        var intro = $.trim($("#intro").val());
        if (coursesname == '') {
            $.alertError("请输入课程名称");
            return false;
        }
        if (coursesurl == '') {
            $.alertError("请输入课程URL");
            return false;
        }

        if (editor.hasContents())
            $("#contents").val(editor.getContent());

        var formData = new FormData($("#courseForm")[0]);
        $.ajax({
            url: $path_base + "/m/backstage/updateCourse",
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                if (data == '1') {
                    $.alertSuccess("保存成功!");
                    closeDia();
                } else {
                    $.alertError("保存失败!");
                }
            },
            error: function (data) {

            }
        });
    }

    function closeDia() {
        var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
        parent.layer.close(index);
    }
</script>
</body>
</html>
