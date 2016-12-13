//个人中心更换头像
function previewImage(file,type){
		 var MAXWIDTH  = 260; 
         var MAXHEIGHT = 180;
         var div='';
         var imgpath=$('input[type=file]').val();
         //type 1表示更改企业logo  2代表修改公司简介图片
         if(type==1){
        	 div=document.getElementById('comlogo');
         }else if(type==2){
        	 div=document.getElementById('briefimg');
         }else{
        	 div=document.getElementById('preview');
         }
         var filetype=['jpg','png','jpeg','pns'];
         if(type==2){
        	 uploadImg=file;
         }else{
        	 uploadImg=file.files[0];
         }
         var imgtype=uploadImg.name.substring(uploadImg.name.length-3,uploadImg.name.length)
         if($.inArray(imgtype.toLowerCase(),filetype)!=-1){
        	 if (uploadImg){
        		 if(type==1){
        			 updUserInfo();
	                 div.innerHTML =
	                '    <img id=imghead width="180" height="180" alt=""><span class="ProfileAvatarEditor-tip">更换头像</span><form method="POST"  id="previewImage" enctype="multipart/form-data"><input name="headIconFile" class="file-3" type="file" accept="image/jpg,image/jpeg,image/png,image/pns" size="30" onchange="previewImage(this,1)" /> </form>';
        		 }else if(type==2){
        			   updComInfo();
        			   div.innerHTML =
       	                '<img id=imgbrief width="834" height="220" alt=""><span class="ProfileAvatarEditor-tip">更换图片</span><form method="POST"  id="briefImage" enctype="multipart/form-data"><input name="imgFiles" class="file-3" id="brieffile" type="file" accept="image/jpg,image/jpeg,image/png,image/pns" size="30"/> </form>';
        		 }else{
        			 updUserInfo();
        			 div.innerHTML =
        	            '<img id=imghead width="150" height="190" alt=""><span class="ProfileAvatarEditor-tip">更换头像</span><form method="POST"  id="previewImage" enctype="multipart/form-data"><input name="headIconFile" class="file-3" type="file" accept="image/jpg,image/jpeg,image/png,image/pns" size="30" onchange="previewImage(this)" /> </form>';
        		 }
                 var img;
                 if(type==2){
                	 img = document.getElementById('imgbrief');
                 }else{
                	 img = document.getElementById('imghead');
                 }
                  
                /* img.onload = function(){
                   var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
                   img.width  =  rect.width;
                   img.height =  rect.height;
                   img.style.marginTop = rect.top+'px';
                 }*/

                 var reader = new FileReader();
                 reader.onload = function(evt){img.src = evt.target.result;}
                 reader.readAsDataURL(uploadImg);
             }
             else //兼容IE
             {
               var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
               file.select();
               var src = document.selection.createRange().text;
               div.innerHTML = '<img id=imghead>';
               var img = document.getElementById('imghead');
               img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
               var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
               status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
               div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
             }
         }
         else{
        	 ZENG.msgbox.show('抱歉，上传失败 ，请上传小于2M的图片!', 5, 3000);
        	 return false;
         }
         
   }
//获取input图片宽高和大小
/*function getImageWidthAndHeight(id, callback) {
    var _URL = window.URL || window.webkitURL;
    $("#" + id).change(function (e) {
      var file, img;
      if ((file = this.files[0])) {
        img = new Image();
        img.onload = function () {
          callback && callback({"width": this.width, "height": this.height, "file":file});
          if(this.width<830 || this.height<220){
         	 ZENG.msgbox.show('请上传宽度大于830px,高度大于220px的图片!', 5, 3000);return false;
              return false;
           }
     	   previewImage(file,2);
        };
        img.src = _URL.createObjectURL(file);
      }

    });
  }*/
  
  
//更新用户表tbl_user
function updUserInfo() {  
    var formData = new FormData($("#previewImage")[0]);  
    $.ajax({  
    	 url:"/myHome/updUserInfo",
         type: 'POST',  
         data: formData,  
         async: false,  
         cache: false,  
         contentType: false,  
         processData: false,  
         success: function (data) {  
            
         },  
         error: function (data) {  
             alert(data);  
         }  
    });  
} 
//更改用户简介图片
function updComInfo(){
	 var formData = new FormData($("#briefImage")[0]);  
	 $.ajax({  
    	 url:"/comp/updUserInfo",
         type: 'POST',  
         data: formData,  
         /*async: false,  */
         cache: false,  
         contentType: false,  
         processData: false,  
         success: function (data) {  
            
         },  
         error: function (data) {  
             console.log(data);  
         }  
    });  
}
function clacImgZoomParam( maxWidth, maxHeight, width, height ){
            var param = {top:0, left:0, width:width, height:height};
            if( width>maxWidth || height>maxHeight )
            {
                rateWidth = width / maxWidth;
                rateHeight = height / maxHeight;
                
                if( rateWidth > rateHeight )
                {
                    param.width =  maxWidth;
                    param.height = Math.round(height / rateWidth);
                }else
                {
                    param.width = Math.round(width / rateHeight);
                    param.height = maxHeight;
                }
            }
            
            param.left = Math.round((maxWidth - param.width) / 2);
            param.top = Math.round((maxHeight - param.height) / 2);
            return param;
 }


function previewNewsImage(file){
	 var MAXWIDTH  = 260; 
     var MAXHEIGHT = 180;
     var filetype=['jpg','png','jpeg','pns'];
     var imgtype=file.name.substring(file.name.length-3,file.name.length);
     var div='';
     if($.inArray(imgtype.toLowerCase(),filetype)!=-1){
    	 if (file){
    		 var addImgHtml =
	                '<div class="img-preview"><img id=imgnews width="790" height="120" alt=""><a href="javascript:void(0)" class="close-newsimg" id="close-newsimg"></a></div>';
    		 $('#addNewsContent').append(addImgHtml);
    		 var img = document.getElementById('imgnews');
    		 var reader = new FileReader();
             reader.onload = function(evt){img.src = evt.target.result;}
             reader.readAsDataURL(file);
    	 } else{//兼容IE
             var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
             file.select();
             var src = document.selection.createRange().text;
             div.innerHTML = '<img id=imghead>';
             var img = document.getElementById('imghead');
             img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
             var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
             status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
             div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
           }
     }else{
    	 ZENG.msgbox.show('抱歉，上传失败 ，请上传小于2M的图片!', 5, 3000);
    	 return false;
     }
}
