<meta name="description" content="User login page"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>

<link href="/manage/css/bootstrap.min.css" rel="stylesheet"/>
<link rel="stylesheet" href="/manage/css/font-awesome.min.css"/>
<link rel="stylesheet" href="/manage/css/jquery-ui-1.10.3.full.min.css"/>
<link rel="stylesheet" href="/manage/css/ui.jqgrid.css"/>

<link rel="stylesheet" href="/manage/css/ace.min.css"/>
<link rel="stylesheet" href="/manage/css/ace-rtl.min.css"/>
<link rel="stylesheet" href="/manage/css/ace-skins.min.css"/>

<script src="/manage/js/jquery-1.10.2.min.js"></script>
<script src="/manage/js/bootstrap.min.js"></script>
<script src="/manage/js/typeahead-bs2.min.js"></script>
<script src="/manage/js/date-time/bootstrap-datepicker.min.js"></script>
<script src="/manage/js/jqGrid/jquery.jqGrid.min.js"></script>
<script src="/manage/js/jqGrid/i18n/grid.locale-en.js"></script>
<script src="/manage/js/ace-elements.min.js"></script>
<script src="/manage/js/ace.min.js"></script>
<script type="text/javascript">
    var $path_base = "";
    function updatePagerIcons(table) {
        var replacement ={
            'ui-icon-seek-first': 'icon-double-angle-left bigger-140',
            'ui-icon-seek-prev': 'icon-angle-left bigger-140',
            'ui-icon-seek-next': 'icon-angle-right bigger-140',
            'ui-icon-seek-end': 'icon-double-angle-right bigger-140'
        };
        $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function () {
            var icon = $(this);
            var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
            if ($class in replacement) icon.attr('class', 'ui-icon ' + replacement[$class]);
        })
    }

    jQuery.extend({
        alert: function (msg) {
            parent.layer.alert(msg);
        },
        alert: function (msg, icon) {
            parent.layer.alert(msg, {icon: icon, closeBtn: 0});
        },
        alertSuccess: function (msg) {
            parent.layer.alert(msg, {icon: 1, closeBtn: 0});
        },
        alertError: function (msg) {
            parent.layer.alert(msg, {icon: 2, closeBtn: 0});
        },
        alertException: function () {
            parent.layer.alert("服务器异常，请稍后再试！", {icon: 5});
        }
    });
</script>