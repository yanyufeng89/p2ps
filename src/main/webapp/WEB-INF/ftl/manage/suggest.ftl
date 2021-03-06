<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>用户建议</title>
<#include "/manage/header.ftl"/>
</head>
<body>
<div class="page-content">
    <div class="page-header">
        <h1>
            用户建议
        </h1>
    </div><!-- /.page-header -->
    <div class="row">
        <form class="form-horizontal" id="sample-form" style="display: block;">
            <div class="form-group">
                <label class="col-md-1 control-label">用户电话:</label>
                <div class="col-md-2">
                    <input type='text' id='search_username' class='form-control' placeholder="输入用户名"/>
                </div>
                <label class="col-md-1 control-label">用户邮箱:</label>
                <div class="col-md-2">
                    <input type='text' id='search_email' class='form-control' placeholder="输入邮箱"/>
                </div>
                <label class="col-md-1 control-label">是否处理:</label>
                <div class="col-md-2">
                    <select id='search_isdeal'>
                        <option value="">全部</option>
                        <option value="0">待处理</option>
                        <option value="1">已处理</option>
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
            url: $path_base + '/m/backstage/suggest',
            mtype: "POST",
            datatype: "json",
            height: 350,
            colModel: [
                {name: 'id', key: 'true', hidedlg: true, hidden: true},
                {name: 'isdeal', hidden: true},
                {label: '用户电话', name: 'sugtel', width: '100', sortable: false},
                {label: '用户邮箱', name: 'sugemail', width: '100', sortable: false},
                {label: '建议内容', name: 'sugcontent', sortable: false},
                {
                    label: '是否处理',
                    name: 'isdealcopy',
                    width: '20',
                    sortable: false,
                    align: 'center',
                    formatter: function (cellvalue, options, rowObject) {
                        if (rowObject.isdeal == 1)
                            return '<i class="icon-ok" style="color: #34c951;" title="已处理"></i>';
                        return '<i class="icon-remove" style="color: #dc4256;" title="待处理"></i>';
                    }
                },
                {label: '提交时间', name: 'sugtime', width: '30', sortable: false},
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
                    if (rowData.isdeal != 1) {
                        var operate = '&emsp;<button type="button" class="btn btn-danger btn-minier" onclick="deal(' + rowIds[i] + ');">确认处理</button>';
                        $("#jqGrid").jqGrid("setRowData", rowIds[i], {operates: operate});
                    }
                }
            }
        });

        $(window).bind('resize', function () {
            var width = $('.col-xs-12').width();
            $('#jqGrid').setGridWidth(width);
        });
    });

    function deal(id) {
        parent.layer.confirm('确认已处理？', {
            btn: ['确定', '取消']
        }, function () {
            $.ajax({
                type: "POST",
                url: $path_base + "/m/backstage/suggest/deal/" + id,
                dataType: "json",
                success: function (data) {
                    if (data == '1') {//返回成功
                        $.alertSuccess('处理成功！');
                        $("#jqGrid").jqGrid('setGridParam', {
                            postData: $("#jqGrid").jqGrid("getGridParam").postData
                        }).trigger("reloadGrid");
                    } else {//返回失败
                        $.alertError('处理失败！');
                    }
                }
            });
        }, function () {
        });
    }

    function search() {
        var condition = new Object();
        condition.isdeal = $.trim($("#search_isdeal").val());
        condition.sugemail = $.trim($("#search_email").val());
        condition.sugtel = $.trim($("#search_mobile").val());

        $("#jqGrid").jqGrid('setGridParam', {
            postData: {'condition': condition},
            page: 1
        }).trigger("reloadGrid");
    }
</script>
</body>
</html>
