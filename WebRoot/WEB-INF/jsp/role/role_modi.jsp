<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="/netctoss/styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="/netctoss/styles/global_color.css" />
        <script type="text/javascript" src="/netctoss/js/jquery-1.11.1.js"></script>
        <script language="javascript" type="text/javascript">
            $(document).ready(function(){
            	$("#name_error").hide();
            	$("#privilege_error").hide();
            });
            
            
            
            function showResultDiv(flag) {
                var divResult = document.getElementById("save_result_info");
                if (flag)
                    divResult.style.display = "block";
                else
                    divResult.style.display = "none";
            }
            
            //保存成功的提示消息
            var flag = false;
            
            function checkName(){
            	flag = false;
            	var name = $("#name").val();
            	if(name == ""){
            		$("#name_error").show();
            		flag = false;
            	}
            	var pattern = /^[A-Za-z0-9]{1,20}$/;
            	if(pattern.test(name)==false){
            		$("#name_error").show();
            		flag = false;
            		alert("flag: " + flag);
            	}else{
            		flag = true;
            		alert("flag: " + flag);
            	}
            	if(!$(".privileges").is(':checked')) {
    				flag = false;
    				$("#privilege_error").show();
				}
				return flag;
            }
            
            
        </script>
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="/netctoss/images/logo.png" alt="logo" class="left"/>
            <a href="#">[退出]</a>            
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">
            <ul id="menu">
                <li><a href="../index.html" class="index_off"></a></li>
                <li><a href="../role/role_list.html" class="role_on"></a></li>
                <li><a href="../admin/admin_list.html" class="admin_off"></a></li>
                <li><a href="../fee/fee_list.html" class="fee_off"></a></li>
                <li><a href="../account/account_list.html" class="account_off"></a></li>
                <li><a href="../service/service_list.html" class="service_off"></a></li>
                <li><a href="../bill/bill_list.html" class="bill_off"></a></li>
                <li><a href="../report/report_list.html" class="report_off"></a></li>
                <li><a href="../user/user_info.html" class="information_off"></a></li>
                <li><a href="../user/user_modi_pwd.html" class="password_off"></a></li>
            </ul>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">           
            <!--保存操作后的提示信息：成功或者失败-->
            <div id="save_result_info" class="save_success">保存成功！</div>
            <form action="/netctoss/role/toUpdate/${page}" method="post" class="main_form" onsubmit="return checkName()">
                <div class="text_info clearfix"><span>角色名称：</span></div>
                <div class="input_info">
                    <input type="text" class="width200" value="${role.name}" name="name" id="name"/>
                    <input type="hidden" value="${id}" name="id"/>
                    <span class="required">*</span>
                    <div class="validate_msg_medium error_msg" id="name_error">不能为空，且为20长度的字母、数字和汉字的组合</div>
                </div>                    
                <div class="text_info clearfix"><span>设置权限：</span></div>
                <div class="input_info_high">
                    <div class="input_info_scroll">
                    
                    <ul>
                        <li><input type="checkbox" value="1" name="privileges" class="privileges" <c:forEach items="${pids}" var="i"><c:if test="${i eq 1}"> checked="checked"</c:if></c:forEach>/>角色管理</li>
                        <li><input type="checkbox" value="2" name="privileges" class="privileges" <c:forEach items="${pids}" var="i"><c:if test="${i eq 2}"> checked="checked"</c:if></c:forEach>/>管理员</li>
                        <li><input type="checkbox" value="3" name="privileges" class="privileges" <c:forEach items="${pids}" var="i"><c:if test="${i eq 3}"> checked="checked"</c:if></c:forEach>/>资费管理</li>
                        <li><input type="checkbox" value="4" name="privileges" class="privileges" <c:forEach items="${pids}" var="i"><c:if test="${i eq 4}"> checked="checked"</c:if></c:forEach>/>账务账号</li>
                        <li><input type="checkbox" value="5" name="privileges" class="privileges" <c:forEach items="${pids}" var="i"><c:if test="${i eq 5}"> checked="checked"</c:if></c:forEach>/>业务账号</li>
                        <li><input type="checkbox" value="6" name="privileges" class="privileges" <c:forEach items="${pids}" var="i"><c:if test="${i eq 6}"> checked="checked"</c:if></c:forEach>/>账单</li>
                        <li><input type="checkbox" value="7" name="privileges" class="privileges" <c:forEach items="${pids}" var="i"><c:if test="${i eq 7}"> checked="checked"</c:if></c:forEach>/>报表</li>
                     </ul>
                        
                    </div>
                    <span class="required">*</span>
                    <div class="validate_msg_tiny" id="privilege_error">至少选择一个权限</div>
                </div>
                <div class="button_info clearfix">
                    <input type="submit" value="保存" class="btn_save" />
                    <input type="button" value="取消" class="btn_save" />
                </div>
            </form> 
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <span>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</span>
            <br />
            <span>版权所有(C)加拿大达内IT培训集团公司 </span>
        </div>
    </body>
</html>
