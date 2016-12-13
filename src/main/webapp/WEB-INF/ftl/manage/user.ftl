<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>用户管理</title>
<#include "/manage/header.ftl"/>
</head>
<body>
<div class="page-content">
    <div class="page-header">
        <h1>
            用户管理
        </h1>
    </div><!-- /.page-header -->
    <div class="row">
        <form class="form-horizontal" id="sample-form" style="display: block;">
            <div class="form-group">
                <label class="col-md-1 control-label">用户名:</label>
                <div class="col-md-2">
                    <input type='text' id='search_username' class='form-control' placeholder="输入用户名"/>
                </div>
                <label class="col-md-1 control-label">邮箱:</label>
                <div class="col-md-2">
                    <input type='text' id='search_email' class='form-control' placeholder="输入邮箱"/>
                </div>
                <label class="col-md-1 control-label">手机号码:</label>
                <div class="col-md-2">
                    <input type='text' id='search_mobile' class='form-control' placeholder="输入手机号码"/>
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
            <!-- PAGE CONTENT BEGINS -->
            <table id="jqGrid"></table>

            <div id="gridPager"></div>

            <!-- PAGE CONTENT ENDS -->
        </div><!-- /.col -->
    </div><!-- /.row -->
</div><!-- /.page-content -->
<script type="text/javascript">

    $(function ($) {
        $("#jqGrid").jqGrid({
            url: $path_base + '/m/backstage/user',
            mtype: "POST",
            datatype: "json",
            height: 350,
            colModel: [
                {name: 'userid', key: 'true', hidedlg: true, hidden: true},
                {label: '用户名', name: 'username', width: '100', sortable: false},
                {label: '邮箱', name: 'email', width: '100', sortable: false},
                {label: '手机', name: 'mobile', width: '100', sortable: false},
                {label: '创建时间', name: 'createtime', width: '30', sortable: false},
                {label: '操作', name: 'operates', width: 20, sortable: false, resize: false}
            ],
            rowNum: 10,
            sortname: 'id',
            sortorder: "desc",
            rowList: [10, 20],
            rownumbers: true,
            autowidth: true,
            regional: 'cn',
            viewrecords: true,
            multiselect: false,
            height: 400,
            pager: "#gridPager",
            ajaxGridOptions: {contentType: "application/json"},
            serializeGridData: function (data) {
                return JSON.stringify(data);
            },
            gridComplete: function () {
                updatePagerIcons(this);
                var rowIds = $("#jqGrid").jqGrid('getDataIDs');
                for (var i = 0; i < rowIds.length; i++) {
                    var rowData = $("#jqGrid").jqGrid('getRowData', rowIds[i]);
                    var operate = '&emsp;<button type="button" class="btn btn-danger btn-minier" onclick="deleteUser(' + rowData.userid + ');">删除</button>';
                    $("#jqGrid").jqGrid("setRowData", rowIds[i], {operates: operate});
                }
            }
        });

        $(window).bind('resize', function () {
            var width = $('.col-xs-12').width();
            $('#jqGrid').setGridWidth(width);
        });
    });

    function deleteUser(id) {
        parent.layer.confirm('确定删除该用户？', {
            btn: ['确定', '取消']
        }, function () {
            $.ajax({
                type: "POST",
                url: $path_base + "/m/backstage/user/delete/" + id,
                dataType: "json",
                success: function (data) {
                    if (data == '1') {//返回成功
                        $.alertSuccess('删除成功！');
                        $("#jqGrid").jqGrid('setGridParam', {
                            postData: $("#jqGrid").jqGrid("getGridParam").postData
                        }).trigger("reloadGrid");
                    } else {//返回失败
                        $.alertError('删除失败！');
                    }
                }
            });
        }, function () {
        });
    }

    function search() {
        var condition = new Object();
        condition.username = $.trim($("#search_username").val());
        condition.email = $.trim($("#search_email").val());
        condition.mobile = $.trim($("#search_mobile").val());

        $("#jqGrid").jqGrid('setGridParam', {
            postData: {'condition': condition},
            page: 1
        }).trigger("reloadGrid");
    }
</script>
</body>
</html>
