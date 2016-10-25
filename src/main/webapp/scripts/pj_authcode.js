    
		$(function () {
			var code = document.getElementById('vcode_imgs1'); 
			var c = new KinerCode({
			    len: 4,//需要产生的验证码长度
			    chars: [
			        1, 2, 3, 4, 5, 6, 7, 8, 9, 0,
			        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
			        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
			    ],//经典模式:指定产生验证码的词典，若不给或数组长度为0则试用默认字典
			    //question:false,//若给定词典为算数题，则此项必须选择true,程序将自动计算出结果进行校验【若选择此项，则可不配置len属性】,若选择经典模式，必须选择false
			    copy: false,//是否允许复制产生的验证码
			    bgColor:"",//背景颜色[与背景图任选其一设置]
			    bgImg:"/image/authcode.inc.jpg",//若选择背景图片，则背景颜色失效
			    randomBg : false,//若选true则采用随机背景颜色，此时设置的bgImg和bgColor将失效
			   
			    codeArea:code,//验证码放置的区域【HTMLDivElement 】
			    click2refresh:true,//是否点击验证码刷新验证码
			    
			    validateEven : "blur",//触发验证的方法名，如click，blur等  
			})
		})
        
        function check_codes()
		{
			var code = document.getElementById('vcode_imgs1'); 
	        code.innerHTML="";
	        var c = new KinerCode({
	            len: 4,//需要产生的验证码长度
	            chars: [
	                1, 2, 3, 4, 5, 6, 7, 8, 9, 0,
	                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
	                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
	            ],//经典模式:指定产生验证码的词典，若不给或数组长度为0则试用默认字典
	           // question:false,//若给定词典为算数题，则此项必须选择true,程序将自动计算出结果进行校验【若选择此项，则可不配置len属性】,若选择经典模式，必须选择false
	            copy: false,//是否允许复制产生的验证码
	            bgColor:"",//背景颜色[与背景图任选其一设置]
	            bgImg:"/image/authcode.inc.jpg",//若选择背景图片，则背景颜色失效
	            randomBg : false,//若选true则采用随机背景颜色，此时设置的bgImg和bgColor将失效
	           
	            codeArea:code,//验证码放置的区域【HTMLDivElement 】
	            click2refresh:true,//是否点击验证码刷新验证码
	            
	            validateEven : "click",//触发验证的方法名，如click，blur等  
	        });
		}
		function showtime(t,messagetype,mtype){
			//1代码获取邮箱验证码  0是手机验证码    mtype=forgetpw 表示是找回密码界面获取验证码  否则是注册界面获取验证码
			if(mtype=='forgetpw'){
				if(messagetype=='1'){
					document.getElementById('emailcode').disabled=true;
					for(i=1;i<=t;i++) {
						window.setTimeout("update_e(" + i + ","+t+")", i * 1000);
					}
				}else{
					document.getElementById('phonecode').disabled=true;
					for(i=1;i<=t;i++) {
						window.setTimeout("update_p(" + i + ","+t+")", i * 1000);
					}
				}
			}else{
				document.getElementById('phonecode').disabled=true;
				for(i=1;i<=t;i++) {
					window.setTimeout("update_p(" + i + ","+t+")", i * 1000);
				}
			}
			
		}
		function update_e(num,t) {
			if(num == t) {
					document.getElementById('emailcode').value='重新发送';
					document.getElementById('emailcode').disabled=false;
			}
			else {
				    printnr = t-num;
					document.getElementById('emailcode').value=" (" + printnr +")秒后重新发送";

			}
		}
		function update_p(num,t){
			if(num == t) {
					document.getElementById('phonecode').value='重新发送';
					document.getElementById('phonecode').disabled=false;
			}
			else {
				    printnr = t-num;
			        document.getElementById('phonecode').value=" (" + printnr +")秒后重新发送";		
			}
		}

		
