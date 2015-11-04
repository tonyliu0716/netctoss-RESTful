<%@ page pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="/netctoss/styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="/netctoss/styles/global_color.css" /> 
        <script type="text/javascript" src="/netctoss/js/jquery-1.11.1.js"></script>
        <script type="text/javascript">
       		 
       		 function submitTable(){
       		 	//使用AJAX发送敏感信息
       		 	var name = $("#name").val();
       		 	var password = $("#password").val();
       		 	var code = $("#vcode").val();
       		 	//发送请求
       		 	$.ajax({
       		 		url:"/netctoss/login/login/" + code,
       		 		type:"GET",
       		 		beforeSend:function(xhr){
       		 			//将账号和密码放入HTTP请求的Header部分
       		 			xhr.setRequestHeader("name", name);
       		 			xhr.setRequestHeader("password", password);
       		 		},
       		 		success: function(data){
       		 			//服务器返回内容,是一个Map
       		 			var ok = data.login;
       		 			if(ok){
       		 				window.location = "/netctoss/login/toIndex";
       		 			}else{
       		 				//不成功，显示错误信息
       		 				$("#code_error").html(data.code_error);
       		 				$("#error").html(data.error);
       		 				//刷新验证码信息：
       		 				var timestamp = (new Date()).valueOf();
       		 				$("#image").attr("src","/netctoss/login/getCode/" + timestamp);
       		 			}
       		 		}
       		 	});	
       		 }
       		 
       		 function changeImage(){
       		 	var timestamp = (new Date()).valueOf();
       		 	$("#image").attr("src","/netctoss/login/getCode/" + timestamp);
       		 }
        </script>
    </head>
    <body class="index">
       <form action="" method="" id="loginForm">
        <div class="login_box">
            <table>
                <tr>
                    <td class="login_info">账号：</td>
                    <td colspan="2">
                    <input id="name" name="admin_code" type="text" class="width150" />
                   
                    </td>
                    <td class="login_error_info"><span id="username" class="required" ></span></td>
                </tr>
                <tr>
                    <td class="login_info">密码：</td>
                    <td colspan="2">
                    <input id="password" name="password" type="password" class="width150" />
                    </td>
                    <td><span id="message" class="required"></span></td>
                </tr>
                <tr>
                    <td class="login_info">验证码：</td>
                    <td class="width70">
                    <input id="vcode" name="code" type="text" class="width70" />
                    </td>
                    <td>
	                    <img id="image" src="/netctoss/login/getCode/(new Date())" alt="verification code" title="change" />
                    </td>  
                    <td>
	                    <a href="javascript:;" onclick="changeImage();">
	                    	<span>Change</span>
	                    </a>
                    	<br/>
                    	<span class="required" id="code_error"></span>
                    </td>              
                </tr>            
                <tr>
                    <td></td>
                    <td class="login_button" colspan="2">
                        <a href="javascript:;" onclick="submitTable()" id="submit"><img src="/netctoss/images/login_btn.png" /></a>
                    </td>    
                    <td><span class="required" id="error"></span></td>                
                </tr>
            </table>
        </div>
        </form>
    </body>
</html>
