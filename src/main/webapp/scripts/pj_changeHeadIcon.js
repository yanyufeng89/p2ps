//个人中心更换头像
function previewImage(file,type){
		 var MAXWIDTH  = 260; 
         var MAXHEIGHT = 180;
         var div='';
         var imgpath=$('input[type=file]').val();
         if(type==1){
        	 div=document.getElementById('comlogo');
         }else{
        	 div=document.getElementById('preview');
         }
         var filetype=['jpg','png','jpeg','pns'];
         var imgtype=file.files[0].name.substring(file.files[0].name.length-3,file.files[0].name.length)
         if($.inArray(imgtype.toLowerCase(),filetype)!=-1){
        	 updUserInfo();
        	 if (file.files && file.files[0]){
        		 if(type==1){//1表示更改公司logo  2代表更改公司简介图片
	                 div.innerHTML =
	                '<img id=imghead width="180" height="180"><span class="ProfileAvatarEditor-tip">更换头像</span><form method="POST"  id="previewImage" enctype="multipart/form-data"><input name="headIconFile" class="file-3" type="file" accept="image/*" size="30" onchange="previewImage(this)" /> </form>';
        		 }else{
        			   div.innerHTML =
       	                '<img id=imghead width="150" height="190"><span class="ProfileAvatarEditor-tip">更换头像</span><form method="POST"  id="previewImage" enctype="multipart/form-data"><input name="headIconFile" class="file-3" type="file" accept="image/*" size="30" onchange="previewImage(this)" /> </form>';
        		 }
                 
                 var img = document.getElementById('imghead');
                /* img.onload = function(){
                   var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
                   img.width  =  rect.width;
                   img.height =  rect.height;
                   img.style.marginTop = rect.top+'px';
                 }*/

                 var reader = new FileReader();
                 reader.onload = function(evt){img.src = evt.target.result;}
                 reader.readAsDataURL(file.files[0]);
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
        	 ZENG.msgbox.show('抱歉，上传失败 ，请上传小于2M的图片!', 5, 3000);return false;
         }
         
   }
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
             /*alert('hk'); */ 
         },  
         error: function (data) {  
             alert(data);  
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
