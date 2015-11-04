<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
        <script type="text/javascript" src="../js/jquery-1.11.1.js"></script>
        <script language="javascript" type="text/javascript">
            //保存结果的提示
            function showResult() {
                showResultDiv(true);
                window.setTimeout("showResultDiv(false);", 3000);
            }
            function showResultDiv(flag) {
                var divResult = document.getElementById("save_result_info");
                if (flag)
                    divResult.style.display = "block";
                else
                    divResult.style.display = "none";
            }

            //切换资费类型
            function feeTypeChange(type) {
                var inputArray = document.getElementById("main").getElementsByTagName("input");
                if (type == 1) {
                    inputArray[4].readonly = true;
                    inputArray[4].value = "";
                    inputArray[4].className += " readonly";
                    inputArray[5].readonly = false;
                    inputArray[5].className = "width100";
                    inputArray[6].readonly = true;
                    inputArray[6].className += " readonly";
                    inputArray[6].value = "";
                    $("#star_base").hide();
                    $("#base_duration_error").hide();
                    $("#star_unit").hide();
                    $("#unit_cost_error").hide();
                }
                else if (type == 2) {
                    inputArray[4].readonly = false;
                    inputArray[4].className = "width100";
                    inputArray[5].readonly = false;
                    inputArray[5].className = "width100";
                    inputArray[6].readonly = false;
                    inputArray[6].className = "width100";
                    $("#star_base").show();
                    $("#base_duration_error").show();
                    $("#star_cost").show();
                    $("#base_cost_error").show();
                     $("#star_unit").show();
                    $("#unit_cost_error").show();
                }
                else if (type == 3) {
                    inputArray[4].readonly = true;
                    inputArray[4].value = "";
                    inputArray[4].className += " readonly";
                    inputArray[5].readonly = true;
                    inputArray[5].value = "";
                    inputArray[5].className += " readonly";
                    inputArray[6].readonly = false;
                    inputArray[6].className = "width100";
                    $("#star_base").hide();
                    $("#base_duration_error").hide();
                    $("#star_cost").hide();
                    $("#base_cost_error").hide();
                }
            }
            
            var name_flag = false;
            //表单触发检查，由onsumbit触发
            function doSubmit(){
            	name_flag = checkName();
            	var flags = checkBaseDuration();
            	return name_flag && flags;
            }
            
            function checkName(){
            	name_flag = false;
            	var v_name = $("#name").val();
            	if(v_name == ""){
            		$("#name_error").html("资费名不能为空！");
            		$("#name_error").addClass("error_msg");
            		name_flag = false;
            		return false;
            	}
            	//如果不为空，发送Ajax
            	$.ajax({
            		type:"GET",
            		url:"check/" + v_name,
            		async: false,
            		success: function(ok){
            			if(ok){
            				$("#name_error").html("资费名可用");
            				$("#name_error").removeClass("error_msg");
            				//允许提交
            				name_flag = true;
            			}else{
            				$("#name_error").html("资费名被占用!");
            				$("#name_error").addClass("error_msg");
            				name_flag = false;
            			}
            		}
            	});
            	
            	//根据变量name_flag的值，判断是否可以执行submit
            	return name_flag;
            }
            
            //检查基本时长！
            var baseDuration_flag = false;
            var baseCost_flag = false;
            var unitCost_flag = false;
            function checkBaseDuration(){
            	baseDuration_flag = false;
            	baseCost_flag = false;
            	unitCost_flag = false;
            	//检测基本时长,如果为套餐类型，则不能为空
            	var v_type = $("input[name='cost_type']:checked").val();
            	
            	
            	if(v_type == "2"){//套餐类型
            		var v_duration = $("#base_duration").val();
            		var v_cost = $("#base_cost").val();
            		
            		if(v_duration == ""){
            			$("#base_duration_error").html("基本时长不能为空！");
            			$("#base_duration_error").addClass("error_msg");
            			baseDuration_flag = false;
            		}else{
            			//如果有值，必须在1-600之间
            			//isNaN方法的意思是is not a number,是的返回true,不是的返回false
            			var isNumber = isNaN(v_duration);
            			if(!isNumber){
            				if(parseInt(v_duration) >= 1 && parseInt(v_duration) <= 600){
            					$("#base_duration_error").html("类型正确！");
            					$("#base_duration_error").removeClass("error_msg");
            					baseDuration_flag = true;
            				}
            			}
            			if(!baseDuration_flag){
            				$("#base_duration_error").html("1-600之间！");
            				$("#base_duration_error").addClass("error_msg");
            				baseDuration_flag = false;
            			}
            			//********base_cost********
            			if(v_cost == ""){
            				$("#base_cost_error").html("基本费用不能为空！");
            				$("#base_cost_error").addClass("error_msg");
            				baseCost_flag = false;
            			}else{
            				var cost_num = isNaN(v_cost);
            				
            				if(cost_num == false){
            					//结果为数字，则判断范围
            					if(parseFloat(v_cost) >= 1 && parseFloat(v_cost) <= 9999.99){
            						$("#base_cost_error").html("格式正确！");
            						$("#base_cost_error").removeClass("error_msg");
            						baseCost_flag = true;
            					}else{
            						$("#base_cost_error").html("数字范围必须在0-9999.99之间");
            						$("#base_cost_error").addClass("error_msg");
            						baseCost_flag = false;
            					}
            				}else{
            					//结果为 true，说明不是数字
            					$("#base_cost_error").html("基本费用必须数字！");
            					$("#base_cost_error").addClass("error_msg");
            					baseCost_flag = false;
            				}
            			}
            			//*******unit_cost********	
            			var v_unit = $("#unit_cost").val();
            			
            			if(v_unit == ""){
            				$("#unit_cost_error").html("单位费用不能为空!");
            				$("#unit_cost_error").addClass("error_msg");
            				unitCost_flag = false;
            			}else{
            				//如果不为空，判断是否为数字
            				var notNumber = isNaN(v_unit);
            				if(notNumber){
            					//如果不是数字
            					$("#unit_cost_error").html("单位费用必须为数字！");
            					$("#unit_cost_error").addClass("error_msg");
            					unitCost_flag = false;
            				}else{
            					//如果是数字，则判断范围
            					if(parseFloat(v_unit) >= 1 && parseFloat(v_unit) <= 9999.99){
            						$("#unit_cost_error").html("格式正确！");
            						$("#unit_cost_error").removeClass("error_msg");
            						unitCost_flag = true;
            					}else{
            						$("#unit_cost_error").html("单位费用必须为0-9999.99之间的数字！");
            						$("#unit_cost_error").addClass("error_msg");
            						unitCost_flag = false;
            					}
            				}
            			}
            		}
            	}else if(v_type == "1"){//包月类型，只需要判断基本费用
            		baseDuration_flag = true;
            		unitCost_flag = true;
            		baseCost_flag = false;
            		var v_base = $("#base_cost").val();
            		if(v_base == ""){
            			$("#base_cost_error").html("基本费用不能为空！");
            			$("#base_cost_error").addClass("error_msg");
            			baseCost_flag = false;
            		}else{
            			//不为空，则判断是否为数字
            			var notNumber = isNaN(v_base);
            			if(!notNumber){//是数字
            				//判断是否在范围内
            				if(parseFloat(v_base) >= 1 && parseFloat(v_base) <= 9999.99){
            					$("#base_cost_error").html("格式正确！");
            					$("#base_cost_error").removeClass("error_msg");
            					baseCost_flag = true;
            				}else{
            					$("#base_cost_error").html("基本费用必须为0-9999.99之间的数字！");
            					$("#base_cost_error").addClass("error_msg");
            					baseCost_flag = false;
            				}
            			}else{
            				$("#base_cost_error").html("基本费用必须为数字！");
            				$("#base_cost_error").addClass("error_msg");
            				baseCost_flag = false;
            			}
            		}
            	}else{
            		//v_type == "3"的情况:只需要单位费用，别的可以为空
            		baseDuration_flag = true;
            		baseCost_flag = true;
            		unitCost_flag = false;
            		var v_unit = $("#unit_cost").val();
            		if(v_unit == ""){
            			$("#unit_cost_error").html("单位费用不能为空！");
            			$("#unit_cost_error").addClass("error_msg");
            			unitCost_flag = false;
            		}else{
            			//判断是否为数字
            			var notNumber = isNaN(v_unit);
            			if(!notNumber){//如果是数字
            				if(parseFloat(v_unit) >= 1 && parseFloat(v_unit) <= 9999.99){
            					$("#unit_cost_error").html("格式正确！");
            					$("#unit_cost_error").removeClass("error_msg");
            					unitCost_flag = true;
            				}else{
            					$("#unit_cost_error").html("单位费用必须为1-9999.99之间的数字！");
            					$("#unit_cost_error").addClass("error_msg");
            					unitCost_flag = false;
            				}
            			}else{
            				//如果不是数字
            				$("#unit_cost_error").html("单位费用必须为数字！");
            				$("#unit_cost_error").addClass("error_msg");
            				unitCost_flag = false;
            			}
            		}
            	}
            	return baseDuration_flag && baseCost_flag && unitCost_flag;
            }
        </script>
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="../images/logo.png" alt="logo" class="left"/>
            <a href="#">[退出]</a>            
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">
            <ul id="menu">
                <li><a href="../index.html" class="index_off"></a></li>
                <li><a href="../role/role_list.html" class="role_off"></a></li>
                <li><a href="../admin/admin_list.html" class="admin_off"></a></li>
                <li><a href="/netctoss/fee/list/1" class="fee_on"></a></li>
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
            <div id="save_result_info" class="save_fail">保存失败，资费名称重复！</div>
            <form action="/netctoss/fee/add" method="post" 
            	class="main_form" onsubmit="return doSubmit();">
                <div class="text_info clearfix"><span>资费名称：</span></div>
                <div class="input_info">
                    <input type="text" class="width300" value="" name="name" id="name"/>
                    <span class="required">*</span>
                    <div class="validate_msg_short" id="name_error">50长度的字母、数字、汉字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>资费类型：</span></div>
                <div class="input_info fee_type">
                    <input type="radio" name="cost_type"  value="1" id="monthly" onclick="feeTypeChange(1);" />
                    <label for="monthly">包月</label>
                    <input type="radio" name="cost_type"  value="2" checked="checked" id="package" onclick="feeTypeChange(2);" />
                    <label for="package">套餐</label>
                    <input type="radio" name="cost_type" value="3" id="timeBased" onclick="feeTypeChange(3);" />
                    <label for="timeBased">计时</label>
                </div>
                <div class="text_info clearfix"><span>基本时长：</span></div>
                <div class="input_info">
                    <input type="text" value="" class="width100" name="base_duration" id="base_duration"/>
                    <span class="info">小时</span>
                    <span class="required" id="star_base">*</span>
                    <div class="validate_msg_long" id="base_duration_error">1-600之间的整数</div>
                </div>
                <div class="text_info clearfix"><span>基本费用：</span></div>
                <div class="input_info">
                    <input type="text" value="" class="width100" name="base_cost" id="base_cost"/>
                    <span class="info">元</span>
                    <span class="required" id="star_cost">*</span>
                    <div class="validate_msg_long" id="base_cost_error">0-99999.99之间的数值</div>
                </div>
                <div class="text_info clearfix"><span>单位费用：</span></div>
                <div class="input_info">
                    <input type="text" value="" class="width100" name="unit_cost" id="unit_cost"/>
                    <span class="info">元/小时</span>
                    <span class="required" id="star_unit">*</span>
                    <div class="validate_msg_long" id="unit_cost_error">0-99999.99之间的数值</div>
                </div>
                <div class="text_info clearfix"><span>资费说明：</span></div>
                <div class="input_info_high">
                    <textarea class="width300 height70" name="descr"></textarea>
                    <div class="validate_msg_short" id="descr_error">100长度的字母、数字、汉字和下划线的组合</div>
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
