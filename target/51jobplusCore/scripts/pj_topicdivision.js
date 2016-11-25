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

	var Condition = $("#topics_input").val();
	var sortType = $("#filterCollapse li.last span.active").attr("data-index");
	var sharedType = $("#filterCollapse li.first span.active").attr(
			"data-index");
	var tags = "";
	var uurl = encodeURI(projectName + "topics/fore/search/" + sharedType
			+ "?Condition=" + Condition + "&sortType=" + sortType);
	
	window.location.href = uurl;

}