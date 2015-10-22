<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" contentType="text/html;charset=utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
	<title><s:property value="getText('common.title')" /></title>
	<%@include file="/WEB-INF/jsp/common/css/common_css.jsp"%>
	<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all" />
	<style type="text/css">
	.lineStyle {
		background: url(../images/wline.gif) repeat-x top left;
		float: left;
		height: 0px;
		padding-top: 10px;
		width: 100%;
		color: #2a2a2a;
	}
	.mypreview {
	 max-width:120px;
	 max-height:140px;
	 width:expression_r_r(document.body.clientWidth > 120? "120px": "auto" ); 
	 height:expression_r_r(document.body.scrollHeight > 400 ? "140px" : "auto" );
	}
	</style>
</head>
<body >
<div id="wrapper">
	<%@include file="/WEB-INF/jsp/common/header.jsp"%>
	<div id="content">
	<s:form id="feedBackDetail" action="readyPage.shtml" method="post" enctype="multipart/form-data">
		<s:hidden name="page" />
		<s:hidden name="pageSize" />
		<s:hidden id="empName" name="empName" />
		<s:hidden id="feedBackDate" name="feedBackDate" />
		<s:hidden id="feedBackStatus" name="feedBackStatus" />
		<div class="toolbar">
		    <div class="contentTil">员工信息</div>
		</div>
		<div id="studentDiv" style="overflow:auto;clear:both;">
		<table id="studentTable" width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td align="center"><s:actionerror theme="mat" /> <s:fielderror
							theme="mat" /> <s:actionmessage theme="mat" /></td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td valign="top">
				<table class="msgBox2" width="720" border="0" align="center"
					cellpadding="0" cellspacing="0">
					<tr>
						<td height="32"><span class="msgTitle">问题反馈明细</span></td>
					</tr>
					<tr>
						<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="100px" height="28" align="right">
										反馈人：
									</td>
									<td width="160px" class="fsBlack">
										<s:textfield id="emp_name" name="feedBackMsg.owner" cssStyle="border: 0px; background: #EEEEEE;" maxlength="16" readonly="readonly" />
									</td>
									<td width="100px" height="28" align="right">员工号：</td>
									<td align="left">
										<s:textfield id="emp_license" name="feedBackMsg.empCode" maxlength="16" readonly="readonly" cssStyle="border: 0px; background: #EEEEEE;"/>
									</td>
								</tr>
								
								<tr>
									<td width="100px" height="28" align="right">反馈时间：</td>
									<td align="left" colspan="3">
										<s:textfield id="feedBackDateP" name="feedBackMsg.suggestDate" maxlength="16" readonly="readonly" cssStyle="border: 0px; background: #EEEEEE;"/>
									</td>
									<!-- 
									<td width="100px" height="28" align="right">状态：</td>
									<td align="left">
										<s:textfield id="feedBackStatusP" name="feedBackMsg.feedBackStatus" maxlength="16" readonly="readonly" cssStyle="border: 0px; background: #EEEEEE;"/>
									</td>
									 -->
								</tr>
								<tr>
									<td width="100px" height="28" align="right" style="padding-top: 3px;">
										备注：
									</td>
									<td colspan="3" rowspan="3">
											<s:textarea id="remarks1" name="feedBackMsg.content" rows="10"  cols="61" ></s:textarea>
									</td>
								</tr>
								<tr>
									<td width="100px" height="58" align="right" >
									</td>
								</tr>
								<tr>
									<td width="100px" height="58" align="right" >
									</td>
								</tr>
								<tr height="30px">
									<td></td>
<!-- 									还可以输入<span id="textleft" ></span>个字 -->
									<td colspan=2  align="left" style="color:green;margin-left:5px" ></td>
								</tr>
								
							</table>
						</td>
					</tr>
					<tr>
					    <!-- <td height="5px" class="lineStyle">
						 -->
						 <td height="5px" >
						</td>
					</tr>

					<tr>
					    <td height="5px" >
						</td>
					</tr>
					
					<tr>
						<td class="btnBar">
							<a href="javascript:void(0)" onclick="goBack();" class="buttontwo">
								<s:text name="button.cancle" />
							</a>
<%-- 							<s:if test="#session.perUrlList.contains('111_3_5_12')"> --%>
<%-- 								<s:url id="delete" action="employee_delete"> --%>
<%-- 									<s:param name="studentInfo.student_id" value="studentInfo.student_id" /> --%>
<%-- 									<s:param name="page" value="page" /> --%>
<%-- 									<s:param name="pageSize" value="pageSize" /> --%>
<%-- 								</s:url> --%>
<%-- 								<a href="#"	onclick="Wit.delConfirm('${delete}', '<s:text name="common.delete.confirm" />')" class="buttontwo"> --%>
<%-- 									<s:text name="button.delete" /> --%>
<!-- 								</a> -->
<%-- 						    </s:if>  --%>
<%-- 						    <s:if test="#session.perUrlList.contains('111_3_5_12')"> --%>
<!-- 								<a href="#" class="buttontwo" onClick="submitAlterForm();"> -->
<%-- 									<s:text name="button.edit" /> --%>
<!-- 								</a> -->
<%-- 							</s:if> --%>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</div>
		</s:form>
	</div>
	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</div>
<%@include file="/WEB-INF/jsp/common/js/common_js.jsp"%>
<script>
	function goBack(){
		$("#feedBackDetail").submit();
	}
jQuery(function() {
	//获取页面高度
	function get_page_height() {
	  var min_height = jQuery.data(window, 'mk-autoresize').options.min_height;
	  return (parseInt(jQuery(window).height())<= min_height) ? min_height : jQuery(window).height();
	}
	//获取页面宽度
	function get_page_width() {
	  var min_width = jQuery.data(window, 'mk-autoresize').options.min_width;
	  return (parseInt(jQuery(window).width())<= min_width) ? min_width : jQuery(window).width();
	}
	 
	//计算控件宽高
	function testWidthHeight(){
		var h = get_page_height();
		var w = get_page_width();
		jQuery(".bDiv").height(h-330);
		jQuery(".flexigrid").width(w-23);
		 
		jQuery(window).mk_autoresize({
		       height_include: '#content',
		       height_exclude: ['#header', '#footer'],
		       height_offset: 0,
		       width_include: ['#header', '#content', '#footer'],
		       width_offset: 0
		    });

		 h = get_page_height();
		 w = get_page_width();
		 jQuery(".bDiv").height(h-330);
		 jQuery(".flexigrid").width(w-23);
		 
	}
});
</script>
</body>
</html>
