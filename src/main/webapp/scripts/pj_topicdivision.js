$(function () {
    //绑定查询事件
    $("#filterCollapse li span").click(function (e) {
    	$(this).parents('li').find('span').removeClass("active")
        /*$(this).siblings().removeClass("active");*/
        $(this).addClass("active");
        reloadPage();
    });
})

function reloadPage() {
    var theme = $("#filterCollapse li.last span.active").attr("data-index");
    var topicstype = $("#filterCollapse li.first span.active").attr("data-index");
    $.ajax({
        type: "POST",
        url: projectName + "topics/fore/search/" + theme,
        data: {topicstype: topicstype},
        dataType: "json",
        ansync: false,
        success: function (data) {
            var datamodel = {
                topicsPage: data.topicsPage.list,
                topiclen:data.topicsPage.count,
            }
            //加载模板
            $('.pagetemplate').setTemplateURL(projectName + 'topicSearchTemplate.html');
            $('.pagetemplate').processTemplate(datamodel);
            $('.items_area').empty().append($('.pagetemplate').html());
            $('.pagetemplate').empty();
            //刷新分页插件
            var count = data.topicsPage.count;
            var pageSize = data.topicsPage.pageSize;
            if (count > 0) {
                $("#sharetopicpaging").pagination({
                    items: count,
                    itemsOnPage: pageSize,
                    cssStyle: 'light-theme',
                    moduleType: 'topicsearch'
                });
            } else {
                $("#sharetopicpaging").empty();
            }
        }
    })
}