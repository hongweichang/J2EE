<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@include file="../common/taglibs.jsp"%>
<html>
	<head>
		<%@include file="../common/meta.jsp"%>
		<title>
          <s:property value="getText('menu.cl')" />&nbsp;|&nbsp;<s:property value="getText('menu.cl.handheld')" />
        </title>
		<link rel="stylesheet" href="<s:url value='/styles/common.css' />" type="text/css" media="all" />
	</head>
	<body>
		<%@include file="../common/menu.jsp"%>
		<%@include file="handheldDevice_validate.jsp"%>
		<s:form id="handheldDevice_form" action="queryHandheldDevice" method="post">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top">
						<table width="100%" border="0" cellspacing="5" cellpadding="0">
							<s:if test="#session.perUrlList.contains('111_0_2_4_1')">
								<tr>
									<td class="reportOnline2">
										<table width="100%" border="0" cellspacing="8" cellpadding="0">
											<tr>
												<td>
													<table border="0" cellspacing="4" cellpadding="0">
														<tr>
															<td>手机IMEI号：</td>	
															<td align="left">
																<s:textfield id="cellPhoneImei" name="handheldDeviceInfo.cellPhoneImei" cssClass="input120" />&nbsp;&nbsp;
															</td>
															<td>设备编号：</td>
															<td>
																<s:textfield id="handheldDeviceNo" name="handheldDeviceInfo.handheldDeviceNo" cssClass="input120" />&nbsp;&nbsp;
															</td>
															<td>手机号：</td>
															<td>
																<s:textfield id="cellPhone" name="handheldDeviceInfo.cellPhone" cssClass="input120" />&nbsp;&nbsp;
																<s:property value="getText('enterprise.info.ENTERPRISE_CODE')" />：
																<s:textfield id="enterpriseCode" name="handheldDeviceInfo.enterpriseCode" cssClass="input120" />&nbsp;&nbsp;
                                                            </td>
                                                            <td>
                                                                <a href="#" onclick="searchList()" class="sbutton">
																   <s:property value="getText('btn.query')" /> 
																</a>
															</td>
														    <td>&nbsp;</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</s:if>

							<tr>
								<td valign="top">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td class="reportOnline">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td class="titleStyle">
															<table width="100%" border="0" cellspacing="0"
																cellpadding="0">
																<tr>
																	<td width="30%" class="titleStyleZi">
																		手持设备注册信息表
																	</td>
																	<td width="69%" align="right">
																		<s:if
																			test="#session.perUrlList.contains('111_0_2_4_2')">
																			<div class="buhuanhangbut">
																				<a href="<s:url value='/cl/addHandheldDeviceBefore.shtml' />"
																				   class="btnAdd"
																				   title="<s:property value="getText('msg.add')" />">
																				</a>
																			</div>
																		</s:if>
																	</td>
																	<td width="1%">&nbsp;
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td class="reportInline">
															<div id="message">
															  <div id="empDiv">
																<s:actionerror theme="mat" />
																<s:fielderror theme="mat" />
																<s:actionmessage theme="mat" />
														      </div>
															</div>
															<div>
															  <table id="handheldDeviceList" width="100%"  cellspacing="0">
							                                  </table>
															</div>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>

						</table>
					</td>
				</tr>
			</table>
		</s:form>
		<%@include file="../common/copyright.jsp"%>
	</body>
</html>