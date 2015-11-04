<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="/netctoss/styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="/netctoss/styles/global_color.css" />
        <script type="text/javascript" src="/netctoss/js/jquery-1.11.1.js"></script>
        <script language="javascript" type="text/javascript">
            //月租排序按钮的点击事件
            function monthlySort(btnObj) {
            	
                if (btnObj.className == "sort_desc"){
                    btnObj.className = "sort_asc";
                  	window.location = "/netctoss/fee/monthly_sortAsc/sort_asc/monthly_asc/1";
                }else{
                    btnObj.className = "sort_desc";
                    window.location = "/netctoss/fee/monthlySortDesc/sort_desc/monthly_desc/1";
                }
                
            }
			//基费排序按钮的点击事件
			function baseSort(btnObj) {
                if (btnObj.className == "sort_desc"){
                    btnObj.className = "sort_asc";
                  	window.location= "/netctoss/fee/base_sortAsc/sort_asc/base_asc/1";
                }else{
                    btnObj.className = "sort_desc";
                    window.location = "/netctoss/fee/base_sortDesc/sort_desc/base_desc/1";
                }
                
            }
			//时长排序按钮的点击事件
			function durationSort(btnObj) {
                if (btnObj.className == "sort_desc"){
                    btnObj.className = "sort_asc";
                  	window.location = "/netctoss/fee/dura_sortAsc/sort_asc/duration_asc/1";
                }else{
                    btnObj.className = "sort_desc";
                  	window.location = "/netctoss/fee/dura_sortDesc/sort_desc/duration_desc/1";
                }
                
            }
			
			
            //启用
            function startFee(id) {
                var r = window.confirm("确定要启用此资费吗？资费启用后将不能修改和删除。");
                //确定启用资费
                if(r){
                	$.ajax({
                		type:"PUT",
                		url:"/netctoss/fee/start/"+id,
                		success:function(ok){
                			if(ok){
                				window.location="/netctoss/fee/list/1";
                			}
                		}
                	});
                }
            }
            //删除
            function deleteFee(id) {
                var r = window.confirm("确定要删除此资费吗？");
                if(r){
                	//确定删除,下面的方式使用的是get请求发送。
                	//window.location="/netctoss/fee/" + id;
                	//在这里不能使用form标记，method=RequestMethod.DELETE,所以使用AJAX
                	$.ajax({
                		url:"/netctoss/fee/" + id,
                		type:"delete",
                		success:function(ok){
                			if(ok){
                				window.location="/netctoss/fee/list/1";
                			}
                		}
                	});
                }
            }
            
            var page = "${page}";
            function nextPage(page){
            	page++;
            	window.location= page;
            }
            
            function prePage(page){
            	page--;
            	if(page <= 0){
            		page = 1;
            	}
            	window.location=  page;
            }
            
        </script>        
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="/netctoss/images/logo.png" alt="logo" class="left"/>
			<span>当前账号：<b>scott</b></span>
            <a href="#">[退出]</a>            
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">                        
            <ul id="menu">
                <li><a href="../login/login.from" class="index_off"></a></li>
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
            <form action="" method="">
                <!--排序-->
                <div class="search_add">
                    <div>
                        <input id="monthly" type="button" value="月租" class="${jsClass}" onclick="monthlySort(this);" />
                        <input id="base" type="button" value="基费" class="${jsClass}" onclick="baseSort(this);" />
                        <input id="duration" type="button" value="时长" class="${jsClass}" onclick="durationSort(this);" />
                    </div>
                    <input type="button" value="增加" class="btn_add" onclick="location.href='../toadd';" />
                </div> 
                <!--启用操作的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    删除成功！
                </div>    
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th>资费ID</th>
                            <th class="width100">资费名称</th>
                            <th>基本时长</th>
                            <th>基本费用</th>
                            <th>单位费用</th>
                            <th>创建时间</th>
                            <th>开通时间</th>
                            <th class="width50">状态</th>
                            <th class="width200"></th>
                        </tr>   
                        <c:forEach items="${costs}" var="s">
                        	<tr>
                            <td>${s.id}</td>
                            <td><a href="fee_detail.html">${s.name}</a></td>
                            <td>${s.base_duration}</td>
                            <td>${s.base_cost}</td>
                            <td>${s.unit_cost}</td>
                            <td>${s.creatime}</td>
                            <td>${s.startime}</td>
                            <td>${s.status=="1"?"开通":"暂停"}</td>
                            <td>      
                            	<c:if test='${s.status=="0"}'>                       
                                	<input type="button" value="启用" class="btn_start" onclick="startFee(${s.id});" />
                                	<input type="button" value="修改" class="btn_modify" onclick="location.href='/netctoss/fee/${s.id}/toedit/${page}';" />
                                	<input type="button" value="删除" class="btn_delete" onclick="deleteFee(${s.id});" />
                            	</c:if>   
                            </td>
                        </tr>
                        </c:forEach>                   
                        
                    </table>
                    <p>业务说明：<br />
                    1、创建资费时，状态为暂停，记载创建时间；<br />
                    2、暂停状态下，可修改，可删除；<br />
                    3、开通后，记载开通时间，且开通后不能修改、不能再停用、也不能删除；<br />
                    4、业务账号修改资费时，在下月底统一触发，修改其关联的资费ID（此触发动作由程序处理）
                    </p>
                </div>
                <!--分页-->
                <div id="pages">
                <c:choose>
                	<%--正常请求的上一页显示 --%>
                	<c:when test="${flag==true}">
		                	<c:choose>
		                		<c:when test="${page > 1}">
		                			<a href="javascript:;" onclick="prePage(page)">上一页</a>
		                		</c:when>
		                		<c:otherwise>
		                			<a>上一页</a>
		                		</c:otherwise>
		                	</c:choose>
		                	<c:forEach var="i" begin="1" end="${totalPage}">
		                		<c:choose>
		                			<c:when test="${i==page}">
		                				<a href="${i}" class="current_page">${i}</a>
		                			</c:when>
		                			<c:otherwise>
		                				<a href="${i}">${i}</a>
		                			</c:otherwise>
		                		</c:choose>
		                	</c:forEach>
		               		<c:choose>
		               			<c:when test="${page < totalPage}">
		               				<a href="javascript:;" onclick="nextPage(page)">下一页</a>
		               			</c:when>
		               			<c:otherwise>
		               				<a>下一页</a>
		               			</c:otherwise>
		               		</c:choose>
 						</c:when>
 						
 						<c:otherwise>
 							<c:if test='${type == "monthly_desc"}'>
 							<!-- 按月租降序排序：上一页 sort_desc -->
 								<c:choose>
 									<c:when test="${page > 1}">
 											<!--/netctoss/fee/monthlySortDesc/sort_desc/monthly_desc/${page-1} -->
 										<a href="/netctoss/fee/monthlySortDesc/sort_desc/monthly_desc/${page-1}">上一页</a>
 									</c:when>
 									<c:otherwise><a>上一页</a></c:otherwise>
 								</c:choose>
 								<!-- 中间页 sort_desc -->
 								<c:forEach var="i" begin="1" end="${totalPage}">
			                		<c:choose>
			                			<c:when test="${i==page}">
			                				<a href="/netctoss/fee/monthlySortDesc/sort_desc/monthly_desc/${i}" class="current_page">${i}</a>
			                			</c:when>
			                			<c:otherwise>
			                				<a href="/netctoss/fee/monthlySortDesc/sort_desc/monthly_desc/${i}">${i}</a>
			                			</c:otherwise>
			                		</c:choose>
		                		</c:forEach>
		                	<!-- 下一页 sort_desc -->
		                		<c:choose>
		               			<c:when test="${page < totalPage}">
		               				<a href="/netctoss/fee/monthlySortDesc/sort_desc/monthly_desc/${page+1}">下一页</a>
		               			</c:when>
		               			<c:otherwise>
		               				<a>下一页</a>
		               			</c:otherwise>
		               		</c:choose>
 							</c:if>
 							
 							<c:if test='${type=="monthly_asc"}'>
 								<!-- 按月租升序排序：上一页 sort_asc -->
 								<c:choose>
 									<c:when test="${page > 1}">
 									<!-- /netctoss/fee/monthly_sortAsc/sort_asc/monthly_asc/${page}  -->
 										<a href="/netctoss/fee/monthly_sortAsc/sort_asc/monthly_asc/${page-1}">上一页</a>
 									</c:when>
 									<c:otherwise><a>上一页</a></c:otherwise>
 								</c:choose>
 								<!-- 中间页 sort_desc -->
 								<c:forEach var="i" begin="1" end="${totalPage}">
			                		<c:choose>
			                			<c:when test="${i==page}">
			                				<a href="/netctoss/fee/monthly_sortAsc/sort_asc/monthly_asc/${i}" class="current_page">${i}</a>
			                			</c:when>
			                			<c:otherwise>
			                				<a href="/netctoss/fee/monthly_sortAsc/sort_asc/monthly_asc/${i}">${i}</a>
			                			</c:otherwise>
			                		</c:choose>
		                		</c:forEach>
		                		<!-- 下一页 sort_desc -->
		                		<c:choose>
		               			<c:when test="${page < totalPage}">
		               				<a href="/netctoss/fee/monthly_sortAsc/sort_asc/monthly_asc/${page+1}">下一页</a>
		               			</c:when>
		               			<c:otherwise>
		               				<a>下一页</a>
		               			</c:otherwise>
		               		</c:choose>
 							</c:if>
 							<!--  下面是根据基费排序，升序  -->
 							<c:if test='${type=="base_asc" }'>
 								<!-- 按基费升序排序：上一页 base_asc -->
 								<c:choose>
 									<c:when test="${page > 1}">
 									<!-- /netctoss/fee/base_sortAsc/sort_asc/base_asc/${page} -->
 										<a href="/netctoss/fee/base_sortAsc/sort_asc/base_asc/${page-1}">上一页</a>
 									</c:when>
 									<c:otherwise><a>上一页</a></c:otherwise>
 								</c:choose>
 								<!-- 中间页 sort_asc -->
	 								<c:forEach var="i" begin="1" end="${totalPage}">
				                		<c:choose>
				                			<c:when test="${i==page}">
				                				<a href="/netctoss/fee/base_sortAsc/sort_asc/base_asc/${i}" class="current_page">${i}</a>
				                			</c:when>
				                			<c:otherwise>
				                				<a href="/netctoss/fee/base_sortAsc/sort_asc/base_asc/${i}">${i}</a>
				                			</c:otherwise>
				                		</c:choose>
			                		</c:forEach>
		                		<!-- 下一页 sort_asc -->
		                		<c:choose>
		               			<c:when test="${page < totalPage}">
		               				<a href="/netctoss/fee/base_sortAsc/sort_asc/base_asc/${page+1}">下一页</a>
		               			</c:when>
		               			<c:otherwise>
		               				<a>下一页</a>
		               			</c:otherwise>
		               			</c:choose>
 							</c:if>
 							<!--  下面是根据基费排序，降序  -->
 							<c:if test='${type=="base_desc" }'>
 								<!-- 按基费升序排序：上一页 base_desc -->
 								<c:choose>
 									<c:when test="${page > 1}">
 									<!-- /netctoss/fee/base_sortDesc/sort_desc/base_desc/${page} -->
 										<a href="/netctoss/fee/base_sortDesc/sort_desc/base_desc/${page-1}">上一页</a>
 									</c:when>
 									<c:otherwise><a>上一页</a></c:otherwise>
 								</c:choose>
 								<!-- 中间页 sort_desc -->
	 								<c:forEach var="i" begin="1" end="${totalPage}">
				                		<c:choose>
				                			<c:when test="${i==page}">
				                				<a href="/netctoss/fee/base_sortDesc/sort_desc/base_desc/${i}" class="current_page">${i}</a>
				                			</c:when>
				                			<c:otherwise>
				                				<a href="/netctoss/fee/base_sortDesc/sort_desc/base_desc/${i}">${i}</a>
				                			</c:otherwise>
				                		</c:choose>
			                		</c:forEach>
		                		<!-- 下一页 sort_desc -->
		                		<c:choose>
		               			<c:when test="${page < totalPage}">
		               				<a href="/netctoss/fee/base_sortDesc/sort_desc/base_desc/${page+1}">下一页</a>
		               			</c:when>
		               			<c:otherwise>
		               				<a>下一页</a>
		               			</c:otherwise>
		               			</c:choose>
 							</c:if>
 							
 							<!-- 按照时长排序，降序 -->
 							<c:if test='${type=="duration_desc"}'>
 								<c:choose>
 									<c:when test="${page > 1}">
 									<!-- /netctoss/fee/dura_sortDesc/sort_desc/duration_desc/${page} -->
 										<a href="/netctoss/fee/dura_sortDesc/sort_desc/duration_desc/${page-1}">上一页</a>
 									</c:when>
 									<c:otherwise><a>上一页</a></c:otherwise>
 								</c:choose>
 								<c:forEach var="i" begin="1" end="${totalPage}">
				                		<c:choose>
				                			<c:when test="${i==page}">
				                				<a href="/netctoss/fee/dura_sortDesc/sort_desc/duration_desc/${i}" class="current_page">${i}</a>
				                			</c:when>
				                			<c:otherwise>
				                				<a href="/netctoss/fee/dura_sortDesc/sort_desc/duration_desc/${i}">${i}</a>
				                			</c:otherwise>
				                		</c:choose>
			                	</c:forEach>
			                	<c:choose>
		               			<c:when test="${page < totalPage}">
		               				<a href="/netctoss/fee/dura_sortDesc/sort_desc/duration_desc/${page+1}">下一页</a>
		               			</c:when>
		               			<c:otherwise>
		               				<a>下一页</a>
		               			</c:otherwise>
		               			</c:choose>	
			                	
 							</c:if>
 							
 							<!--  按照时长排序，升序 -->
 							<c:if test='${type=="duration_asc"}'>
 								<c:choose>
 									<c:when test="${page > 1}">
 										<a href="/netctoss/fee/dura_sortAsc/sort_asc/duration_asc/${page-1}">上一页</a>
 									</c:when>
 									<c:otherwise><a>上一页</a></c:otherwise>
 								</c:choose>
 								<c:forEach var="i" begin="1" end="${totalPage}">
				                		<c:choose>
				                			<c:when test="${i==page}">
				                				<a href="/netctoss/fee/dura_sortAsc/sort_asc/duration_asc/${i}" class="current_page">${i}</a>
				                			</c:when>
				                			<c:otherwise>
				                				<a href="/netctoss/fee/dura_sortAsc/sort_asc/duration_asc/${i}">${i}</a>
				                			</c:otherwise>
				                		</c:choose>
			                	</c:forEach>
			                	<c:choose>
		               			<c:when test="${page < totalPage}">
		               				<a href="/netctoss/fee/dura_sortAsc/sort_asc/duration_asc/${page+1}">下一页</a>
		               			</c:when>
		               			<c:otherwise>
		               				<a>下一页</a>
		               			</c:otherwise>
		               			</c:choose>	
			                	
 							</c:if>
 						</c:otherwise>
                	</c:choose>
                </div>
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
            <p>版权所有(C)加拿大达内IT培训集团公司 </p>
        </div>
    </body>
</html>
