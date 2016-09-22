<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>资源管理</title>
<#include "/manage/header.ftl"/>
</head>
<body>
<div class="page-content">
    <div class="page-header">
        <h1>
            资源管理
        </h1>
    </div><!-- /.page-header -->
    <div class="row">
        <form class="form-horizontal" id="sample-form" style="display: block;">
            <div class="form-group">
                <label class="col-md-1 control-label required">ID:</label>
                <div class="col-md-2">
                    <input type='text' id='search_id' class='form-control' placeholder="输入ID"/>
                </div>
                <label class="col-md-1 control-label required">类型:</label>
                <div class="col-md-2">
                    <select id='search_type'>
                        <option value="0">文档</option>
                        <option value="1">话题</option>
                        <option value="2">书籍</option>
                        <option value="3">课程</option>
                        <option value="4">文章</option>
                        <option value="5">站点</option>
                        <option value="6">文档评论</option>
                        <option value="7">书籍评论</option>
                        <option value="8">话题评论</option>
                        <option value="9">课程评论</option>
                        <option value="10">文章评论</option>
                        <option value="11">站点评论</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <button class="btn btn-info btn-sm" type="button" onclick="search()"><i
                            class="icon-ok bigger-110"></i>查找
                    </button>
                    <button class="btn btn-sm" type="reset"><i class="icon-undo bigger-110"></i>重置</button>
                </div>
            </div>
        </form>
    </div>
    <hr/>
    <div class="row">
        <div class="col-xs-12">
            <table id="sample-table-1" class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <th width="100">ID</th>
                    <th>内容</th>
                    <th width="100">操作</th>
                </tr>
                </thead>

                <tbody>

                </tbody>
            </table>
        </div><!-- /.col -->
    </div><!-- /.row -->
</div><!-- /.page-content -->
<script type="text/javascript">
    function deleteResource(id, tableName) {
        parent.layer.confirm('确定删除该资源？', {
            btn: ['确定', '取消']
        }, function () {
            $.ajax({
                type: "POST",
                url: $path_base + "/manage/backstage/delObjOrComment",
                data: {id: id, tableName: tableName},
                dataType: "json",
                success: function (data) {
                    if (data.returnStatus == '000') {//返回成功
                        $.alertSuccess('删除成功！');
                        $("tbody").empty();
                    } else {//返回失败
                        $.alertError('删除失败！');
                    }
                }
            });
        }, function () {
        });
    }

    function search() {
        var search_id = $.trim($("#search_id").val());
        var search_type = $.trim($("#search_type").val());
        if (search_id == '' || search_type == '') {
            $.alertError("请ID和类型不能为空");
            return false;
        }
        $.ajax({
            type: "POST",
            url: $path_base + "/manage/backstage/getOneObj",
            data: {id: search_id, type: search_type},
            dataType: "json",
            success: function (data) {
                $("tbody").empty();
                if (data.returnStatus == '000') {
                    var obj = data.obj;
                    var html = '<td><a href="' + obj.objUrl + '" target="_blank">' + obj.objId + '</a></td><td><a href="' + obj.objUrl + '" target="_blank">' + obj.objName + '</a></td><td>&emsp;<button class="btn btn-xs btn-danger" onclick="deleteResource(' + obj.objId + ',\'' + obj.objTblName + '\')"><i class="icon-trash bigger-120"></i></button></td>';
                    $("tbody").append(html);
                }
            }
        });
    }
</script>
</body>
</html>
