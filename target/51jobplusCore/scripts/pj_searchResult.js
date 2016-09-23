$(function () {
    var $currentType = $(".select-2 a.active").parent();
    var $pos = $("span[data-id='" + $currentType.attr("data-pid") + "']");
    if ($currentType.length == 1) {
        $currentType.show();
        $currentType.parent().show();
        $currentType.parent().find(".select-2-arrow").css({left: $pos.position().left - 111 - $pos.width()}).show();
    }
    //导航栏
    var $posbox = $('#searchCollapse .select-1 span');
    var $anvlfteb = $('#searchCollapse li:first-child')
    $posbox.mouseover(function () {
        var index = $(this).data('index');
        var id = $(this).data('id');
        var positonleft = $(this).position().left;
        if (index != undefined) {
            $('.select-2 .2-list').hide();
            $('.select-2 .2-list').parent().hide();
            var $currentType = $(".select-2 a.active").parent();
            if ($currentType.length == 1) {
                var $pos = $("span[data-id='" + $currentType.attr("data-pid") + "']");
                if ((Number($pos.data("index")) < 12 && Number(index) > 11) || (Number($pos.data("index")) > 11 && Number(index) < 12)) {
                    $currentType.show();
                    $currentType.parent().show();
                    $currentType.parent().find(".select-2-arrow").css({left: $pos.position().left - 111 - $pos.width()}).show();
                }
            }
            $('.select-2 .2-list').each(function () {
                if ($(this).data('pid') == id && $(this).data('index') == index) {
                    $(this).parent().show();
                    $(this).parent().find('.select-2-arrow').css({left: positonleft - 165 + $("span[data-id='" + $(this).attr("data-pid") + "']").width()}).show();
                    $(this).show();
                }
            })
        }
        if (index == undefined) {
            $('.select-2,.select-2 .2-list').hide();
        }
    });
    $posbox.mouseleave(function () {
        var index = $(this).data('index');
        if (index == undefined) {
            $('.select-2 .2-list').hide();
            $('.select-2 .2-list').parent().hide();
            var $currentType = $(".select-2 a.active").parent();
            if ($currentType.length == 1) {
                var $pos = $("span[data-id='" + $currentType.attr("data-pid") + "']");
                $currentType.show().siblings().hide();
                $currentType.parent().show();
                $currentType.parent().find(".select-2-arrow").css({left: $pos.position().left - 111 - $pos.width()}).show();
            }
        }
    });
    $anvlfteb.mouseleave(function () {
        $('.select-2 .2-list').hide();
        $('.select-2 .2-list').parent().hide();
        var $currentType = $(".select-2 a.active").parent();
        if ($currentType.length == 1) {
            var $pos = $("span[data-id='" + $currentType.attr("data-pid") + "']");
            $currentType.show().siblings().hide();
            $currentType.parent().show();
            $currentType.parent().find(".select-2-arrow").css({left: $pos.position().left - 111 - $pos.width()}).show();
        }
    });

    //绑定查询事件
    $("#searchCollapse li:first-child span,.select-2 a").click(function (e) {
        $("#searchCollapse li:first-child span,.select-2 a").removeClass("active");
        $(this).addClass("active");
        reloadPage();
    });
    $('#searchCollapse li:last-child span').click(function (e) {
        $("#searchCollapse li:last-child span").removeClass("active");
        $(this).addClass("active");
        reloadPage();
    });


})

/**
 * 搜索
 * @param encoding
 * @param request
 * @param Condition  查询关键字
 * @param sharedType  分类 100 101 102 200
 * @param protoType  那种分类 doc/book/article/courses/sites/topics
 * @param tags  标签
 * @param pages 第几页，默认第0页
 */
function reloadPage() {
    var Condition = $("#searchres_input").val();
    var protoType = $("#searchCollapse li:last-child span.active").attr("data-objtype");
    var sharedType = 0;
    if ($("#searchCollapse li:first-child span.active").length == 1) {
        sharedType = $("#searchCollapse li:first-child span.active").attr("data-id");
    } else {
        sharedType = $(".select-2 a.active").attr("data-id");
    }
    var tags = "";
    var pages = "";
//    var topicstype = $("#filterCollapse li.last span.active").attr("data-index");
//
//    console.log("Condition=="+Condition);
//    console.log("sharedType="+sharedType);
//    console.log("protoType=="+protoType);

    window.location.href = projectName + "search/" + sharedType + "?Condition=" + Condition + "&protoType=" + protoType;

    /* post请求
     *    $.ajax({
     type:"POST",
     url:projectName + "search/" + sharedType,
     data: {Condition:Condition, sharedType:sharedType, protoType:protoType, tags:tags, pages:pages},
     dataType:"json",
     ansync:false,
     success:function(data){
     var array = eval(data.result);
     console.log(array);
     var datamodel={
     //result:data.result,
     result:array,
     }
     //加载模板
     $('.pagetemplate').setTemplateURL(projectName+'searchTemplate.html');
     $('.pagetemplate').processTemplate(datamodel);
     $('.items_area').empty().append($('.pagetemplate').html());
     $('.pagetemplate').empty();

     //刷新分页插件
     var count = data.reCount;
     var pageSize = data.pageSize;
     if (count > 0) {
     $("#searchpaging").pagination({
     items: count,
     itemsOnPage: pageSize,
     cssStyle: 'light-theme',
     moduleType: 'search'
     });
     } else {
     $("#searchpaging").empty();
     }

     }
     })*/
}