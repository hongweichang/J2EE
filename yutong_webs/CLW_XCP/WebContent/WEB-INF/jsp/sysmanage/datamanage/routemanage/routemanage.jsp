<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:text name="common.title" /></title>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
</head>
<body>
	<div id="wrapper">
		<%@include file="/WEB-INF/jsp/common/header.jsp"%>
		<div id="content">
			<s:form id="route_form" action="" method="post">
			</s:form>
			<s:hidden id="addSiteString" value=""></s:hidden>
			<s:hidden id="delSiteString" value=""></s:hidden>
			<table  width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="36" valign="top" background="../images/tree_tabBg.gif">
								<div class="toolbar">
									<div class="contentTil"><s:text name="menu3.xlgl" /></div>
								</div>
							</td>
						</tr>
					</table>
					<table width="100%" border="0" cellspacing="5" cellpadding="0">
						<tr>
							<td class="reportOnline2">
							<table width="100%" border="0" cellspacing="8" cellpadding="0">
								<tr>
									<td>
									 <s:if test="#session.perUrlList.contains('111_3_3_6_1')">
									<table border="0" cellspacing="4" cellpadding="0">
										<tr>
											<td>
												<%-- 查询 --%>
												<s:text name="routeinfo.route_name" />：
												<s:textfield id="route_name" name="routeInfo.route_name" maxlength="14" cssClass="input120" onkeypress="if(event.keyCode==13){searchRoute();}"/>&nbsp;&nbsp;
												<s:text name="routeinfo.route_incharge_person" />：
												<s:textfield id="route_incharge_person" name="routeinfo.route_incharge_person" maxlength="12" cssClass="input120" onkeypress="if(event.keyCode==13){searchRoute();}"/>
											</td>
											<td><a href="#" onclick="searchRoute()" class="sbutton"><s:text name="common.chaxun" /></a></td>
										</tr>
									</table>
									</s:if>
									</td>
								</tr>
							</table>		
							</td>
						</tr>
						<tr>
						  <td valign="top">
						   <table width="100%" border="0" cellspacing="0" cellpadding="0" class="reportOnline">
						     <tr>
						        <td>
						        <table width="100%" border="0" cellspacing="0" cellpadding="0">
						          <tr>
			                         <td class="titleStyle">
				                         <table width="100%" border="0" cellspacing="0" cellpadding="0">
				                             <tr>
				                               <td width="30%" class="titleStyleZi"><s:text name="routeinfo.info" /></td>
				                               <td width="69%"align="right">
					                               	<%-- 添加 --%>
					                               <s:if test="#session.perUrlList.contains('111_3_3_6_2')">
					                               		<div class="buhuanhangbut"><a href="<s:url value='/route/addrouteb.shtml' />" class="btnAdd" title="<s:text name="button.create" />"></a></div>
					                               </s:if>
				                               </td>
				                               <td width="1%">&nbsp;</td>
				                             </tr>
				                          </table>
			                          </td>
			                      </tr>
						        </table>
						        </td>
						     </tr>
						     <tr>
						        <td class="reportInline">
						          	<div id="Below_new" style="text-align:center"><s:actionmessage cssStyle="color:green"/><s:actionerror cssStyle="color:red"/></div>
						          	<%-- 列表内容 --%>
									<table id="gala"></table>
						        </td>
						     </tr>
						   </table>
						  </td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			
			</div>
		<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
	</div>
	<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
	<script type="text/javascript" language="JavaScript1.2" src="<s:url value='/scripts/flexigrid/flexigrid2.0.js'/>"></script>
	<SCRIPT type="text/javascript">
		var p_Route_ID="";
		var p_Organization_ID="";
		var upDownFlag="";
	
		jQuery(function() {
			 var gala = jQuery('#gala');
			 gala.flexigrid({
			  url: '<s:url value="/routeGrid/routeList.shtml" />', 
			  dataType: 'json',    //json格式
			  height: 375,
			  width:2000,
			  colModel : [ 
			        {display: '<s:text name="routeinfo.route_name" />', name : 'route_name', width : (get_page_width()-150)*100/700, sortable : true, escape:true,align: 'center'},
			        {display: '<s:text name="routeinfo.route_incharge_person" />', name : 'route_incharge_person', width : (get_page_width()-150)*80/700, sortable : true, escape:true,align: 'center'},
			        {display: '联系电话', name : 'route_phone', width : (get_page_width()-150)*80/700, sortable : true, escape:true,align: 'center'},
			        {display: '上学站点数', name : 'upsitenum', width : (get_page_width()-150)*80/700, sortable : true, escape:true,align: 'center'},
			        {display: '放学站点数', name : 'downsitenum', width : (get_page_width()-150)*80/700, sortable : true, escape:true,align: 'center'},
			        {display: '线路规划', name : '', width : (get_page_width()-150)*80/700, sortable : true, align: 'center',hide:true,toggle:false},
			        {display: '线路简介', name : 'route_remark', width : (get_page_width()-150)*200/700, sortable : true, escape:true,align: 'center'},
	  				{display: '&nbsp;', name : 'update', width : 40, sortable : false, align: 'center', process: reWriteLink}
	  	            <s:if test="#session.perUrlList.contains('111_3_3_6_4')">
					,{display: '&nbsp;', name : 'delete', width : 40, sortable : false, align: 'center', process: reWriteDelLink}
		            </s:if>
			        ],
			       sortname: 'route_name',
			       sortorder: 'asc',  //升序OR降序
			       sortable: true,   //是否支持排序
			       striped :true,     // 是否显示斑纹效果，默认是奇偶交互的形式  
			       usepager :true,  //是否分页
			       resizable: false,  //是否可以设置表格大小
			       useRp: true,    // 是否可以动态设置每页显示的结果数
			       rp: 20,  //每页显示记录数
			       rpOptions : [10,20,50,100],   // 可选择设定的每页结果数
			       showTableToggleBtn: true,  // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错 
			       onSuccess:function(){
			       		//设置所有行高为20px
				  	    //jQuery("tr[id^='row'] div").css('height','20px').css('line-height','20px');
				    	return true;
				   }
			     });
			});
			//转换链接
			function reWriteLink(tdDiv,pid,row){
				return '<a href="route_edit_form.shtml?routeInfo.route_id=' + pid + '">修改</a>';
			}
			//转换链接
			function reWriteDelLink(tdDiv,pid){
				return '<a href="#" onclick="delRouteInfo(' + pid + ')">删除</a>';
			}
	
			function get_cell_text(pid, cell_idx){
				return jQuery('#row' + pid).children('td').eq(cell_idx).eq(0).text();
			}
			
			//查询
			function searchRoute() {
				document.getElementById('Below_new').style.display='none';
				jQuery('#gala').flexOptions({
					newp: 1,
					params: [{name: 'routeInfo.route_name', value: formatSpecialChar(jQuery('#route_name').val()) },
					         {name: 'routeInfo.route_incharge_person', value: formatSpecialChar(jQuery('#route_incharge_person').val()) }]
				});
				jQuery('#gala').flexReload();
			}
	
			//页面自适应
			(function (jQuery){
				jQuery(window).resize(function(){
					testWidthHeight();
				});
				jQuery(window).load(function (){
					testWidthHeight();
				});
			})(jQuery);
			
			function get_page_height(){
				var min_height=jQuery.data(window,'mk-autoresize').options.min_height;
				return (parseInt(jQuery(window).height())<=min_height)?min_height:jQuery(window).height();
			}
			function get_page_width(){
				var min_width=jQuery.data(window,'mk-autoresize').options.min_width;
				return (parseInt(jQuery(window).width())<=min_width)?min_width:jQuery(window).width();
			}
			 
			//计算控件宽高
			function testWidthHeight(){
				jQuery(window).mk_autoresize({
	            	height_include: '#content',
	            	height_exclude: ['#header', '#footer'],
	            	height_offset: 0,
	            	width_include: ['#header', '#content', '#footer'],
	            	width_offset: 0
	      		});
				var h = get_page_height();
				var w = get_page_width();
				jQuery(".flexigrid").width(w-30);
				//jQuery("#reportTab").height(h-100);
				jQuery(".bDiv").height(h-330);
				jQuery(window).mk_autoresize({
	            	height_include: '#content',
	            	height_exclude: ['#header', '#footer'],
	            	height_offset: 0,
	            	width_include: ['#header', '#content', '#footer'],
	            	width_offset: 0
	      		});
			}
			
		 /** 删除线路信息 **/
		  function delRouteInfo(id) {
		    if(confirm("<s:property value="getText('routeinfo.delete.confirm')" />")) {
		    	document.getElementById("route_form").action="route_delete.shtml?routeInfo.route_id="+id;
		    	document.getElementById("route_form").submit(); 
		    }else{
		        return false;
		    }
		  }
	</script>
</body>
</html>